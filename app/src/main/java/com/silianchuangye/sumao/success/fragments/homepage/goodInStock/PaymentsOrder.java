package com.silianchuangye.sumao.success.fragments.homepage.goodInStock;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.dialog.Error_Dialog;
import com.silianchuangye.sumao.success.dialog.Ok_Dialog;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean.OrderList;
import com.silianchuangye.sumao.success.utils.LogUtils;
//import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean.OrderIdList;

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
 * Created by Administrator on 2016/7/4 0004.
 */
public class PaymentsOrder extends Activity implements View.OnClickListener {

    private TextView title_bar_white_title;
    private TextView product_order_number;
    private TextView money_number;
    private TextView residual_time;
    private TextView surplus_amount_et;
    private TextView purchase_quantity_et;
    private TextView min_variable_et;
    private TextView delivery_time_et;
    private TextView classification_pre_sale_et;
    private TextView warehouse_et;
    private TextView region_et;
    private TextView company_et;
    private TextView total_money;
    private Button buy_immediately;
    private ImageView title_bar_white_shopping_cart;
    private ImageView title_bar_white_back;
    private TextView tv;
    private ListView lv;
    private EditText et;
    private PopupWindowAdaptrer adapter1;
    private PopupWindow popupWindow;
    private ListView lvdemo;
    private List<Map<String, Object>> list;
    private SimpleAdapter list_adapter;
    private List<String> list_id;
    private List<OpenAuction> list_pop;
    private String bank_Info;
    private List<String> list_order = new ArrayList<String>();
    private String name, type, price, number, qiye, all_price, cangku, comm, paihao, order_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payments_order_activity);

        lvdemo = (ListView) findViewById(R.id.lbDem);
        addData();
        //页面标题 “支付订单”
        title_bar_white_title = (TextView) findViewById(R.id.title_bar_white_title);
//        设置时：   产品订单号：+number
        product_order_number = (TextView) findViewById(R.id.product_order_number);
//        支付总金额
        money_number = (TextView) findViewById(R.id.money_number);
//        设置时： 提示：剩余时间+num+分
        residual_time = (TextView) findViewById(R.id.residual_time);

////        公司
        company_et = (TextView) findViewById(R.id.company_et);
//        总金额
        total_money = (TextView) findViewById(R.id.total_money);
//        立即购买
        buy_immediately = (Button) findViewById(R.id.buy_immediately);
//        因为标题是复用的，现在要把多余的图标影藏掉
        title_bar_white_shopping_cart = (ImageView) findViewById(R.id.title_bar_white_shopping_cart);
        title_bar_white_shopping_cart.setVisibility(View.INVISIBLE);
//        返回按钮设置监听
        title_bar_white_back = (ImageView) findViewById(R.id.title_bar_white_back);
        title_bar_white_back.setOnClickListener(this);
//        为  立即支付按钮  设置监听
        buy_immediately.setOnClickListener(this);
