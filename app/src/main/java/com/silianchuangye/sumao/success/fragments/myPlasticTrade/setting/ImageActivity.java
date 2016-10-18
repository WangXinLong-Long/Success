/*
 * Copyright (c) 2016.
 * 创建人: 小辛
 * 创建时间: 16-4-18 下午9:12.
 * 个人博客: http://blog.csdn.net/xiaoxin_android
 * QQ 交流群: 190088567
 */

package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.silianchuangye.sumao.success.R;

/**
 * Created by xinxin on 16/4/18.
 */
public class ImageActivity extends Activity {
    private ImageView show_img;
    public static final String BASE_PATH = "http://42.202.144.112:8080/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_image);
        show_img = (ImageView) findViewById(R.id.show_img);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.i("TAG", BASE_PATH + getIntent().getStringExtra("image"));
        Glide.with(ImageActivity.this).load(getIntent().getStringExtra("image"))
                .into(show_img);
    }
}
