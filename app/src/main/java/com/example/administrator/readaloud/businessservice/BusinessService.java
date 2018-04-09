package com.example.administrator.readaloud.businessservice;

import android.content.Context;

import com.example.administrator.readaloud.app.ui.welcome.UserModel;
import com.example.administrator.readaloud.databaseservice.DBHelper;

/**
 * Created by p-sha on 24.02.2018.
 */

public interface BusinessService {

    UserModel getMyUser(Context context, DBHelper helper, String name);

    void makeLogin(String name, String avatarUrl);

}
