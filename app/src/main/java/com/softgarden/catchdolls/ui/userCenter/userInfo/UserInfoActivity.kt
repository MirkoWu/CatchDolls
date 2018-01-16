package com.softgarden.catchdolls.ui.userCenter.userInfo

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.softgarden.baselibrary.widget.BottomListDialog
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity
import com.softgarden.catchdolls.bean.UserBean
import com.softgarden.catchdolls.network.NetworkTransformer
import com.softgarden.catchdolls.network.RetrofitManager
import com.softgarden.catchdolls.network.SampleCallback
import com.softgarden.catchdolls.ui.userCenter.userInfo.address.AddressListActivity
import com.softgarden.catchdolls.utils.ImageUtil
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity : ToolbarActivity() {

    companion object {
        val REQUEST_UPDATE_NAME = 1
        val REQUEST_UPDATE_ADDRESS = 2

        fun start(context: Context) {
            val starter = Intent(context, UserInfoActivity::class.java)
            //starter.putExtra("", "")
            context.startActivity(starter)
        }
    }

    override fun getLayoutId() = R.layout.activity_user_info

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(getString(R.string.user_info))
    }

    override fun initialize() {
        llHeader.setOnClickListener { updateHeader() }
        llNickName.setOnClickListener { EditNickNameActivity.start(this, REQUEST_UPDATE_NAME) }
        llSex.setOnClickListener { }
        llAddress.setOnClickListener { AddressListActivity.start(this, REQUEST_UPDATE_ADDRESS) }

        loadData()
    }

    private fun loadData() {
        RetrofitManager.getUserService()
                .userInfoDetail
                .compose(NetworkTransformer(this))
                .subscribe(object : SampleCallback<UserBean>() {
                    override fun onSuccess(data: UserBean?) {
                        setData(data)
                    }
                })
    }

    private fun setData(data: UserBean?) {
        UserBean.saveUser(data)
        ImageUtil.load(ivHeader, data!!.headerImg)
        tvNickName.text = data.name
        tvSex.text = if (data.sex == 0) "男" else "女"
        tvAddress.text = data.fullAddress
    }

    private fun updateHeader() {
        BottomListDialog().setData(listOf("拍照上传", "相册上传"))
                .setTitle(getString(R.string.update_header))
                .setOnItemClickListener { data, position ->
                    if (position == 1) {

                    } else if (position == 2) {
                    }
                    true
                }
                .show(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            loadData()
        }
    }
}
