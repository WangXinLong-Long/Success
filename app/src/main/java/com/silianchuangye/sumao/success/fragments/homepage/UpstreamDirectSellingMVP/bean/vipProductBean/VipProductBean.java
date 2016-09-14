package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.vipProductBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public class VipProductBean implements Serializable {
    List<VipProduct> vipProduct;
    String info;

    public List<VipProduct> getVipProduct() {
        return vipProduct;
    }

    public void setVipProduct(List<VipProduct> vipProduct) {
        this.vipProduct = vipProduct;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
