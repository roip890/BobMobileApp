package com.bob.bobmobileapp.tools.UI;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by User on 22/11/2017.
 */

public class MyDateTextView extends MyTextView {

    private int years;
    private int months;
    private int days;
    protected SimpleDateFormat dateFormat;
    protected Calendar calendar;
    protected DatePickerDialog dateDialog;

    public MyDateTextView(Context context) {
        this(context, null);
    }

    public MyDateTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyDateTextView(Context context, AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setYears(0);
        this.setMonths(0);
        this.setDays(0);
        this.dateFormat = new SimpleDateFormat("hh:mm", Locale.getDefault());
        this.calendar = Calendar.getInstance();
        this.setHint("Select Time:");
        this.dateDialog = new DatePickerDialog(this.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                setYears(year);
                setMonths(month);
                setDays(day);
                calendar.set(Calendar.YEAR, years);
                calendar.set(Calendar.MONTH, months);
                calendar.set(Calendar.DAY_OF_MONTH, days);
                textView.setText(dateFormat.format(calendar.getTime()));
                dateDialog.dismiss();
            }
        }, years, months, days);
        this.textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog.updateDate(years, months, days);
                dateDialog.show();
            }
        });
    }

    public void setDateFormat(String format) {
        this.dateFormat.applyPattern(format);
    }

    public void setYears(int years) {
        this.years = years;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
