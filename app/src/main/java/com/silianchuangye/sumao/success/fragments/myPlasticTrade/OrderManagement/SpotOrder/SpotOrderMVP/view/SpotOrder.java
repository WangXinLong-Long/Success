package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
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

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.adapter.SpotOrderAdapter;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.dialog.Error_Dialog;
import com.silianchuangye.sumao.success.dialog.Ok_Dialog;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.bean.BankListBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.bean.ContractInformation;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.presenter.SpotOrderPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.informationView.ContractInformationActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.informationView.InvoiceInformationActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.informationView.PaymentInformationActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.bean.PreSellAllBean;
import com.silianchuangye.sumao.success.model.SpotOrderModel;
import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
    List<SpotOrderModel> list;
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
    private List<String> list_order = new ArrayList<String>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;
    private SpotOrderPresenter spotOrderPresenter;
    private PreSellAllBean.OrderBean orderBean;
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
        orderBean = (PreSellAllBean.OrderBean) getIntent().getSerializableExtra("OrderBean");
        setValue(orderBean);
        IntentFilter filter = new IntentFilter();
        filter.addAction("finishsportorder");
        registerReceiver(broadcastReceiver, filter);

        bt_zhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Popupwindow();
            }
        });
    }


    //    初始化控件
    private void initView() {
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

        bt_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager copy = (ClipboardManager) SpotOrder.this
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(the_order_number_number.getText().toString());

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
        spotOrderAdapter = new SpotOrderAdapter(SpotOrder.this);

        spot_order_listView.setAdapter(spotOrderAdapter);
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

    //    设置值
    public void setValue(PreSellAllBean.OrderBean orderBean) {

        spotOrderAdapter.addData(orderBean);
        String state = orderBean.getState();
//        除了待支付，其他的都不显示倒计时
        if (state.equals("SUBMITTED")) {//待支付
            tv_title_bar_title.setText("待支付");
            state2.setText("待支付");
            count_down_tv.setVisibility(View.VISIBLE);
            bottom_money.setVisibility(View.VISIBLE);
            List<PreSellAllBean.OrderBean.ClBean> cl = orderBean.getCl();
            double total = 0;
            for (int i = 0; i < cl.size(); i++) {
                double amount = Double.parseDouble(cl.get(i).getCl_amount());
                total += amount;
            }
            bottom_money2.setText(String.valueOf(total));
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
        the_order_number_number.setText(orderBean.getOrderId());

        /**
         *待支付订单result
         * 根据系统下单时间，获取剩余结束时间
         */

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long countDownTime = 0;
//        订单类型
        String type = orderBean.getType();
        if (type.equals("fixedPricingOrder")) {//现货
            type2.setText("现货");
            tv_xianqing_type.setText("现货");
            long countDown = (Long.parseLong(orderBean.getSubmittedDate()) + 1000 * 3600) - System.currentTimeMillis();
            countDownTime = countDown > 0 ? countDown : 0;
        } else if (type.equals("openAuctionOrder")) {//公开竞拍
            type2.setText("公开竞拍");
            tv_xianqing_type.setText("公开竞拍");
            long countDown = (calendar.getTimeInMillis()) - Long.parseLong(orderBean.getSubmittedDate());
            countDownTime = countDown > 0 ? countDown : (calendar.getTimeInMillis() + 24 * 60 * 60 * 1000) - Long.parseLong(orderBean.getSubmittedDate());
        } else if (type.equals("sealedAuctionOrder")) {//密封竞拍
            type2.setText("密封竞拍");
            tv_xianqing_type.setText("密封竞拍");
            long countDown = (calendar.getTimeInMillis()) - Long.parseLong(orderBean.getSubmittedDate());
            countDownTime = countDown > 0 ? countDown : (calendar.getTimeInMillis() + 24 * 60 * 60 * 1000) - Long.parseLong(orderBean.getSubmittedDate());
        } else if (type.equals("integratePurchaseOrder")) {//团购
            type2.setText("团购");
            tv_xianqing_type.setText("团购");
            long countDown = (calendar.getTimeInMillis()) - Long.parseLong(orderBean.getSubmittedDate());
            countDownTime = countDown > 0 ? countDown : (calendar.getTimeInMillis() + 24 * 60 * 60 * 1000) - Long.parseLong(orderBean.getSubmittedDate());
        } else if (type.equals("forwardPricingOrder")) {//预售
            type2.setText("预售");
            tv_xianqing_type.setText("预售");
            countDownTime = Long.parseLong(orderBean.getCl().get(0).getPaymentEndDate()) - Long.parseLong(orderBean.getSubmittedDate());
        } else if (type.equals("scheduleOrder")) {//计划订单
            type2.setText("计划订单");
            tv_xianqing_type.setText("计划订单");
            long countDown = (calendar.getTimeInMillis()) - Long.parseLong(orderBean.getSubmittedDate());
            countDownTime = countDown > 0 ? countDown : (calendar.getTimeInMillis() + 24 * 60 * 60 * 1000) - Long.parseLong(orderBean.getSubmittedDate());
        }
        mc = new MyCount(countDownTime, 1000);
        mc.start();
//  采购员
        buyer2.setText(orderBean.getOwner());
//        状态
        company2.setText(orderBean.getCl().get(0).getCl_gongsi());
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

    @Override
    public void onClick(View v) {
        String orderId = orderBean.getOrderId();
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

                spotOrderPresenter.getPaymentInformationResult(orderId, _dynSessConf);

                break;
//            发票信息
            case R.id.bottom2:
                spotOrderPresenter.getInvoiceInformationResult(orderId, _dynSessConf);
//                Intent intent_fapiao = new Intent();
//                intent_fapiao.setClass(this, InvoiceInformationActivity.class);
//                intent_fapiao.putExtra("id",orderId);
//                intent_fapiao.putExtra("unique",_dynSessConf);
//                startActivity(intent_fapiao);
                break;
//            合同信息
            case R.id.bottom3:
                spotOrderPresenter.getContractInformationResult(orderId, _dynSessConf);

                break;
//            物流信息
            case R.id.bottom4:
//                intent.setClass(this, LogisticsInformationActivity.class);
//                startActivity(intent);
                Intent intent1=new Intent(SpotOrder.this,logisticsActivity.class);
                intent1.putExtra("orderId",orderId);
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
            count_down_tv.setText("finish");
            bottom_money.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            String time = format.format(millisUntilFinished - TimeZone.getDefault().getRawOffset());
            count_down_tv.setText("剩余支付时间：" + time);
        }
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
        spotOrderPresenter.getBankListFromService(order, orderId);
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
                            Toast.makeText(SpotOrder.this, "请选择的银行进行付款", Toast.LENGTH_SHORT).show();
                        }
                    }


                }
            });
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (list_pop.get(position).tv_Name.equals("昆仑银行")) {
                        Toast.makeText(SpotOrder.this, "昆仑银行需要进行网关支付", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(SpotOrder.this, Ok_Dialog.class);
                intent.putExtra("type", "现货订单");
                intent.putExtra("number", "这里填写订单号");
                startActivity(intent);
            } else if (state.equals("1")) {
                //部分订单成功
                Toast.makeText(SpotOrder.this, "部分订单成功,到订单详情界面查看", Toast.LENGTH_SHORT).show();
            } else if (state.equals("2")) {
                //全部失败
                Intent intent = new Intent(SpotOrder.this, Error_Dialog.class);
                intent.putExtra("number", "这里填写订单号");
                startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //    获取付款信息的内容
    @Override
    public void setPaymentInformationInActivity(String result) {

        Intent intent = new Intent();
        intent.setClass(this, PaymentInformationActivity.class);
        intent.putExtra("result",result);
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
            startActivity(intent);
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

        String orderIds = a;
        String platform = bankId;
        String _dynSessConf = zhifu_unique;
        Loding.show(this, "正在进行支付...", true, null);
        spotOrderPresenter.getPaymentInfoFromService(orderIds, platform, _dynSessConf);
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
