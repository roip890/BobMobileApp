package com.bob.bobmobileapp.tools.UI;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bob.bobmobileapp.BOBApplication;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.validators.ViewsValidator;

import java.util.ArrayList;

/**
 * Created by user on 04/10/2017.
 */

public class MyTextViewList extends LinearLayout {

    //constants
    public final int ALL_TEXT_VIEWS = -1;
    public final int TITLE_TEXT_VIEW = -2;
    public final int ERROR_TEXT_VIEW = -3;

    //general
    protected LinearLayout textViewsListView;
    protected TextView bottomLine;
    protected ViewsValidator validator;
    protected int bottomLineColor;
    protected Drawable backgroundDrawable;
    protected int backgroundColor;

    //title
    protected TextView titleTextView;
    protected int titleColor, titleStartDrawableColor, titleEndDrawableColor;
    protected boolean titleIsBold, titleIsUnderline, titleIsItalic;
    protected Drawable titleStartDrawable, titleEndDrawable;
    protected DrawableOnClickListener titleStartDrawableOnClickListener, titleEndDrawableOnClickListener;
    protected boolean titleStartDrawableOnFocusOnly;
    protected boolean titleEndDrawableOnFocusOnly;


    //error
    protected TextView errorTextView;
    protected int errorColor, errorStartDrawableColor, errorEndDrawableColor;
    protected boolean errorIsBold, errorIsUnderline, errorIsItalic;
    protected Drawable errorStartDrawable, errorEndDrawable;
    protected DrawableOnClickListener errorStartDrawableOnClickListener, errorEndDrawableOnClickListener;
    protected boolean errorStartDrawableOnFocusOnly;
    protected boolean errorEndDrawableOnFocusOnly;

    //text views
    protected ArrayList<TextView> textViews;
    protected ArrayList<Integer> textViewsColors, textViewsStartDrawableColors, textViewsEndDrawableColors;
    protected ArrayList<Drawable> textViewsStartDrawables, textViewsEndDrawables;
    protected ArrayList<Boolean> textViewsIsBold, textViewsIsItalic, textViewsIsUnderline;
    protected ArrayList<DrawableOnClickListener> textViewsStartDrawableOnClickListener, textViewsEndDrawableOnClickListener;
    protected ArrayList<Boolean> textViewsStartDrawableOnFocusOnly, textViewsEndDrawableOnFocusOnly;
    protected ArrayList<Boolean> textViewsFocusIsFirst;


    //constructors
    public MyTextViewList(Context context) {
        this(context, null);
    }

    public MyTextViewList(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextViewList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.setOrientation(VERTICAL);
        this.addViews();
    }




