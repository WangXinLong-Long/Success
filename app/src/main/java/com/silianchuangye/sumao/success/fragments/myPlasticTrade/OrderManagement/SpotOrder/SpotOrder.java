package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder;

import android.app.Activity;
import android.content.Intent;
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
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.OrderDetails.OrderDetailsActivity;
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
    TextView cancellation_of_order;
    RelativeLayout spot_order_title;
//    订单编号和金额 所在的条目可以点击
    RelativeLayout order_amount;
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
//        金额所在的条目的点击事件，可以查看详情
        order_amount = ((RelativeLayout) findViewById(R.id.order_amount));
        order_amount.setOnClickListener(this);

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
//        取消订单的文字以及点击事件
        cancellation_of_order = ((TextView) findViewById(R.id.cancellation_of_order));
        cancellation_of_order.setVisibility(View.VISIBLE);
        cancellation_of_order.setOnClickListener(this);
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
//        ScrollView 点击进去不能到达最顶部，只有设置了这个属性，可以让ScrollView到达最顶部
        spot_order_listView.setFocusable(false);
       /* spot_order_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SpotOrder.this,"dianji"+position+"条",Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_title_bar_back:
                finish();
                break;
            case R.id.cancellation_of_order:
                Toast.makeText(this,"取消订单",Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.order_amount:
                Intent intent = new Intent();
                intent.setClass(this,OrderDetailsActivity.class);
                startActivity(intent);
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
