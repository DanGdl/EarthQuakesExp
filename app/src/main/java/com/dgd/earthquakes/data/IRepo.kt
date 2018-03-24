package com.dgd.earthquakes.data

import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener
import com.dgd.earthquakes.data.network.infra.QuakeData
import com.dgd.earthquakes.models.Quake
import com.dgd.earthquakes.ui.quakes.SearchDTO
import java.util.*

/**
 * Created by Max
 * on 23-Jun-17.
 */

interface IRepo {

    fun getEarthquakes(start: Date, end: Date, listener: IQuakesCallbackListener)

    fun checkNewEarthquakes(listener: IQuakesCallbackListener)

    fun getAllQuakes(searchParams: SearchDTO?): List<Quake>

    fun saveLastUpdate(time: Long)

    fun save(quakes: List<QuakeData>)
}
