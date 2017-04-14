package com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
import com.silianchuangye.sumao.success.dialog.Ok_Dialog;
import com.silianchuangye.sumao.success.fragments.homepage.auction.OpenAuction;
import com.silianchuangye.sumao.success.fragments.homepage.auction.VesselThreeActivity;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.JoinActivity;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.RuleActivity;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.bean.GroupBuyingDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.presenter.GroupBuyingDetailPresenter;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleBondBean;
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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class GroupBuyingSuccessActivity extends AppCompatActivity implements IGroupBuyingDetailView, View.OnClickListener {
    private ListView lvDemo;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> list;
    private TextView tv_success;
    private TextView tv_failed;

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
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groupBuyingDetailPresenter = new GroupBuyingDetailPresenter(this);
        setContentView(R.layout.activity_group_buying_success);
        init();

        event();
        addData();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void init() {
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
        Bundle bundle = getIntent().getExtras();
        result = bundle.getString("result");
        state = bundle.getString("state");
        result2 = bundle.getString("result2");
        Log.d("成团状态", "" + result2);
        Log.d("result的值",""+result);
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
        number = (EditText) findViewById(R.id.ed_shuzhi_number);

        layoutContent_auction = ((LinearLayout) findViewById(R.id.layoutContent_auction));
        btn_tuangou_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (unique.equals("false")) {
                    tuichu();
                    Intent intent = new Intent(GroupBuyingSuccessActivity.this, LoginUserActivity.class);
                    intent.putExtra("roles", "buyer");
                    intent.putExtra("position", 102);
                    startActivity(intent);
                    //startActivityForResult(intent, 102);
                } else {
                    //进行用户是否有权限购买判断
                    booleanZizhi();
                    //弹出popwindow


//                    backgroundAlpha(0.5f);
                }

            }
        });
        //        购买数量
        tv_item_cart_buy_num = ((EditText) findViewById(R.id.tv_item_cart_buy_num));

        getinfo(result);
//        减
        img_item_cart_buy_sub = ((TextView) findViewById(R.id.img_item_cart_buy_sub));
        img_item_cart_buy_sub.setOnClickListener(this);
