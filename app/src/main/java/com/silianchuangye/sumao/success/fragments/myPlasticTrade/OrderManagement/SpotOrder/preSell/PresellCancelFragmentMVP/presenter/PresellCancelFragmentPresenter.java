package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.PresellCancelFragmentMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.PresellCancelFragmentMVP.view.IPresellCancelFragmentView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.model.IPresellallFragmentModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.model.PresellallFragmentCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.model.PresellallFragmentModel;

/**
 * Created by Jobs Created on 2016/12/29.
 */
public class PresellCancelFragmentPresenter {
    IPresellCancelFragmentView presellCancelFragmentView;

    public PresellCancelFragmentPresenter(IPresellCancelFragmentView presellCancelFragmentView) {
        this.presellCancelFragmentView = presellCancelFragmentView;
    }

    public void getPresellCancelFragmentResult(String searchTrateType,String searchCompanyName, String searchOrderType,
                                               String searchOrderState, String pageNum, String searchOrderId,
                                               String startDate, String endDate, String submitType,
                                               String searchCheckType,String _dynSessConf,boolean update){
        IPresellallFragmentModel presellallFragmentModel = new PresellallFragmentModel( searchTrateType,searchCompanyName,  searchOrderType,
                searchOrderState,  pageNum,  searchOrderId,
                startDate,  endDate,  submitType,
                searchCheckType,_dynSessConf,update);
        presellallFragmentModel.getPresellallFragmentResult(new PresellallFragmentCallback() {
            @Override
            public void callbackPresellallFragmentResult(String result,boolean update) {
                presellCancelFragmentView.setPresellCancelFragmentResult(result,update);
            }
        });
    }
}
