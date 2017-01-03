package com.havrylyuk.rgbcolors;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * RGB Application
 * Created by Igor Havrylyuk on 31.12.2016.
 */

public class RGBApplication extends Application {

    public static SharedPreferences sSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

    }

    public static SharedPreferences getSharedPreferences() {
        return sSharedPreferences;
    }
}
