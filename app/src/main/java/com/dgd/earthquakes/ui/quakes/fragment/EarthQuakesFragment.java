package com.dgd.earthquakes.ui.quakes.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.databinding.FragmentRecyclerBinding;
import com.dgd.earthquakes.injection.Injection;
import com.dgd.earthquakes.models.Quake;
import com.mdgd.commons.recycler.CommonRecyclerAdapter;
import com.mdgd.commons.support.v7.fragment.recycler.SwipeRecyclerFragment;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Max
 * on 01-May-17.
 */

public class EarthQuakesFragment extends SwipeRecyclerFragment<QuakesFragmentContract.IPresenter, QuakesFragmentContract.IHost, Quake>
        implements QuakesFragmentContract.IFragment, QuakesFragmentContract.IView, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private static final float TRANSLATE = -500;
    private FragmentRecyclerBinding binding;

    public static EarthQuakesFragment newInstance(){
        return new EarthQuakesFragment();
    }

    @Override
    protected QuakesFragmentContract.IPresenter getPresenter() {
        return Injection.getEarthQuakesPresenter(this);
    }

    @Override
    protected CommonRecyclerAdapter<Quake> getAdapter() {
        return new EarthQuakesAdapter(getActivity(), this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler, container, false);
        initViews(binding.getRoot());
        return binding.getRoot();
    }

    protected void initViews(View v) {
        binding.swipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN);
        binding.swipeRefresh.setOnRefreshListener(this);

        binding.recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = getAdapter();
        binding.recycler.setAdapter(adapter);
        binding.recycler.addOnScrollListener(new EndlessScrollListener(){

            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.getNextBulk(((EarthQuakesAdapter)adapter).getLastDate());
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
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
        presenter.getEarthQuakes(null);
    }

    public void updateEarthQuakes(List<Quake> quakes){
        binding.toolbarInc.toolbarIcon.requestFocus();
        if(adapter != null){
            adapter.setItems(quakes);
        }
    }

    @Override
    public void onRefresh() {
        presenter.refreshEarthQuakes();
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
                SearchDTO searchData = SearchDTO.createBuilder()
                    .setQuery(binding.toolbarInc.search.getText().toString())
                    .setFromTime(binding.searchParams.fromTime.getText().toString())
                    .setFromDate(binding.searchParams.fromDate.getText().toString())
                    .setFromMagnitude(binding.searchParams.fromMagnitude.getText().toString())
                    .setToTime(binding.searchParams.toTime.getText().toString())
                    .setToDate(binding.searchParams.toDate.getText().toString())
                    .setToMagnitude(binding.searchParams.toMagnitude.getText().toString())
                    .build();
                presenter.getEarthQuakes(searchData);
            }
        }
        else if (id == R.id.fromTime) {
            new TimePickerDialog(getActivity(), (TimePicker timePicker, int h, int m) ->
                binding.searchParams.fromTime.setText(String.format(Locale.getDefault(), "%1$2d : %2$2d", h, m)),
            0, 0, true).show();
        }
        else if (id == R.id.toTime) {
            new TimePickerDialog(getActivity(), (TimePicker timePicker, int h, int m) ->
                binding.searchParams.toTime.setText(String.format(Locale.getDefault(), "%1$2d : %2$2d", h, m)),
            0, 0, true).show();
        }
        else if (id == R.id.fromDate) {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(getActivity(), (DatePicker datePicker, int y, int m, int d) ->
                binding.searchParams.fromDate.setText(String.format(Locale.getDefault(), "%1$2d.%2$2d.%3$4d", d, m, y)),
            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
        else if (id == R.id.toDate) {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(getActivity(), (DatePicker datePicker, int y, int m, int d) ->
                binding.searchParams.toDate.setText(String.format(Locale.getDefault(), "%1$2d.%2$2d.%3$4d", d, m, y)),
            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        float translateDist = b ? (-1 * TRANSLATE) : TRANSLATE;
        binding.searchParams.getRoot().animate().translationYBy(translateDist).setDuration(300).start();
    }
}
