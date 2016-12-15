package com.silianchuangye.sumao.success.fragments.homepage.PagerOneGridViewDetailActivityMVP;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.silianchuangye.sumao.success.R;

/**
 * Created by Jobs Created on 2016/12/15.
 */
public class PagerOneGridViewDetailActivity extends Activity implements View.OnClickListener{
    private RelativeLayout tradingPatterns;
    private RelativeLayout region;
    private RelativeLayout classification;
    private RelativeLayout application;
    private RelativeLayout grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_one_grid_view_detail_activity);

        tradingPatterns = ((RelativeLayout) findViewById(R.id.trading_patterns_rl));
        tradingPatterns.setOnClickListener(this);
        region = ((RelativeLayout) findViewById(R.id.region_rl));
        region.setOnClickListener(this);
        classification = ((RelativeLayout) findViewById(R.id.classification_rl));
        classification.setOnClickListener(this);
        application = ((RelativeLayout) findViewById(R.id.application_rl));
        application.setOnClickListener(this);
        grade = ((RelativeLayout) findViewById(R.id.grade_rl));
        grade.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.trading_patterns_rl:

                break;
            case R.id.region_rl:

                break;
             case R.id.classification_rl:

                break;
             case R.id.application_rl:

                break;
             case R.id.grade_rl:

                break;

        }
    }
}
