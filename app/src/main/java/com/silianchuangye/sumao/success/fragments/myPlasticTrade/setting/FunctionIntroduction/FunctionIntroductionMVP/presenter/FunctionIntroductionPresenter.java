package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.FunctionIntroduction.FunctionIntroductionMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.FunctionIntroduction.FunctionIntroductionMVP.bean.FunctionIntroductionDetailBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.FunctionIntroduction.FunctionIntroductionMVP.model.FunctionIntroductionCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.FunctionIntroduction.FunctionIntroductionMVP.model.FunctionIntroductionModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.FunctionIntroduction.FunctionIntroductionMVP.model.IFunctionIntroductionModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.FunctionIntroduction.FunctionIntroductionMVP.view.IFunctionIntroductionView;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public class FunctionIntroductionPresenter {
    IFunctionIntroductionView functionIntroductionView ;

    public FunctionIntroductionPresenter(IFunctionIntroductionView functionIntroductionView) {
        this.functionIntroductionView = functionIntroductionView;
    }

    public void getFunctionIntroductionInfo(String helpid){
        IFunctionIntroductionModel functionIntroductionModel = new FunctionIntroductionModel(helpid);
        functionIntroductionModel.getFunctionIntroductionDetailInfo(new FunctionIntroductionCallback() {
            @Override
            public void callbackFunctionIntroduction(FunctionIntroductionDetailBean functionIntroductionDetailBean) {
                functionIntroductionView.sendFunctionIntroductionDetailInActivity( functionIntroductionDetailBean);
            }
        });

    }
}
