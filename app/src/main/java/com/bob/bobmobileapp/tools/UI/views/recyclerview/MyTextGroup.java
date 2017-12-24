package com.bob.bobmobileapp.tools.UI.views.recyclerview;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bob.bobmobileapp.tools.UI.views.MyView;

import java.util.ArrayList;

/**
 * Created by User on 24/12/2017.
 */

public class MyTextGroup<ViewType extends TextView> extends MyRecyclerView<ViewType> {

    //text views
    protected ArrayList<ViewType> textViews;
    protected ArrayList<Integer> textViewsColors, textViewsStartDrawableColors, textViewsEndDrawableColors;
    protected ArrayList<Drawable> textViewsStartDrawables, textViewsEndDrawables;
    protected ArrayList<Boolean> textViewsIsBold, textViewsIsItalic, textViewsIsUnderline;
    protected ArrayList<MyTextViewList.DrawableOnClickListener> textViewsStartDrawableOnClickListener, textViewsEndDrawableOnClickListener;
    protected ArrayList<Boolean> textViewsStartDrawableOnFocusOnly, textViewsEndDrawableOnFocusOnly;
    protected ArrayList<Boolean> textViewsFocusIsFirst;


    public MyTextGroup(Context context) {
        this(context, null);
    }

    public MyTextGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initTextViews();
    }

    protected void initTextViews() {
        this.textViews = this.adapter.getViews();
        this.textViewsColors = new ArrayList<Integer>();
        this.textViewsStartDrawableColors = new ArrayList<Integer>();
        this.textViewsEndDrawableColors = new ArrayList<Integer>();
        this.textViewsStartDrawables = new ArrayList<Drawable>();
        this.textViewsEndDrawables = new ArrayList<Drawable>();
        this.textViewsIsBold = new ArrayList<Boolean>();
        this.textViewsIsItalic = new ArrayList<Boolean>();
        this.textViewsIsUnderline = new ArrayList<Boolean>();
        this.textViewsStartDrawableOnClickListener = new ArrayList<MyTextViewList.DrawableOnClickListener>();
        this.textViewsEndDrawableOnClickListener = new ArrayList<MyTextViewList.DrawableOnClickListener>();
        this.textViewsStartDrawableOnFocusOnly = new ArrayList<Boolean>();
        this.textViewsEndDrawableOnFocusOnly = new ArrayList<Boolean>();
        this.textViewsFocusIsFirst = new ArrayList<Boolean>();

    }



    //text views
    public void setTextViewText(String text, int textViewIndex) {
        this.adapter.getView(textViewIndex).setText(text);
    }

    public CharSequence getTextViewText(int textViewIndex) {
        return this.getText(this.adapter.getView(textViewIndex));
    }

    public void setTextViewTextSize(int size, int textViewIndex) {
        this.setTextSize(size, this.adapter.getView(textViewIndex));
    }

    public void setTextViewTextInputType(int type, int textViewIndex) {
        this.setTextInputType(type, this.adapter.getView(textViewIndex));
    }

    public void setTextViewTextColor(int color, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsColors, textViewIndex, color);
        this.paintTextColor(color, this.adapter.getView(textViewIndex));
    }

    public void setTextViewStartDrawable(int startDrawable, int textViewIndex) {
        this.setTextViewStartDrawable(ContextCompat.getDrawable(getContext(), startDrawable), textViewIndex);
    }

    public void setTextViewStartDrawable(Drawable startDrawable, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsStartDrawables, textViewIndex, startDrawable);
        this.paintStartDrawable(this.errorStartDrawableColor,
                this.errorStartDrawable,
                this.errorEndDrawable,
                this.adapter.getView(textViewIndex));
    }

    public void setTextViewStartDrawableColor(int color, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsStartDrawableColors, textViewIndex, color);
        this.paintStartDrawable(this.errorStartDrawableColor,
                this.errorStartDrawable,
                this.errorEndDrawable,
                this.adapter.getView(textViewIndex));
    }

    public void setTextViewStartDrawableEnable(boolean drawableEnable, int textViewIndex) {
        this.makeStartDrawableEnable(drawableEnable, this.adapter.getView(textViewIndex));
    }

    public void setTextViewEndDrawable(int endDrawable, int textViewIndex) {
        this.setTextViewEndDrawable(ContextCompat.getDrawable(getContext(), endDrawable), textViewIndex);
    }

    public void setTextViewEndDrawable(Drawable endDrawable, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsEndDrawables, textViewIndex, endDrawable);
        this.paintEndDrawable(this.errorStartDrawableColor,
                this.errorStartDrawable,
                this.errorEndDrawable,
                this.adapter.getView(textViewIndex));
    }

    public void setTextViewEndDrawableColor(int color, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsEndDrawableColors, textViewIndex, color);
        this.paintEndDrawable(this.errorStartDrawableColor,
                this.errorStartDrawable,
                this.errorEndDrawable,
                this.adapter.getView(textViewIndex));
    }

    public void setTextViewEndDrawableEnable(boolean drawableEnable, int textViewIndex) {
        this.makeEndDrawableEnable(drawableEnable, this.adapter.getView(textViewIndex));
    }

    public void setTextViewStartDrawableOnClickListener(MyView.DrawableOnClickListener listener, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsStartDrawableOnClickListener, textViewIndex, listener);
    }

    public void setTextViewEndDrawableOnClickListener(MyView.DrawableOnClickListener listener, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsEndDrawableOnClickListener, textViewIndex, listener);
    }

    public void setTextViewStartDrawableOnFocusOnly(boolean startDrawableOnFocusOnly, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsStartDrawableOnFocusOnly, textViewIndex, startDrawableOnFocusOnly);
    }

    public void setTextViewEndDrawableOnFocusOnly(boolean endDrawableOnFocusOnly, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsEndDrawableOnFocusOnly, textViewIndex, startDrawableOnFocusOnly);
    }

    public void setTextViewTextTypeface(Typeface typeface, int textViewIndex) {
        this.setTextTypeface(typeface, this.adapter.getView(textViewIndex));
    }

    public void setTextViewBoldEnable(boolean isBold, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsIsBold, textViewIndex, isBold);
        this.makeBoldEnable(this.errorIsBold, this.errorIsItalic, this.adapter.getView(textViewIndex));
    }

    public void setTextViewItalicEnable(boolean isItalic, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsIsItalic, textViewIndex, isItalic);
        this.makeItalicEnable(this.errorIsItalic, this.errorIsBold, this.adapter.getView(textViewIndex));
    }

    public void setTextViewUnderlineEnable(boolean isUnderline, int textViewIndex) {
        this.addOrSetIfExist(this.textViewsIsUnderline, textViewIndex, isUnderline);
        this.makeUnderlineEnable(isUnderline, this.adapter.getView(textViewIndex));
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
