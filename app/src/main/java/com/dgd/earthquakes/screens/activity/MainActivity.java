package com.dgd.earthquakes.screens.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.databinding.ActivityMainBinding;
import com.dgd.earthquakes.models.Quake;
import com.dgd.earthquakes.screens.fragments.IEarthQuakesFragment;
import com.dgd.earthquakes.screens.interfaces.IEarthquakesFragmentHost;

import io.realm.RealmResults;

public class MainActivity extends BaseActivity implements IEarthquakesFragmentHost {
	private ActivityMainBinding mBinding;
    private IEarthQuakesFragment mFragment;

    public static Intent getIntent(Context context){
        return new Intent(context, MainActivity.class);
    }

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mFragment = (IEarthQuakesFragment)getSupportFragmentManager().findFragmentById(R.id.earthquakesFragment);
    }

    @Override
	public void getEarthQuakes() {
        // todo add query params
        RealmResults<Quake> quakes = getRepo().getAllQuakes();
        mFragment.updateEarthQuakes(quakes);
	}

	@Override
    public void refreshEarthQuakes() {
        getRepo().checkNewEarthquakes();
    }

    @Override
    public void getNextBulk(final long lastDate) {
//        if(quakeModels.isEmpty()){
//            Date end = new Date(lastDate);
//            Date start = new Date(lastDate);
//            start.setDate(start.getDate() - 2);
//            getNetwork().getEarthquakes(start, end, new IQuakesCallbackListener() {
//                @Override
//                public void onNetworkError(String errorMessage, int errorCode) {
//                    showMessage(getString(R.string.shit, errorMessage));
//                }
//
//                @Override
//                public void onNetworkSuccess(List<QuakeData> quakes) {
//                    getDB().saveQuakes(quakes);
//                    quakeModels.addAll(getDB().getQuakesBulk(lastDate));
//                }
//            });
//        }
    }

    @Override
    public void hideProgress() {
        mFragment.hideProgress();
    }
}
