package com.softgarden.catchdolls.ui.more.message.catchRecord

import android.app.Activity
import android.content.Intent
import com.softgarden.baselibrary.base.BaseRVAdapter
import com.softgarden.baselibrary.base.BaseRVHolder
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.RefreshActivity
import com.softgarden.catchdolls.bean.CatchRecordBean

class CatchRecordActivity : RefreshActivity() {
    companion object {

        fun start(activity: Activity) {
            val starter = Intent(activity, CatchRecordActivity::class.java)
            activity.startActivity(starter)
        }
    }
    private var mAdapter: BaseRVAdapter<CatchRecordBean>? = null

    override fun getLayoutId() = R.layout.layout_recyclerview_refresh

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(R.string.catch_record)
    }

    override fun initialize() {
        initRefreshLayout()
        initRecyclerView()

        mAdapter = object : BaseRVAdapter<CatchRecordBean>(R.layout.item_catch_record) {

            override fun onBindVH(holder: BaseRVHolder, data: CatchRecordBean, position: Int) {

            }
        }
        mRecyclerView.adapter = mAdapter
        val data = listOf<CatchRecordBean>(CatchRecordBean(), CatchRecordBean(), CatchRecordBean(), CatchRecordBean())
        mAdapter!!.setNewData(data)
    }

    override fun onRefresh() {
    }
}
