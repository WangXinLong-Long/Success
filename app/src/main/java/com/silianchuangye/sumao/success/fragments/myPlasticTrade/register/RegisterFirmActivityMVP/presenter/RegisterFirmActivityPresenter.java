package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.AddressDisplayModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.IAddressDisplayCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.IAddressDisplayModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.view.IRegisterFirmActivityView;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public class RegisterFirmActivityPresenter  {
    IRegisterFirmActivityView registerFirmActivityView;

    public  RegisterFirmActivityPresenter(IRegisterFirmActivityView registerFirmActivityView){
        this.registerFirmActivityView = registerFirmActivityView;

    }
    public void setDetailAddress(String province,String city,String county){
        IAddressDisplayModel addressDisplayModel = new AddressDisplayModel(province,city,county,0);
        addressDisplayModel.getAddressDisplayInfo(new IAddressDisplayCallback() {
            @Override
            public void callbackAddressDisplayInfo(String address,int position) {
                registerFirmActivityView.setStringInText(address);

            }
        });
    }
}
