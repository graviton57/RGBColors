package com.havrylyuk.rgbcolors.comparator;

import com.havrylyuk.rgbcolors.model.RGBColor;
import com.havrylyuk.rgbcolors.model.SortingOrder;

import java.util.Comparator;

/**
 * RGB Comparator
 * Created by Igor Havrylyuk on 28.12.2016.
 */

public class RGBComparator implements Comparator<RGBColor> {

    private SortingOrder redSortOrder;
    private SortingOrder greenSortOrder;
    private SortingOrder blueSortOrder;


    public RGBComparator(SortingOrder redSortOrder, SortingOrder greenSortOrder, SortingOrder blueSortOrder) {
        this.redSortOrder = redSortOrder;
        this.greenSortOrder = greenSortOrder;
        this.blueSortOrder = blueSortOrder;
    }

    @Override
    public int compare(RGBColor c1, RGBColor c2) {
        int result ;
            result = Integer.compare(c1.getRed(), c2.getRed());
            if (result != 0) return (getOrderCoefficient(redSortOrder) * result);
            result = Integer.compare(c1.getGreen(), c2.getGreen());
            if (result != 0) return (getOrderCoefficient(greenSortOrder) * result);
            result = Integer.compare(c1.getBlue(), c2.getBlue());
            if (result != 0) return (getOrderCoefficient(blueSortOrder) * result);

        return result;

    }

    private int getOrderCoefficient(SortingOrder sortingOrder) {
        if (sortingOrder == SortingOrder.ASCENDING) {
            return 1;
        } else if (sortingOrder == SortingOrder.DESCENDING) {
            return -1;
        } else return Math.random() < 0.5 ? 1 : -1; //SHUFFLE
    }

}
