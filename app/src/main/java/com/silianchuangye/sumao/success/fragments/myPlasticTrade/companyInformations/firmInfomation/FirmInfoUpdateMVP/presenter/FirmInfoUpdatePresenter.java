package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.AddressDisplayModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.IAddressDisplayCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.IAddressDisplayModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.bean.FirmInfoUpdateActivityBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.model.FirmInfoUpdateCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.model.FirmInfoUpdateModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.model.IFirmInfoUpdateModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.model.valueModel.IValueModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.model.valueModel.ValueCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.model.valueModel.ValueModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.view.IFirmInfoUpdateView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.FirmInfoTypeActivityMVP.model.IEnterpriseInformationCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.FirmInfoTypeActivityMVP.model.IUserRegisterModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.FirmInfoTypeActivityMVP.model.UserRegisterModel;
import com.silianchuangye.sumao.success.model.DifferentTypes;
import com.silianchuangye.sumao.success.model.EnterpriseInformation;

import java.util.List;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public class FirmInfoUpdatePresenter {
    IFirmInfoUpdateView firmInfoUpdateView;

    public FirmInfoUpdatePresenter(IFirmInfoUpdateView firmInfoUpdateView) {
        this.firmInfoUpdateView = firmInfoUpdateView;
    }

    public void setDetailAddress(String province,String city,String county){
        IAddressDisplayModel addressDisplayModel = new AddressDisplayModel(province,city,county,0);
        addressDisplayModel.getAddressDisplayInfo(new IAddressDisplayCallback() {
            @Override
            public void callbackAddressDisplayInfo(String address,int position) {
                firmInfoUpdateView.setStringInText(address);

            }
        });
    }

}
