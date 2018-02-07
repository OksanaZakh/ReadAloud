package com.example.administrator.readaloud.database;

import java.util.List;

/**
 * Created by Administrator on 06.02.2018.
 */

public interface IUser {

    void addUser(User user);

    boolean isUserInBase(String name);

    List<User> getAllUsers();

    int getUserCount();

    int updateUser(int id, User user);

    void deleteUser(int id);

    int getUserId(String name);
}
