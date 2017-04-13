package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.PagerTwoView.NewsActivity;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.adapter.ListViewDataAdapter;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.adapter.PagerTwoTextGrideViewAdapter;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.bean.PagerTwoDataGridViewBean;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.bean.PagerTwoDataListViewBean;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.presenter.DataFragmentPresenter;
import com.silianchuangye.sumao.success.utils.NetWorkChangeBroadcastReceiver;

import java.text.SimpleDateFormat;
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
        String dateType = year+"-"+(monthOfYear)+"-"+dayOfMonth;
        Log.d("在數據中選擇的時間",""+dateType);
        dataFragmentPresenter.getDataFragmentResult(dateType);
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
                    Intent intent=new Intent(getActivity(), NewsActivity.class);
                    intent.putExtra("title",in.get(position).getDataname());
                    intent.putExtra("id",in.get(position).getDataid());
                  //  intent.putExtra("time",in.get(position).getDatatime());
                    intent.putExtra("time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Double.parseDouble(in.get(position).getDatatime())));
                    intent.putExtra("number","abc");
                    intent.putExtra("from","abc");
                    intent.putExtra("dianzan","abc");
                    startActivity(intent);
                   // Toast.makeText(getActivity(), "List--->" + in.get(position).getDataid(), Toast.LENGTH_SHORT).show();
                    break;

                case R.id.dateType:
                    String dateType = gridViewBeanList.get(position).getId();
                    in.clear();
                    dataFragmentPresenter.getDataFragmentResult(dateType);
                    break;
            }
        }
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
                    dataFragmentPresenter.getDataFragmentResult(null);
                }
            }
        };
        IntentFilter filter = new IntentFilter(NetWorkChangeBroadcastReceiver.NET_CHANGE);
        getActivity().registerReceiver(netReceiver,filter);
    }

}
