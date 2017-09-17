package com.bob.bobmobileapp.menu.viewholders;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.TimePicker;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by user on 01/09/2017.
 */

public class TimeViewHolder extends TextViewViewHolder {

    protected int hours, minutes;
    protected boolean isInput;


    public TimeViewHolder(Context context, View view) {
        super(context, view, null);
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.hours = 0;
        this.minutes = 0;
        this.hint = "Select Time:";
        this.isInput = false;
    }

    protected void updateProperties(HashMap<String, String> properties) {
        super.updateProperties(properties);
        String curProperty;
        if ((curProperty = properties.get("is_input")) != null) {
            if (curProperty.equals("true")) {
                this.isInput = true;
            } else if (curProperty.equals("false")) {
                this.isInput = false;
            }
        }
    }

    @Override
    protected void configure() {
        if (this.isInput) {
            Calendar calendar = Calendar.getInstance();
            this.hours = calendar.get(Calendar.HOUR_OF_DAY);
            this.minutes = calendar.get(Calendar.MINUTE);
            this.textField.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            hours = selectedHour;
                            minutes = selectedMinute;
                            setValue();
                        }
                    }, hours, minutes, true);
                    timePickerDialog.setTitle(hint);
                    timePickerDialog.show();
                }
            });
        } else {
            this.textField.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
        if ((this.textLable == null)) {
            if (this.isInput) {
                Calendar calendar = Calendar.getInstance();
                this.hours = calendar.get(Calendar.HOUR_OF_DAY);
                this.minutes = calendar.get(Calendar.MINUTE);
            } else {
                this.hours = 0;
                this.minutes = 0;
            }
        }
        super.configure();
    }


    @Override
    protected void getValue(HashMap<String, String> properties) {
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
    protected void setValue() {
        NumberFormat formatter = new DecimalFormat("00");
        this.textField.setText(formatter.format(hours) + ":" + formatter.format(minutes));
    }

}
