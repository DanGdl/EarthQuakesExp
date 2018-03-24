package com.dgd.earthquakes

import com.dgd.earthquakes.data.IRepo
import com.dgd.earthquakes.data.Repo
import com.dgd.earthquakes.data.database.SQLiteManager
import com.dgd.earthquakes.data.network.NetworkManager
import com.dgd.earthquakes.data.prefs.SharedPrefsManager
import com.dgd.earthquakes.ui.quakes.QuakesListPresenter
import com.dgd.earthquakes.ui.quakes.QuakesScreenContract
import com.dgd.earthquakes.ui.splash.SplashPresenter
import com.dgd.earthquakes.ui.splash.SplashScreenContract

/**
 * Created by Owner
 * on 19/03/2018.
 */

object Injection {

    internal val repository: IRepo
        get() = Repo(NetworkManager(), SQLiteManager.instance, SharedPrefsManager.instance)

    fun getQuakesPresenter(view: QuakesScreenContract.IView): QuakesScreenContract.IPresenter {
        return QuakesListPresenter(view)
    }

    fun getSplashPresenter(view: SplashScreenContract.IView): SplashScreenContract.IPresenter {
        return SplashPresenter(view)
    }
}
