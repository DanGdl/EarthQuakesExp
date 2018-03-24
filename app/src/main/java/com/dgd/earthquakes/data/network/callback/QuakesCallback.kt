package com.dgd.earthquakes.data.network.callback

import com.dgd.earthquakes.data.network.infra.QuakesResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Max
 * on 30-Apr-17.
 */

class QuakesCallback(private var mListener: IQuakesCallbackListener?) : Callback<QuakesResponse> {

    override fun onResponse(call: Call<QuakesResponse>, response: Response<QuakesResponse>) {
        val raw = response.raw()
        if (raw.isSuccessful && (raw.code() == 200 || raw.code() == 201)) { // request OK
            val body = response.body()
            if (mListener != null) {
                mListener!!.onNetworkSuccess(body!!.earthquakes)
            }
        } else {
            if (mListener != null) {
                mListener!!.onNetworkError(raw.message(), raw.code())
            }
        }
    }

    override fun onFailure(call: Call<QuakesResponse>, t: Throwable) {
        if (mListener != null) {
            mListener!!.onNetworkError(t.message!!, 400)
        }
    }

    fun setListener(listener: IQuakesCallbackListener) {
        this.mListener = listener
    }
}