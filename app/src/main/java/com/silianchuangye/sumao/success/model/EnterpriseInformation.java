package com.silianchuangye.sumao.success.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/26 0026.
 */
public class EnterpriseInformation implements Serializable{
    String id;
    String displayName;

    public EnterpriseInformation( String displayName,String id) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
