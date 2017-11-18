package com.bob.bobmobileapp.tools.UI;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.validators.Validator;

import java.lang.reflect.Field;

/**
 * Created by user on 04/10/2017.
 */

public class MyTextView extends TextInputLayout{

    protected Drawable startDrawable, endDrawable;
    protected DrawableOnClickListener startDrawableOnClickListener, endDrawableOnClickListener;
    protected Validator validator;
    protected TextView textView, textBottomLine;
    protected int textColor, bottomLineColor, startDrawableColor, endDrawableColor, errorColor, hintColor;
    protected boolean startDrawableOnFocusOnly;
    protected boolean endDrawableOnFocusOnly;
    protected boolean focusIsFirst;
    protected boolean isBold, isItalic, isUnderline;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.startDrawable = null;
        this.endDrawable = null;
        this.startDrawableOnClickListener = null;
        this.endDrawableOnClickListener = null;
        this.validator = null;
        this.focusIsFirst = true;
        this.setStartDrawableOnFocusOnly(false);
        this.setEndDrawableOnFocusOnly(false);

        this.initTextView(context);
        this.initBottomLine(context);
        this.initColors(context);
        this.addViews();
    }

    protected void initTextViewUIObject(Context context) {
        this.textView = new TextView(context);
    }

    protected void initTextView(Context context) {
        initTextViewUIObject(context);


        this.textView.setOnTouchListener(new View.OnTouchListener() {
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
        this.textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (focusIsFirst) {
                    focusIsFirst = false;
                } else {
                    validateTextField(((TextView) view).getText().toString());
                    if (!hasFocus) {
                        Drawable startDrawable = textView.getCompoundDrawablesRelative()[0];
                        Drawable endDrawable = textView.getCompoundDrawablesRelative()[2];
                        if (startDrawableOnFocusOnly) {
                            startDrawable = null;
                        }
                        if (endDrawableOnFocusOnly) {
                            endDrawable = null;
                        }
                        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(startDrawable, null, endDrawable, null);
                    }
                }
            }
        });

        this.textView.setCompoundDrawablePadding(this.asDP(5));
        this.textView.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.transparent));
        this.isBold = false;
        this.isItalic = false;
        this.isUnderline = false;
    }

    protected void initBottomLine(Context context) {
        try {
            FrameLayout.LayoutParams lineLayoutParams = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    this.asDP(2)
            );
            lineLayoutParams.setMargins(0, 0, 0, this.asDP(5));
            if (this.textView.getCompoundDrawablesRelative()[0] != null) {
                lineLayoutParams.setMarginStart(
                        this.textView.getCompoundDrawablesRelative()[0].getIntrinsicWidth() + this.asDP(5)
                );
            } else {
                lineLayoutParams.setMarginStart(0);
            }
            lineLayoutParams.gravity = Gravity.BOTTOM;

            this.textBottomLine = new TextView(this.getContext());
            this.textBottomLine.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.colorPrimary));
            Field fInputFrame =TextInputLayout.class.getDeclaredField("mInputFrame");
            fInputFrame.setAccessible(true);
            FrameLayout mInputFrame = (FrameLayout) fInputFrame.get(this);
            mInputFrame.addView(this.textBottomLine, lineLayoutParams);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initColors(Context context) {
        this.setTextColor(ContextCompat.getColor(context, R.color.textColorPrimary));
        this.setBottomLineColor(ContextCompat.getColor(context, R.color.textColorPrimary));
        this.setStartDrawableColor(ContextCompat.getColor(context, R.color.textColorPrimary));
        this.setEndDrawableColor(ContextCompat.getColor(context, R.color.textColorPrimary));
        this.setHintColor(ContextCompat.getColor(context, R.color.textColorPrimary));
        this.setErrorTextColor(ContextCompat.getColor(context, R.color.colorError));
    }

    protected void addViews() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        this.addView(this.textView, 0, params);
    }


    //validation
    public void validateTextField(String text) {
        if (validator != null) {
            String errorMessage = validator.validate(text);
            if (errorMessage != null) {
                this.paintBottomLineColor(this.errorColor);
                this.paintEndDrawable(this.errorColor);
                this.paintStartDrawable(this.errorColor);
                this.paintErrorTextColor(this.errorColor);
                this.setError(errorMessage);
            } else {
                this.paintBottomLineColor(this.bottomLineColor);
                this.paintEndDrawable(this.endDrawableColor);
                this.paintStartDrawable(this.startDrawableColor);
                this.paintErrorTextColor(this.errorColor);
                this.setError(null);
            }
        }
    }

    //text view
    public void setText(String text) {
        this.textView.setText(text);
    }

    public void setTextSize(int size) {
        this.textView.setTextSize(this.asDP(size));
    }

    public void setTextColor(int color) {
        this.textColor = color;
        this.paintTextColor(color);
    }

    protected void paintTextColor(int color) {
        this.textView.setTextColor(color);
    }

    public void setTextTypeface(Typeface typeface) {
        this.textView.setTypeface(typeface);
    }

    public void setBoldEnable(boolean isBold) {
        this.isBold = isBold;
        if (this.isBold) {
            if (this.isItalic) {
                this.textView.setTypeface(this.textView.getTypeface(), Typeface.BOLD_ITALIC);
            } else {
                this.textView.setTypeface(this.textView.getTypeface(), Typeface.BOLD);
            }
        } else {
            if (this.isItalic) {
                this.textView.setTypeface(this.textView.getTypeface(), Typeface.ITALIC);
            } else {
                this.textView.setTypeface(this.textView.getTypeface(), Typeface.NORMAL);
            }
        }
    }

    public void setItalicEnable(boolean isItalic) {
        this.isItalic = isItalic;
        if (this.isItalic) {
            if (this.isBold) {
                this.textView.setTypeface(this.textView.getTypeface(), Typeface.BOLD_ITALIC);
            } else {
                this.textView.setTypeface(this.textView.getTypeface(), Typeface.ITALIC);
            }
        } else {
            if (this.isBold) {
                this.textView.setTypeface(this.textView.getTypeface(), Typeface.BOLD);
            } else {
                this.textView.setTypeface(this.textView.getTypeface(), Typeface.NORMAL);
            }
        }
    }

    public void setUnderlineEnable(boolean isUnderline) {
        this.isUnderline = isUnderline;
        if (this.isUnderline) {
            this.textView.setPaintFlags(this.textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        } else {
            this.textView.setPaintFlags(this.textView.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
        }
    }

    public void setTextInputType(int type) {
        this.textView.setInputType(type);
    }

    public void setWidth(int width) {
        this.textView.setWidth(width);
        if (this.textView.getCompoundDrawablesRelative()[0] != null) {
            this.textBottomLine.setWidth(width -
                    (this.textView.getCompoundDrawablesRelative()[0].getIntrinsicWidth() + this.asDP(5)));
        } else {
            this.textBottomLine.setWidth(width);
        }
    }

    public void setHeight(int height) {
        this.textView.setHeight(height);
    }

    public CharSequence getText() {
        return this.textView.getText();
    }

    //bottom line
    public void setBottomLineEnable(boolean enable) {
        if (enable) {
            this.textBottomLine.setVisibility(VISIBLE);
        } else {
            this.textBottomLine.setVisibility(INVISIBLE);
        }
    }

    public void setBottomLineColor(int color) {
        this.bottomLineColor = color;
        this.paintBottomLineColor(color);
    }

    protected void paintBottomLineColor(int color) {
        this.textBottomLine.setBackgroundColor(color);
    }

    //drawables
    protected void paintStartDrawable(int color) {
        if (this.startDrawable != null) {
            this.startDrawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        }
        this.textView.setCompoundDrawablesRelativeWithIntrinsicBounds(this.startDrawable, null, this.endDrawable, null);
        if (this.textView.getCompoundDrawablesRelative()[0] != null) {
            ((FrameLayout.LayoutParams)this.textBottomLine.getLayoutParams()).setMarginStart(
                    this.textView.getCompoundDrawablesRelative()[0].getIntrinsicWidth() + this.asDP(5)
            );
        } else {
            ((FrameLayout.LayoutParams)this.textBottomLine.getLayoutParams()).setMarginStart(0);
        }
    }

    public void setStartDrawable(int startDrawable) {
        this.setStartDrawable(ContextCompat.getDrawable(getContext(), startDrawable));
    }

    public void setStartDrawable(Drawable startDrawable) {
        this.startDrawable = startDrawable;
        this.paintStartDrawable(startDrawableColor);
    }

    public void setStartDrawableColor(int color) {
        this.startDrawableColor = color;
        this.paintStartDrawable(this.startDrawableColor);
    }

    public void setStartDrawableEnable(boolean drawableEnable) {
        Drawable[] drawables = this.textView.getCompoundDrawablesRelative();
        if (drawableEnable) {
            this.textView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    drawables[0], drawables[1], drawables[2], drawables[3]
            );
        } else {
            this.textView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null, drawables[1], drawables[2], drawables[3]
            );
        }
    }

    protected void paintEndDrawable(int color) {
        if (this.endDrawable != null) {
            this.endDrawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        }
        this.textView.setCompoundDrawablesRelativeWithIntrinsicBounds(this.startDrawable, null, this.endDrawable, null);
    }

    public void setEndDrawable(int endDrawable) {
        this.setEndDrawable(ContextCompat.getDrawable(getContext(), endDrawable));
    }

    public void setEndDrawable(Drawable endDrawable) {
        this.endDrawable = endDrawable;
        this.paintEndDrawable(endDrawableColor);
    }

    public void setEndDrawableColor(int color) {
        this.endDrawableColor = color;
        this.paintEndDrawable(color);
    }

    public void setEndDrawableEnable(boolean drawableEnable) {
        Drawable[] drawables = this.textView.getCompoundDrawablesRelative();
        if (drawableEnable) {
            this.textView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    drawables[0], drawables[1], drawables[2], drawables[3]
            );
        } else {
            this.textView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    drawables[0], drawables[1], null, drawables[3]
            );
        }
    }

    //drawables on click listeners
    public void setStartDrawableOnClickListener(DrawableOnClickListener listener) {
        this.startDrawableOnClickListener = listener;
    }

    public void setEndDrawableOnClickListener(DrawableOnClickListener listener) {
        this.endDrawableOnClickListener = listener;
    }

    public abstract static class DrawableOnClickListener {

        public abstract void onDrawableClick();

    }

    //drawable on focus boolean
    public void setStartDrawableOnFocusOnly(boolean startDrawableOnFocusOnly) {
        this.startDrawableOnFocusOnly = startDrawableOnFocusOnly;
    }

    public void setEndDrawableOnFocusOnly(boolean endDrawableOnFocusOnly) {
        this.endDrawableOnFocusOnly = endDrawableOnFocusOnly;
    }

    //check if touch on drawable
    protected boolean isOnEndDrawable(TextView editText, float x) {
        if (this.getContext().getResources().getBoolean(R.bool.is_right_to_left)) {
            if ((editText.getCompoundDrawablesRelative()[2] != null) &&
                    (x <= editText.getCompoundDrawablesRelative()[2].getIntrinsicWidth())) {
                return true;
            }
        } else {
            if ((editText.getCompoundDrawablesRelative()[2] != null) &&
                    (x >= editText.getWidth() - editText.getCompoundDrawablesRelative()[2].getIntrinsicWidth())) {
                return true;
            }
        }
        return false;
    }

    protected boolean isOnStartDrawable(TextView editText, float x) {
        if (this.getContext().getResources().getBoolean(R.bool.is_right_to_left)) {
            if ((editText.getCompoundDrawablesRelative()[0] != null) &&
                    (x >= editText.getCompoundDrawablesRelative()[0].getIntrinsicWidth())) {
                return true;
            }
        } else {
            if ((editText.getCompoundDrawablesRelative()[0] != null) &&
                    (x <= editText.getWidth() - editText.getCompoundDrawablesRelative()[0].getIntrinsicWidth())) {
                return true;
            }
        }
        return false;
    }

    //validator
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    //error text view
    public void setError(String text) {
        super.setError(text);
        if (text != null) {
            this.paintUpperHintColor(this.errorColor);
            this.paintTextColor(this.errorColor);
            this.paintStartDrawable(this.errorColor);
            this.paintEndDrawable(this.errorColor);
            this.paintBottomLineColor(this.errorColor);
            this.paintErrorTextColor(this.errorColor);
        } else {
            this.paintUpperHintColor(this.hintColor);
            this.paintTextColor(this.textColor);
            this.paintStartDrawable(this.startDrawableColor);
            this.paintEndDrawable(this.endDrawableColor);
            this.paintBottomLineColor(this.bottomLineColor);
            this.paintErrorTextColor(this.errorColor);
        }
    }

    public void setErrorTextColor(int color) {
        this.errorColor = color;
        this.paintErrorTextColor(color);
    }

    protected void paintErrorTextColor(int color) {
        try {
            this.setErrorEnabled(true);
            Field fErrorView = TextInputLayout.class.getDeclaredField("mErrorView");
            fErrorView.setAccessible(true);
            TextView mErrorView = (TextView)fErrorView.get(this);
            mErrorView.setTextColor(color);
            if (this.textView.getCompoundDrawablesRelative()[0] != null) {
                ((ViewGroup.MarginLayoutParams)mErrorView.getLayoutParams()).setMarginStart(
                        this.textView.getCompoundDrawablesRelative()[0].getIntrinsicWidth() + this.asDP(5)
                );
            } else {
                ((ViewGroup.MarginLayoutParams)mErrorView.getLayoutParams()).setMarginStart(0);
            }
            mErrorView.requestLayout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //set hint color
    public void setHintColor(int color) {
        this.hintColor = color;
        this.paintUpperHintColor(color);
    }

    protected void paintUpperHintColor(int color) {
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

    //text view getter
    public TextView getTextView() {
        return this.textView;
    }

    //int to dp tool
    protected int asDP(int num) {
        return num * ((int)(this.getContext().getResources().getDisplayMetrics().density));
    }
}
