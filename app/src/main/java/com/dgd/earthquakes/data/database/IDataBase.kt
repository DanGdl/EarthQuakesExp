package com.dgd.earthquakes.data.database

import com.dgd.earthquakes.data.network.infra.QuakeData
import com.dgd.earthquakes.models.IQuake

/**
 * Created by Max
 * on 23-Jun-17.
 */

interface IDataBase {

    fun saveQuakes(quakes: List<QuakeData>)

    fun getQuakesBulk(date: Long): List<IQuake>
}
