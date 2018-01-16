package com.softgarden.catchdolls.ui.userCenter.userInfo.address

import android.app.Activity
import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.softgarden.baselibrary.base.BaseRVAdapter
import com.softgarden.baselibrary.base.BaseRVHolder
import com.softgarden.baselibrary.utils.ToastUtil
import com.softgarden.baselibrary.widget.BottomListDialog
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.app.Constants
import com.softgarden.catchdolls.base.RefreshActivity
import com.softgarden.catchdolls.bean.AddressBean
import com.softgarden.catchdolls.network.NetworkTransformer
import com.softgarden.catchdolls.network.RetrofitManager
import com.softgarden.catchdolls.network.SampleCallback
import kotlinx.android.synthetic.main.activity_address_list.*

class AddressListActivity : RefreshActivity(), BaseQuickAdapter.OnItemChildClickListener {

    companion object {
        val REQUESTCODE_EDIT = 1
        fun start(context: Activity, requestCode: Int) {
            val starter = Intent(context, AddressListActivity::class.java)
            context.startActivityForResult(starter, requestCode)
        }
    }

    private var mAdapter: BaseRVAdapter<AddressBean>? = null

    override fun getLayoutId() = R.layout.activity_address_list

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(R.string.sendAddress)
    }


    override fun initialize() {
        initRefreshLayout()
        initRecyclerView()
        mAdapter = object : BaseRVAdapter<AddressBean>(R.layout.item_address) {
            override fun onBindVH(holder: BaseRVHolder?, data: AddressBean?, position: Int) {
                holder!!.setText(R.id.tvName, data!!.userName)
                        .setText(R.id.tvPhone, data!!.phone)
                        .setText(R.id.tvAddress, data!!.fullAddress)
                        .addOnClickListener(R.id.tvAsDefault)
                        .addOnClickListener(R.id.tvEdit)
                        .addOnClickListener(R.id.tvDel)
            }
        }
        mRecyclerView.adapter = mAdapter
        mAdapter!!.onItemChildClickListener = this

        btnCreateAddress.setOnClickListener { EditAddressActivity.start(this, REQUESTCODE_EDIT) }

        loadData()
    }


    override fun onRefresh() {
        mPage = 1
        loadData()
    }

    override fun onLoadMoreRequested() {
        mPage++
        loadData()
    }

    private fun loadData() {
        RetrofitManager.getUserService()
                .getAddressList(mPage, Constants.PAGE_COUNT)
                .compose(NetworkTransformer(this))
                .subscribe(object : SampleCallback<List<AddressBean>>() {
                    override fun onSuccess(data: List<AddressBean>?) {
                        setLoadMore(mRecyclerView, mAdapter, data)
                    }

                    override fun onFinish() {
                        finishRefresh()
                    }
                })
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        val bean = mAdapter!!.getItem(position)
        val id = view!!.id
        when (id) {
            R.id.tvAsDefault -> {
                asDefaultAddress(bean)
            }
            R.id.tvEdit -> {
                EditAddressActivity.start(this, REQUESTCODE_EDIT, bean!!)
            }
            R.id.tvDel -> {
                BottomListDialog().setTitle(R.string.ensure_del_address_hint)
                        .setData(listOf(getString(R.string.del)))
                        .setOnItemClickListener { data, position ->
                            delAddress(bean, position)
                            true
                        }
                        .show(activity)
            }
        }
    }

    private fun asDefaultAddress(bean: AddressBean?) {

    }

    private fun delAddress(bean: AddressBean?, position: Int) {
        RetrofitManager.getUserService()
                .delAddress(bean!!.id)
                .compose(NetworkTransformer(this))
                .subscribe(object : SampleCallback<Any>() {
                    override fun onSuccess(data: Any?) {
                        ToastUtil.s(getString(R.string.del_succeed))
                        mAdapter!!.remove(position)
                        mAdapter!!.notifyItemRemoved(position)
                    }
                })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            onRefresh()
        }
    }


}
