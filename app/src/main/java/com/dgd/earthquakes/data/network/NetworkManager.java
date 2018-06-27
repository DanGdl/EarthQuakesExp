package com.dgd.earthquakes.data.network;

import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener;
import com.dgd.earthquakes.data.network.callback.QuakesCallback;
import com.dgd.earthquakes.data.network.infra.QuakesResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Max
 * on 30-Apr-17.
 */
public class NetworkManager implements INetworkManager {
    private final IQuakesAPI mRetrofitInterface;
    private final SimpleDateFormat mSDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()); // 2017-05-02T10:52:57

    public NetworkManager() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor interceptor = (Interceptor.Chain chain) -> {
            Request original = chain.request();
            Request.Builder rBuilder = original.newBuilder()
                    .header("Accept", "application/json")
                    .method(original.method(), original.body());
            return chain.proceed(rBuilder.build());
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(loggingInterceptor)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();

        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
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

        start = mSDF.format(cal.getTime());
        getEarthquakes(start, "NOW", listener);
    }

    @Override
    public void checkNewEarthquakes(long lastUpdateDate, IQuakesCallbackListener listener){
        getEarthquakes(lastUpdateDate, listener);
    }
}
