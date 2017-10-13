package com.bob.bobmobileapp.menu.viewholders.formitem.text;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.menu.viewholders.formitem.base.BaseViewHolder;
import com.bob.bobmobileapp.tools.validators.Validator;

import java.util.HashMap;


/**
 * Created by user on 01/09/2017.
 */

public class TextViewViewHolder extends BaseViewHolder {

    protected Validator validator;
    protected TextView textView;
    protected String textLable;
    private View bottomLine;
    protected int inputType;

    public TextViewViewHolder(Context context, View view, Validator validator) {
        super(context, view, validator);
        this.validator = validator;
    }

    public TextViewViewHolder(Context context, View view) {
        this(context, view, null);
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public View getBottomLine() {
        return bottomLine;
    }

    public void setBottomLine(View bottomLine) {
        this.bottomLine = bottomLine;
    }

    @Override
    protected void initView(View view) {
        setTextInputLayout((TextInputLayout) view.findViewById(R.id.text_input_layout));
        setTextView((TextInputEditText) view.findViewById(R.id.text_view));
        setBottomLine(view.findViewById(R.id.text_view_bottom_line));
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.inputType = finals.inputTypes.get("none");
    }

    @Override
    protected void updateProperties(HashMap<String, String> properties) {
        String curProperty;
        if ((curProperty = properties.get("input_type")) != null) {
            this.inputType = finals.inputTypes.get(curProperty);
        }
        if ((curProperty = properties.get("text_lable")) != null) {
            this.textLable = curProperty;
        }
    }

    @Override
    protected void configure() {
        if (textInputLayout != null) {
            this.textInputLayout.setBackground(this.layoutBackground);
            this.textInputLayout.setBackgroundColor(this.layoutBackgroundColor);
            this.textInputLayout.setTypeface(this.fontType);
        }
        startDrawable.setColorFilter(new PorterDuffColorFilter(this.fontColor, PorterDuff.Mode.SRC_IN));
        this.setDrawables(startDrawable, null, null, null);

        this.textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if ((motionEvent.getAction() == MotionEvent.ACTION_UP) && !textView.getText().equals("")
                && isOnResetDrawable(motionEvent.getX()) && getDrawables()[2] != null) {
                    textView.setText("");
                }
                return true;
            }
        });

        this.textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
                if (!text.equals("")) {
                    startDrawable.setColorFilter(new PorterDuffColorFilter(fontColor, PorterDuff.Mode.SRC_IN));
                    setDrawables(startDrawable, null, null, null);
                }
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                SpannableString span = new SpannableString(text.toString());
                if(underlineText)
                {
                    span.setSpan(new UnderlineSpan(),0, text.length() - 1 , 0);
                }
                if(boldText)
                {
                    span.setSpan(new StyleSpan(Typeface.BOLD), 0, text.length() - 1 ,  0);
                }
                if(italicText)
                {
                    span.setSpan(new StyleSpan(Typeface.ITALIC), 0, text.length() - 1 , 0);
                }
                textView.setText(span, TextView.BufferType.SPANNABLE);
                validateTextField(text.toString());
            }

            @Override
            public void afterTextChanged(Editable text) {
                validateTextField(text.toString());
            }
        });

        this.textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateTextField(((TextView) v).getText().toString());
                }
            }
        });

        this.textView.setInputType(this.inputType);
        this.textView.setGravity(this.gravity);
        this.textView.setTextColor(this.fontColor);
        this.bottomLine.setBackgroundColor(ContextCompat.getColor(this.context, R.color.colorPrimary));
        //ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(
        //        ViewGroup.MarginLayoutParams.WRAP_CONTENT,
        //        (int)(context.getResources().getDisplayMetrics().density * 2)
        //);
        //params.setMarginStart((int)(context.getResources().getDisplayMetrics().density *
        //        this.textView.getTotalPaddingStart()));
        //this.bottomLine.setLayoutParams(params);
        //this.textView.getBackground().setColorFilter(this.lineColor, PorterDuff.Mode.SRC_ATOP);
        this.setErrorTextColor(this.errorColor);
        this.textView.setTextSize(this.fontSize);
        this.textView.setTypeface(this.fontType);
        this.textView.setHint(this.hint);
        if (this.italicText && this.boldText) {
            this.textView.setTypeface(this.textView.getTypeface(), Typeface.BOLD_ITALIC);
        } else {
            if (this.boldText) {
                this.textView.setTypeface(this.textView.getTypeface(), Typeface.BOLD);
            }
            if (this.italicText) {
                this.textView.setTypeface(this.textView.getTypeface(), Typeface.ITALIC);
            }
        }
        if (this.underlineText) {
            this.textView.setPaintFlags(this.textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }
        this.setValue();
    }

    protected void setValue() {
        this.textView.setText(this.textLable);
    }

    protected void validateTextField(String text) {
        if (!this.isValid(text)) {
            if (text.equals("")) {
                startDrawable.setColorFilter(new PorterDuffColorFilter(this.fontColor, PorterDuff.Mode.SRC_IN));
                this.setDrawables(startDrawable, null, null, null);
                if (notEmpty) {
                    setError(emptyErrorLable);
                } else {
                    setError(errorLable);
                }
            } else {
                Drawable resetDrawable = findDrawable("gmi_close_circle");
                resetDrawable.setColorFilter(new PorterDuffColorFilter(this.errorColor, PorterDuff.Mode.SRC_IN));
                startDrawable.setColorFilter(new PorterDuffColorFilter(this.errorColor, PorterDuff.Mode.SRC_IN));
                this.setDrawables(startDrawable, null, resetDrawable, null);
                setError(errorLable);
            }
        } else if (!text.equals("")) {
            Drawable resetDrawable = findDrawable("gmi_close_circle");
            resetDrawable.setColorFilter(new PorterDuffColorFilter(this.fontColor, PorterDuff.Mode.SRC_IN));
            startDrawable.setColorFilter(new PorterDuffColorFilter(this.fontColor, PorterDuff.Mode.SRC_IN));
            this.setDrawables(startDrawable, null, resetDrawable, null);
            setError(null);
        } else {
            startDrawable.setColorFilter(new PorterDuffColorFilter(this.fontColor, PorterDuff.Mode.SRC_IN));
            this.setDrawables(startDrawable, null, null, null);
            if (notEmpty) {
                setError(emptyErrorLable);
            }
        }
    }

    protected boolean isValid(String text) {
        if (this.validator != null) {
            return this.validator.validate(text) == null;
        }
        return true;
    }

    protected boolean isOnResetDrawable(float x) {
        if (context.getResources().getBoolean(R.bool.is_right_to_left)) {
            if(x >= this.textView.getTotalPaddingRight()) {
                return true;
            }
        } else {
            if(x <= this.textView.getTotalPaddingLeft()) {
                return true;
            }
        }
        return false;
    }

    protected void setError(String errorLable) {
        if (this.textInputLayout != null) {
            this.textInputLayout.setError(errorLable);
        } else {
            this.textView.setError(errorLable);
        }
    }

    protected void setDrawables(Drawable start, Drawable top, Drawable end, Drawable bottom) {
        this.textView.setCompoundDrawablesRelative(start, top, end, bottom);
    }

    protected Drawable[] getDrawables() {
        return this.textView.getCompoundDrawablesRelative();
    }
}
