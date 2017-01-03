package com.havrylyuk.rgbcolors.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import com.havrylyuk.rgbcolors.R;
import com.havrylyuk.rgbcolors.RGBApplication;
import com.havrylyuk.rgbcolors.model.SortingOrder;

/**
 *  Utility class
 * Created by Igor Havrylyuk on 30.12.2016.
 */


public class Utility {

    public static SortingOrder getPrefSortType(Context context, int colorKey) {
        SharedPreferences sharedPreferences = RGBApplication.getSharedPreferences();
        String sortType;
        if (colorKey == R.string.r_sort_key) {
            sortType = sharedPreferences.getString(context.getString(R.string.r_sort_key), context.getString(R.string.sort_mode_value_asc));
        } else  if (colorKey == R.string.g_sort_key) {
            sortType = sharedPreferences.getString(context.getString(R.string.g_sort_key), context.getString(R.string.sort_mode_value_shuffle));
        } else sortType = sharedPreferences.getString(context.getString(R.string.b_sort_key), context.getString(R.string.sort_mode_value_desc));

        if (sortType.equals(context.getString(R.string.sort_mode_value_asc))) {
            return SortingOrder.ASCENDING;
        } else  if (sortType.equals(context.getString(R.string.sort_mode_value_desc))) {
            return SortingOrder.DESCENDING;
        } else return SortingOrder.SHUFFLE;


    }

    public static int getPrefMultipleOf(Context context)  {
        SharedPreferences sp = RGBApplication.getSharedPreferences();
        return Integer.parseInt(sp.getString(context.getString(R.string.multiple_of_key),
                context.getString(R.string.multiple_of_default)));

    }

    public static int getPrefShowItemsCount(Context context) {
        SharedPreferences sp = RGBApplication.getSharedPreferences();
        return Integer.parseInt(sp.getString(context.getString(R.string.display_count_key),
                context.getString(R.string.display_count_default)));
    }

    public static int getPrefListSize(Context context) {
        SharedPreferences sp = RGBApplication.getSharedPreferences();
        return Integer.parseInt(sp.getString(context.getString(R.string.list_size_key),
                context.getString(R.string.list_size_default)));
    }


    /**
     * Returns the complimentary (opposite) color.
     */
    public static int getComplimentColor(int color) {
        // get existing colors
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int blue = Color.blue(color);
        int green = Color.green(color);
        // find compliments
        red = (~red) & 0xff;
        blue = (~blue) & 0xff;
        green = (~green) & 0xff;
        return Color.argb(alpha, red, green, blue);
    }

}

