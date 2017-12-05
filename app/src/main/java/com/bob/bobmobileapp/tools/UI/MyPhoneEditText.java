package com.bob.bobmobileapp.tools.UI;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;
import android.util.Xml;
import android.widget.LinearLayout;

import com.bob.bobmobileapp.R;
import com.hbb20.CountryCodePicker;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created by user on 04/10/2017.
 */

public class MyPhoneEditText extends MyTextView {
    protected CountryCodePicker countryCodePicker;
    protected LinearLayout textViewLayout;

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
    protected void initTextViewUIObject(Context context) {
        XmlPullParser linearLayoutParser = getResources().getXml(R.xml.linear_layout_horizontal);
        try {
            linearLayoutParser.next();
            linearLayoutParser.nextTag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AttributeSet linearLayoutAttrs = Xml.asAttributeSet(linearLayoutParser);
        this.textViewLayout = new LinearLayout(context, linearLayoutAttrs);

        XmlPullParser countryCodePickerParser = getResources().getXml(R.xml.view_default_attribute);
        try {
            countryCodePickerParser.next();
            countryCodePickerParser.nextTag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AttributeSet countryCodePickerAttrs = Xml.asAttributeSet(countryCodePickerParser);
        this.countryCodePicker = new CountryCodePicker(context,countryCodePickerAttrs);

        XmlPullParser textViewParser = getResources().getXml(R.xml.view_default_attribute);
        try {
            textViewParser.next();
            textViewParser.nextTag();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AttributeSet textViewAttrs = Xml.asAttributeSet(textViewParser);
        this.textView = new TextInputEditText(context, textViewAttrs);
    }

    @Override
    protected void addViews() {
        this.textViewLayout.addView(this.countryCodePicker);
        this.textViewLayout.addView(this.textView);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        this.addView(this.textViewLayout, params);
    }

}
