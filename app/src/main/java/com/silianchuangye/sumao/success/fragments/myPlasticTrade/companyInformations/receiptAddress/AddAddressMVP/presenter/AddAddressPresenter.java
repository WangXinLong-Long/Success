package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.view.IInvoiceInformationView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.AddressDisplayModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.IaddressDisplayCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.IaddressDisplayModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.mdel.AddAddressModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.mdel.IaddAddressCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.mdel.IaddAddressModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.view.IAddAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.view.IReceiptAddressDetailView;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public class AddAddressPresenter {
    IAddAddress addAddress;
    IReceiptAddressDetailView receiptAddressDetailView;
    IInvoiceInformationView invoiceInformationView;

    public AddAddressPresenter(IInvoiceInformationView invoiceInformationView) {
        this.invoiceInformationView = invoiceInformationView;
    }

    public  AddAddressPresenter(IAddAddress addAddress){
        this.addAddress = addAddress;
    }

    public AddAddressPresenter(IReceiptAddressDetailView receiptAddressDetailView){
        this.receiptAddressDetailView = receiptAddressDetailView;
    }

    public void sendrequestGetAddress(String province,String city,String county){
        IaddressDisplayModel addressDisplayModel = new AddressDisplayModel(province,city,county,0);
        addressDisplayModel.getAddressDisplayInfo(new IaddressDisplayCallback() {
            @Override
            public void callbackAddressDisplayInfo(String address,int position) {
                invoiceInformationView.setRegionInText(address);

            }
        });
    }

    public void setDisplaylAddress(String province,String city,String county){
        IaddressDisplayModel addressDisplayModel = new AddressDisplayModel(province,city,county,0);
        addressDisplayModel.getAddressDisplayInfo(new IaddressDisplayCallback() {
            @Override
            public void callbackAddressDisplayInfo(String address,int position) {
                receiptAddressDetailView.setDisplayAddressInAddress(address);

            }
        });
    }
    public void setDetailAddress(String province,String city,String county){
        IaddressDisplayModel addressDisplayModel = new AddressDisplayModel(province,city,county,0);
        addressDisplayModel.getAddressDisplayInfo(new IaddressDisplayCallback() {
            @Override
            public void callbackAddressDisplayInfo(String address,int position) {
                addAddress.setAddressInText(address);

            }
        });
    }
    public void sendAddAddressInfo(String address,String sessionId,String consignee,String phone,String zip_code,String mobile,String detail){
        IaddAddressModel model = new AddAddressModel(address,sessionId,consignee,phone,zip_code,mobile,detail);
        model.sendAddAddressInfoToServer(new IaddAddressCallback() {
            @Override
            public void addAddresscallback(String addAddressBean) {
                if (addAddressBean.contains("sucess")){
                    addAddress.sendAddAddressToServerSuccess();

                }else if (addAddressBean.contains("formError")){
                    addAddress.sendAddAddressToServerFaild();
                }
            }
        });

    }

    public void sendAddAddressToServer(){
        addAddress.sendAddAddressToServer();

    }
}
