package com.dgd.earthquakes.ui.splash;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.common.Presenter;
import com.dgd.earthquakes.data.IRepo;
import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;
import com.dgd.earthquakes.data.network.infra.QuakeData;

import java.util.Date;
import java.util.List;

/**
 * Created by max
 * on 04/10/17.
 */

public class SplashPresenter extends Presenter<SplashScreenContract.ISplashView>
        implements SplashScreenContract.ISplashPresenter {

    SplashPresenter(SplashScreenContract.ISplashView view) {
        super(view);
    }

    @Override
    public void updateQuakes() {
        view.showProgress();
        getRepo().checkNewEarthquakes(new IQuakesCallbackListener() {
            @Override
            public void onNetworkError(String errorMessage, int errorCode) {
                view.hideProgress();
                view.showMessage(R.string.shit, errorMessage);
                view.proceedFromSplash();
            }

            @Override
            public void onNetworkSuccess(List<QuakeData> quakes) {
                view.hideProgress();
                IRepo repo = getRepo();
                repo.saveLastUpdate(new Date().getTime());
                repo.saveToRealm(quakes);
                view.proceedFromSplash();
            }
        });
    }
}
