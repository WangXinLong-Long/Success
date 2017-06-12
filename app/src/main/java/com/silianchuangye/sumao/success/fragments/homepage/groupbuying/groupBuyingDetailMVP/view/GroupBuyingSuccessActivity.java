package com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.silianchuangye.sumao.success.HX.Constant;
import com.silianchuangye.sumao.success.HX.ui.LoginActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.BuyAdapter;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.dialog.Error_Dialog;
import com.silianchuangye.sumao.success.dialog.Ok_Dialog;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;
import com.silianchuangye.sumao.success.fragments.homepage.auction.VesselThreeActivity;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.presenter.GroupBuyingPresenter;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.view.IGroupBuyingView;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.JoinActivity;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.RuleActivity;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.bean.GroupBuyingDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.presenter.GroupBuyingDetailPresenter;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleBondBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.SpotOrder;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;
import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;



public class GroupBuyingSuccessActivity extends AppCompatActivity implements IGroupBuyingDetailView, View.OnClickListener,IGroupBuyingView,OnLayoutChangeListener {
    private ListView lvDemo;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> list;
    private TextView tv_success;


    private String result;
    private TextView name;
    private TextView price;
    private TextView count;
    private TextView qigou;
    private TextView bianjia;
    private TextView bianliang;
    private TextView cangku;
    private TextView way;
    private TextView comm;
    private TextView type;
    private TextView add;
    private RelativeLayout jian;
    private RelativeLayout jia;
    private EditText number;
    private TextView count_price;
    private String path;
    private ArrayList<GoodsInStockDetailBean.ClAttributeBean> cl_attribute = new ArrayList<GoodsInStockDetailBean.ClAttributeBean>();
    private String bili, jiezhishijian, can_gku, xiangxi, kaishishijian, jieshushijian, tuan_start, tuan_end;
    private LinearLayout linear_tuangou_time, linear_tuagou_line;
    private RelativeLayout relative_tuangou_end;
    private Button btn_tuangou_add;
    private PopupWindow popupWindow;
    private View popView;
    private TextView tv_pop_price, tv_pop_num, tv_pop_fenlei, tv_pop_start_num,
            tv_pop_type, tv_pop_small_null, tv_pop_diqu, tv_pop_cangku,
            tv_pop_gongsi, tv_pop_add, tv_pop_sub;
    private EditText edt_pop_num;
    private Button btn_pop_ok;
    private List<OpenAuction> list1;
    private PopupWindowAdaptrer popAdapter;
    private String strbianliang, strprice = "";//
    private ImageView img;
    private TextView tv_pro_now;
    private ProgressBar pbDemo;
    private MyCount mc;
    TextView tv_all_num, tv_tuangou_time, tv_tuangou_line;
    RelativeLayout relative_tuangou, layout_not_action, layout_Bottom;
    private String state;
    private LinearLayout layoutContent_auction;
    private String cl_gongsiId;
    private GroupBuyingDetailPresenter groupBuyingDetailPresenter;
    private PopupWindow paymentSecurityPopupWindow;
    private TextView paidDeposit;
    private EditText amountOfMarginRequired;
    private PopupWindowAdaptrer bankListAdapter1;
    private double additionalMarginAmount;
    private String additionalMarginAmountString;
    //    购买数量
    private EditText tv_item_cart_buy_num;
    private TextView img_item_cart_buy_sub;
    private TextView img_item_cart_buy_add;
    private TextView tv_item_cart_all_price;
    //    已支付的保证金金额
    private String zhifujine = "0";
    private String cl_baozhj;
    private String percent;
    private String cl_id;
    private TextView group_amount_et;
    private GroupBuyingDetailBean groupBuyingDetailBean;
    private LinearLayout remarks_rl;
    private String result2;
    private String securedOfSection;
    private TextView tvResult;
    private String shulian;
    private ListView gvDemo;
    private List<Map<String, Object>> list_gridview = new ArrayList<Map<String, Object>>();
    private LinearLayout line_bottom, line_top, line_content;
    private String unique;
    private GroupBuyingPresenter groupBuyingPresenter;
    private String productId;
    private String login;
    private String Inforesult;
    private Handler handler;
    private TimerTask task;
    private Timer timer;
    private String groupPercent;
    private RelativeLayout parent;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;
    private String sumAddQty;
    private boolean istrue=true;
   // private  Double shengyvNumber;
    private String timeinfo;
    private TextView tv_failed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groupBuyingDetailPresenter = new GroupBuyingDetailPresenter(this);
        groupBuyingPresenter = new GroupBuyingPresenter(this);
        setContentView(R.layout.activity_group_buying_success);
        Bundle bundle = getIntent().getExtras();
        productId=bundle.getString("productId");
        Log.d("成团状态", "" + result2);
        Log.d("result的值",""+result);

        init();
        new Thread(){
            @Override
            public void run() {
                super.run();
                info();
            }
        }.start();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        Log.e("计时器","计时器");
                        tuangouinfo();
                        break;
                    default:
                        break;
                }
            }
        };
       // new Thread(new TitleThread()).start();
        addData();
        event();

    }
    public void info(){
        groupBuyingPresenter.getGroupBuyingDetailResult(productId,"","");
    }

    public void init() {
        tv_failed= (TextView) findViewById(R.id.tv_failed);
        parent= (RelativeLayout) findViewById(R.id.parent);
        parent.addOnLayoutChangeListener(this);
        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight/3;
        line_top = (LinearLayout) findViewById(R.id.line_top);
        line_bottom = (LinearLayout) findViewById(R.id.line_bottom);
        line_content = (LinearLayout) findViewById(R.id.line_content);
        gvDemo = (ListView) findViewById(R.id.Lv_infoSeccess);
        layout_Bottom = (RelativeLayout) findViewById(R.id.layout_Bottom);
        layout_not_action = (RelativeLayout) findViewById(R.id.layout_not_action);
        tvResult = (TextView) findViewById(R.id.tv_Result);
        //relative_tuangou= (RelativeLayout) findViewById(R.id.relative_tuangou_end);
        pbDemo = (ProgressBar) findViewById(R.id.pbDemo);
        pbDemo.setMax(100);
        img = (ImageView) findViewById(R.id.ivBack);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        relative_tuangou_end = (RelativeLayout) findViewById(R.id.relative_tuangou_end);

        lvDemo = (ListView) findViewById(R.id.lv_demo);
        list = new ArrayList<Map<String, Object>>();
        tv_failed = (TextView) findViewById(R.id.tv_failed);
        tv_success = (TextView) findViewById(R.id.tv_success);
        layout_Bottom = (RelativeLayout) findViewById(R.id.layout_Bottom);

        linear_tuangou_time = (LinearLayout) findViewById(R.id.linear_tuangou_time);
        //linear_tuagou_line = (LinearLayout) findViewById(R.id.linear_tuagou_line);
        tv_pro_now = (TextView) findViewById(R.id.tv_pro_now);//当前显示的进度
        tv_all_num = (TextView) findViewById(R.id.tv_all_num);
        tv_tuangou_time = (TextView) findViewById(R.id.tv_tuangou_time);
        btn_tuangou_add = (Button) findViewById(R.id.btn_tuangou_add);
        relative_tuangou = (RelativeLayout) findViewById(R.id.relative_tuangou);
        tv_tuangou_line = (TextView) findViewById(R.id.tv_tuangou_line);

        name = (TextView) findViewById(R.id.tvName_auction);
//        团购价格
        price = (TextView) findViewById(R.id.tvPrice_auction);
//        团购总量
        count = (TextView) findViewById(R.id.surplus_amount_et);
//        起购量
        qigou = (TextView) findViewById(R.id.purchase_quantity_et);
//        最小变量单位
        bianliang = (TextView) findViewById(R.id.min_variable_et);
//        总价
        tv_item_cart_all_price = (TextView) findViewById(R.id.tv_item_cart_all_price);
//        成团量
        group_amount_et = ((TextView) findViewById(R.id.group_amount_et));
//        备注
        remarks_rl = ((LinearLayout) findViewById(R.id.remarks_rl));
        remarks_rl.setVisibility(View.GONE);
        cangku = (TextView) findViewById(R.id.warehouse_address_et);
        way = (TextView) findViewById(R.id.delivery_mode_et);
        type = (TextView) findViewById(R.id.classification_pre_sale_et);
//        bianjia= (TextView) findViewById(R.id.warehouse_et);
        add = (TextView) findViewById(R.id.region_et);
        comm = (TextView) findViewById(R.id.company_et);
      //  number = (EditText) findViewById(R.id.ed_shuzhi_number);

        layoutContent_auction = ((LinearLayout) findViewById(R.id.layoutContent_auction));
        layoutContent_auction.setOnClickListener(this);
        btn_tuangou_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (unique.equals("false")||unique.equals("")) {
                    tuichu();
                    Intent intent = new Intent(GroupBuyingSuccessActivity.this, LoginUserActivity.class);
                    intent.putExtra("roles", "buyer");
                    intent.putExtra("position", 102);
                    startActivity(intent);
                    //startActivityForResult(intent, 102);
                } else {
                    //进行用户是否有权限购买判断
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            booleanZizhi();
                        }
                    }.start();
                    //弹出popwindow
                }

            }
        });
        //        购买数量
        tv_item_cart_buy_num = ((EditText) findViewById(R.id.tv_item_cart_buy_num));

        //getinfo(result);
