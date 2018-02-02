package com.dgd.earthquakes.ui.splash;

import android.os.Bundle;

import com.dgd.earthquakes.common.FragmentHostActivity;
import com.dgd.earthquakes.common.HostedFragment;
import com.dgd.earthquakes.ui.quakes.MainHostActivity;

/**
 * Created by Max
 * on 01-May-17.
 */

public class SplashHostActivity extends FragmentHostActivity<SplashScreenContract.ISplashPresenter>
        implements SplashScreenContract.ISplashView {

    @Override
    protected SplashScreenContract.ISplashPresenter setupPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected HostedFragment getFirstFragment(Bundle savedInstanceState) {
        presenter.updateQuakes();
        return SplashFragment.newInstance();
    }

    @Override
    public void proceedFromSplash() {
        startActivity(MainHostActivity.getIntent(SplashHostActivity.this));
    }
}
