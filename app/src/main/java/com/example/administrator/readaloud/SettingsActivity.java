package com.example.administrator.readaloud;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class SettingsActivity extends BaseToolbar {

    public static final String TAG_ACTIVITY_SETTINGS = "TAG_ACTIVITY_SETTINGS";
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (savedInstanceState == null) {
            SettingsFragment fragment = new SettingsFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.settings_fragment_container, fragment, TAG_ACTIVITY_SETTINGS)
                    .commit();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        navigationView = (NavigationView) findViewById(R.id.settings_navigationView);
        drawerLayout = (DrawerLayout) findViewById(R.id.settings_drawer_layout);

        setupToolbar(drawerLayout, toolbar);
        setTitle(R.string.general_settings);
        navigationView.setNavigationItemSelectedListener(this);

    }
}
