package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.model.IScoreModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.model.ScoreCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.model.ScoreModel;
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
            public void callbackScore() {
                scoreView.getScoreState();
            }
        });
    }
}
