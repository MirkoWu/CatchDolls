package com.softgarden.catchdolls.ui.userCenter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.softgarden.baselibrary.base.BaseRVAdapter
import com.softgarden.baselibrary.base.BaseRVHolder
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity
import com.softgarden.catchdolls.bean.UserBean
import com.softgarden.catchdolls.network.NetworkTransformer
import com.softgarden.catchdolls.network.RetrofitManager
import com.softgarden.catchdolls.network.SampleCallback
import com.softgarden.catchdolls.ui.userCenter.myCoins.MyCoinsActivity
import com.softgarden.catchdolls.ui.userCenter.myCoupon.MyCouponsActivity
import com.softgarden.catchdolls.ui.userCenter.myDolls.MyDollsListActivity
import com.softgarden.catchdolls.ui.userCenter.myInviteAwards.InviteAwardsActivity
import com.softgarden.catchdolls.ui.userCenter.recharge.RechargeActivity
import com.softgarden.catchdolls.ui.userCenter.userInfo.UserInfoActivity
import com.softgarden.catchdolls.utils.ImageUtil
import kotlinx.android.synthetic.main.activity_user_center.*

class UserCenterActivity : ToolbarActivity() {

    companion object {

        fun start(context: Context) {
            val starter = Intent(context, UserCenterActivity::class.java)
            //starter.putExtra("", "")
            context.startActivity(starter)
        }
    }

    private var mAdapter: BaseRVAdapter<String>? = null

    override fun getLayoutId() = R.layout.activity_user_center

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(R.string.user_center)
    }


    override fun initialize() {
        ivHeader.setOnClickListener { UserInfoActivity.start(this) }
        tvRecharge.setOnClickListener { RechargeActivity.start(this) }
        llMyDolls.setOnClickListener { MyDollsListActivity.start(this) }
        llMyCoin.setOnClickListener { MyCoinsActivity.start(this) }
        tvMyCoupon.setOnClickListener { MyCouponsActivity.start(this) }
        tvMyInviteAwards.setOnClickListener { InviteAwardsActivity.start(this) }

        initRV()
        loadData()
    }


    private fun initRV() {
        mAdapter = object : BaseRVAdapter<String>(R.layout.item_small_pic) {
            override fun onBindVH(holder: BaseRVHolder?, data: String?, position: Int) {
                ImageUtil.load(holder!!.getView(R.id.ivDoll), data)
            }
        }
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mRecyclerView.adapter = mAdapter
        mAdapter!!.setOnItemClickListener { adapter, view, position -> }
        val url = "http://www.qqzhi.com/uploadpic/2015-01-29/162946280.jpg"
        val data = listOf(url, url, url, url, url, url)
        mAdapter!!.setNewData(data)
    }

    private fun loadData() {
        RetrofitManager.getUserService()
                .userInfo
                .compose(NetworkTransformer(this))
                .subscribe(object : SampleCallback<UserBean>() {
                    override fun onSuccess(data: UserBean?) {
                        setData(data)
                    }
                })
    }

    private fun setData(data: UserBean?) {
        ImageUtil.load(ivHeader, data!!.headerImg)
        tvName.text = data.name
        tvId.text = "ID:" + data.number
    }

}
