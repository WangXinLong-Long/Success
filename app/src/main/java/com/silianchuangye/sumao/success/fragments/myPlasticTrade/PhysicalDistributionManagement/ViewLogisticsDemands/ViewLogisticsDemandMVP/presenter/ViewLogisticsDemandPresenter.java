package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.ViewLogisticsDemands.ViewLogisticsDemandMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.ViewLogisticsDemands.ViewLogisticsDemandMVP.model.IViewLogisticsDemandModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.ViewLogisticsDemands.ViewLogisticsDemandMVP.model.ViewLogisticsDemandCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.ViewLogisticsDemands.ViewLogisticsDemandMVP.model.ViewLogisticsDemandModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.ViewLogisticsDemands.ViewLogisticsDemandMVP.view.IViewLogisticsDemandView;

/**
 * Created by Jobs Created on 2016/12/21.
 */
public class ViewLogisticsDemandPresenter {
IViewLogisticsDemandView viewLogisticsDemandView;

    public ViewLogisticsDemandPresenter(IViewLogisticsDemandView viewLogisticsDemandView) {
        this.viewLogisticsDemandView = viewLogisticsDemandView;
    }

    public void getViewLogisticsDemandResult(String _dynSessConf, String logisticsId, String logisticsType, String shippingMethod, String orderId, String createDate, String deliveryStartDate, String deliveryEndDate){
        IViewLogisticsDemandModel viewLogisticsDemandModel = new ViewLogisticsDemandModel( _dynSessConf,  logisticsId,  logisticsType,  shippingMethod,  orderId,  createDate,  deliveryStartDate,  deliveryEndDate);
        viewLogisticsDemandModel.getViewLogisticsDemandResult(new ViewLogisticsDemandCallback() {
            @Override
            public void callbackViewLogisticsDemand(String result) {
                viewLogisticsDemandView.getViewLogisticsDemandResult( result);
            }
        });
    }
}
