package com.dgd.earthquakes.ui.quakes;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.common.CommonRecyclerAdapter;
import com.dgd.earthquakes.common.RecyclerFragment;
import com.dgd.earthquakes.models.Quake;

import java.util.Calendar;
import java.util.Locale;

import io.realm.RealmResults;

/**
 * Created by Max
 * on 01-May-17.
 */

public class EarthQuakesFragment extends RecyclerFragment<QuackesFragmentContract.IEarthQuakesFragmentHost, Quake>
        implements QuackesFragmentContract.IEarthQuakesFragment, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private static final float TRANSLATE = -500;

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
        binding.toolbarInc.extensionToggle.setOnCheckedChangeListener(this);

        binding.searchParams.fromTime.setOnClickListener(this);
        binding.searchParams.fromDate.setOnClickListener(this);
        binding.searchParams.toTime.setOnClickListener(this);
        binding.searchParams.toDate.setOnClickListener(this);

        binding.searchParams.getRoot().animate().translationYBy(TRANSLATE).setDuration(1).start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(host != null){
            host.getEarthQuakes(null);
        }
    }

    public void updateEarthQuakes(RealmResults<Quake> quakes){
        binding.toolbarInc.toolbarIcon.requestFocus();
        if(adapter != null){
            adapter.setItems(quakes);
        }
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
        int id = view.getId();
        if (id == R.id.searchBtn) {
            if(host != null) {
                SearchDTO.SearchParamsBuilder builder = SearchDTO.createBuilder();
                builder.setQuery(binding.toolbarInc.search.getText().toString());
                builder.setFromTime(binding.searchParams.fromTime.getText().toString());
                builder.setFromDate(binding.searchParams.fromDate.getText().toString());
                builder.setFromMagnitude(binding.searchParams.fromMagnitude.getText().toString());
                builder.setToTime(binding.searchParams.toTime.getText().toString());
                builder.setToDate(binding.searchParams.toDate.getText().toString());
                builder.setToMagnitude(binding.searchParams.toMagnitude.getText().toString());
                host.getEarthQuakes(builder.build());
            }
        }
        else if (id == R.id.fromTime) {
            new TimePickerDialog(getActivity(), (TimePicker timePicker, int h, int m) -> {
                binding.searchParams.fromTime.setText(String.format(Locale.getDefault(), "%1$2d : %2$2d", h, m));
            }, 0, 0, true).show();
        }
        else if (id == R.id.toTime) {
            new TimePickerDialog(getActivity(), (TimePicker timePicker, int h, int m) -> {
                binding.searchParams.toTime.setText(String.format(Locale.getDefault(), "%1$2d : %2$2d", h, m));
            }, 0, 0, true).show();
        }
        else if (id == R.id.fromDate) {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(getActivity(), (DatePicker datePicker, int y, int m, int d) -> {
                binding.searchParams.fromDate.setText(String.format(Locale.getDefault(), "%1$2d.%2$2d.%3$4d", d, m, y));
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
        else if (id == R.id.toDate) {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(getActivity(), (DatePicker datePicker, int y, int m, int d) -> {
                binding.searchParams.toDate.setText(String.format(Locale.getDefault(), "%1$2d.%2$2d.%3$4d", d, m, y));
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        float translateDist = b ? (-1 * TRANSLATE) : TRANSLATE;
        binding.searchParams.getRoot().animate().translationYBy(translateDist).setDuration(300).start();
    }
}
