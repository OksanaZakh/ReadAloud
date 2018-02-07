package com.example.administrator.readaloud.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 06.02.2018.
 */

public class UsersList implements IUser {

    private final SQLiteDatabase db;

    public UsersList(SQLiteDatabase db) {
        this.db = db;
    }

    static void createTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE Results ("
                + "Id INTEGER PRIMARY KEY, "
                + "Name TEXT, "
                + "Token TEXT, "
                + "AvatarId INTEGER"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put("Name", user.getName());
        values.put("Token", user.getToken());
        values.put("AvatarId", user.getAvatarId());
        db.insert("Users", null, values);
    }

    @Override
    public boolean isUserInBase(String name) {
        String selectQuery = "SELECT * FROM Users";
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
        String selectQuery = "SELECT * FROM Users";

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
        String selectQuery = "SELECT * FROM Users";
        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            count += 1;
        }
        cursor.close();
        return count;
    }

    @Override
    public int updateUser(int id, User user) {
        ContentValues values = new ContentValues();
        values.put("Name", user.getName());
        values.put("Token", user.getToken());
        values.put("AvatarId", user.getAvatarId());
        return db.update("Users", values, "Id = " + id, null);
    }

    @Override
    public void deleteUser(int id) {
        db.delete("Users", "Id = " + id, null);
        db.close();
    }

    @Override
    public int getUserId(String name) {
        String selectQuery = "SELECT * FROM Users";
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
