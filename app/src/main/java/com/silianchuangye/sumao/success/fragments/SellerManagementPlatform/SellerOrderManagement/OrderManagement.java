package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyPageAdapter;
import com.silianchuangye.sumao.success.adapter.SellerViewPageAdapter;
import com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement.Fragments.SellerAllOrdersFragment;
import com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement.Fragments.SellerAlreadyPaidFragment;
import com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement.Fragments.SellerChangedFragment;
import com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement.Fragments.SellerHasBeenCanceled;
import com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement.Fragments.SellerToBePaidFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/14 0014.
 */
public class OrderManagement extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<Fragment> listFragment;
    private ArrayList<String> titleList;
    private SellerViewPageAdapter adapter;
    private ViewPager seller_view_pager;
    private TabLayout seller_tab_layout;
    private TextView tv_title_name_order;
    private ImageView ivSearch_order_layout_top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_order_management_activity);
        seller_view_pager = ((ViewPager) findViewById(R.id.seller_view_pager));
        seller_tab_layout = ((TabLayout) findViewById(R.id.seller_tab_layout));
        tv_title_name_order = ((TextView) findViewById(R.id.tv_title_name_order));
        ivSearch_order_layout_top = ((ImageView) findViewById(R.id.ivSearch_order_layout_top));
        ivSearch_order_layout_top.setOnClickListener(this);
        tv_title_name_order.setText("订单管理");
        initData();
        seller_view_pager.setAdapter(adapter);
        seller_tab_layout.setupWithViewPager(seller_view_pager);
        seller_tab_layout.setTabMode(seller_tab_layout.MODE_SCROLLABLE);

    }

    private void initData() {
        listFragment=new ArrayList<Fragment>();
        SellerAllOrdersFragment sellerAllOrdersFragment = new SellerAllOrdersFragment();
        listFragment.add(sellerAllOrdersFragment);
        SellerToBePaidFragment sellerToBePaidFragment = new SellerToBePaidFragment();
        listFragment.add(sellerToBePaidFragment);
        SellerAlreadyPaidFragment sellerAlreadyPaidFragment = new SellerAlreadyPaidFragment();
        listFragment.add(sellerAlreadyPaidFragment);
        SellerChangedFragment sellerChangedFragment = new SellerChangedFragment();
        listFragment.add(sellerChangedFragment);
        SellerHasBeenCanceled sellerHasBeenCanceled = new SellerHasBeenCanceled();
        listFragment.add(sellerHasBeenCanceled);

        titleList = new ArrayList<>();
        titleList.add("全部订单");
        titleList.add("待支付");
        titleList.add("已支付");
        titleList.add("已变更");
        titleList.add("已取消");

        adapter = new SellerViewPageAdapter(getSupportFragmentManager(),this);
        adapter.setData(listFragment);
        adapter.setTitles(titleList);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ivSearch_order_layout_top:
                showPopupWindow();
                break;
        }
    }

    private void showPopupWindow() {
    }
}
