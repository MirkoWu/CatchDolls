package com.softgarden.catchdolls.ui.more

import android.content.Context
import android.content.Intent
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity
import com.softgarden.catchdolls.network.NetworkTransformer
import com.softgarden.catchdolls.network.RetrofitManager
import com.softgarden.catchdolls.network.SampleCallback
import com.softgarden.catchdolls.utils.CheckUtil
import com.softgarden.catchdolls.widget.PromptDialog
import kotlinx.android.synthetic.main.activity_feedback.*

class FeedbackActivity : ToolbarActivity() {
    companion object {
        fun start(context: Context) {
            val starter = Intent(context, FeedbackActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getLayoutId() = R.layout.activity_feedback

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(R.string.feedback)
    }

    override fun initialize() {
        btnSubmit.setOnClickListener { submitFeedback() }
    }

    private fun submitFeedback() {
        val content = edtFeedback.text.toString()
        if (CheckUtil.checkEmpty(content, getString(R.string.feedback_hint))) return
        val email = edtEmail.text.toString()
        if (CheckUtil.checkEmailIllegal(email)) return

        RetrofitManager.getMoreService()
                .submitFeedback(0, content, email)
                .compose(NetworkTransformer(this))
                .subscribe(object : SampleCallback<Any>() {
                    override fun onSuccess(data: Any?) {
                        PromptDialog().setTitle(getString(R.string.reciviced_feedback))
                                .setPositiveButton(R.string.ok)
                                .setOnButtonClickListener(object : PromptDialog.OnDialogClickListener {
                                    override fun onDialogClick(dialog: PromptDialog,isPositiveClick: Boolean) {
                                        finish()
                                    }
                                })
                                .show(activity)
                    }
                })
    }
}
