package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
public class PreSaleDetailCalendarBean implements Serializable {
    private String cl_mingcheng;
    private String cl_cangku;
    private List<Sku> sku;
    private String cl_qiye;
    private String productId;

    public String getCl_mingcheng() {
        return cl_mingcheng;
    }

    public void setCl_mingcheng(String cl_mingcheng) {
        this.cl_mingcheng = cl_mingcheng;
    }

    public String getCl_cangku() {
        return cl_cangku;
    }

    public void setCl_cangku(String cl_cangku) {
        this.cl_cangku = cl_cangku;
    }

    public List<Sku> getSku() {
        return sku;
    }

    public void setSku(List<Sku> sku) {
        this.sku = sku;
    }

    public String getCl_qiye() {
        return cl_qiye;
    }

    public void setCl_qiye(String cl_qiye) {
        this.cl_qiye = cl_qiye;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
