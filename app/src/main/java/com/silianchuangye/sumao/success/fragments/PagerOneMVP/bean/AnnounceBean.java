package com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/17 0017.
 */
public class AnnounceBean implements Serializable {
    private List<AnnouncementBean> articles;

    public List<AnnouncementBean> getArticles() {
        return articles;
    }

    public void setArticles(List<AnnouncementBean> articles) {
        this.articles = articles;
    }
}
