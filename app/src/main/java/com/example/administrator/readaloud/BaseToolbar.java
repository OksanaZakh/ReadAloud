package com.example.administrator.readaloud;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Administrator on 03.02.2018.
 */

public class BaseToolbar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_startReadItem:
                Intent readIntent = new Intent(this, ReadSectionActivity.class);
                startActivity(readIntent);
                break;
            case R.id.menu_resultsItem:
                Intent resultIntent = new Intent(this, ResultActivity.class);
                startActivity(resultIntent);
                break;
            case R.id.menu_settingsItem:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                break;
        }
        return true;
    }
}
