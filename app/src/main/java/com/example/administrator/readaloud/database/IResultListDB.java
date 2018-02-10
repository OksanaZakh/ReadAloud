package com.example.administrator.readaloud.database;

import com.example.administrator.readaloud.ui.result.Result;

import java.util.List;

/**
 * Created by Administrator on 06.02.2018.
 */

public interface IResultListDB {

    void addResult(Result result);

    List<Result> getAllResult();

    int getResultCount();

    int updateResult(int id, Result result);

    void deleteResult(int id);

    Result getResult(int id);
}
