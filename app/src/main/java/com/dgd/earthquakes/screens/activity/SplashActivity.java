package com.dgd.earthquakes.screens.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.data.database.SQLiteManager;
import com.dgd.earthquakes.databinding.ActivitySplashBinding;
import com.dgd.earthquakes.data.network.NetworkManager;
import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;
import com.dgd.earthquakes.data.network.infra.QuakeData;
import com.dgd.earthquakes.util.SharedPrefsManager;

import java.util.Date;
import java.util.List;

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
        NetworkManager.getInstance().checkNewEarthquakes(this);
    }

    @Override
    public void onNetworkError(String errorMessage, int errorCode) {
        showMessage("Shit happens! " + errorMessage);
    }

    @Override
    public void onNetworkSuccess(List<QuakeData> quakes) {
        SharedPrefsManager.getInstance().saveLastUpdateDate(new Date().getTime());
        SQLiteManager.getInstance().saveQuakes(quakes);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.getRoot().postDelayed(this, 3000);
    }

    @Override
    public void run() {
        startActivity(MainActivity.getIntent(SplashActivity.this));
    }
}
