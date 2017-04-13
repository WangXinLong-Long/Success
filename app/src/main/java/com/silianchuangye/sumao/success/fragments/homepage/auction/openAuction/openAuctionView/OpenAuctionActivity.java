package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionView;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
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
import com.silianchuangye.sumao.success.dialog.Error_Dialog;
import com.silianchuangye.sumao.success.dialog.Ok_Dialog;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.bean.AuctionDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.presenter.AuctionPresenter;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.view.IAuctionView;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;
import com.silianchuangye.sumao.success.fragments.homepage.auction.VesselOneActivity;
import com.silianchuangye.sumao.success.fragments.homepage.auction.VesselThreeActivity;
import com.silianchuangye.sumao.success.fragments.homepage.auction.VesselTwoActivity;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.bean.BiddingesultsBean;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.bean.OpenAuctionDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.bean.OpenAuctionStateBean;
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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class OpenAuctionActivity extends AppCompatActivity implements IOpenAuctionView, View.OnClickListener{
    private ListView lv_auction;
    private List<Map<String, Object>> list;
    private Button btZhifu_auction, bt_jinpai, bt_non_jingpai, bt_jinpai_colse;
    private TextView paidDeposit, tvTime, tvTimeValue;
    private EditText amountOfMarginRequired;
    private ListView lv;
    private ImageView ivBack;
    private PopupWindow popupWindow;
    private String id_value="" , type_way;
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
    private MyCount mc;
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
    private ListView lvdemo_result;
    private SharedPreferences sp;
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
    private boolean iscunhuan=false;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_aution);
        openAuctionPresenter = new OpenAuctionPresenter(this);
        Bundle bundle=getIntent().getExtras();

        String result=bundle.getString("result");
        Log.d("传过来的result",""+result);
        Gson gson=new Gson();
        auctionDetailBean = gson.fromJson(result, AuctionDetailBean.class);


        line_top = (LinearLayout) findViewById(R.id.line_top);
        line_bottom = (LinearLayout) findViewById(R.id.line_bottom);
        line_centent = (LinearLayout) findViewById(R.id.line_content);
        lvdemo_result = (ListView) findViewById(R.id.Lv_infoSeccess);

        Log.d("竞拍的详情",auctionDetailBean.getAddressDetail()+"");
        if (auctionDetailBean.getDepositDeadline().getTime()==null||auctionDetailBean.getDepositDeadline().getTime().equals("")||auctionDetailBean.getDepositDeadline().getTime()==""){

                depositDeadline = auctionDetailBean.getDepositDeadline().getTime();
            Log.d("剩余时间",depositDeadline+"");


        }

//        竞拍方式（公开竞拍、密封报价）
        type_way = (getIntent().getStringExtra("type_way"));
        id_value=auctionDetailBean.getCl_id();
        productId = auctionDetailBean.getCl_id();
        proType=getIntent().getStringExtra("proType");



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

        // bt_non_jingpai= (Button) findViewById(R.id.bt_non_jingpai);
        ivBack = (ImageView) findViewById(R.id.ivBack);


       // openAuctionPresenter.getRealTimeQuotes(productId, true);
//返回当前页面
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenAuctionActivity.this.finish();
            }
        });
        //注册
        bt_Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tuichu();
                Intent intent = new Intent(OpenAuctionActivity.this, RegisterPhoneActivity.class);
                startActivity(intent);
            }
        });
        //登录
        bt_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tuichu();
                Intent intent = new Intent(OpenAuctionActivity.this, LoginUserActivity.class);
                intent.putExtra("roles", "buyer");
                intent.putExtra("position", 103);
                //startActivity(intent);
                startActivityForResult(intent, 103);
            }
        });
        //公开竞拍的按钮
        bt_jinpai_colse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bt_jinpai_colse.getText().toString().equals("支付保证金")) {
                    LogUtils.log("支付保证金--->");
                    depositDeadline = auctionDetailBean.getDepositDeadline().getTime();

                        alreadyBaoZhengJin(true);


                } else if (bt_jinpai_colse.getText().toString().equals("立即报价")) {
                    if (type_way.equals("公开竞拍")) {
                        Log.d("公开竞拍的立即报价","公开竞拍的立即报价");
                        //gongkai_baojia();
                        openAuctionPresenter.getRealTimeQuotes(productId, true);

                    } else {
                        Log.d("密封竞拍的立即报价","密封竞拍的立即报价");
                        mifeng_baojia();
                    }
                }
            }
        });
        //参与竞拍
        participate_in_the_auction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (participate_in_the_auction.getText().toString().equals("参与竞拍")){
                    canyujingpai();
                }

            }
        });
//
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

