package com.havrylyuk.rgbcolorsdb;

import com.havrylyuk.rgbcolorsdb.model.RGBColor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class RGBComparatorTest {

    private ArrayList<RGBColor> result;

    // test colors list size of 10
    @Before
    public void setUp() throws Exception {
        result = new ArrayList<>();
        result.add(new RGBColor(0,0,0));
        result.add(new RGBColor(255,0,255));
        result.add(new RGBColor(255,0,0));
        result.add(new RGBColor(10,50,0));
        result.add(new RGBColor(10,65,10));
        result.add(new RGBColor(10,25,5));
        result.add(new RGBColor(50,0,3));
        result.add(new RGBColor(50,0,1));
        result.add(new RGBColor(55,0,2));
        result.add(new RGBColor(55,0,1));
    }


    @Test
    public void selfArrayTest()    {
        Assert.assertNotNull("List shouldn't be null", result);
        Assert.assertEquals("wrong size", 10, result.size());
        //item 0
        Assert.assertEquals("Wrong red color", 0, result.get(0).getRed());
        Assert.assertEquals("Wrong green color", 0, result.get(0).getGreen());
        Assert.assertEquals("Wrong blue color", 0, result.get(0).getBlue());
        //item 1
        Assert.assertEquals("Wrong red color", 255, result.get(1).getRed());
        Assert.assertEquals("Wrong green color", 0, result.get(1).getGreen());
        Assert.assertEquals("Wrong blue color", 255, result.get(1).getBlue());
        //item 9
        Assert.assertEquals("Wrong red color", 55, result.get(9).getRed());
        Assert.assertEquals("Wrong green color", 0, result.get(9).getGreen());
        Assert.assertEquals("Wrong blue color", 1, result.get(9).getBlue());
    }





}