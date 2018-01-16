package com.softgarden.catchdolls.ui.userCenter.myCoupon

import android.content.Context
import android.content.Intent
import com.softgarden.baselibrary.base.BaseRVAdapter
import com.softgarden.baselibrary.base.BaseRVHolder
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.RefreshActivity
import com.softgarden.catchdolls.bean.CouponBean
import com.softgarden.catchdolls.ui.userCenter.myCoins.MyCoinsActivity
import com.softgarden.catchdolls.widget.PromptDialog

class MyCouponsActivity : RefreshActivity() {

    companion object {

        fun start(context: Context) {
            val starter = Intent(context, MyCouponsActivity::class.java)
            //starter.putExtra("", "")
            context.startActivity(starter)
        }
    }

    private var mAdapter: BaseRVAdapter<CouponBean>? = null

    override fun getLayoutId() = R.layout.layout_recyclerview_refresh

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(R.string.my_coupon)
    }

    override fun initialize() {
        initRefreshLayout()
        initRecyclerView()
        mAdapter = object : BaseRVAdapter<CouponBean>(R.layout.item_coupon) {
            override fun onBindVH(holder: BaseRVHolder?, data: CouponBean?, position: Int) {

            }
        }
        mRecyclerView.adapter = mAdapter
        val data = listOf(CouponBean(), CouponBean(), CouponBean(), CouponBean(), CouponBean())
        mAdapter!!.setNewData(data)
        mAdapter!!.setOnItemClickListener { adapter, view, position -> showEnsureDialog() }
    }

    private fun showEnsureDialog() {
        PromptDialog().setTitle(R.string.ensure_use_this_coupon)
                .setContent("使用该优惠券可获得40娃娃币")
                .setPositiveButton(R.string.ok)
                .setNegativeButton(R.string.cancel)
                .setOnButtonClickListener(object : PromptDialog.OnDialogClickListener {
                    override fun onDialogClick(dialog: PromptDialog, isPositiveClick: Boolean) {
                        if (isPositiveClick) exchangeSucceedDialog()
                    }
                })
                .show(this)
    }

    private fun exchangeSucceedDialog() {
        PromptDialog().setTitle(R.string.exchange_coupon_succeed)
                .setContent("您的余额已进账40娃娃币")
                .setPositiveButton(R.string.ok)
                .setNegativeButton(R.string.go_check,R.color.blue)
                .setOnButtonClickListener(object : PromptDialog.OnDialogClickListener {
                    override fun onDialogClick(dialog: PromptDialog, isPositiveClick: Boolean) {
                        if (!isPositiveClick) MyCoinsActivity.start(activity)
                    }
                })
                .show(this)
    }


    override fun onRefresh() {
    }

}
