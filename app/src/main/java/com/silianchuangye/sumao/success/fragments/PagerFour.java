package com.silianchuangye.sumao.success.fragments;

import android.view.View;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

import com.silianchuangye.sumao.success.R;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public class PagerFour extends BasePager {
    @Override
    public void myClickSearch() {

    }

    @Override
    public void initDate() {
        fl_content.setBackgroundColor(getResources().getColor(R.color.blue));
        TextView textView = new TextView(mActivity);
        textView.setText("第四页");
        fl_content.addView(textView);
        title.setText("第四页");
        System.out.println("显示消息界面");
        logo.setVisibility(View.INVISIBLE);
    }

    @Override
    public void myClickLeft() {

    }

    @Override
    public void myClickRight() {

    }
}
