package com.softgarden.catchdolls.ui.userCenter.myDolls

import android.content.Context
import android.content.Intent
import com.softgarden.baselibrary.base.BaseRVAdapter
import com.softgarden.baselibrary.base.BaseRVHolder
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.RefreshActivity
import com.softgarden.catchdolls.bean.DollBean

class MyDollsListActivity : RefreshActivity() {

    companion object {

        fun start(context: Context) {
            val starter = Intent(context, MyDollsListActivity::class.java)
            //starter.putExtra("", "")
            context.startActivity(starter)
        }
    }
    private var mAdapter: BaseRVAdapter<DollBean>? = null
    override fun getLayoutId() = R.layout.layout_recyclerview_refresh

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(getString(R.string.my_doll))
    }

    override fun initialize() {
        initRefreshLayout()
        initRecyclerView()
        mAdapter = object : BaseRVAdapter<DollBean>(R.layout.item_my_dolls) {
            override fun onBindVH(holder: BaseRVHolder?, data: DollBean?, position: Int) {

            }
        }
        mRecyclerView.adapter = mAdapter
        mAdapter!!.setOnItemClickListener { adapter, view, position -> run { DollDetailActivity.start(this) } }

        mAdapter!!.setNewData(listOf(DollBean(), DollBean(), DollBean()))
    }

    override fun onRefresh() {
    }

}
