package com.silianchuangye.sumao.success.customermanger;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

public class CustomerType extends AppCompatActivity implements View.OnClickListener{
private ImageView img_back,img_top,img_bottem;
    private Button btn_save;
    private TextView tv_top,tv_bottem;
    private RelativeLayout relative_top,relative_bottom;
    private String str;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_type);
        initView();
    }

    private void initView() {
        img_back= (ImageView) findViewById(R.id.img_logistics_title_bar_back);
        img_top= (ImageView) findViewById(R.id.img_customer_type_top);
        img_bottem= (ImageView) findViewById(R.id.img_customer_type_bottem);
        tv_top= (TextView) findViewById(R.id.tv_customer_type_top);
        tv_bottem= (TextView) findViewById(R.id.tv_customer_type_bottem);
        btn_save= (Button) findViewById(R.id.btn_customer_type);
        relative_top= (RelativeLayout) findViewById(R.id.relative_customer_top);
        relative_bottom= (RelativeLayout) findViewById(R.id.relative_customer_bottom);
        relative_bottom.setOnClickListener(this);
        relative_top.setOnClickListener(this);
        img_back.setOnClickListener(this);
        btn_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_logistics_title_bar_back:
                finish();
                break;
            case R.id.relative_customer_top:
                img_top.setImageResource(R.mipmap.cart_select);
                img_bottem.setImageResource(R.mipmap.cart_select_null);
                str=tv_top.getText().toString();
                break;
            case R.id.relative_customer_bottom:
                img_bottem.setImageResource(R.mipmap.cart_select);
                img_top.setImageResource(R.mipmap.cart_select_null);
                str=tv_bottem.getText().toString();
                break;
            case R.id.btn_customer_type:
                Toast.makeText(this,"保存",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setAction("type");
                intent.putExtra("type",str);
                sendBroadcast(intent);
                finish();
                break;
        }
    }
}