//        减
        img_item_cart_buy_sub = ((TextView) findViewById(R.id.img_item_cart_buy_sub));
        img_item_cart_buy_sub.setOnClickListener(this);
//        增加
        img_item_cart_buy_add = ((TextView) findViewById(R.id.img_item_cart_buy_add));
        img_item_cart_buy_add.setOnClickListener(this);

    }


    public void tuichu(){
    RequestParams  rp=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/logout");
    SharedPreferences sp=getSharedPreferences("sumao",0);
    //SharedPreferences sp1=g
    String unique=sp.getString("unique","");
    rp.addParameter("_dynSessConf",unique);
    Log.d("退出时的唯一标识",unique);
    x.http().post(rp, new Callback.CommonCallback<String>() {
        @Override
        public void onSuccess(String result) {
            SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.clear();
            editor.commit();

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
    public void booleanZizhi(){
        String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/userprofiling/PurchaseJurisdictionCheckActor/PurchaseResult";
        RequestParams requestParams=new RequestParams(url);
        requestParams.addParameter("productId",cl_id);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("用户是否有参与团购的权限",result);
                if (result.contains("0")){
                    showpop();
                }else if (result.contains("1")){
                    Toast.makeText(GroupBuyingSuccessActivity.this, "该用户无上游资源方购买资质", Toast.LENGTH_SHORT).show();

                }else if (result.contains("2")){
                    Toast.makeText(GroupBuyingSuccessActivity.this, "黑名单不能购买", Toast.LENGTH_SHORT).show();

                }else if (result.contains("3")){
                    Toast.makeText(GroupBuyingSuccessActivity.this, "用户没有登录", Toast.LENGTH_SHORT).show();
                    tuichu();
                    Intent intent = new Intent(GroupBuyingSuccessActivity.this, LoginUserActivity.class);
                    intent.putExtra("roles", "buyer");
                    intent.putExtra("position", 102);
                    startActivity(intent);
                }else if (result.contains("4")){
                    Toast.makeText(GroupBuyingSuccessActivity.this, "没有在购买期限内，不能购买", Toast.LENGTH_SHORT).show();

                }else if (result.contains("5")){
                    Toast.makeText(GroupBuyingSuccessActivity.this, "csc审核没有通过", Toast.LENGTH_SHORT).show();

                }else if (result.contains("6")){
                    Toast.makeText(GroupBuyingSuccessActivity.this, "企业财务员无权购买产品", Toast.LENGTH_SHORT).show();

                }else if(result.contains("7")){
                    Toast.makeText(GroupBuyingSuccessActivity.this, "库存小于购买量", Toast.LENGTH_SHORT).show();

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
    //判断有没有关闭市场
    public void booleanOpenorclose() {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/colsedChecker";
        RequestParams rp = new RequestParams(url);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                if (result.contains("true")) {
                    tuangou_state();
                } else {
                    layout_not_action.setVisibility(View.GONE);
                    layout_Bottom.setVisibility(View.GONE);
                    relative_tuangou_end.setVisibility(View.VISIBLE);
                    tvResult.setText("已闭市");
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

    public void event() {
        shulian = tv_item_cart_buy_num.getText().toString();
        lvDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(GroupBuyingSuccessActivity.this, RuleActivity.class);
                    intent.putExtra("bili", bili);
                    intent.putExtra("jiezhi", "");
                    intent.putExtra("cangku", can_gku);
                    intent.putExtra("xiangxi", xiangxi);
                    intent.putExtra("kaishi", kaishishijian);
                    intent.putExtra("jieshu", jieshushijian);
                    intent.putExtra("tuan_start", tuan_start);
                    intent.putExtra("tuan_end", tuan_end);
                    intent.putExtra("state", state);
                    startActivity(intent);
                } else if (position == 1) {
                    // groupBuyingDetailPresenter.getTheRecordResult(result);

                    Intent intent = new Intent(GroupBuyingSuccessActivity.this, JoinActivity.class);
                    //intent.putExtra("result",  groupBuyingDetailBean);
                    intent.putExtra("Id", cl_id);
                    startActivity(intent);

                } else if (position == 2) {
                    Intent intent = new Intent(GroupBuyingSuccessActivity.this, VesselThreeActivity.class);
                    intent.putExtra("title", "团购");
                    intent.putExtra("auctionDetailBean", groupBuyingDetailBean);
                    intent.putExtra("contract", path);
                    startActivity(intent);

                }
            }
        });


    }

    public void chenggong() {
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique = sp.getString("unique", "");
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/groupResult";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("productId", cl_id);
        rp.addParameter("_dynSessConf", unique);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                if (result.contains("amount")) {
                    line_bottom.setVisibility(View.VISIBLE);
                    line_top.setVisibility(View.VISIBLE);
                    gvDemo.setVisibility(View.VISIBLE);
                    line_content.setVisibility(View.VISIBLE);
                    try {
                        JSONObject object = new JSONObject(result);
                        String Result = object.getString("Result");
                        Log.d("result的值", Result);
                        JSONArray jsonArray = new JSONArray(Result);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String id = jsonObject.getString("id");
                            String amount = jsonObject.getString("amount");
                            String result1 = jsonObject.getString("result");
                            String addPrice = jsonObject.getString("addPrice");
                            String addQty = jsonObject.getString("addQty");
                            Log.d("返回的值", id + amount + result1 + addPrice + addQty);
                            if (result1.equals("win")) {
                                Map<String, Object> map = new Hashtable<String, Object>();
                                map.put("id", id);
                                map.put("amount", amount);
                                map.put("addPrice", addPrice);
                                map.put("addQty", addQty);
                                list_gridview.add(map);
                            }
                            Log.d("循環list", list_gridview.size() + "");
                        }
                        Log.d("list的长度", list_gridview.size() + "");

                        MyAdapter adapter_grid=new MyAdapter(list_gridview,GroupBuyingSuccessActivity.this);
                        gvDemo.setAdapter(adapter_grid);
                        adapter_grid.notifyDataSetChanged();

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
    class MyAdapter extends BaseAdapter{
        private List<Map<String,Object>> list1;
        private Context context;
        private int number;

        public MyAdapter(List<Map<String,Object>> list1,Context context){
            this.list1=list1;
            this.context=context;
        }

        @Override
        public int getCount() {
            return list1.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            //number=position;
            View view=View.inflate(context,R.layout.item_gridview,null);
            TextView tv_order_id= (TextView) view.findViewById(R.id.tv_order_id);
            TextView tv_price= (TextView) view.findViewById(R.id.tv_price);
            TextView tv_number= (TextView) view.findViewById(R.id.tv_number);
            TextView count= (TextView) view.findViewById(R.id.count);
            Button bt_search= (Button) view.findViewById(R.id.bt_search);

            tv_order_id.setText(list1.get(position).get("id").toString());
            BigDecimal b2 = new BigDecimal(list1.get(position).get("addPrice").toString());
            double f2 = b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            tv_price.setText(f2+"");
//            BigDecimal b1 = new BigDecimal(list1.get(position).get("addPrice").toString());
//            double f1 = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            tv_number.setText(list1.get(position).get("addQty").toString());
            BigDecimal b1 = new BigDecimal(list1.get(position).get("amount").toString());
            double f1 = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            count.setText(f1+"");
            bt_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,SpotOrder.class);
                    intent.putExtra("id",list1.get(position).get("id").toString());
                    context.startActivity(intent);
                }
            });
            return view;
        }
    }

    public void addData() {
      //  Log.d("state的值",state);
        SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        String Stutes=sp.getString("state","");
        Log.d("stutes的值",Stutes);
        if (Stutes.equals("0")) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", "交易规则");
            list.add(map);
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("name", "合同详情、产品参数");
            list.add(map2);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", "交易规则");
            list.add(map);
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("name", "我的参团记录");
            list.add(map1);
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("name", "合同详情、产品参数");
            list.add(map2);
        }

        adapter = new SimpleAdapter(this, list, R.layout.item_open_auction,
                new String[]{"name"},
                new int[]{R.id.tvRule_auction});
        lvDemo.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    public void getinfo(String result) {

        Log.d("团购详情的result为了状态",result);
        Gson gson = new Gson();
        groupBuyingDetailBean = gson.fromJson(result, GroupBuyingDetailBean.class);
        cl_baozhj = groupBuyingDetailBean.getCl_baozhj();
        Log.d("保证金的值",cl_baozhj);
        cl_id = groupBuyingDetailBean.getCl_id();
        Log.d("商品的编号",cl_id);
        NumberFormat nf = NumberFormat.getPercentInstance();
        Number number = null;

        try {
            number = nf.parse(cl_baozhj);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        percent = String.format("%.4f", Double.parseDouble(number.toString()));


            name.setText(groupBuyingDetailBean.getCl_mingcheng());
            Log.d("name的值",name.getText().toString());


            strprice = groupBuyingDetailBean.getCl_jine();
            price.setText(strprice);
            state=groupBuyingDetailBean.getSection().toString();
        try {
            long time = 0l;
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");

            Date date1 =  formatter.parse(groupBuyingDetailBean.getUtilDate());
            Date date=new Date();
            long nowTime = date.getTime();
            time = date1.getTime() - nowTime+1000;
            Log.d("time的值",time+"");
            if (time>0) {

                if (state.equals("1")){
                     mc = new MyCount(time, 1000);
                     mc.start();
                }else if (state.equals("0")){
                    new MyCount1(time,1000).start();
                }else{
                    tv_success.setText("团购结束");
                    if(timer!=null){
                        timer.cancel();
                    }
                }
            }else{
                //chenggong
                tv_success.setText("团购结束");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("state的值tesy",state);
            //();
        SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("state",state);
        editor.commit();






            if (result.contains("cl_zongliang")) {
                count.setText(groupBuyingDetailBean.getCl_zongliang());
            } else {
                count.setText("");
            }
            cl_gongsiId = groupBuyingDetailBean.getCl_gongsiId();
            Log.d("公司的Id",cl_gongsiId);


            strbianliang = groupBuyingDetailBean.getCl_xbianliang();
            Log.d("最小变量单位",strbianliang);
            bianliang.setText(strbianliang);
            securedOfSection = groupBuyingDetailBean.getSecuredOfSection();
            Log.d("securedOfSection的值",securedOfSection);
            group_amount_et.setText(groupBuyingDetailBean.getCl_groupQuantity());
            Log.d("输入矿的的值",groupBuyingDetailBean.getCl_groupQuantity());
            qigou.setText(groupBuyingDetailBean.getCl_qigou());
            Log.d("qigouliang的值",groupBuyingDetailBean.getCl_qigou());
            tv_item_cart_buy_num.setText(qigou.getText().toString());
            tv_item_cart_all_price.setText(String.format("%.2f", Double.parseDouble(tv_item_cart_buy_num.getText().toString()) * Double.parseDouble(price.getText().toString())));

            cangku.setText(groupBuyingDetailBean.getCl_cangku());
            Log.d("仓库的值",groupBuyingDetailBean.getCl_cangku());
            String fangshi = groupBuyingDetailBean.getCl_fangshi().toString();
            Log.d("方式的值",groupBuyingDetailBean.getCl_fangshi().toString());
            String str = fangshi.substring(1, fangshi.length() - 1);
            Log.d("提货方式的值",str);
            way.setText(str);
            type.setText(groupBuyingDetailBean.getCl_fenlei());
            Log.d("分类的值",groupBuyingDetailBean.getCl_fenlei());
//                            String bian=obj.getString("cl_xbianjia");
//                            bianjia.setText(obj.getString("cl_xbianjia"));//最小变价
            add.setText(groupBuyingDetailBean.getCl_diqu());
            comm.setText(groupBuyingDetailBean.getCl_gongsi());
//
            List<GoodsInStockDetailBean.ClAttributeBean> cl_attribute = groupBuyingDetailBean.getCl_attribute();
            for (int i = 0; i < cl_attribute.size(); i++) {
                GoodsInStockDetailBean.ClAttributeBean attribute = cl_attribute.get(i);
                attribute.setAttrName(attribute.getAttrName());
                attribute.setAttrValue(attribute.getAttrValue());
                this.cl_attribute.add(attribute);
            }
            path = groupBuyingDetailBean.getContract();
            bili = groupBuyingDetailBean.getCl_baozhj();
            if (groupBuyingDetailBean.getDepositDeadline() == null) {
                jiezhishijian = "";
            } else {
                jiezhishijian = groupBuyingDetailBean.getDepositDeadline();
            }

            can_gku = groupBuyingDetailBean.getCl_cangku();
            if (result.contains("addressDetail")) {
                xiangxi = groupBuyingDetailBean.getAddressDetail();
            } else {
                xiangxi = "";
            }
            kaishishijian = groupBuyingDetailBean.getCl_shijian();
            jieshushijian = groupBuyingDetailBean.getCl_shijianend();
            tuan_start = groupBuyingDetailBean.getGroupStartDate();
            tuan_end = groupBuyingDetailBean.getGroupEndDate();
             sumAddQty = groupBuyingDetailBean.getSumAddQty();//数量
            SharedPreferences sp1=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor2=sp1.edit();
            editor2.putString("sumAddQty",sumAddQty);
            editor2.commit();
             groupPercent = groupBuyingDetailBean.getGroupPercent();//比例

            int i = (int) (Double.valueOf(groupPercent) * 100);
            tv_pro_now.setText("当前成团量为 : " + i + "%");
            tv_all_num.setText(groupBuyingDetailBean.getCl_zongliang() + "t");
            pbDemo.setProgress(i);

            login=groupBuyingDetailBean.getSecuredPage();
            booleanOpenorclose();





    }


    public void tuangou_state(){
        Log.d("login的值",login);

        if (login.equals("unlogin")){
            Log.d("没有登录","没有登录");
            //没有登陆
            Inforesult=groupBuyingDetailBean.getSecuredOfSection();
            Log.d("Inforesult的值",Inforesult);
            if (Inforesult.equals("running")){
                layout_Bottom.setVisibility(View.VISIBLE);
                tv_tuangou_time.setVisibility(View.GONE);
                tv_tuangou_line.setVisibility(View.GONE);
                linear_tuangou_time.setVisibility(View.VISIBLE);
                //tv_tuangou_time.setVisibility(View.VISIBLE);
                tv_success.setVisibility(View.VISIBLE);
               // tv_success.setText("剩余团购时间:"+timeinfo);
                tv_failed.setVisibility(View.GONE);
                relative_tuangou.setVisibility(View.VISIBLE);


            }else if (Inforesult.equals("unGroup")){
                tv_tuangou_line.setVisibility(View.VISIBLE);
                relative_tuangou.setVisibility(View.GONE);
                tv_success.setVisibility(View.GONE);
                tv_failed.setVisibility(View.GONE);
                layout_Bottom.setVisibility(View.INVISIBLE);
                relative_tuangou_end.setVisibility(View.VISIBLE);
                linear_tuangou_time.setVisibility(View.GONE);
                // linear_tuagou_line.setVisibility(View.GONE);
                tv_tuangou_line.setVisibility(View.GONE);
                tv_tuangou_time.setVisibility(View.GONE);
                tvResult.setText("未成团，请关注其他团购");
            }else if (Inforesult.equals("calc")){
                //团购结果结算中
                tv_tuangou_line.setVisibility(View.VISIBLE);
                relative_tuangou.setVisibility(View.GONE);
                tv_success.setVisibility(View.GONE);
                tv_failed.setVisibility(View.GONE);
                layout_Bottom.setVisibility(View.INVISIBLE);
                relative_tuangou_end.setVisibility(View.VISIBLE);
                linear_tuangou_time.setVisibility(View.GONE);
                // linear_tuagou_line.setVisibility(View.GONE);
                tv_tuangou_line.setVisibility(View.GONE);
                tv_tuangou_time.setVisibility(View.GONE);
                tvResult.setText("团购结果结算中");
                if (timer!=null){
                    timer.cancel();
                }
                info();

            }else if (Inforesult.equals("saledOff")){
                tv_tuangou_line.setVisibility(View.VISIBLE);
                relative_tuangou.setVisibility(View.GONE);
                tv_success.setVisibility(View.GONE);
                tv_failed.setVisibility(View.GONE);
                layout_Bottom.setVisibility(View.INVISIBLE);
                relative_tuangou_end.setVisibility(View.VISIBLE);
                linear_tuangou_time.setVisibility(View.GONE);
                // linear_tuagou_line.setVisibility(View.GONE);
                tv_tuangou_line.setVisibility(View.GONE);
                tv_tuangou_time.setVisibility(View.GONE);
                tvResult.setText("团购结束！请关注其他团购");
            }else if (Inforesult.equals("end")){
                tv_tuangou_line.setVisibility(View.VISIBLE);
                relative_tuangou.setVisibility(View.GONE);
                tv_success.setVisibility(View.GONE);
                tv_failed.setVisibility(View.GONE);
                layout_Bottom.setVisibility(View.INVISIBLE);
                relative_tuangou_end.setVisibility(View.VISIBLE);
                linear_tuangou_time.setVisibility(View.GONE);
                // linear_tuagou_line.setVisibility(View.GONE);
                tv_tuangou_line.setVisibility(View.GONE);
                tv_tuangou_time.setVisibility(View.GONE);
                tvResult.setText("团购已结束");
                if (timer!=null){
                    timer.cancel();
                }
            }else if (Inforesult.equals("notStarted")){
                //没有开始状态
                layout_Bottom.setVisibility(View.GONE);
                tv_tuangou_time.setVisibility(View.GONE);
                tv_tuangou_line.setVisibility(View.GONE);
                linear_tuangou_time.setVisibility(View.VISIBLE);
                //tv_tuangou_time.setVisibility(View.VISIBLE);

                tv_success.setVisibility(View.GONE);
                tv_failed.setVisibility(View.VISIBLE);
                //tv_success.setText("距离团购开始时间还有:"+timeinfo);
                relative_tuangou.setVisibility(View.VISIBLE);

            }else if (Inforesult.equals("productOff")){
                tv_tuangou_line.setVisibility(View.VISIBLE);
                relative_tuangou.setVisibility(View.GONE);
                tv_success.setVisibility(View.GONE);
                tv_failed.setVisibility(View.GONE);
                layout_Bottom.setVisibility(View.INVISIBLE);
                relative_tuangou_end.setVisibility(View.VISIBLE);
                linear_tuangou_time.setVisibility(View.GONE);
                // linear_tuagou_line.setVisibility(View.GONE);
                tv_tuangou_line.setVisibility(View.GONE);
                tv_tuangou_time.setVisibility(View.GONE);
                tvResult.setText("商品已下架");
            }

        }else if(login.equals("productOff")){
            //下架
            tv_tuangou_line.setVisibility(View.VISIBLE);
            relative_tuangou.setVisibility(View.GONE);
            tv_success.setVisibility(View.GONE);
            tv_failed.setVisibility(View.GONE);
            layout_Bottom.setVisibility(View.INVISIBLE);
            relative_tuangou_end.setVisibility(View.VISIBLE);
            linear_tuangou_time.setVisibility(View.GONE);
            // linear_tuagou_line.setVisibility(View.GONE);
            tv_tuangou_line.setVisibility(View.GONE);
            tv_tuangou_time.setVisibility(View.GONE);
            tvResult.setText("团购结束");
            if(timer!=null){
                timer.cancel();
            }

        }else if (login.equals("running")){
            //进行中
            layout_Bottom.setVisibility(View.VISIBLE);
            tv_tuangou_time.setVisibility(View.GONE);
            tv_tuangou_line.setVisibility(View.GONE);
            linear_tuangou_time.setVisibility(View.VISIBLE);
            //tv_tuangou_time.setVisibility(View.VISIBLE);
            tv_success.setVisibility(View.VISIBLE );
            relative_tuangou.setVisibility(View.VISIBLE);
            tv_failed.setVisibility(View.GONE);


        }else if (login.equals("saledOff")){
            tv_tuangou_line.setVisibility(View.VISIBLE);
            relative_tuangou.setVisibility(View.GONE);
            tv_success.setVisibility(View.GONE);
            tv_failed.setVisibility(View.GONE);
            layout_Bottom.setVisibility(View.INVISIBLE);
            relative_tuangou_end.setVisibility(View.VISIBLE);
            linear_tuangou_time.setVisibility(View.GONE);
            // linear_tuagou_line.setVisibility(View.GONE);
            tv_tuangou_line.setVisibility(View.GONE);
            tv_tuangou_time.setVisibility(View.GONE);
            tvResult.setText("团购结束！请关注其他团购");
        }else if (login.equals("end_failed")){
            tv_tuangou_line.setVisibility(View.VISIBLE);
            relative_tuangou.setVisibility(View.GONE);
            tv_success.setVisibility(View.GONE);
            tv_failed.setVisibility(View.GONE);
            layout_Bottom.setVisibility(View.INVISIBLE);
            relative_tuangou_end.setVisibility(View.VISIBLE);
            linear_tuangou_time.setVisibility(View.GONE);
            // linear_tuagou_line.setVisibility(View.GONE);
            tv_tuangou_line.setVisibility(View.GONE);
            tv_tuangou_time.setVisibility(View.GONE);
            tvResult.setText("团购已结束");
        }else if (login.equals("end")){
            tv_tuangou_line.setVisibility(View.VISIBLE);
            relative_tuangou.setVisibility(View.GONE);
            tv_success.setVisibility(View.GONE);
            tv_failed.setVisibility(View.GONE);
            layout_Bottom.setVisibility(View.INVISIBLE);
            relative_tuangou_end.setVisibility(View.VISIBLE);
            linear_tuangou_time.setVisibility(View.GONE);
            // linear_tuagou_line.setVisibility(View.GONE);
            tv_tuangou_line.setVisibility(View.GONE);
            tv_tuangou_time.setVisibility(View.GONE);
            tvResult.setText("团购已结束");
        }else if (login.equals("end_success")){
           // info();///这里改动了
            tv_tuangou_line.setVisibility(View.VISIBLE);
            relative_tuangou.setVisibility(View.GONE);
            tv_success.setVisibility(View.GONE);
            tv_failed.setVisibility(View.GONE);
            layout_Bottom.setVisibility(View.INVISIBLE);
            relative_tuangou_end.setVisibility(View.VISIBLE);
            linear_tuangou_time.setVisibility(View.GONE);
            // linear_tuagou_line.setVisibility(View.GONE);
            tv_tuangou_line.setVisibility(View.GONE);
            tv_tuangou_time.setVisibility(View.GONE);
            tvResult.setText("团购已结束");
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    chenggong();
                }
            }.start();
            if (timer!=null){
                timer.cancel();
            }



        }else if (login.equals("unGroup")){
            tv_tuangou_line.setVisibility(View.VISIBLE);
            relative_tuangou.setVisibility(View.GONE);
            tv_success.setVisibility(View.GONE);
            tv_failed.setVisibility(View.GONE);
            layout_Bottom.setVisibility(View.INVISIBLE);
            relative_tuangou_end.setVisibility(View.VISIBLE);
            linear_tuangou_time.setVisibility(View.GONE);
            // linear_tuagou_line.setVisibility(View.GONE);
            tv_tuangou_line.setVisibility(View.GONE);
            tv_tuangou_time.setVisibility(View.GONE);
            tvResult.setText("未成团，请关注其他团购");
        }else if (login.equals("calc")){
            tv_tuangou_line.setVisibility(View.VISIBLE);
            relative_tuangou.setVisibility(View.GONE);
            tv_success.setVisibility(View.GONE);
            tv_failed.setVisibility(View.GONE);
            layout_Bottom.setVisibility(View.INVISIBLE);
            relative_tuangou_end.setVisibility(View.VISIBLE);
            linear_tuangou_time.setVisibility(View.GONE);
            // linear_tuagou_line.setVisibility(View.GONE);
            tv_tuangou_line.setVisibility(View.GONE);
            tv_tuangou_time.setVisibility(View.GONE);
            tvResult.setText("团购结果计算中");
            if (timer!=null){
                timer.cancel();
            }
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    info();
                }
            }.start();

        }else if (login.equals("notStarted")){
            layout_Bottom.setVisibility(View.GONE);
            tv_tuangou_time.setVisibility(View.GONE);
            tv_tuangou_line.setVisibility(View.GONE);
            linear_tuangou_time.setVisibility(View.VISIBLE);
            //tv_tuangou_time.setVisibility(View.VISIBLE);
            tv_success.setVisibility(View.GONE);
            tv_failed.setVisibility(View.VISIBLE);
            //tv_success.setText("距离团购开始时间还有:"+timeinfo);
            relative_tuangou.setVisibility(View.VISIBLE);

        }



    }

    public void shuaxin(){
        Log.e("进来了","刷新");
        //Log.e("state的值",state);
//        SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
//        SharedPreferences.Editor editor=sp.edit();
//        editor.putString("state",state);
//        editor.commit();
        SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        state=sp.getString("state","");
        Log.d("state的值",state);

//
        if(state.equals("1")){
            Log.e("state的值shuaxin",state);
            Log.e("state的else","else");
                    timer = new Timer();
                    task = new TimerTask() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        }
                    };

                    timer.schedule(task, 0, 3000);//在1秒后执行，每个3秒执行一次



         }
    }

    class TitleThread implements Runnable {

        @Override
        public void run() {

            while (istrue) {
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    //刷新进度条
    public void tuangouinfo(){
        String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/groupProduct";
        RequestParams requestParams=new RequestParams(url);
        requestParams.addParameter("productId",productId);
        Log.e("进度条的rp",requestParams+"");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonobject=new JSONObject(result);
                    Log.e("进度条result的值",result);
                    groupPercent=jsonobject.getString("groupPercent");
                    sumAddQty=jsonobject.getString("sumAddQty");
                    String count_info=jsonobject.getString("cl_zongliang");
                    String qigou=jsonobject.getString("cl_qigou");
                    SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                   // editor.putString("sumAddQty",sumAddQty);
                    editor.commit();
                    Log.e("进度条的值",groupPercent+"%的进度条");
                    Log.e("成团量的值",sumAddQty);
                    Log.e("总量的值",count_info);
                    Log.e("起购量的值",qigou);
                    int i = (int) (Double.valueOf(groupPercent) * 100);
                    pbDemo.setProgress(i);
                    tv_pro_now.setText("当前成团量为 : " + i + "%");

                    if (Float.parseFloat(count_info) - Float.parseFloat(sumAddQty) < Float.parseFloat(qigou)) {
                        Log.e("state的if","if");
                        info();
                        timer.cancel();
                        istrue=false;
                    }
                }catch (JSONException e) {
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




    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        this.getWindow().setAttributes(lp);
    }

    private ListView lv;

    //支付保证金popwindow
    private void showpop() {
        getinfo_Bank();
    }

    //获取银行列表接口
    public void getinfo_Bank() {
        Loding.show(this, "加载中...", true, null);
        new Thread(){
            @Override
            public void run() {
                super.run();
                groupBuyingDetailPresenter.getBankListResult(cl_id);
            }
        }.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
       // istrue=false;

        SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.remove("sumAddQty");
        editor.commit();




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!=null) {
            timer.cancel();
        }
    }

    @Override
    public void setBankListResult(String result) {
        Loding.dis();
        if (result.contains("No Bank Info")) {
            Toast.makeText(this, "该商品暂不支持银行付款", Toast.LENGTH_SHORT).show();
        } else {
            backgroundAlpha(0.5f);
            View view = getLayoutInflater().inflate(R.layout.item_popupwindow_auction, null);
            paymentSecurityPopupWindow = new PopupWindow(findViewById(R.id.parent), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
            paymentSecurityPopupWindow.setContentView(view);
            LinearLayout layout_four = (LinearLayout) view.findViewById(R.id.layout_four);
            layout_four.setVisibility(View.GONE);
            LinearLayout layout_one= (LinearLayout) view.findViewById(R.id.layout_one);
            layout_one.setVisibility(View.GONE);
            paidDeposit = (TextView) view.findViewById(R.id.tvPrice_popupwindow_auction);
            paidDeposit.setText(zhifujine);
            amountOfMarginRequired = (EditText) view.findViewById(R.id.etZhifu_auction);
            amountOfMarginRequired.setKeyListener(null);
//            需保证金
            TextView totalAmount = (TextView) view.findViewById(R.id.total_amount);
            TextView count_Price = (TextView) view.findViewById(R.id.count_Price);
            totalAmount.setText("需保证金:");
//        数量*竞拍价格*保证金比例，是最小的保证金，可以输入比这个大的金额和追加保证金
//        补充保证金

            additionalMarginAmount = Double.parseDouble(tv_item_cart_all_price.getText().toString()) * Double.parseDouble(percent + "") - Double.parseDouble(paidDeposit.getText().toString());

            count_Price.setText(String.format("%.2f", Double.parseDouble(tv_item_cart_all_price.getText().toString()) * Double.parseDouble(percent + "")));
            additionalMarginAmountString = additionalMarginAmount > 0 ? additionalMarginAmount + "" : 0 + "";
            amountOfMarginRequired.setText(String.format("%.2f", Double.parseDouble(additionalMarginAmountString)));
            lv = (ListView) view.findViewById(R.id.lv_popupwindow_auction);

            Gson gson = new Gson();
            list1 = new ArrayList<>();
            PreSaleBondBean preSaleBondBean = gson.fromJson(result, PreSaleBondBean.class);
            List<PreSaleBondBean.BankListBean> bankList = preSaleBondBean.getBankList();
            list1.clear();
            for (int i = 0; i < bankList.size(); i++) {
                OpenAuction auction = new OpenAuction();
                auction.tv_money = bankList.get(i).getBalance();
                String type = bankList.get(i).getBankType();
                if (type.equals("1")) {
                    //平安
                    auction.iv_icon = R.mipmap.pingan;
                    auction.tv_Name = "平安银行";
                    auction.tv_money = bankList.get(i).getBalance();
                    auction.message = 1 + "";
                } else if (type.equals("2")) {
                    //昆仑
                    auction.iv_icon = R.mipmap.kunlun;
                    auction.tv_Name = "昆仑银行";
                    auction.tv_money = bankList.get(i).getBalance();
                    auction.message = 2 + "";
                } else if (type.equals("3")) {
                    //建行
                    auction.iv_icon = R.mipmap.jianshe;
                    auction.tv_Name = "中国建设银行";
                    auction.tv_money = bankList.get(i).getBalance();
                    auction.message = 3 + "";
                }

                list1.add(auction);

            }
            bankListAdapter1 = new PopupWindowAdaptrer(list1, GroupBuyingSuccessActivity.this);
            lv.setAdapter(bankListAdapter1);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (parent.getId() == lv.getId()) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (i != position) {
                                list1.get(i).Flag = false;
                            }
                        }
                        list1.get(position).Flag = !list1.get(position).Flag;
                        bankListAdapter1.notifyDataSetChanged();
                    }
                }
            });
            Button bt = (Button) view.findViewById(R.id.btZhifu);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < list1.size(); i++) {
                        if (list1.get(i).Flag) {
                            //支付保证金接口
                            if (Double.parseDouble(amountOfMarginRequired.getText().toString()) == 0) {
                                Toast.makeText(GroupBuyingSuccessActivity.this, "请填写支付保证金", Toast.LENGTH_SHORT).show();
                            } else {
                                final int position=i;
                                paymentSecurityPopupWindow.dismiss();



                                                zhifu_baozhengjin(list1.get(position).message);





                            }

                        } else if (i == list1.size() - 1) {
                            Toast.makeText(GroupBuyingSuccessActivity.this, "请选择的银行进行付款", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
            paymentSecurityPopupWindow.setTouchable(true);
            paymentSecurityPopupWindow.setBackgroundDrawable(new BitmapDrawable());
            paymentSecurityPopupWindow.setOutsideTouchable(true);
            paymentSecurityPopupWindow.setFocusable(true);

            paymentSecurityPopupWindow.showAtLocation(findViewById(R.id.parent), Gravity.BOTTOM, 0, 0);

            paymentSecurityPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    //popupWindow.dismiss();
                    backgroundAlpha(1f);
                }
            });
        }
    }

    @Override
    public void setPaymentSecurityResult(String result) {
        Loding.dis();
        if (result.contains("formError\":true")) {
            Intent intent=new Intent(GroupBuyingSuccessActivity.this, Error_Dialog.class);
            intent.putExtra("number","");
            startActivity(intent);
            //Toast.makeText(GroupBuyingSuccessActivity.this, "您支付保证金失败！", Toast.LENGTH_SHORT).show();
        } else if (result.contains("\"info\":\"sucess\"")) {
            //Toast.makeText(GroupBuyingSuccessActivity.this, "您支付保证金成功！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(GroupBuyingSuccessActivity.this, Ok_Dialog.class);
            intent.putExtra("number", SuMaoConstant.MARGIN_PAYMENT_SUCCESS);
            intent.putExtra("type", "tuangou");
            startActivity(intent);
        }else{
            Intent intent=new Intent(GroupBuyingSuccessActivity.this, Error_Dialog.class);
            intent.putExtra("number","");
            startActivity(intent);
           // Toast.makeText(GroupBuyingSuccessActivity.this, "您支付保证金失败！", Toast.LENGTH_SHORT).show();
        }
    }


    //    支付保证金接口
    public void zhifu_baozhengjin(String platform) {

        Loding.show(this, "保证金支付中...", false, null);


        String productId = cl_id;
//        支付平台
        String paymentPlatform = platform;
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String _dynSessConf = sp.getString("unique", "");
        // groupBuyingDetailPresenter.getPaymentSecurityResult(depositAmount, productId, paymentPlatform, _dynSessConf);
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/payment/OrderPayment/groupDeposit";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("productId", cl_id);
        rp.addParameter("qty", tv_item_cart_buy_num.getText().toString()  );
        rp.addParameter("platform", platform);
        rp.addParameter("_dynSessConf", _dynSessConf);
        Log.d("支付保證金的rp", rp + "");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("支付保證金的值", result);
                Loding.dis();
                if (result.contains("formError\":false")) {
                    Toast.makeText(GroupBuyingSuccessActivity.this, "您支付保证金失败！", Toast.LENGTH_SHORT).show();
                } else if (result.contains("\"info\":\"sucess\"")) {
                    tuangouinfo();
                    Toast.makeText(GroupBuyingSuccessActivity.this, "您支付保证金成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(GroupBuyingSuccessActivity.this, Ok_Dialog.class);
                    intent.putExtra("number", SuMaoConstant.MARGIN_PAYMENT_SUCCESS);
                    intent.putExtra("type", "团购");
                    startActivity(intent);


                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Loding.dis();
                Toast.makeText(GroupBuyingSuccessActivity.this, "支付失败!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    //    我的参团记录
    @Override
    public void setTheRecordResult(String result) {
        Intent intent = new Intent(GroupBuyingSuccessActivity.this, JoinActivity.class);
        intent.putExtra("result", "");
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layoutContent_auction:
               // Toast.makeText(GroupBuyingSuccessActivity.this, "客服聊天", Toast.LENGTH_SHORT).show();
                Log.d("团购的唯一标识的值",unique);
                if (unique.equals("false") || unique.equals("")) {
                    tuichu();
                    Intent intent = new Intent(GroupBuyingSuccessActivity.this, LoginUserActivity.class);
                    intent.putExtra("roles", "buyer");
                    intent.putExtra("position", 102);
                    startActivity(intent);
                    //startActivityForResult(intent, 102);
                } else {

                Intent intentHX = new Intent();
                intentHX.setClass(GroupBuyingSuccessActivity.this, LoginActivity.class);
                intentHX.putExtra(Constant.MESSAGE_TO_INTENT_EXTRA, Constant.MESSAGE_TO_DEFAULT);
//                传入卖家id
//                intentHX.putExtra(Constant.IM_SERVICE_NUMBER, cl_gongsiId);
//                intentHX.putExtra("name",groupBuyingDetailBean.getCl_gongsi());
                    intentHX.putExtra(Constant.IM_SERVICE_NUMBER, groupBuyingDetailBean.getCl_qiyeonlyId());
                    Log.d("公司的唯一标识",groupBuyingDetailBean.getCl_qiyeonlyId()+"是公司id");
                    intentHX.putExtra("gongsiName",groupBuyingDetailBean.getCl_gongsi());
                   startActivity(intentHX);

//                    backgroundAlpha(0.5f);
                }

                break;
            //            数量减
            case R.id.img_item_cart_buy_sub:
                double num1 = Double.valueOf(tv_item_cart_buy_num.getText().toString());
                num1 -= Double.valueOf(bianliang.getText().toString());
                if (num1 < Double.valueOf(qigou.getText().toString())) {
                    tv_item_cart_buy_num.setText(qigou.getText().toString());
                    //Toast.makeText(this, "购买数量不能小于起购量", Toast.LENGTH_SHORT).show();
                } else {
                    //tv_item_cart_buy_num.setText(num1 + "");
                    Double jianshu = sub(num1, Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
                    int beishu = (int) div(jianshu, Float.parseFloat(groupBuyingDetailBean.getCl_xbianliang()), 0);
                    Log.d("倍数的值", beishu + "");
                    if (beishu <= 0) {
                         beishu=1;
                        tv_item_cart_buy_num.setText(groupBuyingDetailBean.getCl_qigou());
                    }else {
                        Double shuliang = mul(beishu, Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()));
                        Double he = add(shuliang, Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
                        BigDecimal b1 = new BigDecimal(he);
                        double f2 = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                        tv_item_cart_buy_num.setText(f2 + "");
                    }
                    BigDecimal b = new BigDecimal(mul(Double.parseDouble(tv_item_cart_buy_num.getText().toString()),Double.parseDouble(groupBuyingDetailBean.getCl_jine())));
                    double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    tv_item_cart_all_price.setText(f1+"");
                    //tv_item_cart_all_price.setText(Double.parseDouble(tv_item_cart_buy_num.getText().toString()) * Double.valueOf(price.getText().toString()) + "");
                }
                break;
//            数量加
            case R.id.img_item_cart_buy_add:
                double num2 = Double.valueOf(tv_item_cart_buy_num.getText().toString());
                Log.d("输入框的值",tv_item_cart_buy_num.getText().toString());
                num2 += Double.valueOf(groupBuyingDetailBean.getCl_xbianliang());
                Log.d("num2的值",num2+"");

                Double jianshu_shuru=sub(Double.parseDouble(groupBuyingDetailBean.getCl_zongliang()),Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
                int beishu_shuru=(int)div(jianshu_shuru,Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()),0);
                Double ji_shuru=mul(beishu_shuru,Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()));
                Double he_shuru=add(ji_shuru,Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
                Log.d("输入框的最大值",he_shuru+"");
                if(he_shuru>Double.parseDouble(groupBuyingDetailBean.getCl_zongliang())){
                    Double ji_shuru1=mul(beishu_shuru-1,Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()));
                    Double he_shuru1=add(ji_shuru1,Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));

                    if (num2>he_shuru1) {
                          BigDecimal b = new BigDecimal(he_shuru1);
                          double f1 = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                          tv_item_cart_buy_num.setText(f1+"");
                    }else{
                        BigDecimal b = new BigDecimal(num2);
                        double f1 = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                        tv_item_cart_buy_num.setText(f1 + "");
                    }
                }else {
                    if (num2 > he_shuru) {
                        BigDecimal b = new BigDecimal(he_shuru);
                        double f1 = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                        tv_item_cart_buy_num.setText(f1 + "");
                    }else {
                        BigDecimal b = new BigDecimal(num2);
                        double f1 = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                        tv_item_cart_buy_num.setText(f1 + "");
                    }
                }

                BigDecimal b1 = new BigDecimal(mul(Double.parseDouble(tv_item_cart_buy_num.getText().toString()),Double.parseDouble(groupBuyingDetailBean.getCl_jine())));
                double f2 = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                tv_item_cart_all_price.setText(f2+"");

//                    Double jianshu = sub(num2, Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
//                    int beishu = (int) div(jianshu, Float.parseFloat(groupBuyingDetailBean.getCl_xbianliang()), 0);
//                    Log.d("倍数的值", beishu + "");
//                    if (beishu <= 0) {
//                        //beishu=1;
//                        tv_item_cart_buy_num.setText(groupBuyingDetailBean.getCl_qigou());
//                    }else {
//                        Double shuliang = mul(beishu, Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()));
//                        Double he = add(shuliang, Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
//
//                        if (he>shengyvNumber){
//                            Log.d("和",he+"");
//                            Log.d("剩余数量",shengyvNumber+"");
//                            Double shuliang1 = mul(beishu-1, Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()));
//                            Double he1 = add(shuliang1, Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
//                            BigDecimal b1 = new BigDecimal(he1);
//                            double f2 = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
//                            tv_item_cart_buy_num.setText(f2 + "");
//                        }else{
//                            BigDecimal b1 = new BigDecimal(he);
//                            double f2 = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
//                            tv_item_cart_buy_num.setText(f2 + "");
//                        }
//
//
//                    }

              //  }
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique = sp.getString("unique", "");
        shuaxin();



    }

    @Override
    public void setGroupBuyingResult(String result, boolean updateOrLoad) {

    }

    @Override
    public void setGroupBuyingDetailResult(String result, String state, String result2) {
        Log.e("result",result);
        Log.e("state",state);
        Log.e("results",result);
        getinfo(result);
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if(oldBottom != 0 && bottom != 0 &&(oldBottom - bottom > keyHeight)){

           // Toast.makeText(GroupBuyingSuccessActivity.this, "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();

        }else if(oldBottom != 0 && bottom != 0 &&(bottom - oldBottom > keyHeight)) {

            // Toast.makeText(GroupBuyingSuccessActivity.this, "监听到软件盘关闭...", Toast.LENGTH_SHORT).show();
            tv_item_cart_buy_num.clearFocus();
            SharedPreferences sp3 = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            String subNumber = sp3.getString("sumAddQty", "");
            if (subNumber.equals("")) {
                subNumber = "0";
            }
            Double jianshu_shuru = sub(Double.parseDouble(groupBuyingDetailBean.getCl_zongliang()), Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
            int beishu_shuru = (int) div(jianshu_shuru, Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()), 0);
            Double ji_shuru = mul(beishu_shuru, Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()));
            Double he_shuru = add(ji_shuru, Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
            if  (Double.parseDouble(tv_item_cart_buy_num.getText().toString())>=Double.parseDouble(groupBuyingDetailBean.getCl_zongliang())){
//
                if (he_shuru<Double.parseDouble(groupBuyingDetailBean.getCl_zongliang())){
//                    Double newji=mul(beishu_shuru-1,Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()));
//                    Double he_shurunew=add(newji,Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
                    BigDecimal b1 = new BigDecimal(he_shuru);
                    double f2 = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                   tv_item_cart_buy_num.setText(f2 + "");
                }else{
                    Double newji=mul(beishu_shuru-1,Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()));
                    Double he_shurunew=add(newji,Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
                    BigDecimal b1 = new BigDecimal(he_shurunew);
                    double f2 = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                    tv_item_cart_buy_num.setText(f2 + "");
                }


            }else{
           //     if (he_shuru<Double.parseDouble(groupBuyingDetailBean.getCl_zongliang())){
//                    Double newji=mul(beishu_shuru-1,Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()));
//                    Double he_shurunew=add(newji,Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
//
                    Double jianshu_shuru_aa = sub(Double.parseDouble(tv_item_cart_buy_num.getText().toString()), Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
                    int beishu_shuru_aa = (int) div(jianshu_shuru_aa, Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()), 0);
                    Double ji_shuru_aa = mul(beishu_shuru_aa, Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()));
                    Double he_shuru_aa = add(ji_shuru_aa, Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
                    BigDecimal b1 = new BigDecimal(he_shuru_aa);
                    double f2 = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                    tv_item_cart_buy_num.setText(f2 + "");


//                }else{
//                    Double newji=mul(beishu_shuru,Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()));
//                    Double he_shurunew=add(newji,Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
//                    BigDecimal b1 = new BigDecimal(he_shurunew);
//                    double f2 = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
//                    tv_item_cart_buy_num.setText(f2 + "");
//                }

            }
            double x = (Double.parseDouble(tv_item_cart_buy_num.getText().toString())) * (Double.parseDouble(groupBuyingDetailBean.getCl_jine()));
            DecimalFormat df = new DecimalFormat("#.##");
            // all_price.setText(df.format(x) + "");
            tv_item_cart_all_price.setText(df.format(x)+"");


        }
    }


    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            //tv_success.setText("团购结束");
            Log.d("即那里了","进来了");
            if (timer!=null){
                timer.cancel();
            }


            new Thread(){
                @Override
                public void run() {
                    super.run();
                    info();
                }
            }.start();
            istrue=false;

        }

        @Override
        public void onTick(long millisUntilFinished) {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            long time = millisUntilFinished;
//            long time=millisUntilFinished- TimeZone.getDefault().getRawOffset();
            long mSec = time % 1000;
            time /= 1000;
            long year = time / (365 * 24 * 3600);
            time = time % (365 * 24 * 3600);
            long month = time / (30 * 24 * 3600);
            time = time % (30 * 24 * 3600);
            long day = time / (24 * 3600);
            time = time % (24 * 3600);
            long hour = time / 3600;
            time = time % 3600;
            long min = time / 60;
            time = time % 60;
            long sec = time;
//            Log.e("TAG","daojishi------"+day+"天"+hour+"小时"+min+"分"+sec+"秒");
            String finaltime = day + "天" + hour + "小时" + min + "分" + sec + "秒";
            //timeinfo=finaltime;
            tv_success.setText("剩余团购时间  :  " + finaltime);
            tv_failed.setText("距离开团时间剩余  :  " + finaltime);


//            if (login.equals("running")||Inforesult.equals("running")){

//            }else if (login.equals("notStarted")||Inforesult.equals("notStarted")){
//               tv_success.setText("距离开团时间剩余  :  " + finaltime);
//            }
        }
    }

    class MyCount1 extends CountDownTimer {
        public MyCount1(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            //tv_success.setText("团购结束");
            Log.d("即那里了","进来了");
//            if (timer!=null){
//                timer.cancel();
//            }


            new Thread(){
                @Override
                public void run() {
                    super.run();
                    info();
                }
            }.start();
            istrue=false;

        }

        @Override
        public void onTick(long millisUntilFinished) {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            long time = millisUntilFinished;
//            long time=millisUntilFinished- TimeZone.getDefault().getRawOffset();
            long mSec = time % 1000;
            time /= 1000;
            long year = time / (365 * 24 * 3600);
            time = time % (365 * 24 * 3600);
            long month = time / (30 * 24 * 3600);
            time = time % (30 * 24 * 3600);
            long day = time / (24 * 3600);
            time = time % (24 * 3600);
            long hour = time / 3600;
            time = time % 3600;
            long min = time / 60;
            time = time % 60;
            long sec = time;
//            Log.e("TAG","daojishi------"+day+"天"+hour+"小时"+min+"分"+sec+"秒");
            String finaltime = day + "天" + hour + "小时" + min + "分" + sec + "秒";
            //timeinfo=finaltime;
            tv_success.setText("剩余团购时间  :  " + finaltime);
            tv_failed.setText("距离开团时间剩余  :  " + finaltime);


//            if (login.equals("running")||Inforesult.equals("running")){

//            }else if (login.equals("notStarted")||Inforesult.equals("notStarted")){
//               tv_success.setText("距离开团时间剩余  :  " + finaltime);
//            }
        }
    }

    class GridAdapter extends BaseAdapter {
        private List<Map<String, Object>> list;
        private Context context;


        public GridAdapter(List<Map<String, Object>> list, Context context) {
            this.list = list;
            this.context = context;

        }

        @Override
        public int getCount() {

            Log.d("list的size", list_gridview.size() + "");
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.item_gridview, null);
            TextView orderId = (TextView) view.findViewById(R.id.tv_order_id);
            TextView price = (TextView) view.findViewById(R.id.tv_price);
            TextView number = (TextView) view.findViewById(R.id.tv_number);
            TextView count = (TextView) view.findViewById(R.id.count);
            Button bt_search = (Button) view.findViewById(R.id.bt_search);
            Log.d("订单号", list.get(position).get("orderId").toString());
            Log.d("shuliang", list.get(position).get("number").toString());
            Log.d("danjia", list.get(position).get("price").toString());
            Log.d("zongjine", list.get(position).get("amount").toString());
            orderId.setText(list.get(position).get("orderId").toString());
            price.setText(list.get(position).get("price").toString());
            number.setText(list.get(position).get("number").toString());
            count.setText(list.get(position).get("amount").toString());
            bt_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(GroupBuyingSuccessActivity.this,SpotOrder.class);
                    intent.putExtra("id",list.get(position).get("orderId").toString());
                    context.startActivity(intent);
                }
            });


            return view;
        }
    }
    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    tv_item_cart_buy_num.clearFocus();
