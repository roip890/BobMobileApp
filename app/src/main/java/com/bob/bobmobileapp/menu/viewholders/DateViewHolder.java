package com.bob.bobmobileapp.menu.viewholders;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by user on 01/09/2017.
 */

public class DateViewHolder extends TextViewViewHolder {

    protected int year, month, day;
    protected SimpleDateFormat dateFormat;
    protected boolean isInput;

    public DateViewHolder(Context context, View view) {
        super(context, view, null);
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
    }

    @Override
    protected void updateProperties(HashMap<String, String> properties) {
        String curProperty;
        if ((curProperty = properties.get("date_format")) != null) {
            this.dateFormat = new SimpleDateFormat(curProperty);
        }
        if ((curProperty = properties.get("is_input")) != null) {
            if (curProperty.equals("true")) {
                this.isInput = true;
            } else if (curProperty.equals("false")) {
                this.isInput = false;
            }
        }
        super.updateProperties(properties);
    }

    @Override
    protected void configure() {
        super.configure();
        if (this.isInput) {
            this.textField.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDays) {
                            year = selectedYear;
                            month = selectedMonth;
                            day = selectedDays;
                            setValue();
                        }
                    }, year, month, day);
                    datePickerDialog.setTitle(hint);
                    datePickerDialog.show();
                }
            });
        } else {
            this.textField.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
    }


    @Override
    protected void getValue(HashMap<String, String> properties) {
        String curProperty;
        if ((curProperty = properties.get("text_lable")) != null) {
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
        this.textField.setText(dateFormat.format(calendar.getTime()));
    }

}
