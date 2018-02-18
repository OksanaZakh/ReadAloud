package com.example.administrator.readaloud.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.readaloud.R;
import com.example.administrator.readaloud.database.DBHandler;
import com.example.administrator.readaloud.database.DBHelper;
import com.example.administrator.readaloud.ui.read.ReadSectionFragment;
import com.example.administrator.readaloud.ui.result.ResultSectionFragment;
import com.example.administrator.readaloud.ui.settings.SettingsSectionFragment;
import com.example.administrator.readaloud.utils.Constants;


/**
 * Created by Administrator on 03.02.2018.
 */

public class BaseActivity extends BaseToolbar implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private String userName;
    private SharedPreferences preferences;
    public TextView navigationHeaderTextView;

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

        preferences = getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE);
        userName = preferences.getString(Constants.APP_PREFERENCES_USER, "");

        navigationHeaderTextView = navigationView.getHeaderView(0).findViewById(R.id.navigation_header_main_textView_userName);
        navigationHeaderTextView.setText(userName);
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
        if (Constants.back_pressed_time + Constants.PERIOD > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), R.string.base_activity_press_again_to_exit, Toast.LENGTH_SHORT).show();
        }
        Constants.back_pressed_time = System.currentTimeMillis();
    }

    public void exitFromApp() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.menu_navigation_item_exit)
                .setMessage(R.string.navigation_menu_exit_dialog_question)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        logOutUser();
                        Intent welcomeIntent = new Intent(getBaseContext(), MainActivity.class);
                        finish();
                        startActivity(welcomeIntent);
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

    public void logOutUser() {
        DBHelper helper = new DBHelper(getBaseContext());
        DBHandler handler = new DBHandler(helper);
        handler.getUserListDB().makeLogOut(userName);
        preferences.edit().clear().apply();
    }
}
