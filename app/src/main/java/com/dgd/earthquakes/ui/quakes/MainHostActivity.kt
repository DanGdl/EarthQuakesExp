package com.dgd.earthquakes.ui.quakes

import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.dgd.earthquakes.Injection
import com.dgd.earthquakes.common.FragmentHostActivity
import com.dgd.earthquakes.common.HostedFragment
import com.dgd.earthquakes.models.Quake

class MainHostActivity : FragmentHostActivity<QuakesScreenContract.IPresenter>(), QuakesFragmentContract.IHost, QuakesScreenContract.IView {

    override fun getEarthQuakes(searchData: SearchDTO?) {
        presenter.getEarthQuakes(searchData)
    }

    private var earthQuakesFragment: QuakesFragmentContract.IFragment? = null

    override fun setupPresenter(): QuakesScreenContract.IPresenter {
        return Injection.getQuakesPresenter(this)
    }

    override fun getFirstFragment(savedInstanceState: Bundle?): HostedFragment<*> {
        val f = EarthQuakesFragment.newInstance()
        earthQuakesFragment = f
        return f
    }

    override fun refreshEarthQuakes() {
        presenter.checkNewEarthQuakes()
    }

    override fun getNextBulk(lastDate: Long) {
        presenter.getNextBulk(lastDate)
    }

    override fun updateEarthQuakes(quakes: List<Quake>) {
        earthQuakesFragment?.updateEarthQuakes(quakes)
    }

    override fun showProgress(title: String, msgResId: Int) {
        showProgress(title, getString(msgResId))
    }

    companion object {

        fun getIntent(context: Context): Intent {
            return Intent(context, MainHostActivity::class.java)
        }
    }
}
