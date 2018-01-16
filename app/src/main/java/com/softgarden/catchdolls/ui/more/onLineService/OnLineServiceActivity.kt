package com.softgarden.catchdolls.ui.more.onLineService

import android.content.Context
import android.content.Intent
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity

class OnLineServiceActivity : ToolbarActivity() {

    companion object {

        fun start(context: Context) {
            val starter = Intent(context, OnLineServiceActivity::class.java)
            //starter.putExtra("", "")
            context.startActivity(starter)
        }
    }

    override fun getLayoutId() = R.layout.activity_on_line_service

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(R.string.online_service)
    }

    override fun initialize() {

    }
}
