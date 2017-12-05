package com.bob.bobmobileapp.tools.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by User on 03/12/2017.
 */

public class MyRadioGroup extends MyTextViewList {

    //constructors
    public MyRadioGroup(Context context) {
        this(context, null);
    }

    public MyRadioGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRadioGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.setOrientation(VERTICAL);
        this.addViews();
    }

    @Override
    protected TextView createTextView() {
        return new RadioButton(this.getContext());
    }

    @Override
    protected ViewGroup createTextViewsListView() {
        return new RadioGroup(this.getContext());
    }

    public RadioButton getChackedView() {
        RadioGroup radioGroup = ((RadioGroup)this.textViewsListView);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        return radioGroup.findViewById(radioButtonID);
    }

    public int getChackedIndex() {
        return ((RadioGroup)(this.textViewsListView)).indexOfChild(this.getChackedView());
    }

    public String getChackedText() {
        return this.getChackedView().getText().toString();
    }


    public RadioButton getViewInIndex(int index) {
        RadioGroup radioGroup = (RadioGroup)(this.textViewsListView);
        return (RadioButton) (radioGroup.getChildAt(index));
    }


    public void checkIndex(int index) {
        RadioGroup radioGroup = (RadioGroup)(this.textViewsListView);
        radioGroup.check(radioGroup.getChildAt(index).getId());
    }

}
