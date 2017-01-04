package com.havrylyuk.rgbcolors.fragment;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.havrylyuk.rgbcolors.R;
import com.havrylyuk.rgbcolors.RGBApplication;
import com.havrylyuk.rgbcolors.comparator.RGBComparator;
import com.havrylyuk.rgbcolors.data.ColorsProvider;
import com.havrylyuk.rgbcolors.model.RGBColor;
import com.havrylyuk.rgbcolors.adapter.ColorsAdapter;
import com.havrylyuk.rgbcolors.loader.ColorsLoader;
import com.havrylyuk.rgbcolors.model.SortingOrder;
import com.havrylyuk.rgbcolors.util.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;



/**
 * Created by Igor Havrylyuk on 28.12.2016.
 * A placeholder fragment containing a simple view.
 */
public class ColorsFragment extends Fragment
            implements LoaderManager.LoaderCallbacks<ArrayList<RGBColor>> ,
                       SharedPreferences.OnSharedPreferenceChangeListener{

    private static final String LOG_TAG = ColorsFragment.class.getSimpleName();
    private final static int COLORS_LOADER = 1001;

    @Bind(R.id.rvColors)
    RecyclerView rvColors;

    @Bind(R.id.pbWait)
    ProgressBar pbWait;

    private ColorsAdapter colorsAdapter;
    private ArrayList<RGBColor> colorsList;
    private ArrayList<RGBColor> displayedList;

    public ColorsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        RGBApplication.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        colorsList = new ArrayList<>();
        getActivity().getSupportLoaderManager().initLoader(COLORS_LOADER, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_colors, container, false);
        ButterKnife.bind(this, rootView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvColors.setLayoutManager(layoutManager);
        colorsAdapter = new ColorsAdapter(getActivity(), displayedList);
        rvColors.setAdapter(colorsAdapter);
        if (savedInstanceState != null) pbWait.setVisibility(View.GONE);
        return rootView;
    }

    @Override
    public void onDestroy() {
        RGBApplication.getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();

    }

    public void updateColorsList() {
        ColorsProvider.getInstance(getActivity()).clearData();
        if (colorsList != null) colorsList.clear();
        if (pbWait != null) pbWait.setVisibility(View.VISIBLE);
        getActivity().getSupportLoaderManager().restartLoader(COLORS_LOADER, null, this);
        if (colorsAdapter != null) colorsAdapter.notifyDataSetChanged();

    }


    private void sortColorsList(List list) {
        if (list != null && !list.isEmpty()) {
            SortingOrder rSortType =  Utility.getPrefSortType(getActivity(), R.string.r_sort_key);
            SortingOrder gSortType =  Utility.getPrefSortType(getActivity(), R.string.g_sort_key);
            SortingOrder bSortType =  Utility.getPrefSortType(getActivity(), R.string.b_sort_key);
            Collections.sort(list, new RGBComparator(rSortType, gSortType, bSortType));
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals(getString(R.string.multiple_of_key))
                ||key.equals(getString(R.string.list_size_key))) {
            ColorsProvider.getInstance(getActivity()).clearData();
            getActivity().getSupportLoaderManager().restartLoader(COLORS_LOADER, null, this);
            if (colorsAdapter != null) colorsAdapter.notifyDataSetChanged();
        }
        if (key.equals(getString(R.string.r_sort_key))||key.equals(getString(R.string.g_sort_key))
                ||key.equals(getString(R.string.b_sort_key)) ||key.equals(getString(R.string.display_count_key))) {
            sortColorsList(colorsList);
            if (!colorsList.isEmpty()) {
                int showItems = Utility.getPrefShowItemsCount(getContext());
                if (showItems <= colorsList.size())
                    displayedList = new ArrayList<>(colorsList.subList(0, showItems));
            }
            colorsAdapter.setData(displayedList);

        }

    }

    @Override
    public Loader<ArrayList<RGBColor>> onCreateLoader(int id, Bundle args) {
        if (id == COLORS_LOADER) {
            if (colorsList != null) colorsList.clear();
            ColorsLoader colorsLoader = new ColorsLoader(getActivity());
            if (pbWait != null) pbWait.setVisibility(View.VISIBLE);
            return colorsLoader;
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<RGBColor>> loader, ArrayList<RGBColor> data) {
        int id = loader.getId();
        if (id == COLORS_LOADER) {
            colorsList = data;
            if (pbWait != null) pbWait.setVisibility(View.GONE);
            //get 9 first items
            sortColorsList(colorsList);
            if (!colorsList.isEmpty()) {
                int showItems = Utility.getPrefShowItemsCount(getContext());
                if (showItems <= colorsList.size())
                    displayedList = new ArrayList<>(colorsList.subList(0, showItems));
            }
            colorsAdapter.setData(displayedList);
        }

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<RGBColor>> loader) {
        colorsAdapter.setData(null);
    }
}
