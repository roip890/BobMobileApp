package com.bob.bobmobileapp.menu.viewholders.dialog;

import android.content.Context;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.Arrays;

/**
 * Created by user on 17/09/2017.
 */

public class MultiChoiseDialogViewHolder extends ListDialogViewHolder {

    public MultiChoiseDialogViewHolder(Context context, View view) {
        super(context, view);
    }

    @Override
    protected void setItemsCallback() {
        this.dialogBuilder.itemsCallbackMultiChoice((Integer[])this.dialogDisabledItems.toArray(), new MaterialDialog.ListCallbackMultiChoice() {
            @Override
            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                dialogSelectedItems.clear();
                dialogSelectedItems.addAll(Arrays.asList(which));
                return false;
            }
        });
    }

}
