package com.dgd.earthquakes.common

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Dan
 * on 20/08/2017.
 */

abstract class CommonViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun setItem(item: T, position: Int)
}
