package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.FirmInfoTypeActivityMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.FirmInfoTypeActivityMVP.model.IEnterpriseInformationCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.FirmInfoTypeActivityMVP.model.IUserRegisterModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.FirmInfoTypeActivityMVP.model.UserRegisterModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.FirmInfoTypeActivityMVP.view.IFirmInfoTypeActivity;
import com.silianchuangye.sumao.success.model.EnterpriseInformation;

import java.util.List;

/**
 * Created by Administrator on 2016/7/26 0026.
 */
public class GetEntInformationPresenter {
    IUserRegisterModel userRegisterModel = new UserRegisterModel();
    IFirmInfoTypeActivity firmInfoTypeActivity;

    public GetEntInformationPresenter(IFirmInfoTypeActivity firmInfoTypeActivity) {
        this.firmInfoTypeActivity = firmInfoTypeActivity;
    }

    public void setDataInView(int i) {

        userRegisterModel.getEntInfo(i,new IEnterpriseInformationCallback() {
            @Override
            public void setData(List<EnterpriseInformation> informations) {
                firmInfoTypeActivity.initFirmInfoTypeActivityView(informations);
            }
        });

    }

    ;
}
