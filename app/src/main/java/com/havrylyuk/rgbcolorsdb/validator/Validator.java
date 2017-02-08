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

package com.havrylyuk.rgbcolorsdb.validator;

import android.content.Context;
import android.widget.Toast;

import com.havrylyuk.rgbcolorsdb.util.Utility;

/**
 *  Validate change integer values on shared preference
 * Created by Igor Havrylyuk on 31.12.2016.
 */

public class Validator {


    public  boolean validateInteger(Context context, Object newValue) {
        try {
            if (newValue != null && ((String)newValue).isEmpty()) {
                throw new NumberFormatException("The value should not be empty!");
            }
            int intValue = Integer.parseInt((String) newValue);
            if (intValue <= 0) {
                throw new NumberFormatException("The value must be greater than zero!");
            }
        } catch (NumberFormatException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return false;
        }
        return  true;
    }

    public  boolean validateListSize(Context context, Object newValue) {
        try {
            if (newValue != null && ((String)newValue).isEmpty()) {
                throw new NumberFormatException("The value should not be empty!");
            }
            int intValue = Integer.parseInt((String) newValue);
            if (intValue <= 0) {
                throw new NumberFormatException("The value must be greater than zero!");
            }
            int displayItems = Utility.getPrefShowItemsCount(context);
            if (intValue < displayItems) {
                throw new NumberFormatException("There can be less than the number of display!");
            }
        } catch (NumberFormatException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return false;
        }
        return  true;
    }

   public boolean validateItemsCount(Context context, Object newValue) {
       try {
           if (newValue != null && ((String)newValue).isEmpty()) {
               throw new NumberFormatException("The value should not be empty!");
           }
           int intValue = Integer.parseInt((String) newValue);
           if (intValue <= 0) {
               throw new NumberFormatException("The value must be greater than zero!");
           }
           int listSize = Utility.getPrefListSize(context);
           if (intValue > listSize) {
               throw new NumberFormatException("There can be more than the length of the list!");
           }
       } catch (NumberFormatException e) {
           Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
           e.printStackTrace();
           return false;
       }
       return  true;
   }

}
