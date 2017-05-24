package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.silianchuangye.sumao.success.HX.Constant;
import com.silianchuangye.sumao.success.HX.ui.LoginActivity;
import com.silianchuangye.sumao.success.MainActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.customCalendar.CalendarView;
import com.silianchuangye.sumao.success.fragments.homepage.auction.VesselThreeActivity;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.LikeProduct;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.SeeProductMVP.view.SeeProduct;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.Orders;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.presenter.GoodsInStockDetailPresenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;
import com.silianchuangye.sumao.success.fragments.pagerThreeMVP.view.PayOrderListActivity;
import com.silianchuangye.sumao.success.fragments.shoppingCart.dialog.Cart_MyDialog;
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
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public class GoodsInStockDetailActivity extends Activity implements View.OnClickListener, IGoodsInStockDetailView ,View.OnLayoutChangeListener {
    CalendarView calendarView;
    ImageView title_bar_white_back;
    TextView title_bar_white_title;
    ImageView title_bar_white_shopping_cart;
    RelativeLayout pre_sale_sale_detail_detail;
    RelativeLayout pre_sale_sale_detail_similar_product, pre_sale_sale_detail_similar_liulan;
    Button join_shopping_cart, buy_immediately;
    View popupWindowView;
    PopupWindow popupWindow;
    //点击支付或者加入购物车弹出的对话框上面的几个按钮
    private TextView img_item_cart_buy_sub;
    private TextView img_item_cart_buy_add;
    private TextView tv_item_cart_buy_num;
    private Cart_MyDialog dialog;
    private Button determine_buy_immediately_button;
    private Intent activityiIntent;
    private GoodsInStockDetailBean goodsInStockDetailBean;
    private GoodsInStockDetailPresenter goodsInStockDetailPresenter;
    private TextView tvName_auction;
    private TextView tvPrice_auction;
    private TextView integral_rule;
    private TextView surplus_amount_et;
    private TextView min_variable_et;
    private TextView delivery_time_et;
    private TextView warehouse_address_et;
    private TextView delivery_mode_et;
    private TextView classification_pre_sale_et;
    private TextView warehouse_et;
    private TextView region_et;
    private TextView company_et;
    private TextView tvRemark_auction;
    private TextView delivery_time_et_end;
    private ImageView image;
    private List<GoodsInStockDetailBean.RecentlyViewedProductBean> relatedProduct;
    private List<GoodsInStockDetailBean.ClAttributeBean> cl_attribute;
    private LinearLayout layoutContent_auction;
    private String cl_gongsiId;
    private String contractString;
    private String qigou;
    private String sku_id;
    private TextView all_price;
    private EditText et_number;
    private TextView tv_jian;
    private TextView tv_jia;
    private TextView purchase_quantity_et;
    private LinearLayout remark_ll;
    private Button bt_Colse;
    private LinearLayout layout_gongneng;
    private String unique;
    private RelativeLayout layout_xianhuo;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;

    private RelativeLayout Layout_Button_Open;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_in_stock_detail);
        bt_Colse = (Button) findViewById(R.id.bt_Colse);
        Layout_Button_Open= (RelativeLayout) findViewById(R.id.Layout_Button_Open);
        layout_gongneng = (LinearLayout) findViewById(R.id.layout_gongneng);
        new Thread() {
            @Override
            public void run() {
                super.run();
                openOrClose();
            }
        }.start();

        activityiIntent = getIntent();
        goodsInStockDetailBean = (GoodsInStockDetailBean) activityiIntent.getSerializableExtra("goodsInStockDetailBean");

    }

    private void initListener() {
        buy_immediately.setOnClickListener(this);
        join_shopping_cart.setOnClickListener(this);
        title_bar_white_back.setOnClickListener(this);
        title_bar_white_shopping_cart.setOnClickListener(this);
        pre_sale_sale_detail_detail.setOnClickListener(this);
        pre_sale_sale_detail_similar_product.setOnClickListener(this);
        pre_sale_sale_detail_similar_liulan.setOnClickListener(this);
        layoutContent_auction.setOnClickListener(this);
        tv_jia.setOnClickListener(this);
        tv_jian.setOnClickListener(this);
        title_bar_white_title.setText("现货");
        layout_xianhuo.addOnLayoutChangeListener(this);


    }

    private void initView() {
        layout_xianhuo= (RelativeLayout) findViewById(R.id.layout_xianhuo);

        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight/3;
        all_price = (TextView) findViewById(R.id.tv_item_cart_all_price);
        tv_jian = (TextView) findViewById(R.id.img_item_cart_buy_sub);
        tv_jia = (TextView) findViewById(R.id.img_item_cart_buy_add);
        et_number = (EditText) findViewById(R.id.tv_item_cart_buy_num);
        et_number.setOnClickListener(this);
//        et_number.setFocusable(true);
//        et_number.setFocusableInTouchMode(true);
        title_bar_white_back = ((ImageView) findViewById(R.id.title_bar_white_back));
        title_bar_white_title = ((TextView) findViewById(R.id.title_bar_white_title));
        title_bar_white_shopping_cart = ((ImageView) findViewById(R.id.title_bar_white_shopping_cart));
        pre_sale_sale_detail_detail = ((RelativeLayout) findViewById(R.id.pre_sale_sale_detail_detail));
        pre_sale_sale_detail_similar_product = ((RelativeLayout) findViewById(R.id.pre_sale_sale_detail_similar_product));
        pre_sale_sale_detail_similar_liulan = (RelativeLayout) findViewById(R.id.pre_sale_sale_detail_similar_liulan);
        buy_immediately = ((Button) findViewById(R.id.buy_immediately));
        join_shopping_cart = ((Button) findViewById(R.id.join_shopping_cart));
        image = ((ImageView) findViewById(R.id.image));
        dialog = new Cart_MyDialog(this);
//        兰州石化7042
        tvName_auction = ((TextView) findViewById(R.id.tvName_auction));
//        6000
        tvPrice_auction = ((TextView) findViewById(R.id.tvPrice_auction));
//        剩余数量
        surplus_amount_et = ((TextView) findViewById(R.id.surplus_amount_et));
//        起购量
        purchase_quantity_et = ((TextView) findViewById(R.id.purchase_quantity_et));
//        最小变量单位
        min_variable_et = ((TextView) findViewById(R.id.min_variable_et));
//        交货时间
        delivery_time_et = ((TextView) findViewById(R.id.delivery_time_et));
//        交货结束时间
        delivery_time_et_end = ((TextView) findViewById(R.id.delivery_time_et_end));
//        仓库地址
        warehouse_address_et = ((TextView) findViewById(R.id.warehouse_address_et));
//        交货方式
        delivery_mode_et = ((TextView) findViewById(R.id.delivery_mode_et));
//        分类
        classification_pre_sale_et = ((TextView) findViewById(R.id.classification_pre_sale_et));
//        仓库
        warehouse_et = ((TextView) findViewById(R.id.warehouse_et));
//        地区
        region_et = ((TextView) findViewById(R.id.region_et));
//        公司
        company_et = ((TextView) findViewById(R.id.company_et));
//        产品备注
        tvRemark_auction = ((TextView) findViewById(R.id.tvRemark_auction));
//        产品备注的LinearLayout
        remark_ll = ((LinearLayout) findViewById(R.id.remark_ll));
//        联系客服
        layoutContent_auction = ((LinearLayout) findViewById(R.id.layoutContent_auction));

//        getPurchase();

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.title_bar_white_back:
                finish();
                break;
            case R.id.title_bar_white_shopping_cart:

                intent.setClass(GoodsInStockDetailActivity.this, MainActivity.class);
                intent.putExtra("cart", 1);
                startActivity(intent);
                break;
            case R.id.pre_sale_sale_detail_detail:
                LogUtils.log("GoodsInStockDetailActivity---->cl_attribute:" + cl_attribute);
                intent.putExtra("cl_attribute", (Serializable) cl_attribute);
                intent.setClass(GoodsInStockDetailActivity.this, VesselThreeActivity.class);
                intent.putExtra("title", "现货");
                intent.putExtra("contract", goodsInStockDetailBean.getContract());
                intent.putExtra("auctionDetailBean", goodsInStockDetailBean);
                LogUtils.log(goodsInStockDetailBean.getContract() + "-------WXL");
                startActivity(intent);
                break;
            case R.id.pre_sale_sale_detail_similar_product:
                if (relatedProduct != null) {
                    Bundle bundle = new Bundle();

                    bundle.putSerializable("relatedProduct", (Serializable) relatedProduct);
                    intent.putExtras(bundle);
                    intent.setClass(GoodsInStockDetailActivity.this, LikeProduct.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "暂时还没有相似产品", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.pre_sale_sale_detail_similar_liulan:
                Toast.makeText(this, "浏览记录", Toast.LENGTH_SHORT).show();
                intent.setClass(GoodsInStockDetailActivity.this, SeeProduct.class);
                startActivity(intent);
                break;
//            立即购买：
            case R.id.buy_immediately:
                int type = 1;

                if (unique.equals("false")||unique.equals("")) {
                    Toast.makeText(GoodsInStockDetailActivity.this, "请登录后进行操作", Toast.LENGTH_SHORT).show();
                    tuichu();
                    Intent intent1 = new Intent(this, LoginUserActivity.class);
                    intent1.putExtra("roles", "buyer");
                    intent1.putExtra("position", 100);
                    startActivity(intent1);
                    //  startActivityForResult(intent1, 100);
                } else {
                    jurisdiction(goodsInStockDetailBean.getCl_id(), "购买", unique);
//
                }
                break;
            // 加入购物车：
            case R.id.join_shopping_cart:
//                SharedPreferences sp1 = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
//                String unique1 = sp1.getString("unique", "");
                Log.d("现货详情的唯一标识",unique);
                if (unique.equals("false")||unique.equals("")) {
                    tuichu();
                    Intent intent1 = new Intent(this, LoginUserActivity.class);
                    intent1.putExtra("roles", "buyer");
                    intent1.putExtra("position", 100);

                   // startActivityForResult(intent1, 100);
                    startActivity(intent1);
                } else {
                    jurisdiction(goodsInStockDetailBean.getCl_id(), "加入购物车", unique);


                }
//
                break;
            case R.id.img_item_cart_buy_sub://数量减少
                String str = et_number.getText().toString();
                float num = Float.valueOf(str);
                num=num-Float.parseFloat(goodsInStockDetailBean.getCl_xiaobian());
                if (num >= Float.parseFloat(goodsInStockDetailBean.getCl_qigou())) {
                    et_number.setText("" + num);
                }else{
                    et_number.setText(goodsInStockDetailBean.getCl_qigou());
                }
                break;
            case R.id.img_item_cart_buy_add:
                str = et_number.getText().toString();
                Log.d("输入框内的值",str);
                num = Float.valueOf(str);

                if (num >= Float.parseFloat(goodsInStockDetailBean.getCl_shuliang())) {
//                    Cart_MyDialog diolog=new Cart_MyDialog(GoodsInStockDetailActivity.this,goodsInStockDetailBean.getCl_mingcheng(),goodsInStockDetailBean.getCl_shuliang());
//                    diolog.show();
                    et_number.setText(goodsInStockDetailBean.getCl_shuliang());
                } else {
                    num=num+Float.parseFloat(goodsInStockDetailBean.getCl_xiaobian());
                    et_number.setText("" + num);
                }

                break;
            case R.id.layoutContent_auction:
                Log.d("现货的唯一标识",unique);
                if (unique.equals("false")||unique.equals("")) {
                    Toast.makeText(GoodsInStockDetailActivity.this, "请登录后进行操作", Toast.LENGTH_SHORT).show();
                    tuichu();
                    Intent intent1 = new Intent(this, LoginUserActivity.class);
                    intent1.putExtra("roles", "buyer");
                    intent1.putExtra("position", 100);
                    startActivity(intent1);
                } else {
                    Intent intentHX = new Intent();
                    intentHX.setClass(GoodsInStockDetailActivity.this, LoginActivity.class);
                    intentHX.putExtra(Constant.MESSAGE_TO_INTENT_EXTRA, Constant.MESSAGE_TO_DEFAULT);
//                传入卖家id
                    intentHX.putExtra(Constant.IM_SERVICE_NUMBER, goodsInStockDetailBean.getCl_qiyeonlyId());
                    Log.d("公司的唯一标识",goodsInStockDetailBean.getCl_qiyeonlyId()+"是公司id");
                    intentHX.putExtra("gongsiName",goodsInStockDetailBean.getCl_gongsi());
                    startActivity(intentHX);
//
                }


                break;
            case R.id.tv_item_cart_buy_num:
                et_number.setFocusable(true);
                et_number.setFocusableInTouchMode(true);
                break;



            default:
                break;
        }
    }
    public void tuichu(){
        SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.clear();
        //      editor.clear();
        editor.commit();
//        //RequestParams  rp=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/logout");
//        SharedPreferences sp=getSharedPreferences("sumao",0);
//        //SharedPreferences sp1=g
//        String unique=sp.getString("unique","");
//        rp.addParameter("_dynSessConf",unique);
//        Log.d("退出时的唯一标识",unique);
//        x.http().post(rp, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//
//                //Toast.makeText(context, "用户名称已删除", Toast.LENGTH_SHORT).show();
//
////                                            SettingActivity.this.finish();
//
////                                        }
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//                            }
//                        }.start();


    }


//      @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//          super.onActivityResult(requestCode, resultCode, data);
//          if (resultCode == 100) {
//              unique = data.getStringExtra("unique");
//              Log.d("返回过来的unique", unique);
//          }
//      }
//


//    private void showPopupWindow(final int num) {
//        popupWindowView = View.inflate(this, R.layout.buy_immediately_popup_window, null);
//        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
//        String unique = sp.getString("unique", "");
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
////                getPurchase();
//            }
//        }.start();
//        img_item_cart_buy_sub = ((TextView) popupWindowView.findViewById(R.id.img_item_cart_buy_sub));
//        img_item_cart_buy_add = ((TextView) popupWindowView.findViewById(R.id.img_item_cart_buy_add));
//        tv_item_cart_buy_num = ((TextView) popupWindowView.findViewById(R.id.tv_item_cart_buy_num));
//        determine_buy_immediately_button = ((Button) popupWindowView.findViewById(R.id.determine_buy_immediately_button));
//        img_item_cart_buy_sub.setOnClickListener(this);
//        img_item_cart_buy_add.setOnClickListener(this);
////        点击确定按钮
//        determine_buy_immediately_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                当立即购买时：
//                if (num == 1) {
//
//                    popupWindow.dismiss();
//                } else if (num == 2)//当加入购物车时
//                {
//                    /**
//                     * 在购物车创建订单的操作在这里执行
//                     */
//
//
//                    popupWindow.dismiss();
//                }
//            }
//        });
//        popupWindowView.measure(0, 0);
//        int w = getWindowManager().getDefaultDisplay().getWidth();
//        popupWindow = new PopupWindow(popupWindowView, w, popupWindowView.getMeasuredHeight());
//        popupWindow.setFocusable(true);
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
////        popupWindow.setAnimationStyle(R.style.PopupAnimation);
//        popupWindow.showAtLocation(buy_immediately, Gravity.BOTTOM, 0, 0);
//        popupWindow.update();
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                backgroundAlpha(1f);
//            }
//        });
//
//    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = GoodsInStockDetailActivity.this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        GoodsInStockDetailActivity.this.getWindow().setAttributes(lp);
    }

    public void getGoodsInStockDetailData(GoodsInStockDetailBean goodsInStockDetailBean) {
//        Loding.dis();
        //skuId
        et_number.setText(goodsInStockDetailBean.getCl_qigou());
        sku_id = goodsInStockDetailBean.getCl_cpid();
//        兰州石化7042
        tvName_auction.setText(goodsInStockDetailBean.getCl_mingcheng());
//        6000
        tvPrice_auction.setText(goodsInStockDetailBean.getCl_jine() + "");
//        剩余数量
        surplus_amount_et.setText(goodsInStockDetailBean.getCl_shuliang());
//        起购量
        purchase_quantity_et.setText(goodsInStockDetailBean.getCl_qigou() + "吨");
//        最小变量单位
        min_variable_et.setText(goodsInStockDetailBean.getCl_xiaobian() + "吨");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        release_date.setText(simpleDateFormat.format(Double.parseDouble(%获取到的数字%)));
//        交货时间
        delivery_time_et.setText(simpleDateFormat.format(Double.parseDouble(goodsInStockDetailBean.getCl_shijian() + "")));
//        交货结束时间
        delivery_time_et_end.setText("至" + simpleDateFormat.format(Double.parseDouble(goodsInStockDetailBean.getCl_shijianend() + "")));
//        仓库地址
        warehouse_address_et.setText(goodsInStockDetailBean.getCl_dizhi() + "");
        StringBuilder sb = new StringBuilder();
        int jhfssize = 0;
        if (goodsInStockDetailBean.getCl_jhfangshi() == null) {
            jhfssize = 0;
        } else {
            jhfssize = goodsInStockDetailBean.getCl_jhfangshi().size();
        }

        for (int i = 0; i < jhfssize; i++) {
            sb.append(goodsInStockDetailBean.getCl_jhfangshi().get(i));
            if (i != jhfssize - 1) {
                sb.append("、");
            }
        }
//        交货方式
        delivery_mode_et.setText(sb.toString());
//        分类
        classification_pre_sale_et.setText(goodsInStockDetailBean.getCl_fenlei());
//        仓库
        warehouse_et.setText(goodsInStockDetailBean.getCl_cangku());
//        地区
        region_et.setText(goodsInStockDetailBean.getCl_diqu());
//        公司
        company_et.setText(goodsInStockDetailBean.getCl_gongsi());
//        产品备注
        if (goodsInStockDetailBean.getCl_cpbeizhu() == null) {
            remark_ll.setVisibility(View.GONE);
        } else {
            tvRemark_auction.setText(goodsInStockDetailBean.getCl_cpbeizhu());
        }

//        总价格
        double x = (Double.parseDouble(et_number.getText().toString())) * (Double.parseDouble(tvPrice_auction.getText().toString()));
        LogUtils.log(x + "<---WXL");
        DecimalFormat df = new DecimalFormat("#.##");
        all_price.setText(df.format(x) + "");
//        相似产品
        relatedProduct = goodsInStockDetailBean.getRecentlyViewedProduct();
//        获取产品参数
        cl_attribute = goodsInStockDetailBean.getCl_attribute();
        LogUtils.log("GoodsInStockDetailActivity---->cl_attribute:" + cl_attribute);
//        卖家id
        cl_gongsiId = goodsInStockDetailBean.getCl_gongsiId();
//        合同
        contractString = goodsInStockDetailBean.getContract();
        //        监听et_number发生改变，从而改变总价
        et_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    et_number.setText("0");
                    et_number.setSelection(1);
                }
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 3) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 4);
                        et_number.setText(s);
                        et_number.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    et_number.setText(s);
                    et_number.setSelection(3);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        et_number.setText(s.subSequence(1, s.length()));
                        et_number.setSelection(1);
                        return;
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Double.parseDouble(et_number.getText().toString()) > Double.parseDouble(surplus_amount_et.getText().toString())) {
                    et_number.setText((surplus_amount_et.getText().toString()));
                    et_number.setSelection(surplus_amount_et.getText().toString().length());
                }
                double x = (Double.parseDouble(et_number.getText().toString())) * (Double.parseDouble(tvPrice_auction.getText().toString()));
                DecimalFormat df = new DecimalFormat("#.##");
                all_price.setText(df.format(x) + "");
            }
        });
        if (System.currentTimeMillis() > Long.parseLong(goodsInStockDetailBean.getCl_xiajiaend())
                || System.currentTimeMillis() > Long.parseLong(goodsInStockDetailBean.getCl_shijianend())) {
            buy_immediately.setBackgroundColor(getResources().getColor(R.color.gray));
            join_shopping_cart.setBackgroundColor(getResources().getColor(R.color.gray));
            Toast.makeText(this, "该商品已下架", Toast.LENGTH_SHORT).show();
        }

    }

    public void openOrClose() {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/colsedChecker";
        RequestParams rp = new RequestParams(url);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("开闭式接口返回的值", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String open = jsonObject.getString("results");
                    Log.d("open的值", open);
                    if (open.equals("false")) {
                        bt_Colse.setVisibility(View.VISIBLE);
                        layout_gongneng.setVisibility(View.GONE);
                    } else {
                        Log.d("走的else", "走的else");
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

    public void jurisdiction(String pId, final String choose, final String unique) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/userprofiling/PurchaseJurisdictionCheckActor/PurchaseResult";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("productId", pId);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("判断用户是否有权限购买改商品", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String jurisdiction = jsonObject.getString("cl_allow");
                    if (jurisdiction.equals("0")) {
                        //用户有权限购买
                        Log.d("用户有权利购买", "用户有权利购买");
                        if (choose.equals("购买")) {
                            if (System.currentTimeMillis() > Long.parseLong(goodsInStockDetailBean.getCl_xiajiaend())
                                    || System.currentTimeMillis() > Long.parseLong(goodsInStockDetailBean.getCl_shijianend())) {
                                Toast.makeText(GoodsInStockDetailActivity.this, "产品已下架", Toast.LENGTH_SHORT).show();
                                //Toast.makeText(this, "产品已下架", Toast.LENGTH_SHORT).show();
                            } else {
                                getPurchase(unique);
                            }
                        } else if (choose.equals("加入购物车")) {
                            if (System.currentTimeMillis() > Long.parseLong(goodsInStockDetailBean.getCl_xiajiaend())
                                    || System.currentTimeMillis() > Long.parseLong(goodsInStockDetailBean.getCl_shijianend())) {
                                Toast.makeText(GoodsInStockDetailActivity.this, "产品已下架", Toast.LENGTH_SHORT).show();
                            } else {
                                image.setVisibility(View.VISIBLE);
                                Animation animation = AnimationUtils.loadAnimation(GoodsInStockDetailActivity.this, R.anim.joincartanim);
                                animation.setFillAfter(true);
                                image.startAnimation(animation);
                                //Toast.makeText(this,"加入购物车成功",Toast.LENGTH_SHORT).show();
                                join_gouwuche();
                            }
                        }
                    } else if (jurisdiction.equals("2")) {
                        //用户没有权限购买
                        Toast.makeText(GoodsInStockDetailActivity.this, "该用户没有权限购买改产品", Toast.LENGTH_SHORT).show();
                    } else if (jurisdiction.equals("1")) {
                        Toast.makeText(GoodsInStockDetailActivity.this, "无上游资源方购买资质", Toast.LENGTH_SHORT).show();
                    } else if (jurisdiction.equals("3")) {
                        Toast.makeText(GoodsInStockDetailActivity.this, "该用户没有登录", Toast.LENGTH_SHORT).show();
                        tuichu();
                        Intent intent1 = new Intent(GoodsInStockDetailActivity.this, LoginUserActivity.class);
                        intent1.putExtra("roles", "buyer");
                        intent1.putExtra("position", 100);
                        startActivity(intent1);

                    } else if (jurisdiction.equals("4")) {
                        // Toast.makeText(GoodsInStockDetailActivity.this, "没有在购买期限内,不能购买", Toast.LENGTH_SHORT).show();
                    } else if (jurisdiction.equals("5")) {
                        Toast.makeText(GoodsInStockDetailActivity.this, "csc审核没有通过", Toast.LENGTH_SHORT).show();
                    } else if (jurisdiction.equals("6")) {
                        Toast.makeText(GoodsInStockDetailActivity.this, "企业财务员无权购买该产品", Toast.LENGTH_SHORT).show();
                    } else if (jurisdiction.equals("7")) {
                        Toast.makeText(GoodsInStockDetailActivity.this, "库存小于购买量", Toast.LENGTH_SHORT).show();
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


    public void join_gouwuche() {
        Log.d("进来这个方法", "进来这个方法");
        String productId = goodsInStockDetailBean.getCl_id();
        Log.d("选择框的值", et_number.getText().toString());
        Float quantity =Float.parseFloat(et_number.getText().toString()) * 1000 ;
        Float f=new Float(quantity);
        int quantityint=f.intValue();
//        Log.d("库存的值",Integer.parseInt(et_number.getText().toString())+"");

        Log.d("库存的值",Float.parseFloat(et_number.getText().toString())*1000+"");
        //  quantity=quantity.substring(0,quantity.length()-2);
        String skuId = sku_id;
        Log.d("skuId的值",skuId);
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique = sp.getString("unique", "");
        String _dynSessConf = unique;
        Log.d("现货唯一",_dynSessConf+"");
        goodsInStockDetailPresenter.joinToCart(productId, quantityint+"", skuId, _dynSessConf);

    }

    public void getPurchase(String unique) {
        String quantity = Integer.parseInt(et_number.getText().toString()) * 1000 + "";
        goodsInStockDetailPresenter.submitOrder(quantity, unique, goodsInStockDetailBean.getCl_id(), sku_id);

    }

    //    获取到提交订单后返回的订单列表
    @Override
    public void getSubmitOrderData(String result) {
        if (result.contains("formError")) {
            Toast.makeText(this, "该商品已下架", Toast.LENGTH_SHORT).show();
        } else {
            Gson gson = new Gson();
            Orders orders = gson.fromJson(result, Orders.class);
            Intent intent = new Intent();
            intent.putExtra("type", "fix");
            intent.putExtra("fix", (Serializable) orders.getOrderIdList());
            intent.setClass(GoodsInStockDetailActivity.this, PayOrderListActivity.class);
            startActivity(intent);
        }


    }

    @Override
    public void getJoinToCartData(String result) {
        if (result.contains("commerceItem")) {
            Toast.makeText(GoodsInStockDetailActivity.this, "加入购物车成功", Toast.LENGTH_SHORT).show();
        } else {
            try {
                JSONObject obj_result = new JSONObject(result);
                String message = obj_result.getString("formExceptions");
                JSONArray array = new JSONArray(message);
                JSONObject obj_array = array.getJSONObject(0);
                String info = obj_array.getString("localizedMessage");
                Toast.makeText(GoodsInStockDetailActivity.this, "" + info, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique = sp.getString("unique", "");
        initView();
        getGoodsInStockDetailData(goodsInStockDetailBean);
        initListener();
        goodsInStockDetailPresenter = new GoodsInStockDetailPresenter(this);


    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if(oldBottom != 0 && bottom != 0 &&(oldBottom - bottom > keyHeight)){

            Layout_Button_Open.setVisibility(View.GONE);
            //Toast.makeText(GoodsInStockDetailActivity.this, "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();

        }else if(oldBottom != 0 && bottom != 0 &&(bottom - oldBottom > keyHeight)){
            Layout_Button_Open.setVisibility(View.VISIBLE);
            Double jianshu=sub(Double.parseDouble(et_number.getText().toString()),Double.parseDouble(goodsInStockDetailBean.getCl_qigou()));
            Log.d("jianshu的值",jianshu+"");
            int beishu=(int)div(jianshu,Float.parseFloat(goodsInStockDetailBean.getCl_xiaobian()),0);
            Log.d("倍数的值",beishu+"");
            if (beishu<=0){
                //beishu=1;
                et_number.setText(goodsInStockDetailBean.getCl_qigou());
            }else {
                Double shuliang = mul(beishu, Double.parseDouble(goodsInStockDetailBean.getCl_xiaobian()));
                Log.d("数量的值", shuliang + "");
                // et_number.setText(shuliang+"");
                Double value123 = add(shuliang, Double.parseDouble(goodsInStockDetailBean.getCl_qigou()));
                et_number.setText(value123 + "");
            }

            double x = (Double.parseDouble(et_number.getText().toString())) * (Double.parseDouble(tvPrice_auction.getText().toString()));
            DecimalFormat df = new DecimalFormat("#.##");
            all_price.setText(df.format(x) + "");
            et_number.clearFocus();
            //et_number.setFocusable(false);



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
                    Layout_Button_Open.setVisibility(View.VISIBLE);
                    et_number.clearFocus();
                    Double jianshu=sub(Double.parseDouble(et_number.getText().toString()),Double.parseDouble(goodsInStockDetailBean.getCl_qigou()));
                    Log.d("jianshu的值",jianshu+"");
                    int beishu=(int)div(jianshu,Float.parseFloat(goodsInStockDetailBean.getCl_xiaobian()),0);
                    Log.d("倍数的值",beishu+"");
                    if (beishu==0){
                       // beishu=1;
                        et_number.setText(goodsInStockDetailBean.getCl_qigou());
                    }
                    Double shuliang=mul(beishu,Double.parseDouble(goodsInStockDetailBean.getCl_xiaobian()));
                    Log.d("数量的值",shuliang+"");
                    // et_number.setText(shuliang+"");
                    Double value123=add(shuliang,Double.parseDouble(goodsInStockDetailBean.getCl_qigou()));
                    et_number.setText(value123+"");

                    double x = (Double.parseDouble(et_number.getText().toString())) * (Double.parseDouble(tvPrice_auction.getText().toString()));
                    DecimalFormat df = new DecimalFormat("#.##");
                    all_price.setText(df.format(x) + "");
                   // et_number.clearFocus();
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
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