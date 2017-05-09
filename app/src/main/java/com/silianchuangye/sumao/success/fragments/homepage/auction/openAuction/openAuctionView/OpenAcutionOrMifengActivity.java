package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
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
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.custom.CustomListView;
import com.silianchuangye.sumao.success.dialog.Error_Dialog;
import com.silianchuangye.sumao.success.dialog.Ok_Dialog;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.bean.AuctionDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.presenter.AuctionPresenter;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;
import com.silianchuangye.sumao.success.fragments.homepage.auction.VesselOneActivity;
import com.silianchuangye.sumao.success.fragments.homepage.auction.VesselThreeActivity;
import com.silianchuangye.sumao.success.fragments.homepage.auction.VesselTwoActivity;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.bean.BiddingesultsBean;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.bean.OpenAuctionDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.bean.RealTimeMardet;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.myOfferMVP.bean.MyOfferBean;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionPresenter.OpenAuctionPresenter;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleBondBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.SpotOrder;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhoneMVP.RegisterPhoneView.RegisterPhoneActivity;
import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.LogUtils;
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
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class OpenAcutionOrMifengActivity extends AppCompatActivity implements IOpenAuctionView, View.OnClickListener, View.OnLayoutChangeListener {
    private ListView lv_auction;
    private List<Map<String, Object>> list;
    private Button btZhifu_auction, bt_jinpai, bt_non_jingpai, bt_jinpai_colse;
    private TextView paidDeposit, tvTime, tvTimeValue;
    private EditText amountOfMarginRequired;
    private ListView lv;
    private ImageView ivBack;
    private PopupWindow popupWindow;
    private String id_value = "", type_way;
    private Button bt_Login, bt_Reg;
    private RelativeLayout Layout_Button_Open, Layout_Button_close, layout_non, Layout_Button_no_login;
    private PopupWindowAdaptrer adapter1;
    private TextView tvRemark_auction;
    private List<OpenAuction> list1;
    private String path;
    String u_clientID;
    //    判断是否登录的状态
    private String state;
    private String name, danjia;
    private RelativeLayout Layout_info;
    private RelativeLayout user_success, layout_Message;
    private TextView tv_order_id, tv_price, tv_number, tv_all_price, tv_result, tv_Message;
    private String price_auction, number_auction, count_auction, order_id;
    private List<GoodsInStockDetailBean.ClAttributeBean> cl_attribute = new ArrayList<GoodsInStockDetailBean.ClAttributeBean>();
    private String max, min, people_Number, quty;
    private RelativeLayout layoutone;
    //    已支付保证金金额
    private String zhifujine = "0";
    private TextView tv_Max_value, tv_dun_number, tv__value, tv_people_number;
    private TextView namechanpin, price, sheng, type, qigou, cangku, bianjiadanwei, diqu, time, company, cangkuaddress, way;
    private String chanpin_Name, chanpin_Price, shengyu, chanpinfenlei, chanpinqigou, chanpincangku,
            min_liang, chanpin_diqu, commit_time, channpin_gongsi, chuanpin_cangku_address, chanpin_way;
    private LinearLayout contactCustomerService;
    private String cl_gongsiId;
    private EditText ed_shuzhi_number;
    private TextView tv_chanpinshuliang;
    private OpenAuctionPresenter openAuctionPresenter;
    //  private AuctionDetailBean auctionDetailBean;
    private Button payImmediately;
    private ArrayList<OpenAuction> list_pop;
    private List<String> list_order = new ArrayList<String>();
    //private MyCount mc;
    private LinearLayout layoutIn;
    private EditText tv_item_cart_buy_num;
    private EditText bid_price_num;
    private TextView all_price_num;
    private TextView tv_chanpinqigou;
    private TextView img_item_cart_buy_sub;
    private TextView img_item_cart_buy_add;

    //
    private String myOffer = "";
    private String realTimeQuotes = "";
    private String status;
    private String additionalMarginAmountString;
    private TextView tv;
    private EditText et;
    private double additionalMarginAmount;
    Number m = null;
    private PopupWindow paymentSecurityPopupWindow;
    private TextView bid_price_sub;
    private TextView bid_price_add;
    private MyOfferBean myOfferBean;
    //    实时行情中包含我的报价
    private boolean realTimeQuotesIncludeMyOffer = false;
    private RealTimeMardet realTimeMardet;
    private Button participate_in_the_auction;
    private LinearLayout participate_in_the_auction_ll;
    private String percent;
    private String productId;
    private boolean needToPayTheMargin;
    private String depositDeadline;
    private TextView tvOpen_auction;
    private LinearLayout remark;
    private LinearLayout line_top, line_bottom, line_centent;
    private CustomListView lvdemo_result;

    private String unique;
    private boolean isbaizhengjin;
    private AuctionPresenter auctionPresenter;
    AuctionDetailBean auctionDetailBean;
    private String shishiprice;
    private String shishinumber;
    private String shishicjnumber;
    private String shishicunzai;
    private String proType;
    private Handler handler;
    private Timer timer;
    private TimerTask task;
    private boolean iscunhuan = false;
    private RelativeLayout parent;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;
    private SharedPreferences sp;
    private String resultAcution;
    private RelativeLayout layout_anniu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_acution_or_mifeng);

        openAuctionPresenter = new OpenAuctionPresenter(this);
        Bundle bundle = getIntent().getExtras();
        resultAcution = bundle.getString("result");
        Log.d("传过来的result", "" + resultAcution);
        Gson gson = new Gson();
        auctionDetailBean = gson.fromJson(resultAcution, AuctionDetailBean.class);
        Log.d("竞拍的详情", auctionDetailBean.getAddressDetail() + "");
        type_way = (getIntent().getStringExtra("type_way"));
        id_value = auctionDetailBean.getCl_id();
        productId = auctionDetailBean.getCl_id();
        proType = getIntent().getStringExtra("proType");
        status = auctionDetailBean.getStatus();
        if (auctionDetailBean.getDepositDeadline().getTime() == null || auctionDetailBean.getDepositDeadline().getTime().equals("") || auctionDetailBean.getDepositDeadline().getTime() == "") {
            depositDeadline = auctionDetailBean.getDepositDeadline().getTime();
        }
        initView();
        initData();
        sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique = sp.getString("unique", "");
        if(unique.equals("")||unique.equals("fasle")){
            tuichu();
        }else {
            SharedPreferences.Editor editor = sp.edit();
            editor.remove("shishicunzai");
            editor.remove("numberValue");
            editor.remove("priceValue");
            editor.commit();
        }
        Log.d("type_way的值", type_way);



    }
    public void setTitleValue(){
        if (status.equals("")||status.equals("0")){
            tvTime.setText("距离竞拍开始时间:");
            long startTime = Long.parseLong(auctionDetailBean.getStartDate()) - System.currentTimeMillis();
            if (startTime>0) {
                new MyCount(startTime, 1000).start();
            }
        }else if (status.equals("1")){
            tvTime.setText("竞拍剩余时间:");
            long startTime = Long.parseLong(auctionDetailBean.getEndDate()) - System.currentTimeMillis();
            if (startTime>0) {
                new MyCount(startTime, 1000).start();
            }
        }else if (status.equals("2")){
            if (type_way.equals("密封报价")){
                tvTime.setVisibility(View.GONE);
                tvTimeValue.setVisibility(View.GONE);
            }else{
                tvTime.setText("竞拍已结束");
                tvTimeValue.setText("");
            }
        }
    }

    private void setValue() {
        Log.d("竞拍唯一标识的值", unique);
        Log.d("竞拍的状态", status);
        if (unique.equals("") || unique.equals("false")) {//没有登录
            if (status.equals("")||status.equals("0")){
                tvTime.setText("距离竞拍开始时间:");
                long startTime = Long.parseLong(auctionDetailBean.getStartDate()) - System.currentTimeMillis();
                if (startTime>0) {
                    new MyCount(startTime, 1000).start();
                }
            }else if (status.equals("1")){
                tvTime.setText("竞拍剩余时间:");
                long startTime = Long.parseLong(auctionDetailBean.getEndDate()) - System.currentTimeMillis();
                if (startTime>0) {
                    new MyCount(startTime, 1000).start();
                }
            }
            Layout_Button_Open.setVisibility(View.GONE);
            layout_non.setVisibility(View.GONE);
            Layout_info.setVisibility(View.GONE);
            Layout_Button_no_login.setVisibility(View.VISIBLE);
            Layout_info.setVisibility(View.GONE);
            layout_Message.setVisibility(View.GONE);
        } else {//登录了，根据不同的状态判断不同的值
            if (status.equals("") || status.equals("0")) {//竞拍没有开始
                //判断是不是第一次进入竞拍，是的话显示参与竞拍按钮，
                // 不是的话进行有没有保证金的判断
                //有，一直显示到竞拍开始
                //没有就等待竞拍开始
                tvTime.setText("距离竞拍开始时间:");
                long startTime = Long.parseLong(auctionDetailBean.getStartDate()) - System.currentTimeMillis();
                if (startTime>0) {
                    new MyCount(startTime, 1000).start();
                }

                    //判断是不是有保证金
                canyujingpaiforResult();




            } else if (status.equals("1")) {//竞拍已经开始
                //进行有没有保证金的判断
                //有，显示支付保证金和立即支付按钮
                //没有就显示字符保证金按钮
                tvTime.setText("竞拍剩余时间:");
                long startTime = Long.parseLong(auctionDetailBean.getEndDate()) - System.currentTimeMillis();
                if (startTime>0) {
                    new MyCount(startTime, 1000).start();
                }
                canyvingjingpaiforResult();

            }

        }

        if (status.equals("2")) {//竞拍已经结束，分为竞拍成功和竞拍失败
            if (type_way.equals("密封报价")){
                tvTime.setVisibility(View.GONE);
                tvTimeValue.setVisibility(View.GONE);
            }else{
                tvTime.setText("竞拍已结束");
                tvTimeValue.setText("");
            }

            if (!unique.equals("") && !unique.equals("false")) {//登录
                if (auctionDetailBean.getDepositFlag().equals("percent")) {
                    openAuctionPresenter.getStateOfPublicBidding(true, type_way, productId, unique);
                } else {
                    openAuctionPresenter.getStateOfPublicBidding(false, type_way, productId, unique);
                }
            } else {
                //没有登录状态返回竞拍已经结束
                Layout_Button_Open.setVisibility(View.GONE);
                layout_non.setVisibility(View.GONE);
                Layout_info.setVisibility(View.VISIBLE);
                Layout_Button_no_login.setVisibility(View.GONE);
                layout_Message.setVisibility(View.GONE);
                participate_in_the_auction_ll.setVisibility(View.GONE);
                Layout_Button_close.setVisibility(View.GONE);
                tv_result.setVisibility(View.VISIBLE);
            }
        }
    }

    public void fuzhi() {

        if (auctionDetailBean.getCl_mingcheng() == null) {
            namechanpin.setText("");
        } else {
            namechanpin.setText(auctionDetailBean.getCl_mingcheng().toString());
        }
        tvOpen_auction.setText(type_way);
        price.setText(auctionDetailBean.getCl_qipai());
        sheng.setText(auctionDetailBean.getCl_zongliang());
        type.setText(auctionDetailBean.getCl_fenlei());
        qigou.setText(auctionDetailBean.getCl_qigou());
        cangku.setText(auctionDetailBean.getCl_cangku());
        bianjiadanwei.setText(auctionDetailBean.getCl_xbianliang());
        diqu.setText(auctionDetailBean.getCl_diqu());
        cangkuaddress.setText(auctionDetailBean.getCl_xbianjia());
//        公司
        company.setText(auctionDetailBean.getCl_gongsi());
        if (auctionDetailBean.getCl_cpbeizhu() == null) {
            remark.setVisibility(View.GONE);
        } else {
            remark.setVisibility(View.VISIBLE);
            tvRemark_auction.setText(auctionDetailBean.getCl_cpbeizhu());
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < auctionDetailBean.getCl_fangshi().size(); i++) {
            if (i == auctionDetailBean.getCl_fangshi().size() - 1) {
                stringBuffer.append(auctionDetailBean.getCl_fangshi().get(i));
            } else {
                stringBuffer.append(auctionDetailBean.getCl_fangshi().get(i) + "、");
            }

        }
//        配送方式
        way.setText(stringBuffer.toString());
        List<GoodsInStockDetailBean.ClAttributeBean> cl_attribute = auctionDetailBean.getCl_attribute();
        if (cl_attribute != null) {
            this.cl_attribute.addAll(cl_attribute);
        }
        path = auctionDetailBean.getContract();
        cl_gongsiId = auctionDetailBean.getCl_gongsiId();

        //cl_attribute.addAll();
//                Jobs Created 联系客服跳转


//        购买数量
        tv_item_cart_buy_num = ((EditText) findViewById(R.id.tv_item_cart_buy_num));

//        减
        img_item_cart_buy_sub = ((TextView) findViewById(R.id.img_item_cart_buy_sub));
        img_item_cart_buy_sub.setOnClickListener(this);
//        增加
        img_item_cart_buy_add = ((TextView) findViewById(R.id.img_item_cart_buy_add));
        img_item_cart_buy_add.setOnClickListener(this);
//        竞拍价格减
        bid_price_sub = ((TextView) findViewById(R.id.bid_price_sub));
        bid_price_sub.setOnClickListener(this);
//        竞拍价格加
        bid_price_add = ((TextView) findViewById(R.id.bid_price_add));
        bid_price_add.setOnClickListener(this);

        //        竞拍价格
        bid_price_num = ((EditText) findViewById(R.id.bid_price_num));
        //        竞拍总价
        all_price_num = ((TextView) findViewById(R.id.all_price_num));
        sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        //shishicunzai=sp.getBoolean("shishicunzai",false);
        shishicunzai = sp.getString("shishicunzai", "");

        if (type_way.equals("公开竞拍")) {
            if (shishicunzai.equals("true")) {
                Log.d("进来了","进来了");
                tv_item_cart_buy_num.setText(shishinumber);
                bid_price_num.setText(shishiprice);
                all_price_num.setText(Double.parseDouble(shishicjnumber) * Double.parseDouble(shishiprice) + "");

            } else {
                Log.d("进来没有实时行情","没有实时行情");
                tv_item_cart_buy_num.setText(qigou.getText().toString());
                bid_price_num.setText(price.getText().toString());
                all_price_num.setText(Double.parseDouble(qigou.getText().toString()) * Double.parseDouble(price.getText().toString()) + "");
            }
            if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) == Double.parseDouble(qigou.getText().toString())) {
                img_item_cart_buy_sub.setTextColor(Color.RED);
            } else if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) == Double.parseDouble(shishicjnumber)) {
                img_item_cart_buy_sub.setTextColor(Color.RED);

            } else {
                img_item_cart_buy_sub.setTextColor(Color.BLACK);
            }

        } else {

            tv_item_cart_buy_num.setText(qigou.getText().toString());
            bid_price_num.setText(price.getText().toString());
            all_price_num.setText(Double.parseDouble(qigou.getText().toString()) * Double.parseDouble(price.getText().toString()) + "");
            if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) == Double.parseDouble(qigou.getText().toString())) {
                img_item_cart_buy_sub.setTextColor(Color.RED);
            } else if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) == Double.parseDouble(shishicjnumber)) {
                img_item_cart_buy_sub.setTextColor(Color.RED);

            } else {
                img_item_cart_buy_sub.setTextColor(Color.BLACK);
            }
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("numberValue",tv_item_cart_buy_num.getText().toString());
            editor.putString("priceValue",bid_price_num.getText().toString());
            editor.commit();
        }


        //购买数量的改变
        tv_item_cart_buy_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (!s.toString().equals("")) {
//                    if (!shishicunzai.equals("true")) {
//                        if (Double.parseDouble(s.toString()) < Double.parseDouble(qigou.getText().toString())) {
//                            tv_item_cart_buy_num.setText(qigou.getText().toString());
//                            tv_item_cart_buy_num.setSelection(qigou.getText().toString().length());
//                            img_item_cart_buy_sub.setTextColor(Color.RED);
//                            img_item_cart_buy_add.setTextColor(Color.BLACK);
//                        } else if (Double.parseDouble(s.toString()) > Double.parseDouble(sheng.getText().toString())) {
//                            tv_item_cart_buy_num.setText(sheng.getText().toString());
//                            tv_item_cart_buy_num.setSelection(sheng.getText().toString().length());
//                            img_item_cart_buy_add.setTextColor(Color.RED);
//                            img_item_cart_buy_sub.setTextColor(Color.BLACK);
//                        }else {
//                            tv_item_cart_buy_num.setText(s.toString());
//                            tv_item_cart_buy_num.setSelection(s.toString().length());
//                        }
//                    } else {
//                        if (Double.parseDouble(s.toString()) < Double.parseDouble(shishicjnumber)) {
//                            tv_item_cart_buy_num.setText(shishicjnumber);
//                            tv_item_cart_buy_num.setSelection(shishicjnumber.length());
//                            img_item_cart_buy_sub.setTextColor(Color.RED);
//                        } else if (Double.parseDouble(s.toString()) > Double.parseDouble(sheng.getText().toString())) {
//                            tv_item_cart_buy_num.setText(sheng.getText().toString());
//                            tv_item_cart_buy_num.setSelection(sheng.getText().toString().length());
//                            img_item_cart_buy_add.setTextColor(Color.RED);
//                        }else {
//                            tv_item_cart_buy_num.setText(s.toString());
//                            tv_item_cart_buy_num.setSelection(s.toString().length());
//                        }
//                    }
                //  }

//                if (){
//                        Log.d("elseif","else if");
//
//                    }
//                if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) > Double.parseDouble(sheng.getText().toString())) {
//
//               // }
//                }else{
//                    SharedPreferences sharedPreferences=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
//                    shishicunzai=sharedPreferences.getString("shishicunzai","");
//                    if (shishicunzai.equals("true")){
//                        Log.d("true","true");
//                        //和实施行情的数据比较
//                        if (Double.parseDouble(tv_item_cart_buy_num.getText().toString())<Double.parseDouble(shishicjnumber)){
//                            tv_item_cart_buy_num.setText(shishicjnumber);
//                            tv_item_cart_buy_num.setSelection(shishicjnumber.length());
//                            img_item_cart_buy_sub.setTextColor(Color.BLACK);
//                        }
//                    }else if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) > Double.parseDouble(sheng.getText().toString())){
//                        Log.d("elseif","else if");
//                        tv_item_cart_buy_num.setText(sheng.getText().toString());
//                        tv_item_cart_buy_num.setSelection(sheng.getText().toString().length());
//                        img_item_cart_buy_add.setTextColor(Color.RED);
//                    }
//                    else{
//                        //和汽狗亮比较
//                        Log.d("else","else");
//                        if (Double.parseDouble(tv_item_cart_buy_num.getText().toString())<Double.parseDouble(qigou.getText().toString())){
//                            tv_item_cart_buy_num.setText(qigou.getText().toString());
//                            tv_item_cart_buy_num.setSelection(qigou.getText().toString().length());
//                            img_item_cart_buy_sub.setTextColor(Color.BLACK);
//                        }
//                    }
            }

            // }
            // }
        });
        // if (Double.parseDouble(bid_price_num.getText().toString())==Double.parseDouble())


