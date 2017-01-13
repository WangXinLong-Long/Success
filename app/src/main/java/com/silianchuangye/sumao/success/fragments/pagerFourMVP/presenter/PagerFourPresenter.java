package com.silianchuangye.sumao.success.fragments.pagerFourMVP.presenter;

import com.silianchuangye.sumao.success.fragments.pagerFourMVP.model.aboutSuMao.IPagerFourAboutSuMaoModel;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.model.aboutSuMao.PagerFourAboutSuMaoCallback;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.model.aboutSuMao.PagerFourAboutSuMaoModel;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.model.customerServiceGuide.IPagerFourCustomerServiceGuideModel;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.model.customerServiceGuide.PagerFourCustomerServiceGuideCallback;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.model.customerServiceGuide.PagerFourCustomerServiceGuideModel;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.model.pagerFourDetail.IPagerFourDetailModel;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.model.pagerFourDetail.PagerFourDetailCallback;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.model.pagerFourDetail.PagerFourDetailModel;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.pagerFourDetail.bean.PagerFourDetailBean;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.view.IPagerFourView;

/**
 * Created by Jobs Created on 2016/12/27.
 */
public class PagerFourPresenter {
    IPagerFourView pagerFourView;

    public PagerFourPresenter(IPagerFourView pagerFourView) {
        this.pagerFourView = pagerFourView;
    }

//    获取关于塑贸下的内容
    public void getPagerFourAboutSuMaoResult(){
        IPagerFourAboutSuMaoModel pagerFourAboutSuMaoModel = new PagerFourAboutSuMaoModel();
        pagerFourAboutSuMaoModel.getPagerFourAboutSuMaoResult(new PagerFourAboutSuMaoCallback() {
            @Override
            public void callbackPagerFourResult(String result) {
                pagerFourView.setPagerFourAboutSuMaoResult(result);
            }
        });
    }

   //    获取客服帮助下的内容
    public void getPagerFourCustomerServiceGuideResult(){
        IPagerFourCustomerServiceGuideModel pagerFourCustomerServiceGuideModel = new PagerFourCustomerServiceGuideModel();
        pagerFourCustomerServiceGuideModel.getPagerFourCustomerServiceGuideResult(new PagerFourCustomerServiceGuideCallback() {
            @Override
            public void callbackPagerFourCustomerServiceGuide(String result) {
                pagerFourView.setPagerFourCustomerServiceGuideResult(result);
            }
        });
    }

    public void getPagerFourDetailResult(String newsId){
        IPagerFourDetailModel pagerFourDetailModel = new PagerFourDetailModel(newsId);
        pagerFourDetailModel.getPagerFourDetailResult(new PagerFourDetailCallback() {
            @Override
            public void callbackPagerFourDetail( PagerFourDetailBean pagerFourDetailBean) {
                pagerFourView.setPagerFourDetailResult(  pagerFourDetailBean);
            }
        });
    }


}
