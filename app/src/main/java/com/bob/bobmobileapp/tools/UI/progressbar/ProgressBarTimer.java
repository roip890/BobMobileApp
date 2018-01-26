package com.bob.bobmobileapp.tools.UI.progressbar;

import android.app.Activity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 27/09/2017.
 */

public class ProgressBarTimer {

    private Timer timer;
    private Activity activity;
    private MyProgressBar progressBar;
    private int progressInterval;


    public ProgressBarTimer(Activity activity, MyProgressBar progressBar, int progressInterval) {
        this.activity = activity;
        this.progressBar = progressBar;
        this.progressInterval = progressInterval;
        this.timer = new Timer();
   }

    public void runTask() {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                progressBar.incrementProgressBy(progressInterval);
            }
        });
    }

    public void startTimer(int interval) {
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runTask();
            }
        }, 0, interval);
    }

    public void stopTimer() {
        this.timer.cancel();
    }

}
