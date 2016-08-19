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
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyAdapter;
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
public class ServiceBornFragment extends Fragment {
    private ExpandableListView elvDemo;
    private List<Map<String,Object>> listparrent;
    private List<List<Map<String,Object>>> listitem;
//    private List<Map<String,Object>> listparrent=new ArrayList<Map<String,Object>>();
//    private List<List<Map<String,Object>>> listitem=new ArrayList<List<Map<String,Object>>>();
    MyAdapter adapter;
    String state1;


    public ServiceBornFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_service_born, container, false);
        //实例化
        elvDemo= (ExpandableListView) view.findViewById(R.id.elvDemo);
        //去掉expandListview的特别的下拉标志
        elvDemo.setGroupIndicator(null);
        //去掉ListView之间的线
        elvDemo.setDivider(null);

        new Thread(){
            @Override
            public void run() {
                super.run();
                sendMy();
            }
        }.start();
//        listparrent=new ArrayList<Map<String,Object>>();
//        Map<String,Object> map1=new Hashtable<String,Object>();
//        map1.put("id","1000001");
//        map1.put("price","70000.0");
//        map1.put("states","待支付");
//        map1.put("name","zhangsan");
//        listparrent.add(map1);
//        Map<String,Object> map2=new Hashtable<String,Object>();
//        map2.put("id","1000001");
//        map2.put("price","88888888");
//        map2.put("states","待支付");
//        map2.put("name","lisi");
//        listparrent.add(map2);
//
//        listitem=new ArrayList<List<Map<String,Object>>>();
//        List<Map<String,Object>> list1=new ArrayList<Map<String,Object>>();
//        Map<String,Object> map=new Hashtable<String,Object>();
//        map.put("type","四联创业");
//        map.put("name","中国");
//        Map<String,Object> map3=new Hashtable<String,Object>();
//        map3.put("type","四联创业");
//        map3.put("name","中国");
//        Map<String,Object> map4=new Hashtable<String,Object>();
//        map4.put("type","四联创业");
//        map4.put("name","中国");
//        list1.add(map);
//        list1.add(map3);
//        list1.add(map4);
//
//        List<Map<String,Object>> list2=new ArrayList<Map<String,Object>>();
//
//        Map<String,Object> map5=new Hashtable<String,Object>();
//        map5.put("type","四联创业");
//        map5.put("name","中国");
//
//        list2.add(map5);
//
//        listitem.add(list1);
//        listitem.add(list2);
//
//        MyAdapter adapter=new MyAdapter(listparrent,listitem,getActivity());
//        elvDemo.setAdapter(adapter);
//        if(adapter!=null && listparrent!=null){
//            for (int i = 0; i < listparrent.size(); i++) {
//                elvDemo.expandGroup(i);
//            }}
        elvDemo.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                Toast.makeText(getContext(), "点击title", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("ID",listparrent.get(groupPosition).get("id").toString());
                intent.setClass(getActivity(), SpotOrder.class);
                startActivity(intent);
                return true;

            }
        });

        return view;
    }
    private void sendMy(){
        listparrent=new ArrayList<Map<String,Object>>();;
        listitem=new ArrayList<List<Map<String,Object>>>();;
        RequestParams params=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/myOrders3");
        params.addParameter("pageNum",1);
        params.addParameter("submitType",2);
        params.addParameter("searchOrderType","3");
        params.addParameter("searchOrderState","APPROVED");//订单生成
        params.addParameter("searchTrateType","offlineOrder");
//        params.addParameter("searchCompanyName",company);
        final SharedPreferences sp = getActivity().getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique123 = sp.getString("unique", "");
        params.addParameter("_dynSessConf", unique123);
        Log.e("TAG","parames======"+params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG","result----"+result);
                try {
                    JSONObject job = new JSONObject(result);
                    String info = job.getString("info");
                    if (info.equals("fail")) {
                        Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
                    } else {
                        String str = job.getString("order");
                        JSONArray jay = new JSONArray(str);
                        for (int i = 0; i < jay.length(); i++) {
                            Log.e("TAG", "i==" + i);
                            JSONObject j = (JSONObject) jay.get(i);
                            String cl = (String) j.getString("cl");
                            String state = j.getString("state");//状态
                            String shippingGroupState = j.getString("shippingGroupState");
                            String type = j.getString("type");
                            String cl_amount = "";
                           state1=getState(state,type,shippingGroupState);
//                            String owner = j.getString("owner");//采购员
                            String orderId = j.getString("orderId");//订单编号

                            JSONArray j1 = new JSONArray(cl);
                            for (int k = 0; k < j1.length(); k++) {
                                JSONObject job1 = (JSONObject) j1.get(k);
                                cl_amount = job1.getString("cl_amount");//金额
                                String cl_mingcheng = job1.getString("cl_mingcheng");//产品名称
                                String cl_fenlei = job1.getString("cl_fenlei");
                                Log.e("TAG", "mingc==" + cl_mingcheng);
                                List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
                                Map<String, Object> map = new Hashtable<String, Object>();
                                map.put("type", cl_fenlei);
                                map.put("name", cl_mingcheng);
                                list1.add(map);
                                listitem.add(list1);
                            }
                            Map<String, Object> map1 = new Hashtable<String, Object>();
                            map1.put("id", orderId);
                            map1.put("price", cl_amount);
                            map1.put("states", state1);
                            map1.put("name", "暂无");//暂时没有这个字段
                            Log.e("TAG", "map1-----" + map1);
                            listparrent.add(map1);
                        }
                    }
                    adapter = new MyAdapter(listparrent, listitem, getActivity());
                    elvDemo.setAdapter(adapter);
                    if (adapter != null && listparrent != null) {
                        for (int i = 0; i < listparrent.size(); i++) {
                            elvDemo.expandGroup(i);
                        }
                    }

                }catch(JSONException e){
                    e.printStackTrace();
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
}