////       购买数量
//        tv_item_cart_buy_num.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//               // tv_item_cart_buy_num.setText("");
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            Log.d("正在改变",""+s.toString());
//
//                if (s.length() == 0) {
//                    if (!status.equals("2")) {
//                        SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
//                        shishicunzai=sp.getString("shishicunzai","");
//                        if (shishicunzai.equals("true")) {
//                            tv_item_cart_buy_num.setText(shishinumber);
//                            tv_item_cart_buy_num.setSelection(1);
//                            if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) == Double.parseDouble(shishinumber)) {
//                                img_item_cart_buy_sub.setTextColor(Color.RED);
//                            } else {
//                                img_item_cart_buy_sub.setTextColor(Color.BLACK);
//                            }
//                        } else {
//                            tv_item_cart_buy_num.setText(qigou.getText().toString());
//                            tv_item_cart_buy_num.setSelection(qigou.getText().toString().length());
//                            if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) == Double.parseDouble(qigou.getText().toString())) {
//                                img_item_cart_buy_sub.setTextColor(Color.RED);
//                            } else {
//                                img_item_cart_buy_sub.setTextColor(Color.BLACK);
//                            }
//                        }
//                    } else {
//                        tv_item_cart_buy_num.setText(qigou.getText().toString());
//                        tv_item_cart_buy_num.setSelection(qigou.getText().toString().length());
//                        if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) == Double.parseDouble(qigou.getText().toString())) {
//                            img_item_cart_buy_sub.setTextColor(Color.RED);
//                        } else {
//                            img_item_cart_buy_sub.setTextColor(Color.BLACK);
//                        }
//                    }
//
//                }
//                if (s.toString().contains(".")) {
//                    if (s.length() - 1 - s.toString().indexOf(".") > 3) {
//                        s = s.toString().subSequence(0,
//                                s.toString().indexOf(".") + 4);
//                        tv_item_cart_buy_num.setText(s);
//                        tv_item_cart_buy_num.setSelection(s.length());
//                    }
//                }
//                if (s.toString().trim().substring(0).equals(".")) {
//                    s = "0" + s;
//                    tv_item_cart_buy_num.setText(s);
//                    tv_item_cart_buy_num.setSelection(3);
//                }
//
//                if (s.toString().startsWith("0")
//                        && s.toString().trim().length() > 1) {
//                    if (!s.toString().substring(1, 2).equals(".")) {
//                        tv_item_cart_buy_num.setText(s.subSequence(1, s.length()));
//                        tv_item_cart_buy_num.setSelection(1);
//                        return;
//                    }
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            Log.d("改变后的值",s.toString()+"");
//
//                if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) > Double.parseDouble(sheng.getText().toString())) {
//                    tv_item_cart_buy_num.setText((sheng.getText().toString()));
//                    tv_item_cart_buy_num.setSelection(sheng.getText().toString().length());
//
//                }
//                if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) == Double.parseDouble(sheng.getText().toString())) {
//                    img_item_cart_buy_add.setTextColor(Color.RED);
//                } else {
//                    img_item_cart_buy_add.setTextColor(Color.BLACK);
//                }
////              如果没有支付过保证金，判断报价和量是否都大于起拍量
//                if (auctionDetailBean.getDepositFlag().equals("no")) {
//
//                    if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) < Double.parseDouble(qigou.getText().toString())) {
//                        tv_item_cart_buy_num.setText((qigou.getText().toString()));
//                        tv_item_cart_buy_num.setSelection(qigou.getText().toString().length());
//
//                    }
//                    if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) == Double.parseDouble(qigou.getText().toString())) {
//                        img_item_cart_buy_sub.setTextColor(Color.RED);
//                    } else {
//                        img_item_cart_buy_sub.setTextColor(Color.BLACK);
//                    }
//                    SharedPreferences sharedPreferences=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
//                    shishicunzai=sharedPreferences.getString("shishicunzai","");
//                    //没有保证金存在实施行情里
//                    if (shishicunzai.equals("true")) {
//
//                        if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) < Double.parseDouble(shishicjnumber)) {
//                            tv_item_cart_buy_num.setText(shishicjnumber);
//                            tv_item_cart_buy_num.setSelection(shishicjnumber.length());
//                        }
//                        if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) == Double.parseDouble(shishicjnumber)) {
//                            img_item_cart_buy_sub.setTextColor(Color.RED);
//                        } else {
//                            img_item_cart_buy_sub.setTextColor(Color.BLACK);
//                        }
//                    }
////              如果有支付过保证金，当大于起拍量时，可以提交成功
//                } else {
//                    SharedPreferences sharedPreferences=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
//                    shishicunzai=sharedPreferences.getString("shishicunzai","");
//                    //有保证金存在实施行情里
//                    if (shishicunzai.equals("true")) {
//                        if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) < Double.parseDouble(shishicjnumber)) {
//                            tv_item_cart_buy_num.setText(shishicjnumber);
//                            tv_item_cart_buy_num.setSelection(shishicjnumber.length());
//                        }
//                        if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) == Double.parseDouble(shishicjnumber)) {
//                            img_item_cart_buy_sub.setTextColor(Color.RED);
//                        } else {
//                            img_item_cart_buy_sub.setTextColor(Color.BLACK);
//                        }
//
//                    } else {
//                        if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) < Double.parseDouble(qigou.getText().toString())) {
//                            tv_item_cart_buy_num.setText(qigou.getText().toString());
//                            tv_item_cart_buy_num.setSelection(qigou.getText().toString().length() );
//                        }
//
//                        if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) == Double.parseDouble(qigou.getText().toString())) {
//                            img_item_cart_buy_sub.setTextColor(Color.RED);
//                        } else {
//                            img_item_cart_buy_sub.setTextColor(Color.BLACK);
//                        }
//                    }
//
//                }
//
//                if (!tv_item_cart_buy_num.getText().toString().equals("")) {
//                    double num = (Double.parseDouble(tv_item_cart_buy_num.getText().toString()));
//                    double price_num = (Double.parseDouble(bid_price_num.getText().toString()));
//                    double x = num * price_num;
//                    DecimalFormat df = new DecimalFormat("#.##");
//                    all_price_num.setText(df.format(x) + "");
//
//                }
//                LogUtils.log(bianjiadanwei.getText().toString() + "<---");
//                if ((Double.parseDouble(tv_item_cart_buy_num.getText().toString()) - Double.parseDouble(qigou.getText().toString())) % Double.parseDouble(bianjiadanwei.getText().toString()) != 0) {
//                    DecimalFormat df = new DecimalFormat("#");
//                    double number = Double.parseDouble(tv_item_cart_buy_num.getText().toString()) / Double.parseDouble(bianjiadanwei.getText().toString());
//                    double priceNum = (Integer.parseInt(df.format(number)) * Double.parseDouble(bianjiadanwei.getText().toString())) + Double.parseDouble(qigou.getText().toString());
//                    String priceString = (priceNum + "");
//                    tv_item_cart_buy_num.setText(priceString);
//                    tv_item_cart_buy_num.setSelection(priceString.length());
//                }
//            }
//        });
////       竞拍价格
//        bid_price_num.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
////                if (s.length() == 0) {
////                    if (!status.equals("2")) {
////                        if (realTimeQuotesIncludeMyOffer) {
////                            bid_price_num.setText(myOfferBean.getMyPrice().get(0).getBidPrice());
////                            if (Double.parseDouble(bid_price_num.getText().toString()) == Double.parseDouble(myOfferBean.getMyPrice().get(0).getBidPrice())) {
////                                bid_price_sub.setTextColor(Color.RED);
////                            } else {
////                                bid_price_sub.setTextColor(Color.BLACK);
////                            }
////                        } else {
////                            if (myOfferBean.getMybidQut() == null) {
////                                if (Double.parseDouble(bid_price_num.getText().toString()) < Double.parseDouble(price.getText().toString()))
////                                    bid_price_num.setText(price.getText().toString());
////                                if (Double.parseDouble(bid_price_num.getText().toString()) == Double.parseDouble(price.getText().toString())) {
////                                    bid_price_sub.setTextColor(Color.RED);
////                                } else {
////                                    bid_price_sub.setTextColor(Color.BLACK);
////                                }
////                            } else if (myOfferBean.getMybidQut().equals("0")) {
////                                bid_price_num.setText(price.getText().toString());
////                                if (Double.parseDouble(bid_price_num.getText().toString()) == Double.parseDouble(price.getText().toString())) {
////                                    bid_price_sub.setTextColor(Color.RED);
////                                } else {
////                                    bid_price_sub.setTextColor(Color.BLACK);
////                                }
////                            } else {
////                                bid_price_num.setText(realTimeMardet.getAuctionPricelist().get(realTimeMardet.getAuctionPricelist().size() - 1).getBidPrice());
////                                if (Double.parseDouble(bid_price_num.getText().toString()) == Double.parseDouble(realTimeMardet.getAuctionPricelist().get(realTimeMardet.getAuctionPricelist().size() - 1).getBidPrice())) {
////                                    bid_price_sub.setTextColor(Color.RED);
////                                } else {
////                                    bid_price_sub.setTextColor(Color.BLACK);
////                                }
////                            }
////                        }
////                    } else {
////                        bid_price_num.setText(price.getText().toString());
////                        bid_price_num.setSelection(price.getText().toString().length());
////                        if (Double.parseDouble(bid_price_num.getText().toString()) == Double.parseDouble(price.getText().toString())) {
////                            bid_price_sub.setTextColor(Color.RED);
////                        } else {
////                            bid_price_sub.setTextColor(Color.BLACK);
////                        }
////                    }
////
////                }
////                if (s.toString().contains(".")) {
////
////                    if (s.length() - 1 - s.toString().indexOf(".") > 3) {
////                        s = s.toString().subSequence(0,
////                                s.toString().indexOf(".") + 4);
////                        bid_price_num.setText(s);
////                        bid_price_num.setSelection(s.length());
////                    }
////                }
////                if (s.toString().trim().substring(0).equals(".")) {
////                    s = "0" + s;
////                    bid_price_num.setText(s);
////                    bid_price_num.setSelection(3);
////                }
////
////                if (s.toString().startsWith("0")
////                        && s.toString().trim().length() > 1) {
////                    if (!s.toString().substring(1, 2).equals(".")) {
////                        bid_price_num.setText(s.subSequence(1, s.length()));
////                        bid_price_num.setSelection(1);
////                        return;
////                    }
////                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
////              如果没有支付过保证金，都是数字的时候，判断报价和量是否都大于起拍价
//                if (auctionDetailBean.getDepositFlag().equals("no")) {
//                    if (Double.parseDouble(bid_price_num.getText().toString()) < Double.parseDouble(price.getText().toString())) {
//                        bid_price_num.setText((price.getText().toString()));
//                        bid_price_num.setSelection(price.getText().toString().length());
//                    }
//                    if (Double.parseDouble(bid_price_num.getText().toString()) == Double.parseDouble(price.getText().toString())) {
//                        bid_price_sub.setTextColor(Color.RED);
//                    } else {
//                        bid_price_sub.setTextColor(Color.BLACK);
//                    }
////              如果有支付过保证金，当大于起拍价时，可以提交成功
//                } else {
//                    SharedPreferences sharedPreferences=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
//                    shishicunzai=sharedPreferences.getString("shishicunzai","");
//                    if (shishicunzai.equals("true")) {
//                        if (Double.parseDouble(bid_price_num.getText().toString()) < Double.parseDouble(shishiprice)) {
//                            bid_price_num.setText(shishiprice);
//                        }
//
//                    } else {
//
//                            if (Double.parseDouble(bid_price_num.getText().toString()) < Double.parseDouble(price.getText().toString()))
//                                bid_price_num.setText(price.getText().toString());
//                            if (Double.parseDouble(bid_price_num.getText().toString()) == Double.parseDouble(price.getText().toString())) {
//                                bid_price_sub.setTextColor(Color.RED);
//                            } else {
//                                bid_price_sub.setTextColor(Color.BLACK);
//                            }
//                       // } else if (myOfferBean.getMybidQut().equals("0")) {
//                            if (Double.parseDouble(bid_price_num.getText().toString()) < Double.parseDouble(price.getText().toString()))
//                                bid_price_num.setText(price.getText().toString());
//                            if (Double.parseDouble(bid_price_num.getText().toString()) == Double.parseDouble(price.getText().toString())) {
//                                bid_price_sub.setTextColor(Color.RED);
//                            } else {
//                                bid_price_sub.setTextColor(Color.BLACK);
//                            }
////                        } else {
////                            if (Double.parseDouble(bid_price_num.getText().toString()) < Double.parseDouble(realTimeMardet.getAuctionPricelist().get(realTimeMardet.getAuctionPricelist().size() - 1).getBidPrice()))
////                                bid_price_num.setText(realTimeMardet.getAuctionPricelist().get(realTimeMardet.getAuctionPricelist().size() - 1).getBidPrice());
////                            if (Double.parseDouble(bid_price_num.getText().toString()) == Double.parseDouble(realTimeMardet.getAuctionPricelist().get(realTimeMardet.getAuctionPricelist().size() - 1).getBidPrice())) {
////                                bid_price_sub.setTextColor(Color.RED);
////                            } else {
////                                bid_price_sub.setTextColor(Color.BLACK);
////                            }
////                        }
//                    }
//                }
//
//                if (!bid_price_num.getText().toString().equals("")) {
//                    double num = (Double.parseDouble(tv_item_cart_buy_num.getText().toString()));
//                    double price_num = (Double.parseDouble(bid_price_num.getText().toString()));
//                    double x = num * price_num;
//                    DecimalFormat df = new DecimalFormat("#.##");
//                    all_price_num.setText(df.format(x) + "");
//
//                }
//                if ((Double.parseDouble(bid_price_num.getText().toString()) - Double.parseDouble(price.getText().toString())) % Double.parseDouble(cangkuaddress.getText().toString()) != 0) {
//                    DecimalFormat df = new DecimalFormat("#");
//                    double number = Integer.parseInt(df.format(Double.parseDouble(bid_price_num.getText().toString()) / Double.parseDouble(cangkuaddress.getText().toString()))) + 1;
//                    double priceNum = (Integer.parseInt(df.format(number)) * Double.parseDouble(cangkuaddress.getText().toString())) + Double.parseDouble(price.getText().toString());
//                    String priceString = (priceNum + "");
//                    bid_price_num.setText(priceString);
//                    bid_price_num.setSelection(priceString.length());
//                }
//            }
//        });

    }

    public void initView() {
        layout_anniu=(RelativeLayout)findViewById(R.id.layout_anniu);
        line_top = (LinearLayout) findViewById(R.id.line_top);
        line_bottom = (LinearLayout) findViewById(R.id.line_bottom);
        line_centent=(LinearLayout) findViewById(R.id.line_content);
        lvdemo_result= (CustomListView) findViewById(R.id.Lv_infoSeccess);
        parent = (RelativeLayout) findViewById(R.id.parent);
//获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;
        parent.addOnLayoutChangeListener(this);
        //        标题
        tvOpen_auction = ((TextView) findViewById(R.id.tvOpen_auction));
//        最高竞拍价
        tv_Max_value = (TextView) findViewById(R.id.tv_Max_value);
//        拍出吨数
        tv_dun_number = (TextView) findViewById(R.id.tv_dun_number);
//        最低竞拍价
        tv__value = (TextView) findViewById(R.id.tv__value);
//        中标人数
        tv_people_number = (TextView) findViewById(R.id.tv_people_number);
//        支付保证金
        bt_jinpai_colse = (Button) findViewById(R.id.bt_jinpai_colse);
//        支付保证金的RelativeLayout
        Layout_Button_close = (RelativeLayout) findViewById(R.id.Layout_Button_close);
//        支付保证金和立即报价的RelativeLayout
        Layout_Button_Open = (RelativeLayout) findViewById(R.id.Layout_Button_Open);

        layout_non = (RelativeLayout) findViewById(R.id.layout_non);
//        立即报价
        bt_jinpai = (Button) findViewById(R.id.bt_jinpai);
//        最高竞拍价、中标人数等的RelativeLayout
        // layoutone = (RelativeLayout) findViewById(R.id.layout_one);
//        产品名称
        namechanpin = (TextView) findViewById(R.id.tvName_auction);
//        价格
        price = (TextView) findViewById(R.id.tvPrice_auction);
//        竞价总量
        sheng = (TextView) findViewById(R.id.surplus_amount_et);
//        起购量
        qigou = (TextView) findViewById(R.id.purchase_quantity_et);
//        最小变量单位
        bianjiadanwei = (TextView) findViewById(R.id.min_variable_et);

        time = (TextView) findViewById(R.id.delivery_time_et);
//        最小变价单位
        cangkuaddress = (TextView) findViewById(R.id.warehouse_address_et);
//        交货方式
        way = (TextView) findViewById(R.id.delivery_mode_et);
//        分类
        type = (TextView) findViewById(R.id.classification_pre_sale_et);
//        仓库
        cangku = (TextView) findViewById(R.id.warehouse_et);
//        地区
        diqu = (TextView) findViewById(R.id.region_et);
//        公司
        company = (TextView) findViewById(R.id.company_et);
//        产品备注
        tvRemark_auction = (TextView) findViewById(R.id.tvRemark_auction);
        remark = (LinearLayout) findViewById(R.id.remark);
//        登录
        bt_Login = (Button) findViewById(R.id.btLogin_auction);
//        注册
        bt_Reg = (Button) findViewById(R.id.bt_Reg_Auction);
//        倒计时
        layoutIn = ((LinearLayout) findViewById(R.id.layoutIn));
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvTimeValue = (TextView) findViewById(R.id.tvTimeValue);
//        交易成功
        user_success = (RelativeLayout) findViewById(R.id.layout_user_success);
//        订单号
        tv_order_id = (TextView) findViewById(R.id.tv_Order_id);
        tv_order_id.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv_order_id.setOnClickListener(this);
//        价格
        tv_price = (TextView) findViewById(R.id.tv_price);
//        数量
        tv_number = (TextView) findViewById(R.id.tv_number);
//        成交总量
        tv_all_price = (TextView) findViewById(R.id.tv_count);
//        竞拍已结束
        tv_result = (TextView) findViewById(R.id.tv_Result);
//        竞拍已结束
        Layout_info = (RelativeLayout) findViewById(R.id.Layout_info);

        layout_Message = (RelativeLayout) findViewById(R.id.layout_Message);
        tv_Message = (TextView) findViewById(R.id.tv_Message);
//        未登录
        Layout_Button_no_login = (RelativeLayout) findViewById(R.id.Layout_Button_no_login);
//        立即支付
        payImmediately = ((Button) findViewById(R.id.pay_immediately));
        payImmediately.setOnClickListener(this);
//        参与竞拍
        participate_in_the_auction = ((Button) findViewById(R.id.participate_in_the_auction));
        participate_in_the_auction_ll = ((LinearLayout) findViewById(R.id.participate_in_the_auction_ll));
        participate_in_the_auction.setOnClickListener(this);
        btZhifu_auction = (Button) findViewById(R.id.btZhifu_auction);
        contactCustomerService = ((LinearLayout) findViewById(R.id.layoutService));
        // bt_non_jingpai= (Button) findViewById(R.id.bt_non_jingpai);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
        bt_Reg.setOnClickListener(this);
        bt_Login.setOnClickListener(this);
        bt_jinpai_colse.setOnClickListener(this);
        participate_in_the_auction.setOnClickListener(this);
        btZhifu_auction.setOnClickListener(this);
        bt_jinpai.setOnClickListener(this);
        contactCustomerService.setOnClickListener(this);

    }
    public void tuichu(){

        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.clear();
        editor.commit();



    }

    @Override
    public void onClick(View v) {
        SharedPreferences spces=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        shishicunzai=spces.getString("shishicunzai","");
        Log.d("实时行情的值",shishicunzai);
        switch (v.getId()){
            case R.id.ivBack:
                finish();
                break;
            case R.id.btLogin_auction://登录
                tuichu();
                Intent intent = new Intent(OpenAcutionOrMifengActivity.this, LoginUserActivity.class);
                intent.putExtra("roles", "buyer");
                intent.putExtra("position", 103);
                //startActivity(intent);
                startActivityForResult(intent, 103);
                break;
            case R.id.bt_Reg_Auction://注册
                tuichu();
                Intent intent1 = new Intent(OpenAcutionOrMifengActivity.this, RegisterPhoneActivity.class);
                startActivity(intent1);
                break;
            case R.id.layoutService://联系客服
                if (unique.equals("false")||unique.equals("")){
                    tuichu();
                    Intent intent2 = new Intent(OpenAcutionOrMifengActivity.this, LoginUserActivity.class);
                    intent2.putExtra("roles", "buyer");
                    intent2.putExtra("position", 103);
                    startActivityForResult(intent2, 103);
                }else {
                    Intent intentHX = new Intent();
                    intentHX.setClass(OpenAcutionOrMifengActivity.this, LoginActivity.class);
                    intentHX.putExtra(Constant.MESSAGE_TO_INTENT_EXTRA, Constant.MESSAGE_TO_DEFAULT);
//                传入卖家id
                    intentHX.putExtra(Constant.IM_SERVICE_NUMBER, cl_gongsiId);
                    startActivity(intentHX);
                }

                break;
            case R.id.participate_in_the_auction://参与竞拍
                canyujingpai();
                break;
            case R.id.bt_jinpai_colse:
                if (bt_jinpai_colse.getText().toString().equals("支付保证金")){//支付保证金
                    LogUtils.log("支付保证金--->");
                    depositDeadline = auctionDetailBean.getDepositDeadline().getTime();
                    alreadyBaoZhengJin(true);
                }else if (bt_jinpai_colse.getText().toString().equals("立即报价")){//立即报价
                    if (type_way.equals("公开竞拍")) {
                        Log.d("公开竞拍的立即报价","公开竞拍的立即报价");
                        gongkai_baojia();
                        //openAuctionPresenter.getRealTimeQuotes(productId, true);

                    } else {
                        Log.d("密封竞拍的立即报价","密封竞拍的立即报价");
                        mifeng_baojia();
                    }
                }
                break;
            case R.id.btZhifu_auction://支付保证金
                depositDeadline = auctionDetailBean.getDepositDeadline().getTime();
                alreadyBaoZhengJin(true);
                break;
            case R.id.bt_jinpai://立即报价
                if (type_way.equals("公开竞拍")) {
                    Log.d("公开竞拍的立即报价","公开竞拍的立即报价");
                    gongkai_baojia();
//                    openAuctionPresenter.getRealTimeQuotes(productId, true);

                } else {
                    Log.d("密封竞拍的立即报价","密封竞拍的立即报价");
                    mifeng_baojia();
                }
                break;

            case R.id.img_item_cart_buy_sub://购买数量减少
                double num1 = Double.valueOf(tv_item_cart_buy_num.getText().toString());
                num1 -= Double.valueOf(bianjiadanwei.getText().toString());
                if (Double.parseDouble(zhifujine) == 0) {
//                    SharedPreferences spces=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
//                    shishicunzai=spces.getString("shishicunzai","");
                    Log.d("shishicunzai的值",shishicunzai);
                    if (shishicunzai.equals("true")){
                        //可以减少到当前成交数量
                        if (num1<Double.parseDouble(shishicjnumber)){
                            tv_item_cart_buy_num.setText(shishicjnumber);
                        }else{
                            tv_item_cart_buy_num.setText(num1+"");
                        }
                    }else{
                        //可以减少到起购量
                        if (num1 < Double.valueOf(qigou.getText().toString())){
                            Toast.makeText(this, "购买数量不能小于起购量", Toast.LENGTH_SHORT).show();
                        }else{
                            tv_item_cart_buy_num.setText(num1 + "");
                        }
                    }
                } else {


                    if (shishicunzai.equals("true")){
                        //可以减少到当前成交数量
                        if (num1<Double.parseDouble(shishicjnumber)){
                            tv_item_cart_buy_num.setText(shishicjnumber);
                        }else{
                            tv_item_cart_buy_num.setText(num1+"");
                        }
                    }else{
                        //可以减少到起购量
                        if (num1 < Double.valueOf(qigou.getText().toString())){
                            Toast.makeText(this, "购买数量不能小于起购量", Toast.LENGTH_SHORT).show();
                        }else{
                            tv_item_cart_buy_num.setText(num1 + "");
                        }
                    }

                }
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("numberVale",tv_item_cart_buy_num.getText().toString());
                editor.commit();
                all_price_num.setText(Double.parseDouble(tv_item_cart_buy_num.getText().toString()) * Double.valueOf(bid_price_num.getText().toString()) + "");
                break;
            case R.id.img_item_cart_buy_add://购买数量增加
                double num2 = Double.valueOf(tv_item_cart_buy_num.getText().toString());
                Log.d("num2的值",num2+"");
                num2 += Double.valueOf(bianjiadanwei.getText().toString());
                if (num2 > Double.valueOf(sheng.getText().toString())) {
                    Toast.makeText(this, "购买数量不能大于竞拍总量", Toast.LENGTH_SHORT).show();
                } else {
                    tv_item_cart_buy_num.setText(num2 + "");
                    all_price_num.setText(Double.parseDouble(tv_item_cart_buy_num.getText().toString()) * Double.valueOf(bid_price_num.getText().toString()) + "");
                }
                SharedPreferences.Editor editor1=sp.edit();
                editor1.putString("numberVale",tv_item_cart_buy_num.getText().toString());
                editor1.commit();
                break;

            case R.id.bid_price_sub://购买价格减少
                double num3 = Double.valueOf(bid_price_num.getText().toString());
                num3 -= Double.valueOf(cangkuaddress.getText().toString());
                Log.d("价格减少的值",cangkuaddress.getText().toString());
//                 这里需要根据业务进行判断
                Log.d("支付金额的值",zhifujine);

                    if (shishicunzai.equals("true")){
                        //价格不能减少，价格是实施行情的价格
                        if (num3<=Double.parseDouble(shishiprice)){
                            bid_price_num.setText(shishiprice);

                        }else{
                            bid_price_num.setText(num3 + "");
                        }
                    }else{
                        //价格不能小于起拍价
                        if (num3<Double.parseDouble(qigou.getText().toString())){
                            bid_price_num.setText(qigou.getText().toString());
                        }else{
                            bid_price_num.setText(num3 + "");
                        }

                }
                SharedPreferences.Editor editor2=sp.edit();
                editor2.putString("priceVale",bid_price_num.getText().toString());
                editor2.commit();
                all_price_num.setText(Double.valueOf(bid_price_num.getText().toString()) * Double.valueOf(tv_item_cart_buy_num.getText().toString()) + "");
                break;

            case R.id.bid_price_add:
                double num4 = Double.valueOf(bid_price_num.getText().toString());
                num4 += Double.valueOf(cangkuaddress.getText().toString());
                bid_price_num.setText(num4 + "");
                Log.d("tv_item_buy_num",tv_item_cart_buy_num.getText().toString());
                SharedPreferences.Editor editor4=sp.edit();
                editor4.putString("priceVale",bid_price_num.getText().toString());
                editor4.commit();
                all_price_num.setText(Double.valueOf(bid_price_num.getText().toString()) * Double.valueOf(tv_item_cart_buy_num.getText().toString()) + "");
                break;


        }

    }

    //    获取支付保证金时的银行列表
    public void getinfo_Bank() {
         Loding.show(this, "加载中...", false, null);
        String productId = id_value;
        openAuctionPresenter.getBankListResult(productId);
    }
    public void Popupwindow() {

        getinfo_Bank();
    }

    public void alreadyBaoZhengJin(boolean needShowPopupWindow) {
        Log.d("进来","即那里");
        String productId = id_value;
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String _dynSessConf = sp.getString("unique", "");
        //获取已支付保证金金额
        openAuctionPresenter.getAmountPaidedResult(productId, _dynSessConf);
        if (needShowPopupWindow) {
            Log.d("没有弹出popupwindow","没有弹出popupwindow");
            Popupwindow();

        }
    }

    //    密封报价
    public void mifeng_baojia() {
        //Loding.show(this, "提交报价中...", false, null);
        String productId = id_value;
        String bidPrice = bid_price_num.getText().toString();
        String bidQuantity = tv_item_cart_buy_num.getText().toString();
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String _dynSessConf = sp.getString("unique", "");
        String qigouliang=qigou.getText().toString();
//        密封竞拍报价
        openAuctionPresenter.getSealedBidResult(productId, bidPrice, bidQuantity, _dynSessConf,qigouliang);
    }

    //参与竞拍
    public void canyujingpai(){
        String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/payment/OrderPayment/attendAuction";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("productId",id_value);
        rp.addParameter("skuId",auctionDetailBean.getCl_cpid());
        rp.addParameter("_dynSessConf",unique);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("参与竞拍的返回值",result+"");

                //if (result.contains("yes")){

                    if (auctionDetailBean.getDepositFlag().equals("no")) {//没有保证金
                        //显示立即报价
                        Layout_Button_Open.setVisibility(View.GONE);
                        layout_non.setVisibility(View.GONE);
                        Layout_info.setVisibility(View.GONE);
                        Layout_Button_no_login.setVisibility(View.GONE);
                        Layout_info.setVisibility(View.GONE);
                        layout_Message.setVisibility(View.GONE);
                        participate_in_the_auction_ll.setVisibility(View.GONE);

                        if (status.equals("")||status.equals("0")){
                            layout_anniu.setVisibility(View.GONE);
                            Layout_Button_close.setVisibility(View.GONE);
                        }else if (status.equals("1")) {
                            Layout_Button_close.setVisibility(View.VISIBLE);
                            bt_jinpai_colse.setText("立即报价");
                        }
                    } else if(auctionDetailBean.getDepositFlag().equals("percent")) {//有保证金
                        Layout_Button_Open.setVisibility(View.GONE);
                        layout_non.setVisibility(View.GONE);
                        Layout_info.setVisibility(View.GONE);
                        Layout_Button_no_login.setVisibility(View.GONE);
                        Layout_info.setVisibility(View.GONE);
                        layout_Message.setVisibility(View.GONE);
                        participate_in_the_auction_ll.setVisibility(View.GONE);
                        Layout_Button_close.setVisibility(View.VISIBLE);


//                保证金最后期限
                        String depositDeadline = auctionDetailBean.getDepositDeadline().getTime();
                        if (Double.parseDouble(depositDeadline) - System.currentTimeMillis() < 0) {
                            bt_jinpai_colse.setBackgroundColor(getResources().getColor(R.color.gray));
                        }
                    }


               // }
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
    //竞拍未开始的判断
    public void canyujingpaiforResult(){
        String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/payment/OrderPayment/attendAuction";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("productId",id_value);
        rp.addParameter("skuId",auctionDetailBean.getCl_cpid());
        unique=sp.getString("unique","");
        Log.d("竞拍未开始unique的值",unique);
        rp.addParameter("_dynSessConf",unique);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("参与竞拍的返回值",result+"");
                     if(!result.contains("formError")){//第一次进来
                        Layout_Button_Open.setVisibility(View.GONE);
                        layout_non.setVisibility(View.GONE);
                        Layout_info.setVisibility(View.GONE);
                        Layout_Button_no_login.setVisibility(View.GONE);
                        Layout_info.setVisibility(View.GONE);
                        layout_Message.setVisibility(View.GONE);
                        participate_in_the_auction_ll.setVisibility(View.VISIBLE);
                       }else {
                         if (auctionDetailBean.getDepositFlag().equals("percent")) {
                             //有保证金
                             Layout_Button_Open.setVisibility(View.GONE);
                             layout_non.setVisibility(View.GONE);
                             Layout_info.setVisibility(View.GONE);
                             Layout_Button_no_login.setVisibility(View.GONE);
                             Layout_info.setVisibility(View.GONE);
                             layout_Message.setVisibility(View.GONE);
                             participate_in_the_auction_ll.setVisibility(View.GONE);
                             Layout_Button_close.setVisibility(View.VISIBLE);
                             //bt_jinpai_colse.setText("立即报价");
                         } else {
                             //没有保证金
                             Layout_Button_Open.setVisibility(View.GONE);
                             layout_non.setVisibility(View.GONE);
                             Layout_info.setVisibility(View.GONE);
                             Layout_Button_no_login.setVisibility(View.GONE);
                             Layout_info.setVisibility(View.GONE);
                             layout_Message.setVisibility(View.GONE);
                             participate_in_the_auction_ll.setVisibility(View.GONE);
                             layout_anniu.setVisibility(View.GONE);
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

    public void canyvingjingpaiforResult(){
        String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/payment/OrderPayment/attendAuction";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("productId",id_value);
        rp.addParameter("skuId",auctionDetailBean.getCl_cpid());
        unique=sp.getString("unique","");
        rp.addParameter("_dynSessConf",unique);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("参与竞拍的返回值",result+"");
                if(!result.contains("formError")){//参与竞拍
                    Layout_Button_Open.setVisibility(View.GONE);
                    layout_non.setVisibility(View.GONE);
                    Layout_info.setVisibility(View.GONE);
                    Layout_Button_no_login.setVisibility(View.GONE);
                    Layout_info.setVisibility(View.GONE);
                    layout_Message.setVisibility(View.GONE);
                    participate_in_the_auction_ll.setVisibility(View.VISIBLE);
                }else {
                    if (auctionDetailBean.getDepositFlag().equals("percent")) {
                        //有保证金
                        Layout_Button_Open.setVisibility(View.VISIBLE);
                        layout_non.setVisibility(View.GONE);
                        Layout_info.setVisibility(View.GONE);
                        Layout_Button_no_login.setVisibility(View.GONE);
                        Layout_info.setVisibility(View.GONE);
                        layout_Message.setVisibility(View.GONE);
                        participate_in_the_auction_ll.setVisibility(View.GONE);
                        Layout_Button_close.setVisibility(View.GONE);

                    } else {
                        //没有保证金Layout_Button_close
                        Layout_Button_Open.setVisibility(View.VISIBLE);
                        layout_non.setVisibility(View.GONE);
                        Layout_info.setVisibility(View.GONE);
                        Layout_Button_no_login.setVisibility(View.GONE);
                        Layout_info.setVisibility(View.GONE);
                        layout_Message.setVisibility(View.GONE);
                        participate_in_the_auction_ll.setVisibility(View.GONE);
                        Layout_Button_close.setVisibility(View.VISIBLE);
                        bt_jinpai_colse.setText("立即报价");



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


    //实时行情查看
    public void shishihangqingSelect() {

        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/auctionProductPriceList";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("productId", productId);

        Log.d("实时行情的rp", "" + rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("实施行情的返回值", result);
                u_clientID = getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("U_clientID", "");
                if (result.contains("auctionPricelist")) {
                    Log.d("if", "if");
                    try {
                        JSONObject jsonobject = new JSONObject(result);
                        String auctionPricelist = jsonobject.getString("auctionPricelist");
                        JSONArray jsonArray = new JSONArray(auctionPricelist);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Log.d("jsonfor", "jsonfor");
                            JSONObject object = jsonArray.getJSONObject(i);
                            Log.d("userid", u_clientID + "u_clientId");
                            if (object.toString().contains(u_clientID)) {
                                Log.d("解析进来了", "解析进来了");
                                shishiprice = object.getString("bidPrice");
                                shishinumber = object.getString("bidQut");
                                shishicjnumber = object.getString("dealQut");
                                Log.d("解析的值", shishiprice + shishicjnumber + shishinumber);
                                sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                //editor.putBoolean("shishicunzai", true);
                                editor.putString("shishicunzai", "true");
                                editor.commit();
                                //editor.commit();

                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setTitleValue();
                fuzhi();


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


    public void shishihangqingSelectshuaxin() {

        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/auctionProductPriceList";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("productId", productId);

        Log.d("实时行情的rp", "" + rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("实施行情的返回值", result);
                u_clientID = getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("U_clientID", "");
                if (result.contains("auctionPricelist")) {
                    Log.d("if", "if");
                    try {
                        JSONObject jsonobject = new JSONObject(result);
                        String auctionPricelist = jsonobject.getString("auctionPricelist");
                        JSONArray jsonArray = new JSONArray(auctionPricelist);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Log.d("jsonfor", "jsonfor");
                            JSONObject object = jsonArray.getJSONObject(i);
                            Log.d("userid", u_clientID + "u_clientId");
                            if (object.toString().contains(u_clientID)) {
                                Log.d("解析进来了", "解析进来了");
                                shishiprice = object.getString("bidPrice");
                                shishinumber = object.getString("bidQut");
                                shishicjnumber = object.getString("dealQut");
                                Log.d("解析的值", shishiprice + shishicjnumber + shishinumber);
                                sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                //editor.putBoolean("shishicunzai", true);
                                editor.putString("shishicunzai", "true");
                                editor.commit();
                                //editor.commit();

                            }
                        }
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

    public void initData() {
        lv_auction = (ListView) findViewById(R.id.lv_auction);
        list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new Hashtable<String, Object>();
        map1.put("text", "实时行情、我的报价");
        list.add(map1);
        Map<String, Object> map2 = new Hashtable<String, Object>();
        map2.put("text", "交易规则");
        list.add(map2);
        Map<String, Object> map3 = new Hashtable<String, Object>();
        map3.put("text", "合同详情、产品参数");
        list.add(map3);
        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.item_open_auction, new String[]{"text"}, new int[]{R.id.tvRule_auction});

        lv_auction.setAdapter(adapter);

        lv_auction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("unique", "").equals("false")) {
                    Intent intent = new Intent(OpenAcutionOrMifengActivity.this, LoginUserActivity.class);
                    intent.putExtra("roles", "buyer");
                    startActivity(intent);
                } else {
                    if (position == 0) {
                        //登录状态
                        Intent intent = new Intent(OpenAcutionOrMifengActivity.this, VesselOneActivity.class);
                        intent.putExtra("myOffer", myOffer);
                        intent.putExtra("realTimeQuotes", realTimeQuotes);
                        intent.putExtra("type_way", type_way);
                        intent.putExtra("productId", productId);
                        intent.putExtra("U_company", auctionDetailBean.getU_company());
                        startActivity(intent);
                    } else if (position == 1) {
                        Intent intent = new Intent(OpenAcutionOrMifengActivity.this, VesselTwoActivity.class);
                        intent.putExtra("auctionDetailBean", auctionDetailBean);
                        intent.putExtra("zhifujine", zhifujine);
                        intent.putExtra("auctionMode", type_way);
                        startActivity(intent);
                    } else if (position == 2) {
                        Intent intent = new Intent(OpenAcutionOrMifengActivity.this, VesselThreeActivity.class);
                        intent.putExtra("title", "竞拍");
                        intent.putExtra("cl_attribute", (Serializable) cl_attribute);
                        intent.putExtra("contract", path);
                        intent.putExtra("auctionDetailBean", auctionDetailBean);
                        startActivity(intent);
                    }
                }

            }
        });
    }


    @Override
    public void getOpenAuctionResult(String result) {
        if (result.equals("success")) {
            Intent intent = new Intent(OpenAcutionOrMifengActivity.this, Ok_Dialog.class);
            intent.putExtra("number", SuMaoConstant.QUOTE_SUCCESS);
            intent.putExtra("type", "");
            startActivity(intent);

        }
        shishihangqingSelectshuaxin();

    }

    @Override//密封竞拍报价
    public void getSealedBidResult(String result) {
        Loding.dis();
        if (result.equals("success")) {
            Intent intent = new Intent(OpenAcutionOrMifengActivity.this, Ok_Dialog.class);
            intent.putExtra("number", SuMaoConstant.QUOTE_SUCCESS);
            intent.putExtra("type", "");
            startActivity(intent);
        }
    }

    @Override//获取银行列表
    public void setBankListResult(String result) {
        Loding.dis();
        if (result.contains("No Bank Info")) {
            Toast.makeText(this, "该商品暂不支持银行付款", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("弹出popupwindow","弹出popupwindow");
            View view = getLayoutInflater().inflate(R.layout.item_popupwindow_auction, null);
            paymentSecurityPopupWindow = new PopupWindow(findViewById(R.id.parent), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
            paymentSecurityPopupWindow.setContentView(view);
            LinearLayout layout_four = (LinearLayout) view.findViewById(R.id.layout_four);
            layout_four.setVisibility(View.GONE);
            paidDeposit = (TextView) view.findViewById(R.id.tvPrice_popupwindow_auction);
            paidDeposit.setText(zhifujine);
            amountOfMarginRequired = (EditText) view.findViewById(R.id.etZhifu_auction);
//            需保证金
            TextView totalAmount = (TextView) view.findViewById(R.id.total_amount);
            TextView count_Price = (TextView) view.findViewById(R.id.count_Price);
            totalAmount.setText("需保证金:");
//        数量*竞拍价格*保证金比例，是最小的保证金，可以输入比这个大的金额和追加保证金
//        补充保证金
            //获取最小保证金

            Log.d("总金额",all_price_num.getText().toString());
            Log.d("保证金的比例",auctionDetailBean.getCl_baozhj());
            float result_baozhj=new Float(auctionDetailBean.getCl_baozhj().substring(0,auctionDetailBean.getCl_baozhj().indexOf("%")))/100;
            Log.d("保证金变成小数的值",result_baozhj+"");
            additionalMarginAmount = Double.parseDouble(all_price_num.getText().toString()) * Double.parseDouble(result_baozhj + "") - Double.parseDouble(paidDeposit.getText().toString());
            Log.d("需要缴纳的保证金",Double.parseDouble(all_price_num.getText().toString()) * Double.parseDouble(result_baozhj + "")+"" );
            Log.d("需要续交的保证金",additionalMarginAmount+"");
//
            DecimalFormat  df=new  DecimalFormat("#.##");
            double  d=Double.parseDouble(all_price_num.getText().toString()) * Double.parseDouble(result_baozhj + "");
            //System.out.println(df.format(d));
            count_Price.setText(df.format(d));
            additionalMarginAmountString = additionalMarginAmount > 0 ? additionalMarginAmount + "" : 0 + "";
            Log.d("需要续交的保证金",additionalMarginAmountString);
            amountOfMarginRequired.setText(df.format(Double.parseDouble(additionalMarginAmountString)));
            amountOfMarginRequired.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() == 0) {
                        amountOfMarginRequired.setText(additionalMarginAmountString);
                        amountOfMarginRequired.setSelection(additionalMarginAmountString.length());
                    }
                    if (s.toString().contains(".")) {
                        if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                            s = s.toString().subSequence(0,
                                    s.toString().indexOf(".") + 3);
                            amountOfMarginRequired.setText(s);
                            amountOfMarginRequired.setSelection(s.length());
                        }
                    }
                    if (s.toString().trim().substring(0).equals(".")) {
                        s = "0" + s;
                        amountOfMarginRequired.setText(s);
                        amountOfMarginRequired.setSelection(3);
                    }

                    if (s.toString().startsWith("0")
                            && s.toString().trim().length() > 1) {
                        if (!s.toString().substring(1, 2).equals(".")) {
                            amountOfMarginRequired.setText(s.subSequence(1, s.length()));
                            amountOfMarginRequired.setSelection(1);
                            return;
                        }
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {

                    if (Double.parseDouble(amountOfMarginRequired.getText().toString()) < Double.parseDouble(additionalMarginAmountString)) {
                        amountOfMarginRequired.setText(additionalMarginAmountString);
                        amountOfMarginRequired.setSelection(additionalMarginAmountString.length());

                    }
                }
            });
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
            adapter1 = new PopupWindowAdaptrer(list1, OpenAcutionOrMifengActivity.this);
            lv.setAdapter(adapter1);

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
                        adapter1.notifyDataSetChanged();
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
                                Toast.makeText(OpenAcutionOrMifengActivity.this, "请填写支付保证金", Toast.LENGTH_SHORT).show();
                            } else {
                                zhifu_baozhengjin(amountOfMarginRequired.getText().toString(), list1.get(i).message);
                                paymentSecurityPopupWindow.dismiss();
                            }

                        } else if (i == list1.size() - 1) {
                            Toast.makeText(OpenAcutionOrMifengActivity.this, "请选择的银行进行付款", Toast.LENGTH_SHORT).show();
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
                    paymentSecurityPopupWindow.dismiss();
                    backgroundAlpha(1f);
                }
            });
            backgroundAlpha(0.5f);
        }

    }
    //    设置背景透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
    public void zhifu_baozhengjin(String amountOfMarginRequired, String platform) {
        Loding.show(this, "保证金支付中...", false, null);
//        保证金金额
        String depositAmount = amountOfMarginRequired;
//        产品Id
        String productId = id_value;
//        支付平台
        String paymentPlatform = platform;
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String _dynSessConf = sp.getString("unique", "");
        openAuctionPresenter.getPaymentSecurityResult(depositAmount, productId, paymentPlatform, _dynSessConf);
    }

    @Override
    public void setPaymentSecurityResult(String result) {
        Loding.dis();
        paymentSecurityPopupWindow.dismiss();
        if (result.contains("formError\":true")) {
            Toast.makeText(OpenAcutionOrMifengActivity.this, "您支付保证金失败！", Toast.LENGTH_SHORT).show();
        } else if (result.contains("\"info\":\"sucess\"")) {
            Toast.makeText(OpenAcutionOrMifengActivity.this, "您支付保证金成功！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(OpenAcutionOrMifengActivity.this, Ok_Dialog.class);
            intent.putExtra("number", SuMaoConstant.MARGIN_PAYMENT_SUCCESS);
            intent.putExtra("type", "");
            startActivity(intent);
        }
        if (type_way.equals("密封报价") && !auctionDetailBean.getCl_baozhj().equals("%")) {
            bt_jinpai_colse.setVisibility(View.GONE);
            Layout_Button_Open.setVisibility(View.VISIBLE);

        }
    }

    @Override//获取已支付保证金金额
    public void setAmountPaidedResult(String result) {
        Log.d("已经支付的保证金金额",result);
        try {
            JSONObject obj = new JSONObject(result);
            zhifujine = obj.getString("deposit");
            Log.d("支付金额的值",zhifujine);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (Double.parseDouble(zhifujine) == 0) {
            bt_jinpai.setVisibility(View.GONE);
        } else {
            bt_jinpai.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void setAuctionResultResult(String result) {
        Gson gson = new Gson();
        BiddingesultsBean biddingesultsBean = gson.fromJson(result, BiddingesultsBean.class);
        Log.d("竞拍结果的返回值", result);
        if (result.contains("totalAmount")) {
            layout_anniu.setVisibility(View.GONE);
            line_top.setVisibility(View.VISIBLE);
            line_bottom.setVisibility(View.VISIBLE);
            line_centent.setVisibility(View.VISIBLE);
            lvdemo_result.setVisibility(View.VISIBLE);
            List<Map<String, Object>> list_result = new ArrayList<>();
            Map<String, Object> map = new Hashtable<>();
            map.put("orderId", biddingesultsBean.getOrderId());
            map.put("price", biddingesultsBean.getBidPrice());
            map.put("dealQut", biddingesultsBean.getDealQut());
            map.put("totalAmount", biddingesultsBean.getTotalAmount());
            list_result.add(map);
            SimpleAdapter adapter_result = new SimpleAdapter(OpenAcutionOrMifengActivity.this, list_result, R.layout.item_gridview,
                    new String[]{"orderId", "totalAmount", "price", "dealQut"},
                    new int[]{R.id.tv_order_id, R.id.count, R.id.tv_price, R.id.tv_number});
            lvdemo_result.setAdapter(adapter_result);
            adapter_result.notifyDataSetChanged();
        } else {
            Layout_info.setVisibility(View.VISIBLE);
            Layout_info.setBackgroundColor(getResources().getColor(R.color.the_order_number_textview_blue));
            tv_result.setText("竞拍已结束");
            Layout_Button_no_login.setVisibility(View.GONE);
            participate_in_the_auction_ll.setVisibility(View.VISIBLE);
            Layout_Button_close.setVisibility(View.GONE);
            Layout_Button_Open.setVisibility(View.GONE);
        }

    }

    @Override
    public void setStateOfPublicBiddingResult(String result, boolean needToPayTheMargin) {
        Log.d("竞拍结果的返回值", result);
        try {
            JSONObject jsonObject = new JSONObject(result);
            String flag = jsonObject.getString("flag");
            if (flag.equals("no")) {
                //竞拍失败，显示各种各样的失败理由
                String message = jsonObject.getString("msg");
                if (message.equals("")) {
                    Layout_Button_Open.setVisibility(View.GONE);
                    layout_non.setVisibility(View.GONE);
                    Layout_info.setVisibility(View.GONE);
                    Layout_Button_no_login.setVisibility(View.VISIBLE);
                    Layout_info.setVisibility(View.GONE);
                    layout_Message.setVisibility(View.GONE);
                } else {
                    Layout_Button_Open.setVisibility(View.GONE);
                    layout_non.setVisibility(View.GONE);
                    Layout_info.setVisibility(View.VISIBLE);
                    Layout_Button_no_login.setVisibility(View.GONE);
                    layout_Message.setVisibility(View.GONE);
                    participate_in_the_auction_ll.setVisibility(View.GONE);
                    Layout_Button_close.setVisibility(View.GONE);
                    tv_result.setText(message);
                }

            } else {
                //竞拍成功，返回竞拍结果
                openAuctionPresenter.getAuctionResult(id_value, unique);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void getPaymentResult(String result, String orderIds) {
        Loding.dis();
        try {
            JSONObject obj = new JSONObject(result);
            String state = obj.getString("paymentStatus");
            if (state.equals("0")) {
                //全部成功
                Intent intent = new Intent(OpenAcutionOrMifengActivity.this, Ok_Dialog.class);
                intent.putExtra("type", "公开竞拍");
                intent.putExtra("number", "这里填写订单号");
                startActivity(intent);
            } else if (state.equals("1")) {
                //部分订单成功
                Toast.makeText(OpenAcutionOrMifengActivity.this, "部分订单成功,到订单详情界面查看", Toast.LENGTH_SHORT).show();
            } else if (state.equals("2")) {
                //全部失败
                Intent intent = new Intent(OpenAcutionOrMifengActivity.this, Error_Dialog.class);
                intent.putExtra("number", "这里填写订单号");
                startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override//获取订单后的详情
    public void getOpenAuctionOrderDetail(String result) {
        Gson gson = new Gson();
        OpenAuctionDetailBean openAuctionDetailBean = gson.fromJson(result, OpenAuctionDetailBean.class);
        Intent intent = new Intent();
        intent.putExtra("OrderBean", openAuctionDetailBean.getOrder());
        intent.setClass(OpenAcutionOrMifengActivity.this, SpotOrder.class);
        startActivity(intent);
    }

    @Override
    public void setMyOfferResult(String result, boolean offer) {
        Loding.dis();
        myOffer = result;
        Log.d("我的报价的返回值",result);
        Gson gson = new Gson();
        myOfferBean = gson.fromJson(myOffer, MyOfferBean.class);
        if (realTimeQuotesIncludeMyOffer && !offer) {
            tv_item_cart_buy_num.setText(myOfferBean.getMyPrice().get(0).getBidQut());
            if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) == Double.parseDouble(myOfferBean.getMyPrice().get(0).getBidQut())) {
                img_item_cart_buy_sub.setTextColor(Color.RED);
            } else {
                img_item_cart_buy_sub.setTextColor(Color.BLACK);
            }
            bid_price_num.setText(myOfferBean.getMyPrice().get(0).getBidPrice());
        } else if (!offer) {
            tv_item_cart_buy_num.setText(qigou.getText().toString());
            if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) == Double.parseDouble(qigou.getText().toString())) {
                img_item_cart_buy_sub.setTextColor(Color.RED);
            } else {
                img_item_cart_buy_sub.setTextColor(Color.BLACK);
            }
            bid_price_num.setText(price.getText().toString());
        }
        double num = (Double.parseDouble(tv_item_cart_buy_num.getText().toString()));
        double price_num = (Double.parseDouble(bid_price_num.getText().toString()));
        double x = num * price_num;
        DecimalFormat df = new DecimalFormat("#.##");
        all_price_num.setText(df.format(x) + "");
        if (offer && realTimeQuotesIncludeMyOffer) {
            if (Double.parseDouble(bid_price_num.getText().toString()) <= Double.parseDouble(myOfferBean.getMyPrice().get(0).getBidPrice()) &&
                    Double.parseDouble(tv_item_cart_buy_num.getText().toString()) <= Double.parseDouble(myOfferBean.getMyPrice().get(0).getBidQut())) {
                Toast.makeText(OpenAcutionOrMifengActivity.this, "竞拍单价必须大于等于您当前的自报价或数量大于已在实时行情中的数量。", Toast.LENGTH_LONG).show();
            } else {
                gongkai_baojia();
            }
        }

    }

    @Override//获取实时行情
    public void setRealTimeQuotesResult(String result, boolean offer) {
        Log.d("实时行情的返回值",result);
//        if (result.equals("")) {
            Loding.dis();
            String productId = auctionDetailBean.getCl_id();
            String type = auctionDetailBean.getCl_type();
            sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            String _dynSessConf = sp.getString("unique", "");
            Log.d("到这了", "还有值吗");

            openAuctionPresenter.getMyOfferResult(_dynSessConf, productId, type, offer);
            realTimeQuotes = result;
            Gson gson = new Gson();
            realTimeMardet = gson.fromJson(realTimeQuotes, RealTimeMardet.class);
            List<RealTimeMardet.AuctionPricelistBean> auctionPricelist = realTimeMardet.getAuctionPricelist();
            String u_clientID = getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("U_clientID", "");
            Log.d("公司名称", u_clientID);
            Log.d("auctionPricelist的值", auctionPricelist.size() + "");

            for (int i = 0; i < auctionPricelist.size(); i++) {
                RealTimeMardet.AuctionPricelistBean auctionPricelistBean = auctionPricelist.get(i);
                if (u_clientID.equals(auctionPricelistBean.getBuyerId())) {
                    realTimeQuotesIncludeMyOffer = true;
                }
            }
            Log.d("offer的值", offer + "");
            Log.d("realTimeQuotesIncludeMyOffer的值", realTimeQuotesIncludeMyOffer + "");
            if (offer && !realTimeQuotesIncludeMyOffer) {
                Log.d("进来了价格判断界面", "价格判断");
                if (Double.parseDouble(bid_price_num.getText().toString()) <= Double.parseDouble(shishiprice) &&
                        Double.parseDouble(tv_item_cart_buy_num.getText().toString()) <= Double.parseDouble(shishicjnumber)) {
                    if (Double.parseDouble(bid_price_num.getText().toString()) <= Double.parseDouble(myOfferBean.getMyPrice().get(0).getBidPrice())) {
                        Toast.makeText(OpenAcutionOrMifengActivity.this, "竞拍单价必须大于等于您当前的自报价。", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(OpenAcutionOrMifengActivity.this, "您当前输入的数量不能小于等于已在实时行情中的数量。", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("提交我的报价", "我的报价");
                    gongkai_baojia();
                }
            }

    }

    //    公开竞拍的报价
    public void gongkai_baojia() {
        //首先判断支付的保证金是否足够,如果足够，则立即报价
        //如果不够则继续提交保证金
        unique=sp.getString("unique","");
        openAuctionPresenter.getAmountPaidedResult(productId, unique);
        Log.d("已经支付多少金额",zhifujine);
        if (auctionDetailBean.getDepositFlag().equals("percent")) {//有保证金
            float result_baozhj = new Float(auctionDetailBean.getCl_baozhj().substring(0, auctionDetailBean.getCl_baozhj().indexOf("%"))) / 100;
            Log.d("保证金变成小数的值", result_baozhj + "");
            Double baozhengjin = Double.parseDouble(all_price_num.getText().toString()) * result_baozhj;
            if (baozhengjin < Double.parseDouble(zhifujine)) {
                //Loding.show(this, "提交报价中...", false, null);
                String productId = id_value;
                String bidPrice = bid_price_num.getText().toString();
                String bidQuantity = tv_item_cart_buy_num.getText().toString();
                SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                String qigouliang = qigou.getText().toString();
                openAuctionPresenter.getOpenBiddingResult(productId, bidPrice, bidQuantity, unique, qigouliang);
            } else {
                Toast.makeText(OpenAcutionOrMifengActivity.this, "请您续交保证金!", Toast.LENGTH_SHORT).show();
            }
        }else {
            //Loding.show(this, "提交报价中...", false, null);
            String productId = id_value;
            String bidPrice = bid_price_num.getText().toString();
            String bidQuantity = tv_item_cart_buy_num.getText().toString();
            SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            String qigouliang = qigou.getText().toString();
            openAuctionPresenter.getOpenBiddingResult(productId, bidPrice, bidQuantity, unique, qigouliang);
        }


    }


    @Override
    public void setParticipateInTheAuction(String result) {

    }


    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if(oldBottom != 0 && bottom != 0 &&(oldBottom - bottom > keyHeight)){

            //Toast.makeText(OpenAcutionOrMifengActivity.this, "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();

        }else if(oldBottom != 0 && bottom != 0 &&(bottom - oldBottom > keyHeight)){

            //Toast.makeText(OpenAcutionOrMifengActivity.this, "监听到软件盘关闭...", Toast.LENGTH_SHORT).show();
            sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
            String value=sp.getString("numberValue","");
            String priceValue=sp.getString("priceValue","");
            shishicunzai=sp.getString("shishicunzai","");
            if (!value.equals(tv_item_cart_buy_num.getText().toString())){
                Log.d("是数量的值","发生的改变");
                //输入的值-起购量/变化量
                tv_item_cart_buy_num.clearFocus();
                Double beijianshu=sub(Double.parseDouble(tv_item_cart_buy_num.getText().toString()),Double.parseDouble(auctionDetailBean.getCl_qigou().toString()));
                int beishu=(int)div(beijianshu,Double.parseDouble(auctionDetailBean.getCl_xbianliang()),0);
                String tatol="";
                if (beishu<=0){
                    //qigouliang
                     tatol=Double.parseDouble(auctionDetailBean.getCl_qigou())+"";
                }else {
                    Double count = mul(beishu, Double.parseDouble(auctionDetailBean.getCl_xbianliang()));
                    tatol = add(count, Double.parseDouble(auctionDetailBean.getCl_qigou()))+"";
                }
                if (shishicunzai.equals("true")){
                    if (Double.parseDouble(tatol)>Double.parseDouble(shishicjnumber)){
                        tv_item_cart_buy_num.setText(tatol);
                    }else{
                        tv_item_cart_buy_num.setText(shishicjnumber);
                    }
                }else{
                    tv_item_cart_buy_num.setText(tatol);
                }
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("numberValue",tv_item_cart_buy_num.getText().toString());
                editor.commit();

            }
            //价格的值
            if (!priceValue.equals(bid_price_num.getText().toString())){
                Log.d("是价格的值","发生价格改变");
                //输入的值-起购量/变化量
                bid_price_num.clearFocus();
                Double beijianshu=sub(Double.parseDouble(bid_price_num.getText().toString()),Double.parseDouble(auctionDetailBean.getCl_qipai()));
                int beishu=(int)div(beijianshu,Double.parseDouble(auctionDetailBean.getCl_xbianjia()),0);
                String tatol="";
                if (beishu<=0){
                    //qigouliang
                    tatol=Double.parseDouble(auctionDetailBean.getCl_qipai())+"";
                }else {
                    Double count = mul(beishu, Double.parseDouble(auctionDetailBean.getCl_xbianjia()));
                    tatol = add(count, Double.parseDouble(auctionDetailBean.getCl_qipai()))+"";
                }
                if (shishicunzai.equals("true")){
                    if (Double.parseDouble(tatol)>Double.parseDouble(shishiprice)){
                        bid_price_num.setText(tatol);
                    }else{
                        bid_price_num.setText(shishiprice);
                    }
                }else{
                    bid_price_num.setText(tatol);
                }

            }

            SharedPreferences.Editor editor=sp.edit();
            editor.putString("priceValue",bid_price_num.getText().toString());
            editor.commit();

        }
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
    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    //判断用户是否有权限购买
    public void booleanquanxian(){
        String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/userprofiling/PurchaseJurisdictionCheckActor/PurchaseResult";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("productId",auctionDetailBean.getCl_id());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("判断用户是不是有权限购买产品",result);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    String allow=jsonObject.getString("cl_allow");
                    if (allow.equals("0")){
                        //没有权限问题可以放心购买
                          setValue();
                    }else if (allow.equals("1")){
                        //无上游资源方购买资质
                        Toast.makeText(OpenAcutionOrMifengActivity.this, "无上游资源方购买资质", Toast.LENGTH_SHORT).show();
                    }else if (allow.equals("2")){
                        Toast.makeText(OpenAcutionOrMifengActivity.this, "黑名单用户不能购买", Toast.LENGTH_SHORT).show();
                    }else if (allow.equals("3")){
                        Layout_info.setVisibility(View.GONE);
                        //tv_result.setText("已闭市");
                        Layout_Button_no_login.setVisibility(View.VISIBLE);
                        participate_in_the_auction_ll.setVisibility(View.GONE);
                        Layout_Button_close.setVisibility(View.GONE);
                        Layout_Button_Open.setVisibility(View.GONE);

                        //  Toast.makeText(OpenAuctionActivity.this, "没有登录", Toast.LENGTH_SHORT).show();
                    }else if (allow.equals("4")){
                        Toast.makeText(OpenAcutionOrMifengActivity.this, "没有在购买期限内，不能购买", Toast.LENGTH_SHORT).show();
                    }else if (allow.equals("5")){
                        Toast.makeText(OpenAcutionOrMifengActivity.this, "csc审核没有通过", Toast.LENGTH_SHORT).show();
                    }else if (allow.equals("6")){
                        Toast.makeText(OpenAcutionOrMifengActivity.this, "企业财务员无权购买产品", Toast.LENGTH_SHORT).show();
                    }else if (allow.equals("7")){
                        Toast.makeText(OpenAcutionOrMifengActivity.this, "库存小于购买量", Toast.LENGTH_SHORT).show();
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

    //开闭市判断
    public void booleanOpenorClose(){
        String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/colsedChecker";
        RequestParams rp=new RequestParams(url);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("开闭市的返回值",result);
                if (result.contains("true")){
                    Log.d("竞拍的唯一标识",unique);
                    if (unique.equals("false") || unique.equals("")){//开市但是没有登录
                        //没有登录登录状态
                        Layout_info.setVisibility(View.GONE);
                        //tv_result.setText("已闭市");
                        Layout_Button_no_login.setVisibility(View.VISIBLE);
                        participate_in_the_auction_ll.setVisibility(View.GONE);
                        Layout_Button_close.setVisibility(View.GONE);
                        Layout_Button_Open.setVisibility(View.GONE);
                    }else{                                         //开市场但是登陆了
                        //登录状态判断用户是不是有权限购买
//
                        Layout_Button_no_login.setVisibility(View.GONE);
                        participate_in_the_auction_ll.setVisibility(View.GONE);
                        Layout_Button_close.setVisibility(View.GONE);
                        Layout_Button_Open.setVisibility(View.GONE);
                        booleanquanxian();
                    }


                }else{
                    //已经关闭市场
                    Layout_info.setVisibility(View.VISIBLE);
                    tv_result.setText("已闭市");
                    Layout_Button_no_login.setVisibility(View.GONE);
                    participate_in_the_auction_ll.setVisibility(View.GONE);
                    Layout_Button_close.setVisibility(View.GONE);
                    Layout_Button_Open.setVisibility(View.GONE);
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

    @Override
    public void onStart() {
        super.onStart();
        if (type_way.equals("公开竞拍")) {
            Log.d("这是公开竞拍", "公开竞拍");
            shishihangqingSelect();

        } else {
            SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            String _dynSessConf = sp.getString("unique", "");
            Log.d("只是我的报价", "我的报价");
            //  setValue();
            setTitleValue();
            fuzhi();
            openAuctionPresenter.getMyOfferResult(_dynSessConf, productId, type_way, false);

        }
        booleanOpenorClose();
        Log.d("type_way的值",type_way+status);
        if(type_way.equals("公开竞拍")){
            if (status.equals("1")) {//刷新实时行情的数据
                if (!unique.equals("") && !unique.equals("false")) {
                    handler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);

                            switch (msg.what) {
                                case 1:
                                    selecttimerFortime();
                                    break;
                                default:
                                    break;
                            }
                        }
                    };

                    timer = new Timer();
                    task = new TimerTask() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        }
                    };

                    timer.schedule(task, 3000, 3000);
                }

            }
        }


    }
    //刷新数据,主要是不同的状态
    public void selecttimer(){
        //iscunhuan=true;
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/auctionProduct";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("productId",productId);
        rp.addParameter("proType",proType);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("竞拍的返回值",result);
                if (result.contains("cl_xbianjia")){
                    Gson gson=new Gson();
                    auctionDetailBean = gson.fromJson(result, AuctionDetailBean.class);
                    //开闭市的判断
                    status=auctionDetailBean.getStatus();
                    Log.d("刷新后的状态值",status);
                    booleanOpenorClose();

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

    public void selecttimerFortime(){
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/auctionProduct";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("productId",productId);
        rp.addParameter("proType",proType);
        Log.d("竞拍刷新的值",rp+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("竞拍的返回值",result);
                if (result.contains("cl_xbianjia")){
                    Gson gson=new Gson();
                    auctionDetailBean = gson.fromJson(result, AuctionDetailBean.class);
                    //开闭市的判断
                    status=auctionDetailBean.getStatus();
                    Log.d("刷新后的状态值",status);
                    //booleanOpenorClose();
                    setTitleValue();
//                    if(){//剩余数量小于最小qigouliang，重新请求，计时器结束
//
//                    }


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




    @Override
    public void onStop() {
        super.onStop();
        if (timer!=null){
            timer.cancel();
        }


    }

    /*定义一个倒计时的内部类*/
    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            selecttimer();
            if(timer!=null){
                timer.cancel();
            }


            //在这里进行判断就可以了

        }

        @Override
        public void onTick(long millisUntilFinished) {
            String time = formatTime(millisUntilFinished /*- TimeZone.getDefault().getRawOffset()*/);
            tvTimeValue.setText("" + time);
        }
    }

    //  转化为倒计时需要的时间
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
                sb.append(hour + "小时");
            }else{
                sb.append("0"+hour+"小时");
            }
        }
        if (minute > 0) {
            //sb.append(minute + "分");
            if (minute>9){
                sb.append(minute + "分");
            }else{
                sb.append("0"+minute + "分");
            }
        }
        if (second > 0) {

            if (second>9){
                sb.append(second + "秒");
            }else {
                sb.append("0"+second + "秒");
            }
        }

        return sb.toString();
    }
}
