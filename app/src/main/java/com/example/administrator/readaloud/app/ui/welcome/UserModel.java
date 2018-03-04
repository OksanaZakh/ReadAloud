package com.example.administrator.readaloud.app.ui.welcome;

/**
 * Created by Administrator on 06.02.2018.
 */

public class UserModel {

    private int id;
    private String name;
    private String token;
    private String avatarUrl;

    public UserModel() {
    }

    public UserModel(int id, String name, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
    }

    public UserModel(String name, String avatarUrl) {
        this.name = name;
        this.avatarUrl = avatarUrl;

    }

    public UserModel(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}
