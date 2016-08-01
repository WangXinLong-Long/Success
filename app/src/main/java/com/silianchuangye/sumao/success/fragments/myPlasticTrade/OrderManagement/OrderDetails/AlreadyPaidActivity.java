package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.OrderDetails;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.SpotOrderAdapter;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.model.SpotOrderModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class AlreadyPaidActivity extends Activity implements View.OnClickListener{
    private TextView tv_child_title_bar_title;
    private ListView spot_order_listView;
    //    SpotOrde模型
    private SpotOrderModel model, dada;
    //      创建SpotOrderModel对象集合
    List<SpotOrderModel> list;
    //    适配器
    SpotOrderAdapter adapter;
    private RelativeLayout order_amount;
    private ImageView iv_child_title_bar_back;
    private Button Copy;
    private TextView the_order_number_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_has_been_paid);
        Copy= (Button) findViewById(R.id.bt_copy);
        the_order_number_number= (TextView) findViewById(R.id.the_order_number_number);
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("已支付订单");
        spot_order_listView = ((CustomListView) findViewById(R.id.spot_order_listView));
        order_amount = ((RelativeLayout) findViewById(R.id.order_amount));
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
        iv_child_title_bar_back.setOnClickListener(this);
        order_amount.setOnClickListener(this);
        /**
         * 复制按钮
         */
        Copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager copy = (ClipboardManager) AlreadyPaidActivity.this
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(the_order_number_number.getText().toString());

            }
        });

        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            model = new SpotOrderModel();
            model.setUnivalent(i * 100);
            model.setNumber(i + 1);
            model.setEnterprise("中石油");
            model.setTotalMoney((i*100)*(i+1));
            model.setWarehouse("讯帮"+i+"库");
            model.setCompany(i+"联创业集团");
            model.setProductModel("中国石油"+i+"型产品");
            list.add(model);
        }
        adapter = new SpotOrderAdapter(this,list);
        spot_order_listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.order_amount:
                Intent intent = new Intent();
                intent.setClass(this,OrderDetailsHaveBeenPaid.class);
                startActivity(intent);
                break;
            case R.id.iv_child_title_bar_back:
                finish();
                break;
            default:
                break;
        }
    }
}
