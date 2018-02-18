package com.example.administrator.readaloud.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.administrator.readaloud.R;
import com.example.administrator.readaloud.database.DBHandler;
import com.example.administrator.readaloud.database.DBHelper;
import com.example.administrator.readaloud.ui.welcome.WelcomeFragment;
import com.example.administrator.readaloud.utils.Constants;

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

        DBHelper helper = new DBHelper(getBaseContext());
        DBHandler handler = new DBHandler(helper);
        int id = handler.getUserListDB().getIdOfLoggedUser();

        if (id != 0) {
            Intent intent = new Intent(this, BaseActivity.class);
            finish();
            startActivity(intent);
        }
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
}