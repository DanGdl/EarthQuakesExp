package com.dgd.earthquakes.common

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dgd.earthquakes.R
import com.dgd.earthquakes.databinding.FragmentRecyclerBinding

/**
 * Created by Dan
 * on 24/07/2017.
 */

abstract class RecyclerFragment<T : FragmentContract.IHost, X> : HostedFragment<T>(), CommonRecyclerAdapter.IOnItemClickListener<X>, SwipeRefreshLayout.OnRefreshListener {

    protected var adapter: CommonRecyclerAdapter<X>? = null
    protected var binding: FragmentRecyclerBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler, container, false)
        setHasProgress(true)
        initViews()
        return binding?.root
    }

    protected fun initViews() {
        binding?.swipeRefresh?.setColorSchemeColors(Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN)
        binding?.swipeRefresh?.setOnRefreshListener(this)

        binding?.recycler?.layoutManager = LinearLayoutManager(activity)
        adapter = createAdapter()
        binding?.recycler?.adapter = adapter
        binding?.recycler?.addOnScrollListener(object : EndlessScrollListener() {

            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                this@RecyclerFragment.onLoadMore(page, totalItemsCount, view)
            }
        })
    }

    override fun showProgress() {
        binding?.swipeRefresh?.isRefreshing = true
    }

    override fun hideProgress() {
        binding?.swipeRefresh?.isRefreshing = false
    }

    protected abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView)

    protected abstract fun createAdapter(): CommonRecyclerAdapter<X>
}
