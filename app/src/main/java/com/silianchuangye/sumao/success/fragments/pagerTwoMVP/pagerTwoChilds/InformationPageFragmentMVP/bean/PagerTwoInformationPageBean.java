package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/27.
 */
public class PagerTwoInformationPageBean implements Serializable {

    private List<InBean> in;

    public List<InBean> getIn() {
        return in;
    }

    public void setIn(List<InBean> in) {
        this.in = in;
    }

    public static class InBean implements Serializable{
        /**
         * newsfrom : 中国石油新闻中心
         * newsname : 中国石油首创“脱硫不排渣”新技术  LDS技术总体达国际领先水平
         * newstime : 1480423232000
         * newspri : http://192.168.32.107/image/20161118/20161118155657_765.jpg
         * newsid : 10189
         * newsnum : 20
         */

        private String newsfrom;
        private String newsname;
        private String newstime;
        private String newspri;
        private String newsid;
        private String newsnum;
        private String thumupCount;

        public String getThumupCount() {
            return thumupCount;
        }

        public void setThumupCount(String thumupCount) {
            this.thumupCount = thumupCount;
        }

        public String getNewsfrom() {
            return newsfrom;
        }

        public void setNewsfrom(String newsfrom) {
            this.newsfrom = newsfrom;
        }

        public String getNewsname() {
            return newsname;
        }

        public void setNewsname(String newsname) {
            this.newsname = newsname;
        }

        public String getNewspri() {
            return newspri;
        }

        public void setNewspri(String newspri) {
            this.newspri = newspri;
        }

        public String getNewsid() {
            return newsid;
        }

        public void setNewsid(String newsid) {
            this.newsid = newsid;
        }

        public String getNewstime() {
            return newstime;
        }

        public void setNewstime(String newstime) {
            this.newstime = newstime;
        }

        public String getNewsnum() {
            return newsnum;
        }

        public void setNewsnum(String newsnum) {
            this.newsnum = newsnum;
        }
    }
}
