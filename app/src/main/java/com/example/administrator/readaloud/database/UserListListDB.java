package com.example.administrator.readaloud.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.readaloud.ui.welcome.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 06.02.2018.
 */

public class UserListListDB implements IUserListDB {

    private final SQLiteDatabase db;
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TOKEN = "token";
    public static final String COLUMN_AVATAR_ID = "avatar_id";

    public UserListListDB(SQLiteDatabase db) {
        this.db = db;
    }

    static void createTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_USERS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_TOKEN + " TEXT, "
                + COLUMN_AVATAR_ID + " INTEGER"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_TOKEN, user.getToken());
        values.put(COLUMN_AVATAR_ID, user.getAvatarId());
        db.insert(TABLE_USERS, null, values);
    }

    @Override
    public boolean isUserInBase(String name) {
        String selectQuery = "SELECT * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            if (cursor.getString(1).equals(name.trim())) {
                cursor.close();
                return true;
            }
        }
        cursor.close();
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_USERS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setName(cursor.getString(1));
            user.setToken(cursor.getString(2));
            user.setAvatarId(Integer.parseInt(cursor.getString(3)));
            userList.add(user);
        }
        cursor.close();
        return userList;
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
    public int updateUser(int id, User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, user.getName());
        values.put(COLUMN_TOKEN, user.getToken());
        values.put(COLUMN_AVATAR_ID, user.getAvatarId());
        return db.update(TABLE_USERS, values, "Id = " + id, null);
    }

    @Override
    public void deleteUser(int id) {
        db.delete(TABLE_USERS, "Id = " + id, null);
        db.close();
    }

    @Override
    public int getUserId(String name) {
        String selectQuery = "SELECT " + COLUMN_NAME + " FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            if (cursor.getString(1).equals(name.trim())) {
                int userId = Integer.parseInt(cursor.getString(0));
                cursor.close();
                return userId;
            }
        }
        cursor.close();
        return 0;
    }
}
