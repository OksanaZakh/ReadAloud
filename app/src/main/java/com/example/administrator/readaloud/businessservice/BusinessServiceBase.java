package com.example.administrator.readaloud.businessservice;

import android.content.Context;

import com.example.administrator.readaloud.app.core.BeanContext;
import com.example.administrator.readaloud.app.ui.welcome.UserModel;
import com.example.administrator.readaloud.databaseservice.DBHelper;
import com.example.administrator.readaloud.databaseservice.IUserListDB;
import com.example.administrator.readaloud.databaseservice.UserListDB;

/**
 * Created by p-sha on 24.02.2018.
 */

public class BusinessServiceBase implements BusinessService {

    public BusinessServiceBase(BeanContext beanContext) {
        this.beanContext = beanContext;
        this.context = beanContext.getContext();
    }

    private final BeanContext beanContext;
    private final Context context;



    @Override
    public UserModel getMyUser(Context context, DBHelper helper, String name) {
        IUserListDB userListDB = new UserListDB(helper.getWritableDatabase());
        return userListDB.getUser(name);
    }
}
