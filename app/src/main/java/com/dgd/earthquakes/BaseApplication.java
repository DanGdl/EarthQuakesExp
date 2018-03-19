package com.dgd.earthquakes;

import android.app.Application;

import com.dgd.earthquakes.data.IRepo;

/**
 * Created by Max
 * on 30-Apr-17.
 */
public class BaseApplication extends Application {

    private static BaseApplication mSingleton;
    private IRepo repository;

    @Override
    public void onCreate() {
        super.onCreate();
        mSingleton = this;
        repository = Injection.getRepository();
    }

    public static BaseApplication getInstance() {
        return mSingleton;
    }

    public IRepo getRepository(){
        return repository;
    }
}
