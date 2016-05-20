package com.silianchuangye.sumao.success.fragments.auction;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyPageAdapter;

import java.util.ArrayList;
import java.util.List;

public class VesselOneActivity extends AppCompatActivity {
    private ImageView ivBack_vessel_one;
    private ImageView ivShop_vessel_one;
    private TabLayout tab_vessel_one;
    private ViewPager vp_vessel_one;
    private ArrayList<Fragment> list;
    private MyPageAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vessel_one);
        ivBack_vessel_one= (ImageView) findViewById(R.id.ivBack_vessel_one);
        ivBack_vessel_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VesselOneActivity.this.finish();
            }
        });
        ivShop_vessel_one= (ImageView) findViewById(R.id.ivShop_vessel_one);
        ivShop_vessel_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转购物车界面
            }
        });

        list=new ArrayList<Fragment>();
        list.add(new RealTimeMarketFragment());
        list.add(new MyOfferFragment());
        adapter=new MyPageAdapter(getSupportFragmentManager());
        adapter.setData(list);

        ArrayList<String> listString=new ArrayList<String>();
        listString.add("实时行情");
        listString.add("我的报价");
        adapter.setTitles(listString);

        tab_vessel_one= (TabLayout) findViewById(R.id.tab_vessel_one);
        vp_vessel_one= (ViewPager) findViewById(R.id.vp_vessel_one);
        vp_vessel_one.setAdapter(adapter);
        tab_vessel_one.setupWithViewPager(vp_vessel_one);


     //   vp_vessel_one.setupWi

      //  tab_vessel_one.set
    }

}
