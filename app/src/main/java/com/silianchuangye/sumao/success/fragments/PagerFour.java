package com.silianchuangye.sumao.success.fragments;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.OrderGoodsActivity;
import com.silianchuangye.sumao.success.R;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.ExpandableListViewAdapter;
import com.silianchuangye.sumao.success.custom.CustomExpandableListView;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public class PagerFour extends BasePager {
    private CustomExpandableListView expandableListView;
    private ExpandableListAdapter sela;
    private Context context;
    View view;
    @Override
    public void myClickSearch() {

    }

    @Override
    public void initDate() {
        rl_title.setVisibility(View.GONE);
        view = View.inflate(mActivity,R.layout.fragment_four,null);
        fl_content.addView(view);

        context =mActivity;
        expandableListView = (CustomExpandableListView) view.findViewById(R.id.expandable_listView);
        sela = new ExpandableListViewAdapter(context);
        expandableListView.setAdapter(sela);
        expandableListView.setGroupIndicator(null);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                return false;
            }
        });
        //为ExpandableListView的子列表单击事件设置监听器
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                if (sela.getChild(groupPosition, childPosition).toString().equals("现货订单"))
                {
                    Intent intent = new Intent();
                    intent.setClass(context,OrderGoodsActivity.class);
                    startActivity(intent);
                }

                return true;
            }
        });
    }

    @Override
    public void myClickLeft() {

    }

    @Override
    public void myClickRight() {

    }
}
