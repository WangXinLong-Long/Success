package com.silianchuangye.sumao.success.fragments.logistics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.silianchuangye.sumao.success.R;

public class CreateLogistics extends AppCompatActivity implements View.OnClickListener{
private ExpandableListView expand_lv_create_logistics;
    private ImageView img_create_logistics_back;
    private ImageView img_create_logistics_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_logistics);
        initView();
    }

    private void initView() {
        img_create_logistics_back= (ImageView) findViewById(R.id.img_create_logistics_back);
        img_create_logistics_search= (ImageView) findViewById(R.id.img_create_logistics_search);
        expand_lv_create_logistics= (ExpandableListView) findViewById(R.id.create_logistics_expand_lv);
        img_create_logistics_back.setOnClickListener(this);
        img_create_logistics_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_create_logistics_back:
                finish();
                break;
            case R.id.img_create_logistics_search:
                finish();
                break;
        }
    }
}
