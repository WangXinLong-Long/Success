package com.silianchuangye.sumao.success.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

/**
 * Created by junjun on 2016/7/7.
 */
public class Ok_Dialog extends AlertDialog {

    protected Ok_Dialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_ok);
        ImageView iv_cancel= (ImageView)findViewById(R.id.iv_dialog_ok_cancel);
        TextView tv_order_number= (TextView)findViewById(R.id.tv_order_number_ok);
        Button bt_my_order= (Button)findViewById(R.id.bt_my_order);
        Button bt_wuliu= (Button) findViewById(R.id.bt_wulliu);
        tv_order_number.setText("");
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("进入","了dialog");



            }
        });
        bt_my_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到我的订单
                Log.d("跳转","我的订单");
                //dismiss();
            }
        });
        bt_wuliu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到物流
                Log.d("跳转","物流需求");
            }
        });
    }


}
