package com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.bean;

import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/26.
 */
public class AuctionDetailBean implements Serializable {

    /**
     * cl_attribute : [{"attrName":"行业分类","attrValue":"null"},{"attrName":"熔体质量流动速率","attrValue":"null"},{"attrName":"拉伸屈服强度（Mpa）","attrValue":"null"},{"attrName":"拉伸断裂标称应变 %","attrValue":"null"}]
     */

    private List<GoodsInStockDetailBean.ClAttributeBean> cl_attribute;

    public List<GoodsInStockDetailBean.ClAttributeBean> getCl_attribute() {
        return cl_attribute;
    }

    public void setCl_attribute(List<GoodsInStockDetailBean.ClAttributeBean> cl_attribute) {
        this.cl_attribute = cl_attribute;
    }

    /**
     * cl_xbianjia : 1
     * startDate : 1485835200000
     * cl_jifen : 1
     * cl_cpid : sku1440057
     * overtimePeriod : 3
     * cl_gongsiId : 100001
     * contractTemplatePath : /mnt/template/auction_product.ftl
     * endDate : 1488211200000
     * cl_baozhj : 5.0%
     * cl_gongsi : 北京橡胶
     * addressDetail : 河北省保定市建国路440号
     * cl_fangshi : ["自提"]
     * cl_qigou : 1
     * freeBiddingTime : 0
     * info : sucess
     * cl_zongliang : 100.0
     * awardPriceRule : selfQuotation
     * depositDeadline : {"time":1485835200000}
     * cl_fenlei : HDPE
     * gradeNumber : DGDB-6097
     * yingyong : 薄膜料
     * triggerOvertimePeriod : 0
     * cl_xbianliang : 1
     * ddnOvertimePeriod : 3
     * cl_mingcheng : 大庆6097
     * contract : /mnt/template/auction_product.htm
     * status : 0
     * cl_id : prod1560070
     * country : 中国
     * U_company : 深圳前海钻库金融服务有限公司
     * cl_shijian : 1484064000000
     * cl_type : englishAuctionProduct
     * cl_shijianend : 1488211200000
     * manufacturer : 大庆石化
     * cl_qipai : 10
     * delayDateNode :
     * cl_diqu : 保定市
     * auctionRuleType : englishAuctionRule-allow-surplus
     * cl_cangku : 保定库
     * group : 中石油
     */

    private String cl_xbianjia;
    private String startDate;
    private String cl_jifen;
    private String cl_cpid;
    private String overtimePeriod;
    private String cl_gongsiId;
    private String contractTemplatePath;
    private String endDate;
    private String cl_baozhj;
    private String cl_gongsi;
    private String addressDetail;
    private String cl_qigou;
    private String freeBiddingTime;
    private String info;
    private String cl_zongliang;
    private String awardPriceRule;
    private DepositDeadlineBean depositDeadline;
    private String cl_fenlei;
    private String gradeNumber;
    private String yingyong;
    private String triggerOvertimePeriod;
    private String cl_xbianliang;
    private String ddnOvertimePeriod;
    private String cl_mingcheng;
    private String contract;
    private String status;
    private String cl_id;
    private String country;
    private String U_company;
    private String cl_shijian;
    private String cl_type;
    private String cl_shijianend;
    private String manufacturer;
    private String cl_qipai;
    private String delayDateNode;
    private String cl_diqu;
    private String auctionRuleType;
    private String cl_cangku;
    private String group;
    private String cl_cpbeizhu;
    private List<String> cl_fangshi;

    public String getCl_cpbeizhu() {
        return cl_cpbeizhu;
    }

    public void setCl_cpbeizhu(String cl_cpbeizhu) {
        this.cl_cpbeizhu = cl_cpbeizhu;
    }

    public String getCl_jifen() {
        return cl_jifen;
    }

    public void setCl_jifen(String cl_jifen) {
        this.cl_jifen = cl_jifen;
    }

    public String getCl_cpid() {
        return cl_cpid;
    }

    public void setCl_cpid(String cl_cpid) {
        this.cl_cpid = cl_cpid;
    }

    public String getCl_gongsiId() {
        return cl_gongsiId;
    }

    public void setCl_gongsiId(String cl_gongsiId) {
        this.cl_gongsiId = cl_gongsiId;
    }

    public String getContractTemplatePath() {
        return contractTemplatePath;
    }

    public void setContractTemplatePath(String contractTemplatePath) {
        this.contractTemplatePath = contractTemplatePath;
    }


    public String getCl_baozhj() {
        return cl_baozhj;
    }

    public void setCl_baozhj(String cl_baozhj) {
        this.cl_baozhj = cl_baozhj;
    }

    public String getCl_gongsi() {
        return cl_gongsi;
    }

