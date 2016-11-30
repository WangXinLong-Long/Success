package com.silianchuangye.sumao.success.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class CalendarFuture {

    private static String date = "";
    private static int yearP;
    private static int monthP;
    private static int dayP;

    public static String showDate(final Context context) {
        final Calendar calend1 = Calendar.getInstance();

        calend1.setTimeInMillis(System.currentTimeMillis());
        yearP = calend1.get(Calendar.YEAR);
        monthP = calend1.get(Calendar.MONTH) + 1;
        dayP = calend1.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog3 = new DatePickerDialog(
                context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view,
                                          int year, int monthOfYear,
                                          int dayOfMonth) {

                        if (year >= yearP && monthOfYear >= monthP && dayOfMonth >= dayP)
                            date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + "";
                        else {
                            Toast.makeText(context, "只能获取今天及以后日期", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, yearP, monthP, dayP);
        /*
        * if (calend1>System.currentTimeMillis())
                    If Calendar1.SelectedDate > Now Then
                    Calendar1.SelectedDates.Clear()*/

        dialog3.show();
        return date;
    }

}
