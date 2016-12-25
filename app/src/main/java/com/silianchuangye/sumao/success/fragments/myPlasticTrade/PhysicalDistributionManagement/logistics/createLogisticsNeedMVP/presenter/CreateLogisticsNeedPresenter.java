package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsNeedMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsNeedMVP.createBuyerSelfLogisticsNeedModel.CreateBuyerSelfLogisticsNeedCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsNeedMVP.createBuyerSelfLogisticsNeedModel.CreateBuyerSelfLogisticsNeedModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsNeedMVP.createBuyerSelfLogisticsNeedModel.ICreateBuyerSelfLogisticsNeedModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsNeedMVP.createSellerDistributionLogisticsNeed.CreateSellerDistributionLogisticsNeedCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsNeedMVP.createSellerDistributionLogisticsNeed.CreateSellerDistributionLogisticsNeedModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsNeedMVP.createSellerDistributionLogisticsNeed.ICreateSellerDistributionLogisticsNeedModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsNeedMVP.view.ICreateLogisticsNeedView;

/**
 * Created by Jobs Created on 2016/12/21.
 */
public class CreateLogisticsNeedPresenter {
    ICreateLogisticsNeedView createLogisticsNeedView ;

    public CreateLogisticsNeedPresenter(ICreateLogisticsNeedView createLogisticsNeedView) {
        this.createLogisticsNeedView = createLogisticsNeedView;
    }

//    买方自提
    public void getCreateBuyerSelfLogisticsNeedResult(String quantity, String commerceJson,
                                                      String shippingMethod, String shippingDate,
                                                      String deliveryerTel, String idCard,
                                                      String wareHouse, String licensePlateNo,
                                                      String deliveryer, String remarks){
        ICreateBuyerSelfLogisticsNeedModel createLogisticsNeedModel = new CreateBuyerSelfLogisticsNeedModel(  quantity,  commerceJson,
                 shippingMethod,  shippingDate,
                 deliveryerTel,  idCard,
                 wareHouse,  licensePlateNo,
                 deliveryer,  remarks);
        createLogisticsNeedModel.getCreateLogisticsNeedResult(new CreateBuyerSelfLogisticsNeedCallback() {
            @Override
            public void callbackCreateLogisticsNeed(String result) {
                createLogisticsNeedView.getCreateLogisticsDemandResult(result);
            }
        });
    }
//    卖家配送

    public void getCreateDistributionLogisticsNeedResult(String quantity, String commerceJson,
                                                         String shippingMethod, String provinceId,
                                                         String cityId, String countyId,
                                                         String contactPhone, String pickUpDateStart,
                                                         String pickUpDateEnd, String shippingContactTel,
                                                         String detailAddress,String contactPerson,
                                                         String repeiptCompany, String shippingContact){
        ICreateSellerDistributionLogisticsNeedModel createSellerDistributionLogisticsNeedModel = new CreateSellerDistributionLogisticsNeedModel( quantity,  commerceJson,
                 shippingMethod,  provinceId,
                 cityId,  countyId,
                 contactPhone,  pickUpDateStart,
                 pickUpDateEnd,  shippingContactTel,
                 detailAddress, contactPerson,
                 repeiptCompany,  shippingContact);
        createSellerDistributionLogisticsNeedModel.getCreateSellerDistribuotionLogisticsNeedResult(new CreateSellerDistributionLogisticsNeedCallback() {
            @Override
            public void callbackCreateSellerDistributionLogisticsNeed(String result) {
                createLogisticsNeedView.getCreateLogisticsDemandResult(result);
            }
        });
    }
}
