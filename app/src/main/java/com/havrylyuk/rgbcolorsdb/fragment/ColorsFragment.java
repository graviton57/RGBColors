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

package com.havrylyuk.rgbcolorsdb.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.havrylyuk.rgbcolorsdb.R;
import com.havrylyuk.rgbcolorsdb.RGBApplication;
import com.havrylyuk.rgbcolorsdb.adapter.ColorsCursorAdapter;
import com.havrylyuk.rgbcolorsdb.data.RGBContract;
import com.havrylyuk.rgbcolorsdb.service.ColorsGenIntentService;
import com.havrylyuk.rgbcolorsdb.util.Utility;

import butterknife.Bind;
import butterknife.ButterKnife;



/**
 * Created by Igor Havrylyuk on 28.12.2016.
 * A placeholder fragment containing a simple view.
 */
public class ColorsFragment extends Fragment
            implements LoaderManager.LoaderCallbacks<Cursor> ,
                       SharedPreferences.OnSharedPreferenceChangeListener{

    private static final String LOG_TAG = ColorsFragment.class.getSimpleName();
    private final static int COLORS_LOADER = 1001;

    @Bind(R.id.rvColors)
    RecyclerView rvColors;

    @Bind(R.id.pbWait)
    ProgressBar pbWait;

    private ColorsCursorAdapter colorsAdapter;

    public ColorsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RGBApplication.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_colors, container, false);
        ButterKnife.bind(this, rootView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvColors.setLayoutManager(layoutManager);
        colorsAdapter = new ColorsCursorAdapter(getActivity());
        rvColors.setAdapter(colorsAdapter);
        getActivity().getSupportLoaderManager().initLoader(COLORS_LOADER, null, this);
        return rootView;
    }

    @Override
    public void onDestroy() {
        RGBApplication.getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();

    }

    public void generateNewColorsList() {
        getActivity().getContentResolver().delete(RGBContract.ColorEntry.CONTENT_URI, null, null);
        Intent intent = new Intent(getActivity(), ColorsGenIntentService.class);
        getActivity().startService(intent);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals(getString(R.string.multiple_of_key))
                ||key.equals(getString(R.string.list_size_key))) {
            generateNewColorsList();
        }
        if (key.equals(getString(R.string.r_sort_key))||key.equals(getString(R.string.g_sort_key))
                ||key.equals(getString(R.string.b_sort_key)) ||key.equals(getString(R.string.display_count_key))) {
            getActivity().getSupportLoaderManager().restartLoader(COLORS_LOADER, null, this);
            if (colorsAdapter!=null) colorsAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case COLORS_LOADER:
                if (pbWait!=null) pbWait.setVisibility(View.VISIBLE);
                String orderBy = Utility.buildSortOrderString(getActivity());// the magic ;-)
                //Log.v(LOG_TAG, "orderBy="+orderBy);
                return new CursorLoader(getActivity(), RGBContract.ColorEntry.CONTENT_URI,
                        ColorsCursorAdapter.COLORS_COLUMNS,
                        null,
                        null,
                        orderBy);
            default:return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        int id = loader.getId();
        switch (id) {
            case COLORS_LOADER:
                if (pbWait!=null) pbWait.setVisibility(View.GONE);
                colorsAdapter.swapCursor(data);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
       colorsAdapter.swapCursor(null);
    }
}
