package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

public class NewPrice extends AppCompatActivity implements View.OnClickListener{
private ImageView img_back;
    private RelativeLayout relative_canler;
    private EditText edt_num;
    private Button btn_save;
    private TextView tv_customer_manager_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_price);
        initView();
    }

    private void initView() {
        img_back= (ImageView) findViewById(R.id.img_logistics_title_bar_back);
        relative_canler= (RelativeLayout) findViewById(R.id.tv_logistics_title_bar_search);
        edt_num= (EditText) findViewById(R.id.edt_newprice_num);
        btn_save= (Button) findViewById(R.id.btn_newprivce_save);
        tv_customer_manager_title= (TextView) findViewById(R.id.tv_customer_manager_title);
        tv_customer_manager_title.setText("新单价(元/吨)");
        img_back.setOnClickListener(this);
        relative_canler.setOnClickListener(this);
        edt_num.setOnClickListener(this);
        btn_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_logistics_title_bar_back:
                finish();
                break;
            case R.id.tv_logistics_title_bar_search:
                finish();
                break;
            case R.id.btn_newprivce_save:
                Intent intent=new Intent();
                intent.putExtra("new",edt_num.getText().toString());
                setResult(1,intent);
                finish();
                break;
        }
    }
}
