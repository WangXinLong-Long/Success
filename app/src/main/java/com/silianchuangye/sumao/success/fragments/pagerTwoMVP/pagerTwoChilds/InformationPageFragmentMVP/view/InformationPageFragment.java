package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.PagerTwoView.NewsActivity;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.adapter.PagerTwoInformationPageListViewAdapter;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.bean.PagerTwoInformationPageBean;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.presenter.InformationPageFragmentPresenter;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.informationDetailsMVP.InformationDetailsActivity;
import com.silianchuangye.sumao.success.utils.NetWorkChangeBroadcastReceiver;

import java.text.SimpleDateFormat;
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
    private BroadcastReceiver netReceiver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initReceiver();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.information_pagefragment, container, false);
        ptrl = ((PullToRefreshLayout) view.findViewById(R.id.refresh_view));

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
//                Intent intent = new Intent(getActivity(), InformationDetailsActivity.class);
//                startActivity(intent);intent
                Intent intent=new Intent(getActivity(), NewsActivity.class);
                intent.putExtra("id",in.get(position).getNewsid());
                intent.putExtra("title",in.get(position).getNewsname());
                //intent.putExtra("time",in.get(position).getNewstime());
                intent.putExtra("time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Double.parseDouble(in.get(position).getNewstime())));
                intent.putExtra("number",in.get(position).getNewsnum());
                intent.putExtra("from",in.get(position).getNewsfrom());
                intent.putExtra("dianzan",in.get(position).getThumupCount());
                startActivity(intent);

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
       // onCreate(null);
        informationPageFragmentPresenter = new InformationPageFragmentPresenter(this);
        // 此处设置下拉刷新或上拉加载更多监听器
        ptrl.setOnPullListener(new PagerTwoInformationPagePullListener());
        listView = (ListView) ptrl.getPullableView();
        initListView();

    }
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

    @Override
    public void setInformationPageFragmentData(String result) {
        Gson gson = new Gson();
        PagerTwoInformationPageBean informationPageBean = gson.fromJson(result, PagerTwoInformationPageBean.class);
        in = informationPageBean.getIn();
        adapter.addSrc(in);
        adapter.notifyDataSetChanged();
    }
    //      监听广播
    private void initReceiver() {
        netReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //                判断当前网络状态
                if(intent.getIntExtra(NetWorkChangeBroadcastReceiver.NET_TYPE,0)==0){
                    //                    0表示无网络连接,
                    //这里填你想要执行的东西
                    Toast.makeText(context, "暂时没有网络,请检查您的网络设置", Toast.LENGTH_SHORT).show();
                }else {
                    //                    1表示有
                    informationPageFragmentPresenter.getInformationPageFragmentData();
                }
            }
        };
        IntentFilter filter = new IntentFilter(NetWorkChangeBroadcastReceiver.NET_CHANGE);
        getActivity().registerReceiver(netReceiver,filter);
    }
}
