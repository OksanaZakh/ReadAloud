package com.example.administrator.readaloud.database;

/**
 * Created by Administrator on 06.02.2018.
 */

public class DBHandler {

    private final ResultsList resultsList;
    private final UsersList usersList;

    public DBHandler(DBHelper helper) {
        usersList = new UsersList(helper.getWritableDatabase());
        resultsList = new ResultsList(helper.getWritableDatabase());
    }

    public ResultsList getResultsList() {
        return resultsList;
    }

    public UsersList getUsersList() {
        return usersList;
    }
}
