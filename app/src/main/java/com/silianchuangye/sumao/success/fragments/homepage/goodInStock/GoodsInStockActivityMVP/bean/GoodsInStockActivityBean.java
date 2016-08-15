package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean;

import com.google.gson.JsonArray;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.SMCl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class GoodsInStockActivityBean implements Serializable {
    private Map<String,String> region;
    private String total;
    private Map<String,String> application;
    private Map<String,String> sort;
    private List<SMCl> cl;

    public Map getRegion() {
        return region;
    }

    public void setRegion(Map region) {
        this.region = region;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Map getApplication() {
        return application;
    }

    public void setApplication(Map application) {
        this.application = application;
    }

    public Map getSort() {
        return sort;
    }

    public void setSort(Map sort) {
        this.sort = sort;
    }

    public List<SMCl> getCl() {
        return cl;
    }

    public void setCl(List<SMCl> cl) {
        this.cl = cl;
    }
}
