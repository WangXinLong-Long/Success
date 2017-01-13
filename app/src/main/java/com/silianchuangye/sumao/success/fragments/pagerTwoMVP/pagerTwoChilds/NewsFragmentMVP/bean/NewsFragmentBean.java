package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.NewsFragmentMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/27.
 */
public class NewsFragmentBean implements Serializable{

    /**
     * newsCount : 3
     * in : [{"in_xwid":"10189","in_xwtime":1480423232000,"in_xwbt":"中国石油首创\u201c脱硫不排渣\u201d新技术  LDS技术总体达国际领先水平"},{"in_xwid":"10307","in_xwtime":1480385753000,"in_xwbt":"bbb"},{"in_xwid":"10306","in_xwtime":1480385567000,"in_xwbt":"aaaa"}]
     */

    private int newsCount;
    private List<InBean> in;

    public int getNewsCount() {
        return newsCount;
    }

    public void setNewsCount(int newsCount) {
        this.newsCount = newsCount;
    }

    public List<InBean> getIn() {
        return in;
    }

    public void setIn(List<InBean> in) {
        this.in = in;
    }

    public static class InBean implements Serializable{
        /**
         * in_xwid : 10189
         * in_xwtime : 1480423232000
         * in_xwbt : 中国石油首创“脱硫不排渣”新技术  LDS技术总体达国际领先水平
         */

        private String in_xwid;
        private String in_xwtime;
        private String in_xwbt;

        public String getIn_xwid() {
            return in_xwid;
        }

        public void setIn_xwid(String in_xwid) {
            this.in_xwid = in_xwid;
        }

        public String getIn_xwtime() {
            return in_xwtime;
        }

        public void setIn_xwtime(String in_xwtime) {
            this.in_xwtime = in_xwtime;
        }

        public String getIn_xwbt() {
            return in_xwbt;
        }

        public void setIn_xwbt(String in_xwbt) {
            this.in_xwbt = in_xwbt;
        }
    }
}
