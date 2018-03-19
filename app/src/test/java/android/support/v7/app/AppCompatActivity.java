package android.support.v7.app;

import android.content.Context1;
import android.os.Bundle;
import android.support.annotation.LayoutRes;

/**
 * Created by Owner
 * on 19/03/2018.
 */

public class AppCompatActivity extends Context1 {

    public AppCompatActivity() {
        Bundle b = new Bundle();
        onCreate(b);
        onStart();
        onResume();
    }

    public void onCreate(Bundle savedInstanceState){}

    public void onStart() {}

    public void onResume() {}

    public void setContentView(@LayoutRes int layoutResID){

    }
}
