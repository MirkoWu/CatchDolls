package com.softgarden.catchdolls.ui.userCenter.userInfo

import android.app.Activity
import android.content.Intent
import com.softgarden.baselibrary.utils.ToastUtil
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity
import com.softgarden.catchdolls.bean.UserBean
import com.softgarden.catchdolls.network.NetworkTransformer
import com.softgarden.catchdolls.network.RetrofitManager
import com.softgarden.catchdolls.network.SampleCallback
import com.softgarden.catchdolls.utils.CheckUtil
import kotlinx.android.synthetic.main.activity_edit_nick_name.*

class EditNickNameActivity : ToolbarActivity() {

    companion object {

        fun start(context: Activity, requestCode: Int) {
            val starter = Intent(context, EditNickNameActivity::class.java)
            //starter.putExtra("", "")
            context.startActivityForResult(starter, requestCode)
        }
    }

    override fun getLayoutId() = R.layout.activity_edit_nick_name

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(R.string.edit_nickname).showTextRight(R.string.save) { editNickName() }
    }

    override fun initialize() {
        edtName.setText(UserBean.getUser().name)
    }


    private fun editNickName() {
        val nickname = edtName.text.toString().trim()
        if (CheckUtil.checkEmpty(nickname, getString(R.string.input_nickname))) return

        RetrofitManager.getUserService()
                .updateUserInfo(nickname, null, null)
                .compose(NetworkTransformer(this))
                .subscribe(object : SampleCallback<Any>() {
                    override fun onSuccess(data: Any?) {
                        ToastUtil.s(getString(R.string.modify_succeed))
                        setResult(Activity.RESULT_OK)
                        finish()
                    }

                })
    }
}
