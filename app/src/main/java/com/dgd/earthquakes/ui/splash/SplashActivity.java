package com.dgd.earthquakes.ui.splash;

import android.support.v4.app.Fragment;

import com.dgd.earthquakes.injection.Injection;
import com.mdgd.commons.support.v7.fragment.HostActivity;

/**
 * Created by Max
 * on 01-May-17.
 */

public class SplashActivity extends HostActivity<SplashScreenContract.IPresenter> implements SplashScreenContract.IView {

    @Override
    protected SplashScreenContract.IPresenter getPresenter() {
        return Injection.getSplashPresenter(this);
    }

    @Override
    protected Fragment getFirstFragment() {
        return SplashFragment.newInstance();
    }
}
