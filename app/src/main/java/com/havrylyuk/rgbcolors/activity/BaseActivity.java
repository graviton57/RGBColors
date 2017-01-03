package com.havrylyuk.rgbcolors.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;



import butterknife.ButterKnife;

/**
 *
 * Created by Igor Havrylyuk on 30.12.2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
    }

    /**
     * abstract method which returns id of layout in the form of R.layout.layout_name.
     * @return id of layout in the form of R.layout.layout_name
     */
    protected abstract int getLayout();


}
