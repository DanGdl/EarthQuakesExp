package com.dgd.earthquakes.models

import java.util.*

class Quake : IQuake {

    override var id: String? = null
    override var date: Date? = null
    override var latitude: Double? = 0.toDouble()
    override var longitude: Double? = 0.toDouble()
    override var magnitude: String? = null
        set(mMagnitude) {
            field = mMagnitude
            if (field != null && field!!.length < 4) {
                field += "0"
            }
        }
    override var link: String? = null
    override var title: String? = null
}
