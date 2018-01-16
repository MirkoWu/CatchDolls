package com.softgarden.catchdolls.ui.home

import android.app.Activity
import android.content.Intent
import android.view.KeyEvent
import com.softgarden.baselibrary.base.FragmentBasePagerAdapter
import com.softgarden.baselibrary.utils.ToastUtil
import com.softgarden.baselibrary.widget.CommonToolbar
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.base.ToolbarActivity
import com.softgarden.catchdolls.ui.more.MoreActivity
import com.softgarden.catchdolls.ui.userCenter.UserCenterActivity
import com.softgarden.catchdolls.utils.CheckUtil
import kotlinx.android.synthetic.main.activity_main.*


class HomeActivity : ToolbarActivity(){
    companion object {

        fun start(activity: Activity) {
            val starter = Intent(activity, HomeActivity::class.java)
            // starter.putExtra("", "")
            starter.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            activity.startActivity(starter)
        }
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun setToolbar(builder: CommonToolbar.Builder): CommonToolbar.Builder? {
        return builder.setTitle(R.string.app_name)
                .showImageLeft(R.mipmap.user, {
                    if (CheckUtil.checkIsLogin(this))
                        UserCenterActivity.start(this)
                })
                .showImageRight(R.mipmap.more, {
                    if (CheckUtil.checkIsLogin(this))
                        MoreActivity.start(this)
                })
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        initialize()
    }

    override fun initialize() {
        val tab = arrayOf("全部", "热门", "热门", "热门", "热门", "热门", "热门", "热门", "热门", "热门", "热门")
        val pagerAdapter = FragmentBasePagerAdapter(supportFragmentManager, HomeFragment::class.java, tab)
        mViewPager.adapter = pagerAdapter
        mTabLayout.setViewPager(mViewPager, tab)
    }

    /**
     * 再按一次退出程序
     */
    private var currentBackPressedTime: Long = 0
    private val BACK_PRESSED_INTERVAL = 5000

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - currentBackPressedTime > BACK_PRESSED_INTERVAL) {
                currentBackPressedTime = System.currentTimeMillis()
                ToastUtil.s(R.string.press_again_to_exit)
                return true
            } else {
                finish() // 退出
            }
            return false

        } else if (event.keyCode == KeyEvent.KEYCODE_MENU) {
            return true
        }
        return super.dispatchKeyEvent(event)
    }
}
