package com.silianchuangye.sumao.success;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.silianchuangye.sumao.success.adapter.MyPageAdapter;
import com.silianchuangye.sumao.success.fragments.OrderAlreadyFinishFragment;
import com.silianchuangye.sumao.success.fragments.OrderAlreadygoodsFragment;
import com.silianchuangye.sumao.success.fragments.OrderCancelFragment;
import com.silianchuangye.sumao.success.fragments.OrderStaypayFragment;
import com.silianchuangye.sumao.success.fragments.OrderUpdateFragment;
import com.silianchuangye.sumao.success.fragments.OrderallFragment;
import com.silianchuangye.sumao.success.fragments.OrderstayshipmentsFragment;

import java.util.ArrayList;

public class OrderGoodsActivity extends AppCompatActivity {

    private ArrayList<Fragment> listFragment;

    private TabLayout tlDemo;
    private ViewPager vpDemo;
    private MyPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_goods);
        listFragment=new ArrayList<Fragment>();
        OrderallFragment all=new OrderallFragment();
        listFragment.add(all);
        OrderStaypayFragment pay=new OrderStaypayFragment();
        listFragment.add(pay);
        OrderstayshipmentsFragment staygoods=new OrderstayshipmentsFragment();
        listFragment.add(staygoods);
        OrderAlreadygoodsFragment goods=new OrderAlreadygoodsFragment();
        listFragment.add(goods);
        OrderAlreadyFinishFragment finish=new OrderAlreadyFinishFragment();
        listFragment.add(finish);
        OrderCancelFragment cancel=new OrderCancelFragment();
        listFragment.add(cancel);
        OrderUpdateFragment update=new OrderUpdateFragment();
        listFragment.add(update);
        adapter=new MyPageAdapter(getSupportFragmentManager());
        adapter.setData(listFragment);


        ArrayList<String> listString=new ArrayList<String>();
        listString.add("全部订单");
        listString.add("待支付");
        listString.add("待发货");
        listString.add("已发货");
        listString.add("已完成");
        listString.add("已变更");
        listString.add("已取消");
        adapter.setTitles(listString);

        tlDemo= (TabLayout) findViewById(R.id.tlDemo);
        vpDemo= (ViewPager) findViewById(R.id.vpDemo);
        vpDemo.setAdapter(adapter);
        tlDemo.setupWithViewPager(vpDemo);
        tlDemo.setTabMode(tlDemo.MODE_SCROLLABLE);

    }
}
