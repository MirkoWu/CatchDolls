package com.softgarden.catchdolls.ui.userCenter.myCoins

import android.content.Context
import android.content.Intent
import com.softgarden.baselibrary.base.FragmentBasePagerAdapter
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity
import com.softgarden.catchdolls.ui.userCenter.recharge.RechargeActivity
import kotlinx.android.synthetic.main.activity_my_coins.*

class MyCoinsActivity : ToolbarActivity() {

    companion object {

        fun start(context: Context) {
            val starter = Intent(context, MyCoinsActivity::class.java)
            //starter.putExtra("", "")
            context.startActivity(starter)
        }
    }

    override fun getLayoutId() = R.layout.activity_my_coins

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(R.string.my_coin)
    }

    override fun initialize() {

        btnRecharge.setOnClickListener { RechargeActivity.start(this) }
        val tab = arrayOf(getString(R.string.hisroty_consume_record), getString(R.string.hisroty_recharge_record))
        val mAdapter = FragmentBasePagerAdapter(supportFragmentManager, HistoryRecordFragment::class.java, tab)
        mViewPager.adapter = mAdapter
        mTabLayout.setViewPager(mViewPager)

    }
}
