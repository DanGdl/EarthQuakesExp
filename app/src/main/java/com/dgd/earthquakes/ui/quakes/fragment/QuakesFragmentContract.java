package com.dgd.earthquakes.ui.quakes.fragment;

import com.dgd.earthquakes.models.Quake;
import com.mdgd.commons.contract.fragment.FragmentContract;

import java.util.List;

/**
 * Created by max
 * on 2/2/18.
 */

public class QuakesFragmentContract {

    public interface IPresenter extends FragmentContract.IPresenter {
        void getEarthQuakes(SearchDTO searchData);

        void refreshEarthQuakes();

        void getNextBulk(long lastDate);
    }

    public interface IView extends FragmentContract.IView {
        void updateEarthQuakes(List<Quake> quakes);
    }

    interface IFragment extends FragmentContract.IFragment {}

    public interface IHost extends FragmentContract.IHost {}
}
