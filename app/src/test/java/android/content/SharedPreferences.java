package android.content;

import android.support.annotation.Nullable;

import java.util.Map;
import java.util.Set;

/**
 * Created by Owner
 * on 19/03/2018.
 */

public interface SharedPreferences {

    interface OnSharedPreferenceChangeListener {

        void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key);
    }

    interface Editor {

        Editor putString(String key, @Nullable String value);

        Editor putStringSet(String key, @Nullable Set<String> values);

        Editor putInt(String key, int value);

        Editor putLong(String key, long value);

        Editor putFloat(String key, float value);

        Editor putBoolean(String key, boolean value);

        Editor remove(String key);

        Editor clear();

        boolean commit();

        void apply();
    }

    Map<String, ?> getAll();

    @Nullable
    String getString(String key, @Nullable String defValue);

    @Nullable
    Set<String> getStringSet(String key, @Nullable Set<String> defValues);

    int getInt(String key, int defValue);

    long getLong(String key, long defValue);

    float getFloat(String key, float defValue);

    boolean getBoolean(String key, boolean defValue);

    boolean contains(String key);

    Editor edit();

    void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener);

    void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener);
}
