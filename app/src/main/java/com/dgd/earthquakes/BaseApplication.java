package com.dgd.earthquakes;

import android.app.Application;

import com.dgd.earthquakes.data.IRepo;
import com.dgd.earthquakes.data.Repo;
import com.dgd.earthquakes.screens.activity.BaseActivity;

/**
 * Created by Max on 30-Apr-17.
 */
public class BaseApplication extends Application{

    private static BaseApplication mSingleton;
    private IRepo repository;
    private LifeCycleListener lifecycleListener;

    @Override
    public void onCreate() {
        super.onCreate();
        mSingleton = this;
        repository = new Repo();
        lifecycleListener = new LifeCycleListener(repository);
        registerActivityLifecycleCallbacks(lifecycleListener);
    }

    public static BaseApplication getInstance() {
        return mSingleton;
    }

    public IRepo getRepository(){
        return repository;
    }

    public BaseActivity getCurrentActivity(){
        if(lifecycleListener != null){
            return lifecycleListener.getCurrentActivity();
        }
        return null;
    }
}
