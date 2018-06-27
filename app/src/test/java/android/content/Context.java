package android.content;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Owner
 * on 19/03/2018.
 */

public class Context {
    // prefs mode
    public static final int MODE_PRIVATE = 0x0000;

    private final Map<String, SharedPreferences> PREFS_MAP = new HashMap<>();
    private final Resources RES = new Resources();

    public Context(){}

    public String getString(int id){
        return RES.getString(id);
    }

    public AssetManager getAssets() {
        return null;
    }

    public Resources getResources() {
        return null;
    }

    public PackageManager getPackageManager() {
        return null;
    }

    public ContentResolver getContentResolver() {
        return null;
    }

    public Looper getMainLooper() {
        return null;
    }

    public Context getApplicationContext() {
        return null;
    }

    public void setTheme(int resid) {

    }

    public Resources.Theme getTheme() {
        return null;
    }

    public ClassLoader getClassLoader() {
        return null;
    }

    public String getPackageName() {
        return null;
    }

    public ApplicationInfo getApplicationInfo() {
        return null;
    }

    public String getPackageResourcePath() {
        return null;
    }

    public String getPackageCodePath() {
        return null;
    }

    public SharedPreferences getSharedPreferences(String name, int mode) {
        String key = name + mode;
        SharedPreferences pref = PREFS_MAP.get(key);
        if (pref == null) {
            pref = PrefsFactory.create(name, mode);
            PREFS_MAP.put(key, pref);
        }
        return PREFS_MAP.get(name + mode);
    }

    public boolean moveSharedPreferencesFrom(Context sourceContext, String name) {
        return false;
    }

    public boolean deleteSharedPreferences(String name) {
        return false;
    }

    public FileInputStream openFileInput(String name) throws FileNotFoundException {
        return null;
    }

    public FileOutputStream openFileOutput(String name, int mode) throws FileNotFoundException {
        return null;
    }

    public boolean deleteFile(String name) {
        return false;
    }

    public File getFileStreamPath(String name) {
        return null;
    }

    public File getDataDir() {
        return null;
    }

    public File getFilesDir() {
        return null;
    }

    public File getNoBackupFilesDir() {
        return null;
    }

    @Nullable
    public File getExternalFilesDir(@Nullable String type) {
        return null;
    }

    public File[] getExternalFilesDirs(String type) {
        return new File[0];
    }

    public File getObbDir() {
        return null;
    }

    public File[] getObbDirs() {
        return new File[0];
    }

    public File getCacheDir() {
        return null;
    }

    public File getCodeCacheDir() {
        return null;
    }

    @Nullable
    public File getExternalCacheDir() {
        return null;
    }

    public File[] getExternalCacheDirs() {
        return new File[0];
    }

    public File[] getExternalMediaDirs() {
        return new File[0];
    }

    public String[] fileList() {
        return new String[0];
    }

