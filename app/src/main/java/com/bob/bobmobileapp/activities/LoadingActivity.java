package com.bob.bobmobileapp.activities;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bob.bobmobileapp.BOBApplication;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.fragments.Map;
import com.bob.bobmobileapp.tools.UI.views.recyclerview.MyTextGroup;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyEditText;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;
import com.bob.bobmobileapp.tools.progressbar.MyProgressBar;
import com.bob.bobmobileapp.tools.progressbar.ProgressBarTimer;
import com.bob.bobmobileapp.tools.style.BackgroundColorTimer;
import com.bob.bobmobileapp.tools.validators.DefaultStringValidator;
import com.bob.bobmobileapp.tools.validators.Validator;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import java.io.IOException;
import java.util.List;

import static java.security.AccessController.getContext;

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


        MyTextGroup myCheckGroup = (MyTextGroup) findViewById(R.id.text_group);


        TextView cb1 = new TextView(this);
        cb1.setText("I'm TextView No.1!");
        TextView cb2 = new TextView(this);
        cb2.setText("I'm TextView No.2!");
        TextView cb3 = new TextView(this);
        cb3.setText("I'm TextView No.3!");

        final Context context = this;
        myCheckGroup.add(cb1);
        myCheckGroup.add(cb2);
        myCheckGroup.add(cb3);

        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoadingActivity.this, Map.class);
                startActivity(intent);

            }
        });


        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoadingActivity.this, MapsActivity.class);
                startActivity(intent);

            }
        });

        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoadingActivity.this, MapActivity.class);
                startActivity(intent);

            }
        });


        initLogoImage();
        initProgressBar();
        initForm();
        initBackgroundAnimation();


        if (savedInstanceState != null) {
            if (savedInstanceState.getFloatArray("primaryColor") != null && savedInstanceState.getFloatArray("secondaryColor") != null) {
                this.backgroundColorTimer.setColors(new float[][]{savedInstanceState.getFloatArray("primaryColor"), savedInstanceState.getFloatArray("secondaryColor")});
            }
        }

        Glide.with(this).load("http://www.freeiconspng.com/uploads/hotel-png-0.png").into(imageView);
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

}
