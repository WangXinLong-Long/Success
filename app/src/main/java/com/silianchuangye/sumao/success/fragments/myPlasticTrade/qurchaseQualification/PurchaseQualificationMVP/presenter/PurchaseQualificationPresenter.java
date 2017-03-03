package com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.PurchaseQualificationMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.PurchaseQualificationMVP.model.IPurchaseQualificationModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.PurchaseQualificationMVP.model.PurchaseQualificationCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.PurchaseQualificationMVP.model.PurchaseQualificationModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.PurchaseQualificationMVP.view.IPurchaseQualificationView;

/**
 * Created by Jobs Created on 2017/2/13.
 */
public class PurchaseQualificationPresenter {
    IPurchaseQualificationView purchaseQualificationView;

    public PurchaseQualificationPresenter(IPurchaseQualificationView purchaseQualificationView) {
        this.purchaseQualificationView = purchaseQualificationView;
    }

    public void getPurchaseQualificationDetail(){
        IPurchaseQualificationModel purchaseQualificationModel = new PurchaseQualificationModel();
        purchaseQualificationModel.getPurchaseQualificationResult(new PurchaseQualificationCallback() {
            @Override
            public void callbackPurchaseQualificationResult(String result) {
                purchaseQualificationView.setPurchaseQualificationDetailInView(result);
            }
        });
    }
}