//        竞拍开始后支付保证金
        btZhifu_auction = (Button) findViewById(R.id.btZhifu_auction);
        btZhifu_auction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Jobs Created 判断是否登录
                SharedPreferences sp = getSharedPreferences("sumao", Context.MODE_PRIVATE);
                String name = sp.getString("name", "");
                if (name.equals("")) {
                   // Toast.makeText(OpenAuctionActivity.this, "请登录后进行操作", Toast.LENGTH_SHORT).show();
                } else {
                        alreadyBaoZhengJin(true);
                }

            }
        });

        bt_jinpai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Double.parseDouble(zhifujine) == 0 && !auctionDetailBean.getCl_baozhj().equals("%")) {
                    Toast.makeText(OpenAuctionActivity.this, "对不起，竞拍开始前未支付保证金,不能报价", Toast.LENGTH_SHORT).show();
                } else {
                    if (type_way.equals("公开竞拍")) {
                        if (Double.parseDouble(bid_price_num.getText().toString()) <= Double.parseDouble(shishiprice) &&

                       Double.parseDouble(tv_item_cart_buy_num.getText().toString()) <= Double.parseDouble(shishicjnumber)) {
//
                         Toast.makeText(OpenAuctionActivity.this, "您当前输入的数量不能小于等于已在实时行情中的数量。", Toast.LENGTH_SHORT).show();

                        } else {
                            alreadyBaoZhengJin(false);
                            Log.d("进来yijing支付金额",zhifujine+"");//他应该实施获取
                            Log.d("进来需要jiao的保证金金额",""+String.format("%.2f", Double.parseDouble(all_price_num.getText().toString()) * Double.parseDouble(percent + "")));
                            if (Double.parseDouble(zhifujine) < Double.parseDouble(String.format("%.2f", Double.parseDouble(all_price_num.getText().toString()) * Double.parseDouble(percent + "")))) {
                                Toast.makeText(OpenAuctionActivity.this, "保证金余额不足，请您续交保证金", Toast.LENGTH_SHORT).show();
                            } else {
                                gongkai_baojia();
                            }

                        }

                        // 密封报价
                    } else {
//                        需要支付保证金
                        if (!auctionDetailBean.getCl_baozhj().equals("%")) {
                            double total = 0;
//                            还没有进行报价
                            if (myOffer.equals("{}")) {
                                total = 0;
//                                已进行的报价
                            } else {
                                for (int i = 0; i < myOfferBean.getMyPrice().size(); i++) {
                                    MyOfferBean.MyPriceBean myPriceBean = myOfferBean.getMyPrice().get(i);
                                    double unitPrice = Double.parseDouble(myPriceBean.getBidPrice()) * Double.parseDouble(myPriceBean.getBidQut());
                                    total += unitPrice;
                                }
                            }
//                            计算是否需要缴纳保证金
                            if (Double.parseDouble(zhifujine) < Double.parseDouble(String.format("%.2f", (total + Double.parseDouble(all_price_num.getText().toString())) * Double.parseDouble(percent + "")))) {
                                double needToPayTheMargin = Double.parseDouble(String.format("%.2f", (total + Double.parseDouble(all_price_num.getText().toString())) * Double.parseDouble(percent + "")))
                                        - Double.parseDouble(zhifujine);
                                Toast.makeText(OpenAuctionActivity.this, "保证金余额不足,需缴纳" +
                                        needToPayTheMargin + "元", Toast.LENGTH_LONG).show();
//                                不需要进行支付保证金（保证金足够）
                            } else {
                                mifeng_baojia();
                            }
//                            不需要支付保证金
                        } else {
                            mifeng_baojia();
                        }

                    }

                }

            }
        });
        lv_auction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("unique", "").equals("false")) {
                    Intent intent = new Intent(OpenAuctionActivity.this, LoginUserActivity.class);
                    intent.putExtra("roles", "buyer");
                    startActivity(intent);
                } else {
                    if (position == 0) {
                        //登录状态
                        Intent intent = new Intent(OpenAuctionActivity.this, VesselOneActivity.class);
                        intent.putExtra("myOffer", myOffer);
                        intent.putExtra("realTimeQuotes", realTimeQuotes);
                        intent.putExtra("type_way", type_way);
                        intent.putExtra("productId", productId);
                        intent.putExtra("U_company", auctionDetailBean.getU_company());
                        startActivity(intent);
                    } else if (position == 1) {
                        Intent intent = new Intent(OpenAuctionActivity.this, VesselTwoActivity.class);
                        intent.putExtra("auctionDetailBean", auctionDetailBean);
                        intent.putExtra("zhifujine", zhifujine);
                        intent.putExtra("auctionMode", type_way);
                        startActivity(intent);
                    } else if (position == 2) {
                        Intent intent = new Intent(OpenAuctionActivity.this, VesselThreeActivity.class);
                        intent.putExtra("title", "竞拍");
                        intent.putExtra("cl_attribute", (Serializable) cl_attribute);
                        intent.putExtra("contract", path);
                        intent.putExtra("auctionDetailBean", auctionDetailBean);
                        startActivity(intent);
                    }
                }

            }
        });
