package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.PresellAlreadyPaidFragmentMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.PresellAlreadyPaidFragmentMVP.view.IPresellAlreadyPaidFragmentView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.model.IPresellallFragmentModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.model.PresellallFragmentCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.model.PresellallFragmentModel;

/**
 * Created by Jobs Created on 2016/12/29.
 */
public class PresellAlreadyPaidFragmentPresenter {
    IPresellAlreadyPaidFragmentView presellAlreadyPaidFragmentView;

    public PresellAlreadyPaidFragmentPresenter(IPresellAlreadyPaidFragmentView presellAlreadyPaidFragmentView) {
        this.presellAlreadyPaidFragmentView = presellAlreadyPaidFragmentView;
    }

    public void getIPreselAlreadyPaidFragmentResult(String searchTrateType,String searchCompanyName, String searchOrderType,
                                                    String searchOrderState, String pageNum, String searchOrderId,
                                                    String startDate, String endDate, String submitType,
                                                    String searchCheckType/*,String _dynSessConf*/,boolean update){
        IPresellallFragmentModel presellallFragmentModel = new PresellallFragmentModel( searchTrateType,searchCompanyName,  searchOrderType,
                searchOrderState,  pageNum,  searchOrderId,
                startDate,  endDate,  submitType,
                searchCheckType/*,_dynSessConf*/,update);
        presellallFragmentModel.getPresellallFragmentResult(new PresellallFragmentCallback() {
            @Override
            public void callbackPresellallFragmentResult(String result,boolean update) {
                presellAlreadyPaidFragmentView.setPresellAlreadyPaidFragmentResult(result,update);
            }
        });
    }
}
