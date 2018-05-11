package com.dgd.earthquakes.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dgd.earthquakes.R
import com.dgd.earthquakes.common.HostedFragment

/**
 * Created by Max
 * on 01-May-17.
 */

class SplashFragment : HostedFragment<SplashFragmentContract.IHost>(), SplashFragmentContract.IFragment {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    companion object {

        fun newInstance(): SplashFragment {
            return SplashFragment()
        }
    }
}
