package com.bob.bobmobileapp.menu.viewholders.formitem.text;

import android.content.Context;
import android.view.View;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;
import com.bob.bobmobileapp.tools.UI.views.textviews.dialogviews.MyTimeTextView;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by user on 01/09/2017.
 */

public class TimeViewHolder extends TextViewViewHolder {

    protected int hours, minutes;
    protected MyTimeTextView timeTextView;

    public TimeViewHolder(Context context, View view) {
        super(context, view, null);
    }

    @Override
    protected void initView(View view) {
        this.setTextView((MyTextView) view.findViewById(R.id.my_time_text_view));
    }

    @Override
    public void setTextView(MyTextView textView) {
        this.textView = textView;
        this.timeTextView = (MyTimeTextView) textView;
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.timeTextView.setHours(0);
        this.timeTextView.setMinutes(0);
        this.textView.setTitleText("Please enter your text");
    }

    protected void updateProperties(HashMap<String, String> properties) {
        super.updateProperties(properties);
        String curProperty;
        if ((curProperty = properties.get("time")) != null) {
            String[] timeParts = curProperty.split(Pattern.quote(":"));
            if (timeParts.length == 2) {
                hours = Integer.parseInt(timeParts[0]);
                minutes = Integer.parseInt(timeParts[1]);
                timeTextView.setHours(hours);
                timeTextView.setWidth(minutes);
            }
        }

    }

}
