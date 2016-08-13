package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.MainActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.customCalendar.CalendarView;
import com.silianchuangye.sumao.success.fragments.homepage.auction.VesselThreeActivity;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.LikeProduct;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.PaymentsOrder;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.SeeProduct;
import com.silianchuangye.sumao.success.fragments.shoppingCart.dialog.Cart_MyDialog;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public class GoodsInStockDetailActivity extends Activity implements View.OnClickListener{
    CalendarView calendarView;
    ImageView title_bar_white_back;
    TextView title_bar_white_title;
    ImageView title_bar_white_shopping_cart;
    RelativeLayout pre_sale_sale_detail_detail;
    RelativeLayout pre_sale_sale_detail_similar_product,pre_sale_sale_detail_similar_liulan;
    Button join_shopping_cart,buy_immediately;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_in_stock_detail);

        activityiIntent = getIntent();
        cl_id = activityiIntent.getStringExtra("cl_id");

        initView();
        initListener();

    }

    private void initListener() {
        buy_immediately.setOnClickListener(this);
        join_shopping_cart.setOnClickListener(this);
        title_bar_white_back.setOnClickListener(this);
        title_bar_white_shopping_cart.setOnClickListener(this);
        pre_sale_sale_detail_detail.setOnClickListener(this);
        pre_sale_sale_detail_similar_product.setOnClickListener(this);
        pre_sale_sale_detail_similar_liulan.setOnClickListener(this);
        title_bar_white_title.setText("现货");
    }

    private void initView() {
        title_bar_white_back = ((ImageView) findViewById(R.id.title_bar_white_back));
        title_bar_white_title = ((TextView) findViewById(R.id.title_bar_white_title));
        title_bar_white_shopping_cart = ((ImageView) findViewById(R.id.title_bar_white_shopping_cart));
        pre_sale_sale_detail_detail = ((RelativeLayout) findViewById(R.id.pre_sale_sale_detail_detail));
        pre_sale_sale_detail_similar_product = ((RelativeLayout) findViewById(R.id.pre_sale_sale_detail_similar_product));
        pre_sale_sale_detail_similar_liulan= (RelativeLayout) findViewById(R.id.pre_sale_sale_detail_similar_liulan);
        buy_immediately = ((Button) findViewById(R.id.buy_immediately));
        join_shopping_cart = ((Button) findViewById(R.id.join_shopping_cart));
        dialog = new Cart_MyDialog(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId())
        {
            case R.id.title_bar_white_back:
                finish();
                break;
            case R.id.title_bar_white_shopping_cart:

                intent.setClass(GoodsInStockDetailActivity.this, MainActivity.class);
                intent.putExtra("cart",1);
                startActivity(intent);
                break;
            case R.id.pre_sale_sale_detail_detail:
                intent.setClass(GoodsInStockDetailActivity.this, VesselThreeActivity.class);
                startActivity(intent);
                break;
            case R.id.pre_sale_sale_detail_similar_product:
                Toast.makeText(this,"相似产品",Toast.LENGTH_SHORT).show();
                intent.setClass(GoodsInStockDetailActivity.this, LikeProduct.class);
                startActivity(intent);
                break;
            case R.id.pre_sale_sale_detail_similar_liulan:
                Toast.makeText(this,"浏览记录",Toast.LENGTH_SHORT).show();
                intent.setClass(GoodsInStockDetailActivity.this, SeeProduct.class);
                startActivity(intent);
                break;
//            立即购买：
            case R.id.buy_immediately:
                int type = 1;
                showPopupWindow(type);
                backgroundAlpha(0.5f);
                break;
//             加入购物车：
            case R.id.join_shopping_cart:
                type = 2;
                showPopupWindow(type);
                backgroundAlpha(0.5f);
                break;
            case R.id.img_item_cart_buy_sub:
                String str=tv_item_cart_buy_num.getText().toString();
                int num=Integer.valueOf(str);
                num--;
                if(num>=1) {
                    tv_item_cart_buy_num.setText("" + num);
                }
                break;
            case R.id.img_item_cart_buy_add:
                str=tv_item_cart_buy_num.getText().toString();
                num=Integer.valueOf(str);
                num++;
                if(num>=15){
                    dialog.show();
                }
                if(num<15&&num>=0) {
                    tv_item_cart_buy_num.setText("" + num);
                }
                break;
            default:
                break;
        }
    }


    private void showPopupWindow(final int num) {
        popupWindowView = View.inflate(this,R.layout.buy_immediately_popup_window,null);
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
                if (num==1)
                {
                    Intent intent = new Intent();
                    intent.setClass(GoodsInStockDetailActivity.this,PaymentsOrder.class);
                    startActivity(intent);
                    popupWindow.dismiss();
                }else if (num==2)//当加入购物车时
                {
                    /**
                     * 在购物车创建订单的操作在这里执行
                     */
                    popupWindow.dismiss();
                }
            }
        });
        popupWindowView.measure(0,0);
        int w = getWindowManager().getDefaultDisplay().getWidth();
        popupWindow = new PopupWindow(popupWindowView,w,popupWindowView.getMeasuredHeight());
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.PopupAnimation);
        popupWindow.showAtLocation(buy_immediately, Gravity.BOTTOM,0,0);
        popupWindow.update();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

    }
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = GoodsInStockDetailActivity.this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        GoodsInStockDetailActivity.this.getWindow().setAttributes(lp);
    }
}