package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.silianchuangye.sumao.success.HX.Constant;
import com.silianchuangye.sumao.success.HX.ui.LoginActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.adapter.SpotOrderAdapter;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.dialog.Error_Dialog;
import com.silianchuangye.sumao.success.dialog.Ok_Dialog;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;

import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.PayMentsOrderMVP.bean.BankListBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.bean.ContractInformation;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.presenter.SpotOrderPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.informationView.ContractInformationActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.informationView.InvoiceInformationActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.informationView.PaymentInformationActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.bean.PreSellAllBean;
import com.silianchuangye.sumao.success.model.SpotOrderModel;
import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by Administrator on 2016/5/3 0003.
 */
public class SpotOrder extends Activity implements View.OnClickListener, ISpotOrderView {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title;
    RelativeLayout spot_order_title;
    RelativeLayout order_amount;
    CustomListView spot_order_listView;
    /*定义一个倒计时的内部类*/
    private MyCount mc;
    //    SpotOrde模型
    private SpotOrderModel model, dada;
    //      创建SpotOrderModel对象集合
    //List<SpotOrderModel> list;
    //    适配器
    SpotOrderAdapter spotOrderAdapter;
    private Button bt_zhifu;
    private TextView count_down_tv;
    private EditText et;
    private ListView lv;
    private PopupWindowAdaptrer adapter1;
    PopupWindow popupWindow;
    Context context;
    private Button bt_copy;
    private TextView the_order_number_number;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private AlertDialog.Builder alert;
    private TextView tv_order_number1;
    String type1, Id, type;//类型和ID-现货的
    private TextView the_order_price, type2, buyer2, state2, company2;
    private TextView bottom_money2;
    private PopupWindowAdaptrer adapterBank;
    String cl_id;
    String submittedDate;
    private List<String> list_order = new ArrayList<String>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;
    private SpotOrderPresenter spotOrderPresenter;
    //private PreSellAllBean.OrderBean orderBean;
    private LinearLayout bottom_money;
    private TextView tv;
    private ArrayList<OpenAuction> list_pop;
    private String count_price;
    private ImageView order_status_order_details;
    private TextView tv_xianqing_type;
    private RelativeLayout paymentInformation;
    private RelativeLayout invoiceInformation;
    private RelativeLayout contractInformation;
    private RelativeLayout logisticsInformation;
    private String zhifu_unique;
    private String OrderId;
    private String state;
    private String price;
    private String owner;
    private List<Map<String,Object>> list;
    private String cl_fangshi;
    private Button services;
    private String paymentEndDate;
    private TextView tv_Count;
    private TextView tv_money;
    private String servicesFree;
    String result1="";
   // private CustomListView typespot_order_listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_to_be_paid);
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        zhifu_unique = sp.getString("unique", "");
        LogUtils.log("zhifu_unique-->"+zhifu_unique);

        LogUtils.log("onCreate--->");
        spotOrderPresenter = new SpotOrderPresenter(this);
        initView();
        OrderId=getIntent().getStringExtra("id");
        //orderBean = (PreSellAllBean.OrderBean) getIntent().getSerializableExtra("OrderBean");
        getOrderInfo();

//        IntentFilter filter = new IntentFilter();
//        filter.addAction("finishsportorder");
//        registerReceiver(broadcastReceiver, filter);

        bt_zhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Popupwindow();
            }
        });
    }


    //    初始化控件
    private void initView() {
        //typespot_order_listView=(CustomListView)findViewById(R.id.spot_order_listView);
        bottom_money = ((LinearLayout) findViewById(R.id.bottom_money));
        bt_copy = (Button) findViewById(R.id.bt_copy);
        the_order_number_number = (TextView) findViewById(R.id.the_order_number_number);
        bottom_money2 = (TextView) findViewById(R.id.bottom_money2);
        the_order_price = (TextView) findViewById(R.id.the_order_price);
        type2 = (TextView) findViewById(R.id.type2);
        buyer2 = (TextView) findViewById(R.id.buyer2);
        state2 = (TextView) findViewById(R.id.state2);
        company2 = (TextView) findViewById(R.id.company2);
        bt_zhifu = (Button) findViewById(R.id.bt_Zhifu);
        services= (Button) findViewById(R.id.services);
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHX = new Intent();
                intentHX.setClass(SpotOrder.this, LoginActivity.class);
                intentHX.putExtra(Constant.MESSAGE_TO_INTENT_EXTRA, Constant.MESSAGE_TO_DEFAULT);
//                传入卖家id
               // intentHX.putExtra(Constant.IM_SERVICE_NUMBER, list.get(0).get("cl_gongsi").toString());
                intentHX.putExtra(Constant.IM_SERVICE_NUMBER, list.get(0).get("cl_qiyeonlyId").toString());
                Log.d("公司的唯一标识",list.get(0).get("cl_qiyeonlyId").toString()+"是公司id");
                intentHX.putExtra("gongsiName",list.get(0).get("cl_gongsi").toString());
                startActivity(intentHX);
            }
        });
        bt_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ClipboardManager copy = (ClipboardManager) SpotOrder.this
