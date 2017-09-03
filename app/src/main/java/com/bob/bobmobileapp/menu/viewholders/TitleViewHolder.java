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

public class TitleViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private TextView textView;

    public TitleViewHolder(Context context, View view) {
        super(view);
        this.context = context;
        setTextView((TextView) view.findViewById(R.id.text_view));
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public void configureFormItem(FormItem formItem) {

    }


}
