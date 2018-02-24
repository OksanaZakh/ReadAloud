package com.example.administrator.readaloud.app.ui.welcome;

/**
 * Created by Administrator on 06.02.2018.
 */

public class UserModel {

    private int id;
    private String name;
    private String token;
    private int avatarId;

    public UserModel() {
    }

    public UserModel(int id, String name, int avatarId) {
        this.id = id;
        this.name = name;
        this.avatarId = avatarId;
    }

    public UserModel(String name, int avatarId) {
        this.name = name;
        this.avatarId = avatarId;

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

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

}
