package com.example.administrator.readaloud.databaseservice;

/**
 * Created by Administrator on 06.02.2018.
 */

public class DBHandler {

    private final ResultListDB resultListDB;
    private final UserListDB userListDB;

    public DBHandler(DBHelper helper) {
        userListDB = new UserListDB(helper.getWritableDatabase());
        resultListDB = new ResultListDB(helper.getWritableDatabase());
    }

    public ResultListDB getResultListDB() {
        return resultListDB;
    }

    public UserListDB getUserListDB() {
        return userListDB;
    }
}
