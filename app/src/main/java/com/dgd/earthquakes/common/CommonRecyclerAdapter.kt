package com.dgd.earthquakes.common

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

/**
 * Created by Dan
 * on 20/08/2017.
 */

abstract class CommonRecyclerAdapter<T>(protected val context: Context, protected val listener: IOnItemClickListener<T>?) : RecyclerView.Adapter<CommonViewHolder<T>>() {
    protected val items: MutableList<T> = mutableListOf()
    protected val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: CommonViewHolder<T>, position: Int) = holder.setItem(items[position], position)

    override fun getItemCount(): Int = items.size

    fun putItems(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    interface IOnItemClickListener<T> {
        fun onItemClicked(item: T, position: Int)
    }
}
