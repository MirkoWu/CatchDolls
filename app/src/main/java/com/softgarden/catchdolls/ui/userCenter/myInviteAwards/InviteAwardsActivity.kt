package com.softgarden.catchdolls.ui.userCenter.myInviteAwards

import android.content.Context
import android.content.Intent
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity
import com.softgarden.catchdolls.ui.userCenter.myCoins.MyCoinsActivity
import com.softgarden.catchdolls.widget.PromptDialog
import com.softgarden.catchdolls.widget.ShareDialog
import com.softgarden.catchdolls.widget.ShareDialog.Companion.SINA
import com.softgarden.catchdolls.widget.ShareDialog.Companion.WEIXIN
import com.softgarden.catchdolls.widget.ShareDialog.Companion.WEIXIN_CIRCLE
import kotlinx.android.synthetic.main.activity_invite_awards.*

class InviteAwardsActivity : ToolbarActivity() {
    companion object {

        fun start(context: Context) {
            val starter = Intent(context, InviteAwardsActivity::class.java)
            //starter.putExtra("", "")
            context.startActivity(starter)
        }
    }

    override fun getLayoutId() = R.layout.activity_invite_awards

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(R.string.invite_awards)
    }

    override fun initialize() {
        btnShareInviteCode.setOnClickListener { showShareDialog() }
        btnExchangeInviteCode.setOnClickListener { showSucceedDialog() }

    }

    private fun showSucceedDialog() {
        PromptDialog().setTitle(getString(R.string.invite_succeed_hint))
                .setContent("您获得的10娃娃币已发送到您的账号")
                .setPositiveButton(R.string.ok)
                .setNegativeButton(R.string.go_check, R.color.blueText)
                .setOnButtonClickListener(object : PromptDialog.OnDialogClickListener {
                    override fun onDialogClick(dialog: PromptDialog, isPositiveClick: Boolean) {
                        if (!isPositiveClick) MyCoinsActivity.start(activity)
                    }
                }).show(this)
    }

    private fun showShareDialog() {
        ShareDialog().setOnShareListener(object : ShareDialog.OnShareListener {
            override fun onShare(dialog: ShareDialog, type: Int) {
                when (type) {
                    WEIXIN_CIRCLE -> {
                    }
                    WEIXIN -> {
                    }
                    SINA -> {
                    }
                }
            }
        }).show(this)
    }
}
