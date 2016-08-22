package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.OrderDetails.AlreadyPaidActivity;
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

    private List<Map<String,Object>>listparrent=new ArrayList<Map<String,Object>>();
    private List<List<Map<String,Object>>> listitem=new ArrayList<List<Map<String,Object>>>() ;
    MyAdapter adapter;
    String orderId,type;
    int page=1;
    String subType=null,Kpstate="",startDate="",endDate="",company="",OrderId="";
    public OrderallFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        listparrent=new ArrayList<Map<String,Object>>();
        listitem=new ArrayList<List<Map<String,Object>>>();
        View view=inflater.inflate(R.layout.fragment_orderall, container, false);
        //实例化
        elvDemo= (ExpandableListView) view.findViewById(R.id.elvDemo);
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
                sendMy(subType,Kpstate,startDate,endDate,company,OrderId);
            }
        }else{
            if(!Flag){
                if(listitem!=null) {
                    listitem.clear();
                }
                if(listparrent!=null){
                    listparrent.clear();
                }
                subType=null;Kpstate="";startDate="";endDate="";company="";OrderId="";
                sendMy(subType,Kpstate,startDate,endDate,company,OrderId);
                if(adapter!=null) {
                    adapter.notifyDataSetChanged();
                }
                Flag=false;
            }
        }
    }
    public  void sendMy(String subType,String KPstate,String startDate,String endDate,String company,String OrderId){
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
        params.addParameter("searchOrderType","fixedPricingOrder");
        params.addParameter("searchOrderState","0");
        params.addParameter("pageNum",page);
        params.addParameter("searchOrderId", OrderId);//订单
        params.addParameter("startDate",startDate);//开始日期
        params.addParameter("endDate", endDate);//结束日期
        params.addParameter("submitType",subType);//查询类型
        params.addParameter("searchCheckType",KPstate);//开票状态
//        SharedPreferences sp =getActivity().getSharedPreferences("sumao", Activity.MODE_PRIVATE);
//        String unique123 = sp.getString("unique", "");
//        params.addParameter("_dynSessConf", unique123);
        Log.e("TAG", "parames======" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG","result----"+result);
                try {
                    JSONObject job=new JSONObject(result);
                    String count=job.getString("count");
                    Log.e("TAG","count----"+count);
                    if(count.equals("0")){
                        listparrent.clear();
                        listitem.clear();
                    }
                    String str=job.getString("order");
                    JSONArray jay=new JSONArray(str);
                    for(int i=0;i<jay.length();i++){
                        JSONObject j= (JSONObject) jay.get(i);
                        String cl= (String) j.getString("cl");
                        String state=j.getString("state");//状态
                        String shippingGroupState=j.getString("shippingGroupState");
                        type=j.getString("type");
                        String cl_amount="";
                        String state1=getState(state,type,shippingGroupState);
                        String owner=j.getString("owner");//采购员
                        orderId=j.getString("orderId");//订单编号

                        JSONArray j1=new JSONArray(cl);
                        for(int k=0;k<j1.length();k++){
                            JSONObject job1= (JSONObject) j1.get(k);
                            cl_amount=job1.getString("cl_amount");//金额
                            String cl_mingcheng=job1.getString("cl_mingcheng");//产品名称
                            String cl_fenlei=job1.getString("cl_fenlei");
                            Log.e("TAG","mingc=="+cl_mingcheng);
                            List<Map<String,Object>> list1=new ArrayList<Map<String,Object>>();
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
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter=new MyAdapter(listparrent,listitem,getActivity());
                elvDemo.setAdapter(adapter);
                if(adapter!=null && listparrent!=null){
                    for (int i = 0; i < listparrent.size(); i++) {
                        elvDemo.expandGroup(i);
                    }
                }
                adapter.notifyDataSetChanged();
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
}
