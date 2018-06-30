package database.kotlin.flow9.net.spbasic.settings;

import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import database.kotlin.flow9.net.spbasic.R;


public class KakaoSettings extends PreferenceFragmentCompat {

    private ListPreference soundPreference;
    private ListPreference keywordSoundPreference;
    private PreferenceScreen keywordScreen;

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.kakao_settings);
        soundPreference = (ListPreference) findPreference("sound");

    }
}
