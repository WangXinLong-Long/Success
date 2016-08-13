package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.AddressDisplayModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.IaddressDisplayCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.IaddressDisplayModel;
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
        IaddressDisplayModel addressDisplayModel = new AddressDisplayModel(province,city,county,0);
        addressDisplayModel.getAddressDisplayInfo(new IaddressDisplayCallback() {
            @Override
            public void callbackAddressDisplayInfo(String address,int position) {
                registerFirmActivityView.setStringInText(address);

            }
        });
    }
}
