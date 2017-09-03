package com.bob.bobmobileapp.menu.viewholders;

import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.realm.objects.FormItem;
import com.julianraj.validatedtextinputlayout.ValidatedTextInputLayout;

import java.util.Calendar;

/**
 * Created by user on 01/09/2017.
 */

public class TimeViewHolder extends LabelViewHolder {


    public TimeViewHolder(Context context, View view) {
        super(context, view);
    }

    @Override
    public void configureFormItem(FormItem formItem) {
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(currentHour);
        stringBuilder.append(":");
        stringBuilder.append(currentMinute);
        textView.setText(stringBuilder.toString());

        this.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timeText = textView.getText().toString();
                String[] timeParts = timeText.split(":");
                int hours = Integer.parseInt(timeParts[0]);
                int minutes = Integer.parseInt(timeParts[1]);
                TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(selectedHour);
                        stringBuilder.append(":");
                        stringBuilder.append(selectedMinute);
                        textView.setText(stringBuilder.toString());
                    }
                }, hours, minutes, true);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });
    }


}
