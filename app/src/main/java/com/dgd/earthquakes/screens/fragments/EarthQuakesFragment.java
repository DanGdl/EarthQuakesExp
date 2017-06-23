package com.dgd.earthquakes.screens.fragments;

import android.content.Context;
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
import com.dgd.earthquakes.adapters.EarthQuakesAdapter;
import com.dgd.earthquakes.databinding.FragmentEarthquakesBinding;
import com.dgd.earthquakes.models.IQuake;
import com.dgd.earthquakes.screens.interfaces.IEarthquakesFragmentHost;
import com.dgd.earthquakes.screens.interfaces.IQuakesUpdated;

import java.util.List;

/**
 * Created by Max on 01-May-17.
 */

public class EarthQuakesFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
        IQuakesUpdated, IEarthQuakesFragment {

    private IEarthquakesFragmentHost mHost;
    private FragmentEarthquakesBinding mBinding;
    private EarthQuakesAdapter mAdapter;

    public static EarthQuakesFragment getInstance(){
        return new EarthQuakesFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof IEarthquakesFragmentHost){
            mHost = (IEarthquakesFragmentHost)context;
        }
        else{
            throw new IllegalStateException(getString(R.string.no_earthquake_implemented));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mHost = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_earthquakes, container, false);
        setupRecycler();
        setupSwipe();
        return mBinding.getRoot();
    }

    private void setupSwipe() {
        mBinding.earthquakeSwipe.setColorSchemeColors(Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN);
        mBinding.earthquakeSwipe.setOnRefreshListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(mHost != null){
            mHost.getEarthQuakes();
        }
    }

    public void updateEarthQuakes(List<IQuake> quakes){
        mAdapter.setQuakes(quakes);
    }

    private void setupRecycler() {
        RecyclerView recycler =  mBinding.earthquakesRecycler;
        mAdapter = new EarthQuakesAdapter(getActivity());
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(mAdapter);
        recycler.addOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                getNextBulk();
            }
        });
    }

    private void getNextBulk(){
        if(mHost != null){
            mBinding.earthquakeSwipe.setRefreshing(true);
            mHost.getNextBulk(mAdapter.getLastDate(), this);
        }
    }

    @Override
    public void onRefresh() {
        if(mHost != null){
            mHost.refreshEarthQuakes(this);
        }
        else {
            mBinding.earthquakeSwipe.setRefreshing(false);
        }
    }

    @Override
    public void quakesUpdated(List<IQuake> quakes) {
        mAdapter.addQuakes(quakes);
        mBinding.earthquakeSwipe.setRefreshing(false);
    }
}
