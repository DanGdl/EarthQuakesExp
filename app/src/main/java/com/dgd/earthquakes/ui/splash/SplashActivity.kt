package com.dgd.earthquakes.ui.splash

import android.os.Bundle

import com.dgd.earthquakes.Injection
import com.dgd.earthquakes.common.FragmentHostActivity
import com.dgd.earthquakes.common.HostedFragment
import com.dgd.earthquakes.ui.quakes.MainHostActivity

/**
 * Created by Max
 * on 01-May-17.
 */

class SplashActivity : FragmentHostActivity<SplashScreenContract.IPresenter>(), SplashScreenContract.IView {

    override fun getFirstFragment(savedInstanceState: Bundle?): HostedFragment<*> {
        presenter.updateQuakes()
        return SplashFragment.newInstance()
    }

    override fun setupPresenter(): SplashScreenContract.IPresenter {
        return Injection.getSplashPresenter(this)
    }

    override fun proceedFromSplash() {
        startActivity(MainHostActivity.getIntent(this@SplashActivity))
    }

    override fun showProgress(title: String, msgResId: Int) {
        showProgress(title, getString(msgResId))
    }
}
