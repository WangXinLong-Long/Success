package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.OrderDetails;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.OrderDetailsListViewAdapter;
import com.silianchuangye.sumao.success.adapter.PreSaleAdapter;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.model.OrderDeatilsModel;
import com.silianchuangye.sumao.success.model.PreSaleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class OrderDetailsActivity  extends Activity{

    private CustomListView order_details_listView;
    private OrderDetailsListViewAdapter adapter;
    private List<OrderDeatilsModel> list;
    private OrderDeatilsModel orderDeatilsModel;
    private TextView tv_child_title_bar_title;
    private Button bt_copy;
    private TextView tv_order_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details_activity);
        bt_copy= (Button) findViewById(R.id.bt_copy);
        tv_order_number= (TextView) findViewById(R.id.tv_order_number);
        order_details_listView = (CustomListView)findViewById(R.id.order_details_listView);
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("订单详情");
        initdata();
        order_details_listView.setAdapter(adapter);
        enevt();
    }
    private void initdata() {

        list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            orderDeatilsModel = new OrderDeatilsModel();
            orderDeatilsModel.setWarehouse("公司"+i);
            orderDeatilsModel.setClassification("分类"+"LLDPE"+i);
            orderDeatilsModel.setDeliveryTime("交货时间"+i);
            orderDeatilsModel.setDistributionMode("配送方式："+i);
            orderDeatilsModel.setNumber(i);
            orderDeatilsModel.setProductUnitPrice("产品单价"+i);
            orderDeatilsModel.setTitle("标题"+i);
            orderDeatilsModel.setTotalProductPrice("总价"+i);
            list.add(orderDeatilsModel);
        }
        adapter = new OrderDetailsListViewAdapter(this, list);

    }
    public void enevt(){
        bt_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager copy = (ClipboardManager) OrderDetailsActivity.this
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(tv_order_number.getText().toString());

            }
        });
    }

}
