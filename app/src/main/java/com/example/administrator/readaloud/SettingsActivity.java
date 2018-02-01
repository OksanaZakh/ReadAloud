package com.example.administrator.readaloud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    public static final String TAG_ACTIVITY_SETTINGS = "TAG_ACTIVITY_SETTINGS";

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
        setTitle(R.string.general_settings);
    }
}
