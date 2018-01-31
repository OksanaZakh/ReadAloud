package com.example.administrator.readaloud;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public static final String TAG_ACTIVITY = "TAG_ACTIVITY";

    private DrawerLayout myDrawerLayout;
    private ActionBarDrawerToggle aToggle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating side bar
        myDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        aToggle = new ActionBarDrawerToggle(this, myDrawerLayout, R.string.open, R.string.close);

        myDrawerLayout.addDrawerListener(aToggle);
        aToggle.syncState();

        if (savedInstanceState == null) {
            WelcomeFragment fragment = new WelcomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_fragment_container, fragment, TAG_ACTIVITY)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (aToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
