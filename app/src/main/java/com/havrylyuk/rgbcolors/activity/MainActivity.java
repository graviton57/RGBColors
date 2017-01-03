package com.havrylyuk.rgbcolors.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.havrylyuk.rgbcolors.R;

import com.havrylyuk.rgbcolors.fragment.ColorsFragment;

import butterknife.Bind;
/**
 *
 * Created by Igor Havrylyuk on 30.12.2016.
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        if (id == R.id.action_generate) {
            ColorsFragment colorsFragment = (ColorsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
            if (colorsFragment != null) colorsFragment.updateColorsList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
