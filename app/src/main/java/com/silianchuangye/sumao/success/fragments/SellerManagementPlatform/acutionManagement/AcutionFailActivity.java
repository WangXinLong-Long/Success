package com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.acutionManagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.silianchuangye.sumao.success.R;

public class AcutionFailActivity extends AppCompatActivity {
    private ImageView iv_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acution_fail);
        init();
        event();
    }

    /**
     * 数据初始化
     */
    public void init(){
        iv_Back= (ImageView) findViewById(R.id.iv_Back_Price_Search);
    }

    /**
     * 时间监听
     */
    public void event(){
        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AcutionFailActivity.this.finish();
            }
        });

    }
}