//      Jobs Created 联系客服
        contactCustomerService = ((LinearLayout) findViewById(R.id.layoutService));



        IntentFilter filter = new IntentFilter();
        filter.addAction("finishOpenAuction");
        registerReceiver(broadcastReceiver, filter);


        if (type_way.equals("公开竞拍")) {
            Log.d("这是公开竞拍","公开竞拍");
            shishihangqingSelect();
           // Loding.show(this, "加载中...", false, null);
            //这里先注视一下
           //openAuctionPresenter.getRealTimeQuotes(id_value, false);
        } else {
            //String type = auctionDetailBean.getCl_type();
            SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            String _dynSessConf = sp.getString("unique", "");
            Log.d("只是我的报价","我的报价");
            setValue();
            fuzhi();
            openAuctionPresenter.getMyOfferResult(_dynSessConf, productId, type_way, false);
//        我的报价
           // Loding.show(this, "加载中...", false, null);
            //这里先注shi测试一下效果

        }



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
                    if (unique.equals("false") || unique.equals("")){
                        //没有登录登录状态
                        Layout_info.setVisibility(View.GONE);
                        //tv_result.setText("已闭市");
                        Layout_Button_no_login.setVisibility(View.VISIBLE);
                        participate_in_the_auction_ll.setVisibility(View.GONE);
                        Layout_Button_close.setVisibility(View.GONE);
                        Layout_Button_Open.setVisibility(View.GONE);
                    }else{
                        //登录状态判断用户是不是有权限购买
                        Layout_info.setVisibility(View.GONE);
                        //tv_result.setText("已闭市");
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
    public void tuichu(){
        RequestParams  rp=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/logout");
        SharedPreferences sp=getSharedPreferences("sumao",0);
        //SharedPreferences sp1=g
        String unique=sp.getString("unique","默认值");
        rp.addParameter("_dynSessConf",unique);
        Log.d("退出时的唯一标识",unique);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.clear();
                //      editor.clear();
                editor.commit();
              //  Toast.makeText(context, "用户名称已删除", Toast.LENGTH_SHORT).show();

//                                            SettingActivity.this.finish();

//                                        }
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
//                            }
//                        }.start();


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

                        isSuccessorFail(needToPayTheMargin);
                    }else if (allow.equals("1")){
                        //无上游资源方购买资质
                        Toast.makeText(OpenAuctionActivity.this, "无上游资源方购买资质", Toast.LENGTH_SHORT).show();
                    }else if (allow.equals("2")){
                        Toast.makeText(OpenAuctionActivity.this, "黑名单用户不能购买", Toast.LENGTH_SHORT).show();
                    }else if (allow.equals("3")){
                        Layout_info.setVisibility(View.GONE);
                        //tv_result.setText("已闭市");
                        Layout_Button_no_login.setVisibility(View.VISIBLE);
                        participate_in_the_auction_ll.setVisibility(View.GONE);
                        Layout_Button_close.setVisibility(View.GONE);
                        Layout_Button_Open.setVisibility(View.GONE);
                      //  Toast.makeText(OpenAuctionActivity.this, "没有登录", Toast.LENGTH_SHORT).show();
                    }else if (allow.equals("4")){
                        Toast.makeText(OpenAuctionActivity.this, "没有在购买期限内，不能购买", Toast.LENGTH_SHORT).show();
                    }else if (allow.equals("5")){
                        Toast.makeText(OpenAuctionActivity.this, "csc审核没有通过", Toast.LENGTH_SHORT).show();
                    }else if (allow.equals("6")){
                        Toast.makeText(OpenAuctionActivity.this, "企业财务员无权购买产品", Toast.LENGTH_SHORT).show();
                    }else if (allow.equals("7")){
                        Toast.makeText(OpenAuctionActivity.this, "库存小于购买量", Toast.LENGTH_SHORT).show();
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




    private void setValue() {
//        竞拍的状态
        status = auctionDetailBean.getStatus();
        if (status.equals("") || status.equals("0")){//竞拍未开始
            tvTime.setText("距离竞拍开始时间:");
            long startTime = Long.parseLong(auctionDetailBean.getStartDate()) - System.currentTimeMillis();
            new MyCount(startTime, 1000).start();
            Layout_Button_Open.setVisibility(View.GONE);
            layout_non.setVisibility(View.INVISIBLE);
            Layout_info.setVisibility(View.GONE);
            Layout_Button_no_login.setVisibility(View.GONE);
            Layout_info.setVisibility(View.GONE);
            layout_Message.setVisibility(View.GONE);
            isSuccessorFail(needToPayTheMargin);

        }else if (status.equals("1")){//正在竞拍
            tvTime.setText("竞拍剩余时间:");
            long startTime = Long.parseLong(auctionDetailBean.getEndDate()) - System.currentTimeMillis();
            new MyCount(startTime, 1000).start();
            isSuccessorFail(needToPayTheMargin);
        }else if (status.equals("2")){//竞拍结束
            tvTime.setText("竞拍已结束");
            tvTimeValue.setText("");
            Layout_info.setVisibility(View.GONE);
            Layout_Button_no_login.setVisibility(View.GONE);
            participate_in_the_auction_ll.setVisibility(View.GONE);
            Layout_Button_close.setVisibility(View.GONE);
            Layout_Button_Open.setVisibility(View.GONE);
            //显示listview
           // isSuccessorFail(needToPayTheMargin);
            auctionResult();
        }


    }
    public void fuzhi(){
      //        String name = "";
//        if (!getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("unique", "").equals("false"))
//            name = getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("name", "");
//        if (name.equals("")) {
//            state = "no";
//        } else {
//            state = "yes";
//        }
//
//        //        获取ProductID
//        id_value = auctionDetailBean.getCl_id();
//        LogUtils.log("status--->" + status);
//        if (status.equals("") || status.equals("0")) {//竞拍未开始
//            tvTime.setText("距离竞拍开始时间:");
//            long startTime = Long.parseLong(auctionDetailBean.getStartDate()) - System.currentTimeMillis();
//            new MyCount(startTime, 1000).start();
//            Layout_Button_Open.setVisibility(View.GONE);
//            layout_non.setVisibility(View.INVISIBLE);
//            Layout_info.setVisibility(View.GONE);
//            if (state.equals("no")) {
//                //没有登录或者注册
//                Layout_Button_no_login.setVisibility(View.VISIBLE);
//                layout_Message.setVisibility(View.GONE);
//                Layout_info.setVisibility(View.GONE);
//                participate_in_the_auction_ll.setVisibility(View.GONE);
//            } else {
//                Layout_Button_no_login.setVisibility(View.GONE);
//                Layout_info.setVisibility(View.GONE);
//                layout_Message.setVisibility(View.GONE);
//                isSuccessorFail(needToPayTheMargin);
//            }
//        } else if (status.equals("1")) {//正在竞拍
//            tvTime.setText("竞拍剩余时间:");
//            long startTime = Long.parseLong(auctionDetailBean.getEndDate()) - System.currentTimeMillis();
//            new MyCount(startTime, 1000).start();
//            if (state.equals("no")) {
//                //提示登录或者注册
//                Layout_Button_no_login.setVisibility(View.VISIBLE);
//                Layout_Button_close.setVisibility(View.GONE);
//                Layout_Button_Open.setVisibility(View.GONE);
//                Layout_info.setVisibility(View.GONE);
//                layout_Message.setVisibility(View.GONE);
//            } else {
//                //Layout_Button_no_login.setVisibility(View.GONE);
//                //正常显示
//                //这里需要判断是否支付保证金
//                Layout_info.setVisibility(View.GONE);
//                Layout_Button_no_login.setVisibility(View.GONE);
//                LogUtils.log("正在竞拍是否需要支付保证金--->" + needToPayTheMargin);
//                isSuccessorFail(needToPayTheMargin);
//            }
////      竞拍结束
//        } else if (status.equals("2")) {
        //  tvTime.setText("竞拍已结束");
//            tvTimeValue.setText("");
//            bt_jinpai.setBackgroundColor(getResources().getColor(R.color.gray_type));
//            Layout_Button_close.setVisibility(View.GONE);
//            Layout_Button_Open.setVisibility(View.GONE);
////            未登录
//            if (state.equals("no")) {
//                //没有登录或者注册，最高标价
//                Layout_Button_no_login.setVisibility(View.VISIBLE);
//                layout_Message.setVisibility(View.VISIBLE);
//                tv_Message.setText("竞拍结束");
//                Layout_info.setVisibility(View.GONE);
//            } else {
//                Layout_info.setVisibility(View.VISIBLE);
//                Layout_Button_no_login.setVisibility(View.GONE);
//                //已经登录或者注册，判断是否成功
//                isSuccessorFail(needToPayTheMargin);
//            }
//        }
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

        //cl_attribute.addAll();
//                Jobs Created 联系客服跳转
//        contactCustomerService.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentHX = new Intent();
//                intentHX.setClass(OpenAuctionActivity.this, LoginActivity.class);
//                intentHX.putExtra(Constant.MESSAGE_TO_INTENT_EXTRA, Constant.MESSAGE_TO_DEFAULT);
////                传入卖家id
//                intentHX.putExtra(Constant.IM_SERVICE_NUMBER, cl_gongsiId);
//                startActivity(intentHX);
//            }
//        });
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
        sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        //shishicunzai=sp.getBoolean("shishicunzai",false);
        shishicunzai=sp.getString("shishicunzai","");
        Log.d("解析的值 ",""+shishicunzai+"我想睡觉");
        if (type_way.equals("公开竞拍")) {
            if (shishicunzai.equals("true")) {
                tv_item_cart_buy_num.setText(shishinumber);
                bid_price_num.setText(shishiprice);
                all_price_num.setText(Double.parseDouble(shishicjnumber) * Double.parseDouble(shishiprice) + "");

            } else {
                tv_item_cart_buy_num.setText(qigou.getText().toString());
                bid_price_num.setText(price.getText().toString());
                all_price_num.setText(Double.parseDouble(qigou.getText().toString()) * Double.parseDouble(price.getText().toString()) + "");
            }
            if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) == Double.parseDouble(qigou.getText().toString())) {
                img_item_cart_buy_sub.setTextColor(Color.RED);
            } else if (Double.parseDouble(tv_item_cart_buy_num.getText().toString())==Double.parseDouble(shishicjnumber)){
                img_item_cart_buy_sub.setTextColor(Color.RED);

            }else{
                img_item_cart_buy_sub.setTextColor(Color.BLACK);
            }
        }else{

            tv_item_cart_buy_num.setText(qigou.getText().toString());
            bid_price_num.setText(price.getText().toString());
            all_price_num.setText(Double.parseDouble(qigou.getText().toString()) * Double.parseDouble(price.getText().toString()) + "");
            if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) == Double.parseDouble(qigou.getText().toString())) {
                img_item_cart_buy_sub.setTextColor(Color.RED);
            } else if (Double.parseDouble(tv_item_cart_buy_num.getText().toString())==Double.parseDouble(shishicjnumber)){
                img_item_cart_buy_sub.setTextColor(Color.RED);

            }else{
                img_item_cart_buy_sub.setTextColor(Color.BLACK);
            }
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





    public void Popupwindow() {

        getinfo_Bank();
    }

    //    设置背景透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    //    获取支付保证金时的银行列表
    public void getinfo_Bank() {
       // Loding.show(this, "加载中...", false, null);
        String productId = id_value;
        openAuctionPresenter.getBankListResult(productId);
    }

    //    竞拍的状态
    public void isSuccessorFail(boolean needToPayTheMargin) {
        String productId = id_value;
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String _dynSessConf = sp.getString("unique", "");
        LogUtils.log("竞拍的状态是否需要支付保证金--->" + needToPayTheMargin);
        openAuctionPresenter.getStateOfPublicBidding(needToPayTheMargin, type_way, productId, _dynSessConf);
    }

    //    竞拍结果
    public void auctionResult() {

        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String _dynSessConf = sp.getString("unique", "");
        openAuctionPresenter.getAuctionResult(id_value, _dynSessConf);
    }

    //    支付保证金接口
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

    //    公开竞拍的报价
    public void gongkai_baojia() {
        //Loding.show(this, "提交报价中...", false, null);
        String productId = id_value;
        String bidPrice = bid_price_num.getText().toString();
        String bidQuantity = tv_item_cart_buy_num.getText().toString();
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String _dynSessConf = sp.getString("unique", "");
        String qigouliang=qigou.getText().toString();
        openAuctionPresenter.getOpenBiddingResult(productId, bidPrice, bidQuantity, _dynSessConf,qigouliang);
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

    //      获取已支付的保证金金额
    public void alreadyBaoZhengJin(boolean needShowPopupWindow) {
        Log.d("进来","即那里");
        String productId = id_value;
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String _dynSessConf = sp.getString("unique", "");
        openAuctionPresenter.getAmountPaidedResult(productId, _dynSessConf);
        if (needShowPopupWindow) {
            Popupwindow();
        }
    }

    //    公开竞拍报价
    @Override
    public void getOpenAuctionResult(String result) {
        //Loding.dis();
        if (result.equals("success")) {
            Intent intent = new Intent(OpenAuctionActivity.this, Ok_Dialog.class);
            intent.putExtra("number", SuMaoConstant.QUOTE_SUCCESS);
            intent.putExtra("type", "");
            startActivity(intent);
        }
        openAuctionPresenter.getRealTimeQuotes(productId, false);
    }

    //    密封竞拍报价
    @Override
    public void getSealedBidResult(String result) {
        Loding.dis();
        if (result.equals("success")) {
            Intent intent = new Intent(OpenAuctionActivity.this, Ok_Dialog.class);
            intent.putExtra("number", SuMaoConstant.QUOTE_SUCCESS);
            intent.putExtra("type", "");
            startActivity(intent);
        }
    }

    //    获取支付保证金时的银行列表
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
            paidDeposit = (TextView) view.findViewById(R.id.tvPrice_popupwindow_auction);
            paidDeposit.setText(zhifujine);
            amountOfMarginRequired = (EditText) view.findViewById(R.id.etZhifu_auction);
//            需保证金
            TextView totalAmount = (TextView) view.findViewById(R.id.total_amount);
            TextView count_Price = (TextView) view.findViewById(R.id.count_Price);
            totalAmount.setText("需保证金:");
//        数量*竞拍价格*保证金比例，是最小的保证金，可以输入比这个大的金额和追加保证金
//        补充保证金

            additionalMarginAmount = Double.parseDouble(all_price_num.getText().toString()) * Double.parseDouble(percent + "") - Double.parseDouble(paidDeposit.getText().toString());

            count_Price.setText(String.format("%.2f", Double.parseDouble(all_price_num.getText().toString()) * Double.parseDouble(percent + "")));
            additionalMarginAmountString = additionalMarginAmount > 0 ? additionalMarginAmount + "" : 0 + "";
            amountOfMarginRequired.setText(additionalMarginAmountString);
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
            adapter1 = new PopupWindowAdaptrer(list1, OpenAuctionActivity.this);
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
                                Toast.makeText(OpenAuctionActivity.this, "请填写支付保证金", Toast.LENGTH_SHORT).show();
                            } else {
                                zhifu_baozhengjin(amountOfMarginRequired.getText().toString(), list1.get(i).message);
                                paymentSecurityPopupWindow.dismiss();
                            }

                        } else if (i == list1.size() - 1) {
                            Toast.makeText(OpenAuctionActivity.this, "请选择的银行进行付款", Toast.LENGTH_SHORT).show();
                        }
                    }

