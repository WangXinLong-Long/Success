package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.acutionManagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuctionSuccessActivity extends AppCompatActivity {
    private ImageView iv_Back;
    private ListView lv_auction_success;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> list;

    private LinearLayout line_Top,line_Bottom;
    private RelativeLayout layout_time;
    private RelativeLayout layout_result,layout_result_bule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction_success);
        init();
        event();
        addData();
    }

    /**
     * 数据初始化
     */
    public void init(){
        layout_result= (RelativeLayout) findViewById(R.id.layout_result);
        layout_result_bule= (RelativeLayout) findViewById(R.id.layout_result_blue);
        iv_Back= (ImageView) findViewById(R.id.iv_Back_Price_Search);
        lv_auction_success= (ListView) findViewById(R.id.lv_auction_success);
        layout_time= (RelativeLayout) findViewById(R.id.layout_time);
        line_Top= (LinearLayout) findViewById(R.id.layout_time_line_top);
        line_Bottom= (LinearLayout) findViewById(R.id.layout_time_line_Bottom);
        list=new ArrayList<Map<String,Object>>();
        Bundle bundle=getIntent().getExtras();
        String state=bundle.getString("state");
        if (state.equals("进行中")){
            layout_time.setVisibility(View.VISIBLE);

            line_Bottom.setVisibility(View.VISIBLE);
            layout_result.setVisibility(View.INVISIBLE);
            layout_result_bule.setVisibility(View.INVISIBLE);
        }else if (state.equals("竞拍成功")){
            layout_time.setVisibility(View.INVISIBLE);
            line_Bottom.setVisibility(View.GONE);
            layout_result.setVisibility(View.VISIBLE);
            layout_result_bule.setVisibility(View.VISIBLE);
        }
    }
    /*
      事件监听
     */
    public void event(){
        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuctionSuccessActivity.this.finish();
            }
        });

    }

    /**
     * 添加数据
     */
    public void addData(){
        for (int i=0;i<=3;i++){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("comm","北京四联创业集团");
            map.put("time","2016-09-08 12:00");
            map.put("price","2000元");
            map.put("number","6吨");
            map.put("price_count","2000元");
            map.put("id","111111111111");
            list.add(map);
        }
        adapter=new SimpleAdapter(AuctionSuccessActivity.this,list,R.layout.item_auction_success,
                 new String[]{"comm","time","price","number","price_count","id"},
                 new int[]{
                         R.id.com,
                         R.id.tv_aucton_success,
                         R.id.tv_aucton_success_price,
                         R.id.tv_aucton_price_value,
                         R.id.tv_aucton_success_number,
                         R.id.tv_aucton_id_value

                 }
                );
        lv_auction_success.setAdapter(adapter);

    }
}
