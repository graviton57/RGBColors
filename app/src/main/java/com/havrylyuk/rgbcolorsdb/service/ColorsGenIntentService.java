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

package com.havrylyuk.rgbcolorsdb.service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.havrylyuk.rgbcolorsdb.data.RGBContract;
import com.havrylyuk.rgbcolorsdb.model.RGBColor;
import com.havrylyuk.rgbcolorsdb.util.Utility;

import java.util.Random;


/**
 * Simple IntentService for Generate new random colors list
 * Created by Igor Havrylyuk on 04.01.2017.
 */
public class ColorsGenIntentService extends IntentService {


    private static final String LOG_TAG = ColorsGenIntentService.class.getSimpleName();

    public ColorsGenIntentService() {
        super("ColorsGenIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            generateRandomColors();
        }
    }

    private void generateRandomColors() {
        int i = 0;
        int  multipleOf =  Utility.getPrefMultipleOf(getBaseContext());
        int  itemsCount =  Utility.getPrefShowItemsCount(getBaseContext());
        //long lStartTime = System.nanoTime();
        // Log.v(LOG_TAG, "--------- Beginning generating  "+itemsCount+" colors  ------- ");
        while (i < itemsCount) {
            Random r = new Random();
            RGBColor color = new RGBColor(r.nextInt(256), r.nextInt(256), r.nextInt(256));
            if (color.getRGB() % multipleOf == 0) {
                ContentValues cv = colorsToContentValues(color);
                getContentResolver().insert(RGBContract.ColorEntry.CONTENT_URI, cv);
                i++;
            }
        }
        // long lEndTime = System.nanoTime();// execution finished
        // long timeElapsed = lEndTime - lStartTime;
        // Log.v(LOG_TAG, "-------- End generate colors duration=" + (timeElapsed / 1000000000) + " sec ------");
    }

    private ContentValues colorsToContentValues(@NonNull RGBColor color) {
        ContentValues cv = new ContentValues();
        cv.put(RGBContract.ColorEntry.COLOR_R, color.getRed());
        cv.put(RGBContract.ColorEntry.COLOR_G, color.getGreen());
        cv.put(RGBContract.ColorEntry.COLOR_B, color.getBlue());
        return cv;
    }

}
