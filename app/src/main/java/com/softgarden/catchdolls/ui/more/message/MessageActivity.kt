package com.softgarden.catchdolls.ui.more.message

import android.app.Activity
import android.content.Intent
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity
import com.softgarden.catchdolls.ui.more.message.catchRecord.CatchRecordActivity
import com.softgarden.catchdolls.ui.more.message.chatMessage.ChatMessageActivity
import com.softgarden.catchdolls.ui.more.message.noticeMessage.NoticeMessageActivity
import kotlinx.android.synthetic.main.activity_message.*

class MessageActivity : ToolbarActivity() {
    companion object {

        fun start(activity: Activity) {
            val starter = Intent(activity, MessageActivity::class.java)
            activity.startActivity(starter)
        }
    }

    override fun getLayoutId() = R.layout.activity_message

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(R.string.message)
    }

    override fun initialize() {
        tvChatMessage.setOnClickListener { ChatMessageActivity.start(this) }
        tvCatchRecord.setOnClickListener { CatchRecordActivity.start(this) }
        tvNoticeMessage.setOnClickListener { NoticeMessageActivity.start(this) }
    }

}
