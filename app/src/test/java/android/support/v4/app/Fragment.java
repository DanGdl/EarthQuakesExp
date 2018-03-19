package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Owner
 * on 19/03/2018.
 */

public class Fragment {

    public Fragment() {}

    public void onAttach(Context contex) {}

    public void onCreate(Bundle savedInstanceState) {
        onViewCreated(onCreateView(getLayoutInflater(), null, savedInstanceState), savedInstanceState);
    }

    private LayoutInflater getLayoutInflater() {
        return new LayoutInflater(){};
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {}

    public void onActivityCreated(Bundle savedInstanceState) {}

    public void onViewStateRestored(Bundle savedInstanceState) {}

    public void onStart() {}

    public void onResume() {}



    public void onDetach (){}
}
