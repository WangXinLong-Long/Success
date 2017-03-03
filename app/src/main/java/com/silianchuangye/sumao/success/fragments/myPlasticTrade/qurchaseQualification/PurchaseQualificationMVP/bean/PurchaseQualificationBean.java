package com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.PurchaseQualificationMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2017/2/24.
 */
public class PurchaseQualificationBean implements Serializable{

    private List<OrgBean> org;

    public List<OrgBean> getOrg() {
        return org;
    }

    public void setOrg(List<OrgBean> org) {
        this.org = org;
    }

    public static class OrgBean implements Serializable{
        /**
         * id : iorg90003
         * name : 中石油西北—合成橡胶处
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
