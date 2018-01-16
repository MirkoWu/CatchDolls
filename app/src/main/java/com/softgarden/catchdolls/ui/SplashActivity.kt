package com.softgarden.catchdolls.ui

import com.softgarden.baselibrary.base.BaseActivity
import com.softgarden.catchdolls.R
import com.softgarden.catchdolls.ui.home.HomeActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_splash

    override fun initialize() {
        Observable.timer(1500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    HomeActivity.start(this)
                    finish()
                }
    }


}
