package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.SeeProductMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class SeeProductBean implements Serializable{
    private List<RecentlyViewedProduct> recentlyViewedProduct;

    public List<RecentlyViewedProduct> getRecentlyViewedProduct() {
        return recentlyViewedProduct;
    }

    public void setRecentlyViewedProduct(List<RecentlyViewedProduct> recentlyViewedProduct) {
        this.recentlyViewedProduct = recentlyViewedProduct;
    }
}
