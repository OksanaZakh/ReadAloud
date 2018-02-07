package com.example.administrator.readaloud.database;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 06.02.2018.
 */

public class ResultsList implements IResult {

    private final SQLiteDatabase db;

    public ResultsList(SQLiteDatabase db) {
        this.db = db;
    }

    static void createTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE Results ("
                + "Id INTEGER PRIMARY KEY, "
                + "UserId INTEGER, "
                + "Velocity INTEGER, "
                + "Quality INTEGER, "
                + "Date TEXT"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void addResult(Result result) {
        ContentValues values = new ContentValues();
        values.put("UserId", result.getUserId());
        values.put("Velocity", result.getVelocity());
        values.put("Quality", result.getQuality());
        values.put("Date", result.getDate());
        db.insert("Results", null, values);
    }

    @Override
    public List<Result> getAllResult() {
        List<Result> resultList = new ArrayList<>();
        String selectQuery = "SELECT * FROM Results";

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
        String selectQuery = "SELECT * FROM Results";
        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            count += 1;
        }
        cursor.close();
        return count;
    }

    @Override
    public int updateResult(int id, Result result) {
        ContentValues values = new ContentValues();
        values.put("UserId", result.getUserId());
        values.put("Velocity", result.getVelocity());
        values.put("Quality", result.getQuality());
        values.put("Date", result.getDate());
        return db.update("Results", values, "Id = " + id, null);
    }

    @Override
    public void deleteResult(int id) {
        db.delete("Results", "Id = " + id, null);
        db.close();
    }

    @Override
    public Result getBestVelocityResult(int userId) {
        int bestVelocityResult = 0;
        int bestVelocityResultId = 0;
        String selectQuery = "SELECT * FROM Results";
        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            if (Integer.parseInt(cursor.getString(1)) == userId
                    && bestVelocityResult < Integer.parseInt(cursor.getString(2))) {
                bestVelocityResult = Integer.parseInt(cursor.getString(2));
                bestVelocityResultId = Integer.parseInt(cursor.getString(0));
            }
        }
        cursor.close();
        if (bestVelocityResult > 0) {
            return getResult(bestVelocityResultId);
        } else return null;
    }

    @Override
    public Result getBestQualityResult(int userId) {
        int bestQualityResult = 0;
        int bestQualityResultId = 0;
        String selectQuery = "SELECT * FROM Results";
        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            if (Integer.parseInt(cursor.getString(1)) == userId
                    && bestQualityResult < Integer.parseInt(cursor.getString(3))) {
                bestQualityResult = Integer.parseInt(cursor.getString(3));
                bestQualityResultId = Integer.parseInt(cursor.getString(0));
            }
        }
        cursor.close();
        if (bestQualityResult > 0) {
            return getResult(bestQualityResultId);
        } else return null;
    }

    @Override
    public Result getResult(int id) {
        Result result = new Result();
        String selectQuery = "SELECT * FROM Results";
        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            if (Integer.parseInt(cursor.getString(0)) == id) {
                result.setId(Integer.parseInt(cursor.getString(0)));
                result.setUserId(Integer.parseInt(cursor.getString(1)));
                result.setVelocity(Integer.parseInt(cursor.getString(2)));
                result.setQuality(Integer.parseInt(cursor.getString(3)));
                result.setDate(cursor.getString(4));
            }
        }
        cursor.close();
        return null;
    }
}
