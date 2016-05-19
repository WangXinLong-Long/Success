package com.silianchuangye.sumao.success.fragments.homepage.theprice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//支付保证金界面
public class ChinaNorth_Margin extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
private ImageView img_margin_back;
    private TextView tv_margin_margin_price,tv_margin_product,tv_margin_num,
                tv_margin_price,tv_margin_all_price;
    private ListView lv_margin_bank;
    private Button btn_margin_zhifu;
    List<Map<String,Object>> list1 =new ArrayList<Map<String,Object>>();
    private SimpleAdapter adapter;
    private boolean flag;
    private MyMargin_Dialog dialog;
    private MyReciver my;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_china_north__margin);
        initDate();
        initView();
    }

    private void initDate() {

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("bankname","中国建设银行");
        map.put("bankmoney","111,111.00");
        Map<String,Object> map1=new HashMap<String,Object>();
        map1.put("bankname","中国工商银行");
        map1.put("bankmoney","123,456.00");
        list1.add(map);
        list1.add(map1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(my);
    }

    private void initView() {
        dialog=new MyMargin_Dialog(this);
        my=new MyReciver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("erroy");
        registerReceiver(my,filter);
        img_margin_back = (ImageView) findViewById(R.id.img_margin_back);
        tv_margin_margin_price = (TextView) findViewById(R.id.tv_margin_margin_price);
        tv_margin_product = (TextView) findViewById(R.id.tv_margin_product_name);
        tv_margin_num = (TextView) findViewById(R.id.tv_margin_num);
        tv_margin_price = (TextView) findViewById(R.id.tv_myprice_price);
        tv_margin_all_price = (TextView) findViewById(R.id.tv_margin_all_price);
        btn_margin_zhifu = (Button) findViewById(R.id.btn_margin_zifu);
        lv_margin_bank = (ListView) findViewById(R.id.lv_margin_bank);
        adapter = new SimpleAdapter(this,
                list1,
                R.layout.item_chinanorth_margin,
                new String[]{"bankname",
                        "bankmoney",
                },
                new int[]{R.id.tv_item_margin_bank,
                        R.id.tv_item_margin_price,
                });
        lv_margin_bank.setAdapter(adapter);
        img_margin_back.setOnClickListener(this);
        btn_margin_zhifu.setOnClickListener(this);
        lv_margin_bank.setOnItemClickListener(this);
        String margin_price = getIntent().getStringExtra("margin_price");
        String num = getIntent().getStringExtra("num");
        tv_margin_margin_price.setText(margin_price);
        tv_margin_num.setText(num);
        View v = View.inflate(this, R.layout.item_chinanorth_margin, null);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_margin_back:
                finish();
                break;
            case R.id.btn_margin_zifu:
                //弹出dialog
                dialog.show();
                break;
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ChinaNorth_Margin.this,"onItemClick",Toast.LENGTH_SHORT).show();
        flag=true;
    }
    private class MyReciver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            dialog.dismiss();
        }
    }
}
