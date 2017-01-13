package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.OrderDetails.OrderDetailsMVP.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
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
import com.silianchuangye.sumao.success.adapter.OrderDetailsListViewAdapter;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.adapter.SpotOrderAdapter;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.dialog.Error_Dialog;
import com.silianchuangye.sumao.success.dialog.Ok_Dialog;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.bean.BankListBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.OrderDetails.OrderDetailsMVP.presenter.OrderDetailsActivityPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.bean.PreSellAllBean;
import com.silianchuangye.sumao.success.model.OrderDeatilsModel;
import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class OrderDetailsActivity  extends Activity implements IOrderDetailsView{

    private OrderDetailsListViewAdapter adapter;
    private List<OrderDeatilsModel> list;
    private OrderDeatilsModel orderDeatilsModel;
    private TextView tv_child_title_bar_title;
    private Button bt_copy;
    private TextView tv_order_number;
    private PreSellAllBean.OrderBean orderBean;
    private TextView totalMoney2,univalent2,number2,enterprise2,warehouse2;
    private TextView bottom_money2,tv_xianqing_type;
    private ImageView iv_child_title_bar_back;
    private Button btn_pay;
    private ListView lv;
    String type1;
    PopupWindowAdaptrer adapterBank;
    PopupWindow popupWindow;
    private ImageView order_status_order_details;
    private LinearLayout total_money;
    private OrderDetailsActivityPresenter orderDetailsActivityPresenter;
    private List<String> list_order = new ArrayList<String>();
    private TextView tv;
    private EditText et;
    private ArrayList<OpenAuction> list_pop;
    private String count_price;
    private PopupWindowAdaptrer adapter1;
    CustomListView spot_order_listView;
    //    适配器
    SpotOrderAdapter spotOrderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details_activity);
        orderDetailsActivityPresenter = new OrderDetailsActivityPresenter(this);
        orderBean =(PreSellAllBean.OrderBean)getIntent().getSerializableExtra("orderBean");
        LogUtils.log("orderBean--->"+ orderBean);
        bt_copy= (Button) findViewById(R.id.bt_copy);
        tv_order_number= (TextView) findViewById(R.id.tv_order_number);
        univalent2= (TextView) findViewById(R.id.univalent2);
        number2= (TextView) findViewById(R.id.number2);
        enterprise2= (TextView) findViewById(R.id.enterprise2);
        totalMoney2= (TextView) findViewById(R.id.totalMoney2);
        warehouse2= (TextView) findViewById(R.id.warehouse2);
        spot_order_listView = (CustomListView)findViewById(R.id.order_details_listView);
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("订单详情");
        bottom_money2= (TextView) findViewById(R.id.bottom_money2);
        tv_xianqing_type= (TextView) findViewById(R.id.tv_xianqing_type);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        btn_pay= (Button) findViewById(R.id.btn_pay);
        order_status_order_details = (ImageView) findViewById(R.id.order_status_order_details);
        total_money = ((LinearLayout) findViewById(R.id.total_money));

        iv_child_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        initdata();
        enevt();
        setValue();
        IntentFilter filter = new IntentFilter();
        filter.addAction("finishsportorder");
        registerReceiver(broadcastReceiver, filter);
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Popupwindow();
                backgroundAlpha(0.5f);
            }
        });
    }

    private void setValue() {

//        订单号
        tv_order_number.setText(orderBean.getOrderId());
//        订单类型
        String type = orderBean.getType();
        if (type.equals("fixedPricingOrder")){//现货
            univalent2.setText("现货");
            tv_xianqing_type.setText("现货");
        }else if (type.equals("openAuctionOrder")){//公开竞拍
            univalent2.setText("公开竞拍");
            tv_xianqing_type.setText("公开竞拍");
        }else if (type.equals("sealedAuctionOrder")){//密封竞拍
            univalent2.setText("密封竞拍");
            tv_xianqing_type.setText("密封竞拍");
        }else if (type.equals("integratePurchaseOrder")){//团购
            univalent2.setText("团购");
            tv_xianqing_type.setText("团购");
        }else if (type.equals("forwardPricingOrder")){//预售
            univalent2.setText("预售");
            tv_xianqing_type.setText("预售");
        }else if (type.equals("scheduleOrder")){//计划订单
            univalent2.setText("计划订单");
            tv_xianqing_type.setText("计划订单");
        }
//        订单状态
//        立即支付按钮的显示与隐藏
//        现实图标
        String state = orderBean.getState();
        if (state.equals("SUBMITTED")){//待支付
            number2.setText("待支付");
            total_money.setVisibility(View.VISIBLE);
            order_status_order_details.setImageResource(R.mipmap.to_pay_the_picture);
        }else if (state.equals("PAYMENT_COMPLETION")){//货款已支付
            number2.setText("货款已支付");
            total_money.setVisibility(View.GONE);
            order_status_order_details.setImageResource(R.mipmap.completed_order);
        }else if (state.equals("DEPOSIT_ERROR")){//保证金解冻失败
            number2.setText("保证金解冻失败");
            total_money.setVisibility(View.GONE);
            order_status_order_details.setImageResource(R.mipmap.completed_order);
        }else if (state.equals("DEPOSIT_SUCCESS")){//保证金解冻成功
            number2.setText("保证金解冻成功");
            total_money.setVisibility(View.GONE);
            order_status_order_details.setImageResource(R.mipmap.completed_order);
        }else if (state.equals("QUOTED")){//已支付
            number2.setText("已支付");
            total_money.setVisibility(View.GONE);
            order_status_order_details.setImageResource(R.mipmap.completed_order);
        }else if (state.equals("REMOVED")){//已取消
            number2.setText("已取消");
            total_money.setVisibility(View.GONE);
            order_status_order_details.setImageResource(R.mipmap.no_order_form);
        }else if (state.equals("CHANGED")){//已变更
            number2.setText("已变更");
            total_money.setVisibility(View.GONE);
            order_status_order_details.setImageResource(R.mipmap.no_order_form);
        }
//        订单提交时间
        enterprise2.setText(""+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Double.parseDouble(orderBean.getSubmittedDate())));
        double counnt = 0;
        for (int i = 0; i < orderBean.getCl().size(); i++) {
            counnt +=Double.parseDouble(orderBean.getCl().get(i).getCl_amount());
        }
