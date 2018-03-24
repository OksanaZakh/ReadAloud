package com.example.administrator.readaloud.api.AvatarCreator;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 28.02.2018.
 */

public interface ApiService {

    @GET("/services/feeds/photos_public.gne?tags=angry birds&format=json&nojsoncallback=1")
    Call<AvatarsList> getJSON();
}
