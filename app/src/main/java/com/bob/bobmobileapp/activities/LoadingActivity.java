package com.bob.bobmobileapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bob.bobmobileapp.BOBApplication;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.progressbar.MyProgressBar;
import com.bob.bobmobileapp.tools.progressbar.ProgressBarTimer;
import com.bob.bobmobileapp.tools.style.BackgroundColorTimer;
import com.bumptech.glide.Glide;

/**
 * Created by user on 27/09/2017.
 */


public class LoadingActivity extends AppCompatActivity {

    public static int CHECK_USER = 0;
    public static int LOAD_MENU = 1;

    private ImageView imageView;
    private MyProgressBar progressBar;
    private BackgroundColorTimer backgroundColorTimer;
    private ProgressBarTimer progressBarTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.loading_layout);
        RelativeLayout backgroundLayout = (RelativeLayout) findViewById(R.id.loading_layout_background);
        this.imageView = (ImageView) findViewById(R.id.image_view);
        this.progressBar = (MyProgressBar) findViewById(R.id.progress_bar);

        this.imageView.getLayoutParams().height = 500;
        this.imageView.getLayoutParams().width = 500;

        this.progressBar.getLayoutParams().width = 700;
        this.progressBarTimer = new ProgressBarTimer(this, this.progressBar, 5);

        this.backgroundColorTimer = new BackgroundColorTimer(this, backgroundLayout, ContextCompat.getColor(this, R.color.colorPrimary), ContextCompat.getColor(this, R.color.colorPrimary));
        this.backgroundColorTimer.setColorIntervals(1, 0, 0, 1, 0);
        this.backgroundColorTimer.setColorIntervalRange(1, 0, 0, 30, 0);
        if (savedInstanceState != null) {
            if (savedInstanceState.getFloatArray("primaryColor") != null && savedInstanceState.getFloatArray("secondaryColor") != null) {
                this.backgroundColorTimer.setColors(new float[][]{savedInstanceState.getFloatArray("primaryColor"), savedInstanceState.getFloatArray("secondaryColor")});
            }
        } else {

        }

        Glide.with(this).load("http://www.freeiconspng.com/uploads/hotel-png-0.png").into(imageView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.backgroundColorTimer.startTimer(50);
        this.progressBarTimer.startTimer(500);
    }


    @Override
    protected void onPause() {
        this.backgroundColorTimer.stopTimer();
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloatArray("primaryColor", this.backgroundColorTimer.getColors()[0]);
        outState.putFloatArray("secondaryColor", this.backgroundColorTimer.getColors()[1]);
    }

    private void checkUser() {
        String username = BOBApplication.get().getSecureSharedPreferences().getString("username", "");
        String password = BOBApplication.get().getSecureSharedPreferences().getString("password", "");
        if ((username != "") && (password != "")) {
            this.startActivity(new Intent(LoadingActivity.this, MainActivity.class));
        } else {

        }
    }

    private void makeAction() {
        Intent intent = getIntent();
        int action = intent.getIntExtra("action", 0);

        if (action == CHECK_USER) {
            this.checkUser();
        } else if (action == LOAD_MENU) {

        }

    }

}
