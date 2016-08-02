package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.AddressDisplayModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.IAddressDisplayCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.IAddressDisplayModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.view.IAddressDisplayView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.view.IAddAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.model.ReceiptAddressModel;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public class AddAddressPresenter {
    ReceiptAddressModel model;
    IAddAddress addAddress;

    public  AddAddressPresenter(IAddAddress addAddress){
        this.addAddress = addAddress;

    }
    public void setDetailAddress(String province,String city,String county){
        IAddressDisplayModel addressDisplayModel = new AddressDisplayModel(province,city,county);
        addressDisplayModel.getAddressDisplayInfo(new IAddressDisplayCallback() {
            @Override
            public void callbackAddressDisplayInfo(String address) {
                addAddress.setAddressInText(address);

            }
        });
    }
}
