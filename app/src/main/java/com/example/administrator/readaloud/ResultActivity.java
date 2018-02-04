package com.example.administrator.readaloud;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


public class ResultActivity extends BaseToolbar {

    public static final String TAG_ACTIVITY_RESULT = "TAG_ACTIVITY_RESULT";
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        if (savedInstanceState == null) {
            ResultFragment fragment = new ResultFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.result_fragment_container, fragment, TAG_ACTIVITY_RESULT)
                    .commit();
        }
        toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        navigationView = (NavigationView) findViewById(R.id.result_navigationView);
        drawerLayout = (DrawerLayout) findViewById(R.id.result_drawer_layout);

        setupToolbar(drawerLayout, toolbar);
        setTitle(R.string.general_results);
        navigationView.setNavigationItemSelectedListener(this);

    }
}
