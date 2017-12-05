package com.bob.bobmobileapp.tools.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 03/12/2017.
 */

public class MyCheckBoxGroup extends MyTextViewList {

    public final int UNCHACKED = 0;
    public final int CHACKED = 1;

    //constructors
    public MyCheckBoxGroup(Context context) {
        this(context, null);
    }

    public MyCheckBoxGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCheckBoxGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.setOrientation(VERTICAL);
        this.addViews();
    }

    @Override
    protected TextView createTextView() {
        return new CheckBox(this.getContext());
    }

    public ArrayList<CheckBox> getChackedView() {
        int children = this.textViewsListView.getChildCount();
        ArrayList<CheckBox> checked = new ArrayList<CheckBox>();
        for (int i = 0; i < children; i++) {
            if (this.textViewsListView.getChildAt(i) instanceof CheckBox &&
                    ((CheckBox)this.textViewsListView.getChildAt(i)).isChecked()) {
                checked.add(((CheckBox)this.textViewsListView.getChildAt(i)));
            }
        }
        return checked;
    }

    public ArrayList<Integer> getChackedIndices() {
        int children = this.textViewsListView.getChildCount();
        ArrayList<Integer> checked = new ArrayList<Integer>();
        for (int i = 0; i < children; i++) {
            if (this.textViewsListView.getChildAt(i) instanceof CheckBox &&
                    ((CheckBox)this.textViewsListView.getChildAt(i)).isChecked()) {
                        checked.add(i);
            }
        }
        return checked;
    }

    public ArrayList<String> getChackedTexts() {
        int children = this.textViewsListView.getChildCount();
        ArrayList<String> checked = new ArrayList<String>();
        for (int i = 0; i < children; i++) {
            if (this.textViewsListView.getChildAt(i) instanceof CheckBox &&
                    ((CheckBox)this.textViewsListView.getChildAt(i)).isChecked()) {
                checked.add(((CheckBox)this.textViewsListView.getChildAt(i)).getText().toString());
            }
        }
        return checked;
    }

    public CheckBox getViewInIndex(int index) {
        return (CheckBox) (this.textViewsListView.getChildAt(index));
    }

    public void checkIndex(int index) {
        if (this.textViewsListView.getChildAt(index) instanceof CheckBox) {
            ((CheckBox) this.textViewsListView.getChildAt(index)).setChecked(true);
        }
    }

}
