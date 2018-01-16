package com.softgarden.catchdolls.ui.login

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import com.softgarden.baselibrary.utils.EmptyUtil
import com.softgarden.baselibrary.utils.MD5Util
import com.softgarden.baselibrary.utils.ToastUtil
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.BuildConfig
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity
import com.softgarden.catchdolls.network.NetworkTransformer
import com.softgarden.catchdolls.network.RetrofitManager
import com.softgarden.catchdolls.network.SampleCallback
import com.softgarden.catchdolls.utils.CheckUtil
import com.trello.rxlifecycle2.android.ActivityEvent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_retrieve_pwd.*
import java.util.concurrent.TimeUnit

class RetrievePwdActivity : ToolbarActivity() {
    private var timer: Disposable? = null
    private var curCount: Int = 0

    companion object {
        private val COUNT = 60
        fun start(activity: Activity) {
            val starter = Intent(activity, RetrievePwdActivity::class.java)
            activity.startActivity(starter)
        }
    }

    override fun getLayoutId() = R.layout.activity_retrieve_pwd

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(getString(R.string.retrieve_pwd))
    }


    override fun initialize() {
        cbShowPwd.setOnCheckedChangeListener { buttonView, isChecked ->
            edtSetPwd.transformationMethod = if (isChecked) HideReturnsTransformationMethod.getInstance()
            else PasswordTransformationMethod.getInstance()
        }
        cbShowPwdAgain.setOnCheckedChangeListener { buttonView, isChecked ->
            edtPwdAgain.transformationMethod = if (isChecked) HideReturnsTransformationMethod.getInstance()
            else PasswordTransformationMethod.getInstance()
        }

        tvGetCode.setOnClickListener { getVerifyCode() }
        btnNext.setOnClickListener { checkVerifyCode() }
        btnFinish.setOnClickListener { resetPwd() }
    }

    /**
     * 获取验证码
     */
    private fun getVerifyCode() {
        val phone = edtPhone.text.toString().trim()
        if (CheckUtil.checkPhoneIllegal(phone)) return
        RetrofitManager.getLoginService()
                .getVerifyCode(phone, 2)
                .compose(NetworkTransformer(this))
                .subscribe(object : SampleCallback<String>() {
                    override fun onSuccess(data: String?) {
                        countTime()
                        if (BuildConfig.DEBUG) edtCode.setText(data)
                    }
                })
    }

    /**
     * 验证码倒计时
     */
    private fun countTime() {
        tvGetCode.isEnabled = false
        timer = Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe {
                    curCount++
                    if (curCount == COUNT) {
                        if (timer != null && !timer!!.isDisposed) timer!!.dispose()
                        tvGetCode.text = getString(R.string.get_verify_code)
                        tvGetCode.isEnabled = true
                        curCount = 0
                    } else {
                        tvGetCode.text = getString(R.string.get_code_count, (COUNT - curCount))
                    }
                }
    }

    /**
     * 校验验证码
     */
    private fun checkVerifyCode() {
        val phone = edtPhone.text.toString().trim()
        val code = edtCode.text.toString().trim()
        if (CheckUtil.checkPhoneIllegal(phone)) return
        if (EmptyUtil.isEmpty(code)) {
            ToastUtil.s(getString(R.string.input_verify_code))
            return
        }
        RetrofitManager.getLoginService()
                .checkVerifyCode(phone, 2, code)
                .compose(NetworkTransformer(this))
                .subscribe(object : SampleCallback<String>() {
                    override fun onSuccess(data: String?) {
                        llStepOne.visibility = View.GONE
                        llStepTwo.visibility = View.VISIBLE
                    }
                })
    }

    /**
     * 重置密码
     */
    private fun resetPwd() {
        val phone = edtPhone.text.toString().trim()
        val code = edtCode.text.toString().trim()
        val pwd = edtSetPwd.text.toString().trim()
        val pwdAgain = edtPwdAgain.text.toString().trim()
        if (CheckUtil.checkPwdIllegal(pwd)) return
        if (!TextUtils.equals(pwd, pwdAgain)) {
            ToastUtil.s(getString(R.string.pwd_not_same))
            return
        }
        val dm5Pwd = MD5Util.ToMD5NOKey(pwd)
        RetrofitManager.getLoginService()
                .resetPwd(phone, dm5Pwd, code)
                .compose(NetworkTransformer(this))
                .subscribe(object : SampleCallback<String>() {
                    override fun onSuccess(data: String?) {
                        ToastUtil.s(getString(R.string.resetpwd_succeed))
                        finish()
                    }
                })
    }

}
