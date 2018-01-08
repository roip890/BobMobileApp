package com.bob.bobmobileapp.tools.UI.views.textviews;

import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by User on 22/11/2017.
 */

public class MyTimeTextView extends MyTextView {

    protected int hours, minutes, seconds;
    protected SimpleDateFormat dateFormat;
    protected Calendar calendar;
    protected TimePickerDialog timeDialog;
    protected Boolean fullHours;

    public MyTimeTextView(Context context) {
        this(context, null);
    }

    public MyTimeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTimeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
        this.fullHours = true;
        this.dateFormat = new SimpleDateFormat("hh:mm", Locale.getDefault());
        this.calendar = Calendar.getInstance();
        this.setHint("Select Time:");
        this.timeDialog = new TimePickerDialog(this.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {
                hours = hourOfDay;
                minutes = minuteOfDay;
                calendar.set(Calendar.HOUR, hours);
                calendar.set(Calendar.MINUTE, minutes);
                textView.setText(dateFormat.format(calendar.getTime()));
                updateText();
                timeDialog.dismiss();
            }
        }, this.hours, this.minutes, this.fullHours);
        this.textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                timeDialog.updateTime(hours, minutes);
                timeDialog.show();
            }
        });
    }

    public void setDateFormat(String format) {
        this.dateFormat.applyPattern(format);
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    private void updateText() {
        calendar.set(Calendar.HOUR, hours);
        calendar.set(Calendar.MINUTE, minutes);
        textView.setText(dateFormat.format(calendar.getTime()));
    }

}
