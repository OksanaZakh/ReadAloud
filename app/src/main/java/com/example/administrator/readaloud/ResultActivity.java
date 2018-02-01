package com.example.administrator.readaloud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ResultActivity extends AppCompatActivity {

    public static final String TAG_ACTIVITY_RESULT = "TAG_ACTIVITY_RESULT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        if (savedInstanceState == null) {
            ResultFragment fragment = new ResultFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.result_fragment_container, fragment, TAG_ACTIVITY_RESULT)
                    .commit();
        }
        setTitle(R.string.general_results);
    }
}
