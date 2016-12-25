package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.dialog.Error_Dialog;
import com.silianchuangye.sumao.success.dialog.Ok_Dialog;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.Orders;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.adapter.PayMentsOrderListViewAdapter;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.bean.BankListBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.presenter.PaymentsOrderPresenter;
import com.silianchuangye.sumao.success.utils.Loding;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean.OrderIdList;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class PaymentsOrder extends Activity implements View.OnClickListener, IPaymentsOrderView {

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
    private PayMentsOrderListViewAdapter list_adapter;
    private List<String> list_id;
    private List<OpenAuction> list_pop;
    private String bank_Info;
    private String count_price;
    private TextView tv_payments_order_money;
    private List<String> list_order = new ArrayList<String>();
    private String name, type, price, number, qiye, all_price, cangku, comm, paihao, order_id;
    private PaymentsOrderPresenter paymentsOrderPresenter;
    private  List<Orders.OrderIdListBean> orderList;
    private Callback.Cancelable cancelable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payments_order_activity);
        paymentsOrderPresenter = new PaymentsOrderPresenter(this);
        lvdemo = (ListView) findViewById(R.id.lbDem);


        tv_payments_order_money = (TextView) findViewById(R.id.tv_payments_order_money);

        //页面标题 “支付订单”
        title_bar_white_title = (TextView) findViewById(R.id.title_bar_white_title);
//        设置时：   产品订单号：+number
        product_order_number = (TextView) findViewById(R.id.product_order_number);
//        支付总金额
        money_number = (TextView) findViewById(R.id.money_number);
//        设置时： 提示：剩余时间+num+分
        residual_time = (TextView) findViewById(R.id.residual_time);

//        公司
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
        IntentFilter filter = new IntentFilter();
        filter.addAction("finish");
        registerReceiver(broadcastReceiver, filter);
        addData();
    }

    public void addData() {

        commit_order();
    }

    public void commit_order() {
        String fixOrCart = getIntent().getStringExtra("type");
//        if (fixOrCart.equals("cart")) {
//            TODO !!!
//            list_id = getIntent().getExtras().getStringArrayList("id");
//            Log.d("商品id的集合", list_id + "ssssssssssssssssssssssss");
//            // TODO !!!
//            String a = list_id.toString();
//            int len = a.length() - 1;
//            String id = a.substring(1, len).replaceAll(" ", "");
//            Log.d("商品id", "" + id);
////            rp.addParameter("strCommerceItemIds", id);
//
        //        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
//        String coummit_unique = sp.getString("unique", "");
//        String _dynSessConf = coummit_unique;
//        String strCommerceItemIds = orderList.getOrderIdList().get(0).getCommerceItem().get(0).getCommerceItemId();
//        goodsInStockDetailPresenter.getPaymentOrder(strCommerceItemIds, _dynSessConf);

//            tv_payments_order_money.setText(getIntent().getStringExtra("all_price"));
//            if (result.contains("orderIdList")) {
//                try {
//                    JSONObject obj_result = new JSONObject(result);
//                    String Message = obj_result.getString("orderIdList");
//                    JSONArray array = new JSONArray(Message);
//                    list = new ArrayList<Map<String, Object>>();
//                    for (int i = 0; i < array.length(); i++) {
//                        JSONObject obj_array = array.getJSONObject(i);
//                        //Map<String,Object> map=new Hashtable<String, Object>();
//                        String info = obj_array.getString("commerceItem");
//                        String time = obj_array.getString("remainingTime");
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat
//                                ("yyyy年MM月dd日 hh:mm:mm");
//                        String data = simpleDateFormat.format(Double.parseDouble(time));
//                        order_id = obj_array.getString("orderId");
//                        String total = obj_array.getString("total");
//                        JSONArray array_obj = new JSONArray(info);
//                        for (int j = 0; j < array_obj.length(); j++) {
//                            JSONObject gouwuche_info = array_obj.getJSONObject(j);
//                            type = gouwuche_info.getString("parentCategories");
//                            price = gouwuche_info.getString("salePrice");
//                            paihao = gouwuche_info.getString("gradeNumber");
//                            number = gouwuche_info.getString("quantity");
//                            qiye = gouwuche_info.getString("manufacturer");
//                            all_price = gouwuche_info.getString("amount");
//                            cangku = gouwuche_info.getString("warehouse");
//                            comm = gouwuche_info.getString("salesCompanyDisplayName");
//                            name = gouwuche_info.getString("productName");
//
//                        }
//                        Log.d("支付的总价", total + "aa");
//                        Map<String, Object> map = new Hashtable<String, Object>();
//                        map.put("order_id", "订单编号:" + order_id);
//                        map.put("total", total);
//                        map.put("time", time);
//                        map.put("name", name);
//                        map.put("type", type);
//                        map.put("paihao", paihao);
//                        map.put("qiye", qiye);
//                        map.put("cangku", cangku);
//                        map.put("price", price);
//                        map.put("number", number);
//                        map.put("all_price", all_price);
//                        map.put("comm", comm);
//                        list.add(map);
//                        list_order.add(order_id);
//
//                    }
//                    list_adapter = new PayMentsOrderListViewAdapter(PaymentsOrder.this);
//                    lvdemo.setAdapter(list_adapter);/////////////
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            } else if (result.contains("formExceptions")) {
//                try {
//                    JSONObject object_message = new JSONObject(result);
//                    String message = object_message.getString("formExceptions");
//                    JSONArray array_msg = new JSONArray(message);
//                    for (int i = 0; i < array_msg.length(); i++) {
//                        JSONObject obj = array_msg.getJSONObject(i);
//                        String message_info = obj.getString("localizedMessage");
//                        Toast.makeText(PaymentsOrder.this, "" + message_info, Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }

//        } else {
        orderList = ( List<Orders.OrderIdListBean>) getIntent().getSerializableExtra("fix");
        list = new ArrayList<Map<String, Object>>();
        list_adapter = new PayMentsOrderListViewAdapter(this);
        list_adapter.addLists(orderList);
        lvdemo.setAdapter(list_adapter);
        double total = 0;
        for (int i = 0; i < orderList.size(); i++) {

            double count = Double.parseDouble(orderList.get(i).getTotal());
            total+=count;
        }
        tv_payments_order_money.setText(total + "");
//        }

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
        getinfo_Bank();

    }

    //设置背景透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    //    请求银行列表
    public void getinfo_Bank() {
//        Bundle bundle = getIntent().getExtras();
//        list_id = bundle.getStringArrayList("id");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < orderList.size(); i++) {
            if (i == orderList.size()-1){
                sb.append(orderList.get(i).getOrderId());
            }else {
                sb.append(orderList.get(i).getOrderId()+",");
            }

        }
        String orderId = sb.toString();
        list_order.add(orderId);
        String order = null;
        Loding.show(this, "加载中...", false, null);
        paymentsOrderPresenter.getBankListFromService(order, orderId);
    }

    //    进行支付请求
    public void getzhifu(String bankId) {
//TODO
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < list_order.size(); i++) {
            if (i == list_order.size() - 1) {
                stringBuffer.append(list_order.get(i));
            } else {
                stringBuffer.append(list_order.get(i) + ",");
            }
        }
        String a = stringBuffer.toString();
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String zhifu_unique = sp.getString("unique", "");
        String orderIds = a;
        String platform = bankId;
        String _dynSessConf = zhifu_unique;
        Loding.show(this,"正在进行支付...",false,null);
        paymentsOrderPresenter.getPaymentInfoFromService(orderIds, platform, _dynSessConf);
    }

    //    获取到银行列表
    @Override
    public void setBankListResult(String result) {
        Loding.dis();
        if (result.contains("openedAccount\":\"true\"")){

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

                adapter1 = new PopupWindowAdaptrer(list_pop, PaymentsOrder.this);
                lv.setAdapter(adapter1);

            } else {
                Toast.makeText(PaymentsOrder.this, "登录已超时,请重新登录!", Toast.LENGTH_SHORT).show();
            }
            popupWindow.showAtLocation(buy_immediately, Gravity.BOTTOM, 0, 0);

            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    //popupWindow.dismiss();
                    backgroundAlpha(1f);
                }
            });
            count_Price.setText(count_price);
