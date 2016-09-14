package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.ViewLogisticsDemands;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.TrackingDistributionDetailsAdapter;
import com.silianchuangye.sumao.success.fragments.bean.logisticsMessageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/12 0012.
 */
public class TrackingDistributionDetails extends Activity implements View.OnClickListener{
    private ImageView img_create_logistics_back;
    private ListView lv_detatils;
    TrackingDistributionDetailsAdapter adapter;
    private List<logisticsMessageInfo> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_distribution_details);
        initDate();
        initView();
    }

    private void initDate() {

        list=new ArrayList<logisticsMessageInfo>();
        logisticsMessageInfo info1=new logisticsMessageInfo();
        info1.title="分配车辆1";
        info1.date="2016-1-1";
        info1.time1_left="11:22";
        info1.time1_right="装车.....";
        info1.time2_left="11:02";
        info1.time2_right="分配司机";
        info1.name="王小二";
        info1.phoneNum="12312312323";
        info1.date2="2016-2-1";
        info1.time3_left="23:23";
        info1.time3_right="货物出库";
        logisticsMessageInfo info2=new logisticsMessageInfo();
        info2.title="分配车辆1";
        info2.date="2016-1-1";
        info2.time1_left="11:22";
        info2.time1_right="装车.....";
        info2.time2_left="11:02";
        info2.time2_right="分配司机";
        info2.name="王小二";
        info2.phoneNum="12312312323";
        info2.date2="2016-2-1";
        info2.time3_left="23:23";
        info2.time3_right="货物出库";
        list.add(info1);
        list.add(info2);

    }

    private void initView() {
        img_create_logistics_back= (ImageView) findViewById(R.id.img_create_logistics_back);
        lv_detatils= (ListView) findViewById(R.id.lv_details);
        img_create_logistics_back.setOnClickListener(this);
        adapter=new TrackingDistributionDetailsAdapter(list,this);
        lv_detatils.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_create_logistics_back:
                finish();
                break;
        }
    }
}
