package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.order.OrderallFragmentMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.order.OrderallFragmentMVP.view.IOrderallFragmentView;

/**
 * Created by Jobs Created on 2016/12/29.
 */
public class OrderallFragmentPresenter {
    IOrderallFragmentView orderallFragmentView ;

    public OrderallFragmentPresenter(IOrderallFragmentView orderallFragmentView) {
        this.orderallFragmentView = orderallFragmentView;
    }

    public void getOrderallFragmentResult(String searchCompanyName, String searchOrderType,
                                            String searchOrderState, String pageNum, String searchOrderId,
                                            String startDate, String endDate, String submitType,
                                            String searchCheckType/*,String _dynSessConf*/,boolean update){
//        IPresellallFragmentModel presellallFragmentModel = new PresellallFragmentModel( searchCompanyName,  searchOrderType,
//                searchOrderState,  pageNum,  searchOrderId,
//                startDate,  endDate,  submitType,
//                searchCheckType/*,_dynSessConf*/,update);
//        presellallFragmentModel.getPresellallFragmentResult(new PresellallFragmentCallback() {
//            @Override
//            public void callbackPresellallFragmentResult(String result,boolean update) {
//                orderallFragmentView.setOrderallFragmentData(result,update);
//            }
//        });
    }
}
