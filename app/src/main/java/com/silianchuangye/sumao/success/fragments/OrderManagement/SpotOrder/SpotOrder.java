package com.silianchuangye.sumao.success.fragments.OrderManagement.SpotOrder;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.SpotOrderAdapter;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.model.SpotOrderModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Administrator on 2016/5/3 0003.
 */
public class SpotOrder extends Activity implements View.OnClickListener{
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv;
    RelativeLayout spot_order_title;
    CustomListView spot_order_listView;
    /*定义一个倒计时的内部类*/
    private MyCount mc;
//    SpotOrde模型
    private SpotOrderModel model,dada;
//      创建SpotOrderModel对象集合
    List<SpotOrderModel> list;
//    适配器
    SpotOrderAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_order);

        spot_order_title = ((RelativeLayout) findViewById(R.id.spot_order_title));
        spot_order_title.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title  = ((TextView) findViewById(R.id.tv_title_bar_title));

        iv_title_bar_logo.setVisibility(View.INVISIBLE);
        iv_title_bar_service.setVisibility(View.INVISIBLE);
        sv_title_bar_serachView.setVisibility(View.INVISIBLE);

        iv_title_bar_back.setOnClickListener(this);
        iv_title_bar_search.setOnClickListener(this);
        tv_title_bar_title.setText("现货订单");
        tv_title_bar_title.setTextColor(Color.WHITE);

        tv = ((TextView) findViewById(R.id.tv));
        /**
         *
         * 根据系统下单时间，获取剩余结束时间
         */
        mc = new MyCount(50*1000, 1000);
        mc.start();

        list = new ArrayList<>();
        for (int i = 0; i <10; i++) {
            model = new SpotOrderModel();
            model.setUnivalent(i*100);
            model.setNumber(i+1);
            model.setEnterprise("中石油");
            model.setTotalMoney((i*100)*(i+1));
            model.setWarehouse("讯帮"+i+"库");
            model.setCompany(i+"联创业集团");
            model.setProductModel("中国石油"+i+"型产品");
            list.add(model);
        }

        spot_order_listView = ((CustomListView) findViewById(R.id.spot_order_listView));
        adapter = new SpotOrderAdapter(this,list);
        spot_order_listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_title_bar_back:
                finish();
                break;
            case R.id.iv_title_bar_search:
                Toast.makeText(this,"搜索",Toast.LENGTH_SHORT).show();

                break;
        }
    }

    /*定义一个倒计时的内部类*/
    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish() {
            tv.setText("finish");
        }
        @Override
        public void onTick(long millisUntilFinished) {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            String time = format.format(millisUntilFinished- TimeZone.getDefault().getRawOffset());
            tv.setText("剩余支付时间：" +time);
        }
    }
}
