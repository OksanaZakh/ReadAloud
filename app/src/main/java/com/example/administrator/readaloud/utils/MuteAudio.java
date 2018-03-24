package com.example.administrator.readaloud.utils;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;

/**
 * Created by Administrator on 10.03.2018.
 */

public class MuteAudio {

    public static void muteAudio(Context context) {
        fillData(context, AudioManager.ADJUST_MUTE, true);
    }

    public static void unMuteAudio(Context context) {
        fillData(context, AudioManager.ADJUST_UNMUTE, false);
    }

    private static void fillData(Context context, int audioStatus, boolean isMute) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            audioManager.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, audioStatus, 0);
            audioManager.adjustStreamVolume(AudioManager.STREAM_ALARM, audioStatus, 0);
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, audioStatus, 0);
            audioManager.adjustStreamVolume(AudioManager.STREAM_RING, audioStatus, 0);
            audioManager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, audioStatus, 0);
        } else {
            audioManager.setStreamMute(AudioManager.STREAM_NOTIFICATION, isMute);
            audioManager.setStreamMute(AudioManager.STREAM_ALARM, isMute);
            audioManager.setStreamMute(AudioManager.STREAM_MUSIC, isMute);
            audioManager.setStreamMute(AudioManager.STREAM_RING, isMute);
            audioManager.setStreamMute(AudioManager.STREAM_SYSTEM, isMute);
        }
    }

}
