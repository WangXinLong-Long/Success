package com.silianchuangye.sumao.success.ShangYou.SeePlanMVP.view;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.bean.CaiGouBean;
import com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.bean.PlanOrderList;
import com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.bean.VipPlanOrder;
import com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.presenter.CaiGouPresenter;
import com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.view.CaiGou;
import com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.view.ICaiGouView;
import com.silianchuangye.sumao.success.ShangYou.SeePlanInfo;
import com.silianchuangye.sumao.success.ShangYou.SeePlanMVP.presenter.SeePlanPresenter;
import com.silianchuangye.sumao.success.adapter.SeePlanAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeePlan extends Fragment implements ISeePlanView{
    private ListView lv;
    private List<SeePlanInfo> list;
    private SeePlanAdapter adapter;
    private Bundle arguments;
    private CaiGouBean caiGouBeans;
    private VipPlanOrder CaiGouBean;
    private List<PlanOrderList> planOrderList = new ArrayList<>();
//    private String productName;
//    private String demandScheduleState;
//    private String logisticsChangeName;
//    private String logisticsName;
//    private String orderId;
//    private String planID;
//    private String productNameChange;
//    private String quantity;
//    private String quantityChange;
//    private String scheduleDate;
//    private String scheduleDateChange;
//    private String warehouseChangeName;
//    private String warehouseName;
    private PullToRefreshLayout ptrl;
    private String sellerCompanyId;
    private SeePlanPresenter seePlanPresenter;
    private String sessionID;

    public SeePlan() {
        // Required empty public constructor
    }

    String pageNum = "1"; //页数
    String pageSize = "10";//每页条数
    String sellerEnterpriseId;//上游资源方ID
//    String _dynSessConf = getActivity().getSharedPreferences("sumao",getActivity().MODE_PRIVATE).getString("unique",""); //sessionID
    String productName = "";//商品名字
    String planStartDate = "";//开始日
    String planEndDate = "";//结束日
    String planState = "";//提报状态
    String warehouseName = "";//仓库名字
    String logisticsID = "";//物流ID
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_see_plan, container, false);
        ptrl = ((PullToRefreshLayout) v.findViewById(R.id.refresh_view));
        ptrl.setOnPullListener(new MyPullToRefreshListener());
        lv= (ListView) v.findViewById(R.id.lv_seeplan);

        arguments = getArguments();
        caiGouBeans = (CaiGouBean)arguments.getSerializable("caiGouBeans");
        sellerCompanyId = arguments.getString("sellerCompanyId");
        sessionID = arguments.getString("sessionID");
        productName = arguments.getString("productName",productName);
        planStartDate = arguments.getString("planStartDate",planStartDate);
        planEndDate = arguments.getString("planEndDate",planEndDate);
        planState = arguments.getString("planState",planState);
        warehouseName = arguments.getString("warehouseName",warehouseName);
        logisticsID = arguments.getString("logisticsID",logisticsID);
        CaiGouBean = caiGouBeans.getVipPlanOrder();

        adapter=new SeePlanAdapter(getActivity(),planOrderList);
        lv.setAdapter(adapter);
        planOrderList.clear();
        planOrderList.addAll( CaiGouBean.getPlanOrderList());
        adapter.notifyDataSetChanged();
//        getActivity().
        seePlanPresenter = new SeePlanPresenter(this);

//        initDate();
        return  v;
    }

//    private void initDate() {
//        list=new ArrayList<SeePlanInfo>();
//        for (int i = 0; i < planOrderList.size(); i++) {
//            productName = planOrderList.get(i).getProductName();//产品名称
//            demandScheduleState = planOrderList.get(i).getDemandScheduleState();//订单状态
//            logisticsName = planOrderList.get(i).getLogisticsName();//物流
//            orderId = planOrderList.get(i).getOrderId();//订单ID
//            planID = planOrderList.get(i).getPlanID();//计划ID
//            quantity = planOrderList.get(i).getQuantity();//数量
//            warehouseName = planOrderList.get(i).getWarehouseName();//仓库名
//            scheduleDate = planOrderList.get(i).getScheduleDate();//计划日
//            productNameChange = planOrderList.get(i).getProductNameChange();//修改后产品名字
//            logisticsChangeName = planOrderList.get(i).getLogisticsChangeName();//修改后物流
//            quantityChange = planOrderList.get(i).getQuantityChange();//修改后数量
//            scheduleDateChange = planOrderList.get(i).getScheduleDateChange();//修改后计划日
//            warehouseChangeName = planOrderList.get(i).getWarehouseChangeName();//修改后仓库名
//        }
//
//    }

    @Override
    public void getSeePlanInfoInFragment(CaiGouBean caiGouBean,String pageNum, String planStartDate,
                                         String planEndDate, String planState, String warehouseName,
                                         String logisticsID) {
        if (pageNum.equals("1")){
            planOrderList.clear();
        }
        this.pageNum = pageNum;
        this.planStartDate = planStartDate;
        this.planEndDate = planEndDate;
        this.planState = planState;
        this.warehouseName = warehouseName;
        this.logisticsID = logisticsID;
        planOrderList.addAll(caiGouBean.getVipPlanOrder().getPlanOrderList());
        adapter.notifyDataSetChanged();
    }


    //    上拉刷新，下拉加载的监听
    private class MyPullToRefreshListener implements PullToRefreshLayout.OnPullListener {
        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (planOrderList != null) {
                        planOrderList.clear();
                    }

                    pageNum = 1+"";
                    seePlanPresenter.requestSeePlanDate(pageNum,"10",sellerCompanyId,sessionID,productName,planStartDate,planEndDate,planState,warehouseName,logisticsID);

                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }

            }.sendEmptyMessageDelayed(0, 1000);


        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    int count = Integer.parseInt(pageNum)+1;
                    pageNum = count+"";
                    seePlanPresenter.requestSeePlanDate(pageNum,pageSize,sellerCompanyId,sessionID,productName,planStartDate,planEndDate,planState,warehouseName,logisticsID);
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);

                }
            }.sendEmptyMessageDelayed(0, 1000);
        }
    }
}
