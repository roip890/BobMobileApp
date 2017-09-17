package com.bob.bobmobileapp.menu.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.menu.viewholders.base.TextViewHolder;
import com.bob.bobmobileapp.menu.viewholders.output.TimeViewHolder;
import com.bob.bobmobileapp.realm.objects.FormItem;

import java.util.List;

/**
 * Created by user on 01/09/2017.
 */

public class FormItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final int FORM_ITEM_DEFAULT = -1;
    private final int FORM_ITEM_TEXT = 0;
    private final int FORM_ITEM_LABEL = 1;
    private final int FORM_ITEM_TITLE = 2;
    private final int FORM_ITEM_TIME = 3;
    private final int FORM_ITEM_DATE = 4;
    private final int FORM_ITEM_PHONE = 5;
    private final int FORM_ITEM_CURRENCY = 6;
    private final int FORM_ITEM_lOCATION_INPUT = 7;
    private final int FORM_ITEM_LOCATION = 8;
    private final int FORM_ITEM_NUMBER = 9;
    private final int FORM_ITEM_COMBO_BOX = 10;
    private final int FORM_ITEM_RADIO_BUTTON = 11;
    private final int FORM_ITEM_CHECK_BOX = 12;
    private final int FORM_ITEM_IMAGE = 13;
    private final int FORM_ITEM_GALLERY = 14;
    private final int FORM_ITEM_MUSIC = 15;
    private final int FORM_ITEM_VIDEO = 16;


    // The items to display in your RecyclerView
    private List<FormItem> formItems;
    private Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public FormItemsAdapter(Context context, List<FormItem> items) {
        this.formItems = items;
        this.context = context;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.formItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (formItems.get(position).getType().equals("text")) {
            return FORM_ITEM_TEXT;
        } else if (formItems.get(position).getType().equals("label")) {
            return FORM_ITEM_LABEL;
        } else if (formItems.get(position).getType().equals("title")) {
            return FORM_ITEM_TITLE;
        } else if (formItems.get(position).getType().equals("time")) {
            return FORM_ITEM_TIME;
        } else if (formItems.get(position).getType().equals("date")) {
            return FORM_ITEM_DATE;
        } else if (formItems.get(position).getType().equals("phone")) {
            return FORM_ITEM_PHONE;
        } else if (formItems.get(position).getType().equals("currency")) {
            return FORM_ITEM_CURRENCY;
        } else if (formItems.get(position).getType().equals("location_input")) {
            return FORM_ITEM_lOCATION_INPUT;
        } else if (formItems.get(position).getType().equals("location")) {
            return FORM_ITEM_LOCATION;
        } else if (formItems.get(position).getType().equals("number")) {
            return FORM_ITEM_NUMBER;
        } else if (formItems.get(position).getType().equals("combo_box")) {
            return FORM_ITEM_COMBO_BOX;
        } else if (formItems.get(position).getType().equals("radio_button")) {
            return FORM_ITEM_RADIO_BUTTON;
        } else if (formItems.get(position).getType().equals("check_box")) {
            return FORM_ITEM_CHECK_BOX;
        } else if (formItems.get(position).getType().equals("image")) {
            return FORM_ITEM_IMAGE;
        } else if (formItems.get(position).getType().equals("gallery")) {
            return FORM_ITEM_GALLERY;
        } else if (formItems.get(position).getType().equals("music")) {
            return FORM_ITEM_MUSIC;
        } else if (formItems.get(position).getType().equals("video")) {
            return FORM_ITEM_VIDEO;
        } else {
            return FORM_ITEM_DEFAULT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) {
            case FORM_ITEM_TEXT:
                return new TextViewHolder(context, inflater.inflate(R.layout.form_item_text_input_layout, viewGroup, false));
            case FORM_ITEM_LABEL:
                return new DefaultLabelViewHolder(context, inflater.inflate(R.layout.form_item_label_layout, viewGroup, false));
            case FORM_ITEM_TITLE:
                return new TitleViewHolder(context, inflater.inflate(R.layout.form_item_title_layout, viewGroup, false));
            case FORM_ITEM_TIME:
                return new TimeViewHolder(context, inflater.inflate(R.layout.form_item_label_layout, viewGroup, false));
            case FORM_ITEM_DATE:
                return new DateViewHolder(context, inflater.inflate(R.layout.form_item_label_layout, viewGroup, false));
            default:
                return new DefaultViewHolder(context, inflater.inflate(R.layout.form_item_default_layout, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        FormItem formItem = this.formItems.get(position);

        switch (viewHolder.getItemViewType()) {
            case FORM_ITEM_TEXT:
                TextViewHolder textViewHolder = (TextViewHolder) viewHolder;
                textViewHolder.configureFormItem(formItem);
                break;
            default:
                break;
        }    }
}
