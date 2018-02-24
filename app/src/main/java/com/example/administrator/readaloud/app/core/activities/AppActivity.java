package com.example.administrator.readaloud.app.core.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.readaloud.app.core.BeanContext;

/**
 * Created by p-sha on 24.02.2018.
 */

public abstract class AppActivity extends AppCompatActivity {

    private BeanContext beanContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        if (beanContext != null) {
            beanContext.close();
            beanContext = null;
        }
        beanContext = BeanContext.getInstance(this);
        super.onCreate(savedInstanceState, persistentState);
    }

    public BeanContext getBeanContext() {
        return beanContext;
    }

    @Override
    public void onBackPressed() {
        onBackAction();
    }

    protected void onBackAction() {
        super.onBackPressed();
    }

    protected void onClosePressed() {
        finish();
    }

}
