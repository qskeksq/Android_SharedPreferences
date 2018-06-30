package database.kotlin.flow9.net.spbasic.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import database.kotlin.flow9.net.spbasic.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    private SharedPreferences mPrefs;

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.settings);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mPrefs.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if(key.equals("check_box_preference_1")) {

                }
            }
        });
    }



}
