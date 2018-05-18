package com.example.administrator.readaloud.api.ReadHandler;

import android.app.Activity;
import android.widget.SeekBar;

import com.example.administrator.readaloud.app.ui.read.ReadSectionFragment;

/**
 * Created by Administrator on 20.03.2018.
 */

public class SeekBarHandler {
    private int progressValue;
    private Thread thread;
    private SeekBar seekBar;
    private Activity activity;
    public OnSpeechListener onSpeechListener;

    public interface OnSpeechListener {
        void onSpeechListen(ReadSectionFragment.ReadingStatus status);
    }

    public SeekBarHandler(Activity activity, SeekBar seekBar, OnSpeechListener onSpeechListener) {
        this.progressValue = 0;
        this.activity = activity;
        this.seekBar = seekBar;
        this.onSpeechListener = onSpeechListener;
    }

    public void runSeekBar() {
        thread = createTimeSeekBarThread();
        thread.start();
        onSpeechListener.onSpeechListen(ReadSectionFragment.ReadingStatus.ON);
    }

    public void pauseSeekBar() {
        thread.interrupt();
        onSpeechListener.onSpeechListen(ReadSectionFragment.ReadingStatus.PAUSE);
    }

    public void restartSeekBar() {
        if (thread != null) {
            thread.interrupt();
        }
        progressValue = 0;
        seekBar.setProgress(progressValue);
        onSpeechListener.onSpeechListen(ReadSectionFragment.ReadingStatus.RESTART);
        onSpeechListener.onSpeechListen(ReadSectionFragment.ReadingStatus.OFF);
    }

    private Thread createTimeSeekBarThread() {
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (progressValue <= 480 && !isInterrupted()) {
                        Thread.sleep(125);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressValue++;
                                seekBar.setProgress(progressValue);
                                if (progressValue == 480) {
                                    onSpeechListener.onSpeechListen(ReadSectionFragment.ReadingStatus.OFF);
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        return t;
    }
}
