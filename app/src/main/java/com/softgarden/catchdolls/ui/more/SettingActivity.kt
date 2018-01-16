package com.softgarden.catchdolls.ui.more

import android.content.Context
import android.content.Intent
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity

class SettingActivity : ToolbarActivity() {
    companion object {

        fun start(context: Context) {
            val starter = Intent(context, SettingActivity::class.java)
            //starter.putExtra("", "")
            context.startActivity(starter)
        }
    }

    override fun getLayoutId() = R.layout.activity_setting

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(R.string.setting)
    }

    override fun initialize() {

    }


}
