package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/14.
 */
public class PagerTwoDataListViewBean {

    /**
     * newsCount : 8
     * in : [{"dataname":"市场价格001","datatime":1482744194000,"dataid":"10343"},{"dataname":"装置动态001","datatime":1482743992000,"dataid":"10342"},{"dataname":"资源方价格001","datatime":1482743928000,"dataid":"10341"},{"dataname":"fdshdhs","datatime":1480990761000,"dataid":"10323"},{"dataname":"ggggggg","datatime":1480899103000,"dataid":"10322"},{"dataname":"sfgfdgsdgsdgfsdfgfdsgfsd","datatime":1480664798000,"dataid":"10321"},{"dataname":"点赞测试","datatime":1480036783000,"dataid":"10281"},{"dataname":"11月17日PP期货行情","datatime":1479454086000,"dataid":"10188"}]
     */

    private String newsCount;
    private List<InBean> in;


    public List<InBean> getIn() {
        return in;
    }

    public void setIn(List<InBean> in) {
        this.in = in;
    }

    public static class InBean implements Serializable{
        /**
         * dataname : 市场价格001
         * datatime : 1482744194000
         * dataid : 10343
         */

        private String dataname;
        private String datatime;
        private String dataid;

        public String getDataname() {
            return dataname;
        }

        public void setDataname(String dataname) {
            this.dataname = dataname;
        }

        public String getDatatime() {
            return datatime;
        }

        public void setDatatime(String datatime) {
            this.datatime = datatime;
        }

        public String getDataid() {
            return dataid;
        }

        public void setDataid(String dataid) {
            this.dataid = dataid;
        }
    }
}
