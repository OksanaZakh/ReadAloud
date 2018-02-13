package com.example.administrator.readaloud.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.administrator.readaloud.R;
import com.example.administrator.readaloud.ui.welcome.WelcomeFragment;

public class MainActivity extends AppCompatActivity {

    private static long back_pressed_time;
    private static long PERIOD = 2000;
    private SharedPreferences preferences;

    public static final String APP_PREFERENCES = "prefsFile";
    public static final String APP_PREFERENCES_LOGGED = "logged";
    public static final String APP_PREFERENCES_USER = "user";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            WelcomeFragment fragment = new WelcomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_fragment_container, fragment, WelcomeFragment.TAG_WELCOME)
                    .commit();
        }

        preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (preferences.getBoolean(APP_PREFERENCES_LOGGED, false)) {
            Intent intent = new Intent(this, BaseActivity.class);
            finish();
            startActivity(intent);
        }

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
}