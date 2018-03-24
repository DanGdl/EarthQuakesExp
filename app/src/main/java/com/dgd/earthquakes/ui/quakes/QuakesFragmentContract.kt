package com.dgd.earthquakes.ui.quakes

import com.dgd.earthquakes.common.FragmentContract
import com.dgd.earthquakes.models.Quake

/**
 * Created by max
 * on 2/2/18.
 */

class QuakesFragmentContract {

    interface IFragment : FragmentContract.IFragment {
        fun updateEarthQuakes(quakes: List<Quake>)
    }

    interface IHost : FragmentContract.IHost {
        fun getNextBulk(lastDate: Long)

        fun getEarthQuakes(searchData: SearchDTO?)

        fun refreshEarthQuakes()
    }
}
