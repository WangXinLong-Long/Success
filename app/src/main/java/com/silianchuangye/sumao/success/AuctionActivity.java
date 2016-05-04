package com.silianchuangye.sumao.success;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class AuctionActivity extends AppCompatActivity {
    private ImageView imageback;
    private ListView lvAuction;
    private List<Map<String,Object>> list;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction);
        init();
        event();
    }
    public void init(){
        imageback= (ImageView) findViewById(R.id.ivBack_auction_layout_top);
        lvAuction= (ListView) findViewById(R.id.lvAuction_Auction_Layout);
        list=new ArrayList<Map<String,Object>>();
        Map<String,Object> map=new Hashtable<String,Object>();
        map.put("name","福建联合");
        list.add(map);
        Map<String,Object> map1=new Hashtable<String,Object>();
        map1.put("name","福建联合");
        list.add(map1);
        adapter=new SimpleAdapter(this,list,R.layout.auctionitem,new String[]{"name"},new int[]{R.id.tv_auction_name});
        lvAuction.setAdapter(adapter);


    }
    public void event(){

    }
}
