package com.dgd.earthquakes.common;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.databinding.FragmentRecyclerBinding;

/**
 * Created by Dan on 24/07/2017.
 */

public abstract class RecyclerFragment<T, X> extends HostedFragment<T> implements
        CommonRecyclerAdapter.IOnItemClickListener<X>, SwipeRefreshLayout.OnRefreshListener {

    protected CommonRecyclerAdapter<X> adapter;
    protected FragmentRecyclerBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler, container, false);
        setHasProgress(true);
        initViews();
        return binding.getRoot();
    }

    protected void initViews() {
        binding.swipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN);
        binding.swipeRefresh.setOnRefreshListener(this);

        binding.recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = getAdapter();
        binding.recycler.setAdapter(adapter);
        binding.recycler.addOnScrollListener(new EndlessScrollListener(){

            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                RecyclerFragment.this.onLoadMore(page, totalItemsCount, view);
            }
        });
    }

    @Override
    public void showProgress() {
        binding.swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        binding.swipeRefresh.setRefreshing(false);
    }

    protected abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);

    protected abstract CommonRecyclerAdapter<X> getAdapter();
}
