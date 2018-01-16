package com.softgarden.catchdolls.ui.more.message.noticeMessage

import android.app.Activity
import android.content.Intent
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity

class MsgDetailActivity : ToolbarActivity() {
    companion object {

        fun start(activity: Activity) {
            val starter = Intent(activity, MsgDetailActivity::class.java)
            activity.startActivity(starter)
        }
    }

    override fun getLayoutId()=R.layout.activity_msg_detail
    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(R.string.message)
    }
    override fun initialize() {
    }


}
