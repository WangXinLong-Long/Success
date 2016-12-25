package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsMVP.model.CreateLogisticCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsMVP.model.CreateLogisticModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsMVP.model.ICreateLogisticModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsMVP.view.ICreateLogisticView;

/**
 * Created by Jobs Created on 2016/12/21.
 */
public class CreateLogisticPresenter {
    ICreateLogisticView createLogisticView;

    public CreateLogisticPresenter(ICreateLogisticView createLogisticView) {
        this.createLogisticView = createLogisticView;
    }

    public void getCreateLogisticResultFromService(String _dynSessConf){
        ICreateLogisticModel createLogisticModel = new CreateLogisticModel( _dynSessConf);
        createLogisticModel.getCreateLogisticResult(new CreateLogisticCallback() {
            @Override
            public void callbackCreateLogistic(String result) {
                createLogisticView.getCreateLogisticResult( result);
            }
        });
    }
}
