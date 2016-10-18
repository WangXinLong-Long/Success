package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.HelpAndFeedbackMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.HelpAndFeedbackMVP.bean.HelpAndFeedbackBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.HelpAndFeedbackMVP.model.HelpAndFeedbackCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.HelpAndFeedbackMVP.model.HelpAndFeedbackModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.HelpAndFeedbackMVP.model.IHelpAndFeedbackModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.HelpAndFeedbackMVP.view.IHelpAndFeedbackView;

/**
 * Created by Administrator on 2016/9/27 0027.
 */
public class HelpAndFeedbackPresenter {
    IHelpAndFeedbackView helpAndFeedbackView;

    public HelpAndFeedbackPresenter(IHelpAndFeedbackView helpAndFeedbackView) {
        this.helpAndFeedbackView = helpAndFeedbackView;
    }

    public void sendMessageInServer(String telephone,String message,String userName){
        IHelpAndFeedbackModel helpAndFeedbackModel = new HelpAndFeedbackModel(telephone,message,userName);
        helpAndFeedbackModel.sendMessageHelpAndFeedback(new HelpAndFeedbackCallback() {
            @Override
            public void callbackHelpAndFeedback(HelpAndFeedbackBean helpAndFeedbackBean) {
                helpAndFeedbackView.getResultInActivity( helpAndFeedbackBean);
            }
        });
    }
}
