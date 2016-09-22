package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
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
import com.silianchuangye.sumao.success.dialog.Error_Dialog;
import com.silianchuangye.sumao.success.dialog.Ok_Dialog;
import com.silianchuangye.sumao.success.fragments.homepage.auction.VesselThreeActivity;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean.CLAttribute;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean.OrderIdList;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean.RelatedProduct;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.presenter.GoodsInStockDetailPresenter;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.LikeProduct;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.PaymentsOrder;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.SeeProductMVP.view.SeeProduct;
import com.silianchuangye.sumao.success.fragments.shoppingCart.dialog.Cart_MyDialog;
import com.silianchuangye.sumao.success.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private String cl_id;
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
    private RelatedProduct relatedProduct;
    private ArrayList<CLAttribute> cl_attribute;
    private LinearLayout layoutContent_auction;
    private String cl_gongsiId;
    private String contractString;
    private String qigou;
    private String sku_id;
    private TextView all_price;
    private EditText et_number;
    private TextView tv_jian;
    private TextView tv_jia;
    private String unique;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_in_stock_detail);

        activityiIntent = getIntent();
        cl_id = activityiIntent.getStringExtra("cl_id");

        initView();
        initListener();
        goodsInStockDetailPresenter = new GoodsInStockDetailPresenter(this);
        goodsInStockDetailPresenter.GoodsInStockDetailSendData(cl_id);

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
        all_price= (TextView) findViewById(R.id.tv_item_cart_all_price);
        tv_jian= (TextView) findViewById(R.id.img_item_cart_buy_sub);
        tv_jia= (TextView) findViewById(R.id.img_item_cart_buy_add);
        et_number= (EditText) findViewById(R.id.tv_item_cart_buy_num);
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
//        积分规则
        integral_rule = ((TextView) findViewById(R.id.integral_rule));
//        剩余数量
        surplus_amount_et = ((TextView) findViewById(R.id.surplus_amount_et));
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
                LogUtils.log("GoodsInStockDetailActivity---->cl_attribute:"+cl_attribute);
                intent.putExtra("cl_attribute",cl_attribute);
                intent.setClass(GoodsInStockDetailActivity.this, VesselThreeActivity.class);
                intent.putExtra("title","现货");
                intent.putExtra("contract",contractString);
                startActivity(intent);
                break;
            case R.id.pre_sale_sale_detail_similar_product:
                if (relatedProduct!=null){
                    Bundle bundle = new Bundle();

                    bundle.putSerializable("relatedProduct",relatedProduct);
                    intent.putExtras(bundle);
                    intent.setClass(GoodsInStockDetailActivity.this, LikeProduct.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this,"暂时还没有相似产品哦",Toast.LENGTH_SHORT).show();
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
                SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                unique = sp.getString("unique","");
                if (unique.equals("false")){
                    Toast.makeText(GoodsInStockDetailActivity.this,"请登录后进行操作",Toast.LENGTH_SHORT).show();
                }else {
//                    showPopupWindow(type);
//                    backgroundAlpha(0.5f);
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            getPurchase(unique);
                        }
                    }.start();
                }

                break;
//             加入购物车：
            case R.id.join_shopping_cart:
////                type = 2;
////                showPopupWindow(type);
////                backgroundAlpha(0.5f);
                image.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.joincartanim);
                animation.setFillAfter(true);
                image.startAnimation(animation);
                //Toast.makeText(this,"加入购物车成功",Toast.LENGTH_SHORT).show();
                new Thread(){
                        @Override
                        public void run() {
                           // super.run();
                           join_gouwuche();
                        }
                    }.start();
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
//                if (num >= Integer.valueOf(surplus_amount_et.getText().toString())) {
//                    dialog.show();
//                }
              //  if (num < Integer.parseInt(surplus_amount_et.getText().toString()) && num >= 0) {
                    et_number.setText("" + num);
              //  }
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

    @Override
    public void getGoodsInStockDetailData(GoodsInStockDetailBean goodsInStockDetailBean) {
        //skuId
         sku_id=goodsInStockDetailBean.getCl_cpid();
//        兰州石化7042
        tvName_auction.setText(goodsInStockDetailBean.getCl_mingcheng());
//        6000
        tvPrice_auction.setText(goodsInStockDetailBean.getCl_jine());
//        积分规则
        integral_rule.setText(goodsInStockDetailBean.getCl_jifen());
//        剩余数量
        surplus_amount_et.setText(goodsInStockDetailBean.getCl_shuliang());
//        最小变量单位
        min_variable_et.setText(goodsInStockDetailBean.getCl_xiaobian());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
//        release_date.setText(simpleDateFormat.format(Double.parseDouble(%获取到的数字%)));
//        交货时间
        delivery_time_et.setText(simpleDateFormat.format(Double.parseDouble(goodsInStockDetailBean.getCl_shijian())));
//        交货结束时间
        delivery_time_et_end.setText("-"+simpleDateFormat.format(Double.parseDouble(goodsInStockDetailBean.getCl_shijianend())));
//        仓库地址
        warehouse_address_et.setText(goodsInStockDetailBean.getCl_dizhi());
        StringBuilder sb = new StringBuilder();
        int jhfssize = goodsInStockDetailBean.getCl_jhfangshi().size();
        for (int i = 0 ;i< jhfssize;i++){
            sb.append(goodsInStockDetailBean.getCl_jhfangshi().get(i));
            if (i != jhfssize-1 ){
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
        tvRemark_auction.setText(goodsInStockDetailBean.getCl_jifen());
//        相似产品
        relatedProduct = goodsInStockDetailBean.getRelatedProducts();
//        获取产品参数
        cl_attribute = goodsInStockDetailBean.getCl_attribute();
        LogUtils.log("GoodsInStockDetailActivity---->cl_attribute:"+cl_attribute);
//        卖家id
        cl_gongsiId = goodsInStockDetailBean.getCl_gongsiId();
//        合同
        contractString = goodsInStockDetailBean.getContract();

    }
    public void join_gouwuche(){
        String url="http://192.168.32.126:7023/rest/model/atg/commerce/ShoppingCartActor/addItemToOrder";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("productId",cl_id);
        rp.addParameter("quantity",(Integer.parseInt(et_number.getText().toString())*1000)+"");
        rp.addParameter("skuId",sku_id);
        Log.d("skuid的值",sku_id);
        SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
        String unique=sp.getString("unique","");
        rp.addParameter("_dynSessConf",unique);
        Log.d("添加到购物车的skuid",""+rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("添加到购物车result",result);
                if (result.contains("commerceItem")){
                    Toast.makeText(GoodsInStockDetailActivity.this,"加入购物车成功",Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        JSONObject obj_result = new JSONObject(result);
                        String message=obj_result.getString("formExceptions");
                        JSONArray array=new JSONArray(message);
                        JSONObject obj_array=array.getJSONObject(0);
                        String info=obj_array.getString("localizedMessage");
                        Toast.makeText(GoodsInStockDetailActivity.this, ""+info, Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
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
        // rp.addParameter("");
    }
    public void getPurchase(String unique){
        Intent intent = new Intent();
        StringBuilder sb = new StringBuilder();
        sb.append((Integer.parseInt(et_number.getText().toString())*1000)+",");
        sb.append(unique+",");
        sb.append(cl_id+",");
        sb.append(sku_id);
        String string = sb.toString();
//        List<String> list_id = new ArrayList<>();
//        list_id.add(string);
        intent.putExtra("type","fix");
        intent.putExtra("fix",string);
        intent.setClass(GoodsInStockDetailActivity.this, PaymentsOrder.class);
        startActivity(intent);

    }



}