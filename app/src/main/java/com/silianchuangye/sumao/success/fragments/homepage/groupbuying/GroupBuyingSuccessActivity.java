package com.silianchuangye.sumao.success.fragments.homepage.groupbuying;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupBuyingSuccessActivity extends AppCompatActivity {
    private ListView lvDemo;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> list;

    private TextView tv_success;
    private TextView tv_failed;
    private LinearLayout aaa;
    private RelativeLayout layout_number;
    private RelativeLayout layout_Bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_buying_success);
        init();
        event();
        addData();
    }
    public void init(){
        Bundle bundle=getIntent().getExtras();
        String state=bundle.getString("state");
        lvDemo= (ListView) findViewById(R.id.lv_demo);
        list=new ArrayList<Map<String,Object>>();
        tv_failed= (TextView) findViewById(R.id.tv_failed);
        tv_success= (TextView) findViewById(R.id.tv_success);
        aaa= (LinearLayout) findViewById(R.id.aaa);
        layout_number= (RelativeLayout) findViewById(R.id.layout_number);
        layout_Bottom= (RelativeLayout) findViewById(R.id.layout_Bottom);
        if (state.equals("no")){
            tv_success.setVisibility(View.GONE);
            tv_failed.setVisibility(View.VISIBLE);
            aaa.setVisibility(View.GONE);
            layout_number.setVisibility(View.GONE);
            layout_Bottom.setVisibility(View.INVISIBLE);
        }


    }
    public void event(){
        lvDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    Intent intent=new Intent(GroupBuyingSuccessActivity.this,RuleActivity.class);
                    startActivity(intent);
                }else if (position==1){
                    Intent intent=new Intent(GroupBuyingSuccessActivity.this,JoinActivity.class);
                    startActivity(intent);

                }else if (position==2){

                }
            }
        });


    }
    public void addData(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name","交易规则");
        list.add(map);
        Map<String,Object> map1=new HashMap<String,Object>();
        map1.put("name","我的参团记录");
        list.add(map1);
        Map<String,Object> map2=new HashMap<String,Object>();
        map2.put("name","合同详情、产品参数、量价图");
        list.add(map2);

        adapter=new SimpleAdapter(this,list,R.layout.item_open_auction,
                new String[]{"name"},
                new int[]{R.id.tvRule_auction});
        lvDemo.setAdapter(adapter);


    }
}
