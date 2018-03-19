package com.dgd.earthquakes.data.network;

import com.dgd.earthquakes.data.network.infra.QuakesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Max
 * on 30-Apr-17.
 */

public interface IQuakesAPI {

    // https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2018-03-01&endtime=2018-03-02
    @GET("query?format=geojson&")
    Call<QuakesResponse> getQuakes(@Query("starttime") String start, @Query("endtime") String end);
}
