package com.dgd.earthquakes;

import android.app.Application;

import com.dgd.earthquakes.injection.ComponentProvider;
import com.dgd.earthquakes.injection.Injection;

/**
 * Created by Max
 * on 30-Apr-17.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Injection.setComponentProvider(new ComponentProvider(this));
    }
}
