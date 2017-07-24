package com.silianchuangye.sumao.success.fragments.myPlasticTrade.accountmeneger.open;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.WindowDecorActionBar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyPageAdapter;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class OpenzijinActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_child_title_bar_back;
    private TextView tv_child_title_bar_title;
    private TabLayout tab_layout;
    private ViewPager view_pager;
    private ArrayList<Fragment> listFragment;
    private ArrayList<String > listString;
    private MyPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openzijin);
       // getinfo();
        init();
    }
    private void init(){
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        tab_layout= (TabLayout) findViewById(R.id.tab_layout);
        view_pager= (ViewPager) findViewById(R.id.view_pager);
        tv_child_title_bar_title.setText("开通资金账户");
        listFragment=new ArrayList<>();
        JiansheFragment jiansheFragment=new JiansheFragment();
        listFragment.add(jiansheFragment);
        PinganFragment pinganFragment=new PinganFragment();
        listFragment.add(pinganFragment);
        KunlunFragment kunlunFragment=new KunlunFragment();
        listFragment.add(kunlunFragment);
        adapter = new MyPageAdapter(getSupportFragmentManager());
        adapter.setData(listFragment);
        listString = new ArrayList<String>();
        listString.add("建设银行");
        listString.add("平安银行");
        listString.add("昆仑银行");
        adapter.setTitles(listString);

        view_pager.setAdapter(adapter);
        tab_layout.setupWithViewPager(view_pager);
      //  tab_layout.setTabMode(TabLayout);




    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_child_title_bar_back:
                finish();
                break;
            default:
                break;
        }
    }
}
