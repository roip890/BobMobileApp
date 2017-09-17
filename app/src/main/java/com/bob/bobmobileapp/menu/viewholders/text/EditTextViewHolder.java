package com.bob.bobmobileapp.menu.viewholders.text;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.Validator;

/**
 * Created by user on 01/09/2017.
 */

public class EditTextViewHolder extends TextViewViewHolder {


    public EditTextViewHolder(Context context, View view, Validator validator) {
        super(context, view, validator);
    }

    @Override
    protected void initView(View view) {
        setTextInputLayout((TextInputLayout) view.findViewById(R.id.text_input_layout));
        setTextView((TextInputEditText) view.findViewById(R.id.edit_text));
    }

}
