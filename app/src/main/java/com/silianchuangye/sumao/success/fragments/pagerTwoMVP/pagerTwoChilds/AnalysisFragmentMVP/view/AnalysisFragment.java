package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.AnalysisFragmentMVP.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.AnalysisFragmentMVP.presenter.AnalysisFragmentPresenter;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.adapter.ListViewDataAdapter;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.bean.PagerTwoDataGridViewBean;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.bean.PagerTwoDataListViewBean;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.presenter.DataFragmentPresenter;
import com.silianchuangye.sumao.success.utils.NetWorkChangeBroadcastReceiver;

import java.util.List;


/**
 * Created by Administrator on 2016/12/13.
 */

public class AnalysisFragment extends Fragment implements IAnalysisFragmentView {
    private ListView listViewData;
    private ListViewDataAdapter listViewAdapter;
    private DataFragmentPresenter dataFragmentPresenter;
    private List<PagerTwoDataListViewBean.InBean> in;
    private List<PagerTwoDataGridViewBean> gridViewBeanList;
    private AnalysisFragmentPresenter analysisFragmentPresenter;
    private BroadcastReceiver netReceiver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initReceiver();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_fragment, container, false);

        analysisFragmentPresenter = new AnalysisFragmentPresenter(this);
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
        analysisFragmentPresenter.getAnalysisFragmentResult();

    }


    //    从网络加载数据
    @Override
    public void setAnalysisFragmentResult(String result) {
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

    public void searchData(int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(getActivity(), "在分析页面中操作", Toast.LENGTH_SHORT).show();
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
                    analysisFragmentPresenter.getAnalysisFragmentResult();
                }
            }
        };
        IntentFilter filter = new IntentFilter(NetWorkChangeBroadcastReceiver.NET_CHANGE);
        getActivity().registerReceiver(netReceiver,filter);
    }

}
