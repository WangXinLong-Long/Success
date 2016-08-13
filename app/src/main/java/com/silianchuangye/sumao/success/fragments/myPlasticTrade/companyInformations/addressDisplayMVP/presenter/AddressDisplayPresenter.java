package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.AddressDisplayModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.IaddressDisplayCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.IaddressDisplayModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.view.IAddressDisplayView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.view.IAddAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.model.ReceiptAddressModel;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class AddressDisplayPresenter {
    IAddressDisplayView addressDisplayView ;
    ReceiptAddressModel model;
    IAddAddress addAddress;
    public AddressDisplayPresenter(IAddressDisplayView addressDisplayView ) {
        this.addressDisplayView = addressDisplayView;
    }
    public void setDetailAddress(String province,String city,String county,int position){
        IaddressDisplayModel addressDisplayModel = new AddressDisplayModel(province,city,county,position);
        addressDisplayModel.getAddressDisplayInfo(new IaddressDisplayCallback() {
            @Override
            public void callbackAddressDisplayInfo(String address,int position) {
                addressDisplayView.setAddressDisplay(address,position);

            }
        });
    }
}
