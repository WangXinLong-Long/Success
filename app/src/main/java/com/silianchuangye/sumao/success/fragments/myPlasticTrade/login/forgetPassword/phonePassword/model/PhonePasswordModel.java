package com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.forgetPassword.phonePassword.model;

/**
 * Created by Jobs Created on 2017/2/22.
 */
public class PhonePasswordModel implements IPhonePasswordModel {
    String phoneNumber;

    public PhonePasswordModel(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void getPhonePasswordResult(PhonePasswordCallback callback) {
        callback.callbackPhonePassword("result");
    }
}
