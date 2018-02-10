package com.example.administrator.readaloud.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.readaloud.R;
import com.example.administrator.readaloud.ui.read.ReadSectionFragment;
import com.example.administrator.readaloud.ui.result.ResultSectionFragment;
import com.example.administrator.readaloud.ui.settings.SettingsSectionFragment;

/**
 * Created by Administrator on 03.02.2018.
 */

public class BaseActivity extends BaseToolbar implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static long back_pressed_time;
    private static long PERIOD = 2000;

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
                    .add(R.id.base_fragment_container, fragment, ReadSectionFragment.TAG_READ_SECTION)
                    .commit();
        }

        toolbar = findViewById(R.id.toolbar_main);
        drawerLayout = findViewById(R.id.base_activity_drawer_layout);
        navigationView = findViewById(R.id.base_navigationView);

        setupToolbar(drawerLayout, toolbar);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.menu_startReadItem:
                startReadSectionFragment();
                break;
            case R.id.menu_resultsItem:
                fragment = new ResultSectionFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.base_fragment_container, fragment, ResultSectionFragment.TAG_RESULT_SECTION)
                        .commit();
                getSupportActionBar().setTitle(R.string.general_results);

                break;
            case R.id.menu_settingsItem:
                fragment = new SettingsSectionFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.base_fragment_container, fragment, SettingsSectionFragment.TAG_SETTINGS_SECTION)
                        .commit();
                getSupportActionBar().setTitle(R.string.general_settings);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
                toolbar.setNavigationOnClickListener(this);
                break;
            case R.id.menu_feedbackItem:
                createFeedback();
                break;
            case R.id.menu_exitItem:
                exitFromApp();
        }
        drawerLayout.closeDrawers();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (back_pressed_time + PERIOD > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), R.string.base_activity_press_again_to_exit, Toast.LENGTH_SHORT).show();
        }
        back_pressed_time = System.currentTimeMillis();
    }

    public void exitFromApp() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.menu_navigation_item_exit)
                .setMessage(R.string.navigation_menu_exit_dialog_question)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }

    public void createFeedback() {

    }

    @Override
    public void onClick(View view) {
        startReadSectionFragment();
        setupToolbar(drawerLayout, toolbar);
    }

    public void startReadSectionFragment() {
        ReadSectionFragment fragment = new ReadSectionFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.base_fragment_container, fragment, ReadSectionFragment.TAG_READ_SECTION)
                .commit();
        getSupportActionBar().setTitle(R.string.general_start_reading);
    }
}
