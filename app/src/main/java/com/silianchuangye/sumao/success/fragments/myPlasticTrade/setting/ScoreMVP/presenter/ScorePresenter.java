package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.HelpAndFeedbackMVP.bean.HelpAndFeedbackBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.model.IScoreModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.model.ScoreCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.model.ScoreModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.model.scoreInformationModel.IScoreInformationModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.model.scoreInformationModel.ScoreInformationCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.model.scoreInformationModel.ScoreInformationModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.view.IScoreView;

/**
 * Created by Administrator on 2016/9/27 0027.
 */
public class ScorePresenter {
    IScoreView scoreView;

    public ScorePresenter(IScoreView scoreView) {
        this.scoreView = scoreView;
    }

    public void sendScoreToServer(String number,String userName){
        IScoreModel scoreModel = new ScoreModel(number,userName);
        scoreModel.sendScoreInServer(new ScoreCallback() {
            @Override
            public void callbackScore(HelpAndFeedbackBean helpAndFeedbackBean) {
                scoreView.getScoreState( helpAndFeedbackBean);
            }
        });
    }
    public void scoreInformation(String userName){
        IScoreInformationModel scoreInformationModel = new ScoreInformationModel(userName);
        scoreInformationModel.getScoreInformation(new ScoreInformationCallback() {
            @Override
            public void callbackScoreInformation(String result) {
                scoreView.getScoreInfo(result);
            }
        });

    }


}
