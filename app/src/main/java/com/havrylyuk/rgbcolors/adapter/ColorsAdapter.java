package com.havrylyuk.rgbcolors.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.havrylyuk.rgbcolors.R;
import com.havrylyuk.rgbcolors.model.RGBColor;
import com.havrylyuk.rgbcolors.util.Utility;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 *
 * Created by Igor Havrylyuk on 30.12.2016.
 */

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ColorViewHolder> {


    private ArrayList<RGBColor> colors;
    private Context context;

    public ColorsAdapter(Context context, ArrayList<RGBColor> colors) {
           this.colors = colors;
           this.context = context;
    }

    public void setData(ArrayList<RGBColor> data) {
        this.colors = data;
        notifyDataSetChanged();
    }

    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ColorViewHolder holder,  int position) {
        int color = colors.get(position).getRGB();
        holder.itemView.setBackgroundColor(color);
        holder.tvColor.setText(String.format("%s #%s", colors.get(position).toString(),Integer.toHexString(color)));
        holder.tvColor.setTextColor(Utility.getComplimentColor(color));
        holder.tvCount.setText(String.format("%s", String.valueOf(position+1)));
        final String message = (position % 2 == 0) ? "This is a great color!" : "Great choice! I also like this color!";
        final SpannableString spannableString = new SpannableString(message);
        spannableString.setSpan(new ForegroundColorSpan(color), 0, spannableString.length(), 0);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,spannableString,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (colors == null) return 0;
        return colors.size();
    }


    /**
    *  Color RecyclerView Holder.
    */

    static class ColorViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvColor)
        TextView tvColor;

        @Bind(R.id.tvCount)
        TextView tvCount;

        ColorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
