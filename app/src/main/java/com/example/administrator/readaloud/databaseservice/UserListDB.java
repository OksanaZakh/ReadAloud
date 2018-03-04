package com.example.administrator.readaloud.databaseservice;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.readaloud.app.ui.welcome.UserModel;
import com.example.administrator.readaloud.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 06.02.2018.
 */

public class UserListDB implements IUserListDB {

    private final SQLiteDatabase db;
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TOKEN = "token";
    public static final String COLUMN_AVATAR_URL = "avatar_url";

    public UserListDB(SQLiteDatabase db) {
        this.db = db;
    }

    static void createTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_USERS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_TOKEN + " TEXT, "
                + COLUMN_AVATAR_URL + " TEXT"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void addUser(UserModel userModel) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, userModel.getName());
        values.put(COLUMN_TOKEN, userModel.getToken());
        values.put(COLUMN_AVATAR_URL, userModel.getAvatarUrl());
        db.insert(TABLE_USERS, null, values);
    }

    @Override
    public boolean isUserInBase(String name) {
        String selectQuery = "SELECT * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        }
        while (cursor.moveToNext()) {
            if (cursor.getString(1).equals(name)) {
                cursor.close();
                return true;
            }
        }
        cursor.close();
        return false;
    }

    @Override
    public List<UserModel> getAllUsers() {
        List<UserModel> userModelList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_USERS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            UserModel userModel = new UserModel();
            userModel.setId(Integer.parseInt(cursor.getString(0)));
            userModel.setName(cursor.getString(1));
            userModel.setToken(cursor.getString(2));
            userModel.setAvatarUrl(cursor.getString(3));
            userModelList.add(userModel);
        }
        cursor.close();
        return userModelList;
    }

    @Override
    public int getUserCount() {
        int count = 0;
        String selectQuery = "SELECT * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        count = cursor.getCount();
        cursor.close();
        return count;
    }

    @Override
    public int updateUser(int id, UserModel userModel) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, userModel.getName());
        values.put(COLUMN_TOKEN, userModel.getToken());
        values.put(COLUMN_AVATAR_URL, userModel.getAvatarUrl());
        return db.update(TABLE_USERS, values, COLUMN_ID + "=" + id, null);
    }

    @Override
    public void deleteUser(int id) {
        db.delete(TABLE_USERS, COLUMN_ID + "=" + id, null);
        db.close();
    }

    @Override
    public UserModel getUser(String name) {
        UserModel user = new UserModel();
        String selectQuery = "SELECT * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            if (cursor.getString(1).equals(name)) {
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName(name);
                user.setToken(cursor.getString(2));
                user.setAvatarUrl(cursor.getString(3));
                cursor.close();
                return user;
            }
        }
        cursor.close();
        return null;
    }

    public void makeLogOut(String name) {
        UserModel user = getUser(name);
        user.setToken(Constants.DEFAULT_TOKEN);
        updateUser(user.getId(), user);
    }

    @Override
    public void makeLogIn(String name, String avatarUrl) {
        UUID tokenUuid = UUID.randomUUID();
        String token = tokenUuid.toString();
        if (!isUserInBase(name)) {
            UserModel user = new UserModel();
            user.setName(name);
            user.setToken(token);
            user.setAvatarUrl(avatarUrl);
            addUser(user);
        } else {
            UserModel user = getUser(name);
            user.setToken(token);
            user.setAvatarUrl(avatarUrl);
            updateUser(user.getId(), user);
        }

    }

    public int getIdOfLoggedUser() {
        int id = 0;
        String selectQuery = "SELECT _id, token FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            if (!cursor.getString(1).equals(Constants.DEFAULT_TOKEN)) {
                id = cursor.getInt(0);
            }
        }
        cursor.close();
        return id;
    }

}
