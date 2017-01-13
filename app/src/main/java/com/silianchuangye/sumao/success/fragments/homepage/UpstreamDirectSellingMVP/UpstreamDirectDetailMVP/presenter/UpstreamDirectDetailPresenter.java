package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.presenter;

import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.bean.DirectSellingBean;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.model.DirectSellingCallback;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.model.DirectSellingModel;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.model.IDirectSellingModel;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.view.IUpstreamDirectDetailView;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.model.AuctionSessionCallback;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.model.AuctionSessionModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.model.IAuctionSessionModel;
import com.silianchuangye.sumao.success.utils.LogUtils;

/**
 * Created by Jobs Created on 2016/11/18.
 */
public class UpstreamDirectDetailPresenter {
    IUpstreamDirectDetailView upstreamDirectDetailView;

    public UpstreamDirectDetailPresenter(IUpstreamDirectDetailView upstreamDirectDetailView) {
        this.upstreamDirectDetailView = upstreamDirectDetailView;
    }

    public void getDirectSelling(String pageNum, String sellerCompanyId, String _dynSessConf){
        IDirectSellingModel directSellingModel = new DirectSellingModel(pageNum,sellerCompanyId,_dynSessConf);
        directSellingModel.getDirecSellingInfo(new DirectSellingCallback() {
            @Override
            public void callbackDirectSelling(DirectSellingBean directSellingBean) {
                LogUtils.log("Jobs Created 上游直销控制中心回调方法");
                upstreamDirectDetailView.setUpstreanDirectDataInView(directSellingBean);
            }
        });
    }

    public void getAuctionSessionResult(String pageNum, String sellerCompanyId, String _dynSessConf){
        IAuctionSessionModel auctionSessionModel = new AuctionSessionModel( pageNum,  sellerCompanyId,  _dynSessConf);
        auctionSessionModel.getAuctionSessionResult(new AuctionSessionCallback() {
            @Override
            public void callbackAuctionSession(String result) {
                upstreamDirectDetailView.getAuctionSessionResult(result);
            }
        });
    }
}
