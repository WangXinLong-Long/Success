package com.silianchuangye.sumao.success.fragments.PagerOneMVP;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.pagerOneGridViewDetailActivity.PagerOneGridViewDetailActivity;

import java.util.List;

/**
 * Created by Jobs Created on 2016/12/15.
 */
public class MyAdapterItemClickListener implements android.widget.AdapterView.OnItemClickListener {
    Context context;
    List<?> lists;
    public MyAdapterItemClickListener(Activity context, List<?> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.area_detail:
                Toast.makeText(context,lists.get(position)+"",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(context,PagerOneGridViewDetailActivity.class);
                context.startActivity(intent);
                break;

            case R.id.classification_detail:
                Toast.makeText(context,lists.get(position)+"",Toast.LENGTH_SHORT).show();
                break;

            case R.id.application_detail:
                Toast.makeText(context,lists.get(position)+"",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}