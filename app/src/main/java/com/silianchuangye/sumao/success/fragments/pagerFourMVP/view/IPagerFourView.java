package com.silianchuangye.sumao.success.fragments.pagerFourMVP.view;

import com.silianchuangye.sumao.success.fragments.pagerFourMVP.pagerFourDetail.bean.PagerFourDetailBean;

/**
 * Created by Jobs Created on 2016/12/27.
 */
public interface IPagerFourView {
//    关于塑贸的内容
    void setPagerFourAboutSuMaoResult(String result);
//      客服指南的内容
    void setPagerFourCustomerServiceGuideResult(String result);

//    获取详情页的内容
    void setPagerFourDetailResult( PagerFourDetailBean pagerFourDetailBean);
}
