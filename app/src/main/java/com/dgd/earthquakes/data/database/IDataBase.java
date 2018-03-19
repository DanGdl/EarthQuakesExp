package com.dgd.earthquakes.data.database;

import com.dgd.earthquakes.data.network.infra.QuakeData;
import com.dgd.earthquakes.models.IQuake;

import java.util.List;

/**
 * Created by Max
 * on 23-Jun-17.
 */

public interface IDataBase {

    void saveQuakes(List<QuakeData> quakes);

    List<IQuake> getQuakesBulk(long date);
}
