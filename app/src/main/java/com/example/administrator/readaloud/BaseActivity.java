package com.example.administrator.readaloud;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Created by Administrator on 03.02.2018.
 */

public class BaseActivity extends BaseToolbar implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG_ACTIVITY_READ = "TAG_ACTIVITY_READ";
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        if (savedInstanceState == null) {
            ReadSectionFragment fragment = new ReadSectionFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.base_fragment_container, fragment, TAG_ACTIVITY_READ)
                    .commit();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        drawerLayout = (DrawerLayout) findViewById(R.id.base_activity_drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.base_navigationView);

        setupToolbar(drawerLayout, toolbar);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_startReadItem:
                ReadSectionFragment readSectionFragment = new ReadSectionFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.base_fragment_container, readSectionFragment, TAG_ACTIVITY_READ)
                        .commit();
                getSupportActionBar().setTitle(R.string.general_start_reading);
                break;
            case R.id.menu_resultsItem:
                ResultFragment resultFragment = new ResultFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.base_fragment_container, resultFragment, TAG_ACTIVITY_READ)
                        .commit();
                getSupportActionBar().setTitle(R.string.general_results);
                break;
            case R.id.menu_settingsItem:
                SettingsFragment settingsFragment = new SettingsFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.base_fragment_container, settingsFragment, TAG_ACTIVITY_READ)
                        .commit();
                getSupportActionBar().setTitle(R.string.general_settings);
                break;
        }
        drawerLayout.closeDrawers();
        return true;
    }
}
