package com.dgd.earthquakes.common;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by Dan on 19/07/2017.
 */

public abstract class HostedFragment<T> extends Fragment implements IHostedFragment {
    protected T host;
    private boolean hasProgress;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        host = (T) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        host = null;
    }

    protected void setHasProgress(boolean hasProgress) {
        this.hasProgress = hasProgress;
    }

    @Override
    public boolean hasProgress() {
        return hasProgress;
    }

    @Override
    public void showProgress() {}

    @Override
    public void hideProgress() {}
}
