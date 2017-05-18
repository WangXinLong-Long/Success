package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.PresellChangeFragmentMVP.view;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.SpotOrder;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.TiQu;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.PresellChangeFragmentMVP.presenter.PresellChangeFragmentPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.bean.PreSellAllBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 已变更
 */
public class PresellChangeFragment extends Fragment implements IPresellChangeFragmentView{
    private ExpandableListView elvDemo;
    MyAdapter adapter;
    String page = 1 + "";
    String subType = "";//查询类型（1 查询我的订单  2 查询所有订单  默认为1）
    String Kpstate = "";//开票状态  （0 待开票  1 已开票  不填查询所有）
    String startDate = "";
    String endDate = "";
    String company = "";
    String OrderId = "";
    String searchTrateType = "";
    private List<PreSellAllBean.OrderBean> orders = new ArrayList<>();
    private PresellChangeFragmentPresenter presellChangeFragmentPresenter;

    public PresellChangeFragment() {
        // Required empty public constructor
    }
    private String searchOrderType = "";
    public String getSearchOrderType() {
        return searchOrderType;
    }
    public void setSearchOrderType(String searchOrderType) {
        this.searchOrderType = searchOrderType;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_presell_change, container, false);
        //实例化
        PullToRefreshLayout ptr=(PullToRefreshLayout)view.findViewById(R.id.refresh_view);
        elvDemo=(ExpandableListView)ptr.getPullableView();
        ptr.setOnPullListener(new MyPullListener());
        //去掉expandListview的特别的下拉标志
        elvDemo.setGroupIndicator(null);
        //去掉ListView之间的线
        elvDemo.setDivider(null);
        adapter = new MyAdapter(/*listparrent, listitem,*/ getActivity());
        elvDemo.setAdapter(adapter);
        elvDemo.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Intent intent = new Intent();
                intent.putExtra("id", orders.get(groupPosition).getOrderId());
                intent.setClass(getActivity(), SpotOrder.class);
                startActivity(intent);
                return true;

            }
        });

        return view;
    }

    /**
     * 判断是否是初始化Fragment
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        presellChangeFragmentPresenter = new PresellChangeFragmentPresenter(this);
        if (isVisibleToUser) {
            sendMy(searchTrateType,searchOrderType,page, subType, Kpstate, startDate, endDate, company, OrderId, true);
        }

    }

    public void sendMy(String searchTrateTypeInput,String searchOrderTypeInput,String page, String subType, String KPstate, String startDates, String endDates, String company, String OrderId, boolean updates) {
        String searchCompanyName = company;//公司
        String searchOrderType;
        if (searchOrderTypeInput.equals("")){
            searchOrderType = this.searchOrderType;
        }else {
            searchOrderType = searchOrderTypeInput; //订单类型(预售)
        }//订单类型(预售)
        String searchTrateType = searchTrateTypeInput;//现货订单的类型（团购、竞拍。。。）
        this.searchTrateType = searchTrateTypeInput;

        String searchOrderState = "CHANGED";//订单状态(已变更)
        String pageNum = page;
        String searchOrderId = OrderId;//订单
        String startDate = startDates;//开始日期
        String endDate = endDates;//结束日期
        String submitType = subType;//查询类型
        String searchCheckType = KPstate;//开票状态
//        String _dynSessConf = getActivity().getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("unique", "");
//        LogUtils.log("_dynSessConf-->"+_dynSessConf);
        boolean update = updates;
        Log.d("传入的参数",searchCompanyName+searchOrderType+"CHANGED"+pageNum+searchOrderId);
        Log.d("传入的参数",startDate+endDate+submitType+searchCheckType+update);
        presellChangeFragmentPresenter.getPresellChangeFragmentResult(searchTrateType,searchCompanyName, searchOrderType,
                "CHANGED", pageNum, searchOrderId,
                startDate, endDate, submitType,
                searchCheckType/*, _dynSessConf*/, update);
    }

    //
    @Override
    public void setPresellChangeFragmentResult(String result, boolean update) {
        Gson gson = new Gson();
        PreSellAllBean preSellAllBean = gson.fromJson(result, PreSellAllBean.class);
        if (preSellAllBean.getInfo().equals("fail")) {
            Toast.makeText(getActivity(), "请重新登陆", Toast.LENGTH_SHORT).show();
            new TiQu(getActivity()).showLogin();
            getActivity().finish();
        }
        if (preSellAllBean.getCount().equals("0")) {
            orders.clear();
            adapter.clearData();
            adapter.notifyDataSetChanged();
            Toast.makeText(getActivity(),"没有找到您要查找的商品信息",Toast.LENGTH_SHORT).show();
        }
        List<PreSellAllBean.OrderBean> order = preSellAllBean.getOrder();
        if (update) {
            orders.clear();
        }
        orders.addAll(order);

        adapter.clearData();
        adapter.addData(orders);
        adapter.notifyDataSetChanged();
        if (adapter != null && orders != null) {
            for (int i = 0; i < orders.size(); i++) {
                elvDemo.expandGroup(i);
            }
        }
    }

    private class MyPullListener implements PullToRefreshLayout.OnPullListener {
        String pageNum = "1";

        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            // 下拉刷新操作
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    pageNum = "1";
                    // 千万别忘了告诉控件刷新完毕了哦！
                    sendMy(searchTrateType,searchOrderType,pageNum, subType, Kpstate, startDate, endDate, company, OrderId, true);
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            // 加载操作
            new Handler() {
                @Override
                public void handleMessage(Message msg) {

                    int num = Integer.parseInt(pageNum) + 1;
                    pageNum = num + "";
                    sendMy(searchTrateType,searchOrderType,pageNum, subType, Kpstate, startDate, endDate, company, OrderId, false);
                    // 千万别忘了告诉控件加载完毕了哦！
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }
    }
}
