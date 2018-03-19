package com.dgd.earthquakes.common;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dgd.earthquakes.R;

/**
 * Created by Dan
 * on 25/07/2017.
 */

public abstract class FragmentHostActivity<T> extends AppCompatActivity implements IFragmentHostActivity {
    private ProgressDialog mProgressDialog;
    private boolean onForeground = false;
    protected T presenter;
    private HostedFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        presenter = setupPresenter();
        setFragment(getFirstFragment(savedInstanceState));
    }

    protected abstract T setupPresenter();

    protected int getLayoutResId(){
        return R.layout.activity_main;
    }

    protected void setFragment(HostedFragment fragment) {
        setFragment(fragment, false, null);
    }

    protected void setFragment(HostedFragment fragment, boolean addToStack, String backStackTag) {
        currentFragment = fragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment);
        if(addToStack){
            transaction.addToBackStack(backStackTag);
        }
        transaction.commit();
    }

    protected abstract HostedFragment getFirstFragment(Bundle savedInstanceState);

    public void showProgress(String title, String msg){
        if(currentFragment != null && currentFragment.hasProgress()){
            currentFragment.showProgress();
        }
        else {
            if(mProgressDialog == null){
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setTitle(title);
                mProgressDialog.setMessage(msg);
            }

            if(onForeground && !mProgressDialog.isShowing()){
                mProgressDialog.show();
            }
        }
    }

    public void hideProgress(){
        if(currentFragment != null && currentFragment.hasProgress()){
            currentFragment.hideProgress();
        }
        if(mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        onForeground = true;
    }

    @Override
    protected void onPause(){
        onForeground = false;
        super.onPause();
    }

    public void showMessage(int strRes, String... args) {
        Toast.makeText(this, getString(strRes, args), Toast.LENGTH_SHORT).show();
    }
}
