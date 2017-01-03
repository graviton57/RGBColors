package com.havrylyuk.rgbcolors.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;

import com.havrylyuk.rgbcolors.model.RGBColor;


/**
 * Colors JSON Helper
 * Created by Igor Havrylyuk on 3.01.2017.
 */
public class ColorsJSONSerializer {

    private Context context;
    private String filename;

    public ColorsJSONSerializer(Context c, String f) {
        context = c;
        filename = f;
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<RGBColor> loadColors() throws IOException, JSONException {
        ArrayList<RGBColor> colors = new ArrayList<>();
        BufferedReader reader = null;
        try {
            InputStream in = context.openFileInput(filename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for (int i = 0; i < array.length(); i++) {
                colors.add(new RGBColor(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            // we will ignore this one, since it happens when we start fresh
        } finally {
            if (reader != null)
                reader.close();
}
        return colors;
    }

    public void saveColors(ArrayList<RGBColor> colors) throws JSONException, IOException {

        JSONArray array = new JSONArray();
        for (RGBColor c : colors)
            array.put(c.toJSON());
        Writer writer = null;
        try {
            OutputStream out = context.openFileOutput(filename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
