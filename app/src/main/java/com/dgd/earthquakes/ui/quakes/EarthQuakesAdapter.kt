package com.dgd.earthquakes.ui.quakes

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.View
import android.view.ViewGroup
import com.dgd.earthquakes.R
import com.dgd.earthquakes.common.CommonRecyclerAdapter
import com.dgd.earthquakes.common.CommonViewHolder
import com.dgd.earthquakes.databinding.QuakeDetailsBinding
import com.dgd.earthquakes.models.Quake
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Max
 * on 01-May-17.
 */
class EarthQuakesAdapter internal constructor(context: Context, listener: CommonRecyclerAdapter.IOnItemClickListener<Quake>) : CommonRecyclerAdapter<Quake>(context, listener) {

    companion object {
        private val mSDF = SimpleDateFormat("HH:mm, yyyy.MM.dd", Locale.getDefault())
    }

    val lastDate: Long
        get() = if (!items.isEmpty()) {
            items[itemCount - 1].date!!.time
        } else {
            -1
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder<Quake> {
        val cardBinding = DataBindingUtil.inflate<QuakeDetailsBinding>(inflater, R.layout.quake_details, parent, false)
        return QuakeVH(cardBinding)
    }

    private inner class QuakeVH internal constructor(internal val mBinding: QuakeDetailsBinding) : CommonViewHolder<Quake>(mBinding.root), View.OnClickListener {

        init {
            mBinding.root.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (listener != null) {
                val position = adapterPosition
                listener.onItemClicked(items[position], position)
            }
        }

        override fun setItem(item: Quake, position: Int) {
            mBinding.quakeTime.text = mSDF.format(item.date)
            mBinding.quake = item
        }
    }

}
