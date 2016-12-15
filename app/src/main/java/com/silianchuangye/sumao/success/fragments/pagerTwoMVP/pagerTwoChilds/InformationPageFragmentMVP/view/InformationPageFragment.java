package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.adapter.PagerTwoInformationPageListViewAdapter;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.view.PagerTwoInformationPagePullListener;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.bean.PagerTwoListViewBean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2016/12/13.
 */

public class InformationPageFragment extends Fragment {

    private ListView listView;
    private PullToRefreshLayout ptrl;
    private String[] imgSites = {
            "http://image.baidu.com/",
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.information_pagefragment,container,false);
        ptrl = ((PullToRefreshLayout) view.findViewById(R.id.refresh_view));
        // 此处设置下拉刷新或上拉加载更多监听器
        ptrl.setOnPullListener(new PagerTwoInformationPagePullListener());
        listView = (ListView)ptrl.getPullableView();
        initListView();

        return view;
    }


        // 第一次进入自动刷新
//        if (isFirstIn){ptrl.autoRefresh();isFirstIn = false;}

    /**
     * ListView初始化方法
     */
    private void initListView(){
        PagerTwoInformationPageListViewAdapter adapter = new PagerTwoInformationPageListViewAdapter(getActivity());
        listView.setAdapter(adapter);
        for (String urls : imgSites) {
            loadImgList(urls,adapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                Toast.makeText(getActivity(),
                        " Click on " + parent.getAdapter().getItemId(position),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadImgList(final String url, final PagerTwoInformationPageListViewAdapter imageListAdapter) {
        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<String> imgSrcList = getImgSrcList(result);
                List<PagerTwoListViewBean> items = new ArrayList<>();
                for (int i = 0; i < imgSrcList.size(); i++) {
                    PagerTwoListViewBean bean = new PagerTwoListViewBean();
                    bean.setCount(""+i);
                    bean.setFrom("China"+i);
                    bean.setIcon(imgSrcList.get(i));
                    bean.setTime("12:00:"+i);
                    bean.setTitle("科罗娜打卡打卡卡拉胶大阿达大大大阿达打发打发达到发达打发打发打发打发打发打发打发打发打发"+i);
                    items.add(bean);
                }

                imageListAdapter.addSrc(items);
                imageListAdapter.notifyDataSetChanged();//通知listview更新数据
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 得到网页中图片的地址
     */
    public static List<String> getImgSrcList(String htmlStr) {
        List<String> pics = new ArrayList<String>();

        String regEx_img = "<img.*?src=\"http://(.*?).jpg\""; // 图片链接地址
        Pattern p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        Matcher m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            String src = m_image.group(1);
            if (src.length() < 100) {
                pics.add("http://" + src + ".jpg");
                //pics.add("http://f.hiphotos.baidu.com/zhidao/pic/item/2fdda3cc7cd98d104cc21595203fb80e7bec907b.jpg");
            }
        }
        return pics;
    }
}
