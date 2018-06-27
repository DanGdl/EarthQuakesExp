package android.app;

import android.content.Context;

/**
 * Created by Owner
 * on 19/03/2018.
 */

public abstract class Application extends Context {

    public Application() {
        onCreate();
    }

    public void onCreate(){

    }
}
