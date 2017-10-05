package com.dgd.earthquakes.common;

/**
 * Created by max on 29/09/17.
 */

public interface IFragmentHostActivity {
    void hideProgress();

    void showMessage(int strRes, String... args);
}