//        立即支付
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for (int i = 0; i < list_pop.size(); i++) {
                        if (list_pop.get(i).Flag) {
                            //支付订单的接口
                            getzhifu(list_pop.get(i).message);
                            popupWindow.dismiss();
                        } else if (i == list_pop.size() - 1) {
                            Toast.makeText(PaymentsOrder.this, "请选择的银行进行付款", Toast.LENGTH_SHORT).show();
                        }
                    }


                }
            });
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (list_pop.get(position).tv_Name.equals("昆仑银行")) {
                        Toast.makeText(PaymentsOrder.this, "昆仑银行需要进行网关支付", Toast.LENGTH_SHORT).show();
                    } else {
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

                }
            });
        }else {
            Toast.makeText(this, "该商品暂不支持银行付款", Toast.LENGTH_SHORT).show();
        }

    }

    //    获取支付后的信息
    @Override
    public void getPaymentResult(String result) {
        Loding.dis();
        try {
            JSONObject obj = new JSONObject(result);
            String state = obj.getString("paymentStatus");
            if (state.equals("0")) {
                //全部成功
                Intent intent = new Intent(PaymentsOrder.this, Ok_Dialog.class);
                intent.putExtra("type", "现货");
                intent.putExtra("number", "这里填写订单号");
                startActivity(intent);
            } else if (state.equals("1")) {
                //部分订单成功
                Toast.makeText(PaymentsOrder.this, "部分订单成功,到订单详情界面查看", Toast.LENGTH_SHORT).show();
            } else if (state.equals("2")) {
                //全部失败
                Intent intent = new Intent(PaymentsOrder.this, Error_Dialog.class);
                intent.putExtra("number","这里填写订单号");
                startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //    页面销毁的广播
    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            unregisterReceiver(this);
            ((Activity) context).finish();
        }
    };
}

