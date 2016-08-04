package com.silianchuangye.sumao.success.fragments.homepage.groupbuying;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupBuyingActivity extends AppCompatActivity{
    private ListView lv_group_buying;
    private List<Map<String,Object>> list;
    private SimpleAdapter adapter;
    private ImageView back,search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_buying);
        init();
        addData();
        event();
    }

    /**
     * 初始化数据
     */
    public void init(){
        back= (ImageView) findViewById(R.id.ivBack_group_layout_top);
        search= (ImageView) findViewById(R.id.ivSearch_group_layout_top);
        lv_group_buying= (ListView) findViewById(R.id.lvAuction_Auction_Layout);
        list=new ArrayList<Map<String,Object>>();


    }

    /**
     * 添加数据
     */
    public void addData(){
        for (int i=0;i<=7;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("name","北京四联");
            map.put("starttime","2016-08-03 18:00");
            map.put("price","8888");
            map.put("count","12345");
            map.put("number","60t");
            map.put("way","自提");
            map.put("comm","北京四联创业集团");
            map.put("cangku","讯帮物流1号库");
            map.put("endtime","2016-08-03 18:00");
            list.add(map);

        }
        adapter=new SimpleAdapter(GroupBuyingActivity.this,list,R.layout.layout_for_group_buying,
                new String[]{"name","starttime","price","count",
                             "number","way","comm","cangku","endtime"},
                new int[]{
                        R.id.tv_auction_name,
                        R.id.tv_auction_time,
                        R.id.tv_auction_price,
                        R.id.tv_auction_number,
                        R.id.tv_group_number,
                        R.id.tv_group_way_value,
                        R.id.tv_auction_acdress,
                        R.id.tv_auction_commAdress,
                        R.id.tv_time_end
                });
        lv_group_buying.setAdapter(adapter);

    }

    /**
     * 事件监听
     */
    public void event(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupBuyingActivity.this.finish();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 搜索按钮的点击事件
                 */
            }
        });
        /**
         * ListView的item的点击事件
         */
        lv_group_buying.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 if (position%2==0){
                     Intent intent=new Intent(GroupBuyingActivity.this,GroupBuyingSuccessActivity.class);
                     intent.putExtra("state","ok");
                     startActivity(intent);

                 }else{

                     Intent intent=new Intent(GroupBuyingActivity.this,GroupBuyingSuccessActivity.class);
                     intent.putExtra("state","no");
                     startActivity(intent);

                 }
            }
        });

    }


}
