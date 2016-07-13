package com.silianchuangye.sumao.success.customermanger;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyPageAdapter;

import java.util.ArrayList;

public class CustomerManager extends AppCompatActivity implements View.OnClickListener{
    private ImageView img_back,img_search;
    private ViewPager vp;
    private TabLayout tab;
    private ArrayList<Fragment> listFragment;
    ArrayList<String> listString=new ArrayList<String>();
    private MyPageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customermanager);
        initDate();
        initView();

    }
    private void initDate() {
        listFragment=new ArrayList<Fragment>();
        All_Manager all=new All_Manager();
        Valid_Manager valid=new Valid_Manager();
        Inavild_Manager inavild=new Inavild_Manager();
        listFragment.add(all);
        listFragment.add(valid);
        listFragment.add(inavild);

        listString.add("全部客户");
        listString.add("有效客户");
        listString.add("无效客户");
        adapter=new MyPageAdapter(getSupportFragmentManager());
        adapter.setData(listFragment);
        adapter.setTitles(listString);

        vp.setAdapter(adapter);
    }

    private void initView() {
        img_back= (ImageView) findViewById(R.id.img_logistics_title_bar_back);
        img_search= (ImageView) findViewById(R.id.img_logistics_title_bar_search);
        tab= (TabLayout) findViewById(R.id.tlDemo_OrderPrice_activity);
        vp= (ViewPager) findViewById(R.id.vpDemo_OrderPrice_activity);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);

        img_back.setOnClickListener(this);
        img_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_logistics_title_bar_back:
                finish();
                break;
            case R.id.img_logistics_title_bar_search:
                Toast.makeText(this,"pop",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
