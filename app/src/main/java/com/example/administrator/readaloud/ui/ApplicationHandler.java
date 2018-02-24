package com.example.administrator.readaloud.ui;

import android.app.Application;

import com.example.administrator.readaloud.database.DBHandler;
import com.example.administrator.readaloud.database.DBHelper;

/**
 * Created by Administrator on 21.02.2018.
 */

public class ApplicationHandler extends Application {

    private DBHandler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        DBHelper helper = new DBHelper(getBaseContext());
        this.handler = new DBHandler(helper);
    }

    public DBHandler getHandler() {
        return this.handler;
    }

    public boolean isUserLogged() {
        int id = this.handler.getUserListDB().getIdOfLoggedUser();
        if (id != 0) {
            return true;
        } else {
            return false;
        }
    }
}



