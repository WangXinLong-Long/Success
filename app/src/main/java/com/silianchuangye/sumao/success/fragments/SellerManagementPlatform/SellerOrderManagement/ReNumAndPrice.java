package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReNumAndPrice extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
private String title,sort,name,old_price,new_price,all_price,num,cangku;
private int i;
    private TextView tv_title;
    private ListView lv;
    private ImageView img_back;
    private RelativeLayout relative_save;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_num_and_price);
        title=getIntent().getStringExtra("title");
        sort=getIntent().getStringExtra("sort");
        name=getIntent().getStringExtra("name");
        old_price=getIntent().getStringExtra("old");
        new_price=getIntent().getStringExtra("new");
        all_price=getIntent().getStringExtra("allprice");
        num=getIntent().getStringExtra("num");
        cangku=getIntent().getStringExtra("cangku");
        initDate();
        initView();
    }

    private void initDate() {
        list=new ArrayList<Map<String, Object>>();
        Map<String,Object>map1=new HashMap<String,Object>();
        map1.put("left","分类");
        map1.put("right",sort);
        Map<String,Object>map2=new HashMap<String,Object>();
        map2.put("left","产品名称");
        map2.put("right",title);
        Map<String,Object>map3=new HashMap<String,Object>();
        map3.put("left","原单价(元/吨)");
        map3.put("right",old_price);
        Map<String,Object>map4=new HashMap<String,Object>();
        map4.put("left","新单价(元/吨)");
        map4.put("right",new_price);
        map4.put("img",R.mipmap.my_sumao_iv_order);
        Map<String,Object>map5=new HashMap<String,Object>();
        map5.put("left","数量(吨)");
        map5.put("right",num);
        map5.put("img",R.mipmap.my_sumao_iv_order);
        Map<String,Object>map6=new HashMap<String,Object>();
        map6.put("left","产品总价(元/吨)");
        map6.put("right",all_price);
        Map<String,Object>map7=new HashMap<String,Object>();
        map7.put("left","仓库");
        map7.put("right",cangku);
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
        relative_save= (RelativeLayout) findViewById(R.id.tv_logistics_title_bar_search);
        tv_title= (TextView) findViewById(R.id.tv_re_num_and_price_title);
        tv_title.setText(title);
        lv= (ListView) findViewById(R.id.lv);

        adapter=new SimpleAdapter(this,list,R.layout.item_customer_message_lv,
                new String[]{
                        "left","right","img"
                },
                new int[]{
                        R.id.tv_item_customer_message_lv_left,
                        R.id.tv_item_customer_message_lv_right,
                        R.id.img_item_customer_message
                });
        lv.setAdapter(adapter);
        img_back.setOnClickListener(this);
        relative_save.setOnClickListener(this);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_logistics_title_bar_back:
                finish();
                break;
            case R.id.tv_logistics_title_bar_search:
                i=getIntent().getIntExtra("int",0);
                String str=list.get(3).get("right").toString();
                String num=list.get(4).get("right").toString();
                Intent intent=new Intent();
                intent.putExtra("new",str);
                intent.putExtra("num",num);
                intent.putExtra("i",i);
                setResult(1,intent);
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(list.get(position).get("left").equals("新单价(元/吨)")){
            Intent intent=new Intent(this,NewPrice.class);
            startActivityForResult(intent,0);
        }
        if(list.get(position).get("left").equals("数量(吨)")){
            Intent intent=new Intent(this,Num.class);
            startActivityForResult(intent,0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1){
            list.get(3).put("right",data.getStringExtra("new"));
            adapter.notifyDataSetChanged();
        }
        if(resultCode==2){
            list.get(4).put("right",data.getStringExtra("num"));
            adapter.notifyDataSetChanged();
        }
    }
}
