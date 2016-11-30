package com.silianchuangye.sumao.success.fragments.shoppingCart.dialog;

import android.app.AlertDialog;
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
    private String name;
    private String kucong;
    //private TextView tv_cart_dailog_name;
    public Cart_MyDialog(Context context ) {
        super(context);
        this.context=context;
        setCanceledOnTouchOutside(true);
    }
    public Cart_MyDialog(Context context,String name,String kucong ) {
        super(context);
        this.context=context;
        this.name=name;
        this.kucong=kucong;
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_dialog);
         Tv_name= (TextView) findViewById(R.id.tv_cart_dailog_name);
        Tv_name.setText("您购买的商品"+name+"库存不足！");
         Tv_num= (TextView) findViewById(R.id.tv_cart_dialog_num);
        Tv_num.setText("当前商品共"+kucong+"吨");
       // Tv_name
        btn= (Button) findViewById(R.id.btn_cart_dialog_close);
        img= (ImageView) findViewById(R.id.img_cart_dialog_errey);
        //点击关闭dialog
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroad();
                dismiss();
            }
        });
        //点击关闭dialog
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroad();
                dismiss();
            }
        });
    }

    private void sendBroad() {
        Intent intent=new Intent();
        intent.setAction("close_cart_dialog");
        context.sendBroadcast(intent);
    }
}
