package com.dgd.earthquakes.ui.quakes

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CompoundButton
import android.widget.DatePicker
import android.widget.TimePicker
import com.dgd.earthquakes.R
import com.dgd.earthquakes.common.CommonRecyclerAdapter
import com.dgd.earthquakes.common.RecyclerFragment
import com.dgd.earthquakes.models.Quake
import java.util.*

/**
 * Created by Max
 * on 01-May-17.
 */

class EarthQuakesFragment : RecyclerFragment<QuakesFragmentContract.IHost, Quake>(), QuakesFragmentContract.IFragment, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
        host?.getNextBulk((adapter as EarthQuakesAdapter).lastDate)
    }

    override fun createAdapter(): CommonRecyclerAdapter<Quake> {
        return EarthQuakesAdapter(activity as Context, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.toolbarInc?.searchBtn?.setOnClickListener(this)
        binding?.toolbarInc?.extensionToggle?.setOnCheckedChangeListener(this)

        binding?.searchParams?.fromTime?.setOnClickListener(this)
        binding?.searchParams?.fromDate?.setOnClickListener(this)
        binding?.searchParams?.toTime?.setOnClickListener(this)
        binding?.searchParams?.toDate?.setOnClickListener(this)

        binding?.searchParams?.root?.animate()?.translationYBy(TRANSLATE)?.setDuration(1)?.start()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        host?.getEarthQuakes(null)
    }

    override fun updateEarthQuakes(quakes: List<Quake>) {
        binding?.toolbarInc?.toolbarIcon?.requestFocus()
        adapter?.putItems(quakes)
    }

    override fun onRefresh() {
        host?.refreshEarthQuakes()
    }

    override fun onItemClicked(item: Quake, position: Int) {
        val dialog = QuakeDialog(activity as Context)
        dialog.setQuake(item)
        dialog.show()
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.searchBtn) {
//                SearchDTO searchData = SearchDTO(
//                        binding!!.toolbarInc!!.search.getText().toString(),
//                        binding!!.searchParams!!.fromTime.getText().toString(),
//                        binding!!.searchParams!!.fromDate.getText().toString(),
//                        binding!!.searchParams!!.fromMagnitude.getText().toString(),
//                        binding!!.searchParams!!.toTime.getText().toString(),
//                        binding!!.searchParams!!.toDate.getText().toString(),
//                        binding!!.searchParams!!.toMagnitude.getText().toString())
//                host.getEarthQuakes(searchData);
        } else if (id == R.id.fromTime) {
            TimePickerDialog(activity, { _: TimePicker, h: Int, m: Int -> binding?.searchParams?.fromTime?.text = String.format(Locale.getDefault(), "%1$2d : %2$2d", h, m) },
                    0, 0, true).show()
        } else if (id == R.id.toTime) {
            TimePickerDialog(activity, { _: TimePicker, h: Int, m: Int -> binding?.searchParams?.toTime?.text = String.format(Locale.getDefault(), "%1$2d : %2$2d", h, m) },
                    0, 0, true).show()
        } else if (id == R.id.fromDate) {
            val calendar = Calendar.getInstance()
            DatePickerDialog(activity, { _: DatePicker, y: Int, m: Int, d: Int -> binding?.searchParams?.fromDate?.text = String.format(Locale.getDefault(), "%1$2d.%2$2d.%3$4d", d, m, y) },
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        } else if (id == R.id.toDate) {
            val calendar = Calendar.getInstance()
            DatePickerDialog(activity, { _: DatePicker, y: Int, m: Int, d: Int -> binding?.searchParams?.toDate?.text = String.format(Locale.getDefault(), "%1$2d.%2$2d.%3$4d", d, m, y) },
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    override fun onCheckedChanged(compoundButton: CompoundButton, b: Boolean) {
        val translateDist = if (b) -1 * TRANSLATE else TRANSLATE
        binding?.searchParams?.root?.animate()?.translationYBy(translateDist)?.setDuration(300)?.start()
    }

    companion object {

        private const val TRANSLATE = -500f

        fun newInstance(): EarthQuakesFragment {
            return EarthQuakesFragment()
        }
    }
}
