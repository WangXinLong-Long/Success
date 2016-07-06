package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.List;

public class FrimInfoProvinceActivity extends AppCompatActivity {
    private ListView lv_firm_info_province;
    private ArrayAdapter<String> adapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frim_info_province);

    }
}
