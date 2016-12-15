package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.view;

import android.os.Handler;
import android.os.Message;

import com.jingchen.pulltorefresh.PullToRefreshLayout;

/**
     * 下拉刷新与上拉加载更多监听器
     */
    public class PagerTwoInformationPagePullListener implements PullToRefreshLayout.OnPullListener {
        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
                    // 千万别忘了告诉控件刷新完毕了哦！
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            // 加载更多操作
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    // 千万别忘了告诉控件加载完毕了哦！
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 5000);
        }
    }