package com.bob.bobmobileapp.tools.UI;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.text.TextPaint;
import android.util.AttributeSet;

import com.vstechlab.easyfonts.EasyFonts;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by User on 10/12/2017.
 */

public class CustomTextInputLayout extends TextInputLayout {

    private Object collapsingTextHelper;
    private Method setCollapsedTypefaceMethod;
    private Method setExpandedTypefaceMethod;
    private Object textPaint;
    private Method setUnderline;

    public CustomTextInputLayout(Context context) {
        this(context, null);
    }

    public CustomTextInputLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        try {
            Field cthField = TextInputLayout.class
                    .getDeclaredField("mCollapsingTextHelper");
            cthField.setAccessible(true);
            collapsingTextHelper = cthField.get(this);

            setCollapsedTypefaceMethod = collapsingTextHelper
                    .getClass().getDeclaredMethod("setCollapsedTypeface", Typeface.class);
            setCollapsedTypefaceMethod.setAccessible(true);

            setExpandedTypefaceMethod = collapsingTextHelper
                    .getClass().getDeclaredMethod("setExpandedTypeface", Typeface.class);
            setExpandedTypefaceMethod.setAccessible(true);

            Field tpf = collapsingTextHelper.getClass().getDeclaredField("mTextPaint");
            tpf.setAccessible(true);
            textPaint = tpf.get(collapsingTextHelper);

            Method[] mtds = collapsingTextHelper.getClass().getMethods();
            Method[] mtds2 = textPaint.getClass().getMethods();
            setUnderline = textPaint.getClass().getDeclaredMethod("setUnderlineText", int.class, float.class);
            setUnderline.setAccessible(true);


        }
        catch (Exception e) {
            collapsingTextHelper = null;
            setCollapsedTypefaceMethod = null;
            setExpandedTypefaceMethod = null;
            e.printStackTrace();
        }
    }

    public void setCollapsedTypeface(Typeface typeface) {
        if (collapsingTextHelper == null) {
            return;
        }

        try {
            setCollapsedTypefaceMethod.invoke(collapsingTextHelper, typeface);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void setExpandedTypeface(Typeface typeface) {
        if (collapsingTextHelper == null) {
            return;
        }

        try {
            setExpandedTypefaceMethod.invoke(collapsingTextHelper, typeface);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void setUnderline(boolean isUnderline) {

        try {
            if (isUnderline) {
                setUnderline.invoke(textPaint, Color.GREEN, 1.0f);
            } else {
                setUnderline.invoke(textPaint, ~Paint.UNDERLINE_TEXT_FLAG);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
