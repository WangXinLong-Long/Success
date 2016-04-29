package com.silianchuangye.sumao.success.fragments;

import android.content.Context;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

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
                Toast.makeText(mActivity, "你单击了："
                        +sela.getChild(groupPosition, childPosition), Toast.LENGTH_LONG).show();
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
