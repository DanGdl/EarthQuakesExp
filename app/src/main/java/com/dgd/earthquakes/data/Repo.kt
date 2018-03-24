package com.dgd.earthquakes.data

import com.dgd.earthquakes.data.database.IDataBase
import com.dgd.earthquakes.data.network.INetworkManager
import com.dgd.earthquakes.data.network.callback.IQuakesCallbackListener
import com.dgd.earthquakes.data.network.infra.QuakeData
import com.dgd.earthquakes.data.prefs.IPrefs
import com.dgd.earthquakes.models.Quake
import com.dgd.earthquakes.ui.quakes.SearchDTO
import java.util.*

/**
 * Created by Max
 * on 23-Jun-17.
 */

class Repo(private val network: INetworkManager, private val dataBase: IDataBase, private val prefs: IPrefs) : IRepo {

    override fun getEarthquakes(start: Date, end: Date, listener: IQuakesCallbackListener) {
        network.getEarthquakes(start, end, listener)
    }

    override fun checkNewEarthquakes(listener: IQuakesCallbackListener) {
        network.checkNewEarthquakes(listener)
    }

    override fun getAllQuakes(searchParams: SearchDTO): List<Quake> {
//        RealmQuery<Quake> q = dataBase.where(Quake.class);
//        // todo Dan: impl search by other fields
//        if(!TextUtils.isEmpty(searchParams.query)){
//            q.contains("title", searchParams.query);
//        }
//        return q.findAllSorted("date", Sort.DESCENDING);
        return ArrayList()
    }


    override fun saveLastUpdate(time: Long) {
        prefs.saveLastUpdateDate(time)
    }

    override fun save(quakes: List<QuakeData>) {
        dataBase.saveQuakes(quakes)
    }
}
