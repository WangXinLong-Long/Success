package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.acutionManagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriceSearchinfoActivity extends AppCompatActivity {
    private ImageView iv_Back;
    private ListView lv_search_info;
    private List<Map<String,Object>> list;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_searchinfo);
        init();
        addData();
        event();
    }

    /**
     * 初始化数据
     */
    public void init(){
        iv_Back= (ImageView) findViewById(R.id.iv_Back_Price_Search);
        lv_search_info= (ListView) findViewById(R.id.lv_search_info);
        list=new ArrayList<Map<String,Object>>();
    }

    /**
     * 给ListView添加数据
     */
    public void addData(){
        for (int i=0;i<=3;i++){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("people","业务员"+i+1);
            map.put("time","2016-07-18 09:00:00");
            map.put("price","1000元");
            map.put("number","200吨");
            map.put("count","200000元");
            list.add(map);
        }
        adapter=new SimpleAdapter(PriceSearchinfoActivity.this,list,R.layout.item_price_serach_info,
                new String[]{"people","time","price","number","count"},
                new int[]{R.id.tv_search_info_people,
                        R.id.tv_search_info_time,
                        R.id.tv_acution_price,
                        R.id.tv_acution_number,
                        R.id.tv_auctiob_count
                }
                );
        lv_search_info.setAdapter(adapter);
    }

    /**
     * 事件的监听
     */
    public void event(){
        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PriceSearchinfoActivity.this.finish();
            }
        });

    }
}
