package com.example.administrator.readaloud.api.ReadHandler;

import android.app.Activity;
import android.widget.SeekBar;

/**
 * Created by Administrator on 20.03.2018.
 */

public class SeekBarHandler {
    private int progressValue;
    private Thread thread;
    private SeekBar seekBar;
    private Activity activity;

    public SeekBarHandler(Activity activity, SeekBar seekBar) {
        this.progressValue = 0;
        this.activity = activity;
        this.seekBar = seekBar;
    }

    public void runSeekBar() {
        thread = createTimeSeekBarThread();
        thread.start();
    }

    public void pauseSeekBar() {
        thread.interrupt();
    }

    public void restartSeekBar() {
        thread.interrupt();
        progressValue = 0;
        thread = createTimeSeekBarThread();
        thread.start();
    }

    private Thread createTimeSeekBarThread() {
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (progressValue < 480 && !isInterrupted()) {
                        Thread.sleep(125);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressValue++;
                                seekBar.setProgress(progressValue);
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
