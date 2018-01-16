package com.softgarden.catchdolls.ui.more.message.noticeMessage

import android.app.Activity
import android.content.Intent
import com.softgarden.baselibrary.base.BaseRVAdapter
import com.softgarden.baselibrary.base.BaseRVHolder
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.RefreshActivity
import com.softgarden.catchdolls.bean.NoticeMsgBean

class NoticeMessageActivity : RefreshActivity() {
    companion object {

        fun start(activity: Activity) {
            val starter = Intent(activity, NoticeMessageActivity::class.java)
            activity.startActivity(starter)
        }
    }

    private var mAdapter: BaseRVAdapter<NoticeMsgBean>? = null

    override fun getLayoutId() = R.layout.layout_recyclerview_refresh

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(R.string.chat_message)
    }

    override fun initialize() {
        initRefreshLayout()
        initRecyclerView()

        mAdapter = object : BaseRVAdapter<NoticeMsgBean>(R.layout.item_notice_message) {

            override fun onBindVH(holder: BaseRVHolder, data: NoticeMsgBean, position: Int) {

            }
        }
        mRecyclerView.adapter = mAdapter
        val data = listOf(NoticeMsgBean(), NoticeMsgBean(), NoticeMsgBean(), NoticeMsgBean())
        mAdapter!!.setNewData(data)
        mAdapter!!.setOnItemClickListener { adapter, view, position -> MsgDetailActivity.start(this) }
    }

    override fun onRefresh() {
    }
}
