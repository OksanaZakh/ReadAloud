package com.example.administrator.readaloud;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG_ACTIVITY = "TAG_ACTIVITY";

    private DrawerLayout myDrawerLayout;
    private ActionBarDrawerToggle aToggle;
    private NavigationView navigationView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = (NavigationView) findViewById(R.id.main_navigationView);
        myDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        aToggle = new ActionBarDrawerToggle(this, myDrawerLayout, R.string.open, R.string.close);

        //Creating side bar
        myDrawerLayout.addDrawerListener(aToggle);
        aToggle.syncState();

        if (savedInstanceState == null) {
            WelcomeFragment fragment = new WelcomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_fragment_container, fragment, TAG_ACTIVITY)
                    .commit();
        }

        //Make menu items in side bar clickable
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_startReadItem:
                //Эта часть будет переписана, когда создам боковое меню для всех активити
                ReadSectionFragment fragment = new ReadSectionFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.main_fragment_container, fragment, TAG_ACTIVITY)
                        .commit();
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
        myDrawerLayout.closeDrawers();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (aToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
