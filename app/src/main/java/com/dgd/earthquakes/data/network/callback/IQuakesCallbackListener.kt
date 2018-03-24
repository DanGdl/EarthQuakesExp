package com.dgd.earthquakes.data.network.callback

import com.dgd.earthquakes.data.network.infra.QuakeData

/**
 * Created by Max
 * on 30-Apr-17.
 */
interface IQuakesCallbackListener {
    fun onNetworkError(errorMessage: String, errorCode: Int)
    fun onNetworkSuccess(quakes: List<QuakeData>)
}
