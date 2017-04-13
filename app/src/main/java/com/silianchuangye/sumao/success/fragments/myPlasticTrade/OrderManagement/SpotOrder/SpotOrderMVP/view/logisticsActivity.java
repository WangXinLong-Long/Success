package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class logisticsActivity extends AppCompatActivity implements View.OnClickListener {
    private String orderId;
    private String unique;
    private SharedPreferences sp;
    private List<Map<String, Object>> list;
    private ImageView iv_child_title_bar_back;
    private TextView tv_child_title_bar_title;
    private ListView lv_logistics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics2);
        lv_logistics= (ListView) findViewById(R.id.lv_logistics);
        iv_child_title_bar_back =(ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(this);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        tv_child_title_bar_title.setText("物流信息");
        Bundle bundle = getIntent().getExtras();
        orderId = bundle.getString("orderId");
        sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique = sp.getString("unique", "");
        new Thread() {
            @Override
            public void run() {
                super.run();
                getinfo();
            }
        }.start();


    }


    public void getinfo() {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/logisticsInfo";
        RequestParams rp = new RequestParams(url);
        //rp.addParameter("orderId",orderId);
        rp.addParameter("orderId", orderId);
        // rp.addParameter("_dynSessConf",unique);
        Log.d("物流信息的rp", rp + "");
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("物流信息的返回值", result);

//                "in":[{"carNo":"2","idCard":"2","driverTel":"2","quantity":0.01,"trackNo":"di39001","driverName":"2","
// orderId":"10257200000003","imaterialName":"上海Q210"}
//                ,{"carNo":"2","idCard":"2","driverTel":"2","quantity":0.01,"trackNo":"di39002","driverName":"2","orderId":"10257200000003","imaterialName":"上海Q210"},
//                {"carNo":"2","idCard":"2","driverTel":"2","quantity":0.01,"trackNo":"di39003","driverName":"2","orderId":"10257200000003","imaterialName":"上海Q210"}],"info":"sucess"
                if (result.contains("sucess")) {
                    try {
                        list = new ArrayList<Map<String, Object>>();
                        JSONObject jsonObject = new JSONObject(result);
                        String in = jsonObject.getString("in");
                        JSONArray jsonArray = new JSONArray(in);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            Map<String, Object> map = new Hashtable<String, Object>();
                            map.put("carNo", object.getString("carNo"));
                            map.put("idCard", object.getString("idCard"));
                            map.put("driverTel", object.getString("driverTel"));
                            map.put("quantity", object.getString("quantity"));
                            map.put("trackNo", object.getString("trackNo"));
                            map.put("driverName", object.getString("driverName"));
                            map.put("orderId", object.getString("orderId"));
                            map.put("imaterialName", object.getString("imaterialName"));
                            list.add(map);
                        }
                        //添加适配器
                        SimpleAdapter adapter=new SimpleAdapter(logisticsActivity.this,list,R.layout.item_logistics,
                                new String[]{"trackNo","orderId","carNo","driverName",
                                        "driverTel","quantity","imaterialName","idCard"},
                                new int[]{R.id.tv_diaodu,R.id.tv_produnct,R.id.tv_chepaihao,R.id.tv_Name,
                                          R.id.tv_phone,R.id.tv_shulian,R.id.tv_productName,R.id.tv_Idcard});
                        lv_logistics.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(logisticsActivity.this, "该订单暂时没有物流信息", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("物流信息的返回值", ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                Log.d("物流信息的返回值", "finish");

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_child_title_bar_back:
                logisticsActivity.this.finish();
                break;
        }
    }
}
