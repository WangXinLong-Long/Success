package com.silianchuangye.sumao.success.fragments.homepage.preSale;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.customCalendar.CalendarView;
import com.silianchuangye.sumao.success.custom.customCalendar.DayAndPrice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
public class PreSaleDetailActivity  extends Activity{
    CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_sale_detail);
        List<DayAndPrice> list = new ArrayList<DayAndPrice>();
        DayAndPrice dAndPrice = new DayAndPrice("¥3900起", 2016,2,20);
        DayAndPrice dAndPrice1 = new DayAndPrice("¥3900起", 2016,7,10);
        DayAndPrice dAndPrice2 = new DayAndPrice("¥3900起", 2016,10,1);
        list.add(dAndPrice);list.add(dAndPrice1);list.add(dAndPrice2);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setListDayAndPrice(list);
        calendarView.setDateViewClick(new CalendarView.DateViewClick() {

            @Override
            public void dateClick() {
                Toast.makeText(getApplication(), "点击了：" + calendarView.getSelectMonth(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
