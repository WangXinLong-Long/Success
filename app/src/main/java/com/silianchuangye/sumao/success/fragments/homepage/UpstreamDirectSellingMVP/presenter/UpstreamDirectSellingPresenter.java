package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.presenter;

import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.model.UpstreamDirectSellingDetailModel.IUpstreamDirectSellingDetailModel;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.model.UpstreamDirectSellingDetailModel.UpstreamDirectSellingDetailCallback;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.model.UpstreamDirectSellingDetailModel.UpstreamDirectSellingDetailModel;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.view.IUpstreamDirectSellingView;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class UpstreamDirectSellingPresenter {
//    UpstreamDirectSellingDetailModel
IUpstreamDirectSellingView upstreamDirectSellingView ;

    public UpstreamDirectSellingPresenter(IUpstreamDirectSellingView upstreamDirectSellingView) {
        this.upstreamDirectSellingView = upstreamDirectSellingView;
    }

    public void getUpstreamDirectSellingDetail(){
        IUpstreamDirectSellingDetailModel upstreamDirectSellingDetailModel = new UpstreamDirectSellingDetailModel();
        upstreamDirectSellingDetailModel.getUpstreamDirectSellingDetailInfo(new UpstreamDirectSellingDetailCallback() {
            @Override
            public void callbackIUpstreamDirectSellingDetail() {
                upstreamDirectSellingView.getUpstreamDirectSellingDetailInfo();
            }
        });
    }
}
