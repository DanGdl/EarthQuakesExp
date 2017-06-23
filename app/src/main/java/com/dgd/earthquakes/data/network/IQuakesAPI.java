package com.dgd.earthquakes.data.network;

import com.dgd.earthquakes.data.network.callback.QuakesCallback;
import com.dgd.earthquakes.data.network.infra.QuakesResponse;
import com.dgd.earthquakes.models.IQuake;
import com.google.android.agera.Result;
import com.google.android.agera.Supplier;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Max on 30-Apr-17.
 */

public interface IQuakesAPI {

    // https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2015-05-01&endtime=2015-05-02
    @GET("query?format=geojson&")
    Call<QuakesResponse> getQuakes(@Query("starttime") String start, @Query("endtime") String end);
}
