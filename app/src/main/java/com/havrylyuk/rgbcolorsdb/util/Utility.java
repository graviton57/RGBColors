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

package com.havrylyuk.rgbcolorsdb.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import com.havrylyuk.rgbcolorsdb.R;
import com.havrylyuk.rgbcolorsdb.RGBApplication;
import com.havrylyuk.rgbcolorsdb.model.SortingOrder;

/**
 *  Utility class
 * Created by Igor Havrylyuk on 30.12.2016.
 */


public class Utility {


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

    public static String buildSortOrderString(Context context) {
        StringBuilder result = new StringBuilder();
        SortingOrder rSortType =  getPrefSortType(context, R.string.r_sort_key);
        SortingOrder gSortType =  getPrefSortType(context, R.string.g_sort_key);
        SortingOrder bSortType =  getPrefSortType(context, R.string.b_sort_key);
        if (rSortType != SortingOrder.SHUFFLE) {
            result.append(" R ").append(getOrderForBuilder(rSortType)).append(", ");
        }
        if (gSortType != SortingOrder.SHUFFLE) {
            result.append(" G ").append(getOrderForBuilder(gSortType)).append(", ");
        }
        if (bSortType != SortingOrder.SHUFFLE) {
            result.append(" B ").append(getOrderForBuilder(bSortType));
            if (gSortType == SortingOrder.SHUFFLE || rSortType == SortingOrder.SHUFFLE) {
                result.append(", ");
            }
        }
        if (rSortType == SortingOrder.SHUFFLE || gSortType == SortingOrder.SHUFFLE || bSortType == SortingOrder.SHUFFLE) {
            result.append(" RANDOM()");
        }
        result.append(" LIMIT ").append(getPrefShowItemsCount(context));
        return result.toString();
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

    private static String getOrderForBuilder(SortingOrder sortingOrder) {
        return (sortingOrder == SortingOrder.ASCENDING) ? " ASC" : " DESC";
    }

    private static SortingOrder getPrefSortType(Context context, int colorKey) {
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



}

