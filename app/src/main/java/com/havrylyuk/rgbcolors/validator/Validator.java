package com.havrylyuk.rgbcolors.validator;

import android.content.Context;
import android.widget.Toast;

import com.havrylyuk.rgbcolors.util.Utility;

import static java.security.AccessController.getContext;

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
