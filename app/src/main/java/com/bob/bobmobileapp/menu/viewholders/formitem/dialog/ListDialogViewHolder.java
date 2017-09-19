package com.bob.bobmobileapp.menu.viewholders.formitem.dialog;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.finals;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by user on 17/09/2017.
 */

public class ListDialogViewHolder extends DialogViewHolder {

    protected ArrayList<String> dialogItems;
    protected int dialogItemsColor;
    protected GravityEnum dialogItemsGravity;
    protected ArrayList<Integer> dialogSelectedItems, dialogDisabledItems;

    public ListDialogViewHolder(Context context, View view) {
        super(context, view);
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.dialogItems = new ArrayList<String>();
        this.dialogSelectedItems = new ArrayList<Integer>();
        this.dialogSelectedItems.add(0);
        this.dialogItemsColor = ContextCompat.getColor(context, R.color.colorPrimary);
        this.dialogItemsGravity = finals.dialogGravity.get("start");
        this.dialogDisabledItems = new ArrayList<Integer>();
    }

    protected void updateProperties(HashMap<String, String> properties) {
        super.updateProperties(properties);
        String curProperty;
        if ((curProperty = properties.get("dialog_items")) != null) {
            this.dialogItems = new ArrayList<String>(Arrays.asList(new Gson().fromJson(curProperty, String[].class)));
        }
        if ((curProperty = properties.get("dialog_selected_items")) != null) {
            this.dialogSelectedItems = new ArrayList<Integer>(Arrays.asList(new Gson().fromJson(curProperty, Integer[].class)));
        }
        if ((curProperty = properties.get("dialog_items_color")) != null) {
            try {
                this.dialogItemsColor = Color.parseColor(curProperty);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_items_gravity")) != null) {
            this.dialogItemsGravity = finals.dialogGravity.get(curProperty);
        }
        if ((curProperty = properties.get("dialog_disabled_items")) != null) {
            this.dialogDisabledItems = new ArrayList<Integer>(Arrays.asList(new Gson().fromJson(curProperty, Integer[].class)));
        }
    }

    @Override
    protected void configure() {
        super.configure();
        this.dialogBuilder.items(this.dialogItems)
                .itemsColor(this.dialogItemsColor)
                .itemsGravity(this.dialogItemsGravity)
                .itemsDisabledIndices((Integer[])this.dialogDisabledItems.toArray());
        this.setItemsCallback();
    }

    @Override
    protected void setValue() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, il = this.dialogItems.size(); i < il; i++) {
            if (i > 0)
                stringBuilder.append(",");
            stringBuilder.append(this.dialogItems.get(i));
        }

        this.textView.setText(stringBuilder.toString());
    }

    protected void setItemsCallback() {
        this.dialogBuilder.itemsCallback(new MaterialDialog.ListCallback() {
            @Override
            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                dialogSelectedItems.clear();
                dialogSelectedItems.add(position);
            }
        });
    }

}
