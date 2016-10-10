package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.LogisticsSelectAddress_Adapter;
import com.silianchuangye.sumao.success.fragments.bean.Logistics_SelectAddress_Info;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class Logistics_SelectAddress extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private ImageView img_back;
    private TextView tv_manager;
    private ListView lv;
    private List<Logistics_SelectAddress_Info> list=new ArrayList<Logistics_SelectAddress_Info>();
    private LogisticsSelectAddress_Adapter adater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics__select_address);
        initDate();
        initView();
    }

    private void initDate() {
        Logistics_SelectAddress_Info info=new Logistics_SelectAddress_Info();
        info.tv_address_title="北京市房山区";
        info.tv_address_message="北京市房山区燕山东流水路x号";
        info.tv_address_name="张三";
        info.tv_address_phone_num="12312312312";
        Logistics_SelectAddress_Info info2=new Logistics_SelectAddress_Info();
        info2.tv_address_title="北京市房山区2";
        info2.tv_address_message="北京市房山区燕山东流水路xx号";
        info2.tv_address_name="张四";
        info2.tv_address_phone_num="22312312312";
        Logistics_SelectAddress_Info info3=new Logistics_SelectAddress_Info();
        info3.tv_address_title="北京市房山区3";
        info3.tv_address_message="北京市房山区燕山东流水路xxx号";
        info3.tv_address_name="张五";
        info3.tv_address_phone_num="32312312312";
        list.add(info);
        list.add(info2);
        list.add(info3);
    }

    private void initView() {
        img_back= (ImageView) findViewById(R.id.img_logistics_title_bar_back);
        tv_manager= (TextView) findViewById(R.id.tv_logistics_title_bar);
        lv= (ListView) findViewById(R.id.lv_logistics_select);
        img_back.setOnClickListener(this);
        tv_manager.setOnClickListener(this);
        lv.setOnItemClickListener(this);
        adater=new LogisticsSelectAddress_Adapter(list,this);
        lv.setAdapter(adater);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_logistics_title_bar_back:
                finish();
                break;
            case R.id.tv_logistics_title_bar:
                Toast.makeText(this,"管理",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent();
        intent.putExtra("address",list.get(position).tv_address_title);
        intent.putExtra("address_message",list.get(position).tv_address_message);
        intent.setAction("select");
        sendBroadcast(intent);
        finish();
    }
}
