package com.softgarden.catchdolls.ui.userCenter.recharge

import android.content.Context
import android.content.Intent
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity
import kotlinx.android.synthetic.main.activity_select_pay_type.*

class SelectPayTypeActivity : ToolbarActivity() {
    companion object {

        fun start(context: Context) {
            val starter = Intent(context, SelectPayTypeActivity::class.java)
            //starter.putExtra("", "")
            context.startActivity(starter)
        }
    }

    override fun getLayoutId() = R.layout.activity_select_pay_type

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(getString(R.string.select_pay_type))
    }

    override fun initialize() {
        btnOk.setOnClickListener {
            PaySucceedActivity.start(this)
            finish ()
        }
    }
}
