package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
public class Sku  implements Serializable{
    private String skuId;
    private String cl_date;
    private String cl_jiner;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getCl_date() {
        return cl_date;
    }

    public void setCl_date(String cl_date) {
        this.cl_date = cl_date;
    }

    public String getCl_jiner() {
        return cl_jiner;
    }

    public void setCl_jiner(String cl_jiner) {
        this.cl_jiner = cl_jiner;
    }
}
