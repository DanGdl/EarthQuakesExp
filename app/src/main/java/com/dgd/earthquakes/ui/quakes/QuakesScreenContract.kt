package com.dgd.earthquakes.ui.quakes

import com.dgd.earthquakes.common.ScreenContract
import com.dgd.earthquakes.models.Quake

/**
 * Created by max
 * on 2/2/18.
 */

class QuakesScreenContract {
    interface IPresenter : ScreenContract.IPresenter {
        fun getEarthQuakes(searchParams: SearchDTO?)

        fun checkNewEarthQuakes()

        fun getNextBulk(lastDate: Long)
    }

    interface IView : ScreenContract.IView {
        fun updateEarthQuakes(quakes: List<Quake>)
    }
}
