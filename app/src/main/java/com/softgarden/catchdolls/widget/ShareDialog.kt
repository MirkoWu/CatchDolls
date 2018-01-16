package com.softgarden.catchdolls.widget

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.*
import com.softgarden.baselibrary.base.BaseDialogFragment
import com.softgarden.catchdolls.R
import kotlinx.android.synthetic.main.dialog_share.*

/**
 * @author by DELL
 * @date on 2018/1/5
 * @describe
 */

class ShareDialog : BaseDialogFragment(), View.OnClickListener {

    companion object {
        val WEIXIN = 1
        val WEIXIN_CIRCLE = 2
        val SINA = 3
    }

    private var listener: OnShareListener? = null

    override fun getLayoutId() = R.layout.dialog_share

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window!!.setGravity(Gravity.CENTER)
        val lp = dialog.window!!.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog.window!!.attributes = lp

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initContentView() {
        super.initContentView()
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun initialize() {

        llSina.setOnClickListener(this)
        llWeixin.setOnClickListener(this)
        llWeixinCircle.setOnClickListener(this)
        tvNegative.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (listener != null) {
            val i = v!!.id
            when (i) {
                R.id.llWeixinCircle -> listener!!.onShare(this, WEIXIN_CIRCLE)
                R.id.llWeixin -> listener!!.onShare(this, WEIXIN)
                R.id.llSina -> listener!!.onShare(this, SINA)
                R.id.tvNegative -> {
                }
            }
        }
        dismiss()
    }


    fun setOnShareListener(listener: OnShareListener): ShareDialog {
        this.listener = listener
        return this
    }

    /**
     * 显示Dialog
     */
    fun show(activity: AppCompatActivity) {
        this.show(activity.supportFragmentManager, "")
    }

    open interface OnShareListener {

        /**
         * 当窗口按钮被点击
         *
         */
        fun onShare(dialog: ShareDialog, type: Int)
    }


}
