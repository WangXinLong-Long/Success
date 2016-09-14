package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public class UpstreamDirectorySellingBean implements Serializable {
    List<EnterprisesItem> enterprisesItem;
    String info;

    public List<EnterprisesItem> getEnterprisesItem() {
        return enterprisesItem;
    }

    public void setEnterprisesItem(List<EnterprisesItem> enterprisesItem) {
        this.enterprisesItem = enterprisesItem;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
