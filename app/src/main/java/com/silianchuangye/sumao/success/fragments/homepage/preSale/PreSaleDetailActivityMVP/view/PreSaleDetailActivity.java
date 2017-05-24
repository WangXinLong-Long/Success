package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.HX.Constant;
import com.silianchuangye.sumao.success.HX.ui.LoginActivity;
import com.silianchuangye.sumao.success.MainActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.custom.customCalendar.CalendarView;
import com.silianchuangye.sumao.success.custom.customCalendar.DayAndPrice;
import com.silianchuangye.sumao.success.dialog.Error_Dialog;
import com.silianchuangye.sumao.success.dialog.Ok_Dialog;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;
import com.silianchuangye.sumao.success.fragments.homepage.auction.VesselThreeActivity;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleBondBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailCalendarBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.Sku;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.presenter.PreSaleDetailPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;
import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
public class PreSaleDetailActivity extends Activity implements View.OnClickListener, IPreSaleDetailView,OnLayoutChangeListener {
    CalendarView calendarView;
    ImageView title_bar_white_back;
    TextView title_bar_white_title;
    ImageView title_bar_white_shopping_cart;
    String riliDate;

    private String productId;
    private String skuId;
    private PreSaleDetailPresenter preSaleDetailPresenter;
    private TextView tvName_auction;
    private TextView tvPrice_auction;
    private TextView pre_sale_detail_integral_rule;
    private TextView surplus_amount_et;
    private TextView purchase_quantity_et;
    private TextView min_variable_et;
    private TextView delivery_time_et;
    private TextView warehouse_address_et;
    private TextView delivery_mode_et;
    private TextView classification_pre_sale_et;
    private TextView warehouse_et;
    private TextView region_et;
    private TextView company_et;
    private TextView margin_proportion_et;
    private TextView pre_sale_detail_remark;
    private TextView delivery_time_et_end;
    private int year;
    private int mounth;
    private int day;
    private List<DayAndPrice> calendarlist;
    private Button payment_security;
    private EditText et;
    private ListView lv;
    private PopupWindowAdaptrer adapter;
    private List<OpenAuction> list1 = new ArrayList<>();
    private TextView tv;
    private RelativeLayout pre_sale_sale_detail_detail;
    private ArrayList<GoodsInStockDetailBean.ClAttributeBean> cl_attribute;
    private String newSkuId = "";
    private double BZprice;
    private EditText edt_num;//购买数量
    private String cl_jiner;
    private TextView img_item_cart_buy_add, img_item_cart_buy_sub, tv_item_cart_all_price;
    private LinearLayout layoutService;
    private PreSaleDetailBean preSaleDetailBean;
    private String contractString;
    private boolean canPayTheMargin;
    private TextView payment_deadline_et;
    private LinearLayout remark;
    private Button payment_security_closeMerket;
    private String unique;
    private String cl_gongsiId;
    private RelativeLayout parent;
    private String xianshi="";
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_sale_detail);

        Intent intent = getIntent();
        skuId = intent.getStringExtra("skuId");
        LogUtils.log("skuId---->" + skuId);
        payment_security_closeMerket= (Button) findViewById(R.id.payment_security_closeMerket);
        preSaleDetailBean = (PreSaleDetailBean) intent.getSerializableExtra("preSaleDetailBean");
        productId = preSaleDetailBean.getCl_id();

        Calendar calendar = Calendar.getInstance();
        calendarlist = new ArrayList<>();
//        从服务器获取数据
        preSaleDetailPresenter = new PreSaleDetailPresenter(this);
//        preSaleDetailPresenter.sendPreSaleDetailData(skuId, productId);
        preSaleDetailPresenter.sendPreSaleDetailCalendar(preSaleDetailBean.getCl_id());
        calendarView = (CalendarView) findViewById(R.id.calendarView);
//        一定要在初始化控件以前把数据加载完毕了
        calendarView.setListDayAndPrice(calendarlist);
        calendarView.invalidate();


