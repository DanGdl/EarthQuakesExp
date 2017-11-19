package com.dgd.earthquakes.ui.quakes;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.common.Presenter;
import com.dgd.earthquakes.data.IRepo;
import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;
import com.dgd.earthquakes.data.network.infra.QuakeData;
import com.dgd.earthquakes.models.Quake;

import java.util.Date;
import java.util.List;

import io.realm.RealmResults;

/**
 * Created by max on 04/10/17.
 */

class QuakesListPresenter extends Presenter<IQuakesListView> implements IQuakesListPresenter {

    private String query;

    QuakesListPresenter(IQuakesListView view) {
        super(view);
    }

    @Override
    public void getEarthQuakes(String query) {
        this.query = query;
        RealmResults<Quake> quakes = getRepo().getAllQuakes(query);
        view.updateEarthQuakes(quakes);
    }

    @Override
    public void checkNewEarthQuakes() {
        view.showProgress();
        getRepo().checkNewEarthquakes(new IQuakesCallbackListener() {
            @Override
            public void onNetworkError(String errorMessage, int errorCode) {
                view.hideProgress();
                view.showMessage(R.string.shit, errorMessage);
            }

            @Override
            public void onNetworkSuccess(List<QuakeData> quakes) {
                view.hideProgress();
                IRepo repo = getRepo();
                repo.saveLastUpdate(new Date().getTime());
                repo.saveToRealm(quakes); // real will update view automatically
            }
        });
    }

    @Override
    public void getNextBulk(long lastDate) {
        if(lastDate != -1 && (query == null || query.isEmpty())){
            view.showProgress();
            Date end = new Date(lastDate);
            Date start = new Date(lastDate);
            start.setDate(start.getDate() - 1);
            getRepo().getEarthquakes(start, end, new IQuakesCallbackListener() {
                @Override
                public void onNetworkError(String errorMessage, int errorCode) {
                    view.hideProgress();
                    view.showMessage(R.string.shit, errorMessage);
                }

                @Override
                public void onNetworkSuccess(List<QuakeData> quakes) {
                    view.hideProgress();
                    getRepo().saveToRealm(quakes);
                }
            });
        }
    }
}
