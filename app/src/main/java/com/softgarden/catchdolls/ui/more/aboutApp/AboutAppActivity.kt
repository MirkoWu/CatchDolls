package com.softgarden.catchdolls.ui.more.aboutApp

import android.content.Context
import android.content.Intent
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity
import kotlinx.android.synthetic.main.activity_about_app.*

class AboutAppActivity : ToolbarActivity() {
    companion object {

        fun start(context: Context) {
            val starter = Intent(context, AboutAppActivity::class.java)
            //starter.putExtra("", "")
            context.startActivity(starter)
        }

    }

    override fun getLayoutId() = R.layout.activity_about_app

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(R.string.about_app)
    }

    override fun initialize() {
        tvGiveScore.setOnClickListener { }
        tvQuestion.setOnClickListener { }

    }

}
