package com.bob.bobmobileapp.menu.viewholders.dialog;

import android.content.Context;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by user on 17/09/2017.
 */

public class SingleChoiseDialogViewHolder extends ListDialogViewHolder {

    public SingleChoiseDialogViewHolder(Context context, View view) {
        super(context, view);
    }

    @Override
    protected void setItemsCallback() {
        this.dialogBuilder.itemsCallbackSingleChoice(this.dialogSelectedItems.get(0), new MaterialDialog.ListCallbackSingleChoice() {
            @Override
            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                dialogSelectedItems.clear();
                dialogSelectedItems.add(which);
                return false;
            }
        });
    }

}
