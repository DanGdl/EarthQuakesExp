package com.dgd.earthquakes.common

/**
 * Created by max
 * on 3/24/18.
 */

class ScreenContract {

    interface IView {

        fun showProgress(title: String, msgResId: Int)

        fun hideProgress()

        fun showMessage(msgRes: Int, vararg message: String)
    }

    interface IPresenter
}
