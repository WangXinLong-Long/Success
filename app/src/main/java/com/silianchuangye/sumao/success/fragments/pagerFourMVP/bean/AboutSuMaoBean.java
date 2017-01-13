package com.silianchuangye.sumao.success.fragments.pagerFourMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/27.
 */
public class AboutSuMaoBean implements Serializable {

    private List<NewsItemBean> newsItem;

    public List<NewsItemBean> getNewsItem() {
        return newsItem;
    }

    public void setNewsItem(List<NewsItemBean> newsItem) {
        this.newsItem = newsItem;
    }

    public static class NewsItemBean implements Serializable{
        /**
         * id : 10189
         * caption : 中国石油首创“脱硫不排渣”新技术  LDS技术总体达国际领先水平
         */

        private String id;
        private String caption;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }
    }
}
