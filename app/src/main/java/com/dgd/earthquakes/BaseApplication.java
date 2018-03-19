package com.dgd.earthquakes;

import android.app.Application;

/**
 * Created by Max
 * on 30-Apr-17.
 */
public class BaseApplication extends Application{

    private static BaseApplication mSingleton;

    @Override
    public void onCreate() {
        super.onCreate();
        mSingleton = this;
    }

    public static BaseApplication getInstance() {
        return mSingleton;
    }
}
