package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.HavaPaidSellerOrderDetailsAdapter;
import com.silianchuangye.sumao.success.adapter.HavaPaidSellerOrderDetailsModel;
import com.silianchuangye.sumao.success.custom.CustomListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/15 0015.
 * 已支付界面
 */
public class SellerAlreadyPaidOrderDetails extends Activity{

    CustomListView listView;
    HavaPaidSellerOrderDetailsAdapter adapter ;
    List<HavaPaidSellerOrderDetailsModel> lists;
    HavaPaidSellerOrderDetailsModel havePaidSellerOrderDetailsModel;
    private TextView tv_child_title_bar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_already_paid_order_details);
        listView = ((CustomListView) findViewById(R.id.seller_already_paid_order_details_list_view));
        intitData();
        adapter = new HavaPaidSellerOrderDetailsAdapter(this,lists);
        listView.setAdapter(adapter);
//        设置标题
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("这里输入订单编号");

    }

    private void intitData() {
        lists = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            havePaidSellerOrderDetailsModel = new HavaPaidSellerOrderDetailsModel();
            havePaidSellerOrderDetailsModel.setTitle("兰州石化7042");
            havePaidSellerOrderDetailsModel.setClassification("默认");
            havePaidSellerOrderDetailsModel.setProductName("兰州石化7042");
            havePaidSellerOrderDetailsModel.setOriginalUnitPrice("6100");
            havePaidSellerOrderDetailsModel.setNumber("10");
            havePaidSellerOrderDetailsModel.setNewUnitPrice("6100");
            havePaidSellerOrderDetailsModel.setTotalProductPrice("61000");
            havePaidSellerOrderDetailsModel.setWarehouse("讯帮物流1号库");
            lists.add(havePaidSellerOrderDetailsModel);
        }
    }
}
