package com.silianchuangye.sumao.success.fragments.homepage.preSale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.MainActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.customCalendar.CalendarView;
import com.silianchuangye.sumao.success.custom.customCalendar.DayAndPrice;
import com.silianchuangye.sumao.success.fragments.auction.VesselThreeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
public class PreSaleDetailActivity  extends Activity implements View.OnClickListener{
    CalendarView calendarView;
    ImageView title_bar_white_back;
    TextView title_bar_white_title;
    ImageView title_bar_white_shopping_cart;
    RelativeLayout pre_sale_sale_detail_detail;
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
        title_bar_white_back = ((ImageView) findViewById(R.id.title_bar_white_back));
        title_bar_white_title = ((TextView) findViewById(R.id.title_bar_white_title));
        title_bar_white_shopping_cart = ((ImageView) findViewById(R.id.title_bar_white_shopping_cart));
        pre_sale_sale_detail_detail = ((RelativeLayout) findViewById(R.id.pre_sale_sale_detail_detail));
        title_bar_white_back.setOnClickListener(this);
        title_bar_white_shopping_cart.setOnClickListener(this);
        pre_sale_sale_detail_detail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId())
        {
            case R.id.title_bar_white_back:
                finish();
                break;
            case R.id.title_bar_white_shopping_cart:

                intent.setClass(PreSaleDetailActivity.this, MainActivity.class);
                intent.putExtra("cart",1);
                startActivity(intent);
                break;
            case R.id.pre_sale_sale_detail_detail:
                intent.setClass(PreSaleDetailActivity.this, VesselThreeActivity.class);
                startActivity(intent);
                break;

        }
    }
}
