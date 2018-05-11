package android.content.pm;

/**
 * Created by Owner
 * on 19/03/2018.
 */

public class PackageManager {

    public static class NameNotFoundException extends Exception {
        public NameNotFoundException() {
        }

        public NameNotFoundException(String name) {
            super(name);
        }
    }
}
