package com.havrylyuk.rgbcolors.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.havrylyuk.rgbcolors.data.ColorsProvider;
import com.havrylyuk.rgbcolors.model.RGBColor;

import java.util.ArrayList;

/**
 * Custom AsyncTaskLoader
 * Created by Igor Havrylyuk on 01.01.2017.
 */

public class ColorsLoader extends AsyncTaskLoader<ArrayList<RGBColor>> {


    private Context context;
    private ArrayList<RGBColor> colors;

    public ColorsLoader(Context context) {
        super(context);
        this.context = context;

    }

    @Override
    public ArrayList<RGBColor> loadInBackground() {
        //loading data here
        colors = ColorsProvider.getInstance(context).getColors();
        if (colors == null || colors.isEmpty()) { //if no data(first run or clear exists)
            ColorsProvider.getInstance(context).populateColorsData();// generate now
            colors = ColorsProvider.getInstance(context).getColors(); // get colors
            ColorsProvider.getInstance(context).saveColors();// and save to JSON file
        }
        return  colors;
    }

    @Override
    protected void onStartLoading() {
        if (colors != null) {
            deliverResult(colors);
        } else {
            forceLoad();
        }
    }

    @Override
    protected void onReset() {
        colors = null;
    }

}