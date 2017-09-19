package com.bob.bobmobileapp.menu.viewholders.formitem.groups;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bob.bobmobileapp.tools.Validator;

/**
 * Created by user on 17/09/2017.
 */

public class MultiChoiseViewHolder extends GroupViewHolder {


    public MultiChoiseViewHolder(Context context, View view, Validator validator) {
        super(context, view);
    }

    public MultiChoiseViewHolder(Context context, View view) {
        this(context, view, null);
    }

    @Override
    protected void configure() {
        super.configure();
        for (Integer index : this.selectedItems) {
            if (index < items.size()) {
                ((RadioButton)viewGroup.getChildAt(index)).setChecked(true);
            }
        }
        for (Integer index : this.disabledItems) {
            if (index < items.size()) {
                ((RadioButton)viewGroup.getChildAt(index)).setEnabled(false);
            }
        }
    }

    @Override
    protected void setTextViews() {
        int index = 0;
        for (String item : this.items) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                CheckBox checkBox = new CheckBox(this.context);
                checkBox.setText(item);
                viewGroup.addView(checkBox, index++);
            } else {
                AppCompatCheckBox checkBox = new AppCompatCheckBox(this.context);
                checkBox.setText(item);
                viewGroup.addView(checkBox, index++);
            }
        }
    }

    @Override
    protected void configureTextView(TextView textView) {
        super.configureTextView(textView);
        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked}
                },
                new int[]{
                        Color.DKGRAY,
                        itemsColor,
                }
        );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ((CheckBox) textView).setButtonTintList(colorStateList);
        } else {
            AppCompatCheckBox checkBox = (AppCompatCheckBox) textView;
            checkBox.setSupportButtonTintList(colorStateList);
        }
    }
}
