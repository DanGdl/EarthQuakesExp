package com.dgd.earthquakes.ui.splash;

import com.mdgd.commons.support.v7.mvp.Presenter;

/**
 * Created by max
 * on 04/10/17.
 */

public class SplashPresenter extends Presenter<SplashScreenContract.IView> implements SplashScreenContract.IPresenter {

    public SplashPresenter(SplashScreenContract.IView view) {
        super(view);
    }
}
