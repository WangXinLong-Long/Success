package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.ViewLogisticsDemands;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.presenter.AddAddressPresenter;
import com.silianchuangye.sumao.success.utils.ShowCalendar;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
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
    private TextView popup_window_product_order1;
    private TextView popup_window_delivery_date1,tv_pop_logistics_end_date,tv_pop_logistics_start_date;
    private Button popup_window_search;

    String first = "first";
    String second = "second";
    private boolean flag=true;
    private ArrayAdapter<String> LvAdapter;
    private List<String> list=new ArrayList<String>();
    private ListView lv_logistics_demand;
    private ListView lv_type;
    private ListView lv_distribution_mode;
    private ShowCalendar showCalendar;
    SharedPreferences sp;
    String unique123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique123= sp.getString("unique", "");
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
        iv_screen_title_bar_search.setVisibility(View.VISIBLE);
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
        searchOrder("","","","","","","");
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
            if (i%2 == 0){
                logisticsListParent.setDistributionMode("卖家配送");

            }else {
                logisticsListParent.setDistributionMode("买家自提");
            }
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
                list.clear();
                list.add("请选择");
                list.add("出库配送");
                list.add("提货配送");
                list.add("仅出库");
                list.add("其他");
                initListViewData();
                logicdisplayshowListView(lv_type,first);
                hideListView(lv_logistics_demand);
                hideListView(lv_distribution_mode);
                break;
            case R.id.popup_window_distribution_mode1:
                list.clear();
                list.add("请选择");
                list.add("买方自提");
                list.add("卖家配送");
                initListViewData();
                first = "three";
