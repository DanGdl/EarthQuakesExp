package com.dgd.earthquakes.screens.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.databinding.ActivitySplashBinding;

/**
 * Created by Max on 01-May-17.
 */

public class SplashActivity extends BaseActivity implements Runnable {
    private ActivitySplashBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        updateQuakes();
    }

    private void updateQuakes() {
        getRepo().checkNewEarthquakes();
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
