package com.bob.bobmobileapp.menu.viewholders;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.realm.objects.FormItem;
import com.julianraj.validatedtextinputlayout.ValidatedTextInputLayout;

/**
 * Created by user on 01/09/2017.
 */

public abstract class LabelViewHolder extends RecyclerView.ViewHolder {


    protected Context context;
    protected ValidatedTextInputLayout textInputLayout;
    protected TextView textView;

    public LabelViewHolder(Context context, View view) {
        super(view);
        this.context = context;
        setTextInputLayout((ValidatedTextInputLayout) view.findViewById(R.id.text_input_layout));
        setTextView((TextView) view.findViewById(R.id.text_view));
    }

    public ValidatedTextInputLayout getTextInputLayout() {
        return textInputLayout;
    }

    public void setTextInputLayout(ValidatedTextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public abstract void configureFormItem(FormItem formItem);


}
