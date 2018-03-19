package com.dgd.earthquakes.screens.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dgd.earthquakes.database.IDataBase;
import com.dgd.earthquakes.database.SQLiteManager;
import com.dgd.earthquakes.network.INetworkManager;
import com.dgd.earthquakes.network.NetworkManager;
import com.dgd.earthquakes.util.IPrefs;
import com.dgd.earthquakes.util.SharedPrefsManager;

/**
 * Created by Max
 * on 03-Apr-17.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public void showMessage(String msg){
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    public void showMessage(int msg){
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    protected INetworkManager getNetwork(){
        return NetworkManager.getInstance();
    }

    protected IDataBase getDB(){
        return SQLiteManager.getInstance();
    }

    protected IPrefs getPrefs(){
        return SharedPrefsManager.getInstance();
    }
}
