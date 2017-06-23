package com.dgd.earthquakes.screens.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.databinding.FragmentSplashBinding;

/**
 * Created by Max on 01-May-17.
 */

public class SplashFragment extends BaseFragment {

    private FragmentSplashBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false);
        return mBinding.getRoot();
    }
}
