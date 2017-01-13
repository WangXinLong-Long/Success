package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.PresellToBePaidFragmentMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.PresellToBePaidFragmentMVP.view.IPresellToBePaidFragmentView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.model.IPresellallFragmentModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.model.PresellallFragmentCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.model.PresellallFragmentModel;

/**
 * Created by Jobs Created on 2016/12/29.
 */
public class PresellToBePaidFragmentPresenter {
    IPresellToBePaidFragmentView presellToBePaidFragmentView;

    public PresellToBePaidFragmentPresenter(IPresellToBePaidFragmentView presellToBePaidFragmentView) {
        this.presellToBePaidFragmentView = presellToBePaidFragmentView;
    }

    public void getPresellToBePaidFragmentResult(String searchTrateType,String searchCompanyName, String searchOrderType,
                                                 String searchOrderState, String pageNum, String searchOrderId,
                                                 String startDate, String endDate, String submitType,
                                                 String searchCheckType/*,String _dynSessConf*/,boolean update){
        IPresellallFragmentModel presellallFragmentModel = new PresellallFragmentModel(searchTrateType, searchCompanyName,  searchOrderType,
                searchOrderState,  pageNum,  searchOrderId,
                startDate,  endDate,  submitType,
                searchCheckType/*,_dynSessConf*/,update);
        presellallFragmentModel.getPresellallFragmentResult(new PresellallFragmentCallback() {
            @Override
            public void callbackPresellallFragmentResult(String result,boolean update) {
                presellToBePaidFragmentView.setPresellToBePaidFragmentView(result,update);
            }
        });
    }
}
