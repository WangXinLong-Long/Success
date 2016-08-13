package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.bean.InvoiceInformationBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.model.IInvoiceInformationModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.model.InvoiceInformationCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.model.InvoiceInformationModel;
;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.model.saveModifyInvoiceInformationModel.SaveModifyInvoiceInformationCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.model.saveModifyInvoiceInformationModel.SaveModifyInvoiceInformationModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.view.IInvoiceInformationView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.view.InvoiceInformation;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class InvoiceInformationPresenter {
    IInvoiceInformationView iInvoiceInformationView = new InvoiceInformation();

    public InvoiceInformationPresenter(IInvoiceInformationView iInvoiceInformationView) {
        this.iInvoiceInformationView = iInvoiceInformationView;
    }

//    从服务器获取数据，设置到对应的位置上
    public void getInvoiceInformationToBean(String sessionId){
        IInvoiceInformationModel model = new InvoiceInformationModel(sessionId);
        model.getInvoiceInformationFromService(new InvoiceInformationCallback() {
            @Override
            public void callbackInvoiceInformation(InvoiceInformationBean bean) {
                iInvoiceInformationView.setResultInText(bean);
            }
        });
    }

//    点击保存按钮，将修改的信息保存到服务器上
    public void saveModifyInvoiceInformation(String tax, String address, String phoneNO, String bank,
                                             String bankAccount, String notesRecipientName, String notesRecipientMobile,
                                             String notesRecipientPhone, String notesRecipientProvince,
                                             String notesRecipientCity, String notesRecipientCounty,
                                             String notesRecipientAddress, String notesRecipientZipCode,
                                             String sessionId){
        SaveModifyInvoiceInformationModel  saveModifyInvoiceInformationModel = new SaveModifyInvoiceInformationModel(tax,  address,  phoneNO,  bank,
                 bankAccount,  notesRecipientName,  notesRecipientMobile, notesRecipientPhone,  notesRecipientProvince,
                 notesRecipientCity,  notesRecipientCounty,notesRecipientAddress,  notesRecipientZipCode,sessionId);
        saveModifyInvoiceInformationModel.SaveModifyInvoiceInformationToServer(new SaveModifyInvoiceInformationCallback() {
            @Override
            public void callbackSaveModifyInvoiceInformation(String result) {
                if (result.contains("sucess")){
                    iInvoiceInformationView.saveModifyInvoiceInformationSuccess();
                }else if (result.contains("formExcepution")){
                    iInvoiceInformationView.saveModifyInvoiceInformationFailed();
                }
            }
        });
    }
}
