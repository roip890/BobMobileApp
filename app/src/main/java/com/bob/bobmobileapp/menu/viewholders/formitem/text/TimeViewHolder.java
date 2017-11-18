package com.bob.bobmobileapp.menu.viewholders.formitem.text;

import android.content.Context;
import android.view.View;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by user on 01/09/2017.
 */

public class TimeViewHolder extends TextViewViewHolder {

    protected int hours, minutes;

    public TimeViewHolder(Context context, View view) {
        super(context, view, null);
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.hours = 0;
        this.minutes = 0;
        this.hintText = "Select Time:";
    }

    protected void updateProperties(HashMap<String, String> properties) {
        super.updateProperties(properties);
        String curProperty;
        if ((curProperty = properties.get("time")) != null) {
            String[] timeParts = curProperty.split(Pattern.quote(":"));
            if (timeParts.length == 2) {
                hours = Integer.parseInt(timeParts[0]);
                minutes = Integer.parseInt(timeParts[1]);
            }
            this.text = curProperty;
        }
    }

    @Override
    protected void setValue() {
        NumberFormat formatter = new DecimalFormat("00");
        this.textView.setText(formatter.format(hours) + ":" + formatter.format(minutes));
    }

}
