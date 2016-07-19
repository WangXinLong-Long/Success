package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.acutionManagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuctionSuccessActivity extends AppCompatActivity {
    private ImageView iv_Back;
    private ListView lv_auction_success;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> list;

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
        iv_Back= (ImageView) findViewById(R.id.iv_Back_Price_Search);
        lv_auction_success= (ListView) findViewById(R.id.lv_auction_success);
        list=new ArrayList<Map<String,Object>>();

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

    }
}
