package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuerenShouhuoActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_child_title_bar_back;
    private TextView tv_child_title_bar_title;
    private ListView lvDemo;
    private List<Map<String, Object>> list;
    private String OrderId = "";
    String info="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queren_shouhuo);
        Bundle bundle = getIntent().getExtras();
        OrderId = bundle.getString("orderid");
        init();
        getinfo();
    }

    public void init() {
        iv_child_title_bar_back = (ImageView) findViewById(R.id.iv_child_title_bar_back);
        tv_child_title_bar_title = (TextView) findViewById(R.id.tv_child_title_bar_title);
        lvDemo = (ListView) findViewById(R.id.lvDemo);
        list = new ArrayList<>();
        iv_child_title_bar_back.setOnClickListener(this);
        tv_child_title_bar_title.setText("确认收货");
    }

    public void getinfo() {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/orderConfirm";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("orderId", OrderId);
        Log.d("确认收获的rp", rp.toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("确认收获", result);
                try {
                    JSONObject json_object = new JSONObject(result);
                    String orderConfirm = json_object.getString("orderConfirm");
                    JSONArray jsonarray = new JSONArray(orderConfirm);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject json = jsonarray.getJSONObject(i);
                        String productName = json.getString("productName");
                        String deliveryName = json.getString("deliveryName");
                        String orderNubmer = json.getString("orderNubmer");
                        String orderType = json.getString("orderType");
                        String shippedQuantity = json.getString("shippedQuantity");
                        String deliveyDetailId = json.getString("deliveyDetailId");
                        String createTime = json.getString("createTime");
                        String customerAcceptance="";


                        if (jsonarray.getJSONObject(i).toString().contains("customerAcceptance")) {
                             customerAcceptance = json.getString("customerAcceptance");
                        }else{
                            customerAcceptance="";
                        }
                        JSONObject object = new JSONObject(createTime);
                        String time = object.getString("time");
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("productName", productName);
                        map.put("deliveryName", deliveryName);
                        map.put("orderNubmer", orderNubmer);
                        map.put("orderType", orderType);
                        map.put("shippedQuantity", shippedQuantity);
                        map.put("deliveyDetailId", deliveyDetailId);
                        map.put("time", time);
                        map.put("customerAcceptance",customerAcceptance);
                        list.add(map);
                    }
                    MyAdapter adapter=new MyAdapter(list,QuerenShouhuoActivity.this);
                    lvDemo.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_child_title_bar_back:
                finish();
                break;
        }
    }

    public void getfailorsuccess(String deliveyDetailId,String shippedQuantity,String orderNubmer){

        String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/orderConfirmReceipt";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("deliveyDetailId",deliveyDetailId);
        rp.addParameter("shippedQuantity",shippedQuantity);
        rp.addParameter("orderNubmer",orderNubmer);
        rp.addParameter("acceptanceFrom","MOBILE");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result.contains("success")){
                    Toast.makeText(QuerenShouhuoActivity.this, "确认收货成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(QuerenShouhuoActivity.this,QuerenShouhuoActivity.class);
                    intent.putExtra("orderid",OrderId);
                    startActivity(intent);
                    finish();
                   // getinfo();
                   // info="success";
//                    SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
//                    SharedPreferences.Editor editor=sp.edit();
//                    editor.putString("quereninfo","success");
//                    editor.commit();

                }else{
                    Toast.makeText(QuerenShouhuoActivity.this, "确认收获失败,请重试!", Toast.LENGTH_SHORT).show();
                    //info="fail";
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        //return info;
    }


    class MyAdapter extends BaseAdapter {
        private List<Map<String, Object>> list_info;
        private Context context;

        public MyAdapter(List<Map<String, Object>> list_info, Context context) {
            this.list_info = list_info;
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(context, R.layout.layout_item_queren, null);
            TextView tv_chanpin_name = (TextView) convertView.findViewById(R.id.tv_chanpin_name);
            TextView tv_type = (TextView) convertView.findViewById(R.id.tv_type);
            Button bt_queren = (Button) convertView.findViewById(R.id.bt_queren);
            TextView tv_jiaohuo_num = (TextView) convertView.findViewById(R.id.tv_jiaohuo_num);
            TextView tv_jiaohuo_orderid = (TextView) convertView.findViewById(R.id.tv_jiaohuo_orderid);
            TextView tv_jiaohuo_type = (TextView) convertView.findViewById(R.id.tv_jiaohuo_type);
            TextView tv_jiaohuo_number = (TextView) convertView.findViewById(R.id.tv_jiaohuo_number);
            TextView tv_jiaohuo_info = (TextView) convertView.findViewById(R.id.tv_jiaohuo_info);
            TextView tv_jiaohuo_data= (TextView) convertView.findViewById(R.id.tv_jiaohuo_data);
            tv_chanpin_name.setText(list_info.get(position).get("productName").toString());
            tv_jiaohuo_num.setText(list_info.get(position).get("deliveryName").toString());
            tv_jiaohuo_orderid.setText(list_info.get(position).get("orderNubmer").toString());
            tv_jiaohuo_number.setText(list_info.get(position).get("shippedQuantity").toString()+"吨");
            tv_jiaohuo_info.setText(list_info.get(position).get("deliveyDetailId").toString());
            tv_jiaohuo_data.setText(new SimpleDateFormat("yyyy-MM-dd").format(Double.parseDouble(list_info.get(position).get("time").toString())));
            if (list_info.get(position).get("orderType").toString().equals("canceledAndModifyToNewOrder")){
                tv_jiaohuo_type.setText("退改新单");

            }else{
                tv_jiaohuo_type.setText("销售订单");
            }
            if (list_info.get(position).get("customerAcceptance").toString().equals("Y")) {
//                bt_queren.setVisibility(View.VISIBLE);
//                tv_type.setVisibility(View.GONE);
                bt_queren.setVisibility(View.GONE);
                tv_type.setVisibility(View.VISIBLE);

            }else{

                bt_queren.setVisibility(View.VISIBLE);
                tv_type.setVisibility(View.GONE);
            }
            bt_queren.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getfailorsuccess(list_info.get(position).get("deliveyDetailId").toString(),list_info.get(position).get("shippedQuantity").toString(),list_info.get(position).get("orderNubmer").toString());
//                    SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
//                    String info=sp.getString("quereninfo","");
//                    if (info.equals("success")){
//                        bt_queren.setVisibility(View.VISIBLE);
//                        tv_type.setVisibility(View.GONE);
//
//                    }else{
//
//                    }

                }
            });
            return convertView;
        }
    }
}
