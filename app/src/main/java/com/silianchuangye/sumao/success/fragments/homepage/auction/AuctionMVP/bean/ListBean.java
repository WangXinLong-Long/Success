package com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/26.
 */
public class ListBean  implements Serializable{
    /**
     * allCount : 19
     * prod : [{"id":"prod1510002",
     * "startDate":1482722100000,
     * "cl_zongliang":100,
     * "cl_mingcheng":"兰化7042",
     * "status":"2","cl_qipai":10,
     * "cl_cangku":"保定库",
     * "endDate":1482722469060,
     * "cl_gongsi":"北京橡胶",
     * "type":"englishAuctionProduct",
     * "List":[]}]
     */

    private int allCount;
    private List<ProdBean> prod;

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public List<ProdBean> getProd() {
        return prod;
    }

    public void setProd(List<ProdBean> prod) {
        this.prod = prod;
    }

    public static class ProdBean implements Serializable {
        /**
         * id : prod1510002
         * startDate : 1482722100000
         * cl_zongliang : 100
         * cl_mingcheng : 兰化7042
         * status : 2
         * cl_qipai : 10
         * cl_cangku : 保定库
         * endDate : 1482722469060
         * cl_gongsi : 北京橡胶
         * type : englishAuctionProduct
         * List : []
         */

        private String id;
        private String startDate;
        private String cl_zongliang;
        private String cl_mingcheng;
        private String status;
        private String cl_qipai;
        private String cl_cangku;
        private String endDate;
        private String cl_gongsi;
        private String type;
        private List<?> List;

        public String getCl_qipai() {
            return cl_qipai;
        }

        public String getEndDate() {
            return endDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getCl_zongliang() {
            return cl_zongliang;
        }

        public void setCl_zongliang(String cl_zongliang) {
            this.cl_zongliang = cl_zongliang;
        }

        public void setCl_qipai(String cl_qipai) {
            this.cl_qipai = cl_qipai;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getCl_mingcheng() {
            return cl_mingcheng;
        }

        public void setCl_mingcheng(String cl_mingcheng) {
            this.cl_mingcheng = cl_mingcheng;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCl_cangku() {
            return cl_cangku;
        }

        public void setCl_cangku(String cl_cangku) {
            this.cl_cangku = cl_cangku;
        }


        public String getCl_gongsi() {
            return cl_gongsi;
        }

        public void setCl_gongsi(String cl_gongsi) {
            this.cl_gongsi = cl_gongsi;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<?> getList() {
            return List;
        }

        public void setList(List<?> List) {
            this.List = List;
        }
    }
}
