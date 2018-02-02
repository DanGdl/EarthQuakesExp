package com.dgd.earthquakes.ui.quakes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dgd.earthquakes.common.FragmentHostActivity;
import com.dgd.earthquakes.common.HostedFragment;
import com.dgd.earthquakes.models.Quake;

import io.realm.RealmResults;

public class MainHostActivity extends FragmentHostActivity<QuackesScreenContract.IQuakesListPresenter>
        implements QuackesFragmentContract.IEarthQuakesFragmentHost, QuackesScreenContract.IQuakesListView {

    private QuackesFragmentContract.IEarthQuakesFragment earthQuakesFragment;

    public static Intent getIntent(Context context){
        return new Intent(context, MainHostActivity.class);
    }

    @Override
    protected QuackesScreenContract.IQuakesListPresenter setupPresenter() {
        return new QuakesListPresenter(this);
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
    public void updateEarthQuakes(RealmResults<Quake> quakes) {
        earthQuakesFragment.updateEarthQuakes(quakes);
    }

    @Override
    public void showProgress(String title, int msgResId) {
        showProgress(title, getString(msgResId));
    }
}
