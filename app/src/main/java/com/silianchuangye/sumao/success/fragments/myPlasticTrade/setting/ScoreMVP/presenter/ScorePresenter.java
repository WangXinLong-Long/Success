package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.HelpAndFeedbackMVP.bean.HelpAndFeedbackBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.model.IScoreModels;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.model.ScoreCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.model.ScoreModels;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.model.scoreInformationModel.IScoreInformationModels;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.model.scoreInformationModel.ScoreInformationCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.model.scoreInformationModel.ScoreInformationModels;
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
        IScoreModels scoreModel = new ScoreModels(number,userName);
        scoreModel.sendScoreInServer(new ScoreCallback() {
            @Override
            public void callbackScore(HelpAndFeedbackBean helpAndFeedbackBean) {
                scoreView.getScoreState( helpAndFeedbackBean);
            }
        });
    }
    public void scoreInformation(String userName){
        IScoreInformationModels scoreInformationModel = new ScoreInformationModels(userName);
        scoreInformationModel.getScoreInformation(new ScoreInformationCallback() {
            @Override
            public void callbackScoreInformation(String result) {
                scoreView.getScoreInfo(result);
            }
        });

    }


}
