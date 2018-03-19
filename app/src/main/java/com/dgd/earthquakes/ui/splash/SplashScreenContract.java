package com.dgd.earthquakes.ui.splash;

import com.dgd.earthquakes.common.IView;

/**
 * Created by max
 * on 2/2/18.
 */

public class SplashScreenContract {

    public interface ISplashPresenter {
        void updateQuakes();
    }

    public interface ISplashView extends IView {
        void proceedFromSplash();
    }
}
