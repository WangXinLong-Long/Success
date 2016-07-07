package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.OrderDetails;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.OrderDetailsListViewAdapter;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.model.OrderDeatilsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class OrderDetailsHaveBeenPaid extends Activity implements View.OnClickListener {
    CustomListView order_details_listView;
    private OrderDetailsListViewAdapter adapter;
    private List<OrderDeatilsModel> list;
    private OrderDeatilsModel orderDeatilsModel;
    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details_have_been_paid_activity);
        order_details_listView = ((CustomListView) findViewById(R.id.order_details_listView));
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
        iv_child_title_bar_back.setOnClickListener(this);
        tv_child_title_bar_title.setText("订单详情");
        initdata();
        order_details_listView.setAdapter(adapter);
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

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_child_title_bar_back:
                finish();
                break;
            default:
                break;
        }
    }
}
