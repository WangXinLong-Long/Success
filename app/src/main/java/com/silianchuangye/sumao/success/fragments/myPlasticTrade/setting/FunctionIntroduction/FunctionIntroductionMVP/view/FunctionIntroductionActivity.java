package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.FunctionIntroduction.FunctionIntroductionMVP.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.CustomExpandableListView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.FunctionIntroduction.FunctionIntroductionMVP.adapter.FunctionIntroductionAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.FunctionIntroduction.FunctionIntroductionMVP.bean.FunctionIntroductionDetailBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.FunctionIntroduction.FunctionIntroductionMVP.presenter.FunctionIntroductionPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.FunctionIntroductionDetailActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.SettingMVP.bean.FunctionIntroductionBean;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FunctionIntroductionActivity extends AppCompatActivity implements IFunctionIntroductionView {

    private CustomExpandableListView expandable_listView_function_introduction;
    private FunctionIntroductionBean functionIntroductionBean;
    private TextView tv_child_title_bar_title;
    private FunctionIntroductionPresenter functionIntroductionPresenter;
    private List<List<String>> helpIds;
    private FunctionIntroductionAdapter functionIntroductionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_introduction);
        functionIntroductionPresenter = new FunctionIntroductionPresenter(this);
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("功能介绍");
        functionIntroductionBean = (FunctionIntroductionBean)getIntent().getSerializableExtra("FunctionIntroductionBean");
        LogUtils.log("functionIntroductionBean-->"+functionIntroductionBean);
//        遍历，获取StringList
//        functionIntroductionBean.getResult();
        List<List<String>> list = new ArrayList<>();
        helpIds = new ArrayList<>();
        for (int i = 0; i < functionIntroductionBean.getResult().size(); i++) {
            List<String> childList = new ArrayList<>();
            List<String> helpId = new ArrayList<>();
            for (int j = 0; j < functionIntroductionBean.getResult().get(i).getInformation().size(); j++) {
                childList.add(functionIntroductionBean.getResult().get(i).getInformation().get(j).getHelptheme());
                helpId.add(functionIntroductionBean.getResult().get(i).getInformation().get(j).getHelpid());
            }
            list.add(childList);
            helpIds.add(helpId);
        }
        List<String> groupList = new ArrayList<>();
        for (int i = 0; i < functionIntroductionBean.getResult().size(); i++) {
            groupList.add(functionIntroductionBean.getResult().get(i).getHelpsort());
        }

        expandable_listView_function_introduction = ((CustomExpandableListView) findViewById(R.id.expandable_listView_function_introduction));
        functionIntroductionAdapter = new FunctionIntroductionAdapter(list,groupList,this);
        expandable_listView_function_introduction.setAdapter(functionIntroductionAdapter);
        expandable_listView_function_introduction.setGroupIndicator(null);
        expandable_listView_function_introduction.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });
        expandable_listView_function_introduction.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                LogUtils.log("groupPosition-->"+groupPosition+"<--childPosition-->"+childPosition+"<--helpId-->"+helpIds.get(groupPosition).get(childPosition));
                functionIntroductionPresenter.getFunctionIntroductionInfo(helpIds.get(groupPosition).get(childPosition));
                return true;
            }
        });

    }

    @Override
    public void sendFunctionIntroductionDetailInActivity(FunctionIntroductionDetailBean functionIntroductionDetailBean) {
        Intent intent = new Intent(FunctionIntroductionActivity.this,FunctionIntroductionDetailActivity.class);
        intent.putExtra("functionIntroductionDetailBean",functionIntroductionDetailBean);
        LogUtils.log("FunctionIntroductionActivity-->functionIntroductionDetailBean-->"+functionIntroductionDetailBean);
        startActivity(intent);
    }
}
