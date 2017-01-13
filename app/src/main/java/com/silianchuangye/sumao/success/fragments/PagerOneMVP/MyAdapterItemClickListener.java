package com.silianchuangye.sumao.success.fragments.PagerOneMVP;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.presenter.PagerOnePresenter;
import com.silianchuangye.sumao.success.utils.Loding;

import java.util.List;

/**
 * Created by Jobs Created on 2016/12/15.
 */
public class MyAdapterItemClickListener implements android.widget.AdapterView.OnItemClickListener {
    Context context;
    List<?> lists;
    PagerOnePresenter pagerOnePresenter;
    String pattern = null;//交易模式
    String regions = null;//    大区
    String provinces = null;//省
    String cities = null;//市
    String categoryNames = null;//分类
    String appNames = null;//应用
    String gradeNums = null;//牌号
    String groups = null;//牌号
    String pageNum = "1";
    String updateOrLoad = "update";
    public MyAdapterItemClickListener(Activity context, List<?> lists, PagerOnePresenter pagerOnePresenter) {
        this.context = context;
        this.lists = lists;
        this.pagerOnePresenter = pagerOnePresenter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.area_detail:
                Toast.makeText(context,lists.get(position)+"",Toast.LENGTH_SHORT).show();
                regions = lists.get(position)+"";
                break;

            case R.id.classification_detail:
                Toast.makeText(context,lists.get(position)+"",Toast.LENGTH_SHORT).show();
                categoryNames = lists.get(position)+"";
                break;

            case R.id.application_detail:
                Toast.makeText(context,lists.get(position)+"",Toast.LENGTH_SHORT).show();
                appNames = lists.get(position)+"";
                break;
            case R.id.supplier_detail:
                groups = lists.get(position)+"";
                break;
        }
        Loding.show(context,"加载中...",false,null);
        pagerOnePresenter.getScreeningConditions(groups,pattern,regions,provinces,cities,categoryNames,appNames,gradeNums,pageNum,updateOrLoad);
    }
}