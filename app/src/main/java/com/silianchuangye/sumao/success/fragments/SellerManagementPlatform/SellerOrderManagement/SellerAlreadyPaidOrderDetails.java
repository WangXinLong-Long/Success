package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.HavaPaidSellerOrderDetailsAdapter;
import com.silianchuangye.sumao.success.adapter.HavaPaidSellerOrderDetailsModel;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement.Fragments.FaPiaoMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/15 0015.
 * 已支付界面
 */
public class SellerAlreadyPaidOrderDetails extends Activity implements View.OnClickListener{

    CustomListView listView;
    HavaPaidSellerOrderDetailsAdapter adapter ;
    List<HavaPaidSellerOrderDetailsModel> lists;
    HavaPaidSellerOrderDetailsModel havePaidSellerOrderDetailsModel;
    private TextView tv_child_title_bar_title;
    private RelativeLayout relative_hetong_message,relative_pay_message,
            relative_fapiao_message,relative_logistics_message;
    private ImageView iv_child_title_bar_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_already_paid_order_details);
        listView = ((CustomListView) findViewById(R.id.seller_already_paid_order_details_list_view));
        relative_hetong_message= (RelativeLayout) findViewById(R.id.relative_hetong_message);
        relative_pay_message= (RelativeLayout) findViewById(R.id.relative_pay_message);
        relative_fapiao_message= (RelativeLayout) findViewById(R.id.relative_fapiao_message);
        relative_logistics_message= (RelativeLayout) findViewById(R.id.relative_logistics_message);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        intitData();
        adapter = new HavaPaidSellerOrderDetailsAdapter(this,lists);
        listView.setAdapter(adapter);
//        设置标题
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("这里输入订单编号");
        relative_hetong_message.setOnClickListener(this);
        relative_pay_message.setOnClickListener(this);
        relative_fapiao_message.setOnClickListener(this);
        relative_logistics_message.setOnClickListener(this);
        iv_child_title_bar_back.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_child_title_bar_back:
                finish();
                break;
            case R.id.relative_hetong_message:
                Toast.makeText(this,"合同信息",Toast.LENGTH_SHORT).show();
                break;
            case R.id.relative_pay_message:
                Toast.makeText(this,"支付信息",Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(this,PayMessage.class);
                startActivity(intent1);
                break;
            case R.id.relative_fapiao_message:
                Toast.makeText(this,"发票信息",Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent(this, FaPiaoMessage.class);
                startActivity(intent2);
                break;
            case R.id.relative_logistics_message:
                Toast.makeText(this,"物流信息",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_ok:
                Toast.makeText(this,"同意",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_no:
                Toast.makeText(this,"不同意",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
