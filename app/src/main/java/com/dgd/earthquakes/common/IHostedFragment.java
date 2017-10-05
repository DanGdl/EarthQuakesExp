package com.dgd.earthquakes.common;

/**
 * Created by max on 29/09/17.
 */

public interface IHostedFragment {
    boolean hasProgress();

    void showProgress();

    void hideProgress();
}