//        calendarView.invalidate();
        calendarView.setDateViewClick(new CalendarView.DateViewClick() {

            @Override
            public void dateClick() {
                Integer selectday = calendarView.getSelectDay();
                Integer selectmonth = calendarView.getSelectMonth() + 1;
                Integer selectyear = calendarView.getSelectYear();
                LogUtils.log("选择的日期为：" + selectyear + "年" + selectmonth + "月" + selectday + "日");
                for (int i = 0; i < calendarlist.size(); i++) {
                    Integer day = calendarlist.get(i).getDay();
                    Integer month = calendarlist.get(i).getMonth();
                    Integer year = calendarlist.get(i).getYear();
                    LogUtils.log("List的日期为：" + year + "年" + month + "月" + day + "日");
                    if (selectday.equals(day) && selectmonth.equals(month) && selectyear.equals(year)) {
                        newSkuId = calendarlist.get(i).getSkuId();
                        cl_jiner = calendarlist.get(i).getPrice().substring(1, calendarlist.get(i).getPrice().length());
                    }
                }
                if (newSkuId.equals("") || newSkuId.isEmpty()) {
                    Toast.makeText(PreSaleDetailActivity.this, "出错了", Toast.LENGTH_SHORT).show();
                } else {
                    preSaleDetailPresenter.sendPreSaleDetailData(newSkuId, productId);
                }

            }
        });
        initView();
        new Thread(){
            @Override
            public void run() {
                super.run();
                booleanMarket();
            }
        }.start();
        initListener();
        setPreSaleDetailData(preSaleDetailBean);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique = sp.getString("unique", "");
        Log.d("unique的值",unique);
    }

    private void initListener() {
        payment_security.setOnClickListener(this);
        title_bar_white_back.setOnClickListener(this);
        title_bar_white_shopping_cart.setOnClickListener(this);
        pre_sale_sale_detail_detail.setOnClickListener(this);
        parent.addOnLayoutChangeListener(this);
    }

    private void initView() {
        parent= (RelativeLayout) findViewById(R.id.parent);
       //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight/3;
        payment_security = (Button) findViewById(R.id.payment_security);
        title_bar_white_back = ((ImageView) findViewById(R.id.title_bar_white_back));
        title_bar_white_title = ((TextView) findViewById(R.id.title_bar_white_title));
        title_bar_white_shopping_cart = ((ImageView) findViewById(R.id.title_bar_white_shopping_cart));
        pre_sale_sale_detail_detail = ((RelativeLayout) findViewById(R.id.pre_sale_sale_detail_detail));
        //        兰州石化7042
        tvName_auction = ((TextView) findViewById(R.id.tvName_auction));
        //        6000
        tvPrice_auction = ((TextView) findViewById(R.id.tvPrice_auction));

        //        积分规则:每吨商品积一分
        pre_sale_detail_integral_rule = ((TextView) findViewById(R.id.pre_sale_detail_integral_rule));
        //        剩余数量
        surplus_amount_et = ((TextView) findViewById(R.id.surplus_amount_et));
        //        起购量
        purchase_quantity_et = ((TextView) findViewById(R.id.purchase_quantity_et));
        //        最小变量单位
        min_variable_et = ((TextView) findViewById(R.id.min_variable_et));
        //       交货时间

        delivery_time_et = ((TextView) findViewById(R.id.delivery_time_et));
        delivery_time_et_end = ((TextView) findViewById(R.id.delivery_time_et_end));
        //       仓库地址
        warehouse_address_et = ((TextView) findViewById(R.id.warehouse_address_et));
        //       交货方式
        delivery_mode_et = ((TextView) findViewById(R.id.delivery_mode_et));
        //       分类
        classification_pre_sale_et = ((TextView) findViewById(R.id.classification_pre_sale_et));
        //      仓库
        warehouse_et = ((TextView) findViewById(R.id.warehouse_et));
        //      地区
        region_et = ((TextView) findViewById(R.id.region_et));
        //      公司
        company_et = ((TextView) findViewById(R.id.company_et));
        //      保证金比例
        margin_proportion_et = ((TextView) findViewById(R.id.margin_proportion_et));
        //        产品备注
        pre_sale_detail_remark = ((TextView) findViewById(R.id.pre_sale_detail_remark));
//        货款支付截止时间
        payment_deadline_et = (TextView) findViewById(R.id.payment_deadline_et);
        remark = (LinearLayout) findViewById(R.id.remark);
        //购买数量
        edt_num = (EditText) findViewById(R.id.tv_item_cart_buy_num);

        //        TODO !!!
        edt_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    edt_num.setText("0");
                    edt_num.setSelection(1);
                }
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 3) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 4);
                        edt_num.setText(s);
                        edt_num.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    edt_num.setText(s);
                    edt_num.setSelection(3);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        edt_num.setText(s.subSequence(1, s.length()));
                        edt_num.setSelection(1);
                        return;
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Double.parseDouble(edt_num.getText().toString()) > Double.parseDouble(surplus_amount_et.getText().toString())) {
                    edt_num.setText((surplus_amount_et.getText().toString()));
                    edt_num.setSelection(surplus_amount_et.getText().toString().length());

                }

                if (!edt_num.getText().toString().equals("")) {
                    double num = (Double.parseDouble(edt_num.getText().toString()));
                    if (tvPrice_auction.getText().toString() != null && !tvPrice_auction.getText().toString().equals("")) {
                        double price_num = (Double.parseDouble(tvPrice_auction.getText().toString()));
                        double x = num * price_num;
                        DecimalFormat df = new DecimalFormat("#.##");
                        tv_item_cart_all_price.setText(df.format(x) + "元");

                    }

                }
            }
        });
        img_item_cart_buy_add = (TextView) findViewById(R.id.img_item_cart_buy_add);
        img_item_cart_buy_sub = (TextView) findViewById(R.id.img_item_cart_buy_sub);
        tv_item_cart_all_price = (TextView) findViewById(R.id.tv_item_cart_all_price);
        img_item_cart_buy_add.setOnClickListener(this);
        img_item_cart_buy_sub.setOnClickListener(this);
