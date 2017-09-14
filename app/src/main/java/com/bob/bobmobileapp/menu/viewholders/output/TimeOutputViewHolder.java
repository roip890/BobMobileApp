package com.bob.bobmobileapp.menu.viewholders.output;

import android.content.Context;
import android.view.View;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by user on 01/09/2017.
 */

public class TimeOutputViewHolder extends TextOutputViewHolder {

    protected int hours, minutes;


    public TimeOutputViewHolder(Context context, View view) {
        super(context, view, null);
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.hours = 0;
        this.minutes = 0;
        this.hint = "Select Time:";
    }

    @Override
    protected void getTextLabel(HashMap<String, String> properties) {
        String curProperty;
        if ((curProperty = properties.get("text_lable")) != null) {
            String[] timeParts = curProperty.split(Pattern.quote(":"));
            if (timeParts.length == 2) {
                hours = Integer.parseInt(timeParts[0]);
                minutes = Integer.parseInt(timeParts[1]);
            }
            this.textLable = curProperty;
        }
    }

    @Override
    protected void setText() {
        NumberFormat formatter = new DecimalFormat("00");
        this.textField.setText(formatter.format(hours) + ":" + formatter.format(minutes));
    }

}
