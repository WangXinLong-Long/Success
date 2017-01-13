package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.model.IPresellallFragmentModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.model.PresellallFragmentCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.model.PresellallFragmentModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.view.IPresellallFragmentView;

/**
 * Created by Jobs Created on 2016/12/28.
 */
public class PresellallFragmentPresenter {
    IPresellallFragmentView presellallFragmentView;

    public PresellallFragmentPresenter(IPresellallFragmentView presellallFragmentView) {
        this.presellallFragmentView = presellallFragmentView;
    }

    public void getPresellallFragmentResult(String searchTrateType,String searchCompanyName, String searchOrderType,
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
                presellallFragmentView.setPresellallFragmentResult(result,update);
            }
        });
    }
}
