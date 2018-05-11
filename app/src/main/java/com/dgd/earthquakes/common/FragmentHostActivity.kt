package com.dgd.earthquakes.common

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.dgd.earthquakes.R

/**
 * Created by Dan
 * on 25/07/2017.
 */

abstract class FragmentHostActivity<T : ScreenContract.IPresenter> : AppCompatActivity(), FragmentContract.IHost {
    private var mProgressDialog: ProgressDialog? = null
    private var onForeground = false
    protected var presenter: T = setupPresenter()
    private var currentFragment: HostedFragment<*>? = null

    private val layoutResId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        setFragment(getFirstFragment(savedInstanceState))
    }

    protected abstract fun setupPresenter(): T

    @JvmOverloads
    protected fun setFragment(fragment: HostedFragment<*>, addToStack: Boolean = false, backStackTag: String? = null) {
        currentFragment = fragment
        val transaction = supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment)
        if (addToStack) {
            transaction.addToBackStack(backStackTag)
        }
        transaction.commit()
    }

    protected abstract fun getFirstFragment(savedInstanceState: Bundle?): HostedFragment<*>

    fun showProgress(title: String, msg: String) {
        if (currentFragment?.hasProgress()!!) {
            currentFragment?.showProgress()
        } else {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog(this)
                mProgressDialog?.setTitle(title)
                mProgressDialog?.setMessage(msg)
            }

            if (onForeground && !mProgressDialog?.isShowing!!) {
                mProgressDialog?.show()
            }
        }
    }

    fun hideProgress() {
        if (currentFragment?.hasProgress()!!) {
            currentFragment?.hideProgress()
        }
        if (mProgressDialog?.isShowing!!) {
            mProgressDialog?.dismiss()
            mProgressDialog = null
        }
    }

    override fun onStart() {
        super.onStart()
        onForeground = true
    }

    override fun onPause() {
        onForeground = false
        super.onPause()
    }

    fun showMessage(messageRes: Int, vararg args: String) {
        Toast.makeText(this, getString(messageRes, *args), Toast.LENGTH_SHORT).show()
    }
}
