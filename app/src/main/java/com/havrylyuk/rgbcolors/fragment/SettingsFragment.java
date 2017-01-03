package com.havrylyuk.rgbcolors.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;


import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;


import com.havrylyuk.rgbcolors.R;
import com.havrylyuk.rgbcolors.RGBApplication;
import com.havrylyuk.rgbcolors.validator.Validator;


/**
 *
 * Created by Igor Havrylyuk on 31.12.2016.
 */
public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = SettingsFragment.class.getSimpleName();

    SharedPreferences sharedPreferences;
    Validator validator;

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        //add xml
        addPreferencesFromResource(R.xml.preferences);
        validator = new Validator();
        sharedPreferences = RGBApplication.getSharedPreferences();
        onSharedPreferenceChanged(sharedPreferences, getString(R.string.r_sort_key));
        onSharedPreferenceChanged(sharedPreferences, getString(R.string.g_sort_key));
        onSharedPreferenceChanged(sharedPreferences, getString(R.string.b_sort_key));

        onSharedPreferenceChanged(sharedPreferences, getString(R.string.display_count_key));
        onSharedPreferenceChanged(sharedPreferences, getString(R.string.multiple_of_key));
        onSharedPreferenceChanged(sharedPreferences, getString(R.string.list_size_key));



        findPreference(getString(R.string.list_size_key)).setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        return validator.validateListSize(getContext(), newValue);
                    }
                });
        findPreference(getString(R.string.multiple_of_key)).setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        return validator.validateInteger(getContext(), newValue);
                    }
                });
        findPreference(getString(R.string.display_count_key)).setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        return validator.validateItemsCount(getContext(), newValue);
                    }
                });
        }



    @Override
    public void onResume() {
        super.onResume();
        //unregister the preferenceChange listener
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if (preference instanceof ListPreference)
        {
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(sharedPreferences.getString(key, ""));
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
                preference.setSummary(sharedPreferences.getString(key, ""));
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        //unregister the preference change listener
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }



}
