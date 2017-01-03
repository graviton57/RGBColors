package com.havrylyuk.rgbcolors;

import com.havrylyuk.rgbcolors.comparator.RGBComparator;
import com.havrylyuk.rgbcolors.model.RGBColor;
import com.havrylyuk.rgbcolors.model.SortingOrder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Collections;


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



    @Test
    public void sortRedAscTest()    {
        ArrayList<RGBColor> sortedList = result;
        Collections.sort(sortedList, new  RGBComparator(SortingOrder.ASCENDING, SortingOrder.ASCENDING, SortingOrder.ASCENDING));
        Assert.assertNotNull("List shouldn't be null", sortedList);
        Assert.assertEquals("wrong size", 10, sortedList.size());
        Assert.assertEquals("Wrong red color", 0, sortedList.get(0).getRed());
        Assert.assertEquals("Wrong red color", 10, sortedList.get(1).getRed());
        Assert.assertEquals("Wrong red color", 10, sortedList.get(2).getRed());
        Assert.assertEquals("Wrong red color", 10, sortedList.get(3).getRed());
        Assert.assertEquals("Wrong red color", 50, sortedList.get(4).getRed());
        Assert.assertEquals("Wrong red color", 50, sortedList.get(5).getRed());
        Assert.assertEquals("Wrong red color", 55, sortedList.get(6).getRed());
        Assert.assertEquals("Wrong red color", 55, sortedList.get(7).getRed());
        Assert.assertEquals("Wrong red color", 255, sortedList.get(8).getRed());
        Assert.assertEquals("Wrong red color", 255, sortedList.get(9).getRed());
    }
    @Test
    public void sortRedGreenBlueAscTest()    {
        ArrayList<RGBColor> sortedList = result;
        Collections.sort(sortedList, new  RGBComparator(SortingOrder.ASCENDING, SortingOrder.ASCENDING, SortingOrder.ASCENDING));
        Assert.assertNotNull("List shouldn't be null", sortedList);
        Assert.assertEquals("wrong size", 10, sortedList.size());
        Assert.assertEquals("Wrong red color", 0, sortedList.get(0).getRed());
        Assert.assertEquals("Wrong green color", 0, sortedList.get(0).getGreen());
        Assert.assertEquals("Wrong blue color", 0, sortedList.get(0).getBlue());

        Assert.assertEquals("Wrong red color", 10, sortedList.get(1).getRed());
        Assert.assertEquals("Wrong green color", 25, sortedList.get(1).getGreen());
        Assert.assertEquals("Wrong blue color", 5, sortedList.get(1).getBlue());

        Assert.assertEquals("Wrong red color", 10, sortedList.get(2).getRed());
        Assert.assertEquals("Wrong green color", 50, sortedList.get(2).getGreen());
        Assert.assertEquals("Wrong blue color", 0, sortedList.get(2).getBlue());

        Assert.assertEquals("Wrong red color", 10, sortedList.get(3).getRed());
        Assert.assertEquals("Wrong green color", 65, sortedList.get(3).getGreen());
        Assert.assertEquals("Wrong blue color", 10, sortedList.get(3).getBlue());

        Assert.assertEquals("Wrong red color", 50, sortedList.get(4).getRed());
        Assert.assertEquals("Wrong green color", 0, sortedList.get(4).getGreen());
        Assert.assertEquals("Wrong blue color", 1, sortedList.get(4).getBlue());

        Assert.assertEquals("Wrong red color", 50, sortedList.get(5).getRed());
        Assert.assertEquals("Wrong green color", 0, sortedList.get(5).getGreen());
        Assert.assertEquals("Wrong blue color", 3, sortedList.get(5).getBlue());

        Assert.assertEquals("Wrong red color", 55, sortedList.get(6).getRed());
        Assert.assertEquals("Wrong green color", 0, sortedList.get(6).getGreen());
        Assert.assertEquals("Wrong blue color", 1, sortedList.get(6).getBlue());

        Assert.assertEquals("Wrong red color", 55, sortedList.get(7).getRed());
        Assert.assertEquals("Wrong green color", 0, sortedList.get(7).getGreen());
        Assert.assertEquals("Wrong blue color", 2, sortedList.get(7).getBlue());

        Assert.assertEquals("Wrong red color", 255, sortedList.get(8).getRed());
        Assert.assertEquals("Wrong green color", 0, sortedList.get(8).getGreen());
        Assert.assertEquals("Wrong blue color", 0, sortedList.get(8).getBlue());

        Assert.assertEquals("Wrong red color", 255, sortedList.get(9).getRed());
        Assert.assertEquals("Wrong green color", 0, sortedList.get(9).getGreen());
        Assert.assertEquals("Wrong blue color", 255, sortedList.get(9).getBlue());
    }

    @Test
    public void sortRedDescTest()    {
        ArrayList<RGBColor> sortedList = result;
        Collections.sort(sortedList, new  RGBComparator(SortingOrder.DESCENDING, SortingOrder.SHUFFLE, SortingOrder.ASCENDING));
        Assert.assertNotNull("List shouldn't be null", sortedList);
        Assert.assertEquals("wrong size", 10, sortedList.size());
        Assert.assertEquals("Wrong red color", 255, sortedList.get(0).getRed());
        Assert.assertEquals("Wrong red color", 255, sortedList.get(1).getRed());
        Assert.assertEquals("Wrong red color", 55, sortedList.get(2).getRed());
        Assert.assertEquals("Wrong red color", 55, sortedList.get(3).getRed());
        Assert.assertEquals("Wrong red color", 50, sortedList.get(4).getRed());
        Assert.assertEquals("Wrong red color", 50, sortedList.get(5).getRed());
        Assert.assertEquals("Wrong red color", 10, sortedList.get(6).getRed());
        Assert.assertEquals("Wrong red color", 10, sortedList.get(7).getRed());
        Assert.assertEquals("Wrong red color", 10, sortedList.get(8).getRed());
        Assert.assertEquals("Wrong red color", 0, sortedList.get(9).getRed());
    }

}