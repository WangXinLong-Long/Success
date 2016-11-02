package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.OrderDetails;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.OrderDetailsListViewAdapter;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.CreateLogistics;
import com.silianchuangye.sumao.success.model.OrderDeatilsModel;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class OrderDetailsHaveBeenPaid extends Activity implements View.OnClickListener {
    CustomListView order_details_listView;
    private OrderDetailsListViewAdapter adapter;
    private List<OrderDeatilsModel> list=new ArrayList<OrderDeatilsModel>();
    private OrderDeatilsModel orderDeatilsModel;
    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;
    private Button bt_copy;
    private TextView tv_order_number,univalent2,number2,enterprise2,totalMoney2,warehouse2,tv_type;
    String str,Id;
    private Button bottom_money1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details_have_been_paid_activity);
        Id=getIntent().getStringExtra("ID");
        bt_copy= (Button) findViewById(R.id.bt_copy);
        tv_order_number= (TextView) findViewById(R.id.tv_order_number);
        order_details_listView = ((CustomListView) findViewById(R.id.order_details_listView));
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
        iv_child_title_bar_back.setOnClickListener(this);
        tv_child_title_bar_title.setText("订单详情");
        univalent2= (TextView) findViewById(R.id.univalent2);
        number2= (TextView) findViewById(R.id.number2);
        enterprise2= (TextView) findViewById(R.id.enterprise2);
        totalMoney2= (TextView) findViewById(R.id.totalMoney2);
        warehouse2= (TextView) findViewById(R.id.warehouse2);
        bottom_money1= (Button) findViewById(R.id.bottom_money1);
        tv_type= (TextView) findViewById(R.id.tv_type);
        bottom_money1.setOnClickListener(this);
//        initdata();
        new Thread(){
            @Override
            public void run() {
                super.run();
                sendMy();
            }
        }.start();

    }
//    private void initdata() {
//
//        list = new ArrayList<>();
//        for (int i = 0; i < 15; i++) {
//            orderDeatilsModel = new OrderDeatilsModel();
//            orderDeatilsModel.setWarehouse("公司"+i);
//            orderDeatilsModel.setClassification("分类"+"LLDPE"+i);
//            orderDeatilsModel.setDeliveryTime("交货时间"+i);
//            orderDeatilsModel.setDistributionMode("配送方式："+i);
//            orderDeatilsModel.setNumber(i);
//            orderDeatilsModel.setProductUnitPrice("产品单价"+i);
//            orderDeatilsModel.setTitle("标题"+i);
//            orderDeatilsModel.setTotalProductPrice("总价"+i);
//            list.add(orderDeatilsModel);
//        }
//        adapter = new OrderDetailsListViewAdapter(this, list);
//
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_child_title_bar_back:
                finish();
                break;
            case R.id.bt_copy:
                ClipboardManager copy = (ClipboardManager) OrderDetailsHaveBeenPaid.this
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(tv_order_number.getText().toString());
                break;
            case R.id.bottom_money1:
                Intent intnet=new Intent(this, CreateLogistics.class);
                startActivity(intnet);
                break;
            default:
                break;
        }
    }
    private void sendMy(){
        RequestParams params=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/orderDetail");
        params.addParameter("orderId",Id);
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique123 = sp.getString("unique", "");
        Log.e("TAG","parames======"+params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG","result----"+result);
                try {

                    JSONObject job=new JSONObject(result);
                        String str=job.getString("order");
                        JSONObject job2=new JSONObject(str);
                        String price=job2.getString("price");
                        Log.e("TAG","price--"+price);
                        totalMoney2.setText(price);
                        String type=job2.getString("type");//类型
                        String type1=getType(type);
                        Log.e("TAG","类型-----"+type1);
                         tv_type.setText(type1);
                        univalent2.setText(type1);
                        String shippingGroupState=job2.getString("shippingGroupState");
                        String state = job2.getString("state");//状态
                        Log.e("TAG","状态--"+state);
                        String state1=getState(state,type,shippingGroupState);
                        number2.setText(state1);
//                        String owner = job2.getString("owner");//采购员
                        String orderId = job2.getString("orderId");//订单编号
                        tv_order_number.setText(orderId);
                    Log.e("TAG","订单编号========"+orderId);
                    Long submittedDate=job2.getLong("date");
                    String now = new SimpleDateFormat("yyyy-MM-dd").format(submittedDate);
                    enterprise2.setText(now);
                    Log.e("TAG","日期===="+now);
                        String cl = (String) job2.getString("cl");
                        JSONArray jay=new JSONArray(cl);
                        for(int i=0;i<jay.length();i++) {
                            orderDeatilsModel = new OrderDeatilsModel();
                            JSONObject j = (JSONObject) jay.get(i);
                            String cl_amount = "";


                            JSONArray j1 = new JSONArray(cl);
                            for (int k = 0; k < j1.length(); k++) {
                                JSONObject job1 = (JSONObject) j1.get(k);
                                String cl_mingcheng = job1.getString("cl_mingcheng");//产品名称
                                Log.e("TAG","名称----"+cl_mingcheng);
                                String fenlei = job1.getString("cl_fenlei");//分类
                                Log.e("TAG","分类----"+fenlei);

                                cl_amount = job1.getString("cl_amount");//金额
                                Log.e("TAG","总额----"+cl_amount);
                                String cl_cangku=job1.getString("cl_cangku");//仓库
                                Log.e("TAG","仓库----"+cl_cangku);
                                String cl_gongsi=job1.getString("cl_gongsi");//公司
                                warehouse2.setText(cl_gongsi);
                                Log.e("TAG","公司==="+cl_gongsi);
                                String cl_jituan=job1.getString("cl_jituan");//生产企业
                                Log.e("TAG","生产企业----"+cl_jituan);
                                String cl_shuliang=job1.getString("cl_shuliang");//数量
                                Log.e("TAG","数量====="+cl_shuliang);
                                String cl_jine=job1.getString("cl_jine");
                                Log.e("TAG","单价---"+cl_jine);
                                orderDeatilsModel.setWarehouse(cl_cangku);
                                orderDeatilsModel.setClassification(fenlei);
                                orderDeatilsModel.setDeliveryTime(now);
                                orderDeatilsModel.setDistributionMode("配送方式："+i);
                                orderDeatilsModel.setNumber(Double.valueOf(cl_shuliang));
                                orderDeatilsModel.setProductUnitPrice(cl_jine);
                                orderDeatilsModel.setTitle(cl_mingcheng);
                                orderDeatilsModel.setTotalProductPrice(cl_amount);
                                list.add(orderDeatilsModel);
                                Log.e("TAG","list.size()==="+list.size());
                            }
                            Log.e("TAG","list-----"+list.size());
                            adapter = new OrderDetailsListViewAdapter(OrderDetailsHaveBeenPaid.this, list);
                            order_details_listView.setAdapter(adapter);
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
