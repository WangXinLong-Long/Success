package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.adapter.ListViewDataAdapter;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.bean.PagerTwoDataListViewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */

public class DataFragment extends Fragment {
    private ListView listViewData;
    private ListViewDataAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_fragment,container,false);
        listViewData = ((ListView) view.findViewById(R.id.list_view_data));
        adapter = new ListViewDataAdapter(getActivity());
        listViewData.setAdapter(adapter);
//        设置参数
        initData();
//
        return view;
    }

    private void initData() {
        List<PagerTwoDataListViewBean> pagerTwoDataListViewBeens = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PagerTwoDataListViewBean pagerTwoDataListViewBean = new PagerTwoDataListViewBean();
            pagerTwoDataListViewBean.setTime("19:0"+i);
            pagerTwoDataListViewBean.setTitle("19:0"+i+"的主要内容是:.....");
            pagerTwoDataListViewBeens.add(pagerTwoDataListViewBean);
        }
        adapter.setPagerTwoDataListViewBeens(pagerTwoDataListViewBeens);
    }

    public void searchData(int year,int monthOfYear,int dayOfMonth){
        Toast.makeText(getActivity(), "" + year + "-" + monthOfYear + "-" + dayOfMonth + "在数据中搜索", Toast.LENGTH_SHORT).show();
    }
}