//        订单金额
        bottom_money2.setText(counnt+"");
        totalMoney2.setText(counnt+"元");
//        销售公司
        warehouse2.setText(orderBean.getCl().get(0).getCl_gongsi());

        spotOrderAdapter = new SpotOrderAdapter(OrderDetailsActivity.this);

        spot_order_listView.setAdapter(spotOrderAdapter);
        spotOrderAdapter.addData(orderBean);
    }

    //获取银行列表
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
        String orderId = orderBean.getOrderId();
        list_order.add(orderId);
        String order = null;
        Loding.show(this, "加载中...", false, null);
        orderDetailsActivityPresenter.getBankListFromService(order, orderId);
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

                adapter1 = new PopupWindowAdaptrer(list_pop, OrderDetailsActivity.this);
                lv.setAdapter(adapter1);

            } else {
                Toast.makeText(OrderDetailsActivity.this, "登录已超时,请重新登录!", Toast.LENGTH_SHORT).show();

            }
            popupWindow.showAtLocation(total_money, Gravity.BOTTOM, 0, 0);

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
                            Toast.makeText(OrderDetailsActivity.this, "请选择的银行进行付款", Toast.LENGTH_SHORT).show();
                        }
                    }


                }
            });
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (list_pop.get(position).tv_Name.equals("昆仑银行")) {
                        Toast.makeText(OrderDetailsActivity.this, "昆仑银行需要进行网关支付", Toast.LENGTH_SHORT).show();
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
        } else {
            Toast.makeText(this, "该商品暂不支持银行付款", Toast.LENGTH_SHORT).show();
            backgroundAlpha(1f);
        }

    }

    @Override
    public void getPaymentResult(String result, String orderIds) {
        Loding.dis();
        try {
            JSONObject obj = new JSONObject(result);
            String state = obj.getString("paymentStatus");
            if (state.equals("0")) {
//                TODO !!!
                //全部成功
                Intent intent = new Intent(OrderDetailsActivity.this, Ok_Dialog.class);
                intent.putExtra("type", "现货订单");
                intent.putExtra("number", "这里填写订单号");
                startActivity(intent);
            } else if (state.equals("1")) {
                //部分订单成功
                Toast.makeText(OrderDetailsActivity.this, "部分订单成功,到订单详情界面查看", Toast.LENGTH_SHORT).show();
            } else if (state.equals("2")) {
                //全部失败
                Intent intent = new Intent(OrderDetailsActivity.this, Error_Dialog.class);
                intent.putExtra("number", "这里填写订单号");
                startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        Loding.show(this, "正在进行支付...", true, null);
        orderDetailsActivityPresenter.getPaymentInfoFromService(orderIds, platform, _dynSessConf);
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