//                    SharedPreferences sp3=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
//                    String subNumber=sp3.getString("sumAddQty","");
//                    //首先判断有没有超过剩余数量
//                    Double shengyv=sub(Double.parseDouble(groupBuyingDetailBean.getCl_zongliang()),Double.parseDouble(subNumber));
//                    if (tv_item_cart_buy_num.getText().toString().equals("")){
//                        tv_item_cart_buy_num.setText(groupBuyingDetailBean.getCl_qigou());
//
//                    }else if (Double.parseDouble(tv_item_cart_buy_num.getText().toString())>shengyv){
//                            tv_item_cart_buy_num.setText(shengyv+"");
//                    }else {
//                        Double jianshu = sub(Double.parseDouble(tv_item_cart_buy_num.getText().toString()), Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
//                        Log.d("jianshu的值", jianshu + "");
//                        int beishu = (int) div(jianshu, Float.parseFloat(groupBuyingDetailBean.getCl_xbianliang()), 0);
//                        Log.d("倍数的值", beishu + "");
//                        if (beishu == 0) {
//                            // beishu=1;
//                            tv_item_cart_buy_num.setText(groupBuyingDetailBean.getCl_qigou());
//                        }else{
//                            Double shuliang = mul(beishu, Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()));
//                            Double he = add(shuliang, Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
//
//                            if (he>shengyv){
//                                Double shuliang1 = mul(beishu-1, Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()));
//                                Double he1 = add(shuliang1, Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
//                                BigDecimal b1 = new BigDecimal(he1);
//                                double f2 = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
//                                tv_item_cart_buy_num.setText(f2 + "");
//                            }else{
//                                BigDecimal b1 = new BigDecimal(he);
//                                double f2 = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
//                                tv_item_cart_buy_num.setText(f2 + "");
//                            }
////                            Double shuliang = mul(beishu, Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()));
////                            Log.d("数量的值", shuliang + "");
////                            // et_number.setText(shuliang+"");
////                            Double value123 = add(shuliang, Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
////                            // tv_item_cart_buy_num.setText(value123 + "");
////                            BigDecimal b1 = new BigDecimal(value123);
////                            double f2 = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
////                            tv_item_cart_buy_num.setText(f2+"");
////                            Double jianshu = sub(Double.parseDouble(tv_item_cart_buy_num.getText().toString()), Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
////                            Log.d("jianshu的值", jianshu + "");
////                            int beishu = (int) div(jianshu, Float.parseFloat(groupBuyingDetailBean.getCl_xbianliang()), 0);
////                            Log.d("倍数的值", beishu + "");
////                            if (beishu == 0) {
////                                // beishu=1;
////                                tv_item_cart_buy_num.setText(groupBuyingDetailBean.getCl_qigou());
////                            }else {
////                    Double shuliang = mul(beishu, Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()));
////                    Log.d("数量的值", shuliang + "");
////                    // et_number.setText(shuliang+"");
////                    Double value123 = add(shuliang, Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
////                    BigDecimal b1 = new BigDecimal(value123);
////                    double f2 = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
////                    tv_item_cart_buy_num.setText(f2 + "");
//
//                         //   }
//
//                        }
//
//                    }
//
//                    double x = (Double.parseDouble(tv_item_cart_buy_num.getText().toString())) * (Double.parseDouble(groupBuyingDetailBean.getCl_jine()));
//                    DecimalFormat df = new DecimalFormat("#.##");
//                    // all_price.setText(df.format(x) + "");
//                    tv_item_cart_all_price.setText(df.format(x)+"");
//
//                }
                    tv_item_cart_buy_num.clearFocus();
                    SharedPreferences sp3=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                    String subNumber=sp3.getString("sumAddQty","");
                    if (subNumber.equals("")){
                        subNumber="0";
                    }
                    Double jianshu_shuru=sub(Double.parseDouble(groupBuyingDetailBean.getCl_zongliang()),Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
                    int beishu_shuru=(int)div(jianshu_shuru,Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()),0);
                    Double ji_shuru=mul(beishu_shuru,Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()));
                    Double he_shuru=add(ji_shuru,Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));

                    //首先判断有没有超过剩余数量
                    Double shengyv=sub(Double.parseDouble(groupBuyingDetailBean.getCl_zongliang()),Double.parseDouble(subNumber));
                    if (tv_item_cart_buy_num.getText().toString().equals("")){
                        tv_item_cart_buy_num.setText(groupBuyingDetailBean.getCl_qigou());
                    }else if (Double.parseDouble(tv_item_cart_buy_num.getText().toString())>he_shuru){
                        tv_item_cart_buy_num.setText(he_shuru+"");
                    }else {
                        Double jianshu = sub(Double.parseDouble(tv_item_cart_buy_num.getText().toString()), Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
                        Log.d("jianshu的值", jianshu + "");
                        int beishu = (int) div(jianshu, Float.parseFloat(groupBuyingDetailBean.getCl_xbianliang()), 0);
                        Log.d("倍数的值", beishu + "");
                        if (beishu == 0) {
                            // beishu=1;
                            tv_item_cart_buy_num.setText(groupBuyingDetailBean.getCl_qigou());
                        }else {
//                    Double shuliang = mul(beishu, Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()));
//                    Log.d("数量的值", shuliang + "");
//                    // et_number.setText(shuliang+"");
//                    Double value123 = add(shuliang, Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
//                    BigDecimal b1 = new BigDecimal(value123);
//                    double f2 = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
//                    tv_item_cart_buy_num.setText(f2 + "");
                            Double shuliang = mul(beishu, Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()));
                            Double he = add(shuliang, Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));

                            if (he>shengyv){
                                Double shuliang1 = mul(beishu-1, Double.parseDouble(groupBuyingDetailBean.getCl_xbianliang()));
                                Double he1 = add(shuliang1, Double.parseDouble(groupBuyingDetailBean.getCl_qigou()));
                                BigDecimal b1 = new BigDecimal(he1);
                                double f2 = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                                tv_item_cart_buy_num.setText(f2 + "");
                            }else{
                                BigDecimal b1 = new BigDecimal(he);
                                double f2 = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                                tv_item_cart_buy_num.setText(f2 + "");
                            }
                        }


                    }

                    double x = (Double.parseDouble(tv_item_cart_buy_num.getText().toString())) * (Double.parseDouble(groupBuyingDetailBean.getCl_jine()));
                    DecimalFormat df = new DecimalFormat("#.##");
                    // all_price.setText(df.format(x) + "");
                    tv_item_cart_all_price.setText(df.format(x)+"");
                }
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public  double sub(double d1, double d2)
    {        // 进行减法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.subtract(b2).doubleValue();
    }
    public  double mul(double d1, double d2)
    {        // 进行乘法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.multiply(b2).doubleValue();
    }
    public  double div(double d1,
                       double d2,int len) {// 进行除法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.divide(b2,len,BigDecimal.
                ROUND_HALF_UP).doubleValue();
    }
    public  double add(double d1, double d2)
    {        // 进行加法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.add(b2).doubleValue();
    }
}
