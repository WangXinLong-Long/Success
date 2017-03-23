package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.NewsFragmentMVP.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
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
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.NewsFragmentMVP.adapter.ListViewDataAdapter;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.NewsFragmentMVP.bean.NewsFragmentBean;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.NewsFragmentMVP.presenter.NewsFragmentPresenter;
import com.silianchuangye.sumao.success.utils.NetWorkChangeBroadcastReceiver;

import java.text.SimpleDateFormat;
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
    private BroadcastReceiver netReceiver;
   // private GridView dateType;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initReceiver();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.analysis_fragment,container,false);

        newsFragmentPresenter = new NewsFragmentPresenter(this);
//
//        dateType= (GridView) view.findViewById(R.id.dateType);
//        dateType.setVisibility(View.GONE);
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
        newsFragmentPresenter.getNewsFragmentResult("");

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
                    Intent intent=new Intent(getActivity(), NewsActivity.class);
                    intent.putExtra("title",in.get(position).getIn_xwbt());
                    intent.putExtra("id",in.get(position).getIn_xwid());
                   // intent.putExtra("time",in.get(position).getIn_xwtime());
                    intent.putExtra("time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Double.parseDouble(in.get(position).getIn_xwtime())));
                    intent.putExtra("number","abc");
                    intent.putExtra("from","abc");
                    startActivity(intent);
                    Toast.makeText(getActivity(), "List--->" + in.get(position).getIn_xwid(), Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }


    public void searchData(int year, int monthOfYear, int dayOfMonth) {

        String dateType = year+"-"+(monthOfYear)+"-"+dayOfMonth;
        Log.d("新聞中選擇的時間",dateType);
        newsFragmentPresenter.getNewsFragmentResult(dateType);
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
                    newsFragmentPresenter.getNewsFragmentResult("");
                }
            }
        };
        IntentFilter filter = new IntentFilter(NetWorkChangeBroadcastReceiver.NET_CHANGE);
        getActivity().registerReceiver(netReceiver,filter);
    }
}
