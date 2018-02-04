package com.bob.bobmobileapp.tools.UI.views.textviews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.TextView;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.UI.UIUtilsManager;
import com.bob.bobmobileapp.tools.UI.views.MyView;
import com.bob.bobmobileapp.tools.validators.Validator;

import java.lang.reflect.Field;

/**
 * Created by user on 04/10/2017.
 */

public class MyTextView extends MyView {

    protected Validator validator;
    protected TextView textView;
    protected int textColor, errorColor, titleColor;
    protected boolean isBold, isItalic, isUnderline;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void createMainView() {
        this.textView = new TextView(this.getContext());
    }

    @Override
    protected void initMainView() {


        //init
        this.view = this.textView;

        //text
        this.setText(null, this.textView);

        //typeface (font)
        this.setTextTypeface(Typeface.DEFAULT);

        //input type
        this.setTextInputType(InputType.TYPE_CLASS_TEXT);

        //text color
        this.setTextColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));

        //start drawable
        this.setStartDrawableColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));
        this.setStartDrawable(null);
        this.setStartDrawableOnClickListener(null);
        this.setStartDrawableOnFocusOnly(false);
        this.setStartDrawableEnable(false);

        //end drawable
        this.setEndDrawableColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));
        this.setEndDrawable(null);
        this.setEndDrawableOnClickListener(null);
        this.setEndDrawableOnFocusOnly(false);
        this.setEndDrawableEnable(false);

        //bold
        this.setBoldEnable(false);

        //italic
        this.setItalicEnable(false);

        //underline
        this.setUnderlineEnable(false);

        //init on drawables click listeners
        this.setDrawablesOnClickListener(this.startDrawableOnClickListener,
                this.endDrawableOnClickListener,
                this.textView);

        //init drawables on focus change listeners
        this.setDrawablesOnFocusChangeListener(this.startDrawableOnFocusOnly,
                this.endDrawableOnFocusOnly,
                this.textView);




        this.textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                validateTextField(text.toString());
            }

            @Override
            public void afterTextChanged(Editable text) {

            }
        });

        this.textView.setBackground(null);
    }

    protected int getStartDrawableStartMargin() {
        if (this.textView.getCompoundDrawablesRelative()[0] != null) {
            return this.textView.getCompoundDrawablesRelative()[0].getIntrinsicWidth() + UIUtilsManager.get().convertPixelsToDp(this.getContext(), 5);
        } else {
            return UIUtilsManager.get().convertPixelsToDp(this.getContext(), 5);
        }
    }


    //title view
    @Override
    public void setTitleText(String text) {
            this.setHint(text);
    }

    @Override
    public String getTitleText() {
        return this.getHint().toString();
    }

    public void setTitleColor(int color) {
        this.titleColor = color;
        this.paintTitleColor(color);
    }

    protected void paintTitleColor(int color) {
        try {
            Field fDefaultTextColor = TextInputLayout.class.getDeclaredField("mDefaultTextColor");
            fDefaultTextColor.setAccessible(true);
            fDefaultTextColor.set(this, new ColorStateList(new int[][]{{0}}, new int[]{ color }));

            Field fFocusedTextColor = TextInputLayout.class.getDeclaredField("mFocusedTextColor");
            fFocusedTextColor.setAccessible(true);
            fFocusedTextColor.set(this, new ColorStateList(new int[][]{{0}}, new int[]{ color }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //main view (text view)
    public CharSequence getText() {
        return this.getText(this.textView);
    }

    public void setText(String text) {
        this.setText(text, this.textView);
    }

    public void setTextSize(int size) {
        this.setTextSize(size, this.textView);
    }

    public void setTextInputType(int type) {
        this.setTextInputType(type, this.textView);
    }

    public void setTextColor(int color) {
        this.textColor = color;
        this.paintTextColor(color, this.textView);
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        this.view.setOnClickListener(onClickListener);
    }

    public void setStartDrawable(int startDrawable) {
        this.setStartDrawable(ContextCompat.getDrawable(getContext(), startDrawable));
    }

    public void setStartDrawable(Drawable startDrawable) {
        this.startDrawable = startDrawable;
        this.paintStartDrawable(this.startDrawableColor,
                this.startDrawable,
                this.endDrawable,
                this.textView);
    }

    public void setStartDrawableColor(int color) {
        this.startDrawableColor = color;
        this.paintStartDrawable(this.startDrawableColor,
                this.startDrawable,
                this.endDrawable,
                this.textView);
    }

    public void setStartDrawableEnable(boolean drawableEnable) {
        this.makeStartDrawableEnable(drawableEnable, this.textView);
    }

    public void setEndDrawable(int endDrawable) {
        this.setEndDrawable(ContextCompat.getDrawable(getContext(), endDrawable));
    }

    public void setEndDrawable(Drawable endDrawable) {
        this.endDrawable = endDrawable;
        this.paintEndDrawable(this.startDrawableColor,
                this.startDrawable,
                this.endDrawable,
                this.textView);
    }

    public void setEndDrawableColor(int color) {
        this.endDrawableColor = color;
        this.paintEndDrawable(this.startDrawableColor,
                this.startDrawable,
                this.endDrawable,
                this.textView);
    }

    public void setEndDrawableEnable(boolean drawableEnable) {
        this.makeEndDrawableEnable(drawableEnable, this.textView);
    }

    public void setStartDrawableOnClickListener(MyView.DrawableOnClickListener listener) {
        this.startDrawableOnClickListener = listener;
    }

    public void setEndDrawableOnClickListener(MyView.DrawableOnClickListener listener) {
        this.endDrawableOnClickListener = listener;
    }

    public void setStartDrawableOnFocusOnly(boolean startDrawableOnFocusOnly) {
        this.startDrawableOnFocusOnly = startDrawableOnFocusOnly;
    }

    public void setEndDrawableOnFocusOnly(boolean endDrawableOnFocusOnly) {
        this.endDrawableOnFocusOnly = endDrawableOnFocusOnly;
    }

    public void setTextTypeface(Typeface typeface) {
        this.setTextTypeface(typeface, this.textView);
    }

    public void setBoldEnable(boolean isBold) {
        this.isBold = isBold;
        this.makeBoldEnable(this.isBold, this.isItalic, this.textView);
    }

    public void setItalicEnable(boolean isItalic) {
        this.isItalic = isItalic;
        this.makeItalicEnable(this.isItalic, this.isBold, this.textView);
    }

    public void setUnderlineEnable(boolean isUnderline) {
        this.isUnderline = isUnderline;
        this.makeUnderlineEnable(isUnderline, this.textView);
    }


    //validation
    public void validateTextField(String text) {
        if (this.validator != null) {
            String errorMessage = this.validator.validate(text);
            this.setError(errorMessage);
        }
    }


    //validator
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    //error text view
    @Override
    protected void createErrorView() {
        try {
            this.setErrorEnabled(true);
            Field fErrorView = TextInputLayout.class.getDeclaredField("mErrorView");
            fErrorView.setAccessible(true);
            this.errorTextView = (TextView)fErrorView.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            this.errorTextView = new TextView(this.getContext());
            super.addErrorView();
        }
    }

    @Override
    protected void addErrorView() {}

    @Override
    protected void onErrorEnable() {
        super.onErrorEnable();
        this.paintTitleColor(this.errorColor);
        this.paintTextColor(this.errorColor, this.textView);
        this.paintStartDrawable(this.errorColor, this.startDrawable, this.endDrawable, this.textView);
        this.paintEndDrawable(this.errorColor, this.startDrawable, this.endDrawable, this.textView);
        this.paintTextColor(this.errorColor, this.errorTextView);
    }

    @Override
    protected void onErrorDisable() {
        super.onErrorDisable();
        this.paintTitleColor(this.titleColor);
        this.paintTextColor(this.textColor, this.textView);
        this.paintStartDrawable(this.startDrawableColor, this.startDrawable, this.endDrawable, this.textView);
        this.paintEndDrawable(this.endDrawableColor, this.startDrawable, this.endDrawable, this.textView);
        this.paintTextColor(this.errorColor, this.errorTextView);
    }


    //text view getter
    public TextView getTextView() {
        return this.textView;
    }


    //drawable on focus boolean
    @Override
    protected void onFocusChangedListener(final boolean startDrawableOnFocusOnly,
                                          final boolean endDrawableOnFocusOnly,
                                          final TextView textView, boolean hasFocus) {
        validateTextField(((TextView) view).getText().toString());
        super.onFocusChangedListener(startDrawableOnFocusOnly,
                endDrawableOnFocusOnly,
                textView, hasFocus);
    }


    //on text change validation listener
    protected void addValidationOnTextChange(TextView textView) {

        this.textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                validateTextField(text.toString());
            }

            @Override
            public void afterTextChanged(Editable text) {

            }
        });

    }

    //((ViewGroup.MarginLayoutParams)this.errorTextView.getLayoutParams()).setMarginStart(this.getStartDrawableStartMargin());
}