//                        .getSystemService(Context.CLIPBOARD_SERVICE);
//                copy.setText(the_order_number_number.getText().toString());
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
// 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", the_order_number_number.getText().toString());
// 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);

            }
        });
        tv_order_number1 = (TextView) findViewById(R.id.the_order_number_number);

        spot_order_title = ((RelativeLayout) findViewById(R.id.spot_order_title));
        order_amount = ((RelativeLayout) findViewById(R.id.order_amount));
        order_amount.setOnClickListener(this);
        spot_order_title.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
//        标题
        tv_title_bar_title = ((TextView) findViewById(R.id.tv_title_bar_title));
        spot_order_listView = ((CustomListView) findViewById(R.id.spot_order_listView));

        iv_title_bar_logo.setVisibility(View.INVISIBLE);
        iv_title_bar_service.setVisibility(View.INVISIBLE);
        sv_title_bar_serachView.setVisibility(View.INVISIBLE);

        iv_title_bar_back.setOnClickListener(this);
        iv_title_bar_search.setOnClickListener(this);
        tv_title_bar_title.setTextColor(Color.WHITE);
//      倒计时
        count_down_tv = ((TextView) findViewById(R.id.count_down_tv));
//        显示支付状态的图标
        order_status_order_details = ((ImageView) findViewById(R.id.order_status_order_details));
//        什么类型的商品
        tv_xianqing_type = ((TextView) findViewById(R.id.tv_xianqing_type));
//        付款信息
        paymentInformation = ((RelativeLayout) findViewById(R.id.bottom1));
        paymentInformation.setOnClickListener(this);
//        发票信息
        invoiceInformation = ((RelativeLayout) findViewById(R.id.bottom2));
        invoiceInformation.setOnClickListener(this);
//        合同信息
        contractInformation = ((RelativeLayout) findViewById(R.id.bottom3));
        contractInformation.setOnClickListener(this);
