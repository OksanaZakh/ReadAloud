package com.example.administrator.readaloud.database;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.readaloud.ui.result.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 06.02.2018.
 */

public class ResultListDB implements IResultListDB {

    public static final String TABLE_RESULT = "result";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_VELOCITY = "velocity";
    public static final String COLUMN_QUALITY = "quality";
    public static final String COLUMN_DATE = "date";

    private final SQLiteDatabase db;

    public ResultListDB(SQLiteDatabase db) {
        this.db = db;
    }

    static void createTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_RESULT + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_USER_ID + " INTEGER, "
                + COLUMN_VELOCITY + " INTEGER, "
                + COLUMN_QUALITY + " INTEGER, "
                + COLUMN_DATE + " TEXT"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void addResult(Result result) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, result.getUserId());
        values.put(COLUMN_VELOCITY, result.getVelocity());
        values.put(COLUMN_QUALITY, result.getQuality());
        values.put(COLUMN_DATE, result.getDate());
        db.insert(TABLE_RESULT, null, values);
    }

    @Override
    public List<Result> getAllResult() {
        List<Result> resultList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_RESULT;

        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            Result result = new Result();
            result.setId(Integer.parseInt(cursor.getString(0)));
            result.setUserId(Integer.parseInt(cursor.getString(1)));
            result.setVelocity(Integer.parseInt(cursor.getString(2)));
            result.setQuality(Integer.parseInt(cursor.getString(3)));
            result.setDate(cursor.getString(4));
            resultList.add(result);
        }
        cursor.close();
        return resultList;
    }

    @Override
    public int getResultCount() {
        int count = 0;
        String selectQuery = "SELECT * FROM " + TABLE_RESULT;
        Cursor cursor = db.rawQuery(selectQuery, null);
        count = cursor.getCount();
        cursor.close();
        return count;
    }

    @Override
    public int updateResult(int id, Result result) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, result.getUserId());
        values.put(COLUMN_VELOCITY, result.getVelocity());
        values.put(COLUMN_QUALITY, result.getQuality());
        values.put(COLUMN_DATE, result.getDate());
        return db.update(TABLE_RESULT, values, "Id = " + id, null);
    }

    @Override
    public void deleteResult(int id) {
        db.delete(TABLE_RESULT, "Id = " + id, null);
        db.close();
    }

    @Override
    public Result getResult(int id) {
        Result result = new Result();
        String selectQuery = "SELECT * FROM " + TABLE_RESULT;
        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            if (Integer.parseInt(cursor.getString(0)) == id) {
                result.setId(Integer.parseInt(cursor.getString(0)));
                result.setUserId(Integer.parseInt(cursor.getString(1)));
                result.setVelocity(Integer.parseInt(cursor.getString(2)));
                result.setQuality(Integer.parseInt(cursor.getString(3)));
                result.setDate(cursor.getString(4));
                cursor.close();
                return result;
            }
        }
        cursor.close();
        return null;
    }
}
