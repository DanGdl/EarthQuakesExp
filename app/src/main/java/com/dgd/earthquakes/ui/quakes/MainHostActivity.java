package com.dgd.earthquakes.ui.quakes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dgd.earthquakes.Injection;
import com.dgd.earthquakes.common.FragmentHostActivity;
import com.dgd.earthquakes.common.HostedFragment;
import com.dgd.earthquakes.models.Quake;

import java.util.List;

public class MainHostActivity extends FragmentHostActivity<QuackesScreenContract.IQuakesListPresenter>
        implements QuackesFragmentContract.IEarthQuakesFragmentHost, QuackesScreenContract.IQuakesListView {

    private QuackesFragmentContract.IEarthQuakesFragment earthQuakesFragment;

    public static Intent getIntent(Context context){
        return new Intent(context, MainHostActivity.class);
    }

    @Override
    protected QuackesScreenContract.IQuakesListPresenter setupPresenter() {
        return Injection.getQuakesPresenter(this);
    }

    @Override
    protected HostedFragment getFirstFragment(Bundle savedInstanceState) {
        EarthQuakesFragment f = EarthQuakesFragment.newInstance();
        earthQuakesFragment = f;
        return f;
    }

    @Override
	public void getEarthQuakes(SearchDTO searchData) {
        presenter.getEarthQuakes(searchData);
	}

	@Override
    public void refreshEarthQuakes() {
        presenter.checkNewEarthQuakes();
    }

    @Override
    public void getNextBulk(final long lastDate) {
        presenter.getNextBulk(lastDate);
    }

    @Override
    public void updateEarthQuakes(List<Quake> quakes) {
        earthQuakesFragment.updateEarthQuakes(quakes);
    }

    @Override
    public void showProgress(String title, int msgResId) {
        showProgress(title, getString(msgResId));
    }
}
