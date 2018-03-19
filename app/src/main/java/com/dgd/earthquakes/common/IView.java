package com.dgd.earthquakes.common;

/**
 * Created by max
 * on 04/10/17.
 */

public interface IView {

    void showProgress(String title, int msgResId);

    void hideProgress();

    void showMessage(int msgRes, String... message);
}
