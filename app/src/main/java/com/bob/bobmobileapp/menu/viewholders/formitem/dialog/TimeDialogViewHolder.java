package com.bob.bobmobileapp.menu.viewholders.formitem.dialog;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bob.bobmobileapp.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by user on 17/09/2017.
 */

public class TimeDialogViewHolder extends DialogViewHolder {

    protected int hours, minutes;
    protected TimePicker timePicker;

    public TimeDialogViewHolder(Context context, View view) {
        super(context, view);
    }

    @Override
    protected void initialize() {
        super.initialize();
        Calendar calendar = Calendar.getInstance();
        this.hours = calendar.get(Calendar.HOUR_OF_DAY);
        this.minutes = calendar.get(Calendar.MINUTE);
        this.hint = "Select Time:";
        this.dialogTitleText = this.hint;
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
            this.textLable = curProperty;
        }
    }

    @Override
    protected void setValue() {
        NumberFormat formatter = new DecimalFormat("00");
        this.textView.setText(formatter.format(hours) + ":" + formatter.format(minutes));
    }

    @Override
    protected void onPositiveClicked(MaterialDialog dialog, DialogAction which) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.hours = timePicker.getHour();
            this.minutes = timePicker.getMinute();
        } else {
            this.hours = timePicker.getCurrentHour();
            this.minutes = timePicker.getCurrentMinute();
        }
        this.setValue();
    }

    @Override
    protected View getCustomView() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_time_picker, null);
        timePicker = (TimePicker) view.findViewById(R.id.time_picker);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(hours);
            timePicker.setMinute(minutes);
        } else {
            timePicker.setCurrentHour(hours);
            timePicker.setCurrentMinute(minutes);
        }
        timePicker.setIs24HourView(true);
        this.timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hours = selectedHour;
                minutes = selectedMinute;
                setValue();
            }
        });
        return view;
    }



}
