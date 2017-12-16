package com.bob.bobmobileapp.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bob.bobmobileapp.BOBApplication;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.UI.CustomTextInputLayout;
import com.bob.bobmobileapp.tools.UI.MyButton;
import com.bob.bobmobileapp.tools.UI.MyEditText;
import com.bob.bobmobileapp.tools.UI.MyPhoneEditText;
import com.bob.bobmobileapp.tools.UI.MyTextView;
import com.bob.bobmobileapp.tools.UI.MyTextViewList;
import com.bob.bobmobileapp.tools.progressbar.MyProgressBar;
import com.bob.bobmobileapp.tools.progressbar.ProgressBarTimer;
import com.bob.bobmobileapp.tools.style.BackgroundColorTimer;
import com.bob.bobmobileapp.tools.validators.DefaultValidator;
import com.bob.bobmobileapp.tools.validators.Validator;
import com.bumptech.glide.Glide;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.vstechlab.easyfonts.EasyFonts;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 27/09/2017.
 */


public class LoadingActivity extends AppCompatActivity {

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


        MyPhoneEditText myPhoneEditText = (MyPhoneEditText) findViewById(R.id.phone_view);
        myPhoneEditText.setBottomLineColor(ContextCompat.getColor(this, R.color.colorError));
        myPhoneEditText.setTextColor(ContextCompat.getColor(this, R.color.colorError));
        myPhoneEditText.setCursorColor(ContextCompat.getColor(this, R.color.colorError));
        myPhoneEditText.setText("nananna");


        //MyTextView myEditText = findViewById(R.id.et);
        //myEditText.setText("nanana");
        //myEditText.setBottomLineEnable(false);

        //MyButton myButton = findViewById(R.id.mybtn);
        //Calendar calendar = Calendar.getInstance();
        //calendar.set(Calendar.HOUR, 2);
        //calendar.set(Calendar.MINUTE, 3);
        //calendar.set(Calendar.SECOND, 4);
        //DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        //myButton.setText(dateFormat.format(calendar.getTime()));
        //ViewCompat.setBackgroundTintList(
        //        myButton.getTextView(),
        //        ColorStateList.valueOf(Color.RED));
        //myButton.setBottomLineEnable(false);

        //CustomTextInputLayout til = (CustomTextInputLayout) findViewById(R.id.til);

//        Field fInputFrame = null;
//        try {
//            fInputFrame = TextInputLayout.class.getDeclaredField("mInputFrame");
//            fInputFrame.setAccessible(true);
//            FrameLayout mInputFrame = (FrameLayout) fInputFrame.get(til);
//            EditText et = new EditText(this);
//            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
//                    FrameLayout.LayoutParams.WRAP_CONTENT,
//                    FrameLayout.LayoutParams.MATCH_PARENT
//            );
//            til.addView(et, layoutParams);
//            et.setText("bbb");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        //at.setText("aaa");

        //EditText editText = til.getEditText();


        //Typeface tf = Typeface.create(EasyFonts.funRaiser(this), Typeface.);
        //til.setTypeface(tf);

        //til.setHint("Hint");
        //til.getEditText().setText("Text");
        //til.setCollapsedTypeface(EasyFonts.funRaiser(this));
        //til.setExpandedTypeface(EasyFonts.caviarDreamsBold(this));
        //til.getEditText().setTypeface(EasyFonts.robotoThin(this));
        //til.setUnderline(true);



//        MyTextViewList myTextViewList = (MyTextViewList) findViewById(R.id.text_views_list);
//        myTextViewList.setTitle("title");
//        myTextViewList.addTextView();
//        myTextViewList.addTextView();
//        myTextViewList.addTextView();
//        myTextViewList.setText("a", 0);
//        myTextViewList.setText("b", 1);
//        myTextViewList.setText("c", 2);
//        myTextViewList.setError("error");


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
        this.editText.setValidator(new DefaultValidator());
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
        editText2.setValidator(new DefaultValidator());
        editText2.setEndDrawableOnFocusOnly(true);
        editText2.getTextView().setMaxLines(1);
    }

}
