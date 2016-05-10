package com.silianchuangye.sumao.success.fragments.OrderManagement.SpotOrder;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyPageAdapter;
import com.silianchuangye.sumao.success.fragments.thePrice.ChinaCenterFragment;
import com.silianchuangye.sumao.success.fragments.thePrice.ChinaEastFragment;
import com.silianchuangye.sumao.success.fragments.thePrice.ChinaNorthFragment;
import com.silianchuangye.sumao.success.fragments.thePrice.ChinaSouthFragment;
import com.silianchuangye.sumao.success.fragments.thePrice.ChinaWestFragment;


import java.util.ArrayList;

public class MidpointsListctivity extends AppCompatActivity {
    private ArrayList<Fragment> listFragment;
    private TabLayout tlDemo;
    private ViewPager vpDemo;
    private MyPageAdapter adapter;
    private ImageView back;
    private ImageView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midpoints_listctivity);
        back= (ImageView) findViewById(R.id.ivBack_orderPrice_layout_top);
        search= (ImageView) findViewById(R.id.ivSearch_orderPrice_layout_top);
        listFragment=new ArrayList<Fragment>();
        ChinaNorthFragment north=new ChinaNorthFragment();
        listFragment.add(north);
        ChinaCenterFragment center=new ChinaCenterFragment();
        listFragment.add(center);
        ChinaEastFragment east=new ChinaEastFragment();
        listFragment.add(east);
        ChinaSouthFragment south=new ChinaSouthFragment();
        listFragment.add(south);
        ChinaWestFragment west=new ChinaWestFragment();
        listFragment.add(west);
        adapter=new MyPageAdapter(getSupportFragmentManager());
        adapter.setData(listFragment);


        ArrayList<String> listString=new ArrayList<String>();
        listString.add("华北");
        listString.add("华中");
        listString.add("华东");
        listString.add("华南");
        listString.add("华西");

        adapter.setTitles(listString);

        tlDemo= (TabLayout) findViewById(R.id.tlDemo_OrderPrice_activity);
        vpDemo= (ViewPager) findViewById(R.id.vpDemo_OrderPrice_activity);
        vpDemo.setAdapter(adapter);
        tlDemo.setupWithViewPager(vpDemo);
       // tlDemo.setTabMode(tlDemo.MODE_SCROLLABLE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MidpointsListctivity.this.finish();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PopupWindow
            }
        });


    }
}
