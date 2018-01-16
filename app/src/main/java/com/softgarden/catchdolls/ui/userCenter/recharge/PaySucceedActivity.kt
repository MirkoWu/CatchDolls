package com.softgarden.catchdolls.ui.userCenter.recharge

import android.content.Context
import android.content.Intent
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity
import com.softgarden.catchdolls.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_pay_succeed.*

class PaySucceedActivity : ToolbarActivity() {

    companion object {

        fun start(context: Context) {
            val starter = Intent(context, PaySucceedActivity::class.java)
            //starter.putExtra("", "")
            context.startActivity(starter)
        }
    }

    override fun getLayoutId() = R.layout.activity_pay_succeed

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(getString(R.string.pay_succeed))
    }

    override fun initialize() {
        btnBackHome.setOnClickListener {
            HomeActivity.start(this)
            finish ()
        }
    }
}
