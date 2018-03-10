package com.example.administrator.readaloud.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.administrator.readaloud.R;
import com.example.administrator.readaloud.app.core.activities.AppActivity;

public class MainActivity extends AppActivity {
    private static final String TAG = "MainActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcomeIntent = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(welcomeIntent);
                finish();
            }
        }, 500);
    }
}