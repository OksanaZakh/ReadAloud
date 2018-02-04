package com.example.administrator.readaloud;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

/**
 * Created by Administrator on 03.02.2018.
 */

public class ReadSectionActivity extends BaseToolbar {

    public static final String TAG_ACTIVITY_READ = "TAG_ACTIVITY_READ";
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_section);

        if (savedInstanceState == null) {
            ReadSectionFragment fragment = new ReadSectionFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.read_section_fragment_container, fragment, TAG_ACTIVITY_READ)
                    .commit();
        }
        toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        drawerLayout = (DrawerLayout) findViewById(R.id.read_section_drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.read_section_navigationView);

        setupToolbar(drawerLayout, toolbar);
        setTitle(R.string.general_start_reading);
        navigationView.setNavigationItemSelectedListener(this);

    }

}
