package com.bob.bobmobileapp.menu.viewholders.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bob.bobmobileapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by user on 17/09/2017.
 */

public class DateDialogViewHolder extends DialogViewHolder {

    protected int year, month, day;
    protected SimpleDateFormat dateFormat;
    protected DatePicker datePicker;

    public DateDialogViewHolder(Context context, View view) {
        super(context, view);
    }

    @Override
    protected void initialize() {
        super.initialize();
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.hint = "Select Date:";
        this.dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        this.dialogTitleText = this.hint;
    }

    protected void updateProperties(HashMap<String, String> properties) {
        super.updateProperties(properties);
        String curProperty;
        if ((curProperty = properties.get("date_format")) != null) {
            this.dateFormat = new SimpleDateFormat(curProperty);
        }
        if ((curProperty = properties.get("date")) != null) {
            String[] timeParts = curProperty.split(Pattern.quote("/"));
            if (timeParts.length == 3) {
                year = Integer.parseInt(timeParts[0]);
                month = Integer.parseInt(timeParts[1]);
                day = Integer.parseInt(timeParts[2]);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            this.textLable = dateFormat.format(calendar.getTime());
        }
    }

    @Override
    protected void setValue() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        this.textView.setText(dateFormat.format(calendar.getTime()));
    }

    @Override
    protected void onPositiveClicked(MaterialDialog dialog, DialogAction which) {
        this.year = this.datePicker.getYear();
        this.month = this.datePicker.getMonth();
        this.day = this.datePicker.getDayOfMonth();
        this.setValue();
    }

    @Override
    protected View getCustomView() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_date_picker, null);
        datePicker = (DatePicker) view.findViewById(R.id.date_picker);
        this.datePicker.init(this.year, this.month, this.day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDays) {
                year = selectedYear;
                month = selectedMonth;
                day = selectedDays;
                setValue();
            }
        });
        return view;
    }

}
