package com.dgd.earthquakes.common;

import com.dgd.earthquakes.BaseApplication;
import com.dgd.earthquakes.data.IRepo;

/**
 * Created by max
 * on 04/10/17.
 */

public class Presenter<T> {

    protected final T view;

    public Presenter(T view){
        this.view = view;
    }

    protected IRepo getRepo() {
        return BaseApplication.getInstance().getRepository();
    }
}
