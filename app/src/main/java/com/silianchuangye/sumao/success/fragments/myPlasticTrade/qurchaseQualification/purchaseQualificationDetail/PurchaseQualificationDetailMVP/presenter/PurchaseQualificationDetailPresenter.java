package com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail.PurchaseQualificationDetailMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail.PurchaseQualificationDetailMVP.model.IPurchaseQualificationDetailModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail.PurchaseQualificationDetailMVP.model.PurchaseQualificationDetailCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail.PurchaseQualificationDetailMVP.model.PurchaseQualificationDetailModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail.PurchaseQualificationDetailMVP.view.IPurchaseQualificationDetailView;

/**
 * Created by Jobs Created on 2017/2/24.
 */
public class PurchaseQualificationDetailPresenter {
    IPurchaseQualificationDetailView purchaseQualificationDetailView;

    public PurchaseQualificationDetailPresenter(IPurchaseQualificationDetailView purchaseQualificationDetailView) {
        this.purchaseQualificationDetailView = purchaseQualificationDetailView;
    }

//    获取审核状态
    public void getAuditStatus(String _dynSessConf, String up){
        IPurchaseQualificationDetailModel purchaseQualificationDetailModel = new PurchaseQualificationDetailModel(_dynSessConf,up);
        purchaseQualificationDetailModel.getPurchaseQualificationDetailResult(new PurchaseQualificationDetailCallback() {
            @Override
            public void callbackPurchaseQualificationDetail(String result) {
                purchaseQualificationDetailView.setAuditStatus(result);
            }
        });
    }
}
