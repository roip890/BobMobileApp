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
import android.widget.TextView;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.menu.viewholders.formitem.base.BaseViewHolder;
import com.bob.bobmobileapp.tools.UI.MyTextView;
import com.bob.bobmobileapp.tools.validators.Validator;

import java.util.HashMap;


/**
 * Created by user on 01/09/2017.
 */

public class TextViewViewHolder extends BaseViewHolder {

    protected MyTextView textView;
    protected Validator validator;
    protected String text, hintText;
    protected int foregroundColor, textColor, bottomLineColor, hintColor, errorColor;
    protected int startDrawableColor, endDrawableColor;
    protected int textSize, inputType;
    protected Typeface textType;
    protected Drawable startDrawable, endDrawable;
    protected boolean boldText, underlineText, italicText;


    public TextViewViewHolder(Context context, View view, Validator validator) {
        super(context, view, validator);
        this.validator = validator;
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
        setTextView((MyTextView) view.findViewById(R.id.my_text_view));
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.validator = new Validator() {
            @Override
            public String validate(String text) {
                return null;
            }
        };

        this.text = "";
        this.hintText = "Please enter your text";

        this.foregroundColor = ContextCompat.getColor(context, R.color.textColorPrimary);
        this.textColor = ContextCompat.getColor(context, R.color.textColorPrimary);
        this.hintColor = ContextCompat.getColor(context, R.color.textColorPrimary);
        this.bottomLineColor = ContextCompat.getColor(context, R.color.textColorPrimary);
        this.errorColor = ContextCompat.getColor(context, R.color.textColorPrimary);
        this.startDrawableColor = ContextCompat.getColor(context, R.color.textColorPrimary);
        this.endDrawableColor = ContextCompat.getColor(context, R.color.textColorPrimary);

        this.textSize = (int)(context.getResources().getDimension(R.dimen.text_size_medium));
        this.inputType = finals.inputTypes.get("none");
        this.textType = null;

        this.boldText = false;
        this.underlineText = false;
        this.italicText = false;

        this.startDrawable = null;
        this.initEndDrawable();


    }

    @Override
    protected void updateProperties(HashMap<String, String> properties) {
        String curProperty;

        if ((curProperty = properties.get("text")) != null) {
            this.text = curProperty;
        }
        if ((curProperty = properties.get("hint_text")) != null) {
            this.hintText = curProperty;
        }

        if ((curProperty = properties.get("input_type")) != null) {
            this.inputType = finals.inputTypes.get(curProperty);
        }

        if ((curProperty = properties.get("foreground_color")) != null) {
            try {
                this.foregroundColor = Color.parseColor(curProperty);
                this.textColor = Color.parseColor(curProperty);
                this.hintColor = Color.parseColor(curProperty);
                this.bottomLineColor = Color.parseColor(curProperty);
                this.errorColor = Color.parseColor(curProperty);
                this.startDrawableColor = Color.parseColor(curProperty);
                this.endDrawableColor = Color.parseColor(curProperty);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("text_color")) != null) {
            try {
                this.textColor = Color.parseColor(curProperty);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        if ((curProperty = properties.get("error_color")) != null) {
            try {
                this.errorColor = Color.parseColor(curProperty);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("font_size")) != null) {
            try {
                this.fontSize = Integer.parseInt(curProperty);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("start_drawable")) != null) {
            this.startDrawable = this.findDrawable(curProperty);
        }
        if ((curProperty = properties.get("layout_background")) != null) {
            this.layoutBackground = ContextCompat.getDrawable(context, context.getResources().getIdentifier(curProperty, "drawable", context.getPackageName()));
        }
        if ((curProperty = properties.get("font_type")) != null) {
            this.fontType = this.findTypeface(curProperty);
        }
        if ((curProperty = properties.get("error_lable")) != null) {
            this.errorLable = curProperty;
        }
        if ((curProperty = properties.get("empty_error_lable")) != null) {
            this.emptyErrorLable = curProperty;
        }
        if ((curProperty = properties.get("bold_text")) != null) {
            if (curProperty.equals("true")) {
                this.boldText = true;
            } else if (curProperty.equals("false")) {
                this.boldText = false;
            }
        }
        if ((curProperty = properties.get("underline_text")) != null) {
            if (curProperty.equals("true")) {
                this.underlineText = true;
            } else if (curProperty.equals("false")) {
                this.underlineText = false;
            }
        }
        if ((curProperty = properties.get("italic_text")) != null) {
            if (curProperty.equals("true")) {
                this.italicText = true;
            } else if (curProperty.equals("false")) {
                this.italicText = false;
            }
        }
        if ((curProperty = properties.get("not_empty")) != null) {
            if (curProperty.equals("true")) {
                this.notEmpty = true;
            } else if (curProperty.equals("false")) {
                this.notEmpty = false;
            }
        }
        if ((curProperty = properties.get("hint")) != null) {
            this.errorLable = curProperty;
        }

    }

    @Override
    protected void configure() {
        //this.textView.setBackground(this.layoutBackground);
        //this.textView.setBackgroundColor(this.layoutBackgroundColor);
        //((LinearLayout.LayoutParams) this.textView.getLayoutParams()).gravity = this.gravity;
        this.textView.setValidator(this.validator);

        this.setValue();
        this.textView.setHint(this.hintText);

        this.textView.setTextColor(this.textColor);
        this.textView.setHintColor(this.hintColor);
        this.textView.setBottomLineColor(this.bottomLineColor);
        this.textView.setErrorTextColor(this.errorColor);
        this.textView.setStartDrawableColor(this.startDrawableColor);
        this.textView.setEndDrawableColor(this.endDrawableColor);

        this.textView.setTextSize(this.textSize);
        this.textView.setTextInputType(this.inputType);
        this.textView.setTextTypeface(this.textType);

        this.textView.setBoldEnable(this.boldText);
        this.textView.setItalicEnable(this.italicText);
        this.textView.setUnderlineEnable(this.underlineText);

        this.textView.setStartDrawable(this.startDrawable);
        this.textView.setEndDrawable(this.endDrawable);
    }

    protected void setValue() {
        this.textView.setText(this.text);
    }

    protected void initEndDrawable() {
        this.endDrawable = this.findDrawable("gmi_close_circle");
        this.textView.setEndDrawableOnClickListener(new MyTextView.DrawableOnClickListener() {
            @Override
            public void onDrawableClick() {
                if (!textView.getText().toString().equals("")) {
                    textView.setText("");
                    textView.setEndDrawableEnable(false);
                }
            }
        });
        this.textView.setEndDrawableOnFocusOnly(true);

    }
}
