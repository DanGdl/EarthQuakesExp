package com.dgd.earthquakes.ui.splash;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.data.IRepo;
import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;
import com.dgd.earthquakes.data.network.infra.QuakeData;
import com.mdgd.commons.support.v7.fragment.FragmemtPresenter;

import java.util.Date;
import java.util.List;

/**
 * Created by Owner
 * on 27/06/2018.
 */
public class SplashFragmentPresenter extends FragmemtPresenter<SplashFragmentContract.IView> implements SplashFragmentContract.IPresenter {

    private final IRepo repository;

    public SplashFragmentPresenter(SplashFragmentContract.IView view, IRepo repository) {
        super(view);
        this.repository = repository;
    }

    @Override
    public void updateQuakes() {
        view.showProgress();
        repository.checkNewEarthquakes(new IQuakesCallbackListener() {
            @Override
            public void onNetworkError(String errorMessage, int errorCode) {
                view.hideProgress();
                view.showToast(R.string.shit, errorMessage);
                view.proceedFromSplash();
            }

            @Override
            public void onNetworkSuccess(List<QuakeData> quakes) {
                view.hideProgress();
                repository.saveLastUpdate(new Date().getTime());
                repository.save(quakes);
                view.proceedFromSplash();
            }
        });
    }
}
