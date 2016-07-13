package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.ViewLogisticsDemands;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.ExpandableListViewAdapter;
import com.silianchuangye.sumao.success.adapter.LogisticsDemandExpandableListViewAdapter;
import com.silianchuangye.sumao.success.custom.CustomExpandableListView;
import com.silianchuangye.sumao.success.fragments.bean.LogisticsListChild;
import com.silianchuangye.sumao.success.fragments.bean.LogisticsListParent;
import com.silianchuangye.sumao.success.utils.ShowCalendar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/8 0008.
 * 查看物流需求
 */
public class ViewLogisticsDemand extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private CustomExpandableListView listview;
    private LogisticsDemandExpandableListViewAdapter adapter;
//    private ExpandableListViewAdapter adapter;
    private List<LogisticsListParent> logisticsListParentslist;
    private List<LogisticsListChild> logisticsListChildrenlist;
    LogisticsListChild logisticsListChild;
    LogisticsListParent logisticsListParent;
//    标题栏

    private RelativeLayout view_logistics_demand_activity_title;
    private TextView tv_screen_title_bar_title;
    private ImageView iv_screen_title_bar_back;
    private ImageView iv_screen_title_bar_search;
    //    popupWindow
    View popupWindowView;
    PopupWindow popupWindow;
    private LinearLayout popup_window_back;
    private TextView popup_window_logistics_demand1;
    private TextView popup_window_type1;
    private TextView popup_window_distribution_mode1;
    private TextView popup_window_delivery_date1;

    String first = "first";
    String second = "second";
    private boolean flag=true;
    private ArrayAdapter<String> LvAdapter;
    private List<String> list=new ArrayList<String>();
    private ListView lv_logistics_demand;
    private ListView lv_type;
    private ListView lv_distribution_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_logistics_demand_activity);
        listview = ((CustomExpandableListView) findViewById(R.id.listview));
        view_logistics_demand_activity_title = ((RelativeLayout) findViewById(R.id.view_logistics_demand_activity_title));
//        题目
        tv_screen_title_bar_title = ((TextView) findViewById(R.id.tv_screen_title_bar_title));
        tv_screen_title_bar_title.setText("查看物流需求");
//        返回按钮
        iv_screen_title_bar_back = ((ImageView) findViewById(R.id.iv_screen_title_bar_back));
        iv_screen_title_bar_back.setOnClickListener(this);
//        搜索框
        iv_screen_title_bar_search = ((ImageView) findViewById(R.id.iv_screen_title_bar_search));
        iv_screen_title_bar_search.setOnClickListener(this);
        listview.setDivider(null);
        initData();
        adapter = new LogisticsDemandExpandableListViewAdapter(this,logisticsListParentslist);
        listview.setAdapter(adapter);
        listview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                return false;
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(lv_logistics_demand==parent){
            popup_window_logistics_demand1.setText(LvAdapter.getItem(position));
            lv_logistics_demand.setVisibility(View.GONE);
        }
        if(lv_type==parent){
            popup_window_type1.setText(LvAdapter.getItem(position));
            lv_type.setVisibility(View.GONE);
        }
        if(lv_distribution_mode==parent){
            popup_window_distribution_mode1.setText(LvAdapter.getItem(position));
            lv_distribution_mode.setVisibility(View.GONE);
        }
    }

    private void initData() {
        logisticsListChildrenlist = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            logisticsListChild = new LogisticsListChild();
            logisticsListChild.setClassification("LLDPE"+i);
            logisticsListChild.setNumber((int)(Math.random()*10)+"");
            logisticsListChild.setProductName("中海石油"+i);
            logisticsListChild.setProductOrderNumber(2012+i+"");
            logisticsListChildrenlist.add(logisticsListChild);
        }
        logisticsListParentslist = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            logisticsListParent = new LogisticsListParent();
//            设置联系方式
            logisticsListParent.setContactInformation(1334444555+i+"");
