package com.dgd.earthquakes.screens.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.databinding.ActivitySplashBinding;
import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;
import com.dgd.earthquakes.data.network.infra.QuakeData;
import com.dgd.earthquakes.models.IQuake;
import com.dgd.earthquakes.models.Quake;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Max on 01-May-17.
 */

public class SplashActivity extends BaseActivity implements IQuakesCallbackListener, Runnable {
    private ActivitySplashBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        updateQuakes();
    }

    private void updateQuakes() {
        getRepo().checkNewEarthquakes(this);
    }

    @Override
    public void onNetworkError(String errorMessage, int errorCode) {
        showMessage(getString(R.string.shit, errorMessage));
    }

    @Override
    public void onNetworkSuccess(List<QuakeData> quakes) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.getRoot().postDelayed(this, 2500);
    }

    @Override
    public void run() {
        startActivity(MainActivity.getIntent(SplashActivity.this));
    }
}
