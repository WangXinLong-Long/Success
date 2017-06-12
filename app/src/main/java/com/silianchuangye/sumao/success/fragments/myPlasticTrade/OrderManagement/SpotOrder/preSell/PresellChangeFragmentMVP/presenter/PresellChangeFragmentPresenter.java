package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.PresellChangeFragmentMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.PresellChangeFragmentMVP.view.IPresellChangeFragmentView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.model.IPresellallFragmentModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.model.PresellallFragmentCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.model.PresellallFragmentModel;

/**
 * Created by Jobs Created on 2016/12/29.
 */
public class PresellChangeFragmentPresenter {
    IPresellChangeFragmentView presellChangeFragmentView ;

    public PresellChangeFragmentPresenter(IPresellChangeFragmentView presellChangeFragmentView) {
        this.presellChangeFragmentView = presellChangeFragmentView;
    }

    public void getPresellChangeFragmentResult(String searchTrateType,String searchCompanyName, String searchOrderType,
                                               String searchOrderState, String pageNum, String searchOrderId,
                                               String startDate, String endDate, String submitType,
                                               String searchCheckType,String _dynSessConf,boolean update) {
        IPresellallFragmentModel presellallFragmentModel = new PresellallFragmentModel(searchTrateType,searchCompanyName, searchOrderType,
                searchOrderState, pageNum, searchOrderId,
                startDate, endDate, submitType,
                searchCheckType, _dynSessConf, update);
        presellallFragmentModel.getPresellallFragmentResult(new PresellallFragmentCallback() {
            @Override
            public void callbackPresellallFragmentResult(String result, boolean update) {
                presellChangeFragmentView.setPresellChangeFragmentResult(result, update);
            }
        });
    }
}
