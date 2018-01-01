package com.bob.bobmobileapp.tools.UI.views;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bob.bobmobileapp.R;

import java.lang.reflect.Field;

/**
 * Created by User on 07/12/2017.
 */

public abstract class MyView extends TextInputLayout {


    //general
    protected int mainViewIndex, bottomLineIndex, errorIndex;
    protected int startDrawableColor, endDrawableColor;
    protected Drawable startDrawable, endDrawable;
    protected DrawableOnClickListener startDrawableOnClickListener, endDrawableOnClickListener;
    protected boolean startDrawableOnFocusOnly, endDrawableOnFocusOnly;

    //main container
    protected ViewGroup mainContainer;

    //main view
    protected View view;
    protected Drawable backgroundDrawable;
    protected int backgroundColor;

    //bottom line
    protected View bottomLine;
    protected int bottomLineSize, bottomLineColor;

    //error
    protected TextView errorTextView;
    protected int errorColor, errorStartDrawableColor, errorEndDrawableColor;
    protected boolean errorIsBold, errorIsUnderline, errorIsItalic;
    protected Drawable errorStartDrawable, errorEndDrawable;
    protected MyView.DrawableOnClickListener errorStartDrawableOnClickListener, errorEndDrawableOnClickListener;
    protected boolean errorStartDrawableOnFocusOnly, errorEndDrawableOnFocusOnly;


