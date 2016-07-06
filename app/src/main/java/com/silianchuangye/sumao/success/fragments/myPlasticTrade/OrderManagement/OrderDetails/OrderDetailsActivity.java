package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.OrderDetails;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.OrderDetailsListViewAdapter;
import com.silianchuangye.sumao.success.custom.CustomListView;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class OrderDetailsActivity  extends Activity{

    private CustomListView order_details_listView;
    private OrderDetailsListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details_activity);
        order_details_listView = (CustomListView)findViewById(R.id.order_details_listView);
    }
}
