package com.dgd.earthquakes.network.callback;

import com.dgd.earthquakes.network.infra.QuakesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Max
 * on 30-Apr-17.
 */

public class QuakesCallback implements Callback<QuakesResponse> {
    private IQuakesCallbackListener mListener;

    public QuakesCallback(IQuakesCallbackListener listener) {
        mListener = listener;
    }

    @Override
    public void onResponse(Call<QuakesResponse> call, Response<QuakesResponse> response) {
        okhttp3.Response raw = response.raw();
        if(raw.code() == 200) { // request OK
            QuakesResponse body = response.body();
            if (mListener != null) {
                mListener.onNetworkSuccess(body.getEarthquakes());
            }
        }else {
            if(mListener != null) {
                mListener.onNetworkError(raw.message(), raw.code());
            }
        }
    }

    @Override
    public void onFailure(Call<QuakesResponse> call, Throwable t) {
        if(mListener != null) {
            mListener.onNetworkError(t.getMessage(), 400);
        }
    }
}