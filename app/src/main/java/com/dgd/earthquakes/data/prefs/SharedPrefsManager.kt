package com.dgd.earthquakes.data.prefs

import android.content.SharedPreferences
import android.preference.PreferenceManager

import com.dgd.earthquakes.BaseApplication

/**
 * Created by Max
 * on 02-May-17.
 */

class SharedPrefsManager private constructor() : IPrefs {
    private val mPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(BaseApplication.instance)

    override val lastUpdateDate: Long
        get() = mPrefs.getLong(LAST_UPDATE, -1)

    override fun saveLastUpdateDate(millis: Long) {
        mPrefs.edit().putLong(LAST_UPDATE, millis).apply()
    }

    companion object {
        private const val LAST_UPDATE = "last_update"
        val instance = SharedPrefsManager()
    }
}
