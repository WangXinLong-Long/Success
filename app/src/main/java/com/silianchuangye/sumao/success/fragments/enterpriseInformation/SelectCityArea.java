package com.silianchuangye.sumao.success.fragments.enterpriseInformation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.SelectCityAreaAdapter;
import com.silianchuangye.sumao.success.adapter.SelectProvinceAreaAdapter;
import com.silianchuangye.sumao.success.model.CityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class SelectCityArea  extends Activity implements View.OnClickListener{
    ListView listView;
    SelectCityAreaAdapter adapter;
    List<CityModel> lists;
    CityModel city;
    Intent intent;
    String province;
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
        tv_child_title_bar_title.setText("选择市");
        listView = ((ListView) findViewById(R.id.listview));
        lists = new ArrayList<>();
        intent = getIntent();
        province= intent.getStringExtra("province");
        for (int i = 0; i < 10; i++) {
            city = new CityModel();
            city.setCity("地区"+i+"市");
            lists.add(city);
        }
        className = getIntent().getStringExtra("className");
        adapter = new SelectCityAreaAdapter(this,lists);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(SelectCityArea.this,SelectCountyArea.class);
                intent.putExtra("city",province+lists.get(position).getCity());
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
