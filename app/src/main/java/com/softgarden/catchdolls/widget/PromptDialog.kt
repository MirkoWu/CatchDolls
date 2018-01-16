package com.softgarden.catchdolls.widget

import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import com.softgarden.baselibrary.base.BaseDialogFragment
import com.softgarden.baselibrary.utils.ContextUtil
import com.softgarden.catchdolls.R
import kotlinx.android.synthetic.main.dialog_prompt.*

/**
 * @author by DELL
 * @date on 2018/1/5
 * @describe
 */

class PromptDialog : BaseDialogFragment(), View.OnClickListener {

    private var title: String? = null
    private var content: String? = null
    private var positiveLabel: String? = null
    private var negativeLabel: String? = null
    private var positiveTextColor: Int? = 0
    private var negativeTextColor: Int? = 0
    private var listener: OnDialogClickListener? = null

    override fun getLayoutId() = R.layout.dialog_prompt

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
        tvTitle.text = title
        tvContent.text = content
        tvTitle.visibility = if (TextUtils.isEmpty(title)) GONE else VISIBLE
        tvContent.visibility = if (TextUtils.isEmpty(content)) GONE else VISIBLE

        tvPositive.text = positiveLabel
        tvNegative.text = negativeLabel
        tvPositive.visibility = if (TextUtils.isEmpty(positiveLabel)) GONE else VISIBLE
        tvNegative.visibility = if (TextUtils.isEmpty(negativeLabel)) GONE else VISIBLE
        if (positiveTextColor != 0) tvPositive.setTextColor(ContextUtil.getColor(this!!.positiveTextColor!!))
        if (negativeTextColor != 0) tvNegative.setTextColor(ContextUtil.getColor(this!!.negativeTextColor!!))

        tvPositive.setOnClickListener(this)
        tvNegative.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (listener != null) {
            val i = v!!.id
            if (i == R.id.tvPositive) {
                listener!!.onDialogClick(this,true)
            } else if (i == R.id.tvNegative) {
                listener!!.onDialogClick(this,false)
            }
        }
        dismiss()
    }

    fun setTitle(title: String): PromptDialog {
        this.title = title
        return this
    }

    fun setTitle(@StringRes id: Int): PromptDialog {
        return setTitle(ContextUtil.getString(id))
    }

    fun setContent(content: String): PromptDialog {
        this.content = content
        return this
    }

    fun setContent(@StringRes id: Int): PromptDialog {
        return setContent(ContextUtil.getString(id))
    }


    /**
     * 左边的 （默认取消）
     *
     * @param negativeLabel
     * @return
     */
    fun setNegativeButton(@StringRes negativeLabel: Int, @ColorRes textColorInt: Int): PromptDialog {
        return setNegativeButton(ContextUtil.getString(negativeLabel), textColorInt)
    }

    fun setNegativeButton(negativeLabel: String, @ColorRes textColorInt: Int): PromptDialog {
        this.negativeLabel = negativeLabel
        this.negativeTextColor = textColorInt
        return this
    }


    fun setNegativeButton(@StringRes negativeLabel: Int): PromptDialog {
        return setNegativeButton(ContextUtil.getString(negativeLabel))
    }

    fun setNegativeButton(negativeLabel: String): PromptDialog {
        this.negativeLabel = negativeLabel
        return this
    }

    /**
     * 右边的 （默认确定）
     *
     * @param positiveLabel
     * @return
     */
    fun setPositiveButton(@StringRes positiveLabel: Int, @ColorRes textColorInt: Int): PromptDialog {
        return setPositiveButton(ContextUtil.getString(positiveLabel), textColorInt)
    }

    fun setPositiveButton(positiveLabel: String, @ColorRes textColorInt: Int): PromptDialog {
        this.positiveLabel = positiveLabel
        this.positiveTextColor = textColorInt
        return this
    }

    fun setPositiveButton(@StringRes positiveLabel: Int): PromptDialog {
        return setPositiveButton(ContextUtil.getString(positiveLabel))
    }

    fun setPositiveButton(positiveLabel: String): PromptDialog {
        this.positiveLabel = positiveLabel
        return this
    }


//    fun hideNegativeButton(): PromptDialog {
//        this.hideNegativeBtn = true
//        return this
//    }
//
//    fun hidePositiveButton(): PromptDialog {
//        this.hidePositiveBtn = true
//        return this
//    }


    fun setOnButtonClickListener(listener: OnDialogClickListener): PromptDialog {
        this.listener = listener
        return this
    }

    /**
     * 显示Dialog
     */
    fun show(activity: AppCompatActivity) {
        this.show(activity.supportFragmentManager, "")
    }

   open interface OnDialogClickListener {

        /**
         * 当窗口按钮被点击
         *
         */
        fun onDialogClick(dialog: PromptDialog,isPositiveClick: Boolean)
    }


}
