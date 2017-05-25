package com.silianchuangye.sumao.success.fragments.pagerThreeMVP.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.dialog.Error_Dialog;
import com.silianchuangye.sumao.success.dialog.Ok_Dialog;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.PayMentsOrderMVP.adapter.PayMentsOrderListViewAdapter;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.PayMentsOrderMVP.presenter.PaymentsOrderPresenter;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.Orders;

import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.PayMentsOrderMVP.bean.BankListBean;

import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.PayMentsOrderMVP.utils.TimeTextView;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.PayMentsOrderMVP.view.IPaymentsOrderView;
import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

//import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean.OrderIdList;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class PayOrderListActivity extends Activity implements View.OnClickListener, IPaymentsOrderView {

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
    private ExpandableListView lvdemo;
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
    private List<Orders.OrderIdListBean> orderList;
    private Callback.Cancelable cancelable;
    private String orderId;
    private boolean choose;
    private boolean click=false;
    private int position1;
    private TextView tv_Count;
    private TextView tv_money;
    private RelativeLayout layout_money;
    private String servicesFree;
    private Handler handler;
    private Timer timer;
    private TimerTask task;
    private String daojishi="";
    private String timerfor="";
    private Long aaa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_order_list);
        paymentsOrderPresenter = new PaymentsOrderPresenter(this);
        lvdemo = (ExpandableListView) findViewById(R.id.lbDem);
        lvdemo.setGroupIndicator(null);
        //去掉ListView之间的线
        lvdemo.setDivider(null);

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
        LinearLayout bottom = (LinearLayout) findViewById(R.id.bottom);
        bottom.setVisibility(View.GONE);
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("finish");
//        registerReceiver(broadcastReceiver, filter);
        addData();
        SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        timerfor=sp.getString("daojishishengyv","");

    }

    public void addData() {
        commit_order();
    }


//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        //结束任务
//        if (list_adapter != null)
//            list_adapter.cancelAllTimers();
//    }

    @Override
    protected void onStop() {
        super.onStop();
        if(timer!=null){
            timer.cancel();
        }
    }

    public void commit_order() {
        String fixOrCart = getIntent().getStringExtra("type");
        orderList = (List<Orders.OrderIdListBean>) getIntent().getSerializableExtra("fix");
        Log.d("倒计时的值",orderList.get(0).getCommerceItem().get(0).getProductName());
        list = new ArrayList<Map<String, Object>>();

        ExpanableListViewMyAdapter adapter=new ExpanableListViewMyAdapter(this);
        lvdemo.setAdapter(adapter);
        adapter.addData(orderList);
        Log.d("orderList的size",orderList.size()+"");
    //    Log.d("item的size",orderList.get)
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            lvdemo.expandGroup(i);

        }
        lvdemo.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        double total = 0;
        for (int i = 0; i < orderList.size(); i++) {
            double count = Double.parseDouble(orderList.get(i).getTotal());
            total += count;
        }
        tv_payments_order_money.setText(total + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.buy_immediately:
//                backgroundAlpha(0.5f);
//                Popupwindow();
//                break;
            case R.id.title_bar_white_back:
                finish();
                break;
        }
    }


    public void payImmediately(int position) {
        backgroundAlpha(0.5f);
        getinfo_Bank(position);
    }

    //设置背景透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    //    请求银行列表
    public void getinfo_Bank(int position) {
//        Bundle bundle = getIntent().getExtras();
//        list_id = bundle.getStringArrayList("id");

        orderId = orderList.get(position).getOrderId();
        // list_order.add(orderId);
        String order = null;
        Loding.show(this, "加载中...", false, null);
        paymentsOrderPresenter.getBankListFromService(order, orderId);
    }

    //    进行支付请求
    public void getzhifu(String bankId) {
        //TODO
        // StringBuffer stringBuffer = new StringBuffer();
        Log.d("订单编号的返回值",orderId.toString());

        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String zhifu_unique = sp.getString("unique", "");
        String orderIds = orderId;
        String platform = bankId;
        String _dynSessConf = zhifu_unique;
        Loding.show(this, "正在进行支付...", true, null);
        Log.d("订单编号",orderIds);
        Log.d("银行编号",platform);

        paymentsOrderPresenter.getPaymentInfoFromService(orderIds, platform, _dynSessConf);
    }

    @Override
    protected void onResume() {
        super.onResume();
      //  finish();
    }

    //    获取到银行列表
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
            tv_Count= (TextView) view.findViewById(R.id.tv_Count);
            tv_money= (TextView) view.findViewById(R.id.tv_money);
            layout_money= (RelativeLayout) view.findViewById(R.id.layout_money);
            TextView count_Price = (TextView) view.findViewById(R.id.count_Price);
            // final List<OpenAuction> list_pop=new ArrayList<OpenAuction>();

            Button bt = (Button) view.findViewById(R.id.btZhifu);
            bt.setText("立即支付");

            popupWindow.setTouchable(true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);

            if (result.contains("balance")) {
                Log.d("是否有服务费",result);
                list_pop = new ArrayList<OpenAuction>();
                Gson gson = new Gson();
                BankListBean bankListBean = gson.fromJson(result, BankListBean.class);
                List<BankListBean.BankResultBean> bankResults = bankListBean.getBankResult();
                count_price = bankListBean.getBalance();
                Log.d("货款总额的值",count_Price+"");
                tv_Count.setVisibility(View.VISIBLE);
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

                adapter1 = new PopupWindowAdaptrer(list_pop, PayOrderListActivity.this);
                lv.setAdapter(adapter1);

            } else {
                Toast.makeText(PayOrderListActivity.this, "登录已超时,请重新登录!", Toast.LENGTH_SHORT).show();

            }
            popupWindow.showAtLocation(buy_immediately, Gravity.BOTTOM, 0, 0);

            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    //popupWindow.dismiss();
                    backgroundAlpha(1f);
                }
            });
            count_Price.setText((Integer.parseInt(count_price)+Integer.parseInt(servicesFree))+"元");
