package com.dgd.earthquakes.screens.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.data.database.SQLiteManager;
import com.dgd.earthquakes.databinding.ActivityMainBinding;
import com.dgd.earthquakes.models.IQuake;
import com.dgd.earthquakes.data.network.NetworkManager;
import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;
import com.dgd.earthquakes.data.network.infra.QuakeData;
import com.dgd.earthquakes.screens.fragments.IEarthQuakesFragment;
import com.dgd.earthquakes.screens.interfaces.IEarthquakesFragmentHost;
import com.dgd.earthquakes.screens.interfaces.IQuakesUpdated;
import com.google.android.agera.Updatable;

import java.util.Date;
import java.util.List;

public class MainActivity extends BaseActivity implements IEarthquakesFragmentHost, Updatable {
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
    protected void onStart() {
        super.onStart();
        getRepository().subscribeToEarthquakes(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getRepository().unsubscribeFromEarthquakes(this);
    }

    @Override
	public void getEarthQuakes() {
        // todo add query params
		mFragment.updateEarthQuakes(SQLiteManager.getInstance().getQuakesBulk(0));
	}

	@Override
    public void refreshEarthQuakes(final IQuakesUpdated listener) {
//        getRepository().checkNewEarthquakes();
        NetworkManager.getInstance().checkNewEarthquakes(new IQuakesCallbackListener() {
            @Override
            public void onNetworkError(String errorMessage, int errorCode) {
                showMessage("Shit happens! " + errorMessage);
            }

            @Override
            public void onNetworkSuccess(List<QuakeData> quakes) {
                SQLiteManager.getInstance().saveQuakes(quakes);
                listener.quakesUpdated(SQLiteManager.getInstance().getQuakesBulk(0));
            }
        });
    }

    @Override
    public void getNextBulk(final long lastDate, final IQuakesUpdated listener) {
        final List<IQuake> quakeModels = SQLiteManager.getInstance().getQuakesBulk(lastDate);
        if(quakeModels.isEmpty()){
            Date end = new Date(lastDate);
            Date start = new Date(lastDate);
            start.setDate(start.getDate() - 2);
            NetworkManager.getInstance().getEarthquakes(start, end, new IQuakesCallbackListener() {
                @Override
                public void onNetworkError(String errorMessage, int errorCode) {
                    showMessage("Shit happens! " + errorMessage);
                }

                @Override
                public void onNetworkSuccess(List<QuakeData> quakes) {
                    SQLiteManager.getInstance().saveQuakes(quakes);
                    quakeModels.addAll(SQLiteManager.getInstance().getQuakesBulk(lastDate));
                    listener.quakesUpdated(quakeModels);
                }
            });
        }
        else{
            listener.quakesUpdated(quakeModels);
        }
    }

    @Override
    public void update() {
        List<IQuake> quakes = getRepository().getEarthQuakesRepository().get().get();
        mFragment.updateEarthQuakes(quakes);
    }
}
