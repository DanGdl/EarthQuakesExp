package com.dgd.earthquakes

import android.app.Application

import com.dgd.earthquakes.data.IRepo

/**
 * Created by Max
 * on 30-Apr-17.
 */

class BaseApplication : Application() {
    var repository: IRepo? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        repository = Injection.repository
    }

    companion object {
        var instance: BaseApplication? = null
            private set
    }
}
