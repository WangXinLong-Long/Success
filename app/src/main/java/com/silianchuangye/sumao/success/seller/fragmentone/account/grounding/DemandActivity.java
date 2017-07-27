package com.silianchuangye.sumao.success.seller.fragmentone.account.grounding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class DemandActivity extends AppCompatActivity {
    private ImageView iv_back;
    private TextView tv_Title;
    private TextView save;
    private ListView lv_demand;
    private List<Map<String,Object>> list;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demand);
        init();
        addData();
    }
    public void init(){
        iv_back= (ImageView) findViewById(R.id.iv_back);
        tv_Title= (TextView) findViewById(R.id.tv_Title);
        tv_Title.setText("上架需求计划");
        save= (TextView) findViewById(R.id.save);
        save.setText("保存");
        lv_demand= (ListView) findViewById(R.id.lv_demand);
        list=new ArrayList<>();
    }

    public void addData(){
        Map<String,Object> map1=new Hashtable<>();
        map1.put("left","产品名称");
        map1.put("right","");
        map1.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map1);
        Map<String,Object> map2=new Hashtable<>();
        map2.put("left","上架时间");
        map2.put("right","");
        map2.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map2);
        Map<String,Object> map3=new Hashtable<>();
        map3.put("left","计量单位");
        map3.put("right","");
        map3.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map3);
        Map<String,Object> map4=new Hashtable<>();
        map4.put("left","仓库名称");
        map4.put("right","");
        map4.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map4);
        Map<String,Object> map5=new Hashtable<>();
        map5.put("left","配送方式");
        map5.put("right","");
        map5.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map5);
        Map<String,Object> map6=new Hashtable<>();
        map6.put("left","合同模版");
        map6.put("right","");
        map6.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map6);
        Map<String,Object> map7=new Hashtable<>();
        map7.put("left","收款账户");
        map7.put("right","");
        map7.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map7);
        Map<String,Object> map8=new Hashtable<>();
        map8.put("left","是否审批");
        map8.put("right","");
        map8.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map8);
        Map<String,Object> map9=new Hashtable<>();
        map9.put("left","产品备注");
        map9.put("right","");
        map9.put("icon",R.mipmap.my_sumao_iv_order);
        list.add(map9);
        adapter=new SimpleAdapter(DemandActivity.this,list,R.layout.item_firm_info,
                new String[]{"left","right","icon"},new int[]{R.id.tv_firm_info,
        R.id.tvValue_firm_info,R.id.ivMore_firm_info
        });
        lv_demand.setAdapter(adapter);
        adapter.notifyDataSetChanged();




    }
}
