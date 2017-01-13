package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.presenter;

import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.model.IInformationPageFragmentModel;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.model.InformationPageFragmentCallback;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.model.InformationPageFragmentModel;
import com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.view.IInformationPageFragmentView;

/**
 * Created by Jobs Created on 2016/12/26.
 */
public class InformationPageFragmentPresenter {
    IInformationPageFragmentView iInformationPageFragmentView;

    public InformationPageFragmentPresenter(IInformationPageFragmentView iInformationPageFragmentView) {
        this.iInformationPageFragmentView = iInformationPageFragmentView;
    }

//    获取页面中的数据
    public void getInformationPageFragmentData(){
        IInformationPageFragmentModel iInformationPageFragmentModel = new InformationPageFragmentModel();
        iInformationPageFragmentModel.getInformationPageFragmentResult(new InformationPageFragmentCallback() {
            @Override
            public void callbackInformationPageFragmentResult(String result) {
                iInformationPageFragmentView.setInformationPageFragmentData(result);
            }
        });
    }
}
