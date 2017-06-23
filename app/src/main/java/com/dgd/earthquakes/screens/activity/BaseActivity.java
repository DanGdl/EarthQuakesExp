package com.dgd.earthquakes.screens.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dgd.earthquakes.data.IRepo;
import com.dgd.earthquakes.data.Repo;
import com.dgd.earthquakes.data.database.IDataBase;
import com.dgd.earthquakes.data.database.SQLiteManager;
import com.dgd.earthquakes.data.network.INetworkManager;
import com.dgd.earthquakes.data.network.NetworkManager;
import com.dgd.earthquakes.util.IPrefs;
import com.dgd.earthquakes.util.SharedPrefsManager;

import io.realm.Realm;

/**
 * Created by Max on 03-Apr-17.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Realm realm;

    private void openRealm() {
        if(realm == null || realm.isClosed()) {
            realm = Realm.getInstance(this);
        }
    }

    private void closeRealm() {
        if(realm != null && !realm.isClosed()) {
            realm.close();
        }
    }

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

    protected IRepo getRepo(){
        return Repo.getInstance();
    }

    protected Realm getRealm(){
        openRealm();
        return realm;
    }

    @Override
    protected void onStop() {
        super.onStop();
        closeRealm();
    }
}
