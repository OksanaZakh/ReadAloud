package com.example.administrator.readaloud.app.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.administrator.readaloud.R;
import com.example.administrator.readaloud.app.core.ApplicationHandler;
import com.example.administrator.readaloud.app.core.activities.AppActivity;
import com.example.administrator.readaloud.app.ui.welcome.WelcomeFragment;

public class WelcomeActivity extends AppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if (((ApplicationHandler) getApplication()).isUserLogged()) {
            Intent intent = new Intent(this, BaseActivity.class);
            finish();
            startActivity(intent);
        } else {
            if (savedInstanceState == null) {
                WelcomeFragment fragment = new WelcomeFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.WelcomeActivity_Fragment_Container, fragment, WelcomeFragment.TAG_WELCOME)
                        .commit();
            }
        }
    }
}
