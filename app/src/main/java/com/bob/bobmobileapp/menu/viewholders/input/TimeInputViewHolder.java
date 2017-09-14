package com.bob.bobmobileapp.menu.viewholders.input;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.TimePicker;
import com.bob.bobmobileapp.menu.viewholders.output.TimeOutputViewHolder;

import java.util.Calendar;

/**
 * Created by user on 01/09/2017.
 */

public class TimeInputViewHolder extends TimeOutputViewHolder {

    public TimeInputViewHolder(Context context, View view) {
        super(context, view);
    }

    @Override
    protected void initialize() {
        super.initialize();
        Calendar calendar = Calendar.getInstance();
        this.hours = calendar.get(Calendar.HOUR_OF_DAY);
        this.minutes = calendar.get(Calendar.MINUTE);
    }

    @Override
    protected void configure() {
        super.configure();
        this.textField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        hours = selectedHour;
                        minutes = selectedMinute;
                        setText();
                    }
                }, hours, minutes, true);
                timePickerDialog.setTitle(hint);
                timePickerDialog.show();
            }
        });
    }

}
