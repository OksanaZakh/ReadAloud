package com.example.administrator.readaloud;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.readaloud.R;

/**
 * Created by Administrator on 03.02.2018.
 */

public class BaseToolbar extends AppCompatActivity {

    protected ActionBarDrawerToggle drawerToggle;

    protected final void setupToolbar(DrawerLayout mainDrawerLayout, Toolbar toolbar) {

        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

            drawerToggle = new ActionBarDrawerToggle(this, mainDrawerLayout, toolbar, R.string.open, R.string.close) {

                public void onDrawerClosed(View view) {
                    supportInvalidateOptionsMenu();
                }

                public void onDrawerOpened(View drawerView) {
                    supportInvalidateOptionsMenu();
                }
            };
            drawerToggle.setDrawerIndicatorEnabled(true);
            mainDrawerLayout.addDrawerListener(drawerToggle);
            drawerToggle.syncState();
        }
    }
}
