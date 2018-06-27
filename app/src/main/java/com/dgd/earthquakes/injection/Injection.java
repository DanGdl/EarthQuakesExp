package com.dgd.earthquakes.injection;

import com.dgd.earthquakes.ui.quakes.QuakesContract;
import com.dgd.earthquakes.ui.quakes.QuakesPresenter;
import com.dgd.earthquakes.ui.quakes.fragment.EarthQuakesFragmentPresenter;
import com.dgd.earthquakes.ui.quakes.fragment.QuakesFragmentContract;
import com.dgd.earthquakes.ui.splash.SplashFragmentContract;
import com.dgd.earthquakes.ui.splash.SplashFragmentPresenter;
import com.dgd.earthquakes.ui.splash.SplashPresenter;
import com.dgd.earthquakes.ui.splash.SplashScreenContract;

/**
 * Created by Owner
 * on 19/03/2018.
 */

public class Injection {

    private static IComponentProvider provider;

    public static void setComponentProvider(IComponentProvider componentProvider) {
        Injection.provider = componentProvider;
    }

    public static IComponentProvider getComponentProvider() {
        return provider;
    }

    public static QuakesContract.IPresenter getQuakesPresenter(QuakesContract.IView view) {
        return new QuakesPresenter(view);
    }

    public static SplashScreenContract.IPresenter getSplashPresenter(SplashScreenContract.IView view) {
        return new SplashPresenter(view);
    }

    public static SplashFragmentContract.IPresenter getSplashFragmentPresenter(SplashFragmentContract.IView view) {
        return new SplashFragmentPresenter(view, provider.getRepository());
    }

    public static QuakesFragmentContract.IPresenter getEarthQuakesPresenter(QuakesFragmentContract.IView view) {
        return new EarthQuakesFragmentPresenter(view, provider.getRepository());
    }
}
