package com.dgd.earthquakes.common

/**
 * Created by max
 * on 3/24/18.
 */

class ScreenContract {

    interface IView {

        fun showProgress(title: String, msgResId: Int)

        fun hideProgress()

        fun showMessage(messageRes: Int, vararg args: String)
    }

    interface IPresenter
}
