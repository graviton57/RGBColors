/*
 * Copyright (c)  2017. Igor Gavriluyk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.havrylyuk.rgbcolorsdb.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.util.Log;

import com.havrylyuk.rgbcolorsdb.R;
import com.havrylyuk.rgbcolorsdb.RGBApplication;
import com.havrylyuk.rgbcolorsdb.validator.Validator;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;


/**
 *
 * Created by Igor Havrylyuk on 31.12.2016.
 */
public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = SettingsFragment.class.getSimpleName();

    SharedPreferences sharedPreferences;
    Validator validator;


    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
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
