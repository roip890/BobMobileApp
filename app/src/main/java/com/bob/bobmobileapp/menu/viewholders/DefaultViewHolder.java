package com.bob.bobmobileapp.menu.viewholders;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.realm.objects.FormItem;
import com.julianraj.validatedtextinputlayout.ValidatedTextInputLayout;

/**
 * Created by user on 01/09/2017.
 */

public class DefaultViewHolder extends RecyclerView.ViewHolder {

    private Context context;

    public DefaultViewHolder(Context context, View view) {
        super(view);
        this.context = context;
    }


}
