package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyPageAdapter;
import com.silianchuangye.sumao.success.adapter.SellerViewPageAdapter;
import com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement.Fragments.SellerAllOrdersFragment;
import com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement.Fragments.SellerAlreadyPaidFragment;
import com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement.Fragments.SellerChangedFragment;
import com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement.Fragments.SellerHasBeenCanceled;
import com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement.Fragments.SellerToBePaidFragment;
import com.silianchuangye.sumao.success.utils.ShowCalendar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/14 0014.
 * 我是卖家，订单管理详情界面
 */
public class OrderManagement extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{

    private ArrayList<Fragment> listFragment;
    private ArrayList<String> titleList;
    private SellerViewPageAdapter adapter;
    private ViewPager seller_view_pager;
    private TabLayout seller_tab_layout;
    private TextView tv_title_name_order;
    private ImageView ivSearch_order_layout_top;
    private ImageView ivBack_order_layout_top;
//    popupWindow的布局文件
    private View popupWindowView;
    private PopupWindow popupWindow;
    private RelativeLayout order_layout_top;
    private LinearLayout bottom_pre_sale_search;
    private RelativeLayout seller_order_management_order_type;
    private RelativeLayout seller_order_management_order_status;
    private RelativeLayout order_date_start;
    private RelativeLayout order_date_end;
    private RelativeLayout seller_order_management_billing_statusr;
    private String first = "first";
    private String second = "second";
    private boolean flag=true;
    private ArrayAdapter<String> LvAdapter;
    private List<String> list=new ArrayList<String>();
    private ListView order_type_list_view;
    private ListView order_status_list_view;
    private ListView billing_statusr_list_view;
//    起始日期和结束日期
    private TextView order_date_start1;
    private TextView order_date_end1;
    private ImageView end_candela;
    private ImageView start_candela;
    private TextView seller_order_management_order_status1;
    private TextView seller_order_management_order_type1;
    private TextView seller_order_management_billing_statusr1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_order_management_activity);
        seller_view_pager = ((ViewPager) findViewById(R.id.seller_view_pager));
        seller_tab_layout = ((TabLayout) findViewById(R.id.seller_tab_layout));
        tv_title_name_order = ((TextView) findViewById(R.id.tv_title_name_order));
        ivSearch_order_layout_top = ((ImageView) findViewById(R.id.ivSearch_order_layout_top));
        ivSearch_order_layout_top.setOnClickListener(this);
        ivBack_order_layout_top = ((ImageView) findViewById(R.id.ivBack_order_layout_top));
        ivBack_order_layout_top.setOnClickListener(this);
        order_layout_top = ((RelativeLayout) findViewById(R.id.order_layout_top));
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
            case R.id.ivBack_order_layout_top:
                finish();
                break;
//            阴影
            case R.id.bottom_pre_sale_search:
//                cancel_tv.setVisibility(View.INVISIBLE);
//                iv_screen_title_bar_search.setVisibility(View.VISIBLE);
                popupWindow.dismiss();
                break;
