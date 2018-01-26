package com.bob.bobmobileapp.tools.UI.views;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.StackingBehavior;
import com.afollestad.materialdialogs.Theme;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.finals;

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

    //dialog
    protected boolean isDialogEnable;
    protected MaterialDialog.Builder dialogBuilder;
    protected int dialogBackGroundColor, dialogTitleColor, dialogContentColor, dialogPositiveColor,
            dialogNegativeColor, dialogNeutralColor, dialogDividerColor, dialogLinkColor;
    protected GravityEnum dialogTitleGravity, dialogContentGravity, dialogButtonsGravity,
            dialogButtonsStackedGravity;
    protected int dialogIconMaxSize;
    protected boolean dialogIsPositiveFocus, dialogIsNegativeFocus;
    protected StackingBehavior dialogIsButtonsStacked;
    protected String dialogTitleText, dialogContentText, dialogPositiveText,
            dialogNegativeText, dialogNeutralText;
    protected Theme dialogTheme;
    protected Typeface dialogTitleAndButtonsFont, dialogTextFont;
    protected Drawable dialogIcon;
    protected MaterialDialog.SingleButtonCallback dialogPositiveOnClick, dialogNegativeOnClick, dialogNaturalOnClick;


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
        this.setBottomLineSize(this.convertPixelsToDp(2));

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
        lineLayoutParams.setMargins(0, 0, 0, this.convertPixelsToDp(5));

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

    protected void initDialog() {
        this.dialogBuilder = new MaterialDialog.Builder(this.getContext());
        this.setDialogBackGroundColor(ContextCompat.getColor(this.getContext(), R.color.windowBackground));
        this.setDialogTitleColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));
        this.setDialogContentColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));
        this.setDialogPositiveColor(ContextCompat.getColor(this.getContext(), R.color.colorPrimary));
        this.setDialogNegativeColor(ContextCompat.getColor(this.getContext(), R.color.colorPrimary));
        this.setDialogNeutralColor(ContextCompat.getColor(this.getContext(), R.color.colorPrimary));
        this.setDialogDividerColor(ContextCompat.getColor(this.getContext(), R.color.colorPrimary));
        this.setDialogLinkColor(ContextCompat.getColor(this.getContext(), R.color.colorPrimary));
        this.setDialogTitleGravity(finals.dialogGravity.get("center"));
        this.setDialogContentGravity(finals.dialogGravity.get("center"));
        this.setDialogButtonsGravity(finals.dialogGravity.get("end"));
        this.setDialogButtonsStackedGravity(finals.dialogGravity.get("center"));
        this.setDialogIconMaxSize( (int) this.getContext().getResources().getDimension(R.dimen.dialog_default_max_icon_size));
        this.setDialogIsPositiveFocus(false);
        this.setDialogIsNegativeFocus(true);
        this.setDialogIsButtonsStacked(finals.dialogStackingBehavior.get("never"));
        this.setDialogTitleText("Default dialog");
        this.setDialogContentText("Default message");
        this.setDialogPositiveText("Ok");
        this.setDialogNegativeText("Cancle");
        this.setDialogNeutralText(null);
        this.setDialogTheme(Theme.LIGHT);
        this.setDialogTitleAndButtonsFont(null);
        this.setDialogTextFont(null);
        this.setDialogIcon(null);

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
        return this.convertPixelsToDp(5);
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
        textView.setTextSize(this.convertPixelsToDp(size));
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

    protected void makeItalicEnable(boolean isItalic, boolean isBold, TextView textView) {
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

    protected void makeUnderlineEnable(boolean isUnderline, TextView textView) {
        if (isUnderline) {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        } else {
            textView.setPaintFlags(textView.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
        }
    }

    protected void setTextInputType(int type, TextView textView) {
        textView.setInputType(type);
    }

    //dialog
    public void setDialogEnable(boolean isDialogEnable) {
        this.isDialogEnable = isDialogEnable;
    }

    public void setDialogBackGroundColor(int dialogBackGroundColor) {
        this.dialogBackGroundColor = dialogBackGroundColor;
        this.dialogBuilder.backgroundColor(this.backgroundColor);
    }

    public void setDialogTitleColor(int dialogTitleColor) {
        this.dialogTitleColor = dialogTitleColor;
        this.dialogBuilder.titleColor(this.dialogTitleColor);
    }

    public void setDialogContentColor(int dialogContentColor) {
        this.dialogContentColor = dialogContentColor;
        this.dialogBuilder.contentColor(this.dialogContentColor);
    }

    public void setDialogPositiveColor(int dialogPositiveColor) {
        this.dialogPositiveColor = dialogPositiveColor;
        this.dialogBuilder.positiveColor(this.dialogPositiveColor);
    }

    public void setDialogNegativeColor(int dialogNegativeColor) {
        this.dialogNegativeColor = dialogNegativeColor;
        this.dialogBuilder.negativeColor(this.dialogNegativeColor);
    }

    public void setDialogNeutralColor(int dialogNeutralColor) {
        this.dialogNeutralColor = dialogNeutralColor;
        this.dialogBuilder.neutralColor(this.dialogNeutralColor);
    }

    public void setDialogDividerColor(int dialogDividerColor) {
        this.dialogDividerColor = dialogDividerColor;
        this.dialogBuilder.dividerColor(this.dialogDividerColor);
    }

    public void setDialogLinkColor(int dialogLinkColor) {
        this.dialogLinkColor = dialogLinkColor;
        this.dialogBuilder.linkColor(this.dialogLinkColor);
    }

    public void setDialogTitleGravity(GravityEnum dialogTitleGravity) {
        this.dialogTitleGravity = dialogTitleGravity;
        this.dialogBuilder.titleGravity(this.dialogTitleGravity);
    }

    public void setDialogContentGravity(GravityEnum dialogContentGravity) {
        this.dialogContentGravity = dialogContentGravity;
        this.dialogBuilder.contentGravity(this.dialogContentGravity);
    }

    public void setDialogButtonsGravity(GravityEnum dialogButtonsGravity) {
        this.dialogButtonsGravity = dialogButtonsGravity;
        this.dialogBuilder.buttonsGravity(this.dialogButtonsGravity);
    }

    public void setDialogButtonsStackedGravity(GravityEnum dialogButtonsStackedGravity) {
        this.dialogButtonsStackedGravity = dialogButtonsStackedGravity;
        this.dialogBuilder.btnStackedGravity(this.dialogButtonsStackedGravity);
    }

    public void setDialogIconMaxSize(int dialogIconMaxSize) {
        this.dialogIconMaxSize = dialogIconMaxSize;
        this.dialogBuilder.maxIconSize(this.dialogIconMaxSize);
    }

    public void setDialogIsPositiveFocus(boolean dialogIsPositiveFocus) {
        this.dialogIsPositiveFocus = dialogIsPositiveFocus;
        this.dialogBuilder.positiveFocus(this.dialogIsPositiveFocus);
    }

    public void setDialogIsNegativeFocus(boolean dialogIsNegativeFocus) {
        this.dialogIsNegativeFocus = dialogIsNegativeFocus;
        this.dialogBuilder.negativeFocus(this.dialogIsNegativeFocus);
    }

    public void setDialogIsButtonsStacked(StackingBehavior dialogIsButtonsStacked) {
        this.dialogIsButtonsStacked = dialogIsButtonsStacked;
        this.dialogBuilder.stackingBehavior(this.dialogIsButtonsStacked);
    }

    public void setDialogTitleText(String dialogTitleText) {
        this.dialogTitleText = dialogTitleText;
        this.dialogBuilder.title(this.dialogTitleText);
    }

    public void setDialogContentText(String dialogContentText) {
        this.dialogContentText = dialogContentText;
        this.dialogBuilder.content(this.dialogContentText);
    }

    public void setDialogPositiveText(String dialogPositiveText) {
        this.dialogPositiveText = dialogPositiveText;
        this.dialogBuilder.positiveText(this.dialogPositiveText);
    }

    public void setDialogNegativeText(String dialogNegativeText) {
        this.dialogNegativeText = dialogNegativeText;
        this.dialogBuilder.negativeText(this.dialogNegativeText);
    }

    public void setDialogNeutralText(String dialogNeutralText) {
        this.dialogNeutralText = dialogNeutralText;
        this.dialogBuilder.neutralText(this.dialogNeutralText);
    }

    public void setDialogTheme(Theme dialogTheme) {
        this.dialogTheme = dialogTheme;
        this.dialogBuilder.theme(this.dialogTheme);
    }

    public void setDialogTitleAndButtonsFont(Typeface dialogTitleAndButtonsFont) {
        this.dialogTitleAndButtonsFont = dialogTitleAndButtonsFont;
        this.dialogBuilder.typeface(this.dialogTitleAndButtonsFont, this.dialogTextFont);
    }

    public void setDialogTextFont(Typeface dialogTextFont) {
        this.dialogTextFont = dialogTextFont;
        this.dialogBuilder.typeface(this.dialogTitleAndButtonsFont, this.dialogTextFont);
    }

    public void setDialogIcon(Drawable dialogIcon) {
        this.dialogIcon = dialogIcon;
        this.dialogBuilder.icon(this.dialogIcon);
    }

    public void setDialogPositiveOnClick(MaterialDialog.SingleButtonCallback dialogPositiveOnClick) {
        this.dialogPositiveOnClick = dialogPositiveOnClick;
        this.dialogBuilder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                if (MyView.this.dialogPositiveOnClick != null) {
                    MyView.this.dialogPositiveOnClick.onClick(dialog, which);
                }
            }
        });
    }

    public void setDialogNegativeOnClick(MaterialDialog.SingleButtonCallback dialogNegativeOnClick) {
        this.dialogNegativeOnClick = dialogNegativeOnClick;
        this.dialogBuilder.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                if (MyView.this.dialogNegativeOnClick != null) {
                    MyView.this.dialogNegativeOnClick.onClick(dialog, which);
                }
            }
        });
    }

    public void setDialogNaturalOnClick(MaterialDialog.SingleButtonCallback dialogNaturalOnClick) {
        this.dialogNaturalOnClick = dialogNaturalOnClick;
        this.dialogBuilder.onNeutral(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                if (MyView.this.dialogNaturalOnClick != null) {
                    MyView.this.dialogNaturalOnClick.onClick(dialog, which);
                }
            }
        });
    }

    public void setDialogContentView(int contentView) {
        this.setDialogContentView(contentView, true);
    }

    public void setDialogContentView(int contentView, boolean wrapInScrollView) {
        this.dialogBuilder.customView(contentView, wrapInScrollView);
    }

    public void setDialogContentView(View contentView) {
        this.setDialogContentView(contentView, true);
    }

    public void setDialogContentView(View contentView, boolean wrapInScrollView) {
        this.dialogBuilder.customView(contentView, wrapInScrollView);
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
            return this.startDrawable.getIntrinsicWidth() + this.convertPixelsToDp(5);
        } else {
            return this.convertPixelsToDp(5);
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
    protected int convertPixelsToDp(int px) {
        return px * ((int)(this.getContext().getResources().getDisplayMetrics().density));
    }

    protected int convertPixelsToSp(int px) {
        return convertDpToSp(convertPixelsToDp(px));
    }

    protected int convertDpToPixels(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, this.getContext().getResources().getDisplayMetrics());
    }

    protected int convertSpToPixels(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, this.getContext().getResources().getDisplayMetrics());
    }

    protected int convertDpToSp(float dp) {
        return (int) (convertDpToPixels(dp) / (float) convertSpToPixels(dp));
    }

    protected int convertSpToDp(float sp) {
        return convertPixelsToDp(convertSpToPixels(sp));
    }





}
