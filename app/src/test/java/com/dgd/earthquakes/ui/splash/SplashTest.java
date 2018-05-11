package com.dgd.earthquakes.ui.splash;

import com.dgd.earthquakes.BaseApplication;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Owner
 * on 19/03/2018.
 */

public class SplashTest {

    @Before
    public void setupApplication(){
        new BaseApplication();
    }

    @Test
    public void test(){
        SplashActivity a = new SplashActivity();
        a.onCreate(a.getBundle());
        a.onPostCreate();
//        a.onRestoreInstanceState(a.getBundle());

        a.onStart();
        a.onResume();

        a.onPause();
        a.onStop();
        a.onDestroy();
    }
}
