package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.view.PaymentsOrder;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;
import com.silianchuangye.sumao.success.fragments.shoppingCart.dialog.Cart_MyDialog;
import com.silianchuangye.sumao.success.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public class GoodsInStockDetailActivity extends Activity implements View.OnClickListener, IGoodsInStockDetailView {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_in_stock_detail);

        activityiIntent = getIntent();
        goodsInStockDetailBean = (GoodsInStockDetailBean) activityiIntent.getSerializableExtra("goodsInStockDetailBean");

        initView();
        getGoodsInStockDetailData(goodsInStockDetailBean);
        initListener();
        goodsInStockDetailPresenter = new GoodsInStockDetailPresenter(this);

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
    }

    private void initView() {
        all_price = (TextView) findViewById(R.id.tv_item_cart_all_price);
        tv_jian = (TextView) findViewById(R.id.img_item_cart_buy_sub);
        tv_jia = (TextView) findViewById(R.id.img_item_cart_buy_add);
        et_number = (EditText) findViewById(R.id.tv_item_cart_buy_num);
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
                SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                String unique = sp.getString("unique", "");
                if (unique.equals("false")) {
                    Toast.makeText(GoodsInStockDetailActivity.this, "请登录后进行操作", Toast.LENGTH_SHORT).show();
                    Intent intent1  = new Intent(this, LoginUserActivity.class);
                    intent1.putExtra("roles", "buyer");

                    startActivity(intent1);
                } else {
                    if (System.currentTimeMillis() > Long.parseLong(goodsInStockDetailBean.getCl_xiajiaend())
                            || System.currentTimeMillis() > Long.parseLong(goodsInStockDetailBean.getCl_shijianend())) {
                        Toast.makeText(this, "产品已下架", Toast.LENGTH_SHORT).show();
                    } else {

                        getPurchase(unique);
                    }
                }
                break;
//             加入购物车：
            case R.id.join_shopping_cart:
                SharedPreferences sp1 = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                String unique1 = sp1.getString("unique", "");
                if (unique1.equals("false")) {
                    Intent intent1  = new Intent(this, LoginUserActivity.class);
                    intent1.putExtra("roles", "buyer");
                    startActivity(intent1);
                } else {
                    if (System.currentTimeMillis() > Long.parseLong(goodsInStockDetailBean.getCl_xiajiaend())
                            || System.currentTimeMillis() > Long.parseLong(goodsInStockDetailBean.getCl_shijianend())) {
                        Toast.makeText(this, "产品已下架", Toast.LENGTH_SHORT).show();
                    }else {
                        image.setVisibility(View.VISIBLE);
                        Animation animation = AnimationUtils.loadAnimation(this, R.anim.joincartanim);
                        animation.setFillAfter(true);
                        image.startAnimation(animation);
                        //Toast.makeText(this,"加入购物车成功",Toast.LENGTH_SHORT).show();
                        join_gouwuche();
                    }

                }
//
                break;
            case R.id.img_item_cart_buy_sub:
                String str = et_number.getText().toString();
                int num = Integer.valueOf(str);
                num--;
                if (num >= 1) {
                    et_number.setText("" + num);
                }
                break;
            case R.id.img_item_cart_buy_add:
                str = et_number.getText().toString();
                num = Integer.valueOf(str);
                num++;
                et_number.setText("" + num);
                break;
            case R.id.layoutContent_auction:
                Intent intentHX = new Intent();
                intentHX.setClass(GoodsInStockDetailActivity.this, LoginActivity.class);
                intentHX.putExtra(Constant.MESSAGE_TO_INTENT_EXTRA, Constant.MESSAGE_TO_DEFAULT);
//                传入卖家id
                intentHX.putExtra(Constant.IM_SERVICE_NUMBER, cl_gongsiId);
                startActivity(intentHX);
                break;


            default:
                break;
        }
    }

    private void showPopupWindow(final int num) {
        popupWindowView = View.inflate(this, R.layout.buy_immediately_popup_window, null);
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique = sp.getString("unique", "");
        new Thread() {
            @Override
            public void run() {
                super.run();
//                getPurchase();
            }
        }.start();
        img_item_cart_buy_sub = ((TextView) popupWindowView.findViewById(R.id.img_item_cart_buy_sub));
        img_item_cart_buy_add = ((TextView) popupWindowView.findViewById(R.id.img_item_cart_buy_add));
        tv_item_cart_buy_num = ((TextView) popupWindowView.findViewById(R.id.tv_item_cart_buy_num));
        determine_buy_immediately_button = ((Button) popupWindowView.findViewById(R.id.determine_buy_immediately_button));
        img_item_cart_buy_sub.setOnClickListener(this);
        img_item_cart_buy_add.setOnClickListener(this);
//        点击确定按钮
        determine_buy_immediately_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                当立即购买时：
                if (num == 1) {

                    popupWindow.dismiss();
                } else if (num == 2)//当加入购物车时
                {
                    /**
                     * 在购物车创建订单的操作在这里执行
                     */


                    popupWindow.dismiss();
                }
            }
        });
        popupWindowView.measure(0, 0);
        int w = getWindowManager().getDefaultDisplay().getWidth();
        popupWindow = new PopupWindow(popupWindowView, w, popupWindowView.getMeasuredHeight());
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        popupWindow.setAnimationStyle(R.style.PopupAnimation);
        popupWindow.showAtLocation(buy_immediately, Gravity.BOTTOM, 0, 0);
        popupWindow.update();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = GoodsInStockDetailActivity.this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        GoodsInStockDetailActivity.this.getWindow().setAttributes(lp);
    }

    public void getGoodsInStockDetailData(GoodsInStockDetailBean goodsInStockDetailBean) {
//        Loding.dis();
        //skuId
        sku_id = goodsInStockDetailBean.getCl_cpid();
//        兰州石化7042
        tvName_auction.setText(goodsInStockDetailBean.getCl_mingcheng());
//        6000
        tvPrice_auction.setText(goodsInStockDetailBean.getCl_jine() + "");
//        剩余数量
        surplus_amount_et.setText(goodsInStockDetailBean.getCl_shuliang());
//        起购量
        purchase_quantity_et.setText(goodsInStockDetailBean.getCl_qigou()+"吨");
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
        if (goodsInStockDetailBean.getCl_cpbeizhu()==null){
            remark_ll.setVisibility(View.GONE);
        }else {
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
        }

    }


    public void join_gouwuche() {
        String productId = goodsInStockDetailBean.getCl_id();
        String quantity = Integer.parseInt(et_number.getText().toString()) * 1000 + "";
        String skuId = sku_id;
        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        String unique = sp.getString("unique", "");
        String _dynSessConf = unique;
        goodsInStockDetailPresenter.joinToCart(productId, quantity, skuId, _dynSessConf);
        // rp.addParameter("");
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
            intent.putExtra("fix",(Serializable) orders.getOrderIdList());
            intent.setClass(GoodsInStockDetailActivity.this, PaymentsOrder.class);
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


}