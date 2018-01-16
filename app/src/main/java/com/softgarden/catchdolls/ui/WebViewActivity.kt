package com.softgarden.catchdolls.ui

import android.content.Context
import android.content.Intent
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : ToolbarActivity() {
    open interface UrlType {
        companion object {
            val REGISTER_PROTOCAL = 1//注册协议
            val PRIVACY_PROTOCAL = 2//隐私协议
            val COMMON_QUESTION = 3//常见问题
            val MESSAGE_DETAIL = 4//消息详情

        }
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, WebViewActivity::class.java)
//            starter.putExtra("title", title)
//            starter.putExtra("url", url)
            context.startActivity(starter)
        }
    }

    private var mWebView: WebView? = null
    override fun getLayoutId() = R.layout.activity_web_view

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder
    }

    override fun initialize() {
        mWebView = WebView(this)
        mWebView!!.layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        flRootView.addView(mWebView)

        initWebSetting()
       // loadData()
    }


    private fun initWebSetting() {
        val settings = mWebView!!.settings
        //
        mWebView!!.webViewClient = WebViewClient()
        mWebView!!.webChromeClient = WebChromeClient()

    }

    private fun loadData(type: Int) {
//        when (type) {
//            WebViewActivity.UrlType.COMMON_QUESTION -> {
//            }
//            WebViewActivity.UrlType.MESSAGE_DETAIL -> {
//            }
//        }when (type) {
//            WebViewActivity.UrlType.COMMON_QUESTION -> {
//            }
//            WebViewActivity.UrlType.MESSAGE_DETAIL -> {
//            }
//        }
    }

    private fun loadUrl(url: String) {
        mWebView!!.loadUrl(url)
    }

    private fun loadText(text: String) {
        mWebView!!.loadDataWithBaseURL(null, text, "text/html", "utf-8", null)
    }

    override fun onBackPressed() {
        if (mWebView!!.canGoBack()) mWebView!!.goBack()
        else super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        mWebView!!.webViewClient = null
        mWebView!!.webChromeClient = null
        mWebView!!.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
        mWebView!!.clearHistory()

        (mWebView!!.parent as ViewGroup).removeView(mWebView)
        mWebView!!.destroy()
        mWebView = null
    }
}
