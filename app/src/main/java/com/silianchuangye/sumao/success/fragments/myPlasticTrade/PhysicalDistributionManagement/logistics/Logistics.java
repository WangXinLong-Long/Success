package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.ViewLogisticsDemands.ViewLogisticsDemandMVP.view.ViewLogisticsDemand;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsMVP.view.CreateLogistics;

public class Logistics extends AppCompatActivity {

    private RelativeLayout create_logistics;
    private RelativeLayout view_logistics_demand;
    private TextView tv_child_title_bar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics);

//        标题
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("物流需求");
//        创建物流需求
        create_logistics = ((RelativeLayout) findViewById(R.id.create_logistics));
//        查询物流需求
        view_logistics_demand = ((RelativeLayout) findViewById(R.id.view_logistics_demand));
        create_logistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Logistics.this, CreateLogistics.class);
                startActivity(intent);
            }
        });
        view_logistics_demand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Logistics.this, ViewLogisticsDemand.class);
                startActivity(intent);
            }
        });


    }
}
