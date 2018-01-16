package com.softgarden.catchdolls.ui.more.message.chatMessage

import android.app.Activity
import android.content.Intent
import com.softgarden.baselibrary.base.BaseRVAdapter
import com.softgarden.baselibrary.base.BaseRVHolder
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.RefreshActivity
import com.softgarden.catchdolls.bean.ChatMsgBean

class ChatMessageActivity : RefreshActivity() {
    companion object {

        fun start(activity: Activity) {
            val starter = Intent(activity, ChatMessageActivity::class.java)
            activity.startActivity(starter)
        }
    }

    private var mAdapter: BaseRVAdapter<ChatMsgBean>? = null

    override fun getLayoutId() = R.layout.layout_recyclerview_refresh

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(R.string.chat_message)
    }

    override fun initialize() {
        initRefreshLayout()
        initRecyclerView()

        mAdapter = object : BaseRVAdapter<ChatMsgBean>(R.layout.item_chat_message) {

            override fun onBindVH(holder: BaseRVHolder, data: ChatMsgBean, position: Int) {

            }
        }
        mRecyclerView.adapter = mAdapter
        val data = listOf<ChatMsgBean>(ChatMsgBean(), ChatMsgBean(), ChatMsgBean(), ChatMsgBean())
        mAdapter!!.setNewData(data)
    }

    override fun onRefresh() {
    }
}
