package com.example.administrator.readaloud.api.AvatarsCreator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 02.03.2018.
 */

public class AvatarsList {

    @SerializedName("items")
    @Expose
    private List<Avatar> avatars = null;


    public List<Avatar> getAvatars() {
        return avatars;
    }
}
