package com.softgarden.catchdolls.ui.userCenter.recharge

import android.content.Context
import android.content.Intent
import com.softgarden.baselibrary.base.BaseRVAdapter
import com.softgarden.baselibrary.base.BaseRVHolder
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.RefreshActivity
import com.softgarden.catchdolls.bean.RechargeBean

class RechargeActivity : RefreshActivity() {

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, RechargeActivity::class.java)
            context.startActivity(starter)
        }
    }

    private var mAdapter: BaseRVAdapter<RechargeBean>? = null

    override fun getLayoutId() = R.layout.activity_recharge

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(getString(R.string.recharge))
    }

    override fun initialize() {
        initRefreshLayout()
        initRecyclerView()
        mAdapter = object : BaseRVAdapter<RechargeBean>(R.layout.item_recharge) {
            override fun onBindVH(holder: BaseRVHolder?, data: RechargeBean?, position: Int) {

            }
        }
        mRecyclerView.adapter = mAdapter
        mAdapter!!.setOnItemClickListener { adapter, view, position -> run { SelectPayTypeActivity.start(this) } }

        mAdapter!!.setNewData(listOf(RechargeBean(), RechargeBean(), RechargeBean()))
    }

    override fun onRefresh() {
    }
}
