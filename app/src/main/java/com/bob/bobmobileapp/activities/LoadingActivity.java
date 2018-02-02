package com.bob.bobmobileapp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bob.bobmobileapp.BOBApplication;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.UI.views.mediaviews.MyVideoView;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyEditText;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;
import com.bob.bobmobileapp.tools.UI.progressbar.MyProgressBar;
import com.bob.bobmobileapp.tools.UI.progressbar.ProgressBarTimer;
import com.bob.bobmobileapp.tools.UI.style.BackgroundColorTimer;
import com.bob.bobmobileapp.tools.validators.DefaultStringValidator;
import com.bob.bobmobileapp.tools.validators.Validator;
import com.bumptech.glide.Glide;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import java.io.ByteArrayOutputStream;

/**
 * Created by user on 27/09/2017.
 */


public class LoadingActivity extends AppCompatActivity{

    public static int CHECK_USER = 0;
    public static int LOAD_MENU = 1;

    private ImageView imageView;
    private MyProgressBar progressBar;
    private MyEditText editText;
    private Drawable codeStartEditTextDrawable, codeEndEditTextDrawable;
    private Validator codeEditTextValidator;
    private BackgroundColorTimer backgroundColorTimer;
    private ProgressBarTimer progressBarTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.loading_layout);


//        MyVideoView imageView2 = (MyVideoView) findViewById(R.id.vid_view);
//        imageView2.setVideoUri("http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_2mb.mp4");
//        imageView2.setYoutubeVideoByID("WLMmX17D2uw");
//        imageView2.setVideoUri("https://s3-us-west-2.amazonaws.com/avnohellodev/videos/9e867aba83197862e7500ebe3ebfd68a.mp4");
//        imageView2.setYoutubeVideoByID("668nUCeBHyY");



        initLogoImage();
        initProgressBar();
        initForm();
        initBackgroundAnimation();


        if (savedInstanceState != null) {
            if (savedInstanceState.getFloatArray("primaryColor") != null && savedInstanceState.getFloatArray("secondaryColor") != null) {
                this.backgroundColorTimer.setColors(new float[][]{savedInstanceState.getFloatArray("primaryColor"), savedInstanceState.getFloatArray("secondaryColor")});
            }
        }

        Glide.with(this).load("http://www.freeiconspng.com/uploads/hotel-png-0.png").into(this.imageView);
    }

    private void initLogoImage() {
        this.imageView = (ImageView) findViewById(R.id.image_view);

        this.imageView.getLayoutParams().height = 500;
        this.imageView.getLayoutParams().width = 500;
    }

    private void initProgressBar() {
        this.progressBar = (MyProgressBar) findViewById(R.id.progress_bar);

        this.progressBar.getLayoutParams().width = 700;
        this.progressBarTimer = new ProgressBarTimer(this, this.progressBar, 5);
        this.progressBar.setVisibility(View.INVISIBLE);
    }

    private void initBackgroundAnimation() {
        RelativeLayout backgroundLayout = (RelativeLayout) findViewById(R.id.loading_layout_background);

        this.backgroundColorTimer = new BackgroundColorTimer(this, backgroundLayout, ContextCompat.getColor(this, R.color.colorPrimary), ContextCompat.getColor(this, R.color.colorPrimary));
        this.backgroundColorTimer.setColorIntervals(1, 0, 0, 1, 0);
        this.backgroundColorTimer.setColorIntervalRange(1, 0, 0, 30, 0);
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


    private void initForm() {
        this.editText = (MyEditText) findViewById(R.id.code_edit_text);
        //this.editText.setText("");
        this.editText.setWidth(700);
        this.editText.setStartDrawable(new IconicsDrawable(this).icon(FontAwesome.Icon.faw_key).sizeDp(20));
        this.editText.setEndDrawable(new IconicsDrawable(this).icon(MaterialDesignIconic.Icon.gmi_close_circle).sizeDp(20));
        this.editText.setEndDrawableOnClickListener(new MyTextView.DrawableOnClickListener() {
            @Override
            public void onDrawableClick() {
                if (!editText.getText().toString().equals("")) {
                    editText.setText("");
                    editText.setEndDrawableEnable(false);
                }
            }
        });
        this.editText.setHint("Please enter your code");

        this.editText.setEndDrawableColor(ContextCompat.getColor(this, R.color.colorAccent));
        this.editText.setStartDrawableColor(ContextCompat.getColor(this, R.color.colorAccent));
        this.editText.setBottomLineColor(ContextCompat.getColor(this, R.color.colorAccent));
        this.editText.setCursorColor(ContextCompat.getColor(this, R.color.colorAccent));
        this.editText.setTitleColor(ContextCompat.getColor(this, R.color.colorAccent));
        this.editText.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        this.editText.setErrorTextColor(ContextCompat.getColor(this, R.color.colorError));
        this.editText.setValidator(new DefaultStringValidator());
        this.editText.setEndDrawableOnFocusOnly(true);
        this.editText.getTextView().setMaxLines(1);

























        final MyEditText editText2 = (MyEditText) findViewById(R.id.code_edit_text2);
        //this.editText.setText("");
        editText2.setWidth(700);
        editText2.setStartDrawable(new IconicsDrawable(this).icon(FontAwesome.Icon.faw_key).sizeDp(20));
        editText2.setEndDrawable(new IconicsDrawable(this).icon(MaterialDesignIconic.Icon.gmi_close_circle).sizeDp(20));
        editText2.setEndDrawableOnClickListener(new MyTextView.DrawableOnClickListener() {
            @Override
            public void onDrawableClick() {
                if (!editText2.getText().toString().equals("")) {
                    editText2.setText("");
                    editText2.setEndDrawableEnable(false);
                }
            }
        });
        editText2.setHint("Please enter your code");

        editText2.setEndDrawableColor(ContextCompat.getColor(this, R.color.colorAccent));
        editText2.setStartDrawableColor(ContextCompat.getColor(this, R.color.colorAccent));
        editText2.setBottomLineColor(ContextCompat.getColor(this, R.color.colorAccent));
        editText2.setCursorColor(ContextCompat.getColor(this, R.color.colorAccent));
        editText2.setTitleColor(ContextCompat.getColor(this, R.color.colorAccent));
        editText2.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        editText2.setErrorTextColor(ContextCompat.getColor(this, R.color.colorError));
        editText2.setValidator(new DefaultStringValidator());
        editText2.setEndDrawableOnFocusOnly(true);
        editText2.getTextView().setMaxLines(1);
    }

    private byte[] bitmapToByte(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    //int to dp tool
    protected int asDP(int num) {
        return num * ((int)(this.getResources().getDisplayMetrics().density));
    }

}
