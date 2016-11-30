package com.silianchuangye.sumao.success.fragments.SearchActivityMVP.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/16 0016.
 */
public class SearchActivityBean implements Serializable {
    private Map<String,String> region;
    private String total;
    private Map<String,String> application;
    private Map<String,String> sort;
    private List<Cls> cl;
    private Map<String,String> type;

    public Map<String, String> getRegion() {
        return region;
    }

    public void setRegion(Map<String, String> region) {
        this.region = region;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Map<String, String> getApplication() {
        return application;
    }

    public void setApplication(Map<String, String> application) {
        this.application = application;
    }

    public Map<String, String> getSort() {
        return sort;
    }

    public void setSort(Map<String, String> sort) {
        this.sort = sort;
    }

    public List<Cls> getCl() {
        return cl;
    }

    public void setCl(List<Cls> cl) {
        this.cl = cl;
    }

    public Map<String, String> getType() {
        return type;
    }

    public void setType(Map<String, String> type) {
        this.type = type;
    }
}
