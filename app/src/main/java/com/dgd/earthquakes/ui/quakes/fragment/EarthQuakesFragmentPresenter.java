package com.dgd.earthquakes.ui.quakes.fragment;

import com.dgd.earthquakes.R;
import com.dgd.earthquakes.data.IRepo;
import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;
import com.dgd.earthquakes.data.network.infra.QuakeData;
import com.dgd.earthquakes.models.Quake;
import com.mdgd.commons.support.v7.fragment.FragmemtPresenter;

import java.util.Date;
import java.util.List;

/**
 * Created by Owner
 * on 27/06/2018.
 */
public class EarthQuakesFragmentPresenter extends FragmemtPresenter<QuakesFragmentContract.IView> implements QuakesFragmentContract.IPresenter {

    private final IRepo repository;
    private SearchDTO query;

    public EarthQuakesFragmentPresenter(QuakesFragmentContract.IView view, IRepo repository) {
        super(view);
        this.repository = repository;
    }

    @Override
    public void getEarthQuakes(SearchDTO searchData) {
        this.query = searchData;
        List<Quake> quakes = repository.getAllQuakes(searchData);
        view.updateEarthQuakes(quakes);
    }

    @Override
    public void refreshEarthQuakes() {
        view.showProgress();
        repository.checkNewEarthquakes(new IQuakesCallbackListener() {
            @Override
            public void onNetworkError(String errorMessage, int errorCode) {
                view.hideProgress();
                view.showToast(R.string.shit, errorMessage);
            }

            @Override
            public void onNetworkSuccess(List<QuakeData> quakes) {
                view.hideProgress();
                repository.saveLastUpdate(new Date().getTime());
                repository.save(quakes); // real will update view automatically todo sure?
            }
        });
    }

    @Override
    public void getNextBulk(long lastDate) {
        if(lastDate != -1 && query.isEmpty()){
            view.showProgress();
            Date end = new Date(lastDate);
            Date start = new Date(lastDate);
            start.setDate(start.getDate() - 1);
            repository.getEarthquakes(start, end, new IQuakesCallbackListener() {
                @Override
                public void onNetworkError(String errorMessage, int errorCode) {
                    view.showToast(R.string.shit, errorMessage);

                    view.hideProgress();
                }

                @Override
                public void onNetworkSuccess(List<QuakeData> quakes) {
                    view.hideProgress();
                    repository.save(quakes);
                }
            });
        }
    }
}
