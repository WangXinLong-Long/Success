package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
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
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.MainActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.PopupWindowAdaptrer;
import com.silianchuangye.sumao.success.custom.customCalendar.CalendarView;
import com.silianchuangye.sumao.success.custom.customCalendar.DayAndPrice;
import com.silianchuangye.sumao.success.custom.customCalendar.MonthDateView;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;
import com.silianchuangye.sumao.success.fragments.homepage.auction.VesselThreeActivity;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean.CLAttribute;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailCalendarBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.Sku;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.presenter.PreSaleDetailPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
public class PreSaleDetailActivity extends Activity implements View.OnClickListener, IPreSaleDetailView {
    CalendarView calendarView;
    ImageView title_bar_white_back;
    TextView title_bar_white_title;
    ImageView title_bar_white_shopping_cart;


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
    private List<OpenAuction> list1;
    private TextView tv;
    private RelativeLayout pre_sale_sale_detail_detail;
    private ArrayList<CLAttribute> cl_attribute;
    private String newSkuId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//
        setContentView(R.layout.activity_pre_sale_detail);
        Intent intent = getIntent();
        Calendar calendar = Calendar.getInstance();
        calendarlist = new ArrayList<>();
        productId = intent.getStringExtra("productId");
        skuId = intent.getStringExtra("skuId");
        LogUtils.log("productId:" + productId + "skuId:" + skuId);
//        从服务器获取数据
        preSaleDetailPresenter = new PreSaleDetailPresenter(this);
        preSaleDetailPresenter.sendPreSaleDetailData(skuId, productId);
        preSaleDetailPresenter.sendPreSaleDetailCalendar(productId);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
//        一定要在初始化控件以前把数据加载完毕了
        calendarView.setListDayAndPrice(calendarlist);
        calendarView.invalidate();

//        calendarView.invalidate();
        calendarView.setDateViewClick(new CalendarView.DateViewClick() {

            @Override
            public void dateClick() {
                Integer selectday = calendarView.getSelectDay();
                Integer selectmonth = calendarView.getSelectMonth()+1;
                Integer selectyear = calendarView.getSelectYear();
                LogUtils.log("选择的日期为："+selectyear+"年"+selectmonth+"月"+selectday+"日");
                for (int i = 0; i < calendarlist.size(); i++) {
                    Integer day = calendarlist.get(i).getDay();
                    Integer month = calendarlist.get(i).getMonth();
                    Integer year = calendarlist.get(i).getYear();
                    LogUtils.log("List的日期为："+year+"年"+month+"月"+day+"日");
                    if (selectday.equals(day)&&selectmonth.equals(month)&&selectyear.equals(year)){
                        newSkuId = calendarlist.get(i).getSkuId();
                    }
                }
                if (newSkuId.equals("")||newSkuId.isEmpty()){
                    Toast.makeText(PreSaleDetailActivity.this,"应该是出错了",Toast.LENGTH_SHORT).show();
                }else {
                    preSaleDetailPresenter.sendPreSaleDetailData(newSkuId, productId);
                }

            }
        });
        initView();
        initListener();

    }

    private void initListener() {
        payment_security.setOnClickListener(this);
        title_bar_white_back.setOnClickListener(this);
        title_bar_white_shopping_cart.setOnClickListener(this);
        pre_sale_sale_detail_detail.setOnClickListener(this);
    }

    private void initView() {
        pre_sale_sale_detail_detail= (RelativeLayout) findViewById(R.id.pre_sale_sale_detail_detail);
        payment_security= (Button) findViewById(R.id.payment_security);
        title_bar_white_back = ((ImageView) findViewById(R.id.title_bar_white_back));
        title_bar_white_title = ((TextView) findViewById(R.id.title_bar_white_title));
        title_bar_white_shopping_cart = ((ImageView) findViewById(R.id.title_bar_white_shopping_cart));
        pre_sale_sale_detail_detail = ((RelativeLayout) findViewById(R.id.pre_sale_sale_detail_detail));
//        payment_security = ((Button) findViewById(R.id.payment_security));
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
                intent.putExtra("title","预售");
                intent.putExtra("contract","www.baidu.com");
                intent.putExtra("cl_attribute",cl_attribute);
                startActivity(intent);
                break;

//            点击的 支付保证金 按钮
            case R.id.payment_security:
                /**
                 * 这里需要弹出popupWindow，即支付的弹出窗
                 */
                SharedPreferences sp = getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                String name = sp.getString("name","");
                if (name.equals("")|| name.isEmpty()){
                    //TODO 跳转登录界面
                    Toast.makeText(PreSaleDetailActivity.this,"跳转登录界面",Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(PreSaleDetailActivity.this, LoginUserActivity.class);
                    intent1.putExtra("roles","buyer");
//
                    startActivityForResult(intent1,0);
                }else {
                Popupwindow();
                backgroundAlpha(0.5f);

                }
                break;

        }
    }
    //设置背景透明
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
    public void Popupwindow(){
        View view=getLayoutInflater().inflate(R.layout.pop_yushou,null);
        PopupWindow popupWindow=new PopupWindow(findViewById(R.id.Layout_c), ActionBarOverlayLayout.LayoutParams.MATCH_PARENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        tv= (TextView) view.findViewById(R.id.tv_pay);
//        et= (EditText) view.findViewById(R.id.etZhifu_auction);
        lv= (ListView) view.findViewById(R.id.lv_popupwindow_auction);
        getinfo_Bank();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getId()==lv.getId()){
                    for(int i=0;i<list1.size();i++){
                        Log.d("Listview的item",position+"");
                        if(i!=position){

                            list1.get(i).Flag=false;

                        }
                    }
                    list1.get(position).Flag=!list1.get(position).Flag;
                    adapter.notifyDataSetChanged();
                }
            }
        });
        Button bt= (Button) view.findViewById(R.id.btZhifu);
        //支付
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payMoney();
            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.showAtLocation(pre_sale_sale_detail_detail, Gravity.BOTTOM,0,0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });

    }
    public void getinfo_Bank(){

            new Thread(){
                @Override
                public void run() {
                    // super.run();
                    String url="http://192.168.32.126:7023/rest/model/atg/commerce/catalog/ProductCatalogActor/availableBank";
                    RequestParams rp=new RequestParams(url);
                    rp.addParameter("productId",productId);
                    Log.d("银行列表的rp",""+rp);
                    Log.e("TAG","rp------"+rp);
                    x.http().post(rp, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Log.d("银行的列表",result);
                            Log.e("TAG","result-----"+result);
                            if (result.contains("amount")){
                                try{
                                    list1=new ArrayList<OpenAuction>();
                                    JSONObject obj=new JSONObject(result);
                                    String message=obj.getString("bankList");
                                    JSONArray array=new JSONArray(message);
                                    for (int i=0;i<array.length();i++){
                                        JSONObject obj_array=array.getJSONObject(i);
                                        OpenAuction auction=new OpenAuction();
                                        auction.tv_money=obj_array.getString("balance");
                                        String type=obj_array.getString("bankType");
                                        if (type.equals("1")){
                                            //平安
                                            auction.iv_icon=R.mipmap.pingan;
                                            auction.tv_Name="平安银行";

                                        }else if (type.equals("2")){
                                            //昆仑
                                            auction.iv_icon=R.mipmap.kunlun;
                                            auction.tv_Name="昆仑银行";
                                        }else if (type.equals("3")){
                                            //建行
                                            auction.iv_icon=R.mipmap.jianshe;
                                            auction.tv_Name="中国建设银行";
                                        }
                                        list1.add(auction);

                                    }
                                    adapter=new PopupWindowAdaptrer(list1,PreSaleDetailActivity.this);
                                    lv.setAdapter(adapter);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }else {
                                Toast.makeText(PreSaleDetailActivity.this, "该用户没有登录,无法获取可支付银行!", Toast.LENGTH_SHORT).show();
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

//预售支付保证金--没写完
    private void payMoney(){
        RequestParams params=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/payment/OrderPayment/auctionDeposit");
        params.addParameter("skuId",skuId);
        params.addParameter("productId",productId);
        params.addParameter("presalePrice",1);//保证金
        String str,blankname="";
        if (list1!=null){
            for(int i=0;i<list1.size();i++) {
                if (list1.get(i).Flag) {
                    str = list1.get(i).tv_Name;
                    Log.e("TAG","str----"+str);
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
       SharedPreferences sp=this.getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique123= sp.getString("unique", "");
        params.addParameter("_dynSessConf",unique123);
        Log.e("TAG","blankName------"+blankname);
        params.addParameter("paymentPlatform",blankname);
        Log.e("TAG","params=-----------"+params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG","result-----"+result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG","ex----"+ex.toString());
                Log.e("TAG","ex-----"+ex.getMessage().toString());
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
    public void getPreSaleDetailData(PreSaleDetailBean preSaleDetailBean) {
        //        兰州石化7042
        tvName_auction.setText(preSaleDetailBean.getCl_mingcheng());
        //        6000
//        tvPrice_auction.setText(preSaleDetailBean.get);
        //        积分规则:每吨商品积一分
//        pre_sale_detail_integral_rule.setText(preSaleDetailBean.getCl_jifen());
        //        剩余数量
        surplus_amount_et.setText(preSaleDetailBean.getCl_shuliang());
        //        起购量
        purchase_quantity_et.setText(preSaleDetailBean.getCl_qigou());
        //        最小变量单位
        min_variable_et.setText(preSaleDetailBean.getCl_xiaobian());
        //       交货时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 ");
        delivery_time_et.setText(simpleDateFormat.format(Double.parseDouble(preSaleDetailBean.getDeliveryPeriodStart())));
        delivery_time_et_end.setText(simpleDateFormat.format(Double.parseDouble(preSaleDetailBean.getDeliveryPeriodEnd())));
        //       仓库地址
        warehouse_address_et.setText(preSaleDetailBean.getCl_ckdizhi());
        //       交货方式
        StringBuilder sb = new StringBuilder();
        int jhfssize = preSaleDetailBean.getCl_jhfangshi().size();
        for (int i = 0 ;i< jhfssize;i++){
            sb.append(preSaleDetailBean.getCl_jhfangshi().get(i));
            if (i != jhfssize-1 ){
                sb.append("、");
            }
        }
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
//        pre_sale_detail_remark.setText(preSaleDetailBean.get);
//TODO 合同
//        contractString = preSaleDetailBean.get();
        //        获取产品参数
        cl_attribute = preSaleDetailBean.getCl_attribute();
    }

    @Override
    public void getPreSaleDetailCalendarData(PreSaleDetailCalendarBean preSaleDetailCalendarBean/*,int position*/) {
        List<Sku> skus = preSaleDetailCalendarBean.getSku();
        LogUtils.log("lists" + skus.toString());
//        List<DayAndPrice> list = new ArrayList<DayAndPrice>();
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
            LogUtils.log(year + "..." + mounth + "..." + day);
            calendarlist.add(new DayAndPrice("￥"+sku.getCl_jiner(), year, mounth, day,skuId));
        }
        calendarView.setSelected(true);
        for (int i = 0; i < calendarlist.size(); i++) {
            LogUtils.log("我这没问题：" + calendarlist.get(i).toString());
        }
    }
}