//          设置标题 支付订单
        title_bar_white_title.setText("支付订单");
    }

    public void addData() {

        commit_order();
    }

    public void commit_order() {
        String fixOrCart = getIntent().getStringExtra("type");
        if (fixOrCart.equals("cart")) {
            list_id = getIntent().getExtras().getStringArrayList("id");
            Log.d("商品id的集合", list_id + "ssssssssssssssssssssssss");
            new Thread() {
                @Override
                public void run() {
                    //super.run();
                    String url = "http://192.168.32.126:7023/rest/model/atg/commerce/ShoppingCartActor/cartExpressCheckout";
                    RequestParams rp = new RequestParams(url);
                    String a = list_id.toString();
                    Log.d("商品id", a + "ssssssssssss");
                    int len = a.length() - 1;
                    String id = a.substring(1, len).replaceAll(" ", "");
                    Log.d("商品id", "" + id);
                    rp.addParameter("strCommerceItemIds", id);
                    SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                    String coummit_unique = sp.getString("unique", "");
                    rp.addParameter("_dynSessConf", coummit_unique);
                    Log.d("提交订单的唯一标识", coummit_unique);
                    Log.d("rp的值", rp + "");
                    x.http().post(rp, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Log.d("提交订单的result", result);
                            try {
                                JSONObject obj_result = new JSONObject(result);
                                String Message = obj_result.getString("OrderIdList");
                                JSONArray array = new JSONArray(Message);
                                list = new ArrayList<Map<String, Object>>();
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject obj_array = array.getJSONObject(i);
                                    //Map<String,Object> map=new Hashtable<String, Object>();
                                    String info = obj_array.getString("commerceItem");
                                    String time = obj_array.getString("remainingTime");
                                    order_id = obj_array.getString("orderId");
                                    String total = obj_array.getString("total");
                                    JSONArray array_obj = new JSONArray(info);
                                    for (int j = 0; j < array_obj.length(); j++) {
                                        JSONObject gouwuche_info = array_obj.getJSONObject(j);
                                        type = gouwuche_info.getString("parentCategories");
                                        price = gouwuche_info.getString("salePrice");
                                        paihao = gouwuche_info.getString("gradeNumber");
                                        number = gouwuche_info.getString("quantity");
                                        qiye = gouwuche_info.getString("manufacturer");
                                        all_price = gouwuche_info.getString("amount");
                                        cangku = gouwuche_info.getString("warehouse");
                                        comm = gouwuche_info.getString("salesCompanyDisplayName");
                                        name = gouwuche_info.getString("productName");

                                    }
                                    Map<String, Object> map = new Hashtable<String, Object>();
                                    map.put("order_id", "订单编号:" + order_id);
                                    map.put("total", total);
                                    map.put("time", time);
                                    map.put("name", name);
                                    map.put("type", type);
                                    map.put("paihao", paihao);
                                    map.put("qiye", qiye);
                                    map.put("cangku", cangku);
                                    map.put("price", price);
                                    map.put("number", number);
                                    map.put("all_price", all_price);
                                    map.put("comm", comm);
                                    list.add(map);
                                    list_order.add(order_id);

                                }
                                list_adapter = new SimpleAdapter(PaymentsOrder.this, list, R.layout.order_item,
                                        new String[]{"order_id", "total", "time", "name", "type", "paihao", "qiye",
                                                "cangku", "price", "number", "all_price", "comm"},
                                        new int[]{
                                                R.id.product_order_number,
                                                R.id.money_number,
                                                R.id.residual_time,
                                                R.id.tv_name,
                                                R.id.surplus_amount_et,
                                                R.id.purchase_quantity_et,
                                                R.id.min_variable_et,
                                                R.id.delivery_time_et,
                                                R.id.classification_pre_sale_et,
                                                R.id.warehouse_et,
                                                R.id.region_et,
                                                R.id.company_et


                                        });
                                lvdemo.setAdapter(list_adapter);/////////////
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
            }.start();
        } else {
            String info = getIntent().getStringExtra("fix");
            String[] infos = info.split(",");
            String url = "http://192.168.32.126:7023/rest/model/atg/commerce/ShoppingCartActor/go";
            RequestParams requestParams = new RequestParams(url);
            requestParams.addParameter("quantity", infos[0]);

//        SharedPreferences sp=getActivity().getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            // String unique_gouwuche=sp.getString("unique","");
            // Log.d("一键购得唯一标识",unique_gouwuche);
            requestParams.addParameter("_dynSessConf", infos[1]);
            Log.d("一键购得唯一标识", infos[1]);
            requestParams.addParameter("productId", infos[2]);
            Log.d("商品编号", infos[2]);
            requestParams.addParameter("skuId", infos[3]);
            Log.d("Skuid", infos[3]);
            Log.d("rp的值", requestParams + "");
            x.http().post(requestParams, new Callback.CommonCallback<String>() {
                private OrderList orderList;
                private OrderList orderIdLists;

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("result-->" + result);
                    Gson gson = new Gson();
                    orderList = gson.fromJson(result, OrderList.class);
                    LogUtils.log("可以获取到的数据-->getQuantity" + orderList.getOrderIdList().get(0).getCommerceItem().get(0).getQuantity());//这里可以获取到数据么？
                    list=new ArrayList<Map<String,Object>>();
                    order_id = orderList.getOrderIdList().get(0).getOrderId();
                    type = orderList.getOrderIdList().get(0).getCommerceItem().get(0).getParentCategories();
                    price = orderList.getOrderIdList().get(0).getCommerceItem().get(0).getSalePrice() + "";
                    paihao = orderList.getOrderIdList().get(0).getCommerceItem().get(0).getGradeNumber();
                    number = orderList.getOrderIdList().get(0).getCommerceItem().get(0).getQuantity() + "";
                    qiye = orderList.getOrderIdList().get(0).getCommerceItem().get(0).getManufacturer();
                    all_price = orderList.getOrderIdList().get(0).getCommerceItem().get(0).getAmount() + "";
                    cangku = orderList.getOrderIdList().get(0).getCommerceItem().get(0).getWarehouse();
                    comm = orderList.getOrderIdList().get(0).getCommerceItem().get(0).getSalesCompanyDisplayName();
                    name = orderList.getOrderIdList().get(0).getCommerceItem().get(0).getProductName();
                    String total = orderList.getOrderIdList().get(0).getTotal() + "";
                    String time = orderList.getOrderIdList().get(0).getRemainingTime() + "";
                    Log.d("企业名称","企业名称"+order_id);
                    Log.d("企业名称","企业名称"+qiye);
                    Map<String, Object> map = new Hashtable<String, Object>();
                    map.put("order_id", "订单编号:" + order_id);
                    map.put("total", total);
                    map.put("time", time);
                    map.put("name", name);
                    map.put("type", type);
                    map.put("paihao", paihao);
                    map.put("qiye", qiye);
                    map.put("cangku", cangku);
                    map.put("price", price);
                    map.put("number", number);
                    map.put("all_price", all_price);
                    map.put("comm", comm);
                    Log.d("企业名称",map.toString());
                    list.add(map);
                    list_order.add(order_id);
                    Log.d("企业名称",list.toString());
                    Log.d("企业名称",list.size()+"");


                    list_adapter = new SimpleAdapter(PaymentsOrder.this, list, R.layout.order_item,
                            new String[]{"order_id", "total", "time", "name", "type", "paihao", "qiye",
                                    "cangku", "price", "number", "all_price", "comm"},
                            new int[]{
                                    R.id.product_order_number,
                                    R.id.money_number,
                                    R.id.residual_time,
                                    R.id.tv_name,
                                    R.id.surplus_amount_et,
                                    R.id.purchase_quantity_et,
                                    R.id.min_variable_et,
                                    R.id.delivery_time_et,
                                    R.id.classification_pre_sale_et,
                                    R.id.warehouse_et,
                                    R.id.region_et,
                                    R.id.company_et


                            });
                    lvdemo.setAdapter(list_adapter);

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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_immediately:
                backgroundAlpha(0.5f);
                Popupwindow();
                break;
            case R.id.title_bar_white_back:
                finish();
                break;


        }
    }

    public void Popupwindow() {
        View view = getLayoutInflater().inflate(R.layout.item_popupwindow_auction, null);
        popupWindow = new PopupWindow(findViewById(R.id.Layout_Button_Open), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
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
        // final List<OpenAuction> list_pop=new ArrayList<OpenAuction>();

        getinfo_Bank();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
            }
        });
        Button bt = (Button) view.findViewById(R.id.btZhifu);
        bt.setText("立即支付");
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //支付订单的接口
                getzhifu();
                popupWindow.dismiss();


            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.showAtLocation(buy_immediately, Gravity.BOTTOM, 0, 0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });

    }

    //设置背景透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }


    public void getinfo_Bank() {
        Bundle bundle = getIntent().getExtras();
        list_id = bundle.getStringArrayList("id");
        Log.d("商品id的集合", list_id + "ssssssssssssssssssssssss");
        new Thread() {
            @Override
            public void run() {
                // super.run();
                String url = "http://192.168.32.126:7023/rest/model/atg/commerce/payment/OrderPayment/bankList";
                RequestParams rp = new RequestParams(url);
                rp.addBodyParameter("payFor", "order");
                String a = list_order.toString();
                Log.d("商品id", a + "ssssssssssss");
                int len = a.length() - 1;
                String id = a.substring(1, len).replaceAll(" ", "");
                Log.d("商品id", "" + id);
                rp.addParameter("orderIds", id);
                Log.d("银行列表的rp", "" + rp);
                x.http().post(rp, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("银行的列表", result);
                        if (result.contains("balance")) {
                            try {
                                list_pop = new ArrayList<OpenAuction>();
                                JSONObject obj = new JSONObject(result);
                                String message = obj.getString("bankResult");
                                JSONObject obj_Bank = new JSONObject(message);
                                if (result.contains("pingAn")) {
                                    OpenAuction auction = new OpenAuction();
                                    auction.iv_icon = R.mipmap.pingan;
                                    auction.tv_Name = "平安银行";
                                    if (obj_Bank.getString("pingAn").equals("false")) {
                                        auction.message = "该用户没有开通平安银行";

                                    }
                                    //auction.tv_money=obj.getString("balance");
                                    list_pop.add(auction);
                                }
                                if (result.contains("jianShe")) {
                                    OpenAuction auction = new OpenAuction();
                                    auction.iv_icon = R.mipmap.jianshe;
                                    auction.tv_Name = "中国建设银行";
                                    //auction.tv_money=obj.getString("balance");
                                    if (obj_Bank.getString("jianShe").equals("false")) {
                                        auction.message = "该用户没有开通中国建设银行";
                                    }
                                    list_pop.add(auction);
                                }
//                                if (obj_Bank.getString("pingAn").equals("false")){
//                                    bank_Info="该用户没有绑定平安银行";
//                                }
//                                if (obj_Bank.getString("jianShe").equals("")){
//                                    bank_Info="该用户没有绑定建设银行";
//                                }
//                                JSONArray array=new JSONArray(message);
//                                for (int i=0;i<array.length();i++){
//                                    JSONObject obj_array=array.getJSONObject(i);
//
//                                    auction.tv_money=obj_array.getString("balance");
//                                    String type=obj_array.getString("bankType");
//                                    if (type.equals("1")){
//                                        //平安
//                                        auction.iv_icon=R.mipmap.pingan;
//                                        auction.tv_Name="平安银行";
//
//                                    }else if (type.equals("2")){
//                                        //昆仑
//                                        auction.iv_icon=R.mipmap.kunlun;
//                                        auction.tv_Name="昆仑银行";
//                                    }else if (type.equals("3")){
//                                        //建行
//                                        auction.iv_icon=R.mipmap.jianshe;
//                                        auction.tv_Name="中国建设银行";
//                                    }
//                                    list_pop.add(auction);

                                //}
                                adapter1 = new PopupWindowAdaptrer(list_pop, PaymentsOrder.this);
                                lv.setAdapter(adapter1);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(PaymentsOrder.this, "该用户没有登录,无法获取可支付银行列表!", Toast.LENGTH_SHORT).show();
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

    public void getzhifu() {
        String url = "http://192.168.32.126:7023/rest/model/atg/commerce/payment/OrderPayment/orderPayment";
        RequestParams rp = new RequestParams(url);
        String a = list_order.toString();
        Log.d("商品id", a + "ssssssssssss");
        int len = a.length() - 1;
        String id = a.substring(1, len).replaceAll(" ", "");
        Log.d("商品id", "" + id);
        rp.addParameter("orderIds", id);
        rp.addParameter("platform", 1);
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String zhifu_unique = sp.getString("unique", "");
        rp.addParameter("_dynSessConf", zhifu_unique);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("支付时返回的result", result);
                try {
                    JSONObject obj = new JSONObject(result);
                    String state = obj.getString("paymentStatus");
                    if (state.equals("0")) {
                        //全部成功
                        Intent intent = new Intent(PaymentsOrder.this, Ok_Dialog.class);
                        startActivity(intent);
                    } else if (state.equals("1")) {
                        //部分订单成功
                        Toast.makeText(PaymentsOrder.this, "部分订单成功,到订单详情界面查看", Toast.LENGTH_SHORT).show();
                    } else if (state.equals("2")) {
                        //全部失败
                        Intent intent = new Intent(PaymentsOrder.this, Error_Dialog.class);
                        startActivity(intent);
                    }
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

}
