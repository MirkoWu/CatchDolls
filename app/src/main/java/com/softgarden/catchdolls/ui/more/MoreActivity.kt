package com.softgarden.catchdolls.ui.more

import android.content.Context
import android.content.Intent
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity
import com.softgarden.catchdolls.bean.UserBean
import com.softgarden.catchdolls.ui.home.HomeActivity
import com.softgarden.catchdolls.ui.more.aboutApp.AboutAppActivity
import com.softgarden.catchdolls.ui.more.message.MessageActivity
import com.softgarden.catchdolls.ui.more.onLineService.OnLineServiceActivity
import com.softgarden.catchdolls.ui.userCenter.myInviteAwards.InviteAwardsActivity
import kotlinx.android.synthetic.main.activity_more.*

class MoreActivity : ToolbarActivity() {
    companion object {

        fun start(context: Context) {
            val starter = Intent(context, MoreActivity::class.java)
            //starter.putExtra("", "")
            context.startActivity(starter)
        }
    }

    override fun getLayoutId() = R.layout.activity_more

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(R.string.more)
    }


    override fun initialize() {
        tvMessage.setOnClickListener { MessageActivity.start(this) }
        tvShareInvitationCode.setOnClickListener { InviteAwardsActivity.start(this) }
        tvSetting.setOnClickListener { SettingActivity.start(this) }
        tvOnLineService.setOnClickListener { OnLineServiceActivity.start(this) }
        tvFeedback.setOnClickListener { FeedbackActivity.start(this) }
        tvAboutApp.setOnClickListener { AboutAppActivity.start(this) }

        btnUnLogin.setOnClickListener {
            UserBean.clear()
            HomeActivity.start(this)
        }
    }

}
