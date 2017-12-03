package com.bob.bobmobileapp.tools.UI;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bob.bobmobileapp.R;

import java.lang.reflect.Field;

/**
 * Created by user on 04/10/2017.
 */

public class MyEditText extends MyTextView {
    protected int cursorColor;

    public MyEditText(Context context) {
        this(context, null);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void initTextViewUIObject(Context context) {
        this.textView = new TextInputEditText(context);
    }

    @Override
    protected void initColors(Context context){
        super.initColors(context);
        this.cursorColor = ContextCompat.getColor(context, R.color.textColorPrimary);
    }

    public void setCursorColor(int color) {
        this.cursorColor = color;
        this.paintCursorColor(color);
    }

    protected void paintCursorColor(int color) {
        try {
            Field fCursorDrawableRes =
                    TextView.class.getDeclaredField("mCursorDrawableRes");
            fCursorDrawableRes.setAccessible(true);
            int mCursorDrawableRes = fCursorDrawableRes.getInt(this.textView);
            Field fEditor = TextView.class.getDeclaredField("mEditor");
            fEditor.setAccessible(true);
            Object editor = fEditor.get(this.textView);
            Class<?> clazz = editor.getClass();
            Field fCursorDrawable = clazz.getDeclaredField("mCursorDrawable");
            fCursorDrawable.setAccessible(true);

            Drawable[] drawables = new Drawable[2];
            Resources res = this.textView.getContext().getResources();
            drawables[0] = res.getDrawable(mCursorDrawableRes);
            drawables[1] = res.getDrawable(mCursorDrawableRes);
            drawables[0].setColorFilter(color, PorterDuff.Mode.SRC_IN);
            drawables[1].setColorFilter(color, PorterDuff.Mode.SRC_IN);
            fCursorDrawable.set(editor, drawables);
        } catch (final Throwable ignored) {
        }
    }

    @Override
    public void setError(String text) {
        super.setError(text);
        if (text != null) {
            this.paintCursorColor(this.errorColor);
        } else {
            this.paintCursorColor(this.cursorColor);
        }
    }
}
