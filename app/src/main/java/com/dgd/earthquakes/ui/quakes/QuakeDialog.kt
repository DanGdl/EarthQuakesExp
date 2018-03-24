package com.dgd.earthquakes.ui.quakes

import android.app.Dialog
import android.content.Context
import android.widget.TextView
import com.dgd.earthquakes.R
import com.dgd.earthquakes.models.IQuake
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Max
 * on 02-May-17.
 */

class QuakeDialog internal constructor(context: Context) : Dialog(context) {
    private var mDetails: TextView? = null

    init {
        init()
    }

    private fun init() {
        setContentView(R.layout.dialog_quake)

        setTitle(R.string.quake_time)
        mDetails = findViewById(R.id.quakeDetails) as TextView
        setCanceledOnTouchOutside(true)
    }

    fun setQuake(quake: IQuake) {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val dateString = sdf.format(quake.date)

        setTitle(dateString)
        mDetails?.text = context.resources.getString(R.string.quake_details, quake.magnitude, quake.title, quake.link)
    }
}