//        立即支付
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // 判断是否选择银行
                    if (click && choose){
                        Log.d("用户选择了银行","是的");
                        //getzhifu(list_pop.get(position1).message);
                        Loding.show(PayOrderListActivity.this,"货款支付中...",false,null);
                        fukuan(list_pop.get(position1).message);
                        popupWindow.dismiss();

                    }else{//提示用户选择银行列表
//                        Log.d("用户选择了银行","不是的");
                        Toast.makeText(PayOrderListActivity.this, "请选择银行进行付款", Toast.LENGTH_SHORT).show();
                    }


                }
            });
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //if (list_pop.get(position).tv_Name.equals("昆仑银行")) {
                    //  Toast.makeText(PaymentsOrder.this, "昆仑银行需要进行网关支付", Toast.LENGTH_SHORT).show();
                    //} else {
                    position1=position;
                    click=true;
                    if (parent.getId() == lv.getId()) {
                        for (int i = 0; i < list_pop.size(); i++) {
                            Log.d("Listview的item", position + "");
                            if (i != position) {
                                list_pop.get(i).Flag = false;
                                choose=false;
                            }
                        }
                        list_pop.get(position).Flag = !list_pop.get(position).Flag;
                        if (list_pop.get(position).Flag){
                            choose=true;
                        }
                        adapter1.notifyDataSetChanged();
                    }

                }
            });
        } else {
            Toast.makeText(this, "该商品暂不支持银行付款", Toast.LENGTH_SHORT).show();
            backgroundAlpha(1f);
        }

    }

    public void fukuan(String bankId){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/payment/OrderPayment/orderPayment";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("orderIds",orderId);
        rp.addParameter("platform",bankId);
        SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        String unique=sp.getString("unique","");
        rp.addParameter("_dynSessConf",unique);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Loding.dis();

                if (result.contains("paymentStatus")) {
                    try {
                        JSONObject obj = new JSONObject(result);
                        String state = obj.getString("paymentStatus");
                        Log.d("state的值", state);
                        if (state.equals("0")) {
                            Intent intent = new Intent(PayOrderListActivity.this, Ok_Dialog.class);
                            intent.putExtra("type", "现货");
                            intent.putExtra("number", orderId);
                            startActivity(intent);
                            LogUtils.log("orderIds--->" + orderId);
                        }else{
                            Intent intent = new Intent(PayOrderListActivity.this, Error_Dialog.class);
                            intent.putExtra("number", orderId);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    // Log.d("支付结果的返回值", "不等于空");
                    Intent intent = new Intent(PayOrderListActivity.this, Error_Dialog.class);
                    intent.putExtra("number", orderId);
                    startActivity(intent);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
//                Loding.dis();
//                Intent intent = new Intent(PayOrderListActivity.this, Error_Dialog.class);
//                intent.putExtra("number", orderId);
//                startActivity(intent);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    //    获取支付后的信息
    @Override
    public void getPaymentResult(String result,String orderIds) {
        Log.d("支付结果的返回值", result);
        Loding.dis();

        if (result.contains("paymentStatus")) {
            //Log.d("支付结果的返回值", "等于空");
            try {
                JSONObject obj = new JSONObject(result);
                String state = obj.getString("paymentStatus");
                Log.d("state的值", state);
                if (state.equals("0")) {
                    Intent intent = new Intent(PayOrderListActivity.this, Ok_Dialog.class);
                    intent.putExtra("type", "现货");
                    intent.putExtra("number", orderId);
                    startActivity(intent);
                    LogUtils.log("orderIds--->" + orderId);
                }else{
                    Intent intent = new Intent(PayOrderListActivity.this, Error_Dialog.class);
                    intent.putExtra("number", orderId);
                    startActivity(intent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
           // Log.d("支付结果的返回值", "不等于空");
            Intent intent = new Intent(PayOrderListActivity.this, Error_Dialog.class);
            intent.putExtra("number", orderId);
            startActivity(intent);
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
    class ExpanableListViewMyAdapter extends BaseExpandableListAdapter {
        private List<Orders.OrderIdListBean> order;
        private Context context;
        private Boolean flag=true;
        private List<List<Orders.OrderIdListBean.CommerceItemBean>> childBeans;
        private List<Orders.OrderIdListBean> groupBeans;

        public ExpanableListViewMyAdapter(Context context){
            this.context=context;
            this.order=new ArrayList<>();
            //childBeans=new ArrayList<>();
            this.childBeans=new ArrayList<>();
            //childBeans.add(groupBeans.);
            groupBeans=new ArrayList<>();
            //this.groupBeans=groupBeans;

        }

        public void addData(List<Orders.OrderIdListBean> list) {
            order.addAll(list);
            for (int i = 0; i < order.size(); i++) {

                List<Orders.OrderIdListBean.CommerceItemBean> childBeanList = new ArrayList<>();
                for (int j = 0; j < order.get(i).getCommerceItem().size(); j++) {
                    Orders.OrderIdListBean.CommerceItemBean childBean = new Orders.OrderIdListBean.CommerceItemBean();

                    childBean.setProductName(order.get(i).getCommerceItem().get(j).getProductName());
                    childBean.setParentCategories(order.get(i).getCommerceItem().get(j).getParentCategories());
                    childBean.setSalePrice(order.get(i).getCommerceItem().get(j).getSalePrice());
                    childBean.setGradeNumber(order.get(i).getCommerceItem().get(j).getGradeNumber());
                    childBean.setQuantity(order.get(i).getCommerceItem().get(j).getQuantity());
                    childBean.setManufacturer(order.get(i).getCommerceItem().get(j).getManufacturer());
                    childBean.setAmount(order.get(i).getCommerceItem().get(j).getAmount());
                    childBean.setWarehouse(order.get(i).getCommerceItem().get(j).getWarehouse());
                    childBean.setSalesCompanyDisplayName(order.get(i).getCommerceItem().get(j).getSalesCompanyDisplayName());

                    childBeanList.add(childBean);

                }
                childBeans.add(childBeanList);
                //MyAdapter.GroupBean groupBean = new MyAdapter.GroupBean();
                Orders.OrderIdListBean orderIdListBean=new Orders.OrderIdListBean();
//            orderIdListBean.setName(order.get(i).getOwner());
//            groupBean.setId(order.get(i).getOrderId());
//            groupBean.setStates(order.get(i).getState());
//            groupBean.setPrice(order.get(i).getOwner());
                orderIdListBean.setOrderId(order.get(i).getOrderId());
                orderIdListBean.setTotal(order.get(i).getTotal());
                orderIdListBean.setRemainingTime(order.get(i).getRemainingTime());
                groupBeans.add(orderIdListBean);
            }
        }

        //获取到子项数据，arg0组的序号，arg1子项序号
        public Object getChild(int arg0, int arg1) {
            return childBeans.get(arg0).get(arg1);
        }

        //获取到子项序号，arg0组的序号，arg1子项序号
        public long getChildId(int arg0, int arg1) {
            return arg1;
        }

        //获取到arg0组中子项个数
        public int getChildrenCount(int arg0) {
            return childBeans.get(arg0).size();
        }

        //获取到arg0组的数据
        public Object getGroup(int arg0) {
            return groupBeans.get(arg0);
        }

        //获取到一共多少组
        public int getGroupCount() {

            return groupBeans.size();
        }

        //获取到组的序号
        public long getGroupId(int arg0) {
            return arg0;
        }

        //获取到组布局的效果
        public View getGroupView(final int groupposition, boolean childPosition, View arg2, ViewGroup arg3) {
            final ViewHolder viewHolder;
            if (arg2 == null) {
                viewHolder = new ViewHolder();
                arg2=View.inflate(context,R.layout.paygroupitem,null);
                viewHolder.product_order_number= (TextView) arg2.findViewById(R.id.product_order_number);
                viewHolder.money_number= (TextView) arg2.findViewById(R.id.money_number);
                viewHolder.residual_time= (TimeTextView) arg2.findViewById(R.id.residual_time);
                viewHolder.bt_zhifu= (Button) arg2.findViewById(R.id.bt_zhifu);
                viewHolder.product_order_number.setText("订单编号:" +groupBeans.get(groupposition).getOrderId());
                viewHolder.money_number.setText(groupBeans.get(groupposition).getTotal());
                //viewHolder.residual_time.setText(groupBeans.get(groupposition).getRemainingTime());
                if (flag){
                    // Log.d("要付的值",groupBeans.get(groupposition).getSpecialFrom()+"");
                    viewHolder.residual_time.setTimes(groupBeans.get(groupposition).getSpecialFrom());

                    if (!viewHolder.residual_time.isRun()) {
                        viewHolder.residual_time.run();
                    }
                    arg2.setTag(viewHolder);
                    viewHolder.residual_time.setTag(groupBeans.get(groupposition));


                }
            }else {
                viewHolder = ((ViewHolder) arg2.getTag());
                viewHolder.residual_time.setTag(groupBeans.get(groupposition));
            }
            viewHolder.bt_zhifu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //弹出银行
                    payImmediately(groupposition);
                }
            });

            return arg2;
        }

        //获取到子项布局效果
        public View getChildView(int groupPosition, int childPosition, boolean arg2, View arg3,
                                 ViewGroup arg4) {
            Log.d("进来了子布局","进来了子布局");

            View view_arg3=View.inflate(context,R.layout.payorderchild,null);
            TextView productName= (TextView) view_arg3.findViewById(R.id.productName);
            //品种
            TextView surplus_amount_et = ((TextView) view_arg3.findViewById(R.id.surplus_amount_et));
            //产品牌号
            TextView purchase_quantity_et = ((TextView) view_arg3.findViewById(R.id.purchase_quantity_et));
            //生产企业
            TextView min_variable_et = ((TextView) view_arg3.findViewById(R.id.min_variable_et));
            //仓库
            TextView delivery_time_et = ((TextView) view_arg3.findViewById(R.id.delivery_time_et));
            //产品单价
            TextView classification_pre_sale_et = ((TextView) view_arg3.findViewById(R.id.classification_pre_sale_et));
            //数量
            TextView warehouse_et = ((TextView) view_arg3.findViewById(R.id.warehouse_et));
            //产品总价
            TextView region_et = ((TextView) view_arg3.findViewById(R.id.region_et));
            //公司
            TextView company_et = ((TextView) view_arg3.findViewById(R.id.company_et));
            Log.d("ssss",childBeans.get(groupPosition).get(childPosition).getProductName());
            productName.setText(childBeans.get(groupPosition).get(childPosition).getProductName());
            //  productName.setText(childBeans.get(groupPosition).get(childPosition).getProductName());
            //品种
            surplus_amount_et.setText(childBeans.get(groupPosition).get(childPosition).getParentCategories());
            //产品牌号
            purchase_quantity_et.setText(childBeans.get(groupPosition).get(childPosition).getGradeNumber());
            min_variable_et.setText(childBeans.get(groupPosition).get(childPosition).getManufacturer());
            //仓库
            delivery_time_et.setText(childBeans.get(groupPosition).get(childPosition).getWarehouse());
            //产品单价
            classification_pre_sale_et.setText(childBeans.get(groupPosition).get(childPosition).getSalePrice());
            //数量
            warehouse_et.setText(childBeans.get(groupPosition).get(childPosition).getQuantity());
            //产品总价
            region_et.setText(childBeans.get(groupPosition).get(childPosition).getAmount());
            //公司
            company_et.setText(childBeans.get(groupPosition).get(childPosition).getSalesCompanyDisplayName());


            return view_arg3;
        }

        public boolean hasStableIds() {
            return false;
        }


        public boolean isChildSelectable(int arg0, int arg1) {
            return true;
        }

        class ViewHolder{
            TextView product_order_number;
            TextView money_number;
            TimeTextView residual_time;
            Button bt_zhifu;

        }


    }

}

