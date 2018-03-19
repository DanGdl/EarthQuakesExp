package com.dgd.earthquakes;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.dgd.earthquakes.common.IFragmentHostActivity;
import com.dgd.earthquakes.data.IRepo;

import java.lang.ref.WeakReference;

/**
 * Created by dan
 * on 24/05/16.
 */
class LifeCycleListener implements Application.ActivityLifecycleCallbacks {
    private WeakReference<IFragmentHostActivity> mCurrentActivity;
    private boolean isResumed = false;
    private boolean isStarted = false;
//    private boolean isInBackground = true;
    private IRepo repository;

    LifeCycleListener(IRepo repository) {
        this.repository = repository;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mCurrentActivity = new WeakReference(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
//        isInBackground = false;
        isStarted = true;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        isResumed = true;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        isResumed = false;
    }

    @Override
    public void onActivityStopped(Activity activity) {
//        isInBackground = true;
        isStarted = false;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.e("EARTH", "ACTIVITY DESTROYED! " + activity.getClass().getSimpleName());
        if(!isResumed && !isStarted) {
            repository.closeRealm();
        }
    }

//    public IFragmentHostActivity getCurrentActivity() {
//        if(mCurrentActivity.get() != null) {
//            return mCurrentActivity.get();
//        }
//        return null;
//    }
//
//    public boolean isResumed() {
//        return isResumed;
//    }
//
//    public boolean isStarted() {
//        return isStarted;
//    }
//
//    public boolean isInBackground() {
//        return isInBackground;
//    }
}
