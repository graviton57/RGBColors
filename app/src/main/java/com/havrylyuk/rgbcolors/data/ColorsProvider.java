package com.havrylyuk.rgbcolors.data;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;

import android.util.Log;

import com.havrylyuk.rgbcolors.model.RGBColor;
import com.havrylyuk.rgbcolors.util.Utility;

/**
 * Colors Provider
 * Created by Igor Havrylyuk on 3.01.2017.
 */

public class ColorsProvider {

    private static final String LOG_TAG = ColorsProvider.class.getSimpleName();
    private static final String FILENAME = "colors.json";

    private ArrayList<RGBColor> colors;
    private ColorsJSONSerializer serializer;

    private static ColorsProvider sColorsProvider;


    private ColorsProvider(Context context) {
       serializer = new ColorsJSONSerializer(context, FILENAME);
        try {
            colors = serializer.loadColors();//read colors
        } catch (Exception e) {
            colors = new ArrayList<>();
            Log.e(LOG_TAG, "Error loading colors: ", e);
        }
    }

    public static ColorsProvider getInstance(Context c) {
        if (sColorsProvider == null) {
            sColorsProvider = new ColorsProvider(c.getApplicationContext());
        }
        return sColorsProvider;
    }

    public ArrayList<RGBColor> getColors() {
        return colors;
    }

    public void clearData() {
        colors.clear();
        saveColors();
    }

    public boolean saveColors() {
        try {
            serializer.saveColors(colors);
            Log.d(LOG_TAG, "colors saved to file");
            return true;
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error saving colors: " + e);
            return false;
        }
    }

    public void populateColorsData() {
        int i = 0;
        int  multipleOf =  Utility.getPrefMultipleOf(serializer.getContext());//default 51
        int  listSize =  Utility.getPrefListSize(serializer.getContext()); // default 9
        //long lStartTime = System.nanoTime();//remove after debug
        //Log.v(LOG_TAG, "--------- Beginning generating  "+listSize+" colors  ------- ");//remove after debug
        while (i < listSize) {
            Random r = new Random();
            RGBColor color = new RGBColor(r.nextInt(256), r.nextInt(256), r.nextInt(256));
            if (color.getRGB() % multipleOf == 0) {
                colors.add(color);
                i++;
            }
        }
        //long lEndTime = System.nanoTime();// execution finished remove after debug
        //long timeElapsed = lEndTime - lStartTime;//remove after debug
        //Log.v(LOG_TAG, "-------- End generate colors duration=" + (timeElapsed / 1000000000) + " sec ------");//remove after debug
        //250 - 0 sec
        //10 000 - 6 sec
        //100 000 - 63 sec

    }
}

