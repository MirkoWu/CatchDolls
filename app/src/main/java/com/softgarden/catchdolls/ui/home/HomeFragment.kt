package com.softgarden.catchdolls.ui.home

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter

import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.RefreshFragment
import com.softgarden.catchdolls.bean.GoodsBean

/**
 * @author by DELL
 * @date on 2017/12/21
 * @describe
 */

class HomeFragment : RefreshFragment(), BaseQuickAdapter.OnItemChildClickListener {


    companion object {
        fun newInstance(): HomeFragment {

            val args = Bundle()

            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var mAdapter = HomeAdapter()

    override fun getLayoutId() = R.layout.layout_recyclerview_refresh

    override fun initEventAndData() {
        initRefreshLayout()
        initRecyclerView()
        mRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        mRecyclerView.adapter = mAdapter
        mAdapter.onItemChildClickListener = this
        setEnableRefresh(false)
    }

    override fun lazyLoad() {
        val data = listOf<GoodsBean>(GoodsBean(), GoodsBean(), GoodsBean(), GoodsBean(), GoodsBean(), GoodsBean(), GoodsBean(), GoodsBean(), GoodsBean(), GoodsBean(), GoodsBean(), GoodsBean())
        mAdapter.setNewData(data)


    }


    override fun onRefresh() {

    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

    }
}
