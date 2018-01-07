package com.ahmedkhames.bitbucket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by Ahmed on 06/01/2018.
 */

public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
    }

    protected abstract void setupActivityComponent();

}
