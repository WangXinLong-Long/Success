package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhoneMVP.RegisterPhonePresenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhoneMVP.RegisterPhoneModel.IRegisterPhoneModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhoneMVP.RegisterPhoneModel.RegisterPhoneCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhoneMVP.RegisterPhoneModel.RegisterPhoneModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhoneMVP.RegisterPhoneView.IRegisterPhoneView;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class RegisterPhonePresenter {
    IRegisterPhoneView registerPhoneView ;

    public RegisterPhonePresenter(IRegisterPhoneView registerPhoneView) {
        this.registerPhoneView = registerPhoneView;
    }
    public void sendRegisterPhoneToServer(String telephone){
        IRegisterPhoneModel registerPhoneModel = new RegisterPhoneModel(telephone);
        registerPhoneModel.getRegisterPhoneState(new RegisterPhoneCallback() {
            @Override
            public void callbackRegisterPhone(String result) {
                registerPhoneView.getRegisterPhoneInfo( result);
            }
        });
    }

}
