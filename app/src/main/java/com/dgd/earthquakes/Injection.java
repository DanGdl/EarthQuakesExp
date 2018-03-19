package com.dgd.earthquakes;

import com.dgd.earthquakes.data.IRepo;
import com.dgd.earthquakes.data.Repo;
import com.dgd.earthquakes.data.database.SQLiteManager;
import com.dgd.earthquakes.data.network.NetworkManager;
import com.dgd.earthquakes.data.prefs.SharedPrefsManager;
import com.dgd.earthquakes.ui.quakes.QuackesScreenContract;
import com.dgd.earthquakes.ui.quakes.QuakesListPresenter;
import com.dgd.earthquakes.ui.splash.SplashPresenter;
import com.dgd.earthquakes.ui.splash.SplashScreenContract;

/**
 * Created by Owner
 * on 19/03/2018.
 */

public class Injection {

    static IRepo getRepository() {
        return new Repo(new NetworkManager(), SQLiteManager.getInstance(), SharedPrefsManager.getInstance());
    }

    public static QuackesScreenContract.IQuakesListPresenter getQuakesPresenter(QuackesScreenContract.IQuakesListView view) {
        return new QuakesListPresenter(view);
    }

    public static SplashScreenContract.ISplashPresenter getSplashPresenter(SplashScreenContract.ISplashView view) {
        return new SplashPresenter(view);
    }
}
