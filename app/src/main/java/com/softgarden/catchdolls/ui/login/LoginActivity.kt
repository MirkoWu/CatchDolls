package com.softgarden.catchdolls.ui.login

import android.app.Activity
import android.content.Intent
import com.softgarden.baselibrary.utils.MD5Util
import com.softgarden.baselibrary.utils.ToastUtil
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.BuildConfig
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity
import com.softgarden.catchdolls.bean.UserBean
import com.softgarden.catchdolls.network.NetworkTransformer
import com.softgarden.catchdolls.network.RetrofitManager
import com.softgarden.catchdolls.network.SampleCallback
import com.softgarden.catchdolls.ui.home.HomeActivity
import com.softgarden.catchdolls.utils.CheckUtil
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : ToolbarActivity() {


    companion object {

        fun start(activity: Activity) {
            val starter = Intent(activity, LoginActivity::class.java)
            //starter.putExtra("eventId", "")
            starter.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            activity.startActivity(starter)
        }
    }

    override fun getLayoutId() = R.layout.activity_login


    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(R.string.app_name).setBackButton(R.mipmap.close)
    }


    override fun initialize() {
        if (BuildConfig.DEBUG) {
            edtPhone.setText("15088134055")
            edtPwd.setText("123456")
        }
        btnLogin.setOnClickListener { login() }
        tvRegister.setOnClickListener { RegisterActivity.start(this) }
        tvForgetPwd.setOnClickListener { RetrievePwdActivity.start(this) }
        tvWeiChat.setOnClickListener {}
        tvSina.setOnClickListener {}
    }

    private fun login() {
        val phone = edtPhone.text.toString().trim()
        val pwd = edtPwd.text.toString().trim()
        if (CheckUtil.checkPhoneIllegal(phone)) return
        if (CheckUtil.checkPwdIllegal(pwd)) return
        val dm5Pwd = MD5Util.ToMD5NOKey(pwd)
        RetrofitManager.getLoginService()
                .loginPhone(4, phone, dm5Pwd)
                .compose(NetworkTransformer(this))
                .subscribe(object : SampleCallback<UserBean>() {
                    override fun onSuccess(data: UserBean?) {
                        UserBean.saveUser(data)
                        ToastUtil.s(getString(R.string.login_succeed))
                        HomeActivity.start(activity)
                    }
                })
    }

}
