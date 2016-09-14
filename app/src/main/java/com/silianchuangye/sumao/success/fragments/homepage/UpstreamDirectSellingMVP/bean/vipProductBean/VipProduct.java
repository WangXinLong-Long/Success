package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.vipProductBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public class VipProduct implements Serializable{
    String productName;
    String productID;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

}
