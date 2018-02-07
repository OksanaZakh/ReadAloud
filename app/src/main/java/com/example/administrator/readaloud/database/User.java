package com.example.administrator.readaloud.database;

/**
 * Created by Administrator on 06.02.2018.
 */

public class User {

    private int id;
    private String name;
    private String token;
    private int avatarId;

    public User() {
    }

    public User(int id, String name, int avatarId) {
        this.id = id;
        this.name = name;
        this.token = "";
        this.avatarId = avatarId;
    }

    public User(String name, int avatarId) {
        this.name = name;
        this.token = "";
        this.avatarId = avatarId;

    }

    public User(String name) {
        this.name = name;
        this.token = "";
        this.avatarId = 0;
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
        this.token += token;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "User " + name;
    }
}
