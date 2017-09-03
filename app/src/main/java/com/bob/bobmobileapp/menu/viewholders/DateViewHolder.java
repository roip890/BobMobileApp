package com.bob.bobmobileapp.menu.viewholders;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.bob.bobmobileapp.realm.objects.FormItem;

import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * Created by user on 01/09/2017.
 */

public class DateViewHolder extends LabelViewHolder {


    public DateViewHolder(Context context, View view) {
        super(context, view);
    }

    @Override
    public void configureFormItem(FormItem formItem) {
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(currentDay);
        stringBuilder.append("\\");
        stringBuilder.append(currentMonth);
        stringBuilder.append("\\");
        stringBuilder.append(currentYear);
        textView.setText(stringBuilder.toString());

        this.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timeText = textView.getText().toString();
                String[] timeParts = timeText.split(Pattern.quote("\\"));
                int days = Integer.parseInt(timeParts[0]);
                int month = Integer.parseInt(timeParts[1]);
                int year = Integer.parseInt(timeParts[2]);
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDays) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(selectedDays);
                        stringBuilder.append("\\");
                        stringBuilder.append(selectedMonth);
                        stringBuilder.append("\\");
                        stringBuilder.append(selectedYear);
                        textView.setText(stringBuilder.toString());
                    }
                }, year, month, days);
                datePickerDialog.setTitle("Select Date");
                datePickerDialog.show();
            }
        });
    }


}
