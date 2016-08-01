package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.SelectProvinceAreaAdapter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.bean.Area;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.presenter.SelectProvinceAreaPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.view.ISelectProvinceAreaView;
import com.silianchuangye.sumao.success.model.ProvinceModel;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class SelectProvinceArea extends Activity implements View.OnClickListener,ISelectProvinceAreaView {
    ListView listview;
    SelectProvinceAreaAdapter adapter;
    List<Area> lists;
    Area province;
    ImageView iv_child_title_bar_back;
    TextView tv_child_title_bar_title;
    String className;
    private String name;
    SelectProvinceAreaPresenter presenter;

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
            province = new Area();
            province.setName("地区"+i+"省");
            lists.add(province);
        }

        presenter = new SelectProvinceAreaPresenter(this);
        presenter.putResultInView();
         className = getIntent().getStringExtra("className");
        iv_child_title_bar_back.setOnClickListener(this);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.JumpToCity(position);
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


    @Override
    public void initProvinceAreaView(List<Area> provList) {
        adapter = new SelectProvinceAreaAdapter(this,provList);
        this.lists = provList;
        listview.setAdapter(adapter);
    }

    @Override
    public void onListItemClick(int position) {
        Intent intent = new Intent();
        intent.putExtra("sheng",lists.get(position).getName().toString());
        setResult(0,intent);
        SelectProvinceArea.this.finish();

    }
}
