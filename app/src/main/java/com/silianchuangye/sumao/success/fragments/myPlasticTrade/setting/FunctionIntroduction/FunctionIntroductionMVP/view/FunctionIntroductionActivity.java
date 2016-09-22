package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.FunctionIntroduction.FunctionIntroductionMVP.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.CustomExpandableListView;

public class FunctionIntroductionActivity extends AppCompatActivity {

    private CustomExpandableListView expandable_listView_function_introduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_introduction);
        expandable_listView_function_introduction = ((CustomExpandableListView) findViewById(R.id.expandable_listView_function_introduction));
    }
}
