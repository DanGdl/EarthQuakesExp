package com.dgd.earthquakes.ui.quakes

import com.dgd.earthquakes.R
import com.dgd.earthquakes.common.Presenter
import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener
import com.dgd.earthquakes.data.network.infra.QuakeData
import java.util.*

/**
 * Created by max
 * on 04/10/17.
 */

class QuakesListPresenter(view: QuakesScreenContract.IView) : Presenter<QuakesScreenContract.IView>(view), QuakesScreenContract.IPresenter {

    private var query: SearchDTO? = null

    override fun getEarthQuakes(searchParams: SearchDTO?) {
        this.query = searchParams
        val quakes = repo.getAllQuakes(searchParams)
        view.updateEarthQuakes(quakes)
    }

    override fun checkNewEarthQuakes() {
        view.showProgress("", R.string.wait_please)
        repo.checkNewEarthquakes(object : IQuakesCallbackListener {
            override fun onNetworkError(errorMessage: String, errorCode: Int) {
                view.hideProgress()
                view.showMessage(R.string.shit, errorMessage)
            }

            override fun onNetworkSuccess(quakes: List<QuakeData>) {
                view.hideProgress()
                val repo = repo
                repo.saveLastUpdate(Date().time)
                repo.save(quakes) // real will update view automatically
            }
        })
    }

    override fun getNextBulk(lastDate: Long) {
        if (lastDate != -1L && query?.isEmpty!!) {
            view.showProgress("", R.string.wait_please)
            val end = Date(lastDate)
            val start = Date(lastDate)
            start.date = start.date - 1
            repo.getEarthquakes(start, end, object : IQuakesCallbackListener {
                override fun onNetworkError(errorMessage: String, errorCode: Int) {
                    view.showMessage(R.string.shit, errorMessage)

                    view.hideProgress()
                }

                override fun onNetworkSuccess(quakes: List<QuakeData>) {
                    view.hideProgress()
                    repo.save(quakes)
                }
            })
        }
    }
}