    //constructors
    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initViewsOrder();
        createViews();
        initViews();
        addViews();

    }

    //initialization
    protected void initViewsOrder() {

        this.mainViewIndex = 0;
        this.bottomLineIndex = 1;
        this.errorIndex = 2;


    }

    protected void initView() {

        //init layout params
        ViewGroup.LayoutParams layoutParams = this.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = LayoutParams.WRAP_CONTENT;
            layoutParams.width = LayoutParams.WRAP_CONTENT;
        }

        //set default background image as null
        this.setBackgroundImage(null);

        //set default background color as transparent
        this.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.transparent));
    }

    protected void initMainContainer() {

        try {
            //try to get the frame layout inside the TextInputLayout
            Field fInputFrame =TextInputLayout.class.getDeclaredField("mInputFrame");
            fInputFrame.setAccessible(true);
            this.mainContainer = (FrameLayout) fInputFrame.get(this);
        } catch (Exception e) {
            //if can't get the container take 'this' object as the container
            this.mainContainer = this;
        }

    }

    protected abstract void createMainView();

    protected abstract void initMainView();

    protected void addMainView() {

        //init layout params
        FrameLayout.LayoutParams viewLayoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );

        //add main view
        this.mainContainer.addView(this.view, this.mainViewIndex, viewLayoutParams);

    }

    protected void createBottomLine() {

        this.bottomLine = new View(this.getContext());

    }

    protected void initBottomLine() {

        //set default bottom line size
        this.setBottomLineSize(this.asDP(2));

        //set default bottom line color
        this.setBottomLineColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));

        //set bottom line disable by default
        this.setBottomLineEnable(false);

    }

    protected void addBottomLine() {

        //init layout params
        FrameLayout.LayoutParams lineLayoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                this.bottomLineSize
        );

        //set bottom margin
        lineLayoutParams.setMargins(0, 0, 0, this.asDP(5));

        //gravity
        lineLayoutParams.gravity = Gravity.BOTTOM;

        //add bottom line
        this.mainContainer.addView(this.bottomLine, this.bottomLineIndex, lineLayoutParams);

    }

    protected void createErrorView() {

        this.errorTextView = new TextView(this.getContext());

    }

    protected void initErrorView() {

        //text
        this.setErrorText(null);

        //typeface (font)
        this.setErrorTextTypeface(Typeface.DEFAULT);

        //input type
        this.setErrorTextInputType(InputType.TYPE_CLASS_TEXT);

        //text color
        this.setErrorTextColor(ContextCompat.getColor(this.getContext(), R.color.colorError));

        //start drawable
        this.setErrorStartDrawableColor(ContextCompat.getColor(this.getContext(), R.color.colorError));
        this.setErrorStartDrawable(null);
        this.setErrorStartDrawableOnClickListener(null);
        this.setErrorStartDrawableOnFocusOnly(false);
        this.setErrorStartDrawableEnable(false);

        //end drawable
        this.setErrorEndDrawableColor(ContextCompat.getColor(this.getContext(), R.color.colorError));
        this.setErrorEndDrawable(null);
        this.setErrorEndDrawableOnClickListener(null);
        this.setErrorEndDrawableOnFocusOnly(false);
        this.setErrorEndDrawableEnable(false);

        //bold
        this.setErrorBoldEnable(false);

        //italic
        this.setErrorItalicEnable(false);

        //underline
        this.setErrorUnderlineEnable(false);

        //init on drawables click listeners
        this.setDrawablesOnClickListener(this.errorStartDrawableOnClickListener,
                this.errorEndDrawableOnClickListener,
                this.errorTextView);

        //init drawables on focus change listeners
        this.setDrawablesOnFocusChangeListener(this.errorStartDrawableOnFocusOnly,
                this.errorEndDrawableOnFocusOnly,
                this.errorTextView);

    }

    protected void addErrorView() {

        int i = this.getChildCount();

        //add error view with default layout params
        this.addView(this.errorTextView, this.errorIndex, new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        ));

    }

    protected void createViews() {

        //create all views
        createMainView();
        createBottomLine();
        createErrorView();

    }

    protected void initViews() {

        //init all views
        initView();
        initMainContainer();
        initMainView();
        initBottomLine();
        initErrorView();

    }

    protected void addViews() {

        //add all views
        addMainView();
        addBottomLine();
        addErrorView();

    }


    //general
    public void setWidth(int width) {
        ViewGroup.LayoutParams layoutParams = this.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = width;
        }
    }

    public void setHeight(int height) {
        ViewGroup.LayoutParams layoutParams = this.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = height;
        }
    }

    protected void updateViewsMargins() {

        //bottom line margin update
        FrameLayout.LayoutParams bottomLineLayoutParams = (FrameLayout.LayoutParams)this.bottomLine.getLayoutParams();
        if (bottomLineLayoutParams != null) {
            bottomLineLayoutParams.setMarginStart(this.getStartDrawableStartMargin());
        }

        //error view margin update
        LinearLayout.LayoutParams errorViewLayoutParams = (LinearLayout.LayoutParams)this.errorTextView.getLayoutParams();
        if (errorViewLayoutParams != null) {
            errorViewLayoutParams.setMarginStart(this.getStartDrawableStartMargin());
        }

        //main view margin update
        FrameLayout.LayoutParams mainViewLayoutParams = (FrameLayout.LayoutParams)this.view.getLayoutParams();
        if (mainViewLayoutParams != null) {
            mainViewLayoutParams.setMarginStart(this.getStartDrawableStartMargin());
        }


    }



    //title view
    public abstract void setTitleText(String text);

    public abstract String getTitleText();


    //main view
    public abstract void setStartDrawable(int startDrawable);

    public abstract void setStartDrawable(Drawable startDrawable);

    public abstract void setStartDrawableColor(int color);

    public abstract void setStartDrawableEnable(boolean drawableEnable);

    public abstract void setStartDrawableOnClickListener(MyView.DrawableOnClickListener listener);

    public abstract void setStartDrawableOnFocusOnly(boolean startDrawableOnFocusOnly);

    public abstract void setEndDrawable(int endDrawable);

    public abstract void setEndDrawable(Drawable endDrawable);

    public abstract void setEndDrawableColor(int color);

    public abstract void setEndDrawableEnable(boolean drawableEnable);

    public abstract void setEndDrawableOnClickListener(MyView.DrawableOnClickListener listener);

    public abstract void setEndDrawableOnFocusOnly(boolean endDrawableOnFocusOnly);


    //bottom line
    protected void setBottomLineEnable(boolean enable) {
        if (enable) {
            this.bottomLine.setVisibility(VISIBLE);
        } else {
            this.bottomLine.setVisibility(INVISIBLE);
        }
    }

    public void setBottomLineColor(int color) {
        this.bottomLineColor = color;
        this.paintBottomLineColor(color);
    }

    protected void paintBottomLineColor(int color) {
        this.bottomLine.setBackgroundColor(color);
    }

    public int getBottomLineSize() {
        return bottomLineSize;
    }

    public void setBottomLineSize(int bottomLineSize) {
        this.bottomLineSize = bottomLineSize;
    }

    protected int getBottomLineBottomPadding() {
        return this.asDP(5);
    }


    //error view
    public void setError(String text) {
        this.setText(text, this.errorTextView);
        if (text != null) {
            this.onErrorEnable();
        } else {
            this.onErrorDisable();
        }
    }

    protected void onErrorEnable() {
        this.paintBottomLineColor(this.errorColor);
    }

    protected void onErrorDisable() {
        this.paintBottomLineColor(this.bottomLineColor);
    }

    public void setErrorText(String errorText) {
        this.errorTextView.setText(errorText);
    }

    public CharSequence getErrorText() {
        return this.getText(this.errorTextView);
    }

    public void setErrorTextSize(int size) {
        this.setTextSize(size, this.errorTextView);
    }

    public void setErrorTextInputType(int type) {
        this.setTextInputType(type, this.errorTextView);
    }

    public void setErrorTextColor(int color) {
        this.errorColor = color;
        this.paintTextColor(color, this.errorTextView);
    }

    public void setErrorStartDrawable(int startDrawable) {
        this.setErrorStartDrawable(ContextCompat.getDrawable(getContext(), startDrawable));
    }

    public void setErrorStartDrawable(Drawable startDrawable) {
        this.errorStartDrawable = startDrawable;
        this.paintStartDrawable(this.errorStartDrawableColor,
                this.errorStartDrawable,
                this.errorEndDrawable,
                this.errorTextView);
    }

    public void setErrorStartDrawableColor(int color) {
        this.errorStartDrawableColor = color;
        this.paintStartDrawable(this.errorStartDrawableColor,
                this.errorStartDrawable,
                this.errorEndDrawable,
                this.errorTextView);
    }

    public void setErrorStartDrawableEnable(boolean drawableEnable) {
        this.makeStartDrawableEnable(drawableEnable, this.errorTextView);
    }

    public void setErrorEndDrawable(int endDrawable) {
        this.setErrorEndDrawable(ContextCompat.getDrawable(getContext(), endDrawable));
    }

    public void setErrorEndDrawable(Drawable endDrawable) {
        this.errorEndDrawable = endDrawable;
        this.paintEndDrawable(this.errorStartDrawableColor,
                this.errorStartDrawable,
                this.errorEndDrawable,
                this.errorTextView);
    }

    public void setErrorEndDrawableColor(int color) {
        this.errorEndDrawableColor = color;
        this.paintEndDrawable(this.errorStartDrawableColor,
                this.errorStartDrawable,
                this.errorEndDrawable,
                this.errorTextView);
    }

    public void setErrorEndDrawableEnable(boolean drawableEnable) {
        this.makeEndDrawableEnable(drawableEnable, this.errorTextView);
    }

    public void setErrorStartDrawableOnClickListener(MyView.DrawableOnClickListener listener) {
        this.errorStartDrawableOnClickListener = listener;
    }

    public void setErrorEndDrawableOnClickListener(MyView.DrawableOnClickListener listener) {
        this.errorEndDrawableOnClickListener = listener;
    }

    public void setErrorStartDrawableOnFocusOnly(boolean startDrawableOnFocusOnly) {
        this.errorStartDrawableOnFocusOnly = startDrawableOnFocusOnly;
    }

    public void setErrorEndDrawableOnFocusOnly(boolean endDrawableOnFocusOnly) {
        this.errorEndDrawableOnFocusOnly = endDrawableOnFocusOnly;
    }

    public void setErrorTextTypeface(Typeface typeface) {
        this.setTextTypeface(typeface, this.errorTextView);
    }

    public void setErrorBoldEnable(boolean isBold) {
        this.errorIsBold = isBold;
        this.makeBoldEnable(this.errorIsBold, this.errorIsItalic, this.errorTextView);
    }

    public void setErrorItalicEnable(boolean isItalic) {
        this.errorIsItalic = isItalic;
        this.makeItalicEnable(this.errorIsItalic, this.errorIsBold, this.errorTextView);
    }

    public void setErrorUnderlineEnable(boolean isUnderline) {
        this.errorIsUnderline = isUnderline;
        this.makeUnderlineEnable(isUnderline, this.errorTextView);
    }


    //background
    public void setBackgroundImage(int backgroundDrawable) {
        this.setBackground(ContextCompat.getDrawable(getContext(), backgroundDrawable));
    }

    public void setBackgroundImage(Drawable backgroundDrawable) {
        this.backgroundDrawable = backgroundDrawable;
        this.setBackground(backgroundDrawable);
    }

    public void setBackgroundColor(int color) {
        this.backgroundColor = color;
        super.setBackgroundColor(color);
    }


    //text views
    protected void setText(String text, TextView textView) {
        textView.setText(text);
    }

    protected CharSequence getText(TextView textView) {
        return textView.getText();
    }

    protected void setTextSize(int size, TextView textView) {
        textView.setTextSize(this.asDP(size));
    }

    protected void paintTextColor(int color, TextView textView) {
        textView.setTextColor(color);
    }

    protected void setTextTypeface(Typeface typeface, TextView textView) {
        textView.setTypeface(typeface);
    }

    protected void makeBoldEnable(boolean isBold, boolean isItalic, TextView textView) {
        if (isBold) {
            if (isItalic) {
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD_ITALIC);
            } else {
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
            }
        } else {
            if (isItalic) {
                textView.setTypeface(textView.getTypeface(), Typeface.ITALIC);
            } else {
                textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
            }
        }
    }

    public void makeItalicEnable(boolean isItalic, boolean isBold, TextView textView) {
        if (isItalic) {
            if (isBold) {
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD_ITALIC);
            } else {
                textView.setTypeface(textView.getTypeface(), Typeface.ITALIC);
            }
        } else {
            if (isBold) {
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
            } else {
                textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
            }
        }
    }

    public void makeUnderlineEnable(boolean isUnderline, TextView textView) {
        if (isUnderline) {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        } else {
            textView.setPaintFlags(textView.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
        }
    }

    protected void setTextInputType(int type, TextView textView) {
        textView.setInputType(type);
    }

    //drawables
    protected void paintStartDrawable(int color, Drawable startDrawable, Drawable endDrawable, TextView textView) {
        if (startDrawable != null) {
            startDrawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        }
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(startDrawable, null, endDrawable, null);
        updateViewsMargins();
    }

    protected void makeStartDrawableEnable(boolean drawableEnable, TextView textView) {
        Drawable[] drawables = textView.getCompoundDrawablesRelative();
        if (drawableEnable) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    drawables[0], drawables[1], drawables[2], drawables[3]
            );
        } else {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null, drawables[1], drawables[2], drawables[3]
            );
        }
    }

    protected void paintEndDrawable(int color, Drawable startDrawable, Drawable endDrawable, TextView textView) {
        if (endDrawable != null) {
            endDrawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        }
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(startDrawable, null, endDrawable, null);
    }

    protected void makeEndDrawableEnable(boolean drawableEnable, TextView textView) {
        Drawable[] drawables = textView.getCompoundDrawablesRelative();
        if (drawableEnable) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    drawables[0], drawables[1], drawables[2], drawables[3]
            );
        } else {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    drawables[0], drawables[1], null, drawables[3]
            );
        }
    }

    protected int getStartDrawableStartMargin() {
        if (this.startDrawable != null) {
            return this.startDrawable.getIntrinsicWidth() + this.asDP(5);
        } else {
            return this.asDP(5);
        }
    }


    //drawables on click listeners
    public abstract static class DrawableOnClickListener {

        public abstract void onDrawableClick();

    }

    protected void setDrawablesOnClickListener(final DrawableOnClickListener startDrawableOnClickListener,
                                                final DrawableOnClickListener endDrawableOnClickListener,
                                                final TextView textView) {
        textView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (isOnEndDrawable(textView, motionEvent.getX()) && textView.getCompoundDrawablesRelative()[2] != null) {
                        if (endDrawableOnClickListener != null) {
                            endDrawableOnClickListener.onDrawableClick();
                        }
                    } else if (isOnStartDrawable(textView, motionEvent.getX())&& textView.getCompoundDrawablesRelative()[0] != null) {
                        if (startDrawableOnClickListener != null) {
                            startDrawableOnClickListener.onDrawableClick();
                        }
                    }
                }
                return false;
            }
        });

    }

    //drawable on focus boolean
    protected void setDrawablesOnFocusChangeListener(final boolean startDrawableOnFocusOnly,
                                                      final boolean endDrawableOnFocusOnly,
                                                      final TextView textView) {
        textView.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                onFocusChangedListener(startDrawableOnFocusOnly,
                        endDrawableOnFocusOnly,
                        textView, hasFocus);
            }
        });
    }

    protected void onFocusChangedListener(final boolean startDrawableOnFocusOnly,
                                          final boolean endDrawableOnFocusOnly,
                                          final TextView textView, boolean hasFocus) {
        if (!hasFocus) {
            if (startDrawableOnFocusOnly) {
                makeStartDrawableEnable(false, textView);
            }
            if (endDrawableOnFocusOnly) {
                makeEndDrawableEnable(false, textView);
            }
        } else {
            makeStartDrawableEnable(true, textView);
            makeEndDrawableEnable(true, textView);
        }

    }


    //check if touch on drawable
    protected boolean isOnEndDrawable(TextView textView, float x) {
        if (this.getContext().getResources().getBoolean(R.bool.is_right_to_left)) {
            if ((textView.getCompoundDrawablesRelative()[2] != null) &&
                    (x <= textView.getCompoundDrawablesRelative()[2].getIntrinsicWidth())) {
                return true;
            }
        } else {
            if ((textView.getCompoundDrawablesRelative()[2] != null) &&
                    (x >= textView.getWidth() - textView.getCompoundDrawablesRelative()[2].getIntrinsicWidth())) {
                return true;
            }
        }
        return false;
    }

    protected boolean isOnStartDrawable(TextView textView, float x) {
        if (this.getContext().getResources().getBoolean(R.bool.is_right_to_left)) {
            if ((textView.getCompoundDrawablesRelative()[0] != null) &&
                    (x >= textView.getCompoundDrawablesRelative()[0].getIntrinsicWidth())) {
                return true;
            }
        } else {
            if ((textView.getCompoundDrawablesRelative()[0] != null) &&
                    (x <= textView.getWidth() - textView.getCompoundDrawablesRelative()[0].getIntrinsicWidth())) {
                return true;
            }
        }
        return false;
    }


    //int to dp tool
    protected int asDP(int num) {
        return num * ((int)(this.getContext().getResources().getDisplayMetrics().density));
    }

}
