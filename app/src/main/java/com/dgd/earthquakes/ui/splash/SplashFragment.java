package com.dgd.earthquakes.ui.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.injection.Injection;
import com.dgd.earthquakes.ui.quakes.QuakesActivity;
import com.mdgd.commons.support.v7.fragment.HostedFragment;

/**
 * Created by Max
 * on 01-May-17.
 */

public class SplashFragment extends HostedFragment<SplashFragmentContract.IPresenter, SplashFragmentContract.IHost>
        implements SplashFragmentContract.IFragment, SplashFragmentContract.IView {

    public static SplashFragment newInstance(){
        return new SplashFragment();
    }

    @Override
    protected SplashFragmentContract.IPresenter getPresenter() {
        return Injection.getSplashFragmentPresenter(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_splash;
    }

    @Override
    protected void initViews(View view) {}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.updateQuakes();
    }

    @Override
    public void proceedFromSplash() {
        startActivity(QuakesActivity.getIntent(getActivity()));
    }
}
