package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.order.OrderallFragmentMVP.view;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.adapter.MyAdapter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.OrderDetails.AlreadyPaidActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.SpotOrder;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.TiQu;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.order.OrderallFragmentMVP.presenter.OrderallFragmentPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.bean.PreSellAllBean;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderallFragment extends Fragment implements IOrderallFragmentView {
    private ExpandableListView elvDemo;
    SharedPreferences sp;
    String unique123;
    private List<Map<String, Object>> listparrent;
    private List<List<Map<String, Object>>> listitem;
    MyAdapter adapter;
    String orderId, type;
    String page = 1 + "";
    boolean ListFlag;
    String OrderType = "fixedPricingOrder", subType = 1+"", Kpstate = "", startDate = "", endDate = "", company = "", OrderId = "";
    private OrderallFragmentPresenter orderallFragmentPresenter;
    private List<PreSellAllBean.OrderBean> orders = new ArrayList<>();

    public OrderallFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sp = getActivity().getSharedPreferences("sumao", Activity.MODE_PRIVATE);
        unique123 = sp.getString("unique", "");
        listparrent = new ArrayList<Map<String, Object>>();
        listitem = new ArrayList<List<Map<String, Object>>>();
        View view = inflater.inflate(R.layout.fragment_orderall, container, false);
        //实例化
        PullToRefreshLayout ptr = (PullToRefreshLayout) view.findViewById(R.id.refresh_view);
        elvDemo = (ExpandableListView) ptr.getPullableView();
        ptr.setOnPullListener(new MyPullListener());
        //去掉expandListview的特别的下拉标志
        elvDemo.setGroupIndicator(null);
        //去掉ListView之间的线
        elvDemo.setDivider(null);

        elvDemo.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                LogUtils.log(orders.get(groupPosition).getState() + "");
                if ("QUOTED".equals(orders.get(groupPosition).getState())) {
                    Intent intent = new Intent();
                    intent.putExtra("ID", orders.get(groupPosition));
                    intent.setClass(getActivity(), AlreadyPaidActivity.class);
                    startActivity(intent);
                } else if ("SUBMITTED".equals(orders.get(groupPosition).getState())) {
                    Intent intent = new Intent();
                    intent.putExtra("ID", orders.get(groupPosition));
                    intent.setClass(getActivity(), SpotOrder.class);
                    startActivity(intent);
                }
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
        orderallFragmentPresenter = new OrderallFragmentPresenter(this);
        if (isVisibleToUser) {
            sendMy(page, subType, Kpstate, startDate, endDate, company, OrderId, true);
        }

    }

    public void sendMy(String page, String subType, String KPstate, String startDates, String endDates, String company, String OrderId, boolean updates) {
        String searchCompanyName = company.trim();//公司
        String searchOrderType = "forwardPricingOrder"; //订单类型
        String searchOrderState = "0";//订单状态
        String pageNum = page;
        String searchOrderId = OrderId;//订单
        String startDate = startDates;//开始日期
        String endDate = endDates;//结束日期
        String submitType = subType;//查询类型
        String searchCheckType = KPstate;//开票状态
//        String _dynSessConf = getActivity().getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("unique", "");
        boolean update = updates;
        orderallFragmentPresenter.getOrderallFragmentResult(searchCompanyName, searchOrderType,
                searchOrderState, pageNum, searchOrderId,
                startDate, endDate, submitType,
                searchCheckType/*, _dynSessConf*/, update);

    }

    //
    @Override
    public void setOrderallFragmentData(String result, boolean update) {
        Gson gson = new Gson();
        PreSellAllBean preSellAllBean = gson.fromJson(result, PreSellAllBean.class);
        if (preSellAllBean.getInfo().equals("fail")) {
            Toast.makeText(getActivity(), "请重新登陆", Toast.LENGTH_SHORT).show();
            new TiQu(getActivity()).showLogin();
            getActivity().finish();
        }
        if (preSellAllBean.getCount().equals("0")) {
            orders.clear();
        }
        List<PreSellAllBean.OrderBean> order = preSellAllBean.getOrder();
        if (update) {
            orders.clear();
        }
        orders.addAll(order);
        adapter = new MyAdapter(/*listparrent, listitem,*/ getActivity());
        adapter.addData(orders);
        elvDemo.setAdapter(adapter);
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
                    sendMy(pageNum, subType, Kpstate, startDate, endDate, company, OrderId, false);
                    // 千万别忘了告诉控件加载完毕了哦！
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }
    }
}
