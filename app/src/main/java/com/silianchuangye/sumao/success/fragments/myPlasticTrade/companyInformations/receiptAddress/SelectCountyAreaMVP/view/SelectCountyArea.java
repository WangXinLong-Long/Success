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
import com.silianchuangye.sumao.success.adapter.SelectCountyAreaAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectCountyAreaMVP.model.ISelectCountyAreaModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectCountyAreaMVP.presenter.SelectCountyAreaPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectDetailArea;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.bean.Area;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.view.ISelectProvinceAreaView;
import com.silianchuangye.sumao.success.model.CountyModel;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class SelectCountyArea extends Activity implements View.OnClickListener,ISelectCountyAreaView{
    ListView listView;
    SelectCountyAreaAdapter adapter;
    List<Area> lists;
    Intent intent;
    String city;
    ImageView iv_child_title_bar_back;
    TextView tv_child_title_bar_title;
    String className;
    SelectCountyAreaPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_area);
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        iv_child_title_bar_back.setOnClickListener(this);
        tv_child_title_bar_title.setText("选择县/区/旗");
        listView = ((ListView) findViewById(R.id.listview));
        city = getIntent().getStringExtra("city");
        LogUtils.log("city------>"+city);
        lists = new ArrayList<>();
        presenter = new SelectCountyAreaPresenter(this);
        presenter.getCountyInfo(city);
        intent = getIntent();
        className = intent.getStringExtra("className");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.jumpVillage(position);
                Intent intent = new Intent();
                intent.putExtra("xianqu",lists.get(position).getCounty().toString());
                Log.d("县",lists.get(position).getCounty().toString());
                setResult(2,intent);
                SelectCountyArea.this.finish();

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

    @Override
    public void initCountyView(List<Area> areas) {
        adapter = new SelectCountyAreaAdapter(this,areas);
        lists = areas;
        listView.setAdapter(adapter);
    }

    @Override
    public void jumpVillageActivity(int position) {
        Intent intent = new Intent();
        intent.setClass(SelectCountyArea.this,SelectDetailArea.class);
        intent.putExtra("county",city+lists.get(position).getLevel());
        intent.putExtra("className",className);
        startActivity(intent);
    }
}