//                showLv(Lv_dingdan);
                logicdisplayshowListView(lv_distribution_mode,first);
                hideListView(lv_type);
                hideListView(lv_logistics_demand);
                break;
            case R.id.popup_window_delivery_date1:
                ShowCalendar.showDate(popup_window_delivery_date1,this,false);
                break;
            case R.id.tv_pop_logistics_end_date:
                ShowCalendar.showDate(tv_pop_logistics_end_date,this,false);
                break;
            case R.id.tv_pop_logistics_start_date:
                ShowCalendar.showDate(tv_pop_logistics_start_date,this,false);
                break;
            case R.id.popup_window_search:
                //物流需求号
                String logisticsId;String logisticsType;
                 String shippingMethod;String orderId;String createDate;
                 String deliveryStartDate;String deliveryEndDate;
                logisticsId=popup_window_logistics_demand1.getText().toString();
                logisticsType=popup_window_type1.getText().toString();//类型
                //outbound_deliver 出库配送  collect_deliver 提货配送 outbound  仅出库 other  其他
                String type="";
                if(logisticsType.equals("出库配送")){
                    type="outbound_deliver";
                }else if(logisticsType.equals("提货配送")){
                    type="collect_deliver";
                }else if(logisticsId.equals("仅出库")){
                    type="outbound";
                }else if(logisticsType.equals("其他")){
                    type="other";
                }
                //配送方式（pickUp 买家自提，seller 卖家配送，independent 独立承运商，xunbang 迅邦配送）
                shippingMethod=popup_window_distribution_mode1.getText().toString();//配送方式
               String method="";
                if(shippingMethod.equals("买方自提")){
                    method="pickUp";
                }else if(shippingMethod.equals("卖家配送")){
                    method="seller";
                }else if(shippingMethod.equals("独立承运商")){
                    method="independent";
                }else if(shippingMethod.equals("迅邦配送")){
                    method="xunbang";
                }
                Log.e("TAG","配送方式=="+method);
                orderId=popup_window_product_order1.getText().toString();
                createDate=popup_window_delivery_date1.getText().toString();
                deliveryStartDate=tv_pop_logistics_start_date.getText().toString();
                deliveryEndDate=tv_pop_logistics_end_date.getText().toString();
                searchOrder(logisticsId,type,method,orderId,createDate,deliveryStartDate,deliveryEndDate);
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
//        for(int i=0;i<5;i++){
//            list.add("itemview"+i);
//        }
        LvAdapter=new ArrayAdapter<String>(this,
                R.layout.item_view,
                R.id.tv_item,list);
    }

    private void showPopupWindow() {
//        initListViewData();
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
        popup_window_product_order1= (TextView) popupWindowView.findViewById(R.id.popup_window_product_order1);
        popup_window_delivery_date1 = ((TextView) popupWindowView.findViewById(R.id.popup_window_delivery_date1));
        tv_pop_logistics_end_date= (TextView) popupWindowView.findViewById(R.id.tv_pop_logistics_end_date);
        tv_pop_logistics_start_date= (TextView) popupWindowView.findViewById(R.id.tv_pop_logistics_start_date);
        popup_window_search= (Button) popupWindowView.findViewById(R.id.popup_window_search);
//        popup_window_logistics_demand1.setOnClickListener(this);
        popup_window_type1.setOnClickListener(this);
        popup_window_distribution_mode1.setOnClickListener(this);
        popup_window_delivery_date1.setOnClickListener(this);
        tv_pop_logistics_end_date.setOnClickListener(this);
        tv_pop_logistics_start_date.setOnClickListener(this);
        popup_window_search.setOnClickListener(this);

        lv_logistics_demand = ((ListView) popupWindowView.findViewById(R.id.lv_logistics_demand));
        lv_type = ((ListView) popupWindowView.findViewById(R.id.lv_type));
        lv_distribution_mode = ((ListView) popupWindowView.findViewById(R.id.lv_distribution_mode));

        lv_logistics_demand.setOnItemClickListener(this);
        lv_type.setOnItemClickListener(this);
        lv_distribution_mode.setOnItemClickListener(this);

    }
    //查询接口
    private void searchOrder(String logisticsId,String logisticsType,String shippingMethod,String orderId,String createDate,String deliveryStartDate,String deliveryEndDate){
        RequestParams params=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/logistics/logisticsActor/logisticsInfoSearch");
        params.addParameter("_dynSessConf", unique123);
        params.addParameter("logisticsId",logisticsId);
        params.addParameter("logisticsType",logisticsType);
        params.addParameter("shippingMethod",shippingMethod);
        params.addParameter("orderId",orderId);
        params.addParameter("createDate",createDate);
        params.addParameter("deliveryStartDate",deliveryStartDate);
        params.addParameter("deliveryEndDate",deliveryEndDate);
        Log.e("TAG","params=="+params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG","result=="+result);
//                {"logisticsInfo":
//                    [{"orderInfo":[{"categoryName":"HDPE", "quantity":0,
//                                      "productName":"上海041","orderId":"10089700000032"}
//                                     ],
//                    "pickUpDateStart":1477584000000,
//                            "contactPhone":"18949087654",
//                            "logisticsId":"LGS2016102815102342",
//                            "pickUpDateEnd":1477584000000,
//                            "state":"pengding_confirm",
//                            "shippingMethod":"seller",
//                            "contactPerson":"dean",
//                            "detailAddress":"askdjh"},
                if(popupWindow!=null&&popupWindow.isShowing()){
                    popupWindow.dismiss();
                }
                try {
                    JSONObject job=new JSONObject(result);
                    String logisticsInfo=job.getString("logisticsInfo");
                    JSONArray jay=new JSONArray(logisticsInfo);
                    for(int i=0;i<jay.length();i++){
                        JSONObject job2= (JSONObject) jay.get(i);
                        String orderInfo=job2.getString("orderInfo");//具体商品
                        JSONArray jay2=new JSONArray(orderInfo);
                        for(int j=0;j<jay2.length();j++){
                            JSONObject job3= (JSONObject) jay2.get(j);
                            //数量
                            String quantity=job3.getString("quantity");
                            //分类
                            String categoryName=job3.getString("categoryName");
                            //名称
                            String productName=job3.getString("productName");
                            //订单号
                            String orderId=job3.getString("orderId");
                        }
                        //配送方式
                        String shippingMethod=job2.getString("shippingMethod");
                        Log.e("TAG","ship=="+shippingMethod);
                        //物流需求号
                        String logisticsId=job2.getString("logisticsId");
                        //状态
                        String state=job2.getString("state");
                        if(shippingMethod.equals("pickUp")){//买家自提
                            //提货车号
                            String licensePlateNo=job2.getString("licensePlateNo");
                            //提货人
                            String deliveryer=job2.getString("deliveryer");
                            //联系方式
                            String deliveryerTel=job2.getString("deliveryerTel");
                            //提货人身份证号
                            String idCard=job2.getString("idCard");
                            //提货时间
                            long shippingDate=job2.getLong("shippingDate");
                            String time=new SimpleDateFormat("yyyy-MM-dd").format(shippingDate);
                            //备注
                            String remarks=job2.getString("remarks");
                        }else{
                            //托运联系人
                            String shippingContact="";
                            if(job2.toString().contains("shippingContact")) {
                                shippingContact = job2.getString("shippingContact");
                            }else{
                                shippingContact="";
                            }
                            //卸货地址
                            String detailAddress=job2.getString("detailAddress");
                            //收货公司
                            String repeiptCompany="";
                             if(job2.toString().contains("repeiptCompany")) {
                                 repeiptCompany = job2.getString("repeiptCompany");
                             }
                            //提货结束时间
                            String pickUpDateStart=new SimpleDateFormat("yyyy-MM-dd").format(job2.getLong("pickUpDateStart"));
                            //提货开始时间
                            String pickUpDateEnd=new SimpleDateFormat("yyyy-MM-dd").format(job2.getLong("pickUpDateEnd"));
                            //卸货联系人
                            String contactPerson=job2.getString("contactPerson");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG","ex="+ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