//                    paidDeposit.setText(amountOfMarginRequired.getText().toString());
//                Intent intent=new Intent(OpenAuctionActivity.this,Ok_Dialog.class);
//                intent.putExtra("number","122");
//                startActivity(intent);
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


    //    支付保证金
    @Override
    public void setPaymentSecurityResult(String result) {
        Loding.dis();
        paymentSecurityPopupWindow.dismiss();
        if (result.contains("formError\":true")) {
            Toast.makeText(OpenAuctionActivity.this, "您支付保证金失败！", Toast.LENGTH_SHORT).show();
        } else if (result.contains("\"info\":\"sucess\"")) {
            Toast.makeText(OpenAuctionActivity.this, "您支付保证金成功！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(OpenAuctionActivity.this, Ok_Dialog.class);
            intent.putExtra("number", SuMaoConstant.MARGIN_PAYMENT_SUCCESS);
            intent.putExtra("type", "");
            startActivity(intent);
        }
        if (type_way.equals("密封报价") && !auctionDetailBean.getCl_baozhj().equals("%")) {
            bt_jinpai_colse.setVisibility(View.GONE);
            Layout_Button_Open.setVisibility(View.VISIBLE);

        }
    }

    //    获取已支付保证金金额
    @Override
    public void setAmountPaidedResult(String result) {

        try {
            JSONObject obj = new JSONObject(result);
            zhifujine = obj.getString("deposit");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (Double.parseDouble(zhifujine) == 0) {
            bt_jinpai.setVisibility(View.GONE);
        } else {
            bt_jinpai.setVisibility(View.VISIBLE);
        }

    }

    //    获取竞拍结果
    @Override
    public void setAuctionResultResult(String result) {
        Gson gson = new Gson();
        BiddingesultsBean biddingesultsBean = gson.fromJson(result, BiddingesultsBean.class);
        Log.d("竞拍结果的返回值", result);
        if (result.contains("totalAmount")) {
            line_top.setVisibility(View.VISIBLE);
            line_bottom.setVisibility(View.VISIBLE);
            line_centent.setVisibility(View.VISIBLE);
            lvdemo_result.setVisibility(View.VISIBLE);
            List<Map<String, Object>> list_result = new ArrayList<>();
            Map<String, Object> map = new Hashtable<>();
            map.put("orderId", biddingesultsBean.getTotalAmount());
            map.put("price", biddingesultsBean.getBidPrice());
            map.put("dealQut", biddingesultsBean.getDealQut());
            map.put("totalAmount", biddingesultsBean.getTotalAmount());
            list_result.add(map);
            SimpleAdapter adapter_result = new SimpleAdapter(OpenAuctionActivity.this, list_result, R.layout.item_gridview,
                    new String[]{"orderId", "totalAmount", "price", "dealQut"},
                    new int[]{R.id.tv_order_id, R.id.count, R.id.tv_price, R.id.tv_number});
            lvdemo_result.setAdapter(adapter_result);
            adapter_result.notifyDataSetChanged();
        }else{
            Layout_info.setVisibility(View.VISIBLE);
            Layout_info.setBackgroundColor(getResources().getColor(R.color.the_order_number_textview_blue));
            tv_result.setText("竞拍已结束");
            Layout_Button_no_login.setVisibility(View.GONE);
            participate_in_the_auction_ll.setVisibility(View.VISIBLE);
            Layout_Button_close.setVisibility(View.GONE);
            Layout_Button_Open.setVisibility(View.GONE);
        }

        //  user_success.setVisibility(View.VISIBLE);
//        tv_result.setText("竞拍成功");
//        price_auction = biddingesultsBean.getBidPrice();
//        number_auction = biddingesultsBean.getDealQut();
//        count_auction = biddingesultsBean.getTotalAmount();
//        order_id = biddingesultsBean.getOrderId();
//        tv_order_id.setText(order_id);
//        tv_price.setText(price_auction);
//        tv_number.setText(number_auction);
//        tv_all_price.setText(count_auction);
//        if (biddingesultsBean.getState().equals("QUOTED")) {//已支付
//            payImmediately.setVisibility(View.GONE);
//            layoutIn.setVisibility(View.GONE);
//        } else {
//            payImmediately.setVisibility(View.VISIBLE);
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.HOUR_OF_DAY, 16);
//            calendar.set(Calendar.MINUTE, 0);
//            calendar.set(Calendar.SECOND, 0);
//            long countDownTime = 0;
//            long todayOrTomorrow = (calendar.getTimeInMillis()) - Long.parseLong(biddingesultsBean.getDate());
//            long today = calendar.getTimeInMillis() - System.currentTimeMillis();
//            long tomorrow = calendar.getTimeInMillis() + 24 * 60 * 60 * 1000 - System.currentTimeMillis();
//            countDownTime = todayOrTomorrow > 0 ? today : tomorrow;
//            tvTime.setText("剩余支付时间：");
//            mc = new MyCount(countDownTime, 1000);
//            mc.start();
      //  }


    }

    //    公开竞拍的状态
    @Override
    public void setStateOfPublicBiddingResult(String result, boolean setStateOfPublicBiddingResult) {
        isbaizhengjin=setStateOfPublicBiddingResult;
        LogUtils.log("走这里--->1");
        Gson gson = new Gson();
        OpenAuctionStateBean openAuctionStateBean = gson.fromJson(result, OpenAuctionStateBean.class);
        String deposit_state = openAuctionStateBean.getDeposit_state();
        String promptLanguage = "";
        if (deposit_state.equals("n")) {
            promptLanguage = "不能参与本次竞拍";
        } else if (deposit_state.equals("s0")) {
            promptLanguage = "等待竞拍开始";
        } else if (deposit_state.equals("s1")) {
            promptLanguage = "竞拍正在进行";
        } else if (deposit_state.equals("s2")) {
            promptLanguage = "竞拍结束";
        } else if (deposit_state.equals("s3")) {
            promptLanguage = "竞拍未能成功";
        }
//        竞拍成功
        if (openAuctionStateBean.getFlag().equals("yes")) {
            LogUtils.log("走这里--->2");
            //layout_Message.setVisibility(View.GONE);
            auctionResult();
        } else {
//            竞拍失败
            LogUtils.log("走这里--->3");
            tv_result.setText(promptLanguage);
            LogUtils.log("tv_Message--->" + openAuctionStateBean.getMsg());
            //提示返回的信息
            //Toast.makeText(OpenAuctionActivity.this, ""+openAuctionStateBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
//       显示参与竞拍按钮
        if (openAuctionStateBean.getMsg().equals("attendAuction")) {
            LogUtils.log("走这里--->5");
            participate_in_the_auction_ll.setVisibility(View.VISIBLE);
            layout_Message.setVisibility(View.GONE);
        } else /*if (!status.equals("2"))*/ {
            LogUtils.log("走这里--->6");
            participate_in_the_auction_ll.setVisibility(View.GONE);
//            正在竞拍
            if (status.equals("1")) {
                LogUtils.log("走这里--->7");
                //        需要支付保证金
                LogUtils.log("setStateOfPublicBiddingResult--->" + setStateOfPublicBiddingResult);
                if (auctionDetailBean.getDepositFlag().equals("percent")) {
                    LogUtils.log("走这里--->8");
                    depositDeadline = auctionDetailBean.getDepositDeadline().getTime();
//                保证金最后期限
//                    if (Double.parseDouble(depositDeadline) - System.currentTimeMillis() < 0) {
//                        btZhifu_auction.setBackgroundColor(getResources().getColor(R.color.gray));
//                    }
                    //      保证金比例
                    String str = auctionDetailBean.getCl_baozhj();
                    LogUtils.log("需要支付保证金比例--->" + str);
//        是否需要支付保证金
                    needToPayTheMargin = !str.equals("%");
                    LogUtils.log("是否需要支付保证金--->" + needToPayTheMargin);
                    if (needToPayTheMargin) {
                        NumberFormat nf = NumberFormat.getPercentInstance();
                        try {
                            m = nf.parse(str);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        percent = String.format("%.4f", Double.parseDouble(m.toString()));
                    }

                    bt_jinpai_colse.setVisibility(View.GONE);
                    Layout_Button_Open.setVisibility(View.VISIBLE);
                    alreadyBaoZhengJin(false);
//            不需要支付保证金
                } else if (auctionDetailBean.getDepositFlag().equals("no")){
                    LogUtils.log("走这里--->8的else");
                    Layout_Button_close.setVisibility(View.VISIBLE);
                    bt_jinpai_colse.setVisibility(View.VISIBLE);
                    Layout_Button_Open.setVisibility(View.GONE);
                    bt_jinpai_colse.setText("立即报价");
                }
//            竞拍未开始
            } else if (!status.equals("2")&&state.equals("1")) {
                //需要支付保证金
                if (auctionDetailBean.getDepositFlag().equals("percent")) {
                    depositDeadline = auctionDetailBean.getDepositDeadline().getTime();
                    LogUtils.log("走这里--->10");
//                保证金最后期限
                    String depositDeadline = auctionDetailBean.getDepositDeadline().getTime();
                    if (Double.parseDouble(depositDeadline) - System.currentTimeMillis() < 0) {
                        bt_jinpai_colse.setBackgroundColor(getResources().getColor(R.color.gray));
                    }
                    bt_jinpai_colse.setText("支付保证金");
                    Layout_Button_close.setVisibility(View.VISIBLE);
                    alreadyBaoZhengJin(false);
//            不需要支付保证金
                } else if (auctionDetailBean.getDepositFlag().equals("no")){
                    LogUtils.log("走这里--->11");
                    bt_jinpai_colse.setVisibility(View.GONE);
                    //没有保证金
                    // participate_in_the_auction_ll.setVisibility(View.VISIBLE);
                }

//                String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/payment/OrderPayment/attendAuction";
//                RequestParams rp=new RequestParams(url);
//                rp.addParameter("productId",id_value);
//                rp.addParameter("_dynSessConf",unique);
//                x.http().post(rp, new Callback.CommonCallback<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//                        Log.d("判断状态的返回值",""+result);
//                        if (!result.contains("请前往'订单管理'查看您的订单，订单号")){
//                            //第一次参加竞拍
//                            participate_in_the_auction_ll.setVisibility(View.VISIBLE);
//                        }else{
//                            LogUtils.log("走这里--->9");
////        需要支付保证金
//                            if (isbaizhengjin) {
//                                depositDeadline = auctionDetailBean.getDepositDeadline().getTime();
//                                LogUtils.log("走这里--->10");
////                保证金最后期限
//                                String depositDeadline = auctionDetailBean.getDepositDeadline().getTime();
//                                if (Double.parseDouble(depositDeadline) - System.currentTimeMillis() < 0) {
//                                    bt_jinpai_colse.setBackgroundColor(getResources().getColor(R.color.gray));
//                                }
//                                bt_jinpai_colse.setText("支付保证金");
//                                Layout_Button_close.setVisibility(View.VISIBLE);
//                                alreadyBaoZhengJin(false);
////            不需要支付保证金
//                            } else {
//                                LogUtils.log("走这里--->11");
//                                bt_jinpai_colse.setVisibility(View.GONE);
//                                //没有保证金
//                               // participate_in_the_auction_ll.setVisibility(View.VISIBLE);
//                            }
//
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable ex, boolean isOnCallback) {
//                        Log.d("error的值","error");
//
//                    }
//
//                    @Override
//                    public void onCancelled(CancelledException cex) {
//                        Log.d("oncancelled的值","onCancelled");
//
//                    }
//
//                    @Override
//                    public void onFinished() {
//                        Log.d("finish的值","finish");
//
//                    }
//                });

            }


        }

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
                if (result.contains("yes")){

                    if (auctionDetailBean.getDepositFlag().equals("no")) {
                        bt_jinpai_colse.setText("立即报价");
                    } else if(auctionDetailBean.getDepositFlag().equals("percent")) {
                        participate_in_the_auction.setVisibility(View.GONE);
                        Layout_Button_close.setVisibility(View.VISIBLE);
                        bt_jinpai_colse.setText("支付保证金");
//                保证金最后期限
                        String depositDeadline = auctionDetailBean.getDepositDeadline().getTime();
                        if (Double.parseDouble(depositDeadline) - System.currentTimeMillis() < 0) {
                            bt_jinpai_colse.setBackgroundColor(getResources().getColor(R.color.gray));
                        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pay_immediately:
                payThePriceOfGoodsPopupwindow();
                backgroundAlpha(0.5f);
                break;
//            获取订单详情
            case R.id.tv_Order_id:
                openAuctionPresenter.getOpenAuctionOrderDetail(tv_order_id.getText().toString());
                break;
//            参与竞拍按钮
            case R.id.participate_in_the_auction:
                SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                String _dynSessConf = sp.getString("unique", "");
                openAuctionPresenter.getParticipateInTheAuction(auctionDetailBean.getCl_id(), auctionDetailBean.getCl_cpid(), _dynSessConf);
                break;
//            数量减
            case R.id.img_item_cart_buy_sub:
                double num1 = Double.valueOf(tv_item_cart_buy_num.getText().toString());
                num1 -= Double.valueOf(bianjiadanwei.getText().toString());
                if (Double.parseDouble(zhifujine) == 0) {

//                    if (num1 < Double.valueOf(qigou.getText().toString())) {
//                        Toast.makeText(this, "购买数量不能小于起购量", Toast.LENGTH_SHORT).show();
//                    } else {
//                        tv_item_cart_buy_num.setText(num1 + "");
//                    }
                    SharedPreferences spces=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                    shishicunzai=spces.getString("shishicunzai","");
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

                    SharedPreferences spces=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                    shishicunzai=spces.getString("shishicunzai","");
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
                all_price_num.setText(Double.parseDouble(tv_item_cart_buy_num.getText().toString()) * Double.valueOf(bid_price_num.getText().toString()) + "");
                break;
//            数量加
            case R.id.img_item_cart_buy_add:
                double num2 = Double.valueOf(tv_item_cart_buy_num.getText().toString());
                Log.d("num2的值",num2+"");
                num2 += Double.valueOf(bianjiadanwei.getText().toString());
                if (num2 > Double.valueOf(sheng.getText().toString())) {
                    Toast.makeText(this, "购买数量不能大于竞拍总量", Toast.LENGTH_SHORT).show();
                } else {
                    tv_item_cart_buy_num.setText(num2 + "");
                    all_price_num.setText(Double.parseDouble(tv_item_cart_buy_num.getText().toString()) * Double.valueOf(bid_price_num.getText().toString()) + "");
                }
                break;
//            价格减
            case R.id.bid_price_sub:
                double num3 = Double.valueOf(bid_price_num.getText().toString());
                num3 -= Double.valueOf(cangkuaddress.getText().toString());
//                 这里需要根据业务进行判断
                if (Double.parseDouble(zhifujine) == 0) {
                    if (num3 < Double.valueOf(qigou.getText().toString())) {
                        Toast.makeText(this, "购买数量不能小于起拍价", Toast.LENGTH_SHORT).show();
                    } else {
                        bid_price_num.setText(num3 + "");
                    }
                } else {
                    SharedPreferences sharedPreferences=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                    shishicunzai=sharedPreferences.getString("shishicunzai","");
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
//                    if (realTimeQuotesIncludeMyOffer) {
//                        if (num3 < Double.parseDouble(myOfferBean.getMyPrice().get(0).getBidPrice())) {
//                            bid_price_num.setText(myOfferBean.getMyPrice().get(0).getBidPrice());
//                        } else {
//                            bid_price_num.setText(num3 + "");
//                        }
//
//                    } else {
//                        if (myOfferBean.getMybidQut() == null) {
//                            if (Double.parseDouble(bid_price_num.getText().toString()) < Double.parseDouble(price.getText().toString()))
//                                bid_price_num.setText(price.getText().toString());
//                        } else if (myOfferBean.getMybidQut().equals("0")) {
//                            if (num3 < Double.parseDouble(price.getText().toString())) {
//                                bid_price_num.setText(price.getText().toString());
//                            } else {
//                                bid_price_num.setText(num3 + "");
//                            }
//
//                        } else {
//                            if (num3 < Double.parseDouble(realTimeMardet.getAuctionPricelist().get(realTimeMardet.getAuctionPricelist().size() - 1).getBidPrice())) {
//                                bid_price_num.setText(realTimeMardet.getAuctionPricelist().get(realTimeMardet.getAuctionPricelist().size() - 1).getBidPrice());
//                            } else {
//                                bid_price_num.setText(num3 + "");
//                            }
//
//                        }
                   // }

                }
                all_price_num.setText(Double.valueOf(bid_price_num.getText().toString()) * Double.valueOf(tv_item_cart_buy_num.getText().toString()) + "");
                break;
//            价格加
            case R.id.bid_price_add:
                double num4 = Double.valueOf(bid_price_num.getText().toString());
                num4 += Double.valueOf(cangkuaddress.getText().toString());
                bid_price_num.setText(num4 + "");
                Log.d("tv_item_buy_num",tv_item_cart_buy_num.getText().toString());
                all_price_num.setText(Double.valueOf(bid_price_num.getText().toString()) * Double.valueOf(tv_item_cart_buy_num.getText().toString()) + "");
                break;
        }
    }

    //    获取支付后订单详情
    @Override
    public void getOpenAuctionOrderDetail(String result) {
        Gson gson = new Gson();
        OpenAuctionDetailBean openAuctionDetailBean = gson.fromJson(result, OpenAuctionDetailBean.class);
        Intent intent = new Intent();
        intent.putExtra("OrderBean", openAuctionDetailBean.getOrder());
        intent.setClass(OpenAuctionActivity.this, SpotOrder.class);
        startActivity(intent);
    }


    private void payThePriceOfGoodsPopupwindow() {
        payThePriceOfGoodsGetInfoBank();
    }

    //获取银行列表
    public void payThePriceOfGoodsGetInfoBank() {
        String orderId = tv_order_id.getText().toString();
        list_order.add(orderId);
        String order = null;
       // Loding.show(this, "加载中...", false, null);
        openAuctionPresenter.getBankListFromService(order, orderId);
    }


    //    支付完成后要操作的
    @Override
    public void getPaymentResult(String result, String orderIds) {
        Loding.dis();
        try {
            JSONObject obj = new JSONObject(result);
            String state = obj.getString("paymentStatus");
            if (state.equals("0")) {
                //全部成功
                Intent intent = new Intent(OpenAuctionActivity.this, Ok_Dialog.class);
                intent.putExtra("type", "公开竞拍");
                intent.putExtra("number", "这里填写订单号");
                startActivity(intent);
            } else if (state.equals("1")) {
                //部分订单成功
                Toast.makeText(OpenAuctionActivity.this, "部分订单成功,到订单详情界面查看", Toast.LENGTH_SHORT).show();
            } else if (state.equals("2")) {
                //全部失败
                Intent intent = new Intent(OpenAuctionActivity.this, Error_Dialog.class);
                intent.putExtra("number", "这里填写订单号");
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

    @Override
    public void onStart() {
        super.onStart();
        Log.d("先走这个方法","先走这个方法");
        sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        unique=sp.getString("unique","");

        booleanOpenorClose();
        if (type_way.equals("公开竞拍")) {
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    //getinfo();
                    switch (msg.what) {
                        case 1:
                            selecttimer();
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

    @Override
    protected void onStop() {
        super.onStop();
        if (type_way.equals("公开竞拍")){
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
//            tvTimeValue.setText("");
//            payImmediately.setVisibility(View.GONE);
//            layoutIn.setVisibility(View.GONE);
//            if (status.equals("") || status.equals("0")) {
//                if (Double.parseDouble(zhifujine) == 0 && type_way.equals("公开竞拍")) {
//                    btZhifu_auction.setBackgroundColor(getResources().getColor(R.color.gray_type));
//                    bt_jinpai.setBackgroundColor(getResources().getColor(R.color.gray_type));
//                } else {
//                    //Popupwindow();
//                }
//                Layout_Button_Open.setVisibility(View.VISIBLE);
//                Layout_Button_close.setVisibility(View.GONE);
//            }

        }

        @Override
        public void onTick(long millisUntilFinished) {
            String time = formatTime(millisUntilFinished /*- TimeZone.getDefault().getRawOffset()*/);
            tvTimeValue.setText("" + time);
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
            sb.append(hour + "小时");
        }
        if (minute > 0) {
            sb.append(minute + "分");
        }
        if (second > 0) {
            sb.append(second + "秒");
        }

        return sb.toString();
    }

    //    获取我的报价
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
                Toast.makeText(OpenAuctionActivity.this, "竞拍单价必须大于等于您当前的自报价或数量大于已在实时行情中的数量。", Toast.LENGTH_LONG).show();
            } else {
                gongkai_baojia();
            }
        }
    }

    //    获取实时行情
    @Override
    public void setRealTimeQuotesResult(String result, boolean offer) {
        Loding.dis();
        String productId = auctionDetailBean.getCl_id();
        String type = auctionDetailBean.getCl_type();
        sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String _dynSessConf = sp.getString("unique", "");

        openAuctionPresenter.getMyOfferResult(_dynSessConf, productId, type, offer);
        realTimeQuotes = result;
        Gson gson = new Gson();
        realTimeMardet = gson.fromJson(realTimeQuotes, RealTimeMardet.class);
        List<RealTimeMardet.AuctionPricelistBean> auctionPricelist = realTimeMardet.getAuctionPricelist();
        String u_clientID = getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("U_clientID", "");

        for (int i = 0; i < auctionPricelist.size(); i++) {
            RealTimeMardet.AuctionPricelistBean auctionPricelistBean = auctionPricelist.get(i);
            if (u_clientID.equals(auctionPricelistBean.getBuyerId())) {
                realTimeQuotesIncludeMyOffer = true;
            }
        }
        if (offer && !realTimeQuotesIncludeMyOffer) {
            if (Double.parseDouble(bid_price_num.getText().toString()) <= Double.parseDouble(shishiprice) &&
                    Double.parseDouble(tv_item_cart_buy_num.getText().toString()) <= Double.parseDouble(shishicjnumber)) {
                if (Double.parseDouble(bid_price_num.getText().toString()) <= Double.parseDouble(myOfferBean.getMyPrice().get(0).getBidPrice())) {
                    Toast.makeText(OpenAuctionActivity.this, "竞拍单价必须大于等于您当前的自报价。", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OpenAuctionActivity.this, "您当前输入的数量不能小于等于已在实时行情中的数量。", Toast.LENGTH_SHORT).show();
                }
            } else {
                gongkai_baojia();
            }
        }

    }

    //    点击参加竞拍
    @Override
    public void setParticipateInTheAuction(String result) {
        if (result.contains("YES")) {
            //        不需要支付保证金
            if (auctionDetailBean.getCl_baozhj().equals("%")) {
                bt_jinpai_colse.setText("立即报价");
            } else {
                participate_in_the_auction.setVisibility(View.GONE);
                Layout_Button_close.setVisibility(View.VISIBLE);
                bt_jinpai_colse.setText("支付保证金");
//                保证金最后期限
                String depositDeadline = auctionDetailBean.getDepositDeadline().getTime();
                if (Double.parseDouble(depositDeadline) - System.currentTimeMillis() < 0) {
                    bt_jinpai_colse.setBackgroundColor(getResources().getColor(R.color.gray));
                }
            }

        }

    }

    //实时行情查看
    public void shishihangqingSelect(){

        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/auctionProductPriceList";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("productId", productId);

        Log.d("实时行情的rp",""+rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("实施行情的返回值", result);
                u_clientID = getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("U_clientID", "");
                if (result.contains("auctionPricelist")){
                    Log.d("if","if");
                    try {
                        JSONObject jsonobject=new JSONObject(result);
                        String auctionPricelist=jsonobject.getString("auctionPricelist");
                        JSONArray jsonArray=new JSONArray(auctionPricelist);
                        for (int i=0;i<jsonArray.length();i++){
                            Log.d("jsonfor","jsonfor");
                            JSONObject object=jsonArray.getJSONObject(i);
                            Log.d("userid",u_clientID+"u_clientId");
                            if (object.toString().contains(u_clientID)){
                                Log.d("解析进来了","解析进来了");
                                shishiprice=object.getString("bidPrice");
                                shishinumber=object.getString("bidQut");
                                shishicjnumber=object.getString("dealQut");
                                Log.d("解析的值",shishiprice+shishicjnumber+shishinumber);
                                sp= getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                //editor.putBoolean("shishicunzai", true);
                                editor.putString("shishicunzai","true");
                                editor.commit();
                                //editor.commit();

                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //setValue();
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

    //定时器再次查看详情
    public void selecttimer(){
        //iscunhuan=true;
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/auctionProduct";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("productId",productId);
        rp.addParameter("proType",proType);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result.contains("cl_xbianjia")){
                    Gson gson=new Gson();
                    auctionDetailBean = gson.fromJson(result, AuctionDetailBean.class);
                    //shishihangqingSelect();
                    setValue();
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
