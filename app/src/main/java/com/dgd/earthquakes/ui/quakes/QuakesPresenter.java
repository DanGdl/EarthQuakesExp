package com.dgd.earthquakes.ui.quakes;

import com.mdgd.commons.support.v7.mvp.Presenter;

/**
 * Created by max
 * on 04/10/17.
 */

public class QuakesPresenter extends Presenter<QuakesContract.IView> implements QuakesContract.IPresenter {

    public QuakesPresenter(QuakesContract.IView view) {
        super(view);
    }
}