//            提货车号
            logisticsListParent.setDeliveryNumber("京G"+54524+i);
//            配送方式
            logisticsListParent.setDistributionMode("卖家配送");
//            身份证号
            logisticsListParent.setIdCardNumber("15242816985893458"+i);
//            物流需求号
            logisticsListParent.setLogisticsDemand("LDE"+54654645);
//            子列表
            logisticsListParent.setLogisticsListChildren(logisticsListChildrenlist);
//            取货人姓名
            logisticsListParent.setPickUpPerson("张"+i+"山");
//            备注
            logisticsListParent.setRemarks("这个东西很值钱");
//             状态
            logisticsListParent.setState("已确认");
            logisticsListParentslist.add(logisticsListParent);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_screen_title_bar_back:
                finish();
                break;
            case R.id.iv_screen_title_bar_search:
                showPopupWindow();
                break;
            case R.id.popup_window_back:
                popupWindow.dismiss();
                break;
            case R.id.popup_window_logistics_demand1:
                first = "one";
                logicdisplayshowListView(lv_logistics_demand,first);
                hideListView(lv_type);
                hideListView(lv_distribution_mode);
                break;
            case R.id.popup_window_type1:
                first = "two";
//                showLv(Lv_kaipiao);
                logicdisplayshowListView(lv_type,first);
                hideListView(lv_logistics_demand);
                hideListView(lv_distribution_mode);
                break;
            case R.id.popup_window_distribution_mode1:
                first = "three";
//                showLv(Lv_dingdan);
                logicdisplayshowListView(lv_distribution_mode,first);
                hideListView(lv_type);
                hideListView(lv_logistics_demand);
                break;
            case R.id.popup_window_delivery_date1:
                ShowCalendar.showDate(popup_window_delivery_date1,this);
                break;
            default:
                break;

        }
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

    private void showPopupWindow() {
        initListViewData();
        initPopupWinowView();
        showPopupWindowView();
    }

    private void hideListView(ListView listview)
    {
        listview.setVisibility(View.GONE);
    }

    private void showPopupWindowView() {
        popupWindowView.measure(0,0);
        int w = getWindowManager().getDefaultDisplay().getWidth();
        popupWindow = new PopupWindow(popupWindowView,w,popupWindowView.getMeasuredHeight());
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(view_logistics_demand_activity_title,0,0);
    }

    private void initPopupWinowView() {
        popupWindowView = View.inflate(this,R.layout.view_logistics_demand_popup_window,null);
//        点击透明区域
        popup_window_back = ((LinearLayout) popupWindowView.findViewById(R.id.popup_window_back));
        popup_window_back.setOnClickListener(this);

        popup_window_logistics_demand1 = ((TextView) popupWindowView.findViewById(R.id.popup_window_logistics_demand1));
        popup_window_type1 = ((TextView) popupWindowView.findViewById(R.id.popup_window_type1));
        popup_window_distribution_mode1 = ((TextView) popupWindowView.findViewById(R.id.popup_window_distribution_mode1));
        popup_window_delivery_date1 = ((TextView) popupWindowView.findViewById(R.id.popup_window_delivery_date1));

        popup_window_logistics_demand1.setOnClickListener(this);
        popup_window_type1.setOnClickListener(this);
        popup_window_distribution_mode1.setOnClickListener(this);
        popup_window_delivery_date1.setOnClickListener(this);

        lv_logistics_demand = ((ListView) popupWindowView.findViewById(R.id.lv_logistics_demand));
        lv_type = ((ListView) popupWindowView.findViewById(R.id.lv_type));
        lv_distribution_mode = ((ListView) popupWindowView.findViewById(R.id.lv_distribution_mode));

        lv_logistics_demand.setOnItemClickListener(this);
        lv_type.setOnItemClickListener(this);
        lv_distribution_mode.setOnItemClickListener(this);

    }
}
