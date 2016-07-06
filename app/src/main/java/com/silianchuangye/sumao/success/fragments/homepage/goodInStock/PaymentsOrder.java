package com.silianchuangye.sumao.success.fragments.homepage.goodInStock;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class PaymentsOrder extends Activity implements View.OnClickListener{

    private TextView title_bar_white_title;
    private TextView product_order_number;
    private TextView money_number;
    private TextView residual_time;
    private TextView surplus_amount_et;
    private TextView purchase_quantity_et;
    private TextView min_variable_et;
    private TextView delivery_time_et;
    private TextView classification_pre_sale_et;
    private TextView warehouse_et;
    private TextView region_et;
    private TextView company_et;
    private TextView total_money;
    private Button buy_immediately;
    private ImageView title_bar_white_shopping_cart;
    private ImageView title_bar_white_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payments_order_activity);
        //页面标题 “支付订单”
        title_bar_white_title = (TextView)findViewById(R.id.title_bar_white_title);
//        设置时：   产品订单号：+number
        product_order_number = (TextView)findViewById(R.id.product_order_number);
//        支付总金额
        money_number = (TextView)findViewById(R.id.money_number);
//        设置时： 提示：剩余时间+num+分
        residual_time = (TextView)findViewById(R.id.residual_time);
//        品种
        surplus_amount_et = (TextView)findViewById(R.id.surplus_amount_et);
//        产品编号
        purchase_quantity_et = (TextView)findViewById(R.id.purchase_quantity_et);
//        生产企业
        min_variable_et = (TextView)findViewById(R.id.min_variable_et);
//        仓库
        delivery_time_et = (TextView)findViewById(R.id.delivery_time_et);
//        产品单价
        classification_pre_sale_et = (TextView)findViewById(R.id.classification_pre_sale_et);
//        数量
        warehouse_et = (TextView)findViewById(R.id.warehouse_et);
//        产品总价
        region_et = (TextView)findViewById(R.id.region_et);
//        公司
        company_et = (TextView)findViewById(R.id.company_et);
//        总金额
        total_money = (TextView)findViewById(R.id.total_money);
//        立即购买
        buy_immediately = (Button) findViewById(R.id.buy_immediately);
//        因为标题是复用的，现在要把多余的图标影藏掉
        title_bar_white_shopping_cart = (ImageView) findViewById(R.id.title_bar_white_shopping_cart);
        title_bar_white_shopping_cart.setVisibility(View.INVISIBLE);
//        返回按钮设置监听
        title_bar_white_back = (ImageView) findViewById(R.id.title_bar_white_back);
        title_bar_white_back.setOnClickListener(this);

//        为  立即支付按钮  设置监听
        buy_immediately.setOnClickListener(this);
//          设置标题 支付订单
        title_bar_white_title.setText("支付订单");

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.buy_immediately:

               break;
           case R.id.title_bar_white_back:
               finish();
               break;


        }
    }
}