    public void setCl_gongsi(String cl_gongsi) {
        this.cl_gongsi = cl_gongsi;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCl_zongliang() {
        return cl_zongliang;
    }

    public void setCl_zongliang(String cl_zongliang) {
        this.cl_zongliang = cl_zongliang;
    }

    public String getAwardPriceRule() {
        return awardPriceRule;
    }

    public void setAwardPriceRule(String awardPriceRule) {
        this.awardPriceRule = awardPriceRule;
    }

    public DepositDeadlineBean getDepositDeadline() {
        return depositDeadline;
    }

    public void setDepositDeadline(DepositDeadlineBean depositDeadline) {
        this.depositDeadline = depositDeadline;
    }

    public String getCl_fenlei() {
        return cl_fenlei;
    }

    public void setCl_fenlei(String cl_fenlei) {
        this.cl_fenlei = cl_fenlei;
    }

    public String getGradeNumber() {
        return gradeNumber;
    }

    public void setGradeNumber(String gradeNumber) {
        this.gradeNumber = gradeNumber;
    }

    public String getYingyong() {
        return yingyong;
    }

    public void setYingyong(String yingyong) {
        this.yingyong = yingyong;
    }


    public String getCl_mingcheng() {
        return cl_mingcheng;
    }

    public void setCl_mingcheng(String cl_mingcheng) {
        this.cl_mingcheng = cl_mingcheng;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCl_id() {
        return cl_id;
    }

    public void setCl_id(String cl_id) {
        this.cl_id = cl_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getU_company() {
        return U_company;
    }

    public void setU_company(String U_company) {
        this.U_company = U_company;
    }


    public String getCl_type() {
        return cl_type;
    }

    public void setCl_type(String cl_type) {
        this.cl_type = cl_type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDelayDateNode() {
        return delayDateNode;
    }

    public void setDelayDateNode(String delayDateNode) {
        this.delayDateNode = delayDateNode;
    }

    public String getCl_diqu() {
        return cl_diqu;
    }

    public void setCl_diqu(String cl_diqu) {
        this.cl_diqu = cl_diqu;
    }

    public String getAuctionRuleType() {
        return auctionRuleType;
    }

    public void setAuctionRuleType(String auctionRuleType) {
        this.auctionRuleType = auctionRuleType;
    }

    public String getCl_cangku() {
        return cl_cangku;
    }

    public void setCl_cangku(String cl_cangku) {
        this.cl_cangku = cl_cangku;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<String> getCl_fangshi() {
        return cl_fangshi;
    }

    public void setCl_fangshi(List<String> cl_fangshi) {
        this.cl_fangshi = cl_fangshi;
    }

    public String getCl_xbianjia() {
        return cl_xbianjia;
    }

    public void setCl_xbianjia(String cl_xbianjia) {
        this.cl_xbianjia = cl_xbianjia;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getOvertimePeriod() {
        return overtimePeriod;
    }

    public void setOvertimePeriod(String overtimePeriod) {
        this.overtimePeriod = overtimePeriod;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCl_qigou() {
        return cl_qigou;
    }

    public void setCl_qigou(String cl_qigou) {
        this.cl_qigou = cl_qigou;
    }

    public String getFreeBiddingTime() {
        return freeBiddingTime;
    }

    public void setFreeBiddingTime(String freeBiddingTime) {
        this.freeBiddingTime = freeBiddingTime;
    }

    public String getTriggerOvertimePeriod() {
        return triggerOvertimePeriod;
    }

    public void setTriggerOvertimePeriod(String triggerOvertimePeriod) {
        this.triggerOvertimePeriod = triggerOvertimePeriod;
    }

    public String getCl_xbianliang() {
        return cl_xbianliang;
    }

    public void setCl_xbianliang(String cl_xbianliang) {
        this.cl_xbianliang = cl_xbianliang;
    }

    public String getDdnOvertimePeriod() {
        return ddnOvertimePeriod;
    }

    public void setDdnOvertimePeriod(String ddnOvertimePeriod) {
        this.ddnOvertimePeriod = ddnOvertimePeriod;
    }

    public String getCl_shijian() {
        return cl_shijian;
    }

    public void setCl_shijian(String cl_shijian) {
        this.cl_shijian = cl_shijian;
    }

    public String getCl_shijianend() {
        return cl_shijianend;
    }

    public void setCl_shijianend(String cl_shijianend) {
        this.cl_shijianend = cl_shijianend;
    }

    public String getCl_qipai() {
        return cl_qipai;
    }

    public void setCl_qipai(String cl_qipai) {
        this.cl_qipai = cl_qipai;
    }

    public static class DepositDeadlineBean implements Serializable{
        /**
         * time : 1485835200000
         */

        private String time;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
