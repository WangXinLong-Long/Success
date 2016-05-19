package com.silianchuangye.sumao.success.fragments.homepage.theprice;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyPageAdapter;

import java.util.ArrayList;

//点价界面
public class ChinaNorth_Price extends AppCompatActivity {
    private TabLayout tab_ChinaNorth_Price;
    private ViewPager vp_ChinaNorth_Price;
    private ImageView img_ChinaNorth_Price_back;
    private TextView tv_ChinaNorth_Price_dingwei;
    private ArrayList<Fragment>listFragment;//页卡视图集合
    MyPageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_china_north__price);
        img_ChinaNorth_Price_back= (ImageView) findViewById(R.id.img_chinanorth_price_back);
        tab_ChinaNorth_Price= (TabLayout) findViewById(R.id.tab_chinanorth_price);
        vp_ChinaNorth_Price= (ViewPager) findViewById(R.id.vp_chinanorth_price);
        tv_ChinaNorth_Price_dingwei= (TextView) findViewById(R.id.tv_chinanorth_price_dingwei);
        img_ChinaNorth_Price_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listFragment=new ArrayList<Fragment>();
        ProcessFragment f1=new ProcessFragment();
        listFragment.add(f1);
        RuleFragment f2=new RuleFragment();
        listFragment.add(f2);

        adapter=new MyPageAdapter(getSupportFragmentManager());
        adapter.setData(listFragment);

        ArrayList<String> listString=new ArrayList<String>();
        listString.add("点价流程");
        listString.add("点价规则");

        adapter.setTitles(listString);
        vp_ChinaNorth_Price.setAdapter(adapter);
        tab_ChinaNorth_Price.setupWithViewPager(vp_ChinaNorth_Price);
    }
}
