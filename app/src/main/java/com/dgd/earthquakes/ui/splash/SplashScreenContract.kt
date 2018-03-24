package com.dgd.earthquakes.ui.splash

import com.dgd.earthquakes.common.ScreenContract

/**
 * Created by max
 * on 2/2/18.
 */

class SplashScreenContract {

    interface IPresenter: ScreenContract.IPresenter {
        fun updateQuakes()
    }

    interface IView : ScreenContract.IView {
        fun proceedFromSplash()
    }
}
