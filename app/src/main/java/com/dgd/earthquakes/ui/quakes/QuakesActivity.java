package com.dgd.earthquakes.ui.quakes;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.dgd.earthquakes.injection.Injection;
import com.dgd.earthquakes.ui.quakes.fragment.EarthQuakesFragment;
import com.dgd.earthquakes.ui.quakes.fragment.QuakesFragmentContract;
import com.mdgd.commons.support.v7.fragment.HostActivity;

public class QuakesActivity extends HostActivity<QuakesContract.IPresenter>
        implements QuakesFragmentContract.IHost, QuakesContract.IView {

    public static Intent getIntent(Context context){
        return new Intent(context, QuakesActivity.class);
    }

    @Override
    protected QuakesContract.IPresenter getPresenter() {
        return Injection.getQuakesPresenter(this);
    }

    @Override
    protected Fragment getFirstFragment() {
        return EarthQuakesFragment.newInstance();
    }
}
