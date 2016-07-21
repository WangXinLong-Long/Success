package com.silianchuangye.sumao.success.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/7/12 0012.
 */
public class ShowCalendar {
    public static void showDate(final TextView Tv, final Context context, final Boolean flag){
        Calendar calend1 = Calendar.getInstance();
        calend1.setTimeInMillis(System.currentTimeMillis());
        int year = calend1.get(Calendar.YEAR);
        int month = calend1.get(Calendar.MONTH) + 1;
        int day = calend1.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog3 = new DatePickerDialog(
                context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view,
                                          int year, int monthOfYear,
                                          int dayOfMonth) {
                        Toast.makeText(context,
                                "" + year + "年" + (monthOfYear + 1)
                                        + "月" + dayOfMonth + "日", Toast.LENGTH_LONG).show();
                        if (flag)
                        {
                            Tv.setText(year + "年" + (monthOfYear + 1)
                                    + "月\n" + dayOfMonth + "日");
                        }else {
                            Tv.setText(year + "年" + (monthOfYear + 1)
                                    + "月" + dayOfMonth + "日");
                        }
                    }
                }, year, month, day);
        dialog3.show();
    }

}
