package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Intent;
import android.app.Dialog;
import android.content.Context;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
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


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.adapter.SpotOrderAdapter;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.dialog.Error_Dialog;
import com.silianchuangye.sumao.success.dialog.Ok_Dialog;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.OrderDetails.AlreadyPaidActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.OrderDetails.OrderDetailsActivity;
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
import java.util.TimeZone;

/**
 * Created by Administrator on 2016/5/3 0003.
 */
public class SpotOrder extends Activity implements View.OnClickListener {
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
    List<SpotOrderModel> list;
    //    适配器
    SpotOrderAdapter adapter;
    private Button bt_zhifu;
    private TextView tv;
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
    String type,Id;//类型和ID-现货的
    private TextView the_order_price,type2,buyer2,state2,company2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_to_be_paid);
        type=getIntent().getStringExtra("type");
        Id=getIntent().getStringExtra("ID");
        Log.e("TAG","Id=======type======="+Id+"---"+type);
        bt_copy= (Button) findViewById(R.id.bt_copy);
        the_order_number_number= (TextView) findViewById(R.id.the_order_number_number);
        the_order_price= (TextView) findViewById(R.id.the_order_price);
        type2= (TextView) findViewById(R.id.type2);
        buyer2= (TextView) findViewById(R.id.buyer2);
        state2= (TextView) findViewById(R.id.state2);
        company2= (TextView) findViewById(R.id.company2);
        bt_zhifu = (Button) findViewById(R.id.bt_Zhifu);
        bt_zhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Popupwindow();
                backgroundAlpha(0.5f);

            }
        });
        bt_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager copy = (ClipboardManager) SpotOrder.this
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(the_order_number_number.getText().toString());

            }
        });
        tv_order_number1= (TextView) findViewById(R.id.the_order_number_number);

        spot_order_title = ((RelativeLayout) findViewById(R.id.spot_order_title));
        order_amount = ((RelativeLayout) findViewById(R.id.order_amount));
        order_amount.setOnClickListener(this);
        spot_order_title.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title = ((TextView) findViewById(R.id.tv_title_bar_title));
        spot_order_listView = ((CustomListView) findViewById(R.id.spot_order_listView));
        iv_title_bar_logo.setVisibility(View.INVISIBLE);
        iv_title_bar_service.setVisibility(View.INVISIBLE);
        sv_title_bar_serachView.setVisibility(View.INVISIBLE);

        iv_title_bar_back.setOnClickListener(this);
        iv_title_bar_search.setOnClickListener(this);
        tv_title_bar_title.setText("待支付订单");
        tv_title_bar_title.setTextColor(Color.WHITE);

        tv = ((TextView) findViewById(R.id.tv));
        /**
         *
         * 根据系统下单时间，获取剩余结束时间
         */
        mc = new MyCount(50 * 1000, 1000);
        mc.start();
        new Thread(){
            @Override
            public void run() {
                super.run();
                Log.e("TAG","Thread()");
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
       /* spot_order_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SpotOrder.this,"dianji"+position+"条",Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_title_bar_back:
                finish();
                break;
            case R.id.iv_title_bar_search:
                Toast.makeText(this,"搜索",Toast.LENGTH_SHORT).show();

                break;
            case R.id.order_amount:
                Intent intent = new Intent();
                intent.setClass(this, OrderDetailsActivity.class);
                startActivity(intent);
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
            tv.setText("finish");
        }
        @Override
        public void onTick(long millisUntilFinished) {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            String time = format.format(millisUntilFinished- TimeZone.getDefault().getRawOffset());
            tv.setText("剩余支付时间：" +time);
        }
    }

    public void Popupwindow(){
        View view=getLayoutInflater().inflate(R.layout.item_popupwindow_auction,null);
          popupWindow=new PopupWindow(findViewById(R.id.Layout_c), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        LinearLayout layout_one= (LinearLayout) view.findViewById(R.id.layout_one);
        LinearLayout layout_two= (LinearLayout) view.findViewById(R.id.layout_two);
        LinearLayout layout_three= (LinearLayout) view.findViewById(R.id.layout_three);
        layout_one.setVisibility(View.GONE);
        layout_two.setVisibility(View.GONE);
        layout_three.setVisibility(View.GONE);
        tv= (TextView) view.findViewById(R.id.tvPrice_popupwindow_auction);
        et= (EditText) view.findViewById(R.id.etZhifu_auction);
        lv= (ListView) view.findViewById(R.id.lv_popupwindow_auction);
        final List<OpenAuction> list_pop=new ArrayList<OpenAuction>();
        OpenAuction openauction1=new OpenAuction();
        openauction1.iv_icon=R.mipmap.direct;
        openauction1.tv_Name="北京工商银行";
        openauction1.tv_money="1234";
        list_pop.add(openauction1);
        OpenAuction openauction2=new OpenAuction();
        openauction2.iv_icon=R.mipmap.vertet;
        openauction2.tv_Name="北京建设银行";
        openauction2.tv_money="1234";
        list_pop.add(openauction2);
        Log.d("changdu",list_pop.size()+"");
        adapter1=new PopupWindowAdaptrer(list_pop,SpotOrder.this);
        lv.setAdapter(adapter1);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getId()==lv.getId()){
                    for(int i=0;i<list_pop.size();i++){
                        Log.d("Listview的item",position+"");
                        if(i!=position){

                            list_pop.get(i).Flag=false;

                        }
                    }
                    list_pop.get(position).Flag=!list_pop.get(position).Flag;
                    adapter1.notifyDataSetChanged();
                }
            }
        });
        Button bt= (Button) view.findViewById(R.id.btZhifu);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            popupWindow.dismiss();
                Intent intent=new Intent(SpotOrder.this,Ok_Dialog.class);
                intent.putExtra("number",tv_order_number1.getText().toString());
                startActivity(intent);

            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.showAtLocation(spot_order_listView, Gravity.BOTTOM,0,0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });

    }
    //设置背景透明
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
    private void sendMy(){
        RequestParams params=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/myOrders");
        params.addParameter("pageNum","1");
        params.addParameter("searchOrderId",Id);
//        params.addParameter("searchOrderType",type);
        params.addParameter("searchOrderState","1");
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
                        Toast.makeText(SpotOrder.this,"获取数据失败",Toast.LENGTH_SHORT).show();
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
                            String state1="";
                            if(state.equals("SUBMITTED")||state.equals("PENDING_APPROVAL")||state.equals("APPROVED")||state.equals("FAILED_APPROVAL")){
                                if(type.equals("offlineOrder")) {
                                    state1 = "订单生成";
                                }else{
                                    state1="待支付";
                                }
                            }
                            if(state.equals("DEPOSIT_CONFIRMED")){
                                state1="支付保证金已冻结";
                            }else if(state.equals("QUOTED")){
                                if(shippingGroupState.equals("INITIAL")) {
                                    state1 = "已支付";
                                }else{
                                    state1="已发货";
                                }
                            }else if(state.equals("PRESSING1")){
                                state1="已发货";
                            }else if (state.equals("NO_PENDING_ACTION")){
                                state1="已完成";
                            }else if (state.equals("REMOVED")){
                                state1="已取消";
                            }else if (state.equals("CHANGED")){
                                state1="已变更";
                            }
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
                            adapter = new SpotOrderAdapter(SpotOrder.this,list);
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
}
