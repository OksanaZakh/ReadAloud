package com.example.administrator.readaloud.database;

/**
 * Created by Administrator on 06.02.2018.
 */

public class DBHandler {

    private final ResultListListDB resultListDB;
    private final UserListListDB userListDB;

    public DBHandler(DBHelper helper) {
        userListDB = new UserListListDB(helper.getWritableDatabase());
        resultListDB = new ResultListListDB(helper.getWritableDatabase());
    }

    public ResultListListDB getResultListDB() {
        return resultListDB;
    }

    public UserListListDB getUserListDB() {
        return userListDB;
    }
}
