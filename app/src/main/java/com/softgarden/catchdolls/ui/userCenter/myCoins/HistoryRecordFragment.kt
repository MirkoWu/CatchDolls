package com.softgarden.catchdolls.ui.userCenter.myCoins

import com.softgarden.baselibrary.base.BaseRVAdapter
import com.softgarden.baselibrary.base.BaseRVHolder
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.RefreshFragment
import com.softgarden.catchdolls.bean.HistoryCoinRecordBean
import com.softgarden.catchdolls.utils.ImageUtil

/**
 *
 *
 * @author by DELL
 * @date on 2018/1/11
 * @describe
 */
class HistoryRecordFragment : RefreshFragment() {


    var mAdapter: BaseRVAdapter<HistoryCoinRecordBean>? = null

    override fun getLayoutId() = R.layout.layout_recyclerview

    override fun initEventAndData() {
        initRecyclerView()
        addItemDecoration()
        mAdapter = object : BaseRVAdapter<HistoryCoinRecordBean>(R.layout.item_history_coin_record) {
            override fun onBindVH(holder: BaseRVHolder?, data: HistoryCoinRecordBean?, position: Int) {
                if (getPosition() == 0) {
                    ImageUtil.load(holder!!.getView(R.id.ivDoll), "")
                    holder!!.setVisible(R.id.ivDoll, true)
                } else {
                    holder!!.setVisible(R.id.ivDoll, false)
                }
            }
        }
        mRecyclerView.adapter = mAdapter
    }

    override fun lazyLoad() {
        val data = listOf(HistoryCoinRecordBean(), HistoryCoinRecordBean(), HistoryCoinRecordBean(), HistoryCoinRecordBean())
        mAdapter!!.setNewData(data)
    }

    override fun onRefresh() {
    }
}