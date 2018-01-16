package com.softgarden.catchdolls.ui.userCenter.myDolls

import android.content.Context
import android.content.Intent
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity

class LogisticsInfoActivity : ToolbarActivity() {


    companion object {

        fun start(context: Context) {
            val starter = Intent(context, LogisticsInfoActivity::class.java)
            //starter.putExtra("", "")
            context.startActivity(starter)
        }
    }

    override fun getLayoutId() = R.layout.activity_logistics_info

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(getString(R.string.logistics_info))
    }

    override fun initialize() {

    }
}
