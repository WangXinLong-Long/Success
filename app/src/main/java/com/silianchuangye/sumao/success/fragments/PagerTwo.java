package com.silianchuangye.sumao.success.fragments;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import com.silianchuangye.sumao.success.R;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public class PagerTwo extends BasePager {
    LinearLayout linearLayout;
    @Override
    public void initDate() {
//        fl_content.setBackgroundColor(getResources().getColor(R.color.red));
        linearLayout = (LinearLayout) View.inflate(mActivity,R.layout.test_fragment,null);
        fl_content.addView(linearLayout);


    }

    @Override
    public void myClickLeft() {

    }

    @Override
    public void myClickRight() {

    }
}
