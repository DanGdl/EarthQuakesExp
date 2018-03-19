package com.dgd.earthquakes.ui.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.common.HostedFragment;

/**
 * Created by Max
 * on 01-May-17.
 */

public class SplashFragment extends HostedFragment<SplashFragmentContract.ISplashFragmentHost>
        implements SplashFragmentContract.ISplashFragment {

    public static SplashFragment newInstance(){
        return new SplashFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }
}
