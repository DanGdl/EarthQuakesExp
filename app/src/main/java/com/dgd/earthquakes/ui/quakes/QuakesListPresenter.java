package com.dgd.earthquakes.ui.quakes;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.common.Presenter;
import com.dgd.earthquakes.data.IRepo;
import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;
import com.dgd.earthquakes.data.network.infra.QuakeData;
import com.dgd.earthquakes.models.Quake;

import java.util.Date;
import java.util.List;

/**
 * Created by max
 * on 04/10/17.
 */

public class QuakesListPresenter extends Presenter<QuackesScreenContract.IQuakesListView> implements QuackesScreenContract.IQuakesListPresenter {

    private SearchDTO query;

    public QuakesListPresenter(QuackesScreenContract.IQuakesListView view) {
        super(view);
    }

    @Override
    public void getEarthQuakes(SearchDTO searchParams) {
        this.query = searchParams;
        List<Quake> quakes = getRepo().getAllQuakes(searchParams);
        view.updateEarthQuakes(quakes);
    }

    @Override
    public void checkNewEarthQuakes() {
        view.showProgress("", R.string.wait_please);
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
                repo.save(quakes); // real will update view automatically
            }
        });
    }

    @Override
    public void getNextBulk(long lastDate) {
        if(lastDate != -1 && query.isEmpty()){
            view.showProgress("", R.string.wait_please);
            Date end = new Date(lastDate);
            Date start = new Date(lastDate);
            start.setDate(start.getDate() - 1);
            getRepo().getEarthquakes(start, end, new IQuakesCallbackListener() {
                @Override
                public void onNetworkError(String errorMessage, int errorCode) {
                    view.showMessage(R.string.shit, errorMessage);

                    view.hideProgress();
                }

                @Override
                public void onNetworkSuccess(List<QuakeData> quakes) {
                    view.hideProgress();
                    getRepo().save(quakes);
                    
                }
            });
        }
    }
}
