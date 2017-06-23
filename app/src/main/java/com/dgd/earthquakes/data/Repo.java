package com.dgd.earthquakes.data;

import com.dgd.earthquakes.data.network.NetworkManager2;
import com.dgd.earthquakes.models.IQuake;
import com.dgd.earthquakes.util.SharedPrefsManager;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Supplier;
import com.google.android.agera.Updatable;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Max on 22-Jun-17.
 */

public class Repo implements IRepo {
    private static final IRepo mInstance = new Repo();
    Executor mExecutor = Executors.newFixedThreadPool(4);
    private Repository<Result<List<IQuake>>> mEarthQuakesRepository;

    private Repo(){}

    public static IRepo getInstance() {
        return mInstance;
    }

    public Repository<Result<List<IQuake>>> getEarthQuakesRepository() {
        return mEarthQuakesRepository;
    }

    @Override
    public void checkNewEarthquakes() {
        Supplier<Result<List<IQuake>>> supplier = NetworkManager2.getInstance().checkNewEarthquakes();
        mEarthQuakesRepository = Repositories.repositoryWithInitialValue(Result.<List<IQuake>>absent())
                .observe()
                .onUpdatesPerLoop()
                .goTo(mExecutor)
                .getFrom(supplier)
                .thenTransform(result -> Result.absentIfNull(result.get()))
                .compile();
    }

    @Override
    public void subscribeToEarthquakes(Updatable updatable) {
        mEarthQuakesRepository.addUpdatable(updatable);
    }

    @Override
    public void unsubscribeFromEarthquakes(Updatable updatable) {
        mEarthQuakesRepository.removeUpdatable(updatable);
    }
}
