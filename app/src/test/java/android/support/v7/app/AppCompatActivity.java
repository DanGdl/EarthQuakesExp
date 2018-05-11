package android.support.v7.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by Owner
 * on 19/03/2018.
 */

public class AppCompatActivity extends Context {

    // onCreate -> fr.onAttach() -> onAttachFragment() -> fr.onCreate() -> fr.onCreateView() ->
    // fr.onViewCreated() -> fr.onActivityCreated() -> fr.onViewStateRestored() ->
    // onStart()

    private final FragmentManager FM;
    private Fragment fragment;
    private Bundle b = new Bundle();

    public AppCompatActivity() {
        FM = new FragmentManager(this);
        Bundle b = new Bundle();
        onCreate(b);
    }

    public void onCreate(Bundle savedInstanceState){}

    public void onAttachFragment (Fragment fragment){
        this.fragment = fragment;
        fragment.onCreate(b);
    }

    public void onPostCreate(){
        if(fragment != null) {
            fragment.onActivityCreated(b);
        }
    }

    public void onRestoreInstanceState (Bundle savedInstanceState){
        if(fragment != null) {
            fragment.onViewStateRestored(savedInstanceState);
        }
    }

    public void onStart() {
        if(fragment != null) {
            fragment.onStart();
        }
    }

    public void onResume() {
        if(fragment != null) {
            fragment.onStart();
        }
    }

    public void setContentView(@LayoutRes int layoutResID){

    }

    public FragmentManager getSupportFragmentManager(){
        return FM;
    }

    public Bundle getBundle(){
        return b;
    }
}
