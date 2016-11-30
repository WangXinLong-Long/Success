package com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/17 0017.
 */
public class AnnouncementBean implements Serializable {
   private String id;
   private String headline;
   private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
