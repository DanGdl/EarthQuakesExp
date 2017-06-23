package com.dgd.earthquakes.screens.fragments;

import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by Max on 01-May-17.
 */

public abstract class BaseFragment extends Fragment {

    public void showMessage(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    public void showMessage(int msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }
}
