package com.bob.bobmobileapp.menu.viewholders.formitem.text;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.menu.viewholders.formitem.base.BaseViewHolder;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;
import com.bob.bobmobileapp.tools.validators.Validator;

import java.util.HashMap;


/**
 * Created by user on 01/09/2017.
 */

public class TextViewViewHolder extends BaseViewHolder {

    protected MyTextView textView;

    public TextViewViewHolder(Context context, View view, Validator validator) {
        super(context, view, validator);
    }

    public TextViewViewHolder(Context context, View view) {
        this(context, view, null);
    }

    public MyTextView getTextView() {
        return textView;
    }

    public void setTextView(MyTextView textView) {
        this.textView = textView;
    }

    @Override
    protected void initView(View view) {
        this.setTextView((MyTextView) view.findViewById(R.id.my_text_view));
    }

    @Override
    protected void initialize() {
        this.textView.setBackgroundColor(ContextCompat.getColor(context, R.color.windowBackground));
        this.textView.setGravity(finals.gravity.get("start"));
        this.textView.setText("");
        this.textView.setTitleText("Please enter your text");

        this.textView.setTextColor(ContextCompat.getColor(context, R.color.textColorPrimary));
        this.textView.setTitleColor(ContextCompat.getColor(context, R.color.textColorPrimary));
        this.textView.setBottomLineColor(ContextCompat.getColor(context, R.color.textColorPrimary));
        this.textView.setErrorTextColor(ContextCompat.getColor(context, R.color.textColorPrimary));
        this.textView.setStartDrawableColor(ContextCompat.getColor(context, R.color.textColorPrimary));
        this.textView.setEndDrawableColor(ContextCompat.getColor(context, R.color.textColorPrimary));

        this.textView.setTextSize(this.convertSpToPixels(8));
        this.textView.setTextInputType(finals.inputTypes.get("none"));

        this.textView.setBoldEnable(false);
        this.textView.setUnderlineEnable(false);
        this.textView.setItalicEnable(false);

        this.textView.setStartDrawable(null);
        this.textView.setEndDrawable(null);
    }

    @Override
    protected void updateProperties(HashMap<String, String> properties) {
        String curProperty;
        if ((curProperty = properties.get("layout_background_color")) != null) {
            try {
                this.textView.setBackgroundColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("layout_background")) != null) {
            this.textView.setBackgroundImage(ContextCompat.getDrawable(context, context.getResources().getIdentifier(curProperty, "drawable", context.getPackageName())));
        }

        if ((curProperty = properties.get("width")) != null) {
            try {
                this.textView.setWidth(Integer.parseInt(curProperty));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("height")) != null) {
            try {
                this.textView.setHeight(Integer.parseInt(curProperty));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("gravity")) != null) {
            this.textView.setGravity(finals.gravity.get(curProperty));
        }

        if ((curProperty = properties.get("text")) != null) {
            this.textView.setText(curProperty);
        }
        if ((curProperty = properties.get("hint_text")) != null) {
            this.textView.setTitleText(curProperty);
        }

        if ((curProperty = properties.get("text_color")) != null) {
            try {
                this.textView.setTitleColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("hint_color")) != null) {
            try {
                this.textView.setTitleColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("bottom_line_color")) != null) {
            try {
                this.textView.setBottomLineColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("error_color")) != null) {
            try {
                this.textView.setErrorTextColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("start_drawable_color")) != null) {
            try {
                this.textView.setStartDrawableColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("end_drawable_color")) != null) {
            try {
                this.textView.setEndDrawableColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        if ((curProperty = properties.get("font_size")) != null) {
            try {
                this.textView.setTextSize(this.convertPixelsToSp(Integer.parseInt(curProperty)  ));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("font_type")) != null) {
            this.textView.setTextTypeface(this.findTypeface(curProperty));
        }
        if ((curProperty = properties.get("input_type")) != null) {
            this.textView.setTextInputType(finals.inputTypes.get(curProperty));
        }

        if ((curProperty = properties.get("bold_text")) != null) {
            if (curProperty.equals("true")) {
                this.textView.setBoldEnable(true);
            } else if (curProperty.equals("false")) {
                this.textView.setBoldEnable(false);
            }
        }
        if ((curProperty = properties.get("underline_text")) != null) {
            if (curProperty.equals("true")) {
                this.textView.setUnderlineEnable(true);
           } else if (curProperty.equals("false")) {
                this.textView.setUnderlineEnable(false);
            }
        }
        if ((curProperty = properties.get("italic_text")) != null) {
            if (curProperty.equals("true")) {
                this.textView.setItalicEnable(true);
            } else if (curProperty.equals("false")) {
                this.textView.setItalicEnable(true);
            }
        }

        if ((curProperty = properties.get("start_drawable")) != null) {
            this.textView.setStartDrawable(this.findDrawable(curProperty));
        }
        if ((curProperty = properties.get("end_drawable")) != null) {
            this.textView.setEndDrawable(this.findDrawable(curProperty));
        }
    }

}