//        增加
        img_item_cart_buy_add = ((TextView) findViewById(R.id.img_item_cart_buy_add));
        img_item_cart_buy_add.setOnClickListener(this);

        booleanOpenorclose();
        //       购买数量
        tv_item_cart_buy_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    tv_item_cart_buy_num.setText(qigou.getText().toString());
                    tv_item_cart_buy_num.setSelection(qigou.getText().toString().length());
                }
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 3) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 4);
                        tv_item_cart_buy_num.setText(s);
                        tv_item_cart_buy_num.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    tv_item_cart_buy_num.setText(s);
                    tv_item_cart_buy_num.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        tv_item_cart_buy_num.setText(s.subSequence(1, s.length()));
                        tv_item_cart_buy_num.setSelection(1);
                        return;
                    }
                }

            }


            @Override
            public void afterTextChanged(Editable s) {

                if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) > Double.parseDouble(count.getText().toString())) {
                    tv_item_cart_buy_num.setText((count.getText().toString()));
                    tv_item_cart_buy_num.setSelection(count.getText().toString().length());

                }
                if (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) < Double.parseDouble(qigou.getText().toString())) {
                    tv_item_cart_buy_num.setText((qigou.getText().toString()));
                    tv_item_cart_buy_num.setSelection(qigou.getText().toString().length());
                }
                if (!tv_item_cart_buy_num.getText().toString().equals("")) {
                    double num = (Double.parseDouble(tv_item_cart_buy_num.getText().toString()));
                    double price_num = (Double.parseDouble(price.getText().toString()));
                    double x = num * price_num;
                    DecimalFormat df = new DecimalFormat("#.##");
                    tv_item_cart_all_price.setText(df.format(x) + "");

                }
                if ((Double.parseDouble(tv_item_cart_buy_num.getText().toString()) - Double.parseDouble(qigou.getText().toString())) % Double.parseDouble(bianliang.getText().toString()) != 0) {
                    DecimalFormat df = new DecimalFormat("#");
                    double number = (Double.parseDouble(tv_item_cart_buy_num.getText().toString()) - Double.parseDouble(qigou.getText().toString())) / Double.parseDouble(bianliang.getText().toString());
                    double priceNum = (Integer.parseInt(df.format(number)) * Double.parseDouble(bianliang.getText().toString())) + Double.parseDouble(qigou.getText().toString());
                    String priceString = (priceNum + "");
                    tv_item_cart_buy_num.setText(priceString);
                    tv_item_cart_buy_num.setSelection(priceString.length());
                }
            }

        });

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode==102){
//            unique=data.getStringExtra("unique");
//            Log.d("返回过来的之",unique);
//        }
//    }
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
            //      editor.clear();
            editor.commit();
            //Toast.makeText(context, "用户名称已删除", Toast.LENGTH_SHORT).show();

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
    public void booleanOpenorclose() {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/colsedChecker";
        RequestParams rp = new RequestParams(url);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("開閉式的返回值", result);
                if (result.contains("true")) {
                    if (state.equals("团购未开始")) {
                        layout_not_action.setVisibility(View.GONE);
                        layout_Bottom.setVisibility(View.GONE);
                        relative_tuangou_end.setVisibility(View.VISIBLE);
                        tvResult.setText("团购未开始");
                    } else if (state.equals("团购已开始")) {
                        layout_Bottom.setVisibility(View.VISIBLE);
                        layout_not_action.setVisibility(View.GONE);
                        relative_tuangou_end.setVisibility(View.GONE);

                    } else if (state.equals("团购结束")) {
                        if (securedOfSection.equals("productOff")) {
                            //产品已下架
                            layout_Bottom.setVisibility(View.GONE);
                            layout_not_action.setVisibility(View.GONE);
                            relative_tuangou_end.setVisibility(View.VISIBLE);
                            tvResult.setText("产品已下架");
                            tvResult.setTextColor(getResources().getColor(R.color.red));
                            tvResult.setTextSize(16);
                            //  Toast.makeText(GroupBuyingSuccessActivity.this, "产品已下架", Toast.LENGTH_SHORT).show();
                        } else {
                            if (result2.equals("未成团")) {
                                layout_Bottom.setVisibility(View.GONE);
                                layout_not_action.setVisibility(View.GONE);
                                relative_tuangou_end.setVisibility(View.VISIBLE);
                            } else if (result2.equals("已成团")) {
                                //
                                layout_Bottom.setVisibility(View.GONE);
                                layout_not_action.setVisibility(View.GONE);
                                relative_tuangou_end.setVisibility(View.VISIBLE);
                                tvResult.setText("团购已经结束请,关注其他团购");
                                tvResult.setTextColor(getResources().getColor(R.color.red));
                                tvResult.setTextSize(16);
                                chenggong();

                            }
                        }

                    }
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
                    intent.putExtra("jiezhi", jiezhishijian);
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
                        Log.d("list", list_gridview.size() + "");

                        SimpleAdapter adapter_grid = new SimpleAdapter(GroupBuyingSuccessActivity.this,
                                list_gridview, R.layout.item_gridview,
                                new String[]{"id", "amount", "addPrice", "addQty"},
                                new int[]{R.id.tv_order_id, R.id.count, R.id.tv_price, R.id.tv_number}
                        );
                        gvDemo.setAdapter(adapter_grid);
                        adapter_grid.notifyDataSetChanged();
                        gvDemo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
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

    public void addData() {
        if (state.equals("团购未开始")) {
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


    }

    public void getinfo(String result) {
        Gson gson = new Gson();
        groupBuyingDetailBean = gson.fromJson(result, GroupBuyingDetailBean.class);
        cl_baozhj = groupBuyingDetailBean.getCl_baozhj();
        cl_id = groupBuyingDetailBean.getCl_id();
        NumberFormat nf = NumberFormat.getPercentInstance();
        Number number = null;
        try {
            number = nf.parse(cl_baozhj);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        percent = String.format("%.4f", Double.parseDouble(number.toString()));
        try {
            JSONObject obj = new JSONObject(result);
            String utilDate = groupBuyingDetailBean.getUtilDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long time = 0l;
            try {
                long strdate = dateFormat.parse(utilDate).getTime();
                long nowTime = System.currentTimeMillis();
                time = strdate - nowTime;
                mc = new MyCount(time, 1000);
                mc.start();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            name.setText(groupBuyingDetailBean.getCl_mingcheng());
            strprice = groupBuyingDetailBean.getCl_jine();
            price.setText(strprice);
            if (result.contains("cl_zongliang")) {
                count.setText(groupBuyingDetailBean.getCl_zongliang());
            } else {
                count.setText("");
            }
            cl_gongsiId = groupBuyingDetailBean.getCl_gongsiId();
            layoutContent_auction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(GroupBuyingSuccessActivity.this, "客服聊天", Toast.LENGTH_SHORT).show();
                    Intent intentHX = new Intent();
                    intentHX.setClass(GroupBuyingSuccessActivity.this, LoginActivity.class);
                    intentHX.putExtra(Constant.MESSAGE_TO_INTENT_EXTRA, Constant.MESSAGE_TO_DEFAULT);
//                传入卖家id
                    intentHX.putExtra(Constant.IM_SERVICE_NUMBER, cl_gongsiId);
                    startActivity(intentHX);
                }
            });
            securedOfSection = groupBuyingDetailBean.getSecuredOfSection();
            group_amount_et.setText(groupBuyingDetailBean.getCl_groupQuantity());
            qigou.setText(groupBuyingDetailBean.getCl_qigou());
            tv_item_cart_buy_num.setText(qigou.getText().toString());
            tv_item_cart_all_price.setText(String.format("%.2f", Double.parseDouble(tv_item_cart_buy_num.getText().toString()) * Double.parseDouble(price.getText().toString())));
            strbianliang = groupBuyingDetailBean.getCl_xbianliang();
            bianliang.setText(strbianliang);
            cangku.setText(groupBuyingDetailBean.getCl_cangku());
            String fangshi = groupBuyingDetailBean.getCl_fangshi().toString();
            String str = fangshi.substring(2, fangshi.length() - 2);
            way.setText(str);
            type.setText(groupBuyingDetailBean.getCl_fenlei());
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
            String sumAddQty = groupBuyingDetailBean.getSumAddQty();//数量
            String groupPercent = groupBuyingDetailBean.getGroupPercent();//比例

            int i = (int) (Double.valueOf(groupPercent) * 100);
            tv_pro_now.setText("当前成团量为 : " + i + "%");
            tv_all_num.setText(groupBuyingDetailBean.getCl_zongliang() + "t");
            pbDemo.setProgress(i);
            String state = groupBuyingDetailBean.getSection();
            Log.e("TAG", "state=" + state);
            if (state.equals("2")) {
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
            }
            if (state.equals("1")) {//正在进行
                relative_tuangou.setVisibility(View.VISIBLE);
                tv_tuangou_time.setVisibility(View.GONE);
                tv_tuangou_line.setVisibility(View.GONE);
                linear_tuangou_time.setVisibility(View.VISIBLE);
            }
            if (state.equals("0")) {//未开始
                tv_tuangou_line.setVisibility(View.VISIBLE);
                relative_tuangou.setVisibility(View.GONE);
                layout_Bottom.setVisibility(View.GONE);
                relative_tuangou_end.setVisibility(View.VISIBLE);
                linear_tuangou_time.setVisibility(View.GONE);
                // linear_tuagou_line.setVisibility(View.GONE);
                tv_tuangou_time.setVisibility(View.VISIBLE);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

//    private void showPopWindow() {
//        popView = View.inflate(this, R.layout.pop_tuagou_add, null);
//        tv_pop_price = (TextView) popView.findViewById(R.id.tvPrice_auction);//价钱
//        tv_pop_num = (TextView) popView.findViewById(R.id.surplus_amount_et);//团购总量
//        tv_pop_fenlei = (TextView) popView.findViewById(R.id.classification_pre_sale_et);//分类
//        tv_pop_start_num = (TextView) popView.findViewById(R.id.purchase_quantity_et);//起购量
//        tv_pop_type = (TextView) popView.findViewById(R.id.warehouse_et);//交货方式
//        tv_pop_small_null = (TextView) popView.findViewById(R.id.min_variable_et);//最小变量单位
//        tv_pop_diqu = (TextView) popView.findViewById(R.id.region_et);//地区
//        tv_pop_cangku = (TextView) popView.findViewById(R.id.delivery_time_et);//仓库
//        tv_pop_gongsi = (TextView) popView.findViewById(R.id.warehouse_address_et);//公司
//        tv_pop_add = (TextView) popView.findViewById(R.id.img_item_cart_buy_add);//加号
//        tv_pop_sub = (TextView) popView.findViewById(R.id.img_item_cart_buy_sub);//减号
//        edt_pop_num = (EditText) popView.findViewById(R.id.tv_item_cart_buy_num);//中间变化的数量
//        edt_pop_num.setText(number.getText().toString());
//        btn_pop_ok = (Button) popView.findViewById(R.id.determine_buy_immediately_button);//确定
//        popView.measure(0, 0);
//        int w = getWindowManager().getDefaultDisplay().getWidth();
//        popupWindow = new PopupWindow(popView, w, popView.getMeasuredHeight());
//        popupWindow.setFocusable(true);
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        popupWindow.showAtLocation(btn_tuangou_add, Gravity.BOTTOM, 0, 0);
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                backgroundAlpha(1f);
//            }
//        });
//        tv_pop_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String str = edt_pop_num.getText().toString();
//                double num = Double.valueOf(str);
//                double cc = Double.parseDouble(strbianliang);
//                num += cc;
//                edt_pop_num.setText(num + "");
////                tv_pop_price.setText(Double.valueOf(edt_pop_num.getText().toString())*Double.valueOf(strprice)+"");
//                number.setText(edt_pop_num.getText().toString());
//                count_price.setText(Double.valueOf(number.getText().toString()) * Double.valueOf(strprice) + "");
//            }
//        });
//        tv_pop_sub.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String str = edt_pop_num.getText().toString();
//                double num = Double.valueOf(str);
//                double cc = Double.parseDouble(strbianliang);
//                num -= cc;
//                if (num < cc) {
//                    num = cc;
//                }
//                edt_pop_num.setText(num + "");
////                tv_pop_price.setText(Double.valueOf(edt_pop_num.getText().toString())*Double.valueOf(strprice)+"");
//                //
//                number.setText(edt_pop_num.getText().toString());
//                count_price.setText(Double.valueOf(number.getText().toString()) * Double.valueOf(strprice) + "");
//            }
//        });
//
//        btn_pop_ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //支付保证金popwindow
//                popupWindow.dismiss();
//                showpop();
//                backgroundAlpha(0.5f);
//
//            }
//        });
//        popDate();
//    }

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
        groupBuyingDetailPresenter.getBankListResult(cl_id);
    }

//    private void popDate() {
//        edt_pop_num.setText(number.getText().toString());
//        String str, str2;
//        if (edt_pop_num.getText().toString().equals("")) {
//            str = 0 + "";
//        } else {
//            str = edt_pop_num.getText().toString();
//        }
//        if (strprice.equals("")) {
//            str2 = "0";
//        } else {
//            str2 = strprice;
//        }
//        tv_pop_price.setText(price.getText().toString());
////        String baozhengjin=Double.valueOf(str)*Double.valueOf(str2)+"";
////        tv_pop_price.setText(Double.valueOf(str)*Double.valueOf(str2)+"");
//
//        tv_pop_num.setText(count.getText().toString());//剩余数量
//        tv_pop_fenlei.setText(type.getText().toString());
//        tv_pop_start_num.setText(qigou.getText().toString());
//        tv_pop_type.setText(way.getText().toString());//交易方式
//        tv_pop_small_null.setText(bianliang.getText().toString());
//        tv_pop_diqu.setText(add.getText().toString());
//        tv_pop_cangku.setText(cangku.getText().toString());
//        tv_pop_gongsi.setText(comm.getText().toString());
//        //
//    }

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

            additionalMarginAmount = Double.parseDouble(tv_item_cart_all_price.getText().toString()) * Double.parseDouble(percent + "") - Double.parseDouble(paidDeposit.getText().toString());

            count_Price.setText(String.format("%.2f", Double.parseDouble(tv_item_cart_all_price.getText().toString()) * Double.parseDouble(percent + "")));
            additionalMarginAmountString = additionalMarginAmount > 0 ? additionalMarginAmount + "" : 0 + "";
            amountOfMarginRequired.setText(String.format("%.2f", Double.parseDouble(additionalMarginAmountString)));
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
                                zhifu_baozhengjin(list1.get(i).message);
                                paymentSecurityPopupWindow.dismiss();
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
            Toast.makeText(GroupBuyingSuccessActivity.this, "您支付保证金失败！", Toast.LENGTH_SHORT).show();
        } else if (result.contains("\"info\":\"sucess\"")) {
            Toast.makeText(GroupBuyingSuccessActivity.this, "您支付保证金成功！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(GroupBuyingSuccessActivity.this, Ok_Dialog.class);
            intent.putExtra("number", SuMaoConstant.MARGIN_PAYMENT_SUCCESS);
            intent.putExtra("type", "");
            startActivity(intent);
        }
    }


    //    支付保证金接口
    public void zhifu_baozhengjin(String platform) {
        Loding.show(this, "保证金支付中...", false, null);
//        保证金金额

//        产品Id
        String productId = cl_id;
//        支付平台
        String paymentPlatform = platform;
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String _dynSessConf = sp.getString("unique", "");
        // groupBuyingDetailPresenter.getPaymentSecurityResult(depositAmount, productId, paymentPlatform, _dynSessConf);
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/payment/OrderPayment/groupDeposit";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("productId", cl_id);
        rp.addParameter("qty", shulian);
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
//                    Toast.makeText(GroupBuyingSuccessActivity.this, "您支付保证金成功！", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(GroupBuyingSuccessActivity.this, Ok_Dialog.class);
//                    intent.putExtra("number", SuMaoConstant.MARGIN_PAYMENT_SUCCESS);
//                    intent.putExtra("type", "");
//                    startActivity(intent);
                    //  chenggong();
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
            //            数量减
            case R.id.img_item_cart_buy_sub:
                double num1 = Double.valueOf(tv_item_cart_buy_num.getText().toString());
                num1 -= Double.valueOf(bianliang.getText().toString());
                if (num1 < Double.valueOf(qigou.getText().toString())) {
                    Toast.makeText(this, "购买数量不能小于起购量", Toast.LENGTH_SHORT).show();
                } else {
                    tv_item_cart_buy_num.setText(num1 + "");
                }
                tv_item_cart_all_price.setText(Double.parseDouble(tv_item_cart_buy_num.getText().toString()) * Double.valueOf(price.getText().toString()) + "");
                break;
//            数量加
            case R.id.img_item_cart_buy_add:
                double num2 = Double.valueOf(tv_item_cart_buy_num.getText().toString());
                num2 += Double.valueOf(bianliang.getText().toString());
                if (num2 > Double.valueOf(count.getText().toString())) {
                    Toast.makeText(this, "购买数量不能大于竞拍总量", Toast.LENGTH_SHORT).show();
                } else {
                    tv_item_cart_buy_num.setText(num2 + "");
                    tv_item_cart_all_price.setText(Double.valueOf(tv_item_cart_buy_num.getText().toString()) * Double.valueOf(price.getText().toString()) + "");
                }
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique = sp.getString("unique", "");

    }



    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            tv_success.setText("finish");
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
            tv_success.setText("剩余团购时间  :  " + finaltime);
            tv_tuangou_time.setText("距离开团时间剩余  :  " + finaltime);
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
        public View getView(int position, View convertView, ViewGroup parent) {
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


            return view;
        }
    }
}
