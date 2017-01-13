package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.AnalysisFragmentMVP.presenter;

import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.AnalysisFragmentMVP.model.AnalysisFragmentCallback;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.AnalysisFragmentMVP.model.AnalysisFragmentModel;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.AnalysisFragmentMVP.model.IAnalysisFragmentModel;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.AnalysisFragmentMVP.view.IAnalysisFragmentView;

/**
 * Created by Jobs Created on 2016/12/27.
 */
public class AnalysisFragmentPresenter {
    IAnalysisFragmentView analysisFragmentView;

    public AnalysisFragmentPresenter(IAnalysisFragmentView analysisFragmentView) {
        this.analysisFragmentView = analysisFragmentView;
    }
//
    public void getAnalysisFragmentResult(){
        IAnalysisFragmentModel analysisFragmentModel = new AnalysisFragmentModel();
        analysisFragmentModel.getAnalysisFragmentResult(new AnalysisFragmentCallback() {
            @Override
            public void callbackAnalysisFragmentResult(String result) {
                analysisFragmentView.setAnalysisFragmentResult(result);
            }
        });
    }
}
