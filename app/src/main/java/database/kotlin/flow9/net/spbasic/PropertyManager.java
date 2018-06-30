package database.kotlin.flow9.net.spbasic;

import android.content.Context;
import android.content.SharedPreferences;
import static android.content.Context.MODE_PRIVATE;

public class PropertyManager {

    private static final String PREFS_NAME = "my_prefs";
    private static PropertyManager instance;
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    public static PropertyManager getInstance() {
        if (instance == null) {
            instance = new PropertyManager();
        }
        return instance;
    }

    private PropertyManager() {
        mPrefs = GlobalApp.getContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        mEditor = mPrefs.edit();
    }

    public void putString(Context context, String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public void putInt(Context context, String key, int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    public void putBoolean(Context context, String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public String getString(Context context, String key) {
        return mPrefs.getString(key, "");
    }

    public int getInt(Context context, String key) {
        return mPrefs.getInt(key, 0);
    }

    public boolean getBoolean(Context context, String key) {
        return mPrefs.getBoolean(key, false);
    }

    public void delete(Context context, String key) {
        mEditor.remove(key);
        mEditor.commit();
    }

}
