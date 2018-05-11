package com.dgd.earthquakes.data.network.infra

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Max
 * on 30-Apr-17.
 */

class QuakesResponse {

    @Expose
    @SerializedName("features")
    private var mQuakes: List<QuakeData>? = null

    val earthquakes: List<QuakeData>
        get() = if (mQuakes == null) {
            ArrayList()
        } else mQuakes!!

    fun setQuakes(mQuakes: List<QuakeData>) {
        this.mQuakes = mQuakes
    }
}
