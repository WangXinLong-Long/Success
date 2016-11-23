package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2016/11/21.
 */
public class DirectSellingBean implements Serializable {

    /**
     * allCount : 3
     * productItem : [{"salerEnterpriseName":"中国石油天然气股份有限公司西北化工销售分公司","wareHouse":"上海优月库","skuId":"sku960007","quantity":1000,"type":"forward-pricing-product","productName":"上海DJ210","productId":"prod1030006"},{"salerEnterpriseName":"中国石油天然气股份有限公司西北化工销售分公司","wareHouse":"中油台州库","skuId":"sku920013","quantity":1234,"type":"fixedProduct","productName":"三菱M7026U","productId":"prod990011"},{"salerEnterpriseName":"中国石油天然气股份有限公司西北化工销售分公司","wareHouse":"上海安石仓库","skuId":"sku820004","quantity":9999.667,"type":"fixedProduct","productName":"LG ES18002","productId":"prod890003"}]
     */

    private String allCount;
    private List<ProductItemBean> productItem;

    public String getAllCount() {
        return allCount;
    }

    public void setAllCount(String allCount) {
        this.allCount = allCount;
    }

    public List<ProductItemBean> getProductItem() {
        return productItem;
    }

    public void setProductItem(List<ProductItemBean> productItem) {
        this.productItem = productItem;
    }

}
