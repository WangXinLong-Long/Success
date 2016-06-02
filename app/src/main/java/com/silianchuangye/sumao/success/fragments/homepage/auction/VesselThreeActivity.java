package com.silianchuangye.sumao.success.fragments.homepage.auction;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyPageAdapter;

import java.util.ArrayList;

public class VesselThreeActivity extends AppCompatActivity {
    private ImageView ivBack_vessel_three;
    private ImageView ivShop_vessel_three;
    private TabLayout tab_vessel_three;
    private ViewPager vp_vessel_three;
    private ArrayList<Fragment> list;
    private MyPageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vessel_three);
        ivBack_vessel_three= (ImageView) findViewById(R.id.ivBack_vessel_three);
        ivBack_vessel_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VesselThreeActivity.this.finish();
            }
        });
        ivShop_vessel_three= (ImageView) findViewById(R.id.ivShop_vessel_three);
        ivShop_vessel_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转购物车界面
            }
        });
        list=new ArrayList<Fragment>();
        list.add(new ContractFragment());
        list.add(new ParameterFragment());
        list.add(new PhotographFragment());
        adapter=new MyPageAdapter(getSupportFragmentManager());
        adapter.setData(list);

        ArrayList<String> listString=new ArrayList<String>();
        listString.add("合同详情");
        listString.add("产品参数");
        listString.add("量价图");
        adapter.setTitles(listString);
        vp_vessel_three= (ViewPager) findViewById(R.id.vp_vessel_three);
        tab_vessel_three= (TabLayout) findViewById(R.id.tab_vessel_three);
        vp_vessel_three.setAdapter(adapter);
        tab_vessel_three.setupWithViewPager(vp_vessel_three );


    }
}
