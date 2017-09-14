package com.bob.bobmobileapp.menu.viewholders.output;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.menu.viewholders.base.TextViewHolder;
import com.bob.bobmobileapp.realm.objects.FormItem;
import com.bob.bobmobileapp.tools.Validator;

import java.util.HashMap;

/**
 * Created by user on 01/09/2017.
 */

public class TextOutputViewHolder extends TextViewHolder {

    protected String textLable;

    public TextOutputViewHolder(Context context, View view, Validator validator) {
        super(context, view, validator);
    }

    @Override
    protected void initView(View view) {
        setTextInputLayout((TextInputLayout) view.findViewById(R.id.text_input_layout));
        setTextField((TextInputEditText) view.findViewById(R.id.edit_text));
    }

    @Override
    protected void updateProperties(HashMap<String, String> properties) {
        super.updateProperties(properties);
        this.getTextLabel(properties);
    }

    @Override
    protected void configure() {
        super.configure();
        this.setText();
    }

    protected void getTextLabel(HashMap<String, String> properties) {
        String curProperty;
        if ((curProperty = properties.get("text_lable")) != null) {
            this.textLable = curProperty;
        }
    }

    protected void setText() {
        this.textField.setText(this.textLable);
    }
}
