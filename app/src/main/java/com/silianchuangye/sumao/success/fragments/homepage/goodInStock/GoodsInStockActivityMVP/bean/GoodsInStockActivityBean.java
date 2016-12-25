package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class GoodsInStockActivityBean implements Serializable {

    /**
     * status : YES
     * code : 200
     * in : [{"in_leixing":"现货","in_id":"prod940003","in_qiye":"北京橡胶","in_prix":222,"in_name":"SABIC 511MK10T","in_cangku":"上游配送库-华北","in_cpid":"sku870003","in_num":34},{"in_leixing":"现货","in_id":"prod1180009","in_qiye":"北京橡胶","in_prix":10,"in_name":"SABIC 511MK10T","in_cangku":"上游配送库-东北","in_cpid":"sku1090010","in_num":96}]
     *
     * info : 返回数据成功
     */

    private String status;
    private String code;
    private String info;
    private List<InBean> in;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<InBean> getIn() {
        return in;
    }

    public void setIn(List<InBean> in) {
        this.in = in;
    }

    public static class InBean implements Serializable{
        /**
         * in_leixing : 现货
         * in_id : prod940003
         * in_qiye : 北京橡胶
         * in_prix : 222
         * in_name : SABIC 511MK10T
         * in_cangku : 上游配送库-华北
         * in_cpid : sku870003
         * in_num : 34
         */

        private String in_leixing;
        private String in_id;
        private String in_qiye;
        private String in_prix;
        private String in_name;
        private String in_cangku;
        private String in_cpid;
        private String in_num;

        public String getIn_leixing() {
            return in_leixing;
        }

        public void setIn_leixing(String in_leixing) {
            this.in_leixing = in_leixing;
        }

        public String getIn_id() {
            return in_id;
        }

        public void setIn_id(String in_id) {
            this.in_id = in_id;
        }

        public String getIn_qiye() {
            return in_qiye;
        }

        public void setIn_qiye(String in_qiye) {
            this.in_qiye = in_qiye;
        }


        public String getIn_name() {
            return in_name;
        }

        public void setIn_name(String in_name) {
            this.in_name = in_name;
        }

        public String getIn_cangku() {
            return in_cangku;
        }

        public void setIn_cangku(String in_cangku) {
            this.in_cangku = in_cangku;
        }

        public String getIn_cpid() {
            return in_cpid;
        }

        public void setIn_cpid(String in_cpid) {
            this.in_cpid = in_cpid;
        }

        public String getIn_prix() {
            return in_prix;
        }

        public void setIn_prix(String in_prix) {
            this.in_prix = in_prix;
        }

        public String getIn_num() {
            return in_num;
        }

        public void setIn_num(String in_num) {
            this.in_num = in_num;
        }
    }
}
