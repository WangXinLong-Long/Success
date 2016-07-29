package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.silianchuangye.sumao.success.R;

public class Num extends AppCompatActivity implements View.OnClickListener{
    private ImageView img_back;
    private RelativeLayout relative_canler;
    private EditText edt_num;
    private Button btn_save;
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
                intent.putExtra("num",edt_num.getText().toString());
                setResult(2,intent);
                finish();
                break;
        }
    }
}
