package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.NewsFragmentMVP.view;

import android.graphics.Color;
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
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.NewsFragmentMVP.adapter.ListViewDataAdapter;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.NewsFragmentMVP.bean.NewsFragmentBean;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.NewsFragmentMVP.presenter.NewsFragmentPresenter;

import java.util.List;


/**
 * Created by Administrator on 2016/12/13.
 */

public class NewsFragment extends Fragment implements INewsFragmentView{

    private View view;
    private NewsFragmentPresenter newsFragmentPresenter;
    private ListView listViewData;
    private ListViewDataAdapter listViewAdapter;
    private List<NewsFragmentBean.InBean> in;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.data_fragment,container,false);

        newsFragmentPresenter = new NewsFragmentPresenter(this);
//        列表内容
        listViewData = ((ListView) view.findViewById(R.id.list_view_data));
        listViewData.setOnItemClickListener(new MyAdapterItemClickListener());

        listViewAdapter = new ListViewDataAdapter(getActivity());
        listViewData.setAdapter(listViewAdapter);

//        加载数据
        initData();

        return view;
    }



    private void initData() {
        String dateType = null;
        newsFragmentPresenter.getNewsFragmentResult();

    }

    //    从网络加载数据
    @Override
    public void setNewsFragmentResult(String result) {
        Gson gson = new Gson();
        NewsFragmentBean newsFragmentBean = gson.fromJson(result, NewsFragmentBean.class);
        in = newsFragmentBean.getIn();
        listViewAdapter.clearData();
        listViewAdapter.addPagerTwoDataListViewBeens(in);
        listViewAdapter.notifyDataSetChanged();
    }

    class MyAdapterItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (parent.getId()) {
                case R.id.list_view_data:
                    Toast.makeText(getActivity(), "List--->" + in.get(position).getIn_xwid(), Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }


    public void searchData(int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(getActivity(), "在新闻页面中操作", Toast.LENGTH_SHORT).show();
        view.setBackgroundColor(Color.RED);
    }

}
