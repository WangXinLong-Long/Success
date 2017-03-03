package com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.forgetPassword.phonePassword.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.forgetPassword.phonePassword.model.IPhonePasswordModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.forgetPassword.phonePassword.model.PhonePasswordCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.forgetPassword.phonePassword.model.PhonePasswordModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.forgetPassword.phonePassword.view.IPhonePasswordView;

/**
 * Created by Jobs Created on 2017/2/22.
 */
public class PhonePasswordPresenter {
    IPhonePasswordView phonePasswordView;

    public PhonePasswordPresenter(IPhonePasswordView phonePasswordView) {
        this.phonePasswordView = phonePasswordView;
    }

    public void getPasswordForgetPhone(String phoneNumber) {
        IPhonePasswordModel phonePasswordModel = new PhonePasswordModel(phoneNumber);
        phonePasswordModel.getPhonePasswordResult(new PhonePasswordCallback() {
            @Override
            public void callbackPhonePassword(String result) {
                phonePasswordView.getRegistrationInformation(result);
            }
        });
    }
}
