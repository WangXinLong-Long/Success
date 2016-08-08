package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.model.IReceiptAddressDetailModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.model.ReceiptAddressDetailCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.model.ReceiptAddressDetailModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.model.RemoveReceiptAddress.IRemoveReceiptAddressModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.model.RemoveReceiptAddress.RemoveReceiptAddressCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.model.RemoveReceiptAddress.RemoveReceiptAddressModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.view.IReceiptAddressDetailView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.view.ReceiptAddressDetail;

/**
 * Created by Administrator on 2016/8/4 0004.
 */
public class ReceiptAddressDetailPresenter {
    IReceiptAddressDetailView receiptAddressDetailView = new ReceiptAddressDetail();

    public ReceiptAddressDetailPresenter(IReceiptAddressDetailView receiptAddressDetailView) {
        this.receiptAddressDetailView = receiptAddressDetailView;
    }
    public void setDefaultReceivingAddress(String addr,String sessionId ){
        IReceiptAddressDetailModel receiptAddressDetailModel = new ReceiptAddressDetailModel(addr,sessionId);
        receiptAddressDetailModel.setDefaultReceivingAddress(new ReceiptAddressDetailCallback() {
            @Override
            public void ReceiptAddressDetailInfoCallback(String result) {
                receiptAddressDetailView.defaultReceivingAddress(result);
            }
        });
    }

    public void RemoveReceiptAddress(String addr,String sessionId ){
        IRemoveReceiptAddressModel removeReceiptAddressModel = new RemoveReceiptAddressModel(addr,sessionId);
        removeReceiptAddressModel.removeReceiptAddressRequest(new RemoveReceiptAddressCallback() {
            @Override
            public void RemoveReceiptAddressInfoCallback(String result) {
                receiptAddressDetailView.removeReceiptAddress(result);
            }
        });

    }

}
