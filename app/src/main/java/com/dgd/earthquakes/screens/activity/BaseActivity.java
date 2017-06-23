package com.dgd.earthquakes.screens.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dgd.earthquakes.data.IRepo;
import com.dgd.earthquakes.data.Repo;

/**
 * Created by Max on 03-Apr-17.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public void showMessage(String msg){
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    public void showMessage(int msg){
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    protected IRepo getRepository(){
        return Repo.getInstance();
    }
}
