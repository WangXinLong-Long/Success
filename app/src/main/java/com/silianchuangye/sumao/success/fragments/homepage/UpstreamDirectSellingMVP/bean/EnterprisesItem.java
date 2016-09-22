package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public class EnterprisesItem implements Serializable {
    String sellerCompanyId;
    String sellerCompanyName;

    public String getSellerCompanyId() {
        return sellerCompanyId;
    }

    public void setSellerCompanyId(String sellerCompanyId) {
        this.sellerCompanyId = sellerCompanyId;
    }

    public String getSellerCompanyName() {
        return sellerCompanyName;
    }

    public void setSellerCompanyName(String sellerCompanyName) {
        this.sellerCompanyName = sellerCompanyName;
    }
}
