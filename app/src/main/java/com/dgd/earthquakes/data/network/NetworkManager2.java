package com.dgd.earthquakes.data.network;

import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;
import com.dgd.earthquakes.data.network.infra.QuakesResponse;
import com.dgd.earthquakes.models.IQuake;
import com.dgd.earthquakes.models.Quake;
import com.dgd.earthquakes.util.SharedPrefsManager;
import com.google.android.agera.Result;
import com.google.android.agera.Supplier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import me.drakeet.retrofit2.adapter.agera.AgeraCallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Max on 22-Jun-17.
 */

public class NetworkManager2 {
    private static NetworkManager2 ourInstance = new NetworkManager2();
    private final IQuakesAPI_RX mRetrofitInterface;
    private final SimpleDateFormat mSDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); // 2017-05-02T10:52:57

    public static NetworkManager2 getInstance() {
        return ourInstance;
    }

    private NetworkManager2() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(AgeraCallAdapterFactory.create())
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mRetrofitInterface = mRetrofit.create(IQuakesAPI_RX.class);
    }

    /**
     *
     * @param start - date in format yyyy-mm-dd
     * @param end - date in format yyyy-mm-dd
     */
    private Supplier<Result<List<Quake>>> getEarthquakes(String start, String end){
        return mRetrofitInterface.getQuakes(start, end);
    }

    public Supplier<Result<List<Quake>>> getEarthquakes(Date start, Date end){
        String endDate = mSDF.format(end);
        String startDate = mSDF.format(start);
        return getEarthquakes(startDate, endDate);
    }

    private Supplier<Result<List<Quake>>> getEarthquakes(long startDate) {
        String start;
        Calendar cal = Calendar.getInstance();
        if(startDate != -1){
            cal.setTimeInMillis(startDate);
        }
        else{
            cal.add(Calendar.DATE, -1);
        }

        start = mSDF.format(cal.getTime());
        return getEarthquakes(start, "NOW");
    }

    public Supplier<Result<List<Quake>>> checkNewEarthquakes(){
        long lastUpdate = SharedPrefsManager.getInstance().getLastUpdateDate();
        return getEarthquakes(lastUpdate);
    }
}
