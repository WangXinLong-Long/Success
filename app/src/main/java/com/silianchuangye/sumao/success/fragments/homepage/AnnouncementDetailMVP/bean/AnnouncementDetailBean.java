package com.silianchuangye.sumao.success.fragments.homepage.AnnouncementDetailMVP.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/18 0018.
 */
public class AnnouncementDetailBean implements Serializable{
    String content;
    String headline;
    String author;
    String creationDate;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
