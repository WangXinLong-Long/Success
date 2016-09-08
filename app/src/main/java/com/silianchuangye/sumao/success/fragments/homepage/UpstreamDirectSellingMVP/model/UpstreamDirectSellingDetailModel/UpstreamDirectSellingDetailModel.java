package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.model.UpstreamDirectSellingDetailModel;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class UpstreamDirectSellingDetailModel implements IUpstreamDirectSellingDetailModel {
    @Override
    public void getUpstreamDirectSellingDetailInfo(UpstreamDirectSellingDetailCallback callback) {
        callback.callbackIUpstreamDirectSellingDetail();
    }
}
