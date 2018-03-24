package com.dgd.earthquakes.data.network.infra

import com.dgd.earthquakes.models.Quake
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Max
 * on 01-May-17.
 */

class QuakeData {

    @Expose
    @SerializedName("id")
    var id: String? = null

    @Expose
    @SerializedName("properties")
    var properties: Properties? = null

    @Expose
    @SerializedName("geometry")
    var geometry: Geometry? = null

    fun fillQuake(q: Quake): Quake {
        q.title = properties!!.title
        q.date = Date(properties!!.time)
        q.id = id
        q.link = properties!!.url
        q.magnitude = "" + properties!!.magnitude

        val coordinates = geometry!!.coordinates
        q.latitude = coordinates!![1]
        q.longitude = coordinates[0]
        return q
    }
}
