package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.adapter.PagerTwoInformationPageListViewAdapter;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.bean.PagerTwoInformationPageBean;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.presenter.InformationPageFragmentPresenter;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.informationDetailsMVP.InformationDetailsActivity;

import java.util.List;


/**
 * Created by Administrator on 2016/12/13.
 */

public class InformationPageFragment extends Fragment implements IInformationPageFragmentView {

    private ListView listView;
    private PullToRefreshLayout ptrl;
    private InformationPageFragmentPresenter informationPageFragmentPresenter;
    private PagerTwoInformationPageListViewAdapter adapter;
    private List<PagerTwoInformationPageBean.InBean> in;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.information_pagefragment, container, false);
        ptrl = ((PullToRefreshLayout) view.findViewById(R.id.refresh_view));
        informationPageFragmentPresenter = new InformationPageFragmentPresenter(this);
        // 此处设置下拉刷新或上拉加载更多监听器
        ptrl.setOnPullListener(new PagerTwoInformationPagePullListener());
        listView = (ListView) ptrl.getPullableView();
        initListView();

        return view;
    }


    // 第一次进入自动刷新
//        if (isFirstIn){ptrl.autoRefresh();isFirstIn = false;}

    /**
     * ListView初始化方法
     */
    private void initListView() {

        adapter = new PagerTwoInformationPageListViewAdapter(getActivity());
        listView.setAdapter(adapter);
        informationPageFragmentPresenter.getInformationPageFragmentData();
//            loadImgList(urls,adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(),
                        " Click on " + in.get(position).getNewsid(),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), InformationDetailsActivity.class);
                startActivity(intent);

            }
        });
    }




    @Override
    public void setInformationPageFragmentData(String result) {
        Gson gson = new Gson();
        PagerTwoInformationPageBean informationPageBean = gson.fromJson(result, PagerTwoInformationPageBean.class);
        in = informationPageBean.getIn();
        adapter.addSrc(in);
        adapter.notifyDataSetChanged();
    }
}
