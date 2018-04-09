package com.example.administrator.readaloud.databaseservice;

import com.example.administrator.readaloud.app.ui.welcome.UserModel;

import java.util.List;

/**
 * Created by Administrator on 06.02.2018.
 */

public interface IUserListDB {

    void addUser(UserModel userModel);

    boolean isUserInBase(String name);

    List<UserModel> getAllUsers();

    int getUserCount();

    int updateUser(int id, UserModel userModel);

    void deleteUser(int id);

    UserModel getUser(String name);

    void makeLogOut(String name);
}
