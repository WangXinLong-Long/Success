package com.silianchuangye.sumao.success.fragments;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public class PagerOne extends BasePager {
    @Override
    public void initDate() {
        TextView textView = new TextView(mActivity);
        textView.setText("消息");
        fl_content.addView(textView);
        title.setText("消息");
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
