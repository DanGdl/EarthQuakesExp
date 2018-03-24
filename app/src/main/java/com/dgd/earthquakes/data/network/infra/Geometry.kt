package com.dgd.earthquakes.data.network.infra

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Max
 * on 01-May-17.
 */

class Geometry {
    // first - lng, second - lat
    @Expose
    @SerializedName("coordinates")
    var coordinates: List<Double>? = null
}
