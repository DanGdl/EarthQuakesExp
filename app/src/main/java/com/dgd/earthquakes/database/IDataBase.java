package com.dgd.earthquakes.database;

import com.dgd.earthquakes.models.IQuake;
import com.dgd.earthquakes.network.infra.QuakeData;

import java.util.List;

/**
 * Created by Max on 23-Jun-17.
 */

public interface IDataBase {

    void saveQuakes(List<QuakeData> quakes);

    List<IQuake> getQuakesBulk(long date);
}
