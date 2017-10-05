package com.dgd.earthquakes.ui.quakes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dgd.earthquakes.common.FragmentHostActivity;
import com.dgd.earthquakes.common.HostedFragment;
import com.dgd.earthquakes.models.Quake;

import io.realm.RealmResults;

public class MainHostActivity extends FragmentHostActivity<IQuakesListPresenter>
        implements IEarthQuakesFragmentHost, IQuakesListView {

    private IEarthQuakesFragment earthQuakesFragment;

    public static Intent getIntent(Context context){
        return new Intent(context, MainHostActivity.class);
    }

    @Override
    protected IQuakesListPresenter setupPresenter() {
        return new QuakesListPresenter(this);
    }

    @Override
    protected HostedFragment getFirstFragment(Bundle savedInstanceState) {
        EarthQuakesFragment f = EarthQuakesFragment.newInstance();
        earthQuakesFragment = f;
        return f;
    }

    @Override
	public void getEarthQuakes() {
        presenter.getEarthQuakes();
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
}
