package com.silianchuangye.sumao.success.customermanger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.silianchuangye.sumao.success.R;

public class NoAgree extends AppCompatActivity implements View.OnClickListener{
private RelativeLayout tv_logistics_title_bar_search;
    private ImageView img_logistics_title_bar_back;
    private EditText edt_buy_yuanyin;
    private Button btn_buy_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_agree);
        initView();
    }

    private void initView() {
        tv_logistics_title_bar_search= (RelativeLayout) findViewById(R.id.tv_logistics_title_bar_search);
        img_logistics_title_bar_back= (ImageView) findViewById(R.id.img_logistics_title_bar_back);
        edt_buy_yuanyin= (EditText) findViewById(R.id.edt_buy_yuanyin);
        btn_buy_save= (Button) findViewById(R.id.btn_buy_save);
        img_logistics_title_bar_back.setOnClickListener(this);
        tv_logistics_title_bar_search.setOnClickListener(this);
        btn_buy_save.setOnClickListener(this);
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
            case R.id.btn_buy_save:

                break;
        }
    }
}
