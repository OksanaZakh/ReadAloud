package com.example.administrator.readaloud.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

import java.util.Objects;

/**
 * Created by Administrator on 28.02.2018.
 */

public class InternetConnection {

    public static boolean checkConnection(@NonNull Context context) {
        return ((ConnectivityManager) Objects.requireNonNull(context.getSystemService
                (Context.CONNECTIVITY_SERVICE))).getActiveNetworkInfo() != null;
    }
}
