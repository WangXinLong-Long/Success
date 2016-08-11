package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.AddressDisplayModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.IAddressDisplayCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.IAddressDisplayModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.view.IAddressDisplayView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.view.IAddAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.model.ReceiptAddressModel;

import java.util.List;

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
        IAddressDisplayModel addressDisplayModel = new AddressDisplayModel(province,city,county,position);
        addressDisplayModel.getAddressDisplayInfo(new IAddressDisplayCallback() {
            @Override
            public void callbackAddressDisplayInfo(String address,int position) {
                addressDisplayView.setAddressDisplay(address,position);

            }
        });
    }
}
