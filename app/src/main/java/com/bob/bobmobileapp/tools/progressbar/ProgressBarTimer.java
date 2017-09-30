package com.bob.bobmobileapp.tools.progressbar;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 27/09/2017.
 */

public class ProgressBarTimer extends TimerTask {

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

    public void run() {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                progressBar.incrementProgressBy(progressInterval);
            }
        });
    }

    public void startTimer(int interval) {
        this.timer.schedule(this, 0, interval);
    }

    public void stopTimer() {
        this.timer.cancel();
    }

}
