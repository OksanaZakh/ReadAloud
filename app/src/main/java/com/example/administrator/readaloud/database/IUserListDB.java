package com.example.administrator.readaloud.database;

import com.example.administrator.readaloud.ui.welcome.User;

import java.util.List;

/**
 * Created by Administrator on 06.02.2018.
 */

public interface IUserListDB {

    void addUser(User user);

    boolean isUserInBase(String name);

    List<User> getAllUsers();

    int getUserCount();

    int updateUser(int id, User user);

    void deleteUser(int id);

    int getUserId(String name);
}
