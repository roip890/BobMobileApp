package com.bob.bobmobileapp.menu.viewholders;

import android.content.Context;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by user on 01/09/2017.
 */

public class RadioButtonViewHolder extends TimeViewHolder {

    protected ArrayList<String> items;

    public RadioButtonViewHolder(Context context, View view) {
        super(context, view);
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.items = new ArrayList<String>();
        this.hint = "Please Choose:";
    }

    @Override
    protected void configure() {
        super.configure();
        this.textField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(context)
                        .title(hint)
                        .items(items)
                        .itemsDisabledIndices(1, 3)
                        .itemsCallbackSingleChoice(2, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                return false;
                            }
                        })
                        .positiveText("Ok")
                        .show();
            }
        });
    }

    @Override
    protected void getValue(HashMap<String, String> properties) {
        String curProperty;
        if ((curProperty = properties.get("items")) != null) {
            this.items = new ArrayList<String>(Arrays.asList(new Gson().fromJson(curProperty, String[].class)));
        }
    }

    @Override
    protected void setValue() {
        if (items.size() > 0) {
            this.textField.setText(items.get(0));
        }
    }


}
