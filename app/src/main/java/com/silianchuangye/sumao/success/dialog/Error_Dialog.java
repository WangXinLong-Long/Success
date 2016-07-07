package com.silianchuangye.sumao.success.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

/**
 * Created by junjun on 2016/7/7.
 */
public class Error_Dialog extends AlertDialog {
    private ImageView error_dialog_cancel;
    private TextView tv_order_number;
    private Button bt_reZhifu;

    protected Error_Dialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_error);
        error_dialog_cancel= (ImageView) findViewById(R.id.iv_dialog_error_cancel);
        tv_order_number= (TextView) findViewById(R.id.tv_order_number);
        bt_reZhifu= (Button) findViewById(R.id.bt_rezhifu);
        error_dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_order_number.setText("");
        bt_reZhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //再次调到支付界面
            }
        });
    }
}
