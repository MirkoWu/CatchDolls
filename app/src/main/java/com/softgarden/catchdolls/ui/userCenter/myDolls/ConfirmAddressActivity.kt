package com.softgarden.catchdolls.ui.userCenter.myDolls

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import com.softgarden.baselibrary.base.BaseRVHolder
import com.softgarden.baselibrary.base.SelectedAdapter
import com.softgarden.baselibrary.widget.ColorDividerDecoration
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity
import com.softgarden.catchdolls.bean.DollBean
import com.softgarden.catchdolls.ui.userCenter.recharge.RechargeActivity
import com.softgarden.catchdolls.utils.RecyclerViewUtil
import com.softgarden.catchdolls.widget.PromptDialog
import kotlinx.android.synthetic.main.activity_comfirm_address.*

class ConfirmAddressActivity : ToolbarActivity() {


    companion object {

        fun start(context: Context) {
            val starter = Intent(context, ConfirmAddressActivity::class.java)
            //starter.putExtra("", "")
            context.startActivity(starter)
        }
    }

    var mAdapter: SelectedAdapter<DollBean>? = null

    override fun getLayoutId() = R.layout.activity_comfirm_address

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(getString(R.string.confirm_address))
    }

    override fun initialize() {
        RecyclerViewUtil.autoFixHeight(mRecyclerView, LinearLayoutManager(this))
        mRecyclerView.addItemDecoration(ColorDividerDecoration(this,
                ContextCompat.getColor(this, R.color.grayLight)))
        mAdapter = object : SelectedAdapter<DollBean>(R.layout.item_select_doll) {
            override fun onBindVH(holder: BaseRVHolder?, data: DollBean?, position: Int) {

            }
        }
        mRecyclerView.adapter = mAdapter

        tvModifyAddress.setOnClickListener { }
        btnEnsureDelivery.setOnClickListener {
            ensureDeliveryDialog()

        }

        loadData()
    }

    private fun loadData() {
        val data = listOf(DollBean(), DollBean(), DollBean(), DollBean())
        mAdapter!!.setNewData(data)
    }

    private fun ensureDeliveryDialog() {
        PromptDialog().setTitle(R.string.ensure_delivery_hint)
                .setPositiveButton(R.string.ok)
                .setNegativeButton(R.string.cancel)
                .setOnButtonClickListener(object : PromptDialog.OnDialogClickListener {
                    override fun onDialogClick(dialog: PromptDialog, isPositiveClick: Boolean) {
                        if (isPositiveClick) postageNotEnoughDialog()
                    }
                }).show(this)
    }


    private fun postageNotEnoughDialog() {
        PromptDialog().setTitle(R.string.postage_not_enough_hint)
                .setPositiveButton(R.string.go_recharge)
                .setNegativeButton(R.string.cancel)
                .setOnButtonClickListener(object : PromptDialog.OnDialogClickListener {
                    override fun onDialogClick(dialog: PromptDialog, isPositiveClick: Boolean) {
                        if (isPositiveClick) RechargeActivity.start(activity)
                    }
                }).show(this)
    }
}
