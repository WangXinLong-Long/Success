package com.silianchuangye.sumao.success.fragments.logistics;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.silianchuangye.sumao.success.R;

public class CreateLogisticsNeed extends AppCompatActivity implements View.OnClickListener{
    private ImageView img_logistics_title_back, img_logistics_need_select_xunbang,
            img_logistics_need_select_buy, img_logistics_need_select_maifang;
    private TextView tv_logistics_need_canle;
    //本次发货数量和仓库名称
    private TextView tv_logistics_need_fahuo_num, tv_logistics_need_cangku_name;
    private RelativeLayout relative_logistics_need_address,
            relative_logistics_need_shouhuo_address, relative_logistics_need_message_address;
    private LinearLayout relative_logistics_need_three;
    private Button btn_logistics_need_ok,btn_logistics_need_kefu;
    private ListView lv_logistics_need,lv_logistics_need_three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_logistics_need);
        initView();
    }

    private void initView() {
        img_logistics_title_back = (ImageView) findViewById(R.id.img_logistics_title_bar_back);
        tv_logistics_need_canle= (TextView) findViewById(R.id.tv_logistics_title_bar);
        img_logistics_need_select_xunbang = (ImageView) findViewById(R.id.img_logistics_need_select_xunbang);
        img_logistics_need_select_buy = (ImageView) findViewById(R.id.img_logistics_need_select_buy);
        img_logistics_need_select_maifang = (ImageView) findViewById(R.id.img_logistics_need_select_maifang);
        tv_logistics_need_fahuo_num = (TextView) findViewById(R.id.tv_logistics_need_fahuo_num);
        tv_logistics_need_cangku_name = (TextView) findViewById(R.id.tv_logistics_need_cangku_name);
        relative_logistics_need_three= (LinearLayout) findViewById(R.id.linear_need_three);
        relative_logistics_need_address= (RelativeLayout) findViewById(R.id.relative_logistics_need_address);
        relative_logistics_need_shouhuo_address= (RelativeLayout) findViewById(R.id.relative_logistics_need_shouhuo_address);
        relative_logistics_need_message_address= (RelativeLayout) findViewById(R.id.relative_logistics_need_message_address);
        btn_logistics_need_ok= (Button) findViewById(R.id.btn_logistics_need_ok);
        lv_logistics_need= (ListView) findViewById(R.id.lv_logistics_need);
        btn_logistics_need_kefu= (Button) findViewById(R.id.btn_logistics_need_kefu);
        lv_logistics_need_three= (ListView) findViewById(R.id.lv_logistics_need_three);

        img_logistics_title_back.setOnClickListener(this);
        tv_logistics_need_canle.setOnClickListener(this);
        btn_logistics_need_kefu.setOnClickListener(this);
        btn_logistics_need_ok.setOnClickListener(this);
        relative_logistics_need_address.setOnClickListener(this);
        relative_logistics_need_message_address.setOnClickListener(this);
        relative_logistics_need_shouhuo_address.setOnClickListener(this);
        img_logistics_need_select_maifang.setOnClickListener(this);
        img_logistics_need_select_buy.setOnClickListener(this);
        img_logistics_need_select_xunbang.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_logistics_title_bar_back:
                finish();
                break;
            case R.id.tv_logistics_title_bar:
                finish();
                break;
            case R.id.btn_logistics_need_kefu:
                Toast.makeText(this,"点击了客服",Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_logistics_need_select_xunbang:
                Toast.makeText(this,"迅帮配送",Toast.LENGTH_SHORT).show();
                showImg(img_logistics_need_select_xunbang);
                break;
            case R.id.img_logistics_need_select_buy:
                Toast.makeText(this,"买家自提",Toast.LENGTH_SHORT).show();
               showImg(img_logistics_need_select_buy);
                break;
            case R.id.img_logistics_need_select_maifang:
                Toast.makeText(this,"卖家配送",Toast.LENGTH_SHORT).show();
                showImg(img_logistics_need_select_maifang);
                break;
            case R.id.relative_logistics_need_address:
                Toast.makeText(this,"选择已有地址",Toast.LENGTH_SHORT).show();
                break;
            case R.id.relative_logistics_need_shouhuo_address:
                Toast.makeText(this,"收货地址",Toast.LENGTH_SHORT).show();
                break;
            case R.id.relative_logistics_need_message_address:
                Toast.makeText(this,"收货详细地址",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_logistics_need_ok:
                Toast.makeText(this,"提交物流需求",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void showImg(View v){
        switch (v.getId()){
            case R.id.img_logistics_need_select_xunbang:
                lv_logistics_need_three.setVisibility(View.GONE);
                relative_logistics_need_three.setVisibility(View.VISIBLE);
                img_logistics_need_select_xunbang.setImageResource(R.mipmap.cart_select);
                img_logistics_need_select_buy.setImageResource(R.mipmap.cart_select_null);
                img_logistics_need_select_maifang.setImageResource(R.mipmap.cart_select_null);
                break;
            case R.id.img_logistics_need_select_maifang:
                lv_logistics_need_three.setVisibility(View.GONE);
                relative_logistics_need_three.setVisibility(View.VISIBLE);
                img_logistics_need_select_xunbang.setImageResource(R.mipmap.cart_select_null);
                img_logistics_need_select_buy.setImageResource(R.mipmap.cart_select_null);
                img_logistics_need_select_maifang.setImageResource(R.mipmap.cart_select);
                break;
            case R.id.img_logistics_need_select_buy:
                lv_logistics_need_three.setVisibility(View.VISIBLE);
                relative_logistics_need_three.setVisibility(View.GONE);
                img_logistics_need_select_xunbang.setImageResource(R.mipmap.cart_select_null);
                img_logistics_need_select_buy.setImageResource(R.mipmap.cart_select);
                img_logistics_need_select_maifang.setImageResource(R.mipmap.cart_select_null);
                break;
        }
    }
}
