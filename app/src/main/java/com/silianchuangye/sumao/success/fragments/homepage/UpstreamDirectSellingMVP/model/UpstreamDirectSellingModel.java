package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.model;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class UpstreamDirectSellingModel implements IUpstreamDirectSellingModel {
    @Override
    public void getUpstreamDirectSellingInfo(UpstreamDirectSellingCallback callback) {
        callback.callbackUpstreamDirectSelling();
    }
}
