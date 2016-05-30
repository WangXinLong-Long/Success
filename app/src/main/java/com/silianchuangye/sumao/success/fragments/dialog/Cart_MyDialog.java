package com.silianchuangye.sumao.success.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

/**
 * Created by Administrator on 2016/5/23.
 */
public class Cart_MyDialog extends AlertDialog{
    private TextView Tv_name,Tv_num;
    private Button btn;
    private ImageView img;
    private Context context;
    public Cart_MyDialog(Context context) {
        super(context);
        this.context=context;
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_dialog);
         Tv_num= (TextView) findViewById(R.id.tv_cart_dialog_num);
        btn= (Button) findViewById(R.id.btn_cart_dialog_close);
        img= (ImageView) findViewById(R.id.img_cart_dialog_errey);
        //点击关闭dialog
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroad();
            }
        });
        //点击关闭dialog
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroad();
            }
        });
    }

    private void sendBroad() {
        Intent intent=new Intent();
        intent.setAction("close_cart_dialog");
        context.sendBroadcast(intent);
    }
}
