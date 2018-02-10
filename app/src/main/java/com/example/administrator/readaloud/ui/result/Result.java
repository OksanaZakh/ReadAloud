package com.example.administrator.readaloud.ui.result;

/**
 * Created by Administrator on 06.02.2018.
 */

public class Result {

    private int id;
    private int userId;
    private int velocity; // number of words reading per minute
    private int quality; // % of text red correctly
    private String date;

    public Result() {
    }

    public Result(int id, int userId, int velocity, int quality, String date) {
        this.id = id;
        this.userId = userId;
        this.velocity = velocity;
        this.quality = quality;
        this.date = date;
    }

    public Result(int userId, int velocity, int quality, String date) {
        this.userId = userId;
        this.velocity = velocity;
        this.quality = quality;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
