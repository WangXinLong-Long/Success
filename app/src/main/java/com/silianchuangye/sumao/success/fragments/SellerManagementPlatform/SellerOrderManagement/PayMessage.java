package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayMessage extends AppCompatActivity implements View.OnClickListener{

    private ImageView img_back;
    private ListView lv_fapiao_message;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_message);
        initDate();
        initView();
    }
    private void initDate() {
        list=new ArrayList<Map<String, Object>>();
        Map<String,Object>map1=new HashMap<String,Object>();
        map1.put("left","买卖方户名");
        map1.put("right","四联创业");
        Map<String,Object>map2=new HashMap<String,Object>();
        map2.put("left","开户行");
        map2.put("right","建设银行");
        Map<String,Object>map3=new HashMap<String,Object>();
        map3.put("left","账号");
        map3.put("right","1239104819204829034829");
        Map<String,Object>map4=new HashMap<String,Object>();
        map4.put("left","支付金额");
        map4.put("right","111,11");
        Map<String,Object>map5=new HashMap<String,Object>();
        map5.put("left","服务费金额");
        map5.put("right","10");
        Map<String,Object>map6=new HashMap<String,Object>();
        map6.put("left","保证金冻结");
        map6.put("right","0");
        Map<String,Object>map7=new HashMap<String,Object>();
        map7.put("left","解冻情况");
        map7.put("right","0");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);
    }
    private void initView() {
        img_back= (ImageView) findViewById(R.id.img_logistics_title_bar_back);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_logistics_title_bar_back:
                finish();
                break;
        }
    }
}
