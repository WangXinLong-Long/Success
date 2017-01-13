package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.adapter.ListViewDataAdapter;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.adapter.PagerTwoTextGrideViewAdapter;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.bean.PagerTwoDataGridViewBean;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.bean.PagerTwoDataListViewBean;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.presenter.DataFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */

public class DataFragment extends Fragment implements IDataFragmentView {
    private ListView listViewData;
    private ListViewDataAdapter listViewAdapter;
    private DataFragmentPresenter dataFragmentPresenter;
    private List<PagerTwoDataListViewBean.InBean> in;
    private List<PagerTwoDataGridViewBean> gridViewBeanList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_fragment, container, false);

//        5个选择项
        GridView dateType = (GridView) view.findViewById(R.id.dateType);
        dateType.setOnItemClickListener(new MyAdapterItemClickListener());
        initGrideViewData();
        PagerTwoTextGrideViewAdapter gridViewAdapter = new PagerTwoTextGrideViewAdapter(getActivity());
        dateType.setAdapter(gridViewAdapter);
        gridViewAdapter.addData(gridViewBeanList);

//        列表内容
        listViewData = ((ListView) view.findViewById(R.id.list_view_data));
        listViewData.setOnItemClickListener(new MyAdapterItemClickListener());
        dataFragmentPresenter = new DataFragmentPresenter(this);
        listViewAdapter = new ListViewDataAdapter(getActivity());
        listViewData.setAdapter(listViewAdapter);

//        加载数据
        initData();

        return view;
    }

    private void initGrideViewData() {
        gridViewBeanList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            PagerTwoDataGridViewBean gridViewBean = new PagerTwoDataGridViewBean();
            gridViewBean.setId(i + "");
            if (i == 1) {
                gridViewBean.setName("上游单体");
            } else if (i == 2) {
                gridViewBean.setName("资源方价格");
            } else if (i == 3) {
                gridViewBean.setName("装置动态");
            } else if (i == 4) {
                gridViewBean.setName("市场价格");
            } else if (i == 5) {
                gridViewBean.setName("期货行情");
            }
            gridViewBeanList.add(gridViewBean);
        }

    }

    private void initData() {
        String dateType = null;
        dataFragmentPresenter.getDataFragmentResult(dateType);

    }

    public void searchData(int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(getActivity(), "" + year + "-" + monthOfYear + "-" + dayOfMonth + "在数据中搜索", Toast.LENGTH_SHORT).show();
    }

    //    从网络加载数据
    @Override
    public void setDataInDataFragmentView(String result) {
        Gson gson = new Gson();
        PagerTwoDataListViewBean pagerTwoDataListViewBean = gson.fromJson(result, PagerTwoDataListViewBean.class);
        in = pagerTwoDataListViewBean.getIn();
        listViewAdapter.clearData();
        listViewAdapter.addPagerTwoDataListViewBeens(in);
        listViewAdapter.notifyDataSetChanged();
    }

    class MyAdapterItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (parent.getId()) {
                case R.id.list_view_data:
                    Toast.makeText(getActivity(), "List--->" + in.get(position).getDataid(), Toast.LENGTH_SHORT).show();
                    break;

                case R.id.dateType:
                    String dateType = gridViewBeanList.get(position).getId();
                    in.clear();
                    dataFragmentPresenter.getDataFragmentResult(dateType);
                    break;
            }
        }
    }

}
