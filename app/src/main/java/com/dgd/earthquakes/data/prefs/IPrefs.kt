package com.dgd.earthquakes.data.prefs

/**
 * Created by Max
 * on 23-Jun-17.
 */

interface IPrefs {

    val lastUpdateDate: Long

    fun saveLastUpdateDate(millis: Long)
}
