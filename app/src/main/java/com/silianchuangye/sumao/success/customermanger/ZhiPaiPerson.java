package com.silianchuangye.sumao.success.customermanger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.BuyAdapter;
import com.silianchuangye.sumao.success.fragments.bean.BuyInfo;

import java.util.ArrayList;
import java.util.List;

public class ZhiPaiPerson extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener{
    private ImageView img_back;
    private TextView tv_save,tv_center;
    private ListView lv_buy;
    private List<BuyInfo> list;
    private BuyAdapter adapter;
    private int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_pai_person);
        initDate();
        initView();
    }
    private void initDate() {
        list=new ArrayList<BuyInfo>();
        BuyInfo info1=new BuyInfo();
        info1.tv="张三";
        BuyInfo info2=new BuyInfo();
        info2.tv="李四";
        BuyInfo info3=new BuyInfo();
        info3.tv="王五";
        list.add(info1);
        list.add(info2);
        list.add(info3);
    }

    private void initView() {
        String str=getIntent().getStringExtra("name");
        tv_center= (TextView) findViewById(R.id.tv_top_center);
        tv_center.setText(str);
        lv_buy= (ListView) findViewById(R.id.lv_buy);
        tv_save= (TextView) findViewById(R.id.tv_customer_message_editor);
        img_back= (ImageView) findViewById(R.id.img_logistics_title_bar_back);
        adapter=new BuyAdapter(list,this);
        lv_buy.setAdapter(adapter);
        tv_save.setOnClickListener(this);
        lv_buy.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        i=position;
        for(int i=0;i<list.size();i++){
            if (i!=position){
                list.get(i).flag=false;
            }
        }
        list.get(position).flag=!list.get(position).flag;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_customer_message_editor:
                boolean Flag=false;
                for(BuyInfo info:list){
                    if(info.flag){
                        Flag=true;
                    }
                }
                if(Flag) {
                    Intent intent = new Intent();
                    intent.putExtra("name",list.get(i).tv);
                    setResult(2,intent);
                    finish();
                }else{
                    Toast.makeText(this,"至少选择一项",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
