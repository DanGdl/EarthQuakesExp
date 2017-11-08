package com.dgd.earthquakes.common;

/**
 * Created by max on 04/10/17.
 */

public interface IView {

    void showProgress();

    void hideProgress();

    void showMessage(int msgRes, String... message);
}
