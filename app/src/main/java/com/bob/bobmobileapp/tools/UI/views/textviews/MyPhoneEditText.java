package com.bob.bobmobileapp.tools.UI.views.textviews;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.util.Xml;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.bob.bobmobileapp.R;
import com.hbb20.CountryCodePicker;

import org.xmlpull.v1.XmlPullParser;

import java.lang.reflect.Field;

/**
 * Created by user on 04/10/2017.
 */

public class MyPhoneEditText extends MyEditText {
    protected CountryCodePicker countryCodePicker;
    protected LinearLayout phoneViewLayout;

    public MyPhoneEditText(Context context) {
        this(context, null);
    }

    public MyPhoneEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyPhoneEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void createMainView() {
        XmlPullParser linearLayoutParser = getResources().getXml(R.xml.linear_layout_horizontal);
        try {
            linearLayoutParser.next();
            linearLayoutParser.nextTag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AttributeSet linearLayoutAttrs = Xml.asAttributeSet(linearLayoutParser);
        this.phoneViewLayout = new LinearLayout(this.getContext(), linearLayoutAttrs);

        XmlPullParser countryCodePickerParser = getResources().getXml(R.xml.view_default_attribute);
        try {
            countryCodePickerParser.next();
            countryCodePickerParser.nextTag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AttributeSet countryCodePickerAttrs = Xml.asAttributeSet(countryCodePickerParser);
        this.countryCodePicker = new CountryCodePicker(this.getContext(),countryCodePickerAttrs);

        XmlPullParser textViewParser = getResources().getXml(R.xml.view_default_attribute);
        try {
            textViewParser.next();
            textViewParser.nextTag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AttributeSet textViewAttrs = Xml.asAttributeSet(textViewParser);
        this.textView = new TextInputEditText(this.getContext(), textViewAttrs);
    }

    @Override
    protected void addMainView() {

        FrameLayout.LayoutParams countryCodePickerParams = new FrameLayout.LayoutParams(
                this.asDP(150),
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        this.phoneViewLayout.addView(this.countryCodePicker, countryCodePickerParams);

        this.phoneViewLayout.addView(this.textView);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        this.mainContainer.addView(this.phoneViewLayout, params);

    }

    @Override
    protected int getStartDrawableStartMargin() {
        if (this.textView.getCompoundDrawablesRelative()[0] != null) {
            return this.textView.getCompoundDrawablesRelative()[0].getIntrinsicWidth() + this.asDP(150);
        } else {
            return this.asDP(150);
        }
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
        this.countryCodePicker.setContentColor(color);
    }
}
