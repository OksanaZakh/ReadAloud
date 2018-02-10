package com.example.administrator.readaloud.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.readaloud.R;
import com.example.administrator.readaloud.ui.welcome.WelcomeFragment;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            WelcomeFragment fragment = new WelcomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_fragment_container, fragment, WelcomeFragment.TAG_WELCOME)
                    .commit();
        }
    }
}