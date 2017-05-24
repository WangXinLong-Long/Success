package com.silianchuangye.sumao.success.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.goodsInStock.OrderGoodsActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsMVP.view.CreateLogistics;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

/**
 * Created by junjun on 2016/7/7.
 */
public class Ok_Dialog extends Activity {
    private Context context;
    private String order_number;
    String type;
    private Button btn_ok_dialog;
    private LinearLayout linear;
    private TextView tv_orderId;
    private boolean isClosePrevious;
    private TextView tv_ok_text;
    private TextView pay_success;
    private Button bt_my_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_ok);
        type = getIntent().getStringExtra("type");
        Log.d("type的值",type);
//        确定
        btn_ok_dialog = (Button) findViewById(R.id.btn_ok_dialog);
//        订单详情、物流需求
        linear = (LinearLayout) findViewById(R.id.linear_bottem_ok);
//        您订单支付成功
        tv_ok_text = ((TextView) findViewById(R.id.tv_ok_text));
//        支付成功
        pay_success = ((TextView) findViewById(R.id.pay_success));
        LogUtils.log("type的类型是---》" + type);
        ImageView iv_cancel = (ImageView) findViewById(R.id.iv_dialog_ok_cancel);

        TextView tv_order_number = (TextView) findViewById(R.id.tv_order_number_ok);
        String order_id = getIntent().getStringExtra("number");
        Log.d("订单详请的or",order_id);
        bt_my_order = (Button) findViewById(R.id.bt_my_order);
        Button bt_wuliu = (Button) findViewById(R.id.bt_wulliu);
        if(type.equals("现货")){
            tv_ok_text.setText("恭喜您,支付成功!");
            tv_order_number.setVisibility(View.GONE);
            pay_success.setVisibility(View.GONE);
            btn_ok_dialog.setVisibility(View.VISIBLE);
            bt_my_order.setVisibility(View.VISIBLE);
//            tv_ok_text.setText("恭喜您,支付成功!");
//            tv_order_number.setVisibility(View.GONE);
//            pay_success.setVisibility(View.GONE);
//            btn_ok_dialog.setVisibility(View.VISIBLE);
            linear.setVisibility(View.GONE);
        }else if (type.equals("预售")){
            tv_ok_text.setText("恭喜您,保证金支付成功!");
            tv_order_number.setVisibility(View.GONE);
            pay_success.setVisibility(View.GONE);
            btn_ok_dialog.setVisibility(View.GONE);
            bt_my_order.setVisibility(View.VISIBLE);
            bt_wuliu.setVisibility(View.VISIBLE);

        }else if (type.equals("密封竞拍")||type.equals("公开竞拍")){
            tv_ok_text.setText("恭喜您,报价成功!");
            tv_order_number.setVisibility(View.GONE);
            pay_success.setVisibility(View.GONE);
            btn_ok_dialog.setVisibility(View.VISIBLE);
            linear.setVisibility(View.GONE);
        }else{
            tv_ok_text.setText("恭喜您,支付成功!");
            tv_order_number.setVisibility(View.GONE);
            pay_success.setVisibility(View.GONE);
            btn_ok_dialog.setVisibility(View.VISIBLE);
            linear.setVisibility(View.GONE);
        }

        tv_order_number.setText(order_id);
        btn_ok_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();

                if (type.equals("预售")){
                    Intent intent=new Intent(Ok_Dialog.this,OrderGoodsActivity.class);
                    intent.putExtra("title","预售订单");
                    startActivity(intent);
                    finish();
                }else if (type.equals("团购")||type.equals("竞拍")||type.equals("密封竞拍")||type.equals("公开竞拍")){
                    finish();
                }else {
                    Intent intent=new Intent(Ok_Dialog.this,OrderGoodsActivity.class);
                    intent.putExtra("title","现货订单");
                    startActivity(intent);
                    finish();
                }
            }
        });
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ok_Dialog.this.finish();
            }
        });
        bt_my_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("现货订单")) {
                    isClosePrevious = true;
                } else {
                    Intent intent = new Intent(Ok_Dialog.this, OrderGoodsActivity.class);
                    if (type.equals("现货")) {
                        intent.putExtra("title", "现货订单");
                    } else if (type.equals("预售")) {
                        intent.putExtra("title", "预售订单");
                    } else if (type.equals("公开竞拍")) {
                        intent.putExtra("title", "现货订单");
                    }
                    startActivity(intent);
                    isClosePrevious = true;
                }

                finish();
            }
        });
        bt_wuliu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到物流
//                Intent intent = new Intent(Ok_Dialog.this, CreateLogistics.class);
//                startActivity(intent);
//                isClosePrevious = true;
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isClosePrevious && type.equals("现货")) {
            sendBroadcast(new Intent().setAction("finish"));
        } else if (isClosePrevious && type.equals("现货订单")) {
            sendBroadcast(new Intent().setAction("finishsportorder"));
        } else if (isClosePrevious && type.equals("公开竞拍")) {
            sendBroadcast(new Intent().setAction("finishOpenAuction"));
        }
    }
}
