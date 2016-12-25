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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_ok);
        type=getIntent().getStringExtra("type");
        btn_ok_dialog= (Button) findViewById(R.id.btn_ok_dialog);
        linear= (LinearLayout) findViewById(R.id.linear_bottem_ok);
        LogUtils.log("type的类型是---》"+type);
        if(type.equals("")){
            linear.setVisibility(View.GONE);
        }else{
            btn_ok_dialog.setVisibility(View.GONE);
        }
        ImageView iv_cancel= (ImageView)findViewById(R.id.iv_dialog_ok_cancel);

        TextView tv_order_number= (TextView)findViewById(R.id.tv_order_number_ok);
        String order_id=getIntent().getStringExtra("number");
        Log.d("订单编号",order_id+"aa");
        tv_order_number.setText(order_id);
        btn_ok_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
 
        Button bt_my_order= (Button)findViewById(R.id.bt_my_order);
        Button bt_wuliu= (Button) findViewById(R.id.bt_wulliu);
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Ok_Dialog.this.finish();
            }
        });
        bt_my_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Ok_Dialog.this, OrderGoodsActivity.class);
                if(type.equals("现货")) {
                    intent.putExtra("title", "现货订单");
                }else if(type.equals("预售")){
                    intent.putExtra("title", "预售订单");
                }
                startActivity(intent);
                isClosePrevious = true;
                finish();
            }
        });
        bt_wuliu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到物流
              Intent intent = new Intent(Ok_Dialog.this, CreateLogistics.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isClosePrevious&&type.equals("现货")){
            sendBroadcast(new Intent().setAction("finish"));
        }
    }
}
