package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.OrderDetails;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.OrderDetailsListViewAdapter;
import com.silianchuangye.sumao.success.adapter.SpotOrderAdapter;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrder;
import com.silianchuangye.sumao.success.model.OrderDeatilsModel;
import com.silianchuangye.sumao.success.model.SpotOrderModel;
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
 * Created by Administrator on 2016/7/7 0007.
 */
public class AlreadyPaidActivity extends Activity implements View.OnClickListener{
    private TextView tv_child_title_bar_title;
    private ListView spot_order_listView;
    //    SpotOrde模型
    private SpotOrderModel model, dada;
    //      创建SpotOrderModel对象集合
    List<SpotOrderModel> list=new ArrayList<SpotOrderModel>();
    //    适配器
    SpotOrderAdapter adapter;
    private RelativeLayout order_amount;
    private ImageView iv_child_title_bar_back;
    private Button Copy;
    private TextView the_order_number_number,the_order_price,type2,buyer2,state2,company2;
    String type,Id;//类型和ID-现货的
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_has_been_paid);
        type=getIntent().getStringExtra("type");
        Id=getIntent().getStringExtra("ID");
        Log.e("TAG","type已支付--"+type);
        Copy= (Button) findViewById(R.id.bt_copy);
        the_order_number_number= (TextView) findViewById(R.id.the_order_number_number);
        the_order_price= (TextView) findViewById(R.id.the_order_price);
        type2= (TextView) findViewById(R.id.type2);
        buyer2= (TextView) findViewById(R.id.buyer2);
        state2= (TextView) findViewById(R.id.state2);
        company2= (TextView) findViewById(R.id.company2);

        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("已支付订单");
        spot_order_listView = ((CustomListView) findViewById(R.id.spot_order_listView));
        order_amount = ((RelativeLayout) findViewById(R.id.order_amount));
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
        iv_child_title_bar_back.setOnClickListener(this);
        order_amount.setOnClickListener(this);
        /**
         * 复制按钮
         */
        Copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager copy = (ClipboardManager) AlreadyPaidActivity.this
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(the_order_number_number.getText().toString());

            }
        });
        new Thread(){
            @Override
            public void run() {
                super.run();
                sendMy();
            }
        }.start();
//        list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            model = new SpotOrderModel();
//            model.setUnivalent(i * 100);
//            model.setNumber(i + 1);
//            model.setEnterprise("中石油");
//            model.setTotalMoney((i*100)*(i+1));
//            model.setWarehouse("讯帮"+i+"库");
//            model.setCompany(i+"联创业集团");
//            model.setProductModel("中国石油"+i+"型产品");
//            list.add(model);
//        }
//        adapter = new SpotOrderAdapter(this,list);
//        spot_order_listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.order_amount:
                Intent intent = new Intent();
                intent.putExtra("ID",Id);
                intent.setClass(this,OrderDetailsHaveBeenPaid.class);
                startActivity(intent);
                break;
            case R.id.iv_child_title_bar_back:
                finish();
                break;
            default:
                break;
        }
    }
    private void sendMy(){
        RequestParams params=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/myOrders");
        params.addParameter("pageNum","1");
        params.addParameter("searchOrderId",Id);
        params.addParameter("searchOrderType",type);
        params.addParameter("searchOrderState","QUOTED");
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique123 = sp.getString("unique", "");
        Log.e("TAG","parames======"+params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG","result----"+result);
                try {
                    JSONObject job=new JSONObject(result);
                    String info=job.getString("info");
                    if(info.equals("fail")){
                        Toast.makeText(AlreadyPaidActivity.this,"获取数据失败",Toast.LENGTH_SHORT).show();
                    }else{
                        String str=job.getString("order");
                        JSONArray jay=new JSONArray(str);
                        for(int i=0;i<jay.length();i++) {
                            model = new SpotOrderModel();
                            JSONObject j = (JSONObject) jay.get(i);
                            String cl = (String) j.getString("cl");
                            String type=j.getString("type");//类型
                            String type1=getType(type);
                            Log.e("TAG","类型-----"+type1);
                            type2.setText(type1);
                            String shippingGroupState=j.getString("shippingGroupState");
                            String state = j.getString("state");//状态
                            Log.e("TAG","状态--"+state);
                            String state1=getState(state,type,shippingGroupState);
                            state2.setText(state1);
                            String owner = j.getString("owner");//采购员
                            String orderId  = j.getString("orderId");//订单编号
                            the_order_number_number.setText(orderId);
                            String cl_amount = "";

                            Long submittedDate=j.getLong("submittedDate");
                            String now = new SimpleDateFormat("yyyy-MM-dd").format(submittedDate);
//                            enterprise2.setText(now);
                            JSONArray j1 = new JSONArray(cl);
                            for (int k = 0; k < j1.length(); k++) {
                                JSONObject job1 = (JSONObject) j1.get(k);
                                String cl_mingcheng = job1.getString("cl_mingcheng");//产品名称
                                Log.e("TAG","名称");
                                String fenlei = job1.getString("cl_fenlei");//分类
                                Log.e("TAG","分类");
                                cl_amount = job1.getString("cl_amount");//金额
                                Log.e("TAG","金额二");
                                the_order_price.setText(cl_amount);
                                String cl_cangku=job1.getString("cl_cangku");//仓库
                                String cl_gongsi=job1.getString("cl_gongsi");//公司
                                company2.setText(cl_gongsi);
                                String cl_jituan=job1.getString("cl_jituan");//生产企业
                                Log.e("TAG","生产企业");
                                String cl_shuliang=job1.getString("cl_shuliang");//数量
                                Log.e("TAG","数量");
                                String cl_jine=job1.getString("cl_jine");
                                Log.e("TAG","金额");
                                model.setWarehouse(cl_cangku);
                                model.setWarehouse(cl_cangku);
                                model.setTotalMoney(Double.valueOf(cl_amount));
                                model.setCompany(cl_gongsi);
                                model.setNumber(Double.valueOf(cl_shuliang));
                                model.setUnivalent(Double.valueOf(cl_jine));
                                model.setProductModel(cl_mingcheng);
                                model.setEnterprise(cl_jituan);
                                list.add(model);
                                Log.e("TAG","list.size()==="+list.size());
                            }
                            Log.e("TAG","list-----"+list.size());
                            adapter = new SpotOrderAdapter(AlreadyPaidActivity.this,list);
                            spot_order_listView.setAdapter(adapter);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.e("TAG","失败呢ex------"+ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private String getType(String type){
        String str="";
        if(type.equals("fixedPricingOrder")){
            str="现货";
        }else if(type.equals("openAuctionOrder")){
            str="公开竞拍";
        }else if(type.equals("sealedAuctionOrder")){
            str="密封竞拍";
        }else if(type.equals("integratePurchaseOrder")){
            str="团购";
        }else if(type.equals("forwardPricingOrder")){
            str="预售";
        }else if(type.equals("offlineOrder")){
            str="客服订单";
        }
        return str;
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
