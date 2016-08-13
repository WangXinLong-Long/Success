package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.model.IreceiptAddressDetailModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.model.ModifyReceiptInformation.ImodifyReceiptInformationModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.model.ModifyReceiptInformation.ModifyReceiptInformationCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.model.ModifyReceiptInformation.ModifyReceiptInformationModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.model.ReceiptAddressDetailCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.model.ReceiptAddressDetailModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.model.RemoveReceiptAddress.IremoveReceiptAddressModel;
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
        IreceiptAddressDetailModel receiptAddressDetailModel = new ReceiptAddressDetailModel(addr,sessionId);
        receiptAddressDetailModel.setDefaultReceivingAddress(new ReceiptAddressDetailCallback() {
            @Override
            public void ReceiptAddressDetailInfoCallback(String result) {
                receiptAddressDetailView.defaultReceivingAddress(result);
            }
        });
    }

    public void RemoveReceiptAddress(String addr,String sessionId ){
        IremoveReceiptAddressModel removeReceiptAddressModel = new RemoveReceiptAddressModel(addr,sessionId);
        removeReceiptAddressModel.removeReceiptAddressRequest(new RemoveReceiptAddressCallback() {
            @Override
            public void RemoveReceiptAddressInfoCallback(String result) {
                receiptAddressDetailView.removeReceiptAddress(result);
            }
        });

    }

    public void ModifyReceiptInformation(String name,String region,String addressDetail,String zipCode,String mobile,String phone,String addr,String sessionId){
        ImodifyReceiptInformationModel modifyReceiptInformationModel = new ModifyReceiptInformationModel(name, region, addressDetail, zipCode, mobile, phone, addr,sessionId);
        modifyReceiptInformationModel.sendModifyReceiptInformationRequest(new ModifyReceiptInformationCallback() {
            @Override
            public void callbackModifyReceiptInformation(String result) {
                if (result.contains("sucess")){
                    receiptAddressDetailView.savaModifyReceiptInformationSuccess();
                }else if (result.contains("formExcepution")){
                    receiptAddressDetailView.savaModifyReceiptInformationFailed();
                }
            }
        });
    }

}
