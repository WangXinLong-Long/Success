package com.silianchuangye.sumao.success.fragments.homepage.auction;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class AuctionActivity extends AppCompatActivity {
    private ImageView imageback;
    private ListView lvAuction;
    private List<Map<String,Object>> list;


    private Button bu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction);
        init();
        event();
    }
    public void init(){
        imageback= (ImageView) findViewById(R.id.ivBack_auction_layout_top);
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuctionActivity.this.finish();
            }
        });
        lvAuction= (ListView) findViewById(R.id.lvAuction_Auction_Layout);
        list=new ArrayList<Map<String,Object>>();
        Map<String,Object> map=new Hashtable<String,Object>();
        map.put("name","福建联合");
        list.add(map);
        Map<String,Object> map1=new Hashtable<String,Object>();
        map1.put("name","福建联合");
        list.add(map1);
        MyAdapter adapter=new MyAdapter(this,list,R.layout.auctionitem,new String[]{"name"},new int[]{R.id.tv_auction_name});
        lvAuction.setAdapter(adapter);

    }
    public void event(){

    }
    class MyAdapter extends SimpleAdapter{


        public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=getLayoutInflater().inflate(R.layout.auctionitem,null);
            bu= (Button) view.findViewById(R.id.bt_auctionitem);
            bu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(bu.getText().toString().equals("参与竞拍")){
                       Intent intent=new Intent(AuctionActivity.this,OpenAuctionActivity.class);
                        startActivity(intent);
                    }
                }
            });
            return view;
        }
    }
}
