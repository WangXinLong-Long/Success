package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.FunctionIntroduction.FunctionIntroductionMVP.bean.FunctionIntroductionDetailBean;
import com.silianchuangye.sumao.success.utils.LogUtils;

public class FunctionIntroductionDetailActivity extends AppCompatActivity {

    private FunctionIntroductionDetailBean functionIntroductionDetailBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_introduction_detail);
        functionIntroductionDetailBean = ((FunctionIntroductionDetailBean) getIntent().getSerializableExtra("functionIntroductionDetailBean"));

        LogUtils.log(functionIntroductionDetailBean+"");
        LogUtils.log(functionIntroductionDetailBean.getHelpdate());
    }
}
