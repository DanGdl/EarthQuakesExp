package com.dgd.earthquakes.common

/**
 * Created by max
 * on 3/24/18.
 */

class FragmentContract {

    interface IHost

    interface IFragment {
        fun hasProgress(): Boolean

        fun showProgress()

        fun hideProgress()
    }
}
