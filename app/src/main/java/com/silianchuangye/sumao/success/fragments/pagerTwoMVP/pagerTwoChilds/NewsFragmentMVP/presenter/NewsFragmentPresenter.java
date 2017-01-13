package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.NewsFragmentMVP.presenter;

import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.NewsFragmentMVP.model.INewsFragmentModel;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.NewsFragmentMVP.model.NewsFragmentCallback;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.NewsFragmentMVP.model.NewsFragmentModel;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.NewsFragmentMVP.view.INewsFragmentView;

/**
 * Created by Jobs Created on 2016/12/27.
 */
public class NewsFragmentPresenter {
    INewsFragmentView newsFragmentView;

    public NewsFragmentPresenter(INewsFragmentView newsFragmentView) {
        this.newsFragmentView = newsFragmentView;
    }

//    获取新闻页面中内容
    public  void getNewsFragmentResult(){
        INewsFragmentModel newsFragmentModel = new NewsFragmentModel();
        newsFragmentModel.getNewsFragmentResult(new NewsFragmentCallback() {
            @Override
            public void callbackNewsFragmentResult(String result) {
                newsFragmentView.setNewsFragmentResult(result);
            }
        });
    }
}
