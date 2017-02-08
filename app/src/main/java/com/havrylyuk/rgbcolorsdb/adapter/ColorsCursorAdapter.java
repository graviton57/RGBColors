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

package com.havrylyuk.rgbcolorsdb.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.havrylyuk.rgbcolorsdb.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.havrylyuk.rgbcolorsdb.data.RGBContract.ColorEntry;
import com.havrylyuk.rgbcolorsdb.model.RGBColor;
import com.havrylyuk.rgbcolorsdb.util.Utility;

/**
 * Simple Colors Cursor Adapter
 * Created by Igor Havrylyuk on 04.01.2017.
 */

public class ColorsCursorAdapter extends RecyclerView.Adapter<ColorsCursorAdapter.ColorCursorViewHolder> {

    public static final String[] COLORS_COLUMNS = {
            ColorEntry.COLOR_R,
            ColorEntry.COLOR_G,
            ColorEntry.COLOR_B
    };

    public static final int COL_R = 0;
    public static final int COL_G = 1;
    public static final int COL_B = 2;

    private Cursor mCursor;
    private Context context;

    public ColorsCursorAdapter(Context context) {
        this.context = context;
    }

    public void swapCursor(Cursor cursor) {
        this.mCursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public ColorCursorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ColorCursorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ColorCursorViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        int r = mCursor.getInt(COL_R);
        int g = mCursor.getInt(COL_G);
        int b = mCursor.getInt(COL_B);
        int color = new RGBColor(r,g,b).getRGB();
        holder.itemView.setBackgroundColor(color);
        holder.tvColor.setText(String.format("%s #%s", "RGB(" + r + "," + g + "," + b + ")", Integer.toHexString(color)));
        holder.tvColor.setTextColor(Utility.getComplimentColor(color));
        holder.tvCount.setText(String.format("%s", String.valueOf(position + 1)));
        final String message = (position % 2 == 0) ? "This is a great color!" : "Great choice! I also like this color!";
        final SpannableString spannableString = new SpannableString(message);
        spannableString.setSpan(new ForegroundColorSpan(color), 0, spannableString.length(), 0);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, spannableString, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    static class ColorCursorViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvColor)
        TextView tvColor;
        @Bind(R.id.tvCount)
        TextView tvCount;

        ColorCursorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