    public File getDir(String name, int mode) {
        return null;
    }

    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        return null;
    }

    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, @Nullable DatabaseErrorHandler errorHandler) {
        return null;
    }

    public boolean moveDatabaseFrom(Context sourceContext, String name) {
        return false;
    }

    public boolean deleteDatabase(String name) {
        return false;
    }

    public File getDatabasePath(String name) {
        return null;
    }

    public String[] databaseList() {
        return new String[0];
    }

    public Drawable getWallpaper() {
        return null;
    }

    public Drawable peekWallpaper() {
        return null;
    }

    public int getWallpaperDesiredMinimumWidth() {
        return 0;
    }

    public int getWallpaperDesiredMinimumHeight() {
        return 0;
    }

    public void setWallpaper(Bitmap bitmap) throws IOException {

    }

    public void setWallpaper(InputStream data) throws IOException {

    }

    public void clearWallpaper() throws IOException {

    }

    public void startActivity(Intent intent) {

    }

    public void startActivity(Intent intent, @Nullable Bundle options) {

    }

    public void startActivities(Intent[] intents) {

    }

    public void startActivities(Intent[] intents, Bundle options) {

    }

    public void startIntentSender(IntentSender intent, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws IntentSender.SendIntentException {

    }

    public void startIntentSender(IntentSender intent, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, @Nullable Bundle options) throws IntentSender.SendIntentException {

    }

    public void sendBroadcast(Intent intent) {

    }

    public void sendBroadcast(Intent intent, @Nullable String receiverPermission) {

    }

    public void sendOrderedBroadcast(Intent intent, @Nullable String receiverPermission) {

    }

    public void sendOrderedBroadcast(@NonNull Intent intent, @Nullable String receiverPermission, @Nullable BroadcastReceiver resultReceiver, @Nullable Handler scheduler, int initialCode, @Nullable String initialData, @Nullable Bundle initialExtras) {

    }

    public void sendBroadcastAsUser(Intent intent, UserHandle user) {

    }

    public void sendBroadcastAsUser(Intent intent, UserHandle user, @Nullable String receiverPermission) {

    }

    public void sendOrderedBroadcastAsUser(Intent intent, UserHandle user, @Nullable String receiverPermission, BroadcastReceiver resultReceiver, @Nullable Handler scheduler, int initialCode, @Nullable String initialData, @Nullable Bundle initialExtras) {

    }

    public void sendStickyBroadcast(Intent intent) {

    }

    public void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver resultReceiver, @Nullable Handler scheduler, int initialCode, @Nullable String initialData, @Nullable Bundle initialExtras) {

    }

    public void removeStickyBroadcast(Intent intent) {

    }

    public void sendStickyBroadcastAsUser(Intent intent, UserHandle user) {

    }

    public void sendStickyOrderedBroadcastAsUser(Intent intent, UserHandle user, BroadcastReceiver resultReceiver, @Nullable Handler scheduler, int initialCode, @Nullable String initialData, @Nullable Bundle initialExtras) {

    }

    public void removeStickyBroadcastAsUser(Intent intent, UserHandle user) {

    }

    @Nullable
    public Intent registerReceiver(@Nullable BroadcastReceiver receiver, IntentFilter filter) {
        return null;
    }

    @Nullable
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, @Nullable String broadcastPermission, @Nullable Handler scheduler) {
        return null;
    }

    public void unregisterReceiver(BroadcastReceiver receiver) {

    }

    @Nullable
    public ComponentName startService(Intent service) {
        return null;
    }

    public boolean stopService(Intent service) {
        return false;
    }

    public boolean bindService(Intent service, @NonNull ServiceConnection conn, int flags) {
        return false;
    }

    public void unbindService(@NonNull ServiceConnection conn) {

    }

    public boolean startInstrumentation(@NonNull ComponentName className, @Nullable String profileFile, @Nullable Bundle arguments) {
        return false;
    }

    @Nullable
    public Object getSystemService(@NonNull String name) {
        return null;
    }

    @Nullable
    public String getSystemServiceName(@NonNull Class<?> serviceClass) {
        return null;
    }

    public int checkPermission(@NonNull String permission, int pid, int uid) {
        return 0;
    }

    public int checkCallingPermission(@NonNull String permission) {
        return 0;
    }

    public int checkCallingOrSelfPermission(@NonNull String permission) {
        return 0;
    }

    public int checkSelfPermission(@NonNull String permission) {
        return 0;
    }

    public void enforcePermission(@NonNull String permission, int pid, int uid, @Nullable String message) {

    }

    public void enforceCallingPermission(@NonNull String permission, @Nullable String message) {

    }

    public void enforceCallingOrSelfPermission(@NonNull String permission, @Nullable String message) {

    }

    public void grantUriPermission(String toPackage, Uri uri, int modeFlags) {

    }

    public void revokeUriPermission(Uri uri, int modeFlags) {

    }

    public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags) {
        return 0;
    }

    public int checkCallingUriPermission(Uri uri, int modeFlags) {
        return 0;
    }

    public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags) {
        return 0;
    }

    public int checkUriPermission(@Nullable Uri uri, @Nullable String readPermission, @Nullable String writePermission, int pid, int uid, int modeFlags) {
        return 0;
    }

    public void enforceUriPermission(Uri uri, int pid, int uid, int modeFlags, String message) {

    }

    public void enforceCallingUriPermission(Uri uri, int modeFlags, String message) {

    }

    public void enforceCallingOrSelfUriPermission(Uri uri, int modeFlags, String message) {

    }

    public void enforceUriPermission(@Nullable Uri uri, @Nullable String readPermission, @Nullable String writePermission, int pid, int uid, int modeFlags, @Nullable String message) {

    }

    public Context createPackageContext(String packageName, int flags) throws PackageManager.NameNotFoundException {
        return this;
    }

    public Context createConfigurationContext(@NonNull Configuration overrideConfiguration) {
        return null;
    }

    public Context createDisplayContext(@NonNull Display display) {
        return null;
    }

    public Context createDeviceProtectedStorageContext() {
        return null;
    }

    public boolean isDeviceProtectedStorage() {
        return false;
    }
}
