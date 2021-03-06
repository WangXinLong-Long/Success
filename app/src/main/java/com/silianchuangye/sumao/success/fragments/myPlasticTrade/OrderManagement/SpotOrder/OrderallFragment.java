package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.OrderDetails.AlreadyPaidActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.orderStaypayBean.Order;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.orderStaypayBean.OrderCl;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.orderStaypayBean.ToBePaid;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderallFragment extends Fragment {
    private ExpandableListView elvDemo;
            SharedPreferences sp ;
        String unique123 ;
    private List<Map<String,Object>>listparrent;
    private List<List<Map<String,Object>>> listitem ;
    MyAdapter adapter;
    String orderId,type;
    int page=1;
    boolean ListFlag;
    String OrderType="fixedPricingOrder",subType=null,Kpstate="",startDate="",endDate="",company="",OrderId="";
    public OrderallFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         sp=getActivity().getSharedPreferences("sumao", Activity.MODE_PRIVATE);
       unique123= sp.getString("unique", "");
        listparrent=new ArrayList<Map<String,Object>>();
        listitem=new ArrayList<List<Map<String,Object>>>();
        View view=inflater.inflate(R.layout.fragment_orderall, container, false);
        //实例化
        PullToRefreshLayout ptr=(PullToRefreshLayout)view.findViewById(R.id.refresh_view);
        elvDemo=(ExpandableListView)ptr.getPullableView();
        ptr.setOnPullListener(new MyPullListener());
        //去掉expandListview的特别的下拉标志
        elvDemo.setGroupIndicator(null);
        //去掉ListView之间的线
        elvDemo.setDivider(null);

        elvDemo.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(getContext(), "点击title", Toast.LENGTH_SHORT).show();
                if ("已支付".equals(listparrent.get(groupPosition).get("states"))){
                    Intent intent = new Intent();
                    intent.putExtra("ID",listparrent.get(groupPosition).get("id").toString());
                    intent.putExtra("type",type);
                    Log.e("TAG","yizhifutype==="+type);
                    intent.setClass(getActivity(),AlreadyPaidActivity.class);
                    startActivity(intent);
                }else if("待支付".equals(listparrent.get(groupPosition).get("states"))){
                    Intent intent = new Intent();
                    intent.putExtra("ID",listparrent.get(groupPosition).get("id").toString());
                    intent.putExtra("type",type);
                    Log.e("TAG","type====="+type);
                    intent.setClass(getActivity(), SpotOrder.class);
                    startActivity(intent);
                }
                return true;

            }
        });

        return view;
    }
    boolean Flag;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("TAG","Flag-----"+Flag);
        if(isVisibleToUser){
            if(Flag){
                sendMy(subType,Kpstate,startDate,endDate,company,OrderId,OrderType);
            }
        }else{
            if(!Flag){
                if(listitem!=null) {
                    listitem.clear();
                }
                if(listparrent!=null){
                    listparrent.clear();
                }
                page=1;subType=null;Kpstate="";startDate="";endDate="";company="";OrderId="";OrderType="fixedPricingOrder";
                sendMy(subType,Kpstate,startDate,endDate,company,OrderId,OrderType);
                if(adapter!=null) {
                    adapter.notifyDataSetChanged();
                }
                Flag=false;
            }
        }
    }
    public  void sendMy(String subType,String KPstate,String startDate,String endDate,String company,String OrderId,String OrderType){
        if (!ListFlag) {
            listparrent=new ArrayList<Map<String,Object>>();
            listitem=new ArrayList<List<Map<String,Object>>>();
        }
        RequestParams params=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/myOrders");
        params.setCharset("UTF-8");
        params.setAsJsonContent(true);
        JSONObject job=new JSONObject();
        try {
            job.put("searchCompanyName", company.trim());//公司
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.setBodyContent(job.toString());
        params.addParameter("searchOrderType",OrderType);
        params.addParameter("searchOrderState","0");
        params.addParameter("pageNum",page);
        params.addParameter("searchOrderId", OrderId);//订单
        params.addParameter("startDate",startDate);//开始日期
        params.addParameter("endDate", endDate);//结束日期
        params.addParameter("submitType",subType);//查询类型
        params.addParameter("searchCheckType",KPstate);//开票状态
        params.addParameter("_dynSessConf", unique123);
        Log.e("TAG", "parames======" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("Jobs Create-全部订单-->"+result);
                Gson gson = new Gson();
                ToBePaid toBePaid = gson.fromJson(result, ToBePaid.class);
                LogUtils.log("toBepaid--->"+toBePaid.toString());
                String info=toBePaid.getInfo();
                if(info.equals("fail")){
                    Toast.makeText(getActivity(),"请重新登陆",Toast.LENGTH_SHORT).show();
                    new TiQu(getActivity()).showLogin();
                    getActivity().finish();
                }
                String count = toBePaid.getCount();
                if(count.equals("0")){
                    listparrent.clear();
                    listitem.clear();
                }
                List<Order> orders= toBePaid.getOrder();
                for (int i = 0; i <  orders.size(); i++) {
                    List<OrderCl> cl = orders.get(i).getCl();
                    String state=orders.get(i).getState();//状态
                    String shippingGroupState=orders.get(i).getShippingGroupState();
                    type=orders.get(i).getType();
                    String cl_amount="";
                    String state1=getState(state,type,shippingGroupState);
                    String owner=orders.get(i).getOwner();//采购员
                    orderId=orders.get(i).getOrderId();//订单编号
                    List<Map<String,Object>> list1=new ArrayList<Map<String,Object>>();
                    for(int k=0;k<cl.size();k++){
                        cl_amount=cl.get(k).getCl_amount();//金额
                        String cl_mingcheng=cl.get(k).getCl_mingcheng();//产品名称
                        String cl_fenlei="";
                        LogUtils.log("Jobs Created -->mingc=="+cl_mingcheng);

                        Map<String,Object> map=new Hashtable<String,Object>();
                        map.put("type",cl_fenlei);
                        map.put("name",cl_mingcheng);
                        list1.add(map);
                        listitem.add(list1);
                    }
                    Map<String,Object> map1=new Hashtable<String,Object>();
                    map1.put("id",orderId);
                    map1.put("price",cl_amount);
                    map1.put("states",state1);
                    map1.put("name",owner);
                    Log.e("TAG","map1-----"+map1);
                    listparrent.add(map1);
                }
                if(!ListFlag) {
                    adapter = new MyAdapter(listparrent, listitem, getActivity());
                    elvDemo.setAdapter(adapter);
                    if(adapter!=null && listparrent!=null){
                        for (int i = 0; i < listparrent.size(); i++) {
                            elvDemo.expandGroup(i);
                        }
                    }
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG","ex==="+ex);
                Log.e("TAG","失败呢");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private String getState(String state,String type,String shippingGroupState){
        String s="ldkjfg";
        if(state.equals("SUBMITTED")||state.equals("PENDING_APPROVAL")||state.equals("APPROVED")||state.equals("FAILED_APPROVAL")){
            if(type.equals("offlineOrder")) {
                s = "订单生成";
            }else{
                s="待支付";
            }
        }
        else if(state.equals("DEPOSIT_CONFIRMED")){
            s="支付保证金已冻结";
        }else if(state.equals("QUOTED")){
            if(shippingGroupState.equals("INITIAL")) {
                s = "已支付";
            }else{
                s="已发货";
            }
        }else if (state.equals("NO_PENDING_ACTION")){
            s="已完成";
        }else if (state.equals("REMOVED")||state.equals("PENGDING_CANCEL")){
            if(type.equals("fixedPricingOrder")||type.equals("traderFixedPricingOrder")){
                s="已取消";
            }else{
                s="竞拍失败";
            }
        }else if (state.equals("CHANGED")){
            s="已变更";
        }else{
            s="等待客服处理";
        }
        return s;
    }
    private  class MyPullListener implements PullToRefreshLayout.OnPullListener {

        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            // 下拉刷新操作
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    // 千万别忘了告诉控件刷新完毕了哦！
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    Log.e("TAG","下拉刷子新");
                    page=1;
                    ListFlag=false;
                    sendMy(subType,Kpstate,startDate,endDate,company,OrderId,OrderType);
                    adapter.notifyDataSetChanged();
                }
            }.sendEmptyMessageDelayed(0,1000);
        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            // 加载操作
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    // 千万别忘了告诉控件加载完毕了哦！
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    Log.e("TAG","上拉加载");
                    ListFlag=true;
                    page+=1;
                    sendMy(subType,Kpstate,startDate,endDate,company,OrderId,OrderType);
                    adapter.notifyDataSetChanged();
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }
    }
}
