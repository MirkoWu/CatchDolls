package com.softgarden.catchdolls.ui.more.message.catchRecord

import android.content.Context
import android.content.Intent
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity
import kotlinx.android.synthetic.main.activity_record_detail.*

class RecordDetailActivity : ToolbarActivity() {

    companion object {

        fun start(context: Context) {
            val starter = Intent(context, RecordDetailActivity::class.java)
            //starter.putExtra("", "")
            context.startActivity(starter)
        }
    }

    override fun getLayoutId() = R.layout.activity_record_detail

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(getString(R.string.record_detail))
    }

    override fun initialize() {

        tvWatchGameVideo.setOnClickListener { }
        tvReport.setOnClickListener { ReportActivity.start(this) }
    }
}
