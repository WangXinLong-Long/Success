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
import com.silianchuangye.sumao.success.adapter.SelectCityAreaAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectCityAreaMVP.presenter.SelecteCityAreaPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectCountyAreaMVP.view.SelectCountyArea;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.bean.Area;
import com.silianchuangye.sumao.success.model.CityModel;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class SelectCityArea  extends Activity implements View.OnClickListener{
    ListView listView;
    SelectCityAreaAdapter adapter;
    List<Area> lists;
    Intent intent;
    String province;
    ImageView iv_child_title_bar_back;
    TextView tv_child_title_bar_title;
    String className;
    SelecteCityAreaPresenter presenter;

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
//        省的level
        province = intent.getStringExtra("province");
        LogUtils.log("SelectCityArea:province----->" + province);
        presenter = new SelecteCityAreaPresenter(this);
        presenter.getCityInfo(province);
        className = getIntent().getStringExtra("className");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.jumpCounty(position);
                Intent intent = new Intent();
                intent.putExtra("city",lists.get(position).getCity().toString());
                Log.d("市",lists.get(position).getCity().toString());
                SelectCityArea.this.setResult(1, intent);
                SelectCityArea.this.finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_child_title_bar_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void initCityView(List<Area> areas) {
        adapter = new SelectCityAreaAdapter(this, areas);
        lists = areas;
        listView.setAdapter(adapter);
    }

    @Override
    public void jumpCountryActivity(int position) {
        Intent intent = new Intent();
        intent.setClass(SelectCityArea.this, SelectCountyArea.class);
        intent.putExtra("city", lists.get(position).getLevel());
        intent.putExtra("className", className);
        startActivity(intent);
    }
}
