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

package com.havrylyuk.rgbcolorsdb.model;

import android.graphics.Color;



/**
 * RGBColor class
 * Created by Igor Havrylyuk on 29.12.2016.
 */

public class RGBColor extends Color   {


    private int red;
    private int green;
    private int blue;

    public RGBColor(int r, int g, int b) {
        this.red = r;
        this.green = g;
        this.blue = b;
        rgb(r, g, b);
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
