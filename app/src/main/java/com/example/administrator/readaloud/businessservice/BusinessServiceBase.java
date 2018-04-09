package com.example.administrator.readaloud.businessservice;

import android.content.Context;

import com.example.administrator.readaloud.app.core.BeanContext;
import com.example.administrator.readaloud.app.ui.welcome.UserModel;
import com.example.administrator.readaloud.databaseservice.DBHandler;
import com.example.administrator.readaloud.databaseservice.DBHelper;
import com.example.administrator.readaloud.databaseservice.IUserListDB;
import com.example.administrator.readaloud.databaseservice.UserListDB;

import java.util.UUID;

/**
 * Created by p-sha on 24.02.2018.
 */

public class BusinessServiceBase implements BusinessService {

    public BusinessServiceBase(Context context) {
        this.context = context;
        this.dbHelper = new DBHelper(context);
        this.handler = new DBHandler(dbHelper);
    }

    private final Context context;
    private DBHelper dbHelper;
    private DBHandler handler;

    @Override
    public UserModel getMyUser(Context context, DBHelper helper, String name) {
        IUserListDB userListDB = new UserListDB(helper.getWritableDatabase());
        return userListDB.getUser(name);
    }

    @Override
    public void makeLogin(String name, String avatarUrl) {
        UUID tokenUuid = UUID.randomUUID();
        String token = tokenUuid.toString();
        boolean isUserInBase = handler.getUserListDB().isUserInBase(name);
        if (!isUserInBase) {
            UserModel user = new UserModel();
            user.setName(name);
            user.setToken(token);
            user.setAvatarUrl(avatarUrl);
            handler.getUserListDB().addUser(user);
        } else {
            UserModel user = handler.getUserListDB().getUser(name);
            user.setToken(token);
            user.setAvatarUrl(avatarUrl);
            handler.getUserListDB().updateUser(user.getId(), user);
        }
    }
}
