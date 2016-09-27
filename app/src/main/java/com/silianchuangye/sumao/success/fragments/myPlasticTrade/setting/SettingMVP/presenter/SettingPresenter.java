package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.SettingMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.SettingMVP.bean.FunctionIntroductionBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.SettingMVP.model.ISettingModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.SettingMVP.model.SettingCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.SettingMVP.model.SettingModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.SettingMVP.view.ISettingView;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public class SettingPresenter {
    ISettingView settingView;

    public SettingPresenter(ISettingView settingView) {
        this.settingView = settingView;
    }

    public void getFunctionIntroduction(){
        ISettingModel settingModel = new SettingModel();
        settingModel.getSettingInfo(new SettingCallback() {
            @Override
            public void callbackSetting(FunctionIntroductionBean functionIntroductionBean) {
                settingView.sendInfoInFunctionIntroduction( functionIntroductionBean);
            }
        });
    }
}
