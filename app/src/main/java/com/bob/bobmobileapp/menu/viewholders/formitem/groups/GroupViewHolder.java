/*package com.bob.bobmobileapp.menu.viewholders.formitem.groups;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.menu.viewholders.formitem.base.BaseViewHolder;
import com.bob.bobmobileapp.tools.validators.Validator;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by user on 17/09/2017.
 */

/*
public class GroupViewHolder extends BaseViewHolder {

    protected String text, hintText;
    protected int foregroundColor, textColor, bottomLineColor, hintColor, errorColor;
    protected int layoutBackgroundColor, gravity, width, height, textSize;
    protected Typeface textFont;
    protected Drawable layoutBackground;

    protected TextInputLayout textInputLayout;
    protected ViewGroup viewGroup;
    protected ArrayList<TextView> textViews;
    protected ArrayList<String> items;
    protected int itemsColor;
    protected ArrayList<Integer> selectedItems, disabledItems;


    public GroupViewHolder(Context context, View view, Validator validator) {
        super(context, view);
    }

    public GroupViewHolder(Context context, View view) {
        this(context, view, null);
    }

    public ViewGroup getViewGroup() {
        return viewGroup;
    }

    public void setViewGroup(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    public ViewGroup getTextInputLayout() {
        return textInputLayout;
    }

    public void setTextInputLayout(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
    }

    @Override
    protected void initView(View view) {
        setTextInputLayout((TextInputLayout) view.findViewById(R.id.text_input_layout));
        setViewGroup((ViewGroup) view.findViewById(R.id.view_group));
    }

    @Override
    protected void initialize() {
        this.textViews = new ArrayList<TextView>();
        this.items = new ArrayList<String>();
        this.itemsColor = ContextCompat.getColor(context, R.color.colorPrimary);
        this.selectedItems =  new ArrayList<Integer>();
        this.disabledItems =  new ArrayList<Integer>();

    }

    @Override
    protected void updateProperties(HashMap<String, String> properties) {
        String curProperty;
        if ((curProperty = properties.get("items")) != null) {
            this.items = new ArrayList<String>(Arrays.asList(new Gson().fromJson(curProperty, String[].class)));
        }
        if ((curProperty = properties.get("selected_items")) != null) {
            this.selectedItems = new ArrayList<Integer>(Arrays.asList(new Gson().fromJson(curProperty, Integer[].class)));
        }
        if ((curProperty = properties.get("items_color")) != null) {
            try {
                this.itemsColor = Color.parseColor(curProperty);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("disabled_items")) != null) {
            this.disabledItems = new ArrayList<Integer>(Arrays.asList(new Gson().fromJson(curProperty, Integer[].class)));
        }

    }

    @Override
    protected void configure() {

        if (textInputLayout != null) {
            this.textInputLayout.setBackgroundColor(this.layoutBackgroundColor);
            this.textInputLayout.setBackground(this.layoutBackground);
            this.textInputLayout.setTypeface(this.textFont);
            this.textInputLayout.setHint(this.hintText);
            this.setErrorTextColor(this.errorColor);
        }

        viewGroup.removeAllViews();

        this.setTextViews();
        for (TextView textView : this.textViews) {
            this.configureTextView(textView);
        }
    }

    protected void setTextViews() {
        int index = 0;
        for (String item : this.items) {
            TextView textView = new TextView(this.context);
            textView.setText(item);
            viewGroup.addView(textView, index);
        }
    }

    protected void configureTextView(TextView textView) {
        textView.setTextColor(this.fontColor);
        textView.getBackground().setColorFilter(this.lineColor, PorterDuff.Mode.SRC_ATOP);
        textView.setTextSize(this.fontSize);
        textView.setTypeface(this.fontType);
        if (this.italicText && this.boldText) {
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD_ITALIC);
        } else {
            if (this.boldText) {
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
            }
            if (this.italicText) {
                textView.setTypeface(textView.getTypeface(), Typeface.ITALIC);
            }
        }
        if (this.underlineText) {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }
        textView.setGravity(this.gravity);
    }

}
*/