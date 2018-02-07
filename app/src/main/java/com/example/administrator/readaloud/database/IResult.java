package com.example.administrator.readaloud.database;

import java.util.List;

/**
 * Created by Administrator on 06.02.2018.
 */

public interface IResult {

    void addResult(Result result);

    List<Result> getAllResult();

    int getResultCount();

    int updateResult(int id, Result result);

    void deleteResult(int id);

    Result getBestVelocityResult(int userId);

    Result getBestQualityResult(int userId);

    Result getResult(int id);
}
