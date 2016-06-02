package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.SelectProvinceAreaAdapter;
import com.silianchuangye.sumao.success.model.ProvinceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class SelectProvinceArea extends Activity implements View.OnClickListener{
    ListView listview;
    SelectProvinceAreaAdapter adapter;
    List<ProvinceModel> lists;
    ProvinceModel province;
    ImageView iv_child_title_bar_back;
    TextView tv_child_title_bar_title;
    String className;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_area);
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("选择省/自治区");
        listview = ((ListView) findViewById(R.id.listview));
        lists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
           province = new ProvinceModel();
            province.setProvince("地区"+i+"省");
            lists.add(province);
        }
         className = getIntent().getStringExtra("className");
        iv_child_title_bar_back.setOnClickListener(this);
        adapter = new SelectProvinceAreaAdapter(this,lists);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(SelectProvinceArea.this,SelectCityArea.class);
                intent.putExtra("province",lists.get(position).getProvince());
                intent.putExtra("className",className);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_child_title_bar_back:
                finish();
                break;

        }
    }


}
