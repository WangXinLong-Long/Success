package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionView;

import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.bean.AuctionDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.presenter.AuctionPresenter;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.bean.RealTimeMardet;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.myOfferMVP.bean.MyOfferBean;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionPresenter.OpenAuctionPresenter;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class OpenActionorMifengActivity extends AppCompatActivity implements IOpenAuctionView, View.OnClickListener,View.OnLayoutChangeListener {
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
   // private MyCount mc;
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
    private RelativeLayout parent;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;






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
        Log.d("竞拍的详情",auctionDetailBean.getAddressDetail()+"");
        if (auctionDetailBean.getDepositDeadline().getTime()==null||auctionDetailBean.getDepositDeadline().getTime().equals("")||auctionDetailBean.getDepositDeadline().getTime()==""){

            depositDeadline = auctionDetailBean.getDepositDeadline().getTime();
            Log.d("剩余时间",depositDeadline+"");
            //竞拍方式（公开竞拍、密封报价）
            type_way = (getIntent().getStringExtra("type_way"));
            id_value=auctionDetailBean.getCl_id();
            productId = auctionDetailBean.getCl_id();
            proType=getIntent().getStringExtra("proType");


        }

    }
    public void initView(){
        line_top = (LinearLayout) findViewById(R.id.line_top);
        line_bottom = (LinearLayout) findViewById(R.id.line_bottom);
        line_centent = (LinearLayout) findViewById(R.id.line_content);
        lvdemo_result = (ListView) findViewById(R.id.Lv_infoSeccess);
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


    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void getOpenAuctionResult(String result) {

    }

    @Override
    public void getSealedBidResult(String result) {

    }

    @Override
    public void setBankListResult(String result) {

    }

    @Override
    public void setPaymentSecurityResult(String result) {

    }

    @Override
    public void setAmountPaidedResult(String result) {

    }

    @Override
    public void setAuctionResultResult(String result) {

    }

    @Override
    public void setStateOfPublicBiddingResult(String result, boolean needToPayTheMargin) {

    }

    @Override
    public void getPaymentResult(String result, String orderIds) {

    }

    @Override
    public void getOpenAuctionOrderDetail(String result) {

    }

    @Override
    public void setMyOfferResult(String result, boolean offer) {

    }

    @Override
    public void setRealTimeQuotesResult(String result, boolean offer) {

    }

    @Override
    public void setParticipateInTheAuction(String result) {

    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

    }
}
