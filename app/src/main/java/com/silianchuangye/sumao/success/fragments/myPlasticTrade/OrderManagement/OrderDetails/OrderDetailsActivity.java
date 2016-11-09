package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.OrderDetails;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.Text;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.OrderDetailsListViewAdapter;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.adapter.PreSaleAdapter;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.dialog.Error_Dialog;
import com.silianchuangye.sumao.success.dialog.Ok_Dialog;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;
import com.silianchuangye.sumao.success.model.OrderDeatilsModel;
import com.silianchuangye.sumao.success.model.PreSaleModel;
import com.silianchuangye.sumao.success.utils.Loding;
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
 * Created by Administrator on 2016/7/6 0006.
 */
public class OrderDetailsActivity  extends Activity{

    private CustomListView order_details_listView;
    private OrderDetailsListViewAdapter adapter;
    private List<OrderDeatilsModel> list;
    private OrderDeatilsModel orderDeatilsModel;
    private TextView tv_child_title_bar_title;
    private Button bt_copy;
    private TextView tv_order_number;
    private String Id;
    private TextView totalMoney2,univalent2,number2,enterprise2,warehouse2;
    private TextView bottom_money2,tv_xianqing_type;
    private ImageView iv_child_title_bar_back;
    private Button btn_pay;
    private ListView lv;
    String type1;
    PopupWindowAdaptrer adapterBank;
    PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details_activity);
        Id=getIntent().getStringExtra("ID");
        Log.e("TAG","Id====="+Id);
        bt_copy= (Button) findViewById(R.id.bt_copy);
        tv_order_number= (TextView) findViewById(R.id.tv_order_number);
        univalent2= (TextView) findViewById(R.id.univalent2);
        number2= (TextView) findViewById(R.id.number2);
        enterprise2= (TextView) findViewById(R.id.enterprise2);
        totalMoney2= (TextView) findViewById(R.id.totalMoney2);
        warehouse2= (TextView) findViewById(R.id.warehouse2);
        order_details_listView = (CustomListView)findViewById(R.id.order_details_listView);
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("订单详情");
        bottom_money2= (TextView) findViewById(R.id.bottom_money2);
        tv_xianqing_type= (TextView) findViewById(R.id.tv_xianqing_type);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        btn_pay= (Button) findViewById(R.id.btn_pay);
        iv_child_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        initdata();
        order_details_listView.setAdapter(adapter);
        enevt();
        new Thread(){
            @Override
            public void run() {
                super.run();
                sendMy();
            }
        }.start();
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type1.equals("预售")){
                    Popupwindow();
                    backgroundAlpha(0.5f);
                }else if(type1.equals("现货")){

                }
            }
        });
    }
    TextView tv_yushou;
    public void Popupwindow(){
        View view=getLayoutInflater().inflate(R.layout.pop_yushou,null);
       popupWindow=new PopupWindow(findViewById(R.id.Layout_c), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        tv_yushou= (TextView) view.findViewById(R.id.tv_pay);
        tv_yushou.setText(11111+"");
//        et= (EditText) view.findViewById(R.id.etZhifu_auction);
       lv= (ListView) view.findViewById(R.id.lv_popupwindow_auction);
        getinfo_Bank();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getId()==lv.getId()){
                    for(int i=0;i<list1.size();i++){
                        Log.d("Listview的item",position+"");
                        if(i!=position){
                            list1.get(i).Flag=false;
                        }
                    }
                    list1.get(position).Flag=!list1.get(position).Flag;
                    adapterBank.notifyDataSetChanged();
                }
            }
        });
        Button bt= (Button) view.findViewById(R.id.btZhifu);
        //支付
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payMoney();
            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.showAtLocation(bt, Gravity.BOTTOM,0,0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });

    }
    List<OpenAuction> list1;
    //获取银行列表
    public void getinfo_Bank(){
        new Thread(){
            @Override
            public void run() {
                // super.run();
                String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/availableBank";
                RequestParams rp=new RequestParams(url);
                rp.addParameter("productId","");
                Log.d("银行列表的rp",""+rp);
                Log.e("TAG","rp------"+rp);
                x.http().post(rp, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("银行的列表",result);
                        Log.e("TAG","result-----"+result);
                        //{"bankList":[{"amount":"21417.51","balance":"19752.69","accountNumber":"11014970585008","bankType":"1","bankName":"???????"}],"info":"sucess"}
                        try {
                            JSONObject job=new JSONObject(result);
                            String info=job.getString("info");
                            if(!info.equals("sucess")){
                                Toast.makeText(OrderDetailsActivity.this, "该用户没有登录,无法获取可支付银行!", Toast.LENGTH_SHORT).show();
                            }else{
                                String bankList=job.getString("bankList");
                                if(bankList.equals("No Bank Info")){
                                    Toast.makeText(OrderDetailsActivity.this,"没有银行列表",Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (result.contains("amount")){
                            try{
                                list1=new ArrayList<OpenAuction>();
                                JSONObject obj=new JSONObject(result);
                                String message=obj.getString("bankList");
                                JSONArray array=new JSONArray(message);
                                for (int i=0;i<array.length();i++){
                                    JSONObject obj_array=array.getJSONObject(i);
                                    OpenAuction auction=new OpenAuction();
                                    auction.tv_money=obj_array.getString("balance");
                                    String type=obj_array.getString("bankType");
                                    if (type.equals("1")){
                                        //平安
                                        auction.iv_icon=R.mipmap.pingan;
                                        auction.tv_Name="平安银行";

                                    }else if (type.equals("2")){
                                        //昆仑
                                        auction.iv_icon=R.mipmap.kunlun;
                                        auction.tv_Name="昆仑银行";
                                    }else if (type.equals("3")){
                                        //建行
                                        auction.iv_icon=R.mipmap.jianshe;
                                        auction.tv_Name="中国建设银行";
                                    }
                                    list1.add(auction);

                                }
                                adapterBank=new PopupWindowAdaptrer(list1,OrderDetailsActivity.this);
                                lv.setAdapter(adapterBank);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
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
        }.start();
    }
    //支付预售保证金
    private void payMoney(){
        RequestParams params=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/payment/OrderPayment/goPresale");
        params.addParameter("skuId","243");
        params.addParameter("productId","");
        params.addParameter("quantity","123");//保证金
        String str,blankname="";
        if (list1!=null){
            for(int i=0;i<list1.size();i++) {
                if (list1.get(i).Flag) {
                    str = list1.get(i).tv_Name;
                    Log.e("TAG","str----"+str);
                    if (str.equals("平安银行")) {
                        //平安
                        blankname = "1";
                    } else if (str.equals("昆仑银行")) {
                        //昆仑
                        blankname = "2";
                    } else if (str.equals("中国建设银行")) {
                        //建行
                        blankname = "3";
                    }
                }
            }
        }
        if(blankname.equals("")){
            Toast.makeText(OrderDetailsActivity.this,"请选择支付银行",Toast.LENGTH_SHORT).show();

        }else {
            SharedPreferences sp = this.getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            String unique123 = sp.getString("unique", "");
            params.addParameter("_dynSessConf", unique123);
            Log.e("TAG", "blankName------" + blankname);
            params.addParameter("paymentPlatform", blankname);
            Log.e("TAG", "params=-----------" + params);
            Loding.show(this,"正在请求网络",false,null);//网络请求之前调用
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.e("TAG", "支付保证金result-----" + result);
//                    result-----{"orderId":"10094600000005","info":"sucess"}
                    try {
                        JSONObject job=new JSONObject(result);
                        String info=job.getString("info");
                        String orderId=job.getString("orderId");
                        if(info.equals("sucess")){
                            Toast.makeText(OrderDetailsActivity.this,"支付成功",Toast.LENGTH_SHORT).show();
                            popupWindow.dismiss();
                            // TODO 显示订单信息
                            Intent intent=new Intent(OrderDetailsActivity.this, Ok_Dialog.class);
                            intent.putExtra("number",orderId);
                            intent.putExtra("type","预售保证金");
                            startActivity(intent);

                        }else{
                            Toast.makeText(OrderDetailsActivity.this,"支付失败",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(OrderDetailsActivity.this, Error_Dialog.class);
                            intent.putExtra("number",orderId);
//                            intent.putExtra("type","预售保证金");
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.e("TAG", "ex----" + ex.toString());
                    Log.e("TAG", "ex-----" + ex.getMessage().toString());
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {
                    Loding.dis();
                }
            });
        }
    }
    //设置背景透明
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
    private void initdata() {
        list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            orderDeatilsModel = new OrderDeatilsModel();
            orderDeatilsModel.setWarehouse("公司"+i);
            orderDeatilsModel.setClassification("分类"+"LLDPE"+i);
            orderDeatilsModel.setDeliveryTime("交货时间"+i);
            orderDeatilsModel.setDistributionMode("配送方式："+i);
            orderDeatilsModel.setNumber(i);
            orderDeatilsModel.setProductUnitPrice("产品单价"+i);
            orderDeatilsModel.setTitle("标题"+i);
            orderDeatilsModel.setTotalProductPrice("总价"+i);
            list.add(orderDeatilsModel);
        }
        adapter = new OrderDetailsListViewAdapter(this, list);

    }
    public void enevt(){
        bt_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager copy = (ClipboardManager) OrderDetailsActivity.this
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(tv_order_number.getText().toString());

            }
        });
    }
    private void sendMy(){
        list = new ArrayList<>();
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
                    Log.e("TAG","str------"+str);
                    JSONObject job2=new JSONObject(str);
                    String price=job2.getString("price");
                    Log.e("TAG","price--"+price);
                    bottom_money2.setText(price);
                    String type=job2.getString("type");//类型
                    type1=getType(type);
                    Log.e("TAG","类型-----"+type1);
                    tv_xianqing_type.setText(type1);
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
                            totalMoney2.setText(cl_amount);
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
                        adapter = new OrderDetailsListViewAdapter(OrderDetailsActivity.this,list);
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
