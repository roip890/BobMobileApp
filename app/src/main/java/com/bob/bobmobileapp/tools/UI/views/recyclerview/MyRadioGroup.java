package com.bob.bobmobileapp.tools.UI.views.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;

import com.bob.bobmobileapp.tools.UI.views.recyclerview.adapters.MyRecyclerViewSingleChoiceAdapter;

/**
 * Created by User on 19/12/2017.
 */

public class MyRadioGroup extends MyRecyclerView<RadioButton> {

    public MyRadioGroup(Context context) {
        this(context, null);
    }

    public MyRadioGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRadioGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.adapter = new MyRecyclerViewSingleChoiceAdapter();
        this.recyclerView.setAdapter(this.adapter);

    }

    public RadioButton getCheckedView() {
        return ((MyRecyclerViewSingleChoiceAdapter)this.adapter).getCheckedView();
    }

    public int getCheckedViewIndex() {
        return ((MyRecyclerViewSingleChoiceAdapter)this.adapter).getCheckedViewIndex();
    }


}
