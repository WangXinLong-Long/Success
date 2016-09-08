package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement.Fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaPiaoMessage extends AppCompatActivity implements View.OnClickListener{
    private ImageView img_back;
    private RelativeLayout tv_logistics_title_bar_search;
    private ListView lv_fapiao_message;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_piao_message);
        initDate();
        initView();
    }
    private void initDate() {
        list=new ArrayList<Map<String, Object>>();
        Map<String,Object>map1=new HashMap<String,Object>();
        map1.put("left","开票状态");
        map1.put("right","请填写开票状态");
        map1.put("img",R.mipmap.my_sumao_iv_order);
        Map<String,Object>map2=new HashMap<String,Object>();
        map2.put("left","票据");
        map2.put("right","增值税发票");
        map2.put("img",R.mipmap.my_sumao_iv_order);
        Map<String,Object>map3=new HashMap<String,Object>();
        map3.put("left","快递公司");
        map3.put("right","顺丰快递");
        map3.put("img",R.mipmap.my_sumao_iv_order);
        Map<String,Object>map4=new HashMap<String,Object>();
        map4.put("left","快递单号");
        map4.put("right","1111111111111111111111");
        map4.put("img",R.mipmap.my_sumao_iv_order);
        Map<String,Object>map5=new HashMap<String,Object>();
        map5.put("left","备注");
        map5.put("right","无");
        map5.put("img",R.mipmap.my_sumao_iv_order);

        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);

    }
    private void initView() {
        img_back= (ImageView) findViewById(R.id.img_logistics_title_bar_back);
        tv_logistics_title_bar_search= (RelativeLayout) findViewById(R.id.tv_logistics_title_bar_search);
        lv_fapiao_message= (ListView) findViewById(R.id.lv_fapiao_message);
        adapter=new SimpleAdapter(this,list,R.layout.item_customer_message_lv,
                new String[]{
                        "left","right","img"
                },
                new int[]{
                        R.id.tv_item_customer_message_lv_left,
                        R.id.tv_item_customer_message_lv_right,
                        R.id.img_item_customer_message
                });
        lv_fapiao_message.setAdapter(adapter);
        img_back.setOnClickListener(this);
        tv_logistics_title_bar_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_logistics_title_bar_back:
                finish();
                break;
            case R.id.tv_logistics_title_bar_search:
                Toast.makeText(this,"保存",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
