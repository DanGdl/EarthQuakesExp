package com.dgd.earthquakes.data.network

import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener
import java.util.*

/**
 * Created by Max
 * on 23-Jun-17.
 */

interface INetworkManager {
    fun getEarthquakes(start: Date, end: Date, listener: IQuakesCallbackListener)

    fun checkNewEarthquakes(listener: IQuakesCallbackListener)
}