    //initialization
    protected void initTitleView() {
        this.titleTextView = new TextView(this.getContext());
        this.setText(null, this.TITLE_TEXT_VIEW);
        this.setTextColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary), this.TITLE_TEXT_VIEW);
        this.setStartDrawableColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary), this.TITLE_TEXT_VIEW);
        this.setStartDrawable(null, this.TITLE_TEXT_VIEW);
        this.setStartDrawableOnClickListener(null, this.TITLE_TEXT_VIEW);
        this.setStartDrawableOnFocusOnly(false, this.TITLE_TEXT_VIEW);
        this.setEndDrawableColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary), this.TITLE_TEXT_VIEW);
        this.setEndDrawable(null, this.TITLE_TEXT_VIEW);
        this.setEndDrawableOnClickListener(null, this.TITLE_TEXT_VIEW);
        this.setEndDrawableOnFocusOnly(false, this.TITLE_TEXT_VIEW);
        this.setBoldEnable(false, this.TITLE_TEXT_VIEW);
        this.setItalicEnable(false, this.TITLE_TEXT_VIEW);
        this.setUnderlineEnable(false, this.TITLE_TEXT_VIEW);
    }

    protected void addTitleView() {
        this.initTitleView();

        this.addView(this.titleTextView, new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        ));
    }

    protected void initTextViewListView() {
        this.textViewsListView = new LinearLayout(this.getContext());
        this.textViewsListView.setOrientation(VERTICAL);
        this.textViews = new ArrayList<TextView>();
        this.textViewsColors = new ArrayList<Integer>();
        this.textViewsStartDrawableColors = new ArrayList<Integer>();
        this.textViewsEndDrawableColors = new ArrayList<Integer>();
        this.textViewsStartDrawables = new ArrayList<Drawable>();
        this.textViewsEndDrawables = new ArrayList<Drawable>();
        this.textViewsIsBold = new ArrayList<Boolean>();
        this.textViewsIsItalic = new ArrayList<Boolean>();
        this.textViewsIsUnderline = new ArrayList<Boolean>();
        this.textViewsStartDrawableOnClickListener = new ArrayList<DrawableOnClickListener>();
        this.textViewsEndDrawableOnClickListener = new ArrayList<DrawableOnClickListener>();
        this.textViewsStartDrawableOnFocusOnly = new ArrayList<Boolean>();
        this.textViewsEndDrawableOnFocusOnly = new ArrayList<Boolean>();
        this.textViewsFocusIsFirst = new ArrayList<Boolean>();

    }

    protected void addTextViewListView() {
        this.initTextViewListView();

        this.addView(this.textViewsListView, new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        ));
    }

    protected void initTextView(final int textViewIndex) {

        if (this.textViews.size() <= textViewIndex) {
            this.addOrSetIfExist(this.textViews, textViewIndex,new TextView(this.getContext()));
        }

        this.addOrSetIfExist(this.textViewsColors, textViewIndex, ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));
        this.addOrSetIfExist(this.textViewsStartDrawableColors, textViewIndex, ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));
        this.addOrSetIfExist(this.textViewsEndDrawableColors, textViewIndex, ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));
        this.addOrSetIfExist(this.textViewsStartDrawables, textViewIndex, null);
        this.addOrSetIfExist(this.textViewsEndDrawables, textViewIndex, null);
        this.addOrSetIfExist(this.textViewsEndDrawables, textViewIndex, null);
        this.addOrSetIfExist(this.textViewsIsBold, textViewIndex, false);
        this.addOrSetIfExist(this.textViewsIsItalic, textViewIndex, false);
        this.addOrSetIfExist(this.textViewsIsUnderline, textViewIndex, false);
        this.addOrSetIfExist(this.textViewsStartDrawableOnClickListener, textViewIndex, null);
        this.addOrSetIfExist(this.textViewsEndDrawableOnClickListener, textViewIndex, null);
        this.addOrSetIfExist(this.textViewsStartDrawableOnFocusOnly, textViewIndex, false);
        this.addOrSetIfExist(this.textViewsEndDrawableOnFocusOnly, textViewIndex, false);
        this.addOrSetIfExist(this.textViewsFocusIsFirst, textViewIndex, false);

        final TextView textView = this.textViews.get(textViewIndex);

        this.setText(null, textViewIndex);
        this.setTextColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary), textViewIndex);
        this.setStartDrawableColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary), textViewIndex);
        this.setStartDrawable(null, textViewIndex);
        this.setStartDrawableOnClickListener(null, textViewIndex);
        this.setStartDrawableOnFocusOnly(false, textViewIndex);
        this.setEndDrawableColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary), textViewIndex);
        this.setEndDrawable(null, textViewIndex);
        this.setEndDrawableOnClickListener(null, textViewIndex);
        this.setEndDrawableOnFocusOnly(false, textViewIndex);
        this.setBoldEnable(false, textViewIndex);
        this.setItalicEnable(false, textViewIndex);
        this.setUnderlineEnable(false, textViewIndex);

        textView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (isOnEndDrawable(textView, motionEvent.getX()) && textView.getCompoundDrawablesRelative()[2] != null) {
                        if (textViewsEndDrawableOnClickListener.get(textViewIndex) != null) {
                            textViewsEndDrawableOnClickListener.get(textViewIndex).onDrawableClick();
                        }
                    } else if (isOnStartDrawable(textView, motionEvent.getX())&& textView.getCompoundDrawablesRelative()[0] != null) {
                        if (textViewsStartDrawableOnClickListener.get(textViewIndex) != null) {
                            textViewsStartDrawableOnClickListener.get(textViewIndex).onDrawableClick();
                        }
                    }
                }
                return false;
            }
        });

        textView.addTextChangedListener(new TextWatcher() {
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
        textView.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (textViewsFocusIsFirst.get(textViewIndex)) {
                    addOrSetIfExist(textViewsFocusIsFirst, textViewIndex, false);
                } else {
                    validateTextField(((TextView) view).getText().toString());
                    if (!hasFocus) {
                        Drawable startDrawable = textView.getCompoundDrawablesRelative()[0];
                        Drawable endDrawable = textView.getCompoundDrawablesRelative()[2];
                        if (textViewsStartDrawableOnFocusOnly.get(textViewIndex)) {
                            startDrawable = null;
                        }
                        if (textViewsEndDrawableOnFocusOnly.get(textViewIndex)) {
                            endDrawable = null;
                        }
                        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(startDrawable, null, endDrawable, null);
                    }
                }
            }
        });

        textView.setCompoundDrawablePadding(this.asDP(5));
        textView.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.transparent));
    }

    public void addTextView(TextView textView, int textViewIndex) {
        this.addOrSetIfExist(this.textViews, textViewIndex, textView);
        this.initTextView(textViewIndex);

        LinearLayout.LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMarginStart(this.asDP(5));

        this.textViewsListView.addView(textView, textViewIndex, layoutParams);
    }

    public void addTextView(int textViewIndex) {
        TextView textView = new TextView(this.getContext());
        this.addTextView(textView, textViewIndex);
    }

    public void addTextView(TextView textView) {
        this.addTextView(textView, this.textViews.size());
    }

    public void addTextView() {
        this.addTextView(this.textViews.size());
    }

    protected void initBottomLine() {
        this.bottomLine = new TextView(this.getContext());
        this.setBottomLineColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));
        this.setBottomLineEnable(false);
    }

    protected void addBottomLine() {
        this.initBottomLine();

        LinearLayout.LayoutParams lineLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                this.asDP(2)
        );
        lineLayoutParams.setMargins(0, 0, 0, this.asDP(5));
        if (this.titleTextView.getCompoundDrawablesRelative()[0] != null) {
            lineLayoutParams.setMarginStart(
                    this.titleTextView.getCompoundDrawablesRelative()[0].getIntrinsicWidth() + this.asDP(5)
            );
        } else {
            lineLayoutParams.setMarginStart(0);
        }
        lineLayoutParams.gravity = Gravity.BOTTOM;
        this.addView(this.bottomLine, lineLayoutParams);
    }

    protected void initErrorView() {
        this.errorTextView = new TextView(this.getContext());
        this.setText(null, this.ERROR_TEXT_VIEW);
        this.setTextColor(ContextCompat.getColor(this.getContext(), R.color.colorError), this.ERROR_TEXT_VIEW);
        this.setStartDrawableColor(ContextCompat.getColor(this.getContext(), R.color.colorError), this.ERROR_TEXT_VIEW);
        this.setStartDrawable(null, this.ERROR_TEXT_VIEW);
        this.setStartDrawableOnClickListener(null, this.ERROR_TEXT_VIEW);
        this.setStartDrawableOnFocusOnly(false, this.ERROR_TEXT_VIEW);
        this.setEndDrawableColor(ContextCompat.getColor(this.getContext(), R.color.colorError), this.ERROR_TEXT_VIEW);
        this.setEndDrawable(null, this.ERROR_TEXT_VIEW);
        this.setEndDrawableOnClickListener(null, this.ERROR_TEXT_VIEW);
        this.setEndDrawableOnFocusOnly(false, this.ERROR_TEXT_VIEW);
        this.setBoldEnable(false, this.ERROR_TEXT_VIEW);
        this.setItalicEnable(false, this.ERROR_TEXT_VIEW);
        this.setUnderlineEnable(false, this.ERROR_TEXT_VIEW);
    }

    protected void addErrorView() {
        this.initErrorView();

        this.addView(this.errorTextView, new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        ));
    }

    protected void addViews() {
       addTitleView();
       addTextViewListView();
       addBottomLine();
       addErrorView();
    }

    //general
    public void setWidth(int width) {
        this.textViewsListView.getLayoutParams().width = width;
        if (this.titleTextView.getCompoundDrawablesRelative()[0] != null) {
            this.bottomLine.setWidth(width -
                    (this.titleTextView.getCompoundDrawablesRelative()[0].getIntrinsicWidth() + this.asDP(5)));
        } else {
            this.bottomLine.setWidth(width);
        }
    }

    public void setHeight(int height) {
        this.textViewsListView.getLayoutParams().height = height;
    }

    //background
    public void setBackgroundImage(Drawable backgroundDrawable) {
        this.backgroundDrawable = backgroundDrawable;
        this.setBackground(backgroundDrawable);
    }

    //validation
    public void validateTextField(String text) {
        if (validator != null) {
            String errorMessage = validator.validate(textViews);
            if (errorMessage != null) {
                this.setError(errorMessage);
            } else {
                this.setError(null);
            }
        }
    }

    //text views
    public void setText(String text, int textViewIndex) {
        switch (textViewIndex) {
            case TITLE_TEXT_VIEW:
                this.setText(text, this.titleTextView);
                break;
            case ERROR_TEXT_VIEW:
                this.setText(text, this.errorTextView);
                break;
            case ALL_TEXT_VIEWS:
                for (TextView textView: this.textViews) {
                    this.setText(text, textView);
                }
                break;
            default:
                this.setText(text, this.textViews.get(textViewIndex));
        }
    }

    protected void setText(String text, TextView textView) {
        textView.setText(text);
    }

    public CharSequence getText(int textViewIndex) {
        switch (textViewIndex) {
            case TITLE_TEXT_VIEW:
                return this.getText(this.titleTextView);
            case ERROR_TEXT_VIEW:
                return this.getText(this.errorTextView);
            default:
                return this.getText(this.textViews.get(textViewIndex));
        }
    }

    protected CharSequence getText(TextView textView) {
        return textView.getText();
    }

    public void setTextSize(int size, int textViewIndex) {
        switch (textViewIndex) {
            case TITLE_TEXT_VIEW:
                this.setTextSize(size, this.titleTextView);
                break;
            case ERROR_TEXT_VIEW:
                this.setTextSize(size, this.errorTextView);
                break;
            case ALL_TEXT_VIEWS:
                for (TextView textView: this.textViews) {
                    this.setTextSize(size, textView);
                }
                break;
            default:
                this.setTextSize(size, this.textViews.get(textViewIndex));
        }
    }

    protected void setTextSize(int size, TextView textView) {
        textView.setTextSize(this.asDP(size));
    }

    public void setTextColor(int color, int textViewIndex) {
        switch (textViewIndex) {
            case TITLE_TEXT_VIEW:
                this.titleColor = color;
                this.paintTextColor(color, this.titleTextView);
                break;
            case ERROR_TEXT_VIEW:
                this.errorColor = color;
                this.paintTextColor(color, this.errorTextView);
                break;
            case ALL_TEXT_VIEWS:
                for (int index = 0; index < this.textViews.size(); index++) {
                    this.addOrSetIfExist(this.textViewsColors, index, color);
                    this.paintTextColor(color, this.textViews.get(index));
                }
                break;
            default:
                this.addOrSetIfExist(this.textViewsColors, textViewIndex, color);
                this.paintTextColor(color, this.textViews.get(textViewIndex));
        }
    }

    protected void paintTextColor(int color, TextView textView) {
        textView.setTextColor(color);
    }

    public void setTextTypeface(Typeface typeface, int textViewIndex) {
        switch (textViewIndex) {
            case TITLE_TEXT_VIEW:
                this.setTextTypeface(typeface, this.titleTextView);
                break;
            case ERROR_TEXT_VIEW:
                this.setTextTypeface(typeface, this.errorTextView);
                break;
            case ALL_TEXT_VIEWS:
                for (TextView textView: this.textViews) {
                    this.setTextTypeface(typeface, textView);
                }
                break;
            default:
                this.setTextTypeface(typeface, this.textViews.get(textViewIndex));
        }
    }

    protected void setTextTypeface(Typeface typeface, TextView textView) {
        textView.setTypeface(typeface);
    }

    public void setBoldEnable(boolean isBold, int textViewIndex) {
        switch (textViewIndex) {
            case TITLE_TEXT_VIEW:
                this.titleIsBold = isBold;
                this.makeBoldEnable(this.titleIsBold, this.titleIsItalic, this.titleTextView);
                break;
            case ERROR_TEXT_VIEW:
                this.errorIsBold = isBold;
                this.makeBoldEnable(this.errorIsBold, this.errorIsItalic, this.errorTextView);
                break;
            case ALL_TEXT_VIEWS:
                for (int index = 0; index < this.textViews.size(); index++) {
                    this.addOrSetIfExist(this.textViewsIsBold, index, isBold);
                    this.makeBoldEnable(this.textViewsIsBold.get(index), this.textViewsIsItalic.get(index), this.textViews.get(index));
                }
                break;
            default:
                this.addOrSetIfExist(this.textViewsIsBold, textViewIndex, isBold);
                this.makeBoldEnable(this.textViewsIsBold.get(textViewIndex), this.textViewsIsItalic.get(textViewIndex), this.textViews.get(textViewIndex));
        }
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

    public void setItalicEnable(boolean isItalic, int textViewIndex) {
        switch (textViewIndex) {
            case TITLE_TEXT_VIEW:
                this.titleIsItalic = isItalic;
                this.makeItalicEnable(this.titleIsItalic, this.titleIsBold, this.titleTextView);
                break;
            case ERROR_TEXT_VIEW:
                this.errorIsItalic = isItalic;
                this.makeItalicEnable(this.errorIsItalic, this.errorIsBold, this.errorTextView);
                break;
            case ALL_TEXT_VIEWS:
                for (int index = 0; index < this.textViews.size(); index++) {
                    this.addOrSetIfExist(this.textViewsIsItalic, index, isItalic);
                    this.makeItalicEnable(this.textViewsIsItalic.get(index), this.textViewsIsBold.get(index), this.textViews.get(index));
                }
                break;
            default:
                this.addOrSetIfExist(this.textViewsIsItalic, textViewIndex, isItalic);
                this.makeItalicEnable(this.textViewsIsBold.get(textViewIndex), this.textViewsIsItalic.get(textViewIndex), this.textViews.get(textViewIndex));
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

    public void setUnderlineEnable(boolean isUnderline, int textViewIndex) {
        switch (textViewIndex) {
            case TITLE_TEXT_VIEW:
                this.titleIsUnderline = isUnderline;
                this.makeUnderlineEnable(isUnderline, this.titleTextView);
                break;
            case ERROR_TEXT_VIEW:
                this.errorIsUnderline = isUnderline;
                this.makeUnderlineEnable(isUnderline, this.errorTextView);
                break;
            case ALL_TEXT_VIEWS:
                for (int index = 0; index < this.textViews.size(); index++) {
                    this.addOrSetIfExist(this.textViewsIsUnderline, index, isUnderline);
                    this.makeUnderlineEnable(isUnderline, this.textViews.get(index));
                }
                break;
            default:
                this.addOrSetIfExist(this.textViewsIsUnderline, textViewIndex, isUnderline);
                this.makeUnderlineEnable(isUnderline, this.textViews.get(textViewIndex));
        }
    }

    public void makeUnderlineEnable(boolean isUnderline, TextView textView) {
        if (isUnderline) {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        } else {
            textView.setPaintFlags(textView.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
        }
    }

    public void setTextInputType(int type, int textViewIndex) {
        switch (textViewIndex) {
            case TITLE_TEXT_VIEW:
                this.setTextInputType(type, this.titleTextView);
                break;
            case ERROR_TEXT_VIEW:
                this.setTextInputType(type, this.errorTextView);
                break;
            case ALL_TEXT_VIEWS:
                for (TextView textView: this.textViews) {
                    this.setTextInputType(type, textView);
                }
                break;
            default:
                this.setTextInputType(type, this.textViews.get(textViewIndex));
        }
    }

    protected void setTextInputType(int type, TextView textView) {
        textView.setInputType(type);
    }


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

    //drawables
    public void setStartDrawable(int startDrawable, int textViewIndex) {
        this.setStartDrawable(ContextCompat.getDrawable(getContext(), startDrawable), textViewIndex);
    }

    public void setStartDrawable(Drawable startDrawable, int textViewIndex) {
        switch (textViewIndex) {
            case TITLE_TEXT_VIEW:
                this.titleStartDrawable = startDrawable;
                this.paintStartDrawable(this.titleStartDrawableColor,
                        this.titleStartDrawable,
                        this.titleEndDrawable,
                        this.titleTextView);
                break;
            case ERROR_TEXT_VIEW:
                this.errorEndDrawable = startDrawable;
                this.paintStartDrawable(this.errorStartDrawableColor,
                        this.errorStartDrawable,
                        this.errorEndDrawable,
                        this.errorTextView);
                break;
            case ALL_TEXT_VIEWS:
                for (int index = 0; index < this.textViews.size(); index++) {
                    this.addOrSetIfExist(this.textViewsStartDrawables, index, startDrawable);
                    this.paintStartDrawable(this.textViewsStartDrawableColors.get(index),
                            this.textViewsStartDrawables.get(index),
                            this.textViewsEndDrawables.get(index),
                            this.textViews.get(index));
                }
                break;
            default:
                this.addOrSetIfExist(this.textViewsStartDrawables, textViewIndex, startDrawable);
                this.paintStartDrawable(this.textViewsStartDrawableColors.get(textViewIndex),
                        this.textViewsStartDrawables.get(textViewIndex),
                        this.textViewsEndDrawables.get(textViewIndex),
                        this.textViews.get(textViewIndex));
        }
    }

    public void setStartDrawableColor(int color, int textViewIndex) {
        switch (textViewIndex) {
            case TITLE_TEXT_VIEW:
                this.titleStartDrawableColor = color;
                this.paintStartDrawable(this.titleStartDrawableColor,
                        this.titleStartDrawable,
                        this.titleEndDrawable,
                        this.titleTextView);
                break;
            case ERROR_TEXT_VIEW:
                this.titleStartDrawableColor = color;
                this.paintStartDrawable(this.errorStartDrawableColor,
                        this.errorStartDrawable,
                        this.errorEndDrawable,
                        this.errorTextView);
                break;
            case ALL_TEXT_VIEWS:
                for (int index = 0; index < this.textViews.size(); index++) {
                    this.addOrSetIfExist(this.textViewsStartDrawableColors, index, color);
                    this.paintStartDrawable(this.textViewsStartDrawableColors.get(index),
                            this.textViewsStartDrawables.get(index),
                            this.textViewsEndDrawables.get(index),
                            this.textViews.get(index));
                }
                break;
            default:
                this.addOrSetIfExist(this.textViewsStartDrawableColors, textViewIndex, color);
                this.paintStartDrawable(this.textViewsStartDrawableColors.get(textViewIndex),
                        this.textViewsStartDrawables.get(textViewIndex),
                        this.textViewsEndDrawables.get(textViewIndex),
                        this.textViews.get(textViewIndex));
        }
    }

    protected void paintStartDrawable(int color, Drawable startDrawable, Drawable endDrawable, TextView textView) {
        if (startDrawable != null) {
            startDrawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        }
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(startDrawable, null, endDrawable, null);
        if (textView.getCompoundDrawablesRelative()[0] != null) {
            if (this.bottomLine != null) {
                ((LinearLayout.LayoutParams)this.bottomLine.getLayoutParams()).setMarginStart(
                        textView.getCompoundDrawablesRelative()[0].getIntrinsicWidth() + this.asDP(5)
                );
            }
        } else {
            if (this.bottomLine != null) {
                ((LinearLayout.LayoutParams)this.bottomLine.getLayoutParams()).setMarginStart(0);
            }
        }
    }

    public void setStartDrawableEnable(boolean drawableEnable, int textViewIndex) {
        switch (textViewIndex) {
            case TITLE_TEXT_VIEW:
                this.setStartDrawableEnable(drawableEnable, this.titleTextView);
                break;
            case ERROR_TEXT_VIEW:
                this.setStartDrawableEnable(drawableEnable, this.errorTextView);
                break;
            case ALL_TEXT_VIEWS:
                for (int index = 0; index < this.textViews.size(); index++) {
                    this.setStartDrawableEnable(drawableEnable, this.textViews.get(index));
                }
                break;
            default:
                this.setStartDrawableEnable(drawableEnable, this.textViews.get(textViewIndex));
        }
    }

    protected void setStartDrawableEnable(boolean drawableEnable, TextView textView) {
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

    public void setEndDrawable(int endDrawable, int textViewIndex) {
        this.setEndDrawable(ContextCompat.getDrawable(getContext(), endDrawable), textViewIndex);
    }

    public void setEndDrawable(Drawable endDrawable, int textViewIndex) {
        switch (textViewIndex) {
            case TITLE_TEXT_VIEW:
                this.titleEndDrawable = endDrawable;
                this.paintEndDrawable(this.titleStartDrawableColor,
                        this.titleStartDrawable,
                        this.titleEndDrawable,
                        this.titleTextView);
                break;
            case ERROR_TEXT_VIEW:
                this.titleStartDrawable = endDrawable;
                this.paintEndDrawable(this.errorStartDrawableColor,
                        this.errorStartDrawable,
                        this.errorEndDrawable,
                        this.errorTextView);
                break;
            case ALL_TEXT_VIEWS:
                for (int index = 0; index < this.textViews.size(); index++) {
                    this.addOrSetIfExist(this.textViewsEndDrawables, index, endDrawable);
                    this.paintEndDrawable(this.textViewsStartDrawableColors.get(index),
                            this.textViewsStartDrawables.get(index),
                            this.textViewsEndDrawables.get(index),
                            this.textViews.get(index));
                }
                break;
            default:
                this.addOrSetIfExist(this.textViewsEndDrawables, textViewIndex, endDrawable);
                this.paintEndDrawable(this.textViewsStartDrawableColors.get(textViewIndex),
                        this.textViewsStartDrawables.get(textViewIndex),
                        this.textViewsEndDrawables.get(textViewIndex),
                        this.textViews.get(textViewIndex));
        }
    }

    public void setEndDrawableColor(int color, int textViewIndex) {
        switch (textViewIndex) {
            case TITLE_TEXT_VIEW:
                this.titleEndDrawableColor = color;
                this.paintEndDrawable(this.titleStartDrawableColor,
                        this.titleStartDrawable,
                        this.titleEndDrawable,
                        this.titleTextView);
                break;
            case ERROR_TEXT_VIEW:
                this.errorEndDrawableColor = color;
                this.paintEndDrawable(this.errorStartDrawableColor,
                        this.errorStartDrawable,
                        this.errorEndDrawable,
                        this.errorTextView);
                break;
            case ALL_TEXT_VIEWS:
                for (int index = 0; index < this.textViews.size(); index++) {
                    this.addOrSetIfExist(this.textViewsEndDrawableColors, textViewIndex, color);
                    this.paintEndDrawable(this.textViewsStartDrawableColors.get(index),
                            this.textViewsStartDrawables.get(index),
                            this.textViewsEndDrawables.get(index),
                            this.textViews.get(index));
                }
                break;
            default:
                this.addOrSetIfExist(this.textViewsEndDrawableColors, textViewIndex, color);
                this.paintEndDrawable(this.textViewsStartDrawableColors.get(textViewIndex),
                        this.textViewsStartDrawables.get(textViewIndex),
                        this.textViewsEndDrawables.get(textViewIndex),
                        this.textViews.get(textViewIndex));
        }
    }

    protected void paintEndDrawable(int color, Drawable startDrawable, Drawable endDrawable, TextView textView) {
        if (endDrawable != null) {
            endDrawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        }
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(startDrawable, null, endDrawable, null);
    }

    public void setEndDrawableEnable(boolean drawableEnable, int textViewIndex) {
        switch (textViewIndex) {
            case TITLE_TEXT_VIEW:
                this.setEndDrawableEnable(drawableEnable, this.titleTextView);
                break;
            case ERROR_TEXT_VIEW:
                this.setEndDrawableEnable(drawableEnable, this.errorTextView);
                break;
            case ALL_TEXT_VIEWS:
                for (int index = 0; index < this.textViews.size(); index++) {
                    this.setEndDrawableEnable(drawableEnable, this.textViews.get(index));
                }
                break;
            default:
                this.setEndDrawableEnable(drawableEnable, this.textViews.get(textViewIndex));
        }
    }

    protected void setEndDrawableEnable(boolean drawableEnable, TextView textView) {
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

    //drawables on click listeners
    public void setStartDrawableOnClickListener(DrawableOnClickListener listener, int textViewIndex) {
        switch (textViewIndex) {
            case TITLE_TEXT_VIEW:
                this.titleStartDrawableOnClickListener = listener;
                break;
            case ERROR_TEXT_VIEW:
                this.errorStartDrawableOnClickListener = listener;
                break;
            case ALL_TEXT_VIEWS:
                for (int index = 0; index < this.textViews.size(); index++) {
                    this.addOrSetIfExist(this.textViewsStartDrawableOnClickListener, index, listener);
                }
                break;
            default:
                this.addOrSetIfExist(this.textViewsStartDrawableOnClickListener, textViewIndex, listener);
        }
    }

    public void setEndDrawableOnClickListener(DrawableOnClickListener listener, int textViewIndex) {
        switch (textViewIndex) {
            case TITLE_TEXT_VIEW:
                this.titleEndDrawableOnClickListener = listener;
                break;
            case ERROR_TEXT_VIEW:
                this.errorEndDrawableOnClickListener = listener;
                break;
            case ALL_TEXT_VIEWS:
                for (int index = 0; index < this.textViews.size(); index++) {
                    this.addOrSetIfExist(this.textViewsEndDrawableOnClickListener, index, listener);
                }
                break;
            default:
                this.addOrSetIfExist(this.textViewsEndDrawableOnClickListener, textViewIndex, listener);
        }
    }

    public abstract static class DrawableOnClickListener {

        public abstract void onDrawableClick();

    }

    //drawable on focus boolean
    public void setStartDrawableOnFocusOnly(boolean startDrawableOnFocusOnly, int textViewIndex) {
        switch (textViewIndex) {
            case TITLE_TEXT_VIEW:
                this.titleStartDrawableOnFocusOnly = startDrawableOnFocusOnly;
                break;
            case ERROR_TEXT_VIEW:
                this.errorStartDrawableOnFocusOnly = startDrawableOnFocusOnly;
                break;
            case ALL_TEXT_VIEWS:
                for (int index = 0; index < this.textViews.size(); index++) {
                    this.addOrSetIfExist(this.textViewsStartDrawableOnFocusOnly, index, startDrawableOnFocusOnly);
                }
                break;
            default:
                this.addOrSetIfExist(this.textViewsStartDrawableOnFocusOnly, textViewIndex, startDrawableOnFocusOnly);
        }
    }

    public void setEndDrawableOnFocusOnly(boolean endDrawableOnFocusOnly, int textViewIndex) {
        switch (textViewIndex) {
            case TITLE_TEXT_VIEW:
                this.titleEndDrawableOnFocusOnly = endDrawableOnFocusOnly;
                break;
            case ERROR_TEXT_VIEW:
                this.errorEndDrawableOnFocusOnly = endDrawableOnFocusOnly;
                break;
            case ALL_TEXT_VIEWS:
                for (int index = 0; index < this.textViews.size(); index++) {
                    this.addOrSetIfExist(this.textViewsEndDrawableOnFocusOnly, index, endDrawableOnFocusOnly);
                }
                break;
            default:
                this.addOrSetIfExist(this.textViewsEndDrawableOnFocusOnly, textViewIndex, endDrawableOnFocusOnly);
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

    //validator
    public void setValidator(ViewsValidator validator) {
        this.validator = validator;
    }

    //error text view
    public void setError(String text) {
        this.setText(text, this.ERROR_TEXT_VIEW);
        if (text != null) {
            this.paintTextColor(this.errorColor, this.titleTextView);
            this.paintStartDrawable(this.errorColor, this.titleStartDrawable, this.titleEndDrawable, this.titleTextView);
            this.paintEndDrawable(this.errorColor, this.titleEndDrawable, this.titleStartDrawable, this.titleTextView);
            this.setBottomLineEnable(true);
            this.paintBottomLineColor(this.errorColor);
            this.paintTextColor(this.errorColor, this.errorTextView);
        } else {
            this.paintTextColor(this.titleColor, this.titleTextView);
            this.paintStartDrawable(this.titleStartDrawableColor, this.titleStartDrawable, this.titleEndDrawable, this.titleTextView);
            this.paintEndDrawable(this.titleStartDrawableColor, this.titleEndDrawable, this.titleStartDrawable, this.titleTextView);
            this.setBottomLineEnable(false);
            this.paintBottomLineColor(this.bottomLineColor);
            this.paintTextColor(this.errorColor, this.errorTextView);
        }
    }

    //set title color
    public void setTitle(String text) {
        this.setText(text, this.TITLE_TEXT_VIEW);
    }

    //text view getter
    public TextView getTextView(int textViewIndex) {
        return this.textViews.get(textViewIndex);
    }

    //int to dp tool
    protected int asDP(int num) {
        return num * ((int)(this.getContext().getResources().getDisplayMetrics().density));
    }

    //add if index not exist to array
    protected void addOrSetIfExist(ArrayList array, int index, Object object) {
        if (array.size() <= index) {
            array.add(object);
        } else {
            array.set(index, object);
        }
    }
}