//        Jobs Created 联系客服
        layoutService = ((LinearLayout) findViewById(R.id.layoutService));
        layoutService.setOnClickListener(this);

    }

    public void setPreSaleDetailData(PreSaleDetailBean preSaleDetailBean) {
        if (preSaleDetailBean.getCl_cpbeizhu() == null) {
            remark.setVisibility(View.GONE);
        } else {
            remark.setVisibility(View.VISIBLE);
            pre_sale_detail_remark.setText(preSaleDetailBean.getCl_cpbeizhu());
        }
        //        兰州石化7042
        tvName_auction.setText(preSaleDetailBean.getCl_mingcheng());
        //        6000
        tvPrice_auction.setText(preSaleDetailBean.getCl_jiner() + "");
        //        积分规则:每吨商品积一分
        pre_sale_detail_integral_rule.setText(preSaleDetailBean.getCl_jifen());
        //        剩余数量
        surplus_amount_et.setText(preSaleDetailBean.getCl_shuliang());
        //        起购量
        purchase_quantity_et.setText(preSaleDetailBean.getCl_qigou());
        //        最小变量单位
        min_variable_et.setText(preSaleDetailBean.getCl_xiaobian());
       // payment_deadline_et.setText(/*preSaleDetailBean.getCl_xiaobian()*/"~~");
        //       交货时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Log.d("货款支付截止时间的",preSaleDetailBean.getPaymentEndDate());
        Log.e("货款支付截止时间的",simple.format(Double.parseDouble(preSaleDetailBean.getPaymentEndDate())));
        payment_deadline_et.setText(simple.format(Double.parseDouble(preSaleDetailBean.getPaymentEndDate())));
        delivery_time_et.setText(simpleDateFormat.format(Double.parseDouble(preSaleDetailBean.getDeliveryPeriodStart() + "")));
        delivery_time_et_end.setText(simpleDateFormat.format(Double.parseDouble(preSaleDetailBean.getDeliveryPeriodEnd() + "")));
        //       仓库地址
        warehouse_address_et.setText(preSaleDetailBean.getCl_ckdizhi());
        //       交货方式
        StringBuilder sb = new StringBuilder();
        int jhfssize = 0;
        if (preSaleDetailBean.getCl_jhfangshi() == null) {
            jhfssize = 0;
        } else {
            jhfssize = preSaleDetailBean.getCl_jhfangshi().size();
        }
        for (int i = 0; i < jhfssize; i++) {
            sb.append(preSaleDetailBean.getCl_jhfangshi().get(i));
            if (i != jhfssize - 1) {
                sb.append("、");
            }
        }
        cl_gongsiId=preSaleDetailBean.getCl_qiyeId();
        delivery_mode_et.setText(sb.toString());
        //       分类
        classification_pre_sale_et.setText(preSaleDetailBean.getCl_fenlei());
        //      仓库
        warehouse_et.setText(preSaleDetailBean.getCl_cangku());
        //      地区
        region_et.setText(preSaleDetailBean.getCl_diqu());
        //      公司
        company_et.setText(preSaleDetailBean.getCl_qiye());
        //      保证金比例
        margin_proportion_et.setText(preSaleDetailBean.getCl_baozhj());
        //        产品备注

        contractString = preSaleDetailBean.getContract();
        //        获取产品参数
        cl_attribute = preSaleDetailBean.getCl_attribute();
        edt_num.setText(purchase_quantity_et.getText().toString());
        tv_item_cart_all_price.setText(Double.valueOf(edt_num.getText().toString()) * Double.valueOf(tvPrice_auction.getText().toString()) + "");

//        Jobs Created      联系客服跳转 TODO:  需要cl_gongsiId
//        layoutService.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                Intent intentHX = new Intent();
//                intentHX.setClass(PreSaleDetailActivity.this, LoginActivity.class);
//                intentHX.putExtra(Constant.MESSAGE_TO_INTENT_EXTRA, Constant.MESSAGE_TO_DEFAULT);
////                传入卖家id
//                intentHX.putExtra(Constant.IM_SERVICE_NUMBER, cl_gongsiId);
//                startActivity(intentHX);
//            }
//        });

        //计算保证金
        payBao();
//        String bilistr=margin_proportion_et.getText().toString();
//        String newStr = bilistr.replaceAll("%","");
//        double bili=Double.valueOf(newStr)/100;
//        int num=Integer.valueOf(edt_num.getText().toString());
//        double price=Double.valueOf(tvPrice_auction.getText().toString());
//        BZprice=bili*num*price;//保证金=价格*数量*保证金比例
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.title_bar_white_back:
                finish();
                break;
            case R.id.title_bar_white_shopping_cart:

                intent.setClass(PreSaleDetailActivity.this, MainActivity.class);
                intent.putExtra("cart", 1);
                startActivity(intent);
                break;
            case R.id.pre_sale_sale_detail_detail:
                intent.setClass(PreSaleDetailActivity.this, VesselThreeActivity.class);
                intent.putExtra("title", "预售");
                intent.putExtra("contract", preSaleDetailBean.getContract());
                intent.putExtra("auctionDetailBean", preSaleDetailBean);
                intent.putExtra("cl_attribute", cl_attribute);
                startActivity(intent);
                break;
            case R.id.img_item_cart_buy_add://数量++
                double num = 0.0;
                if (!edt_num.getText().toString().equals("")) {
                    num = Double.valueOf(edt_num.getText().toString());
                }
                num += Double.valueOf(preSaleDetailBean.getCl_xiaobian());
                if (num > Double.valueOf(preSaleDetailBean.getCl_shuliang())) {
                    edt_num.setText(preSaleDetailBean.getCl_shuliang());
                } else {
                    int beishu=(int)div(num,Double.parseDouble(preSaleDetailBean.getCl_xiaobian()),1);
                    Log.d("倍数的值",beishu+"");
                    if (beishu<=0){
                        edt_num.setText(preSaleDetailBean.getCl_qigou());
                    }else {
                        Double ji = mul(beishu, Double.parseDouble(preSaleDetailBean.getCl_xiaobian()));
                        Log.d("即的值", ji + "");
                        edt_num.setText(ji + "");
                    }

//                    BigDecimal b = new BigDecimal(mul(Double.parseDouble(edt_num.getText().toString()),Double.parseDouble(preSaleDetailBean.getCl_jiner())));
//                    double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    // viewHolder.price.setText(f1 + "");
                    //tv_item_cart_all_price.setText(f1+"元");
                   // tv_item_cart_all_price.setText(mul(Double.parseDouble(edt_num.getText().toString()),Double.parseDouble(preSaleDetailBean.getCl_jiner()))+"元");
                  //  tv_item_cart_all_price.setText(df.format(Double.parseDouble(edt_num.getText().toString())*Double.parseDouble(preSaleDetailBean.getCl_jiner())+""));
                    payBao();
                }
                break;
            case R.id.img_item_cart_buy_sub:
                Log.e("TAG", "小于");
                double num1 = Double.valueOf(edt_num.getText().toString());
                num1 -= Double.valueOf(preSaleDetailBean.getCl_xiaobian());
                if (num1 < Double.valueOf(preSaleDetailBean.getCl_qigou())) {
                    edt_num.setText(preSaleDetailBean.getCl_qigou());
                } else {
                    int beishu=(int)div(num1,Double.parseDouble(preSaleDetailBean.getCl_xiaobian()),1);
                    Log.d("倍数的值",beishu+"");
                    if (beishu<=0){
                        edt_num.setText(preSaleDetailBean.getCl_qigou());
                    }else {
                        Double ji = mul(beishu, Double.parseDouble(preSaleDetailBean.getCl_xiaobian()));
                        Log.d("即的值", ji + "");
                        edt_num.setText(ji + "");
                    }
//                    BigDecimal b = new BigDecimal(mul(Double.parseDouble(edt_num.getText().toString()),Double.parseDouble(preSaleDetailBean.getCl_jiner())));
//                    double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//                    // viewHolder.price.setText(f1 + "");
//                    tv_item_cart_all_price.setText(f1+"");
//
//                   // tv_item_cart_all_price.setText(mul(Double.parseDouble(edt_num.getText().toString()),Double.parseDouble(preSaleDetailBean.getCl_jiner()))+"元");
//                    //tv_item_cart_all_price.setText(df.format(Double.parseDouble(edt_num.getText().toString())*Double.parseDouble(preSaleDetailBean.getCl_jiner())+""));
                    payBao();
                }
                break;
//            点击的 支付保证金 按钮
            case R.id.payment_security:
                /**
                 * 这里需要弹出popupWindow，即支付的弹出窗
                 */
                Log.d("预售的唯一标识",unique);
                if (unique.equals("false") || unique.equals("")) {
                    tuichu();
                    Intent intent1 = new Intent(PreSaleDetailActivity.this, LoginUserActivity.class);
                    intent1.putExtra("roles", "buyer");
                    startActivity(intent1);
                } else {
                    //在这里首先判断客户是否有权限
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            booleanJurisdiction(productId);
                        }
                    }.start();

                }
                break;
            case R.id.layoutService:
                Log.d("预售的唯一标识",unique);
                if (unique.equals("false") || unique.equals("")) {
                    tuichu();
                    Intent intent1 = new Intent(PreSaleDetailActivity.this, LoginUserActivity.class);
                    intent1.putExtra("roles", "buyer");
                    startActivity(intent1);
                } else {
                    Intent intentHX = new Intent();
                    intentHX.setClass(PreSaleDetailActivity.this, LoginActivity.class);
                    intentHX.putExtra(Constant.MESSAGE_TO_INTENT_EXTRA, Constant.MESSAGE_TO_DEFAULT);
//                传入卖家id
                    //intentHX.putExtra(Constant.IM_SERVICE_NUMBER, cl_gongsiId);
                    intentHX.putExtra(Constant.IM_SERVICE_NUMBER, preSaleDetailBean.getCl_qiyeonlyId());
                    Log.d("公司的唯一标识",preSaleDetailBean.getCl_qiyeonlyId()+"是公司id");
                    intentHX.putExtra("gongsiName",preSaleDetailBean.getCl_qiye());
                    startActivity(intentHX);


                }
                break;

        }
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
                Log.d("退出时的result",result);
                SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.clear();
                //      editor.clear();
                editor.commit();
               // Toast.makeText(context, "用户名称已删除", Toast.LENGTH_SHORT).show();

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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==101){
//            unique=data.getStringExtra("unique");
//            Log.d("返回过的unique",unique);
//        }
//    }

    //判断用户是否有权限购买
    public void booleanJurisdiction(String Id){
        String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/userprofiling/PurchaseJurisdictionCheckActor/PurchaseResult";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("productId",Id);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("用户是否有权限",result);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    String allow=jsonObject.getString("cl_allow");
                    Log.d("用户权限的返回值",result);
                    if (allow.equals("0")){
                        //没有权限问题，可以放心购买
                        if (canPayTheMargin) {//根据预售日历的情况，判断是否可以支付保证金
                            if (riliDate != null) {
                                if (edt_num.getText().toString().equals("")) {
                                    Toast.makeText(PreSaleDetailActivity.this, "购买数量不能为空", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (Double.valueOf(edt_num.getText().toString()) < Double.valueOf(purchase_quantity_et.getText().toString())) {
                                        Toast.makeText(PreSaleDetailActivity.this, "购买数量不能小于起购量", Toast.LENGTH_SHORT).show();
                                    } else if (Double.valueOf(edt_num.getText().toString()) > Double.valueOf(surplus_amount_et.getText().toString())) {
                                        Toast.makeText(PreSaleDetailActivity.this, "购买数量不能大于剩余数量", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Popupwindow();
//                                backgroundAlpha(0.5f);
                                    }
                                }

                            }
                        } else {
                            Toast.makeText(PreSaleDetailActivity.this, "该产品暂无预售日期", Toast.LENGTH_SHORT).show();
                        }

                    }else if (allow.equals("1")){
                        Toast.makeText(PreSaleDetailActivity.this, "该用户无上游资源方购买资质", Toast.LENGTH_SHORT).show();
                    }else if (allow.equals("2")){
                        Toast.makeText(PreSaleDetailActivity.this, "黑名单不能购买", Toast.LENGTH_SHORT).show();
                    }else if (allow.equals("3")){
                        Toast.makeText(PreSaleDetailActivity.this, "用户没有登录", Toast.LENGTH_SHORT).show();
                        tuichu();
                        Intent intent1 = new Intent(PreSaleDetailActivity.this, LoginUserActivity.class);
                        intent1.putExtra("roles", "buyer");
                        startActivity(intent1);
                    }else if (allow.equals("4")){
                        Toast.makeText(PreSaleDetailActivity.this, "没有在购买期限内，不能购买", Toast.LENGTH_SHORT).show();
                    }else if(allow.equals("5")){
                        Toast.makeText(PreSaleDetailActivity.this, "csc审核没有通过", Toast.LENGTH_SHORT).show();
                    }else if (allow.equals("6")){
                        Toast.makeText(PreSaleDetailActivity.this, "企业财务员无权购买产品", Toast.LENGTH_SHORT).show();
                    }else if (allow.equals("7")){
                        Toast.makeText(PreSaleDetailActivity.this, "库存小于购买量", Toast.LENGTH_SHORT).show();
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

    //设置背景透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    PopupWindow popupWindow;

    public void Popupwindow() {
        getinfo_Bank();
    }

    public void getinfo_Bank() {
        Loding.show(this, "加载中...", false, null);
        preSaleDetailPresenter.getBankListFromService(productId);


    }

    //预售支付保证金--没写完
    private void payMoney() {

        String str, blankname = "";
        if (list1 != null) {
            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i).Flag) {
                    str = list1.get(i).tv_Name;
                    Log.e("TAG", "str----" + str);
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
        if (blankname.equals("")) {
            Toast.makeText(PreSaleDetailActivity.this, "请选择支付银行", Toast.LENGTH_SHORT).show();

        } else {
            Loding.show(this, "正在支付保证金...", false, null);//网络请求之前调用
            String quantity = edt_num.getText().toString();//数量
            SharedPreferences sp = this.getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            String _dynSessConf = sp.getString("unique", "");
            String paymentPlatform = blankname;
            LogUtils.log("skuId," + skuId + "  productId," + productId + "  quantity," +
                    quantity + "  _dynSessConf," + _dynSessConf + "  paymentPlatform," + paymentPlatform);
//            TODO 差skuId，预售日历
//             TODO 修改支付保证金接口？？？？
            preSaleDetailPresenter.getPaymentSecurityResult(tv.getText().toString(), (Integer.parseInt(quantity)*1000)+"", productId, paymentPlatform, skuId,_dynSessConf);

        }
    }


    private void payBao() {
        //String bilistr = margin_proportion_et.getText().toString();
        String bilistr=preSaleDetailBean.getCl_baozhj();
        String newStr = bilistr.replaceAll("%", "");
        double bili = Double.valueOf(newStr) / 100;
        double num = Double.valueOf(edt_num.getText().toString());
//        TODO !!!
        double price = Double.valueOf(preSaleDetailBean.getCl_jiner());
        BZprice = bili * num * price;
        Log.e("TAG", "num*price=" + num * price);
        Log.d("tag",bili * Double.parseDouble(edt_num.getText().toString()) * price+"");

        BigDecimal b = new BigDecimal(mul(Double.parseDouble(edt_num.getText().toString()),Double.parseDouble(preSaleDetailBean.getCl_jiner())));
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        // viewHolder.price.setText(f1 + "");
        tv_item_cart_all_price.setText(f1+"元");
       // String.format("%.2f", BZprice)
    }


    @Override
    public void getPreSaleDetailData(PreSaleDetailBean preSaleDetailBean, String skuId) {
        setPreSaleDetailData(preSaleDetailBean);
    }

    //    预售日历的
    @Override
    public void getPreSaleDetailCalendarData(PreSaleDetailCalendarBean preSaleDetailCalendarBean/*,int position*/) {
        List<Sku> skus = preSaleDetailCalendarBean.getSku();
        if (skus == null) {
            canPayTheMargin = false;
            payment_security.setBackgroundColor(getResources().getColor(R.color.gray));
        } else {
            canPayTheMargin = true;
            LogUtils.log("lists" + skus.toString());
//        List<DayAndPrice> list = new ArrayList<DayAndPrice>();
            StringBuffer cacheSb = new StringBuffer();
            cacheSb.append("00000000");
//            cacheSb.append(preSaleDetailBean.getPaymentEndDate());
            double cachevalue = Double.parseDouble(cacheSb + "");
            int cacheyear = 0;
            int cachemonth = 0;
            int cacheday = 0;
            String cacheSKUId = "";
            for (int i = 0; i < skus.size(); i++) {
                Sku sku = skus.get(i);
                String[] data = sku.getCl_date().split("-");
                LogUtils.log("sku.getCl_jiner()-->" + sku.getCl_jiner() + "<---new Integer(data[0]),new Integer(data[1]),new Integer(data[2])-->" + data[0] + "---" + data[1] + "---" + data[2]);
                String skuId = sku.getSkuId();
                try {
                    year = Integer.parseInt(data[0]);
                    mounth = Integer.parseInt(data[1]);
                    day = Integer.parseInt(data[2]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("没有转换成功");
                }
                riliDate = year + "..." + mounth + "..." + day;
                Log.e("riliDate的值",riliDate);
                LogUtils.log(year + "..." + mounth + "..." + day);
                calendarlist.add(new DayAndPrice("￥" + sku.getCl_jiner(), year, mounth, day, skuId));
                StringBuffer sb = new StringBuffer();
                sb.append(String.valueOf(year));
                sb.append(String.valueOf(mounth));
                sb.append(String.valueOf(day));
                double value = Double.parseDouble(sb + "");
                Log.e("sb的值",value+"");
                Log.e("cachevalue的值",cachevalue+"");
                if (value > cachevalue) {
                    cacheSb = sb;
                    cacheyear = year;
                    cachemonth = mounth;
                    cacheday = day;
                    cacheSKUId = skuId;
                }

            }
            LogUtils.log("skuId-->" + cacheSKUId);
            //LogUtils.log("");
            //设置进来就显示到预售日期的日起最远的一个
            preSaleDetailPresenter.sendPreSaleDetailData(cacheSKUId, productId);
            calendarView.monthDateView.setSelectYearMonth(cacheyear, cachemonth - 1, cacheday);
            calendarView.setSelected(true);
        }

    }

    //    从服务器获取银行列表
    @Override
    public void setBankListResult(String result) {
        Loding.dis();
        if (result.contains("No Bank Info")) {
            Toast.makeText(this, "该商品暂不支持银行付款", Toast.LENGTH_SHORT).show();
        } else {
            backgroundAlpha(0.5f);
            View view = getLayoutInflater().inflate(R.layout.pop_yushou, null);
            popupWindow = new PopupWindow(findViewById(R.id.parent), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT,
                    ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
            popupWindow.setContentView(view);
            tv = (TextView) view.findViewById(R.id.tv_pay);
            //tv.setText(BZprice + "");
            //DecimalFormat df = new DecimalFormat("#.00");
            tv.setText(String.format("%.2f", BZprice)+"元");
//        et= (EditText) view.findViewById(R.id.etZhifu_auction);
            lv = (ListView) view.findViewById(R.id.lv_popupwindow_auction);
            Button bt = (Button) view.findViewById(R.id.btZhifu);

            popupWindow.setTouchable(true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);


            Gson gson = new Gson();
            PreSaleBondBean preSaleBondBean = gson.fromJson(result, PreSaleBondBean.class);
            if (!preSaleBondBean.getInfo().equals("sucess")) {
                Toast.makeText(PreSaleDetailActivity.this, "登录已超时,请重新登录!", Toast.LENGTH_SHORT).show();
            } else {
                if (result.contains("No Bank Info")) {
                    Toast.makeText(PreSaleDetailActivity.this, "没有可用银行", Toast.LENGTH_SHORT).show();
                }

                if (result.contains("amount")) {
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

                        } else if (type.equals("2")) {
                            //昆仑
                            auction.iv_icon = R.mipmap.kunlun;
                            auction.tv_Name = "昆仑银行";
                        } else if (type.equals("3")) {
                            //建行
                            auction.iv_icon = R.mipmap.jianshe;
                            auction.tv_Name = "中国建设银行";
                        }
                        Log.d("银行对象",""+auction.tv_Name);
                        //list1.clear();
                        list1.add(auction);
                        Log.d("银行列表",list1.toString());

                    }
                    adapter = new PopupWindowAdaptrer(list1, PreSaleDetailActivity.this);
                    lv.setAdapter(adapter);

                    popupWindow.showAtLocation(findViewById(R.id.parent), Gravity.BOTTOM, 0, 0);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if (parent.getId() == lv.getId()) {
                                for (int i = 0; i < list1.size(); i++) {
                                    Log.d("Listview的item", position + "");
                                    if (i != position) {
                                        list1.get(i).Flag = false;
                                    }
                                }
                                list1.get(position).Flag = !list1.get(position).Flag;
                                adapter.notifyDataSetChanged();
                            }
                        }
                    });

                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            //popupWindow.dismiss();
                            backgroundAlpha(1f);
                        }
                    });

                    //支付
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            payMoney();
                            popupWindow.dismiss();
                        }
                    });
                }
            }
        }

    }

    //    支付保证金
    @Override
    public void payPaymentSecurity(String result) {
        Log.d("支付保证金的返回值",result);

        Loding.dis();
        if (!result.equals("支付失败")) {
            if (!result.contains("info")){
                Intent intent = new Intent(PreSaleDetailActivity.this, Error_Dialog.class);
                intent.putExtra("number", "");
//                            intent.putExtra("type","预售保证金");
                startActivity(intent);
            }else {
                try {
                    JSONObject job = new JSONObject(result);
                    String info = job.getString("info");
                    String orderId = job.getString("orderId");
                    if (info.equals("sucess")) {
                        Toast.makeText(PreSaleDetailActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                        // TODO 显示订单信息
                        Intent intent = new Intent(PreSaleDetailActivity.this, Ok_Dialog.class);
                        intent.putExtra("number", orderId);
                        intent.putExtra("type", "预售");
                        startActivity(intent);

                    } else {
                       // Toast.makeText(PreSaleDetailActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PreSaleDetailActivity.this, Error_Dialog.class);
                        intent.putExtra("number", orderId);
//                            intent.putExtra("type","预售保证金");
                        startActivity(intent);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }else{
            Intent intent = new Intent(PreSaleDetailActivity.this, Error_Dialog.class);
            intent.putExtra("number", "");
//                            intent.putExtra("type","预售保证金");
            startActivity(intent);
        }

    }

    //判断是否开闭市
    public void booleanMarket(){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/colsedChecker";
        RequestParams rp=new RequestParams(url);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("预售是否闭市场",""+result);
                if (result.contains("true")){
                    //支付按钮不能点
                    payment_security.setVisibility(View.VISIBLE);
                    payment_security_closeMerket.setVisibility(View.GONE);
                    Log.d("现在是开市","现在是开市");
                }else if (result.contains("false")){
                    payment_security.setVisibility(View.GONE);
                    payment_security_closeMerket.setVisibility(View.VISIBLE);
                    Log.d("现在是关闭市","现在是关闭市");

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
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if(oldBottom != 0 && bottom != 0 &&(oldBottom - bottom > keyHeight)){

            if(payment_security.getVisibility()==View.VISIBLE){
                payment_security.setVisibility(View.GONE);
                xianshi="支付保证金";
               // payment_security.set
            }
            if (payment_security_closeMerket.getVisibility()==View.VISIBLE){
                payment_security_closeMerket.setVisibility(View.GONE );
                xianshi="提示";
            }




            //Toast.makeText(PreSaleDetailActivity.this, "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();

        }else if(oldBottom != 0 && bottom != 0 &&(bottom - oldBottom > keyHeight)){
            edt_num.clearFocus();
             if (xianshi.equals("支付保证金")){
                 payment_security.setVisibility(View.VISIBLE);
             }else if (xianshi.equals("提示")){
                 payment_security_closeMerket.setVisibility(View.VISIBLE);
             }
            //Toast.makeText(PreSaleDetailActivity.this, "监听到软件盘关闭...", Toast.LENGTH_SHORT).show();
            Double jianshu=sub(Double.parseDouble(edt_num.getText().toString()),Double.parseDouble(preSaleDetailBean.getCl_qigou()));
            int beishu=(int)div(jianshu,Double.parseDouble(preSaleDetailBean.getCl_xiaobian()),1);
            Log.d("倍数的值",beishu+"");
            if (beishu<=0){
               // beishu=1;
                edt_num.setText(preSaleDetailBean.getCl_qigou());
            }else {
                Double ji = mul(beishu, Double.parseDouble(preSaleDetailBean.getCl_xiaobian()));
                Log.d("即的值", ji + "");
                Double he = add(ji, Double.parseDouble(preSaleDetailBean.getCl_qigou()));
                Log.d("和的值", he + "");
                edt_num.setText(he + "");
            }


//            BigDecimal b = new BigDecimal(mul(Double.parseDouble(edt_num.getText().toString()),Double.parseDouble(preSaleDetailBean.getCl_jiner())));
//            double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//           // viewHolder.price.setText(f1 + "");
//            tv_item_cart_all_price.setText(f1+"");
            payBao();

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
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    edt_num.clearFocus();
                    if (xianshi.equals("支付保证金")){
                        payment_security.setVisibility(View.VISIBLE);
                    }else if (xianshi.equals("提示")){
                        payment_security_closeMerket.setVisibility(View.VISIBLE);
                    }
                    //Toast.makeText(PreSaleDetailActivity.this, "监听到软件盘关闭...", Toast.LENGTH_SHORT).show();
                    Double jianshu=sub(Double.parseDouble(edt_num.getText().toString()),Double.parseDouble(preSaleDetailBean.getCl_qigou()));
                    int beishu=(int)div(jianshu,Double.parseDouble(preSaleDetailBean.getCl_xiaobian()),1);
                    Log.d("倍数的值",beishu+"");
                    if (beishu<=0){
                        //beishu=1;
                        edt_num.setText(preSaleDetailBean.getCl_qigou());
                    }else {
                        Double ji = mul(beishu, Double.parseDouble(preSaleDetailBean.getCl_xiaobian()));
                        Log.d("即的值", ji + "");
                        Double he = add(ji, Double.parseDouble(preSaleDetailBean.getCl_qigou()));
                        Log.d("和的值", he + "");
                        edt_num.setText(he + "");
                    }
//                    tv_item_cart_all_price.setText(Double.parseDouble(edt_num.getText().toString())*Double.parseDouble(preSaleDetailBean.getCl_jiner())+"");
////                    payBao();
//                    BigDecimal b = new BigDecimal(mul(Double.parseDouble(edt_num.getText().toString()),Double.parseDouble(preSaleDetailBean.getCl_jiner())));
//                    double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//                    // viewHolder.price.setText(f1 + "");
//                    tv_item_cart_all_price.setText(f1+"");
                    payBao();

                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
}
