package com.example.administrator.readaloud.app.ui.welcome.AvatarsCreator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 02.03.2018.
 */

public class Avatar {

    @SerializedName("media")
    @Expose
    private Media media;


    public Media getMedia() {
        return media;
    }

}
