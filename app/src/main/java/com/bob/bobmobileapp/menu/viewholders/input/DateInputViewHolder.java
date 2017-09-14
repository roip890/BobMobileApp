package com.bob.bobmobileapp.menu.viewholders.input;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.bob.bobmobileapp.menu.viewholders.output.DateOutputViewHolder;
import com.bob.bobmobileapp.menu.viewholders.output.TextOutputViewHolder;

import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * Created by user on 01/09/2017.
 */

public class DateInputViewHolder extends DateOutputViewHolder {



    public DateInputViewHolder(Context context, View view) {
        super(context, view);
    }

    @Override
    protected void configure() {
        super.configure();


        this.textField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDays) {
                        year = selectedYear;
                        month = selectedMonth;
                        day = selectedDays;
                        setText();
                    }
                }, year, month, day);
                datePickerDialog.setTitle(hint);
                datePickerDialog.show();
            }
        });
    }

}
