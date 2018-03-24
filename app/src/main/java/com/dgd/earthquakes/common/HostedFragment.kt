package com.dgd.earthquakes.common

import android.content.Context
import android.support.v4.app.Fragment

/**
 * Created by Dan
 * on 19/07/2017.
 */

abstract class HostedFragment<T : FragmentContract.IHost> : Fragment(), FragmentContract.IFragment {
    protected var host: T? = null
    private var hasProgress: Boolean = false

    override fun onAttach(activity: Context?) {
        super.onAttach(activity)
        host = activity as T?
    }

    override fun onDetach() {
        super.onDetach()
        host = null
    }

    protected fun setHasProgress(hasProgress: Boolean) {
        this.hasProgress = hasProgress
    }

    override fun hasProgress(): Boolean {
        return hasProgress
    }

    override fun showProgress() {}

    override fun hideProgress() {}
}
