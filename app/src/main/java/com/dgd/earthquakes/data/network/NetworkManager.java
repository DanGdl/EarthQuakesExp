package com.dgd.earthquakes.data.network;

import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;
import com.dgd.earthquakes.data.network.callback.QuakesCallback;
import com.dgd.earthquakes.data.network.infra.QuakesResponse;
import com.dgd.earthquakes.models.IQuake;
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
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Max on 30-Apr-17.
 */
public class NetworkManager {
    private static NetworkManager ourInstance = new NetworkManager();
    private final IQuakesAPI mRetrofitInterface;
    private final SimpleDateFormat mSDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); // 2017-05-02T10:52:57

    public static NetworkManager getInstance() {
        return ourInstance;
    }

    private NetworkManager() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mRetrofitInterface = mRetrofit.create(IQuakesAPI.class);
    }

    /**
     *
     * @param start - date in format yyyy-mm-dd
     * @param end - date in format yyyy-mm-dd
     */
    private void getEarthquakes(String start, String end, IQuakesCallbackListener listener){
        Call<QuakesResponse> call = mRetrofitInterface.getQuakes(start, end);
        QuakesCallback callback = new QuakesCallback(listener);
        call.enqueue(callback);
    }

    public void getEarthquakes(Date start, Date end, IQuakesCallbackListener listener){
        String endDate = mSDF.format(end);
        String startDate = mSDF.format(start);
        getEarthquakes(startDate, endDate, listener);
    }

    private void getEarthquakes(long startDate, IQuakesCallbackListener listener) {
        String start;
        Calendar cal = Calendar.getInstance();
        if(startDate != -1){
            cal.setTimeInMillis(startDate);
        }
        else{
            cal.add(Calendar.DATE, -1);
        }

        start = mSDF.format(cal.getTime());
        getEarthquakes(start, "NOW", listener);
    }

    public void checkNewEarthquakes(IQuakesCallbackListener listener){
        long lastUpdate = SharedPrefsManager.getInstance().getLastUpdateDate();
        getEarthquakes(lastUpdate, listener);
    }
}
