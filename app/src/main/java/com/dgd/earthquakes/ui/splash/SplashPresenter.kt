package com.dgd.earthquakes.ui.splash

import com.dgd.earthquakes.R
import com.dgd.earthquakes.common.Presenter
import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener
import com.dgd.earthquakes.data.network.infra.QuakeData
import java.util.*

/**
 * Created by max
 * on 04/10/17.
 */

class SplashPresenter(view: SplashScreenContract.IView) : Presenter<SplashScreenContract.IView>(view), SplashScreenContract.IPresenter {

    override fun updateQuakes() {
        view.showProgress("", R.string.wait_please)
        repo.checkNewEarthquakes(object : IQuakesCallbackListener {
            override fun onNetworkError(errorMessage: String, errorCode: Int) {
                view.hideProgress()
                view.showMessage(R.string.shit, errorMessage)
                view.proceedFromSplash()
            }

            override fun onNetworkSuccess(quakes: List<QuakeData>) {
                view.hideProgress()
                val repo = repo
                repo.saveLastUpdate(Date().time)
                repo.save(quakes)
                view.proceedFromSplash()
            }
        })
    }
}
