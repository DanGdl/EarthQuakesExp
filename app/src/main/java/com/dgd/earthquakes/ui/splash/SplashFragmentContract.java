package com.dgd.earthquakes.ui.splash;

import com.mdgd.commons.contract.fragment.FragmentContract;

/**
 * Created by max
 * on 2/2/18.
 */

public class SplashFragmentContract {

    public interface IView extends FragmentContract.IView{
        void proceedFromSplash();
    }

    public interface IPresenter extends FragmentContract.IPresenter {
        void updateQuakes();
    }

    interface IFragment extends FragmentContract.IFragment {}

    interface IHost extends FragmentContract.IHost {}
}
