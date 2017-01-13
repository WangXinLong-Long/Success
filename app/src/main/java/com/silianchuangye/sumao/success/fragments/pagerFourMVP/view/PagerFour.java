package com.silianchuangye.sumao.success.fragments.pagerFourMVP.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.custom.CustomExpandableListView;
import com.silianchuangye.sumao.success.fragments.BasePager;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.adapter.PagerFourAdapter;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.bean.AboutSuMaoBean;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.pagerFourDetail.PagerFourDetailActivity;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.pagerFourDetail.bean.PagerFourDetailBean;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.presenter.PagerFourPresenter;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/6.
 */
public class PagerFour extends BasePager implements IPagerFourView {
    private View pagerFourView;
    private CustomExpandableListView expandableListView;
    private Context context;
    private PagerFourAdapter pagerFourAdapter;
    private List<List<AboutSuMaoBean.NewsItemBean>> helpIds = new ArrayList<>();
    private PagerFourPresenter pagerFourPresenter;
    private List<String> groupList;

    @Override
    public void myClickSearch() {
    }

    @Override
    public void initDate() {
        rl_title.setVisibility(View.GONE);
        pagerFourView = View.inflate(mActivity, R.layout.pager_four, null);
        fl_content.addView(pagerFourView);
        context = mActivity;

        expandableListView = (CustomExpandableListView) pagerFourView.findViewById(R.id.expandable_listView);
        pagerFourPresenter = new PagerFourPresenter(this);
        pagerFourPresenter.getPagerFourAboutSuMaoResult();

        groupList = new ArrayList<>();
        groupList.add("塑贸指南");
        groupList.add("客服帮助");

        pagerFourAdapter = new PagerFourAdapter(context);
        expandableListView.setAdapter(pagerFourAdapter);

//        设置箭头图标消失
        expandableListView.setGroupIndicator(null);

//        默认列表展开
        for (int i = 0; i < pagerFourAdapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String newsId = null;
                if (groupPosition == 0){
                    helpIds.get(0).get(childPosition);
                }else {
                    helpIds.get(1).get(childPosition);
                }
                pagerFourPresenter.getPagerFourDetailResult(newsId);
                return true;
            }
        });
    }

    //    得到页面中所有关于塑贸的数据
    @Override
    public void setPagerFourAboutSuMaoResult(String result) {
        Gson gson = new Gson();
        AboutSuMaoBean aboutSuMaoBean = gson.fromJson(result, AboutSuMaoBean.class);
        helpIds.add(aboutSuMaoBean.getNewsItem());
        LogUtils.log("关于塑贸的数据--->"+aboutSuMaoBean.getNewsItem().get(0).getCaption());

        pagerFourPresenter.getPagerFourCustomerServiceGuideResult();
    }

    //    得到页面中所有客服指南的数据
    @Override
    public void setPagerFourCustomerServiceGuideResult(String result) {
        Gson gson = new Gson();
        AboutSuMaoBean customerServiceGuideSuMaoBean = gson.fromJson(result, AboutSuMaoBean.class);
        helpIds.add(customerServiceGuideSuMaoBean.getNewsItem());
        LogUtils.log("客服指南的数据--->"+customerServiceGuideSuMaoBean.getNewsItem().get(0).getCaption());
        pagerFourAdapter.addGroupData(groupList);
        pagerFourAdapter.addChildData(helpIds);
        pagerFourAdapter.notifyDataSetChanged();
    }

//    获取详情页的内容
    @Override
    public void setPagerFourDetailResult( PagerFourDetailBean pagerFourDetailBean) {
        Intent intent = new Intent(mActivity,PagerFourDetailActivity.class);
        intent.putExtra("pagerFourDetailBean",pagerFourDetailBean);
        intent.putExtra("haha","haha");
        startActivity(intent);
    }

    @Override
    public void myClickLeft() {
    }

    @Override
    public void myClickRight() {
    }


}
