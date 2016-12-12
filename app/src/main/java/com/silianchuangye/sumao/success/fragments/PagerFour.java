package com.silianchuangye.sumao.success.fragments;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ExpandableListView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.ExpandableListViewAdapter;
import com.silianchuangye.sumao.success.custom.CustomExpandableListView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.FunctionIntroduction.FunctionIntroductionMVP.adapter.FunctionIntroductionAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/6.
 */
public class PagerFour extends BasePager {
    private View pagerFourView;
    private CustomExpandableListView expandableListView;
    private Context context;
    private FunctionIntroductionAdapter functionIntroductionAdapter;
    private List<List<String>> helpIds;
    @Override
    public void myClickSearch() {
    }

    @Override
    public void initDate() {
        rl_title.setVisibility(View.GONE);
        pagerFourView = View.inflate(mActivity, R.layout.pager_four,null);
        fl_content.addView(pagerFourView);
        context = mActivity;
        expandableListView = (CustomExpandableListView) pagerFourView.findViewById(R.id.expandable_listView);
        List<List<String>> list = new ArrayList<>();
        helpIds = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<String> childList = new ArrayList<>();
            List<String> helpId = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                childList.add("父的"+i+"子的"+j);
                helpId.add("父的"+i+"子的"+j);
            }
            list.add(childList);
            helpIds.add(helpId);
        }
        List<String> groupList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            groupList.add("父的"+i);
        }

        functionIntroductionAdapter = new FunctionIntroductionAdapter(list,groupList,context);
        expandableListView.setAdapter(functionIntroductionAdapter);
//        设置箭头图标消失
        expandableListView.setGroupIndicator(null);
//        默认列表展开
        for (int i = 0; i < functionIntroductionAdapter.getGroupCount(); i++) {

            expandableListView.expandGroup(i);

        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                return false;
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
