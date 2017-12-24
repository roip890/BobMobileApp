package com.bob.bobmobileapp.tools.UI.views.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.bob.bobmobileapp.tools.UI.views.recyclerview.MyBaseTextGroup;

/**
 * Created by User on 24/12/2017.
 */

public class MyTextGroup extends MyBaseTextGroup {


    public MyTextGroup(Context context) {
        this(context, null);
    }

    public MyTextGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected TextView createViewType() {
        return new TextView(this.getContext());
    }


}
