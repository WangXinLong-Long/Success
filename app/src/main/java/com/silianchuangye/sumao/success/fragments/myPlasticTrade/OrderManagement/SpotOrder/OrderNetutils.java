package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import android.os.Handler;
import com.silianchuangye.sumao.success.adapter.MyAdapter;

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
 * Created by Administrator on 2016/8/8.
 */
public class OrderNetutils {
    private List<Map<String,Object>> listparrent=new ArrayList<Map<String,Object>>();
    private List<List<Map<String,Object>>> listitem=new ArrayList<List<Map<String,Object>>>();
    String state1;
    private Context ctx;
    String url;//网络地址
    String searchOrderType,searchOrderState;//订单类型---//订单状态
    int pageNum,submitType;//页码，查询类型
    private List list=new ArrayList();
    public OrderNetutils(Context ctx){
        this.ctx=ctx;
    }
    public List sendMy(String url,int pageNum,int submitType,String searchOrderType,String searchOrderState){
        RequestParams params=new RequestParams(url);
        params.addParameter("pageNum",pageNum);
        params.addParameter("submitType",submitType);
        params.addParameter("searchOrderType",searchOrderType);
        params.addParameter("searchOrderState",searchOrderState);
//        params.addParameter("searchCompanyName",company);
        final SharedPreferences sp = ctx.getSharedPreferences("sumao", Activity.MODE_PRIVATE);
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
                        Toast.makeText(ctx, "获取数据失败", Toast.LENGTH_SHORT).show();
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
                            if (state.equals("SUBMITTED") || state.equals("PENDING_APPROVAL") || state.equals("APPROVED") || state.equals("FAILED_APPROVAL")) {
                                if (type.equals("offlineOrder")) {
                                    state1 = "订单生成";
                                } else {
                                    state1 = "待支付";
                                }
                            }
                            if (state.equals("DEPOSIT_CONFIRMED")) {
                                state1 = "支付保证金已冻结";
                            } else if (state.equals("QUOTED")) {
                                if (shippingGroupState.equals("INITIAL")) {
                                    state1 = "已支付";
                                } else {
                                    state1 = "已发货";
                                }
                            } else if (state.equals("NO_PENDING_ACTION")) {
                                state1 = "已完成";
                            } else if (state.equals("REMOVED") || state.equals("PENGDING_CANCEL")) {
                                if (type.equals("fixedPricingOrder") || type.equals("traderFixedPricingOrder")) {
                                    state1 = "已取消";
                                } else {
                                    state1 = "竞拍失败";
                                }
                            }
                            String owner = j.getString("owner");//采购员
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
                            map1.put("name", owner);
                            Log.e("TAG", "map1-----" + map1);
                            listparrent.add(map1);
                            list.add(listitem);
                            list.add(listparrent);
                            Log.e("TAG","list==="+list.size());
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
        return  list;
    }
}
