package com.silianchuangye.sumao.success.fragments;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public class PagerTwo extends BasePager {
     private RelativeLayout view;


    @Override
    public void myClickSearch() {

    }

    @Override
    public void initDate() {
      view= (RelativeLayout) View.inflate(mActivity,R.layout.fragmenttwo,null);
      rl_title.setVisibility(View.GONE);
        fl_content.addView(view);




    }

    @Override
    public void myClickLeft() {

    }

    @Override
    public void myClickRight() {
        Toast.makeText(mActivity,"haha",Toast.LENGTH_SHORT).show();
    }
}
