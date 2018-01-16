package com.softgarden.catchdolls.ui.more.message.catchRecord

import android.content.Context
import android.content.Intent
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.RefreshActivity
import kotlinx.android.synthetic.main.activity_report.*

class ReportActivity : RefreshActivity() {


    companion object {

        fun start(context: Context) {
            val starter = Intent(context, ReportActivity::class.java)
            //starter.putExtra("", "")
            context.startActivity(starter)
        }
    }

    override fun getLayoutId() = R.layout.activity_report

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(getString(R.string.report))
    }

    override fun initialize() {
        initRecyclerView()
        btnSubmit.setOnClickListener { }

    }

    override fun onRefresh() {

    }
}
