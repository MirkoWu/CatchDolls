package com.softgarden.catchdolls.ui.userCenter.myDolls

import android.content.Context
import android.content.Intent
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity
import com.softgarden.catchdolls.ui.userCenter.myCoins.MyCoinsActivity
import com.softgarden.catchdolls.widget.PromptDialog
import kotlinx.android.synthetic.main.activity_doll_detail.*

class DollDetailActivity : ToolbarActivity() {


    companion object {

        fun start(context: Context) {
            val starter = Intent(context, DollDetailActivity::class.java)
            //starter.putExtra("", "")
            context.startActivity(starter)
        }
    }

    override fun getLayoutId() = R.layout.activity_doll_detail

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(getString(R.string.my_doll_detail))
    }

    override fun initialize() {
        tvWatchGameVideo.setOnClickListener { }
        tvExchangeCoin.setOnClickListener { ensureExchangeToCoinDialog() }
        btnRequestDelivery.setOnClickListener { ConfirmAddressActivity.start(this) }
        btnCheckLogistics.setOnClickListener { LogisticsInfoActivity.start(this) }

    }

    private fun ensureExchangeToCoinDialog() {
        PromptDialog().setTitle("您确定要换成80娃娃币吗？")
                .setPositiveButton(R.string.ok)
                .setNegativeButton(R.string.cancel)
                .setOnButtonClickListener(object : PromptDialog.OnDialogClickListener {
                    override fun onDialogClick(dialog: PromptDialog, isPositiveClick: Boolean) {
                        if (isPositiveClick) exchangeToCoin()
                    }
                })
                .show(this)
    }

    private fun exchangeToCoin() {
        PromptDialog().setTitle(R.string.exchange_succeed)
                .setContent("您换取的80娃娃币已发送到您的账户")
                .setNegativeButton(getString(R.string.go_check), R.color.blue)
                .setPositiveButton(R.string.ok)
                .setOnButtonClickListener(object : PromptDialog.OnDialogClickListener {
                    override fun onDialogClick(dialog: PromptDialog, isPositiveClick: Boolean) {
                        if (isPositiveClick) finish() else MyCoinsActivity.start(activity)
                    }
                })
                .show(this)
    }
}
