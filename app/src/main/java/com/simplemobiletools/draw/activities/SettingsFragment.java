package com.simplemobiletools.draw.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;

import com.simplemobiletools.draw.R;

import static android.content.ContentValues.TAG;

public class SettingsFragment extends PreferenceFragment  implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
        initSettings();
    }

    private void initSettings() {
        SharedPreferences prefs = getPreferenceScreen().getSharedPreferences();
        int currentLinesMode = Integer.parseInt(prefs.getString("grid_settings_lines", "0"));
        setLinesModeSummary(currentLinesMode);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d(TAG, "onSharedPreferenceChanged: ");
        if (key.equals("grid_settings_lines")) {
            setLinesModeSummary(Integer.parseInt(sharedPreferences.getString(key, "0")));
        }
    }

    private void setLinesModeSummary(int linesMode) {
        findPreference("grid_settings_lines").setSummary(getResources().getStringArray(R.array.grid_settings_lines_modes)[linesMode]);
    }
}
