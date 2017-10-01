package com.bob.bobmobileapp.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bob.bobmobileapp.BOBApplication;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.progressbar.MyProgressBar;
import com.bob.bobmobileapp.tools.progressbar.ProgressBarTimer;
import com.bob.bobmobileapp.tools.style.BackgroundColorTimer;
import com.bob.bobmobileapp.tools.validators.DefaultValidator;
import com.bob.bobmobileapp.tools.validators.Validator;
import com.bumptech.glide.Glide;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.lang.reflect.Field;

/**
 * Created by user on 27/09/2017.
 */


public class LoadingActivity extends AppCompatActivity {

    public static int CHECK_USER = 0;
    public static int LOAD_MENU = 1;

    private ImageView imageView;
    private MyProgressBar progressBar;
    private TextInputLayout textInputLayout;
    private TextInputEditText editText;
    private Drawable codeEditTextDrawable;
    private Validator codeEditTextValidator;
    private BackgroundColorTimer backgroundColorTimer;
    private ProgressBarTimer progressBarTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.loading_layout);

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
        this.textInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout);
        this.editText = (TextInputEditText) findViewById(R.id.code_edit_text);

        this.codeEditTextValidator = new DefaultValidator();
        this.codeEditTextDrawable = new IconicsDrawable(this).icon(FontAwesome.Icon.faw_key).color(ContextCompat.getColor(this, R.color.colorPrimary)).sizeDp(20);

        this.editText.setWidth(700);
        this.editText.setCompoundDrawablesRelativeWithIntrinsicBounds(codeEditTextDrawable, null, null, null);
        this.editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (!editText.getText().equals("") && isOnResetDrawable(editText, motionEvent.getX())
                            && editText.getCompoundDrawablesRelative()[0] != null) {
                        editText.setText("");
                    }
                }
                return false;
            }
        });

        this.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

            }
        });

        this.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
                /*if (!text.equals("")) {
                    codeEditTextDrawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(LoadingActivity.this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN));
                    editText.setCompoundDrawablesRelativeWithIntrinsicBounds(codeEditTextDrawable, null, null, null);
                }*/
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                validateTextField(editText, codeEditTextValidator, text.toString());
            }

            @Override
            public void afterTextChanged(Editable text) {
                //validateTextField(editText, codeEditTextValidator, text.toString());
            }
        });

        this.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateTextField(editText, codeEditTextValidator, ((TextView) v).getText().toString());
                }
            }
        });

        this.editText.setTextColor(ContextCompat.getColor(LoadingActivity.this, R.color.colorPrimary));
        this.editText.getBackground().setColorFilter(ContextCompat.getColor(LoadingActivity.this, R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        this.setErrorTextColor(ContextCompat.getColor(LoadingActivity.this, R.color.colorError));
        this.textInputLayout.setHint("Please enter your activision key");

    }

    private void validateTextField(TextInputEditText editText, Validator validator, String text) {
        if (!validator.isValid(text)) {
            if (text.equals("")) {
                codeEditTextDrawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(LoadingActivity.this, R.color.colorError), PorterDuff.Mode.SRC_IN));
                editText.setCompoundDrawablesRelativeWithIntrinsicBounds(codeEditTextDrawable, null, null, null);
                this.setError("Please enter an activision key!");
            } else {
                Drawable resetDrawable = new IconicsDrawable(this).icon(MaterialDesignIconic.Icon.gmi_close_circle).color(ContextCompat.getColor(this, R.color.colorError)).sizeDp(20);
                resetDrawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(LoadingActivity.this, R.color.colorError), PorterDuff.Mode.SRC_IN));
                codeEditTextDrawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(LoadingActivity.this, R.color.colorError), PorterDuff.Mode.SRC_IN));
                editText.setCompoundDrawablesRelativeWithIntrinsicBounds(codeEditTextDrawable, null, resetDrawable, null);
                setError("Please enter valid activision key!");
            }
        } else if (!text.equals("")) {
            Drawable resetDrawable = new IconicsDrawable(this).icon(MaterialDesignIconic.Icon.gmi_close_circle).color(ContextCompat.getColor(this, R.color.colorPrimary)).sizeDp(20);
            resetDrawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN));
            codeEditTextDrawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN));
            editText.setCompoundDrawablesRelativeWithIntrinsicBounds(codeEditTextDrawable, null, resetDrawable, null);
            setError(null);
        } else {
            codeEditTextDrawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(this, R.color.colorError), PorterDuff.Mode.SRC_IN));
            editText.setCompoundDrawablesRelativeWithIntrinsicBounds(codeEditTextDrawable, null, null, null);
            setError("Please enter an activision key!");
        }
    }

    private boolean isOnResetDrawable(TextInputEditText editText, float x) {
        if (this.getResources().getBoolean(R.bool.is_right_to_left)) {
            if(x <= editText.getTotalPaddingLeft()) {
                return true;
            }
        } else {
            if(x >= editText.getTotalPaddingRight()) {
                return true;
            }
        }
        return false;
    }

    private void setErrorTextColor(int color) {
        if (textInputLayout != null) {
            try {
                Field fErrorView = TextInputLayout.class.getDeclaredField("mErrorView");
                fErrorView.setAccessible(true);
                TextView mErrorView = (TextView) fErrorView.get(textInputLayout);
                Field fCurTextColor = TextView.class.getDeclaredField("mCurTextColor");
                fCurTextColor.setAccessible(true);
                fCurTextColor.set(mErrorView, color);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void setError(String errorLable) {
        if (this.textInputLayout != null) {
            this.textInputLayout.setError(errorLable);
        } else {
            this.editText.setError(errorLable);
        }
    }
}
