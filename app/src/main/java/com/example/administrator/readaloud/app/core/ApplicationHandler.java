package com.example.administrator.readaloud.app.core;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.example.administrator.readaloud.databaseservice.DBHandler;
import com.example.administrator.readaloud.databaseservice.DBHelper;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Administrator on 21.02.2018.
 */

public class ApplicationHandler extends Application {

    private DBHandler handler;

    public static ApplicationHandler getInstance(Context context) {
        return ((ApplicationHandler) context.getApplicationContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        DBHelper helper = new DBHelper(getBaseContext());
        this.handler = new DBHandler(helper);
    }

    public DBHandler getHandler() {
        return this.handler;
    }

    public boolean isUserLogged() {
        int id = this.handler.getUserListDB().getIdOfLoggedUser();
        return id != 0;
    }

    public BeanContext createBeanContext(Context creatorContext, BeanFactory beanFactory) {
        return new BeanContext(creatorContext, beanFactory);
    }
}



