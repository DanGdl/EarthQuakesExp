package com.dgd.earthquakes.ui.quakes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.common.CommonRecyclerAdapter;
import com.dgd.earthquakes.common.RecyclerFragment;
import com.dgd.earthquakes.models.Quake;

import io.realm.RealmResults;

/**
 * Created by Max on 01-May-17.
 */

public class EarthQuakesFragment extends RecyclerFragment<IEarthQuakesFragmentHost, Quake> implements
        IEarthQuakesFragment, View.OnClickListener {

    public static EarthQuakesFragment newInstance(){
        return new EarthQuakesFragment();
    }

    @Override
    protected void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
        if(host != null){
            host.getNextBulk(((EarthQuakesAdapter)adapter).getLastDate());
        }
    }

    @Override
    protected CommonRecyclerAdapter<Quake> getAdapter() {
        return new EarthQuakesAdapter(getActivity(), this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.toolbarInc.searchBtn.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(host != null){
            host.getEarthQuakes("");
        }
    }

    public void updateEarthQuakes(RealmResults<Quake> quakes){
        adapter.setItems(quakes);
    }

    @Override
    public void onRefresh() {
        if(host != null){
            host.refreshEarthQuakes();
        }
    }

    @Override
    public void onItemClicked(Quake q, int position) {
        QuakeDialog dialog = new QuakeDialog(getActivity());
        dialog.setQuake(q);
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.searchBtn){
            host.getEarthQuakes(binding.toolbarInc.search.getText().toString());
        }
    }
}
