package android.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Owner
 * on 19/03/2018.
 */

public class PreferenceManager {

    public static SharedPreferences getDefaultSharedPreferences(Context context) {
        return context.getSharedPreferences(getDefaultSharedPreferencesName(context), getDefaultSharedPreferencesMode());
    }

    public static String getDefaultSharedPreferencesName(Context context) {
        return context.getPackageName() + "_preferences";
    }

    private static int getDefaultSharedPreferencesMode() {
        return Context.MODE_PRIVATE;
    }
}
