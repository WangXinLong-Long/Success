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
import com.silianchuangye.sumao.success.adapter.SelectCountyAreaAdapter;
import com.silianchuangye.sumao.success.model.CountyModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class SelectCountyArea extends Activity implements View.OnClickListener{
    ListView listView;
    SelectCountyAreaAdapter adapter;
    List<CountyModel> lists;
    CountyModel county;
    Intent intent;
    String city;
    ImageView iv_child_title_bar_back;
    TextView tv_child_title_bar_title;
    String className;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_area);
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        iv_child_title_bar_back.setOnClickListener(this);
        tv_child_title_bar_title.setText("选择县/区/旗");
        listView = ((ListView) findViewById(R.id.listview));
        lists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            county = new CountyModel();
            county.setCounty("地区"+i+"县");
            lists.add(county);

        }
        intent = getIntent();
        city = intent.getStringExtra("city");
        className = intent.getStringExtra("className");
        adapter = new SelectCountyAreaAdapter(this,lists);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(SelectCountyArea.this,SelectDetailArea.class);
                intent.putExtra("county",city+lists.get(position).getCounty());
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
            default:
                break;
        }
    }
}