//        物流信息
        logisticsInformation = ((RelativeLayout) findViewById(R.id.bottom4));
        logisticsInformation.setOnClickListener(this);
    }
    public void getOrderInfo(){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/orderDetail";
        RequestParams requestParams=new RequestParams(url);
        requestParams.addParameter("_dynSessConf",zhifu_unique);
        requestParams.addParameter("orderId",OrderId);
        Log.d("订单详情的rp",requestParams+"");
        list=new ArrayList<>();
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(" ",result);
//                Gson gson=new Gson();
//                orderBean=gson.fromJson(result,PreSellAllBean.OrderBean.class);
//                setValue(orderBean);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    String order=jsonObject.getString("order");
                    JSONObject object=new JSONObject(order);
                    if (result.contains("shippingGroupState")) {
                        String shippingGroupState = object.getString("shippingGroupState");
                    }
                    price=object.getString("price");
                    String contract=object.getString("contract");
                    submittedDate=object.getString("submittedDate");
                    state=object.getString("state");
                    owner=object.getString("owner");
                    type=object.getString("type");
                    cl_fangshi=object.getString("cl_fangshi");
                    //orderId=object.getString("orderId");
                    String cl=object.getString("cl");
                    JSONArray jsonarray=new JSONArray(cl);
                    for (int i=0;i<jsonarray.length();i++){
                        JSONObject jsonobj=jsonarray.getJSONObject(i);
                        String cl_mingcheng=jsonobj.getString("cl_mingcheng");
                        String cl_gongsiId=jsonobj.getString("cl_gongsiId");
                        String deliveryStartDate=jsonobj.getString("deliveryStartDate");
                        String cl_gongsi=jsonobj.getString("cl_gongsi");
                        String deliveryEndDate=jsonobj.getString("deliveryEndDate");
                        String cl_jine=jsonobj.getString("cl_jine");
                        String cl_id=jsonobj.getString("cl_id");
                        String cl_amount=jsonobj.getString("cl_amount");
                        String cl_qiyeonlyId=jsonobj.getString("cl_qiyeonlyId");
                        String cl_fenlei=jsonobj.getString("cl_fenlei");
                        String cl_jituan=jsonobj.getString("cl_jituan");
                        String cl_cangku=jsonobj.getString("cl_cangku");
                        String cl_shuliang=jsonobj.getString("cl_shuliang");
                        if (jsonobj.toString().contains("paymentEndDate")){
                            paymentEndDate =jsonobj.getString("paymentEndDate");
                        }else{
                            paymentEndDate ="abc";
                        }
                        Map<String,Object> map=new Hashtable<String, Object>();
                        map.put("cl_mingcheng",cl_mingcheng);
                        map.put("cl_gongsiId",cl_gongsiId);
                        map.put("deliveryStartDate",deliveryStartDate);
                        map.put("cl_gongsi",cl_gongsi);
                        map.put("deliveryEndDate",deliveryEndDate);
                        map.put("cl_jine",cl_jine);
                        map.put("cl_id",cl_id);
                        map.put("cl_amount",cl_amount);
                        map.put("cl_qiyeonlyId",cl_qiyeonlyId);
                        map.put("cl_fenlei",cl_fenlei);
                        map.put("cl_jituan",cl_jituan);
                        map.put("cl_cangku",cl_cangku);

                        map.put("paymentEndDate",paymentEndDate);
                        map.put("cl_shuliang",cl_shuliang);
                        list.add(map);
                    }
                    Log.d("物流方式的值",cl_fangshi);
                    spotOrderAdapter = new SpotOrderAdapter(SpotOrder.this,list,cl_fangshi);

                    spot_order_listView.setAdapter(spotOrderAdapter);
                    spotOrderAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setValue();
                typeinfo();

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

    //    设置值
    public void setValue(/*PreSellAllBean.OrderBean orderBean*/) {

        //spotOrderAdapter.addData(orderBean);
       // String state = orderBean.getState();
        Log.d("state的值",state);
//        除了待支付，其他的都不显示倒计时
        if (state.equals("SUBMITTED") ||state.equals("PENDING_APPROVAL") || state.equals("APPROVED")||state.equals("FAILED_APPROVAL")) {//待支付
            tv_title_bar_title.setText("待支付");
            state2.setText("待支付");
            count_down_tv.setVisibility(View.VISIBLE);
            bottom_money.setVisibility(View.VISIBLE);
//            List<PreSellAllBean.OrderBean.ClBean> cl = orderBean.getCl();
//            double total = 0;
//            for (int i = 0; i < cl.size(); i++) {
//                double amount = Double.parseDouble(cl.get(i).getCl_amount());
//                total += amount;
//            }


        } else if (state.equals("PAYMENT_COMPLETION")) {//货款已支付
            tv_title_bar_title.setText("货款已支付");
            state2.setText("货款已支付");
            count_down_tv.setVisibility(View.GONE);
        } else if (state.equals("DEPOSIT_ERROR")) {//保证金解冻失败
            count_down_tv.setVisibility(View.GONE);
            tv_title_bar_title.setText("保证金解冻失败");
            state2.setText("保证金解冻失败");
        } else if (state.equals("DEPOSIT_SUCCESS")) {//保证金解冻成功
            count_down_tv.setVisibility(View.GONE);
            tv_title_bar_title.setText("保证金解冻成功");
            state2.setText("保证金解冻成功");
        } else if (state.equals("QUOTED")) {//已支付
            tv_title_bar_title.setText("已支付");
            state2.setText("已支付");
            count_down_tv.setVisibility(View.GONE);
        } else if (state.equals("REMOVED")) {//已取消
            tv_title_bar_title.setText("已取消");
            state2.setText("已取消");
            count_down_tv.setVisibility(View.GONE);
        } else if (state.equals("CHANGED")) {//已变更
            tv_title_bar_title.setText("已变更");
            state2.setText("已变更");
            count_down_tv.setVisibility(View.GONE);
        }
//        订单号
        the_order_number_number.setText(OrderId);

        /**
         *待支付订单result
         * 根据系统下单时间，获取剩余结束时间
         */


//        订单类型
        //String type = orderBean.getType();

//  采购员
        buyer2.setText(owner);
//        状态
        company2.setText(list.get(0).get("cl_gongsi").toString());
        if (state.equals("SUBMITTED")) {//待支付
            order_status_order_details.setImageResource(R.mipmap.to_pay_the_picture);
        } else if (state.equals("PAYMENT_COMPLETION")) {//货款已支付
            order_status_order_details.setImageResource(R.mipmap.completed_order);
        } else if (state.equals("DEPOSIT_ERROR")) {//保证金解冻失败
            order_status_order_details.setImageResource(R.mipmap.completed_order);
        } else if (state.equals("DEPOSIT_SUCCESS")) {//保证金解冻成功
            order_status_order_details.setImageResource(R.mipmap.completed_order);
        } else if (state.equals("QUOTED")) {//已支付
            order_status_order_details.setImageResource(R.mipmap.completed_order);
        } else if (state.equals("REMOVED")) {//已取消
            order_status_order_details.setImageResource(R.mipmap.no_order_form);
        } else if (state.equals("CHANGED")) {//已变更
            order_status_order_details.setImageResource(R.mipmap.no_order_form);
        }
    }

    public void typeinfo(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long countDownTime = 0;
        BigDecimal b1 = new BigDecimal(String.valueOf(price));
        double f1 = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        bottom_money2.setText(f1+"元");
        if (type.equals("fixedPricingOrder")) {//现货
            type2.setText("现货");
            tv_xianqing_type.setText("现货");

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long time=new Long((Long.parseLong(submittedDate) + 1000 * 3600));
            Log.d("time的值",time+"");
            String d = format.format(time);

            try {
                Date date1 = format.parse(d);
                Log.d("支付截止时间的值",date1+"");
                Date date_new=new Date();
                long countDown =date1.getTime()  - date_new.getTime()+4000;
                countDownTime = countDown > 0 ? countDown : 0;
            }catch (Exception e){
                e.printStackTrace();
            }

        } else if (type.equals("openAuctionOrder")) {//公开竞拍
            type2.setText("公开竞拍");
            tv_xianqing_type.setText("公开竞拍");
            String committime= new SimpleDateFormat("yyyy-MM-dd").format(Double.parseDouble(submittedDate));
            Log.d("comittime",committime);
            //把日期变为String类型
            try {
                String data=committime+" 17:00:00";//与提交日期的当天5点
                Date date111=new Date();           //当前时间
                SimpleDateFormat simpleFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

                Date date = simpleFormat.parse(data);
                Log.d("日期的值",date.toString());
                long createdate=date.getTime();//提交日期当天5点的豪秒值
                Log.d("日期转为秒的值",createdate+"");

                if (createdate-Long.parseLong(submittedDate)>=0){
                    //倒计时到5点
                    Log.d("今天结束","今天结束");
                    if (createdate-System.currentTimeMillis()>=0){

                        countDownTime=date.getTime()-date111.getTime();
                    }
                    Log.d("if countDownTime的值",countDownTime+"");
                }else{
                    Log.d("明天结束","明天结束");
                    //倒计时到明天5点
                    if (createdate+(1000*60*60*24)-System.currentTimeMillis()>=0){
                        //把毫秒转换成日期
                        String data123=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdate+1000*60*60*24);
                        Date date456 = simpleFormat.parse(data123);
                        countDownTime=date456.getTime()-date111.getTime();
                    }
                    Log.d("else countDownTime的值",countDownTime+"");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (type.equals("sealedAuctionOrder")) {//密封竞拍
            type2.setText("密封竞拍");
            tv_xianqing_type.setText("密封竞拍");
            String committime= new SimpleDateFormat("yyyy-MM-dd").format(Double.parseDouble(submittedDate));
            Log.d("comittime",committime);
            //把日期变为String类型
            try {
                String data=committime+" 17:00:00";//与提交日期的当天5点
                Date date111=new Date();           //当前时间
                SimpleDateFormat simpleFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

                Date date = simpleFormat.parse(data);
                Log.d("日期的值",date.toString());
                long createdate=date.getTime();//提交日期当天5点的豪秒值
                Log.d("日期转为秒的值",createdate+"");

                if (createdate-Long.parseLong(submittedDate)>=0){
                    //倒计时到5点
                    Log.d("今天结束","今天结束");
                    if (createdate-System.currentTimeMillis()>=0){

                        countDownTime=date.getTime()-date111.getTime();
                    }
                    Log.d("if countDownTime的值",countDownTime+"");
                }else{
                    Log.d("明天结束","明天结束");
                    //倒计时到明天5点
                    if (createdate+(1000*60*60*24)-System.currentTimeMillis()>=0){
                        //把毫秒转换成日期
                        String data123=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdate+1000*60*60*24);
                        Date date456 = simpleFormat.parse(data123);
                        countDownTime=date456.getTime()-date111.getTime();
                    }
                    Log.d("else countDownTime的值",countDownTime+"");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (type.equals("integratePurchaseOrder")) {//团购
            type2.setText("团购");
            tv_xianqing_type.setText("团购");
            String committime= new SimpleDateFormat("yyyy-MM-dd").format(Double.parseDouble(submittedDate));
            Log.d("comittime",committime);
            //把日期变为String类型
            try {
                String data=committime+" 17:00:00";//与提交日期的当天5点
                Date date111=new Date();           //当前时间
                SimpleDateFormat simpleFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

                Date date = simpleFormat.parse(data);
                Log.d("日期的值",date.toString());
                long createdate=date.getTime();//提交日期当天5点的豪秒值
                Log.d("日期转为秒的值",createdate+"");

                if (createdate-Long.parseLong(submittedDate)>=0){
                    //倒计时到5点
                  Log.d("今天结束","今天结束");
                    if (createdate-System.currentTimeMillis()>=0){

                        countDownTime=date.getTime()-date111.getTime();
                    }
                    Log.d("if countDownTime的值",countDownTime+"");
                }else{
                    Log.d("明天结束","明天结束");
                    //倒计时到明天5点
                    if (createdate+(1000*60*60*24)-System.currentTimeMillis()>=0){
                        //把毫秒转换成日期
                        String data123=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdate+1000*60*60*24);
                        Date date456 = simpleFormat.parse(data123);
                        countDownTime=date456.getTime()-date111.getTime();
                    }
                    Log.d("else countDownTime的值",countDownTime+"");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type.equals("forwardPricingOrder")) {//预售
            type2.setText("预售");
            tv_xianqing_type.setText("预售");
            //countDownTime = Long.parseLong(list.get(0).get("deliveryEndDate").toString()) - Long.parseLong(submittedDate);
            //countDownTime =(Long.parseLong(list.get(0).get("paymentEndDate").toString()) - System.currentTimeMillis())/1000;
            //String timedaizhifu =formatTime(list.get(0).get("paymentEndDate").toString());
            Log.d("countDownTime",countDownTime+"");
            Log.d("预售截止时间",Long.parseLong(list.get(0).get("deliveryEndDate").toString())+"");


            //test
            //根据时间戳得到日期
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long time=new Long(list.get(0).get("paymentEndDate").toString());
            String d = format.format(time);
            try {
                Date date1=format.parse(d);//接口返回
                Date date=new Date();//当前时间
                Long ms=date1.getTime()-date.getTime();
                countDownTime=ms;
            } catch (ParseException e) {
                e.printStackTrace();
            }


        } else if (type.equals("scheduleOrder")) {//计划订单
            type2.setText("计划订单");
            tv_xianqing_type.setText("计划订单");
            String committime= new SimpleDateFormat("yyyy-MM-dd").format(Double.parseDouble(submittedDate));
            Log.d("comittime",committime);
            //把日期变为String类型
            try {
                String data=committime+" 17:00:00";//与提交日期的当天5点
                Date date111=new Date();           //当前时间
                SimpleDateFormat simpleFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

                Date date = simpleFormat.parse(data);
                Log.d("日期的值",date.toString());
                long createdate=date.getTime();//提交日期当天5点的豪秒值
                Log.d("日期转为秒的值",createdate+"");

                if (createdate-Long.parseLong(submittedDate)>=0){
                    //倒计时到5点
                    Log.d("今天结束","今天结束");
                    if (createdate-System.currentTimeMillis()>=0){

                        countDownTime=date.getTime()-date111.getTime();
                    }
                    Log.d("if countDownTime的值",countDownTime+"");
                }else{
                    Log.d("明天结束","明天结束");
                    //倒计时到明天5点
                    if (createdate+(1000*60*60*24)-System.currentTimeMillis()>=0){
                        //把毫秒转换成日期
                        String data123=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdate+1000*60*60*24);
                        Date date456 = simpleFormat.parse(data123);
                        countDownTime=date456.getTime()-date111.getTime();
                    }
                    Log.d("else countDownTime的值",countDownTime+"");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mc = new MyCount(countDownTime, 1000);
        mc.start();
    }






    @Override
    public void onClick(View v) {
        //String orderId = orderBean.getOrderId();
        String _dynSessConf = zhifu_unique;
        Intent intent = new Intent();
        switch (v.getId()) {

            case R.id.iv_title_bar_back:
                finish();
                break;
            case R.id.iv_title_bar_search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();

                break;
//            待支付方详情
            case R.id.order_amount:
                LogUtils.log("点击进入详情");
                break;
//            付款信息
            case R.id.bottom1:

                spotOrderPresenter.getPaymentInformationResult(OrderId, _dynSessConf);

                break;
//            发票信息
            case R.id.bottom2:
                spotOrderPresenter.getInvoiceInformationResult(OrderId, _dynSessConf);
//                Intent intent_fapiao = new Intent();
//                intent_fapiao.setClass(this, InvoiceInformationActivity.class);
//                intent_fapiao.putExtra("id",orderId);
//                intent_fapiao.putExtra("unique",_dynSessConf);
//                startActivity(intent_fapiao);
                break;
//            合同信息
            case R.id.bottom3:
                spotOrderPresenter.getContractInformationResult(OrderId, _dynSessConf);

                break;
//            物流信息
            case R.id.bottom4:
//                intent.setClass(this, LogisticsInformationActivity.class);
//                startActivity(intent);
                Intent intent1=new Intent(SpotOrder.this,logisticsActivity.class);
                intent1.putExtra("orderId",OrderId);
                startActivity(intent1);


                break;

        }
    }


    /*定义一个倒计时的内部类*/
    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {

            count_down_tv.setText("支付已截止");
            count_down_tv.setTextColor(Color.RED);
            bottom_money.setVisibility(View.INVISIBLE);


        }

        @Override
        public void onTick(long millisUntilFinished) {
//            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
//            String time = format.format(millisUntilFinished - TimeZone.getDefault().getRawOffset());
            String time = formatTime(millisUntilFinished);
            count_down_tv.setText("剩余支付时间：" + time);
//            if (time==2000+""){
//                getOrderInfo();
//            }
        }
    }
    //    转化为倒计时需要的时间
    public String formatTime(Long ms) {

        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;


        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        if (hour > 0) {

            if (hour>9){
                sb.append(hour + ":");
            }else if (hour<=9){
                sb.append("0"+hour+":");
            }
        }else if (hour==0){
            sb.append("00:");
        }
        if (minute > 0) {

            if (minute>9){
                sb.append(minute + ":");
            }else {
                sb.append("0"+minute+":");
            }
        }else if (minute==0){
            sb.append("00:");
        }
        if (second > 0) {
            if (second<=9){
                sb.append("0"+second);
            }else {
                sb.append(second);
            }
        }else if (second==0){
            sb.append("00");
        }

        return sb.toString();
    }

    //设置背景透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    public void Popupwindow() {
        backgroundAlpha(0.5f);
        getinfo_Bank();

    }

    //获取银行列表
    public void getinfo_Bank() {
       // String orderId = orderBean.getOrderId();
       // list_order.add(OrderId);
        String order = null;
        Loding.show(this, "加载中...", false, null);
        spotOrderPresenter.getBankListFromService(order, OrderId);
    }

    @Override
    public void setBankListResult(String result) {
        Loding.dis();

        if (result.contains("openedAccount\":\"true\"")) {

            View view = getLayoutInflater().inflate(R.layout.item_popupwindow_auction, null);
            popupWindow = new PopupWindow(findViewById(R.id.Layout_Button_Open),
                    ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
            popupWindow.setContentView(view);
            LinearLayout layout_one = (LinearLayout) view.findViewById(R.id.layout_one);
            LinearLayout layout_two = (LinearLayout) view.findViewById(R.id.layout_two);
            LinearLayout layout_three = (LinearLayout) view.findViewById(R.id.layout_three);
            layout_one.setVisibility(View.GONE);
            layout_two.setVisibility(View.GONE);
            layout_three.setVisibility(View.GONE);
            tv = (TextView) view.findViewById(R.id.tvPrice_popupwindow_auction);
            et = (EditText) view.findViewById(R.id.etZhifu_auction);
            lv = (ListView) view.findViewById(R.id.lv_popupwindow_auction);
            TextView count_Price = (TextView) view.findViewById(R.id.count_Price);
            // final List<OpenAuction> list_pop=new ArrayList<OpenAuction>();
            tv_Count= (TextView) view.findViewById(R.id.tv_Count);
            tv_money= (TextView) view.findViewById(R.id.tv_money);
            RelativeLayout layout_money= (RelativeLayout) view.findViewById(R.id.layout_money);
            Button bt = (Button) view.findViewById(R.id.btZhifu);
            bt.setText("立即支付");

            popupWindow.setTouchable(true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);

            if (result.contains("balance")) {
                list_pop = new ArrayList<OpenAuction>();
                Gson gson = new Gson();
                BankListBean bankListBean = gson.fromJson(result, BankListBean.class);
                List<BankListBean.BankResultBean> bankResults = bankListBean.getBankResult();
                count_price = bankListBean.getBalance();
//                JSONObject obj_Bank = new JSONObject(message);
                tv_Count.setText(count_price+"元");
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    servicesFree=jsonObject.getString("serviceFee");
                    if (servicesFree.equals("0")){
                        layout_money.setVisibility(View.GONE);
                    }
                    tv_money.setText(servicesFree+"元");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                count_Price.setText(Double.parseDouble(servicesFree)+Double.parseDouble(count_price)+"元");
                for (int i = 0; i < bankResults.size(); i++) {
                    if (bankResults.get(i).getOpenedAccount().equals("true")) {
                        if (bankResults.get(i).getBankName().equals("平安银行")) {
                            OpenAuction auction = new OpenAuction();
                            auction.iv_icon = R.mipmap.pingan;
                            auction.tv_Name = "平安银行";
                            auction.tv_money = bankResults.get(i).getBankBalance();
                            auction.message = 1 + "";
                            list_pop.add(auction);
                        }
                        if (bankResults.get(i).getBankName().equals("建设银行")) {
                            OpenAuction auction = new OpenAuction();
                            auction.iv_icon = R.mipmap.jianshe;
                            auction.tv_Name = "中国建设银行";
                            auction.tv_money = bankResults.get(i).getBankBalance();
                            auction.message = 3 + "";
                            list_pop.add(auction);
                        }
                        if (bankResults.get(i).getBankName().equals("昆仑银行")) {
                            OpenAuction auction = new OpenAuction();
                            auction.iv_icon = R.mipmap.kunlun;
                            auction.tv_Name = "昆仑银行";
                            auction.tv_money = bankResults.get(i).getBankBalance();
                            auction.message = 2 + "";
                            list_pop.add(auction);
                        }
                    }

                }

                adapter1 = new PopupWindowAdaptrer(list_pop, SpotOrder.this);
                lv.setAdapter(adapter1);

            } else {
                Toast.makeText(SpotOrder.this, "登录已超时,请重新登录!", Toast.LENGTH_SHORT).show();

            }
            popupWindow.showAtLocation(bottom_money, Gravity.BOTTOM, 0, 0);

            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    //popupWindow.dismiss();
                    backgroundAlpha(1f);
                }
            });
           // count_Price.setText(count_price+"元");
//        立即支付
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("list_pop的值",list_pop.size()+"");

                    for (int i = 0; i < list_pop.size(); i++) {
                        if (list_pop.get(i).Flag) {
                            final String position=list_pop.get(i).message;
                            popupWindow.dismiss();
                            Loding.show(SpotOrder.this, "货款支付中...", true, null);
                            //支付订单的接口
                            //getzhifu(position);
                            fukuan(list_pop.get(i).message);
//                            fukuan_update(list_pop.get(i).message);


                        } else if (i == list_pop.size() - 1) {
                            Toast.makeText(SpotOrder.this, "请选择的银行进行付款", Toast.LENGTH_SHORT).show();
                        }
                    }




                }
            });
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    if (list_pop.get(position).tv_Name.equals("昆仑银行")) {
//                        Toast.makeText(SpotOrder.this, "昆仑银行需要进行网关支付", Toast.LENGTH_SHORT).show();
//                    } else {
                        if (parent.getId() == lv.getId()) {
                            for (int i = 0; i < list_pop.size(); i++) {
                                Log.d("Listview的item", position + "");
                                if (i != position) {
                                    list_pop.get(i).Flag = false;
                                }
                            }
                            list_pop.get(position).Flag = !list_pop.get(position).Flag;
                            adapter1.notifyDataSetChanged();
                        }
                   // }

                }
            });
        } else {
            Toast.makeText(this, "该商品暂不支持银行付款", Toast.LENGTH_SHORT).show();
            backgroundAlpha(1f);
        }

    }

    @Override
    public void getPaymentResult(String result, String orderIds) {
        Log.e("支付结果的返回值",result);
        Loding.dis();
        if (!result.contains("paymentStatus")){
            Intent intent = new Intent(SpotOrder.this, Error_Dialog.class);
            intent.putExtra("number", OrderId);
            startActivity(intent);
        }else {
            try {
                JSONObject obj = new JSONObject(result);
                String state = obj.getString("paymentStatus");
                if (state.equals("0")) {
//                TODO !!!
                    //全部成功
                    Intent intent = new Intent(SpotOrder.this, Ok_Dialog.class);
                    if (type2.getText().toString().equals("预售")) {
                        intent.putExtra("type", "预售订单");
                    } else {
                        intent.putExtra("type", "现货订单");
                    }

                    intent.putExtra("number", OrderId);
                    startActivity(intent);
                } else if (state.equals("1")) {
                    //部分订单成功
                    Intent intent = new Intent(SpotOrder.this, Error_Dialog.class);
                    intent.putExtra("number", OrderId);
                    startActivity(intent);
                    //Toast.makeText(SpotOrder.this, "部分订单成功,到订单详情界面查看", Toast.LENGTH_SHORT).show();
                } else if (state.equals("2")) {
                    //全部失败
                    Intent intent = new Intent(SpotOrder.this, Error_Dialog.class);
                    intent.putExtra("number", OrderId);
                    startActivity(intent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    //    获取付款信息的内容
    @Override
    public void setPaymentInformationInActivity(String result) {

        Intent intent = new Intent();
        intent.setClass(this, PaymentInformationActivity.class);
        intent.putExtra("result",result);
        intent.putExtra("price",price);
        startActivity(intent);
    }

//    发票信息
    @Override
    public void setInvoiceInformationInActivity(String result) {
        Intent intent = new Intent();
        intent.setClass(this, InvoiceInformationActivity.class);
        intent.putExtra("result",result);
        startActivity(intent);
    }

//    合同信息
    @Override
    public void setContractInformationInActivity(String result) {

        if (!result.contains("cl_hturl")){
            Toast.makeText(SpotOrder.this, "暂时没有生成合同", Toast.LENGTH_SHORT).show();
        }else {
            Gson gson = new Gson();
            ContractInformation contractInformation = gson.fromJson(result, ContractInformation.class);
            Intent intent = new Intent();
            intent.setClass(this, ContractInformationActivity.class);
            intent.putExtra("contract",contractInformation.getCl_hturl());
            intent.putExtra("orderId",OrderId);
            startActivity(intent);
        }

    }

    public void fukuan(String bankId){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/payment/OrderPayment/orderPayment";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("orderIds",OrderId);
        rp.addParameter("platform",bankId);
        SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        String unique=sp.getString("unique","");
        rp.addParameter("_dynSessConf",unique);
//        rp.getConnectTimeout(1000 * 60);
//        rp.setMaxRetryCount(0);
        Log.d("请求的接口",rp+"");
        rp.setConnectTimeout(1000*600);
        x.http().get(rp, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.d("result的值",result);
                result1=result;

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
               //onCancelled();
                //onCancelled();
               // on
               // callback.cancel();
                Loding.dis();
                Toast.makeText(SpotOrder.this, "网络连接超时,请到订单详情页查看支付情况!", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(CancelledException cex) {


            }

            @Override
            public void onFinished() {
                Loding.dis();
                if (result1.contains("paymentStatus")) {
                    try {
                        JSONObject obj = new JSONObject(result1);
                        String state = obj.getString("paymentStatus");
                        Log.d("state的值", state);
                        if (state.equals("0")) {
                            Intent intent = new Intent(SpotOrder.this, Ok_Dialog.class);
                            if (type2.getText().toString().equals("预售")) {
                                Log.d("预售","预售");
                                intent.putExtra("type", "预售订单");
                            } else {
                                Log.d("现货","现货");
                                intent.putExtra("type", "现货订单");
                            }
                            intent.putExtra("number", OrderId);
                            startActivity(intent);
                            LogUtils.log("orderIds--->" + OrderId);
                        }else{
                            Intent intent = new Intent(SpotOrder.this, Error_Dialog.class);
                            intent.putExtra("number", OrderId);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    // Log.d("支付结果的返回值", "不等于空");
                    Intent intent = new Intent(SpotOrder.this, Error_Dialog.class);
                    intent.putExtra("number", OrderId);
                    startActivity(intent);
                }
            }
        });
        //callback.cancel();
//
    }

    public void fukuan_update(String BankId){
        String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/payment/OrderPayment/orderPayment";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("orderIds",OrderId);
        rp.addParameter("platform",BankId);
        SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        String unique=sp.getString("unique","");
        rp.addParameter("_dynSessConf",unique);
        Callback.Cancelable cancelable
                // 使用CacheCallback, xUtils将为该请求缓存数据.
                = x.http().get(rp, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("result的值",result);
                Loding.dis();
                if (result1.contains("paymentStatus")) {
                    try {
                        JSONObject obj = new JSONObject(result1);
                        String state = obj.getString("paymentStatus");
                        Log.d("state的值", state);
                        if (state.equals("0")) {
                            Intent intent = new Intent(SpotOrder.this, Ok_Dialog.class);
                            if (type2.getText().toString().equals("预售")) {
                                Log.d("预售","预售");
                                intent.putExtra("type", "预售订单");
                            } else {
                                Log.d("现货","现货");
                                intent.putExtra("type", "现货订单");
                            }
                            intent.putExtra("number", OrderId);
                            startActivity(intent);
                            LogUtils.log("orderIds--->" + OrderId);
                        }else{
                            Intent intent = new Intent(SpotOrder.this, Error_Dialog.class);
                            intent.putExtra("number", OrderId);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    // Log.d("支付结果的返回值", "不等于空");
                    Intent intent = new Intent(SpotOrder.this, Error_Dialog.class);
                    intent.putExtra("number", OrderId);
                    startActivity(intent);
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(SpotOrder.this, "网络异常,请去订单详情页查看该订单支付情况!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                Log.d("返回在那里",result);
                return true;
            }
        });


    }

    //    进行支付请求
    public void getzhifu(String bankId) {
//TODO
//        StringBuffer stringBuffer = new StringBuffer();
//        for (int i = 0; i < list_order.size(); i++) {
//            if (i == list_order.size() - 1) {
//                stringBuffer.append(list_order.get(i));
//            } else {
//                stringBuffer.append(list_order.get(i) + ",");
//            }
//        }
//        String a = stringBuffer.toString();
//
//        String orderIds = a;
        String platform = bankId;
        String _dynSessConf = zhifu_unique;
       // Looper.prepare();

      //  Looper.loop();
        spotOrderPresenter.getPaymentInfoFromService(OrderId, platform, _dynSessConf);
    }

    //    获取页面初始化数据


    //    页面销毁的广播
    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            unregisterReceiver(this);
            ((Activity) context).finish();
        }
    };


}
