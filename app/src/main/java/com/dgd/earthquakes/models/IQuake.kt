package com.dgd.earthquakes.models

import java.util.*

/**
 * Created by Max
 * on 22-Jun-17.
 */

interface IQuake {

    var date: Date?

    var latitude: Double?

    var longitude: Double?

    var magnitude: String?

    var link: String?

    var id: String?

    var title: String?
}
