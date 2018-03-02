package com.example.administrator.readaloud.app.ui.welcome.AvatarsCreator;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 28.02.2018.
 */

public class RetroClient {

    public static final String ROOT_URL = "https://api.flickr.com";

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
