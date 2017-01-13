package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.view;

import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.bean.DirectSellingBean;

/**
 * Created by Jobs Created on 2016/11/18.
 */
public interface IUpstreamDirectDetailView {
//    获取现货直销的数据
    void setUpstreanDirectDataInView(DirectSellingBean directSellingBean);

    //    获取竞拍专场的数据
    void getAuctionSessionResult(String result);
}
