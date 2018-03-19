package android.app;

/**
 * Created by Owner
 * on 19/03/2018.
 */

public abstract class Application {

    public Application() {
        onCreate();
    }

    public abstract void onCreate();
}
