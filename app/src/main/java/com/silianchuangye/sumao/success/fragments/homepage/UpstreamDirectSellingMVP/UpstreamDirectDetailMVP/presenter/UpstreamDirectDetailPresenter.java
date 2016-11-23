package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.presenter;

import android.util.Log;

import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.bean.DirectSellingBean;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.model.DirectSellingCallback;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.model.DirectSellingModel;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.model.IDirectSellingModel;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.view.IUpstreamDirectDetailView;
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
        LogUtils.log("Jobs Created 上游直销控制中心");
        IDirectSellingModel directSellingModel = new DirectSellingModel(pageNum,sellerCompanyId,_dynSessConf);
        directSellingModel.getDirecSellingInfo(new DirectSellingCallback() {
            @Override
            public void callbackDirectSelling(DirectSellingBean directSellingBean) {

//                Jobs Created 上游直销控制中心
                LogUtils.log("Jobs Created 上游直销控制中心回调方法");
                upstreamDirectDetailView.setUpstreanDirectDataInView(directSellingBean);
            }
        });
    }
}
