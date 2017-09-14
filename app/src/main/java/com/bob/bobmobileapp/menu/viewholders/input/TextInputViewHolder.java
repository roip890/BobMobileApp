package com.bob.bobmobileapp.menu.viewholders.input;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.menu.viewholders.base.TextViewHolder;
import com.bob.bobmobileapp.tools.Validator;

/**
 * Created by user on 01/09/2017.
 */

public class TextInputViewHolder extends TextViewHolder {


    public TextInputViewHolder(Context context, View view, Validator validator) {
        super(context, view, validator);
    }

    @Override
    protected void initView(View view) {
        setTextInputLayout((TextInputLayout) view.findViewById(R.id.text_input_layout));
        setTextField((TextInputEditText) view.findViewById(R.id.edit_text));
    }

}
