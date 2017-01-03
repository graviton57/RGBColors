package com.havrylyuk.rgbcolors.model;

import android.graphics.Color;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * RGBColor class
 * Created by Igor Havrylyuk on 29.12.2016.
 */

public class RGBColor extends Color   {

    private static final String JSON_R = "R";
    private static final String JSON_G = "G";
    private static final String JSON_B = "B";

    private int red;
    private int green;
    private int blue;

    public RGBColor(int r, int g, int b) {
        this.red = r;
        this.green = g;
        this.blue = b;
        rgb(r, g, b);
    }
    public RGBColor(JSONObject json) throws JSONException {
        red = json.getInt(JSON_R);
        green = json.getInt(JSON_G);
        blue = json.getInt(JSON_B);

    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_R, red);
        json.put(JSON_G, green);
        json.put(JSON_B, blue);
        return json;
    }


    public int getRGB() {
        return rgb(this.red,this.green,this.blue);
    }
    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
        red(red);
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
        green(green);
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
        blue(blue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RGBColor rgbColor = (RGBColor) o;
        if (red != rgbColor.red) return false;
        if (green != rgbColor.green) return false;
        return blue == rgbColor.blue;

    }

    @Override
    public int hashCode() {
        int result = red;
        result = 31 * result + green;
        result = 31 * result + blue;
        return result;
    }

    @Override
    public String toString() {
        return "RGB(" + red +"," + green +"," + blue +')';
    }


}
