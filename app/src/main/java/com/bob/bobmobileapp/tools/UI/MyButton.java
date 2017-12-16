package com.bob.bobmobileapp.tools.UI;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.Button;

import com.bob.bobmobileapp.R;

/**
 * Created by User on 22/11/2017.
 */

public class MyButton extends MyTextView {

    protected int buttonColor;

    public MyButton(Context context) {
        this(context, null);
    }

    public MyButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.buttonColor = ContextCompat.getColor(context, R.color.colorPrimaryLight);
        this.setBottomLineEnable(false);
    }

    @Override
    protected void createMainView() {
        this.textView = new Button(this.getContext());
    }

    public void setButtonColor(int color) {
        this.buttonColor = color;
        this.paintButtonColor(color);
    }

    public void paintButtonColor(int color) {
        ViewCompat.setBackgroundTintList(this.textView, ColorStateList.valueOf(color));
    }

}