//            订单类型
            case R.id.seller_order_management_order_type:

                first = "one";
                logicdisplayshowListView(order_type_list_view,first);
                hideListView(order_status_list_view);
                hideListView(billing_statusr_list_view);
                break;
            //        订单状态
            case R.id.seller_order_management_order_status:

                first = "two";
                logicdisplayshowListView(order_status_list_view,first);
                hideListView(order_type_list_view);
                hideListView(billing_statusr_list_view);
                break;
            //        开始时间
            case R.id.order_date_start:
                ShowCalendar.showDate(order_date_start1,this,true);

                break;
            //        结束时间
            case R.id.order_date_end:
                ShowCalendar.showDate(order_date_end1,this,true);
                break;
            //        开票状态
            case R.id.seller_order_management_billing_statusr:
                first = "three";
                logicdisplayshowListView(billing_statusr_list_view,first);
                hideListView(order_type_list_view);
                hideListView(order_status_list_view);
                break;

            default:
                break;
        }
    }

    private void showPopupWindow() {
        popupWindowView = View.inflate(this,R.layout.seller_order_management_search_popup_window_view,null);
//        阴影区域
        bottom_pre_sale_search = ((LinearLayout) popupWindowView.findViewById(R.id.bottom_pre_sale_search));
        bottom_pre_sale_search.setOnClickListener(this);
//        订单类型
        seller_order_management_order_type = ((RelativeLayout) popupWindowView.findViewById(R.id.seller_order_management_order_type));
        seller_order_management_order_type.setOnClickListener(this);
//        订单状态
        seller_order_management_order_status = ((RelativeLayout) popupWindowView.findViewById(R.id.seller_order_management_order_status));
        seller_order_management_order_status.setOnClickListener(this);
//        开始时间
        order_date_start = ((RelativeLayout) popupWindowView.findViewById(R.id.order_date_start));
        order_date_start.setOnClickListener(this);
//        结束时间
        order_date_end = ((RelativeLayout) popupWindowView.findViewById(R.id.order_date_end));
        order_date_end.setOnClickListener(this);
//        开票状态
        seller_order_management_billing_statusr = ((RelativeLayout) popupWindowView.findViewById(R.id.seller_order_management_billing_statusr));
        seller_order_management_billing_statusr.setOnClickListener(this);
//        三个下拉框
//          订单类型
        order_type_list_view = ((ListView) popupWindowView.findViewById(R.id.order_type_list_view));
        order_type_list_view.setOnItemClickListener(this);
//          订单状态
        order_status_list_view = ((ListView) popupWindowView.findViewById(R.id.order_status_list_view));
        order_status_list_view.setOnItemClickListener(this);
//          开票状态
        billing_statusr_list_view = ((ListView) popupWindowView.findViewById(R.id.billing_statusr_list_view));
        billing_statusr_list_view.setOnItemClickListener(this);
//          日期的相关控件
        order_date_start1 = ((TextView) popupWindowView.findViewById(R.id.order_date_start1));
        order_date_end1 = ((TextView) popupWindowView.findViewById(R.id.order_date_end1));
        end_candela = ((ImageView) popupWindowView.findViewById(R.id.end_candela));
        start_candela = ((ImageView) popupWindowView.findViewById(R.id.start_candela));
        //          订单类型
        seller_order_management_order_type1 = ((TextView) popupWindowView.findViewById(R.id.seller_order_management_order_type1));
        //          订单状态
        seller_order_management_order_status1 = ((TextView) popupWindowView.findViewById(R.id.seller_order_management_order_status1));
        //          开票状态
        seller_order_management_billing_statusr1 = ((TextView) popupWindowView.findViewById(R.id.seller_order_management_billing_statusr1));
        showSearchDialog();
    }

    private void showSearchDialog() {
        popupWindowView.measure(0,0);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        popupWindow = new PopupWindow(popupWindowView,width,popupWindowView.getMeasuredHeight());
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(order_layout_top,0,0);
        initListViewData();
    }
    /**
     * 如果已经打开一个listView，点击另一个，这个listView影藏
     * @param listview
     * @param first
     */
    private void logicdisplayshowListView(ListView listview, String first) {

        if (second.equals(first))
        {
            showLv(listview);
            second  = first;
        }else {
            flag  = true;
            showLv(listview);
        }

    }

    private void hideListView(ListView listview)
    {
        listview.setVisibility(View.GONE);
    }

    //显示或隐藏listView
    private void showLv(ListView Lv){
        if(flag){
            Lv.setVisibility(View.VISIBLE);
            Lv.setAdapter(LvAdapter);
            flag=false;
        }else{
            Lv.setVisibility(View.GONE);
            flag=true;
        }
    }

    //给ListView添加虚假数据源
    private void initListViewData() {
        for(int i=0;i<5;i++){
            list.add("itemview"+i);
        }
        LvAdapter=new ArrayAdapter<String>(this,
                R.layout.item_view,
                R.id.tv_item,list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //          订单类型
        if(order_type_list_view==parent){
            seller_order_management_order_type1.setText(LvAdapter.getItem(position));
            order_type_list_view.setVisibility(View.GONE);
        }
        //          订单状态
        if(order_status_list_view==parent){
            seller_order_management_order_status1.setText(LvAdapter.getItem(position));
            order_status_list_view.setVisibility(View.GONE);
        }
        //          开票状态
        if(billing_statusr_list_view==parent){
            seller_order_management_billing_statusr1.setText(LvAdapter.getItem(position));
            billing_statusr_list_view.setVisibility(View.GONE);
        }
    }
}
