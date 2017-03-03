package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean;

        import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class PreSaleDetailBean implements Serializable{
    private ArrayList<GoodsInStockDetailBean.ClAttributeBean> cl_attribute;

    /**
     * deliveryPeriodEnd : 1483113600000
     * cl_jifen : 1
     * cl_mingcheng : 上海041
     * contract : /mnt/template/fixed_product.htm
     * cl_baozhj : 3.0%
     * cl_ckdizhi : 河北省保定市建国路440号
     * cl_qigou : 1
     * cl_id : prod1190007
     * cl_qiye : 北京橡胶
     * cl_jhfangshi : ["自提","卖家配送"]
     * deliveryPeriodStart : 1483027200000
     * manufacturer : 上海石化
     * cl_fenlei : HDPE
     * cl_attribute : [{"attrName":"简支梁冲击强度 (KJ/m2)","attrValue":"null"},{"attrName":"氧化诱导时间(210℃/min)","attrValue":"null"},{"attrName":"行业分类","attrValue":"null"},{"attrName":"熔体质量流动速率","attrValue":"null"},{"attrName":"密度（g/cm3）","attrValue":"null"},{"attrName":"拉伸屈服强度（Mpa）","attrValue":"null"},{"attrName":"拉伸断裂标称应变 %","attrValue":"null"}]
     * cl_diqu : 保定市
     * cl_cangku : 保定库
     * cl_qiyeId : 100001
     * cl_shuliang : 100
     * cl_xiaobian : 1
     * cl_jiner : 5
     */

    private String deliveryPeriodEnd;
    private String cl_jifen;
    private String cl_mingcheng;
    private String contract;
    private String cl_baozhj;
    private String cl_ckdizhi;
    private String cl_qigou;
    private String cl_id;
    private String cl_qiye;
    private String deliveryPeriodStart;
    private String manufacturer;
    private String cl_fenlei;
    private String cl_diqu;
    private String cl_cangku;
    private String cl_qiyeId;
    private String cl_shuliang;
    private String cl_xiaobian;
    private String cl_jiner;
    private String cl_cpbeizhu;
    private String paymentEndDate;
    private String gradeNumber;
    private String yingyong;
    private String country;
    private String group;
    private List<String> cl_jhfangshi;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getCl_cpbeizhu() {
        return cl_cpbeizhu;
    }

    public void setCl_cpbeizhu(String cl_cpbeizhu) {
        this.cl_cpbeizhu = cl_cpbeizhu;
    }

    public String getPaymentEndDate() {
        return paymentEndDate;
    }

    public void setPaymentEndDate(String paymentEndDate) {
        this.paymentEndDate = paymentEndDate;
    }

    public ArrayList<GoodsInStockDetailBean.ClAttributeBean> getCl_attribute() {
        return cl_attribute;
    }

    public void setCl_attribute(ArrayList<GoodsInStockDetailBean.ClAttributeBean> cl_attribute) {
        this.cl_attribute = cl_attribute;
    }

    public String getDeliveryPeriodEnd() {
        return deliveryPeriodEnd;
    }

    public void setDeliveryPeriodEnd(String deliveryPeriodEnd) {
        this.deliveryPeriodEnd = deliveryPeriodEnd;
    }

    public String getCl_jifen() {
        return cl_jifen;
    }

    public void setCl_jifen(String cl_jifen) {
        this.cl_jifen = cl_jifen;
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

    public String getCl_baozhj() {
        return cl_baozhj;
    }

    public void setCl_baozhj(String cl_baozhj) {
        this.cl_baozhj = cl_baozhj;
    }

    public String getCl_ckdizhi() {
        return cl_ckdizhi;
    }

    public void setCl_ckdizhi(String cl_ckdizhi) {
        this.cl_ckdizhi = cl_ckdizhi;
    }

    public String getCl_qigou() {
        return cl_qigou;
    }

    public void setCl_qigou(String cl_qigou) {
        this.cl_qigou = cl_qigou;
    }

    public String getCl_id() {
        return cl_id;
    }

    public void setCl_id(String cl_id) {
        this.cl_id = cl_id;
    }

    public String getCl_qiye() {
        return cl_qiye;
    }

    public void setCl_qiye(String cl_qiye) {
        this.cl_qiye = cl_qiye;
    }

    public String getDeliveryPeriodStart() {
        return deliveryPeriodStart;
    }

    public void setDeliveryPeriodStart(String deliveryPeriodStart) {
        this.deliveryPeriodStart = deliveryPeriodStart;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCl_fenlei() {
        return cl_fenlei;
    }

    public void setCl_fenlei(String cl_fenlei) {
        this.cl_fenlei = cl_fenlei;
    }

    public String getCl_diqu() {
        return cl_diqu;
    }

    public void setCl_diqu(String cl_diqu) {
        this.cl_diqu = cl_diqu;
    }

    public String getCl_cangku() {
        return cl_cangku;
    }

    public void setCl_cangku(String cl_cangku) {
        this.cl_cangku = cl_cangku;
    }

    public String getCl_qiyeId() {
        return cl_qiyeId;
    }

    public void setCl_qiyeId(String cl_qiyeId) {
        this.cl_qiyeId = cl_qiyeId;
    }

    public String getCl_shuliang() {
        return cl_shuliang;
    }

    public void setCl_shuliang(String cl_shuliang) {
        this.cl_shuliang = cl_shuliang;
    }

    public String getCl_xiaobian() {
        return cl_xiaobian;
    }

    public void setCl_xiaobian(String cl_xiaobian) {
        this.cl_xiaobian = cl_xiaobian;
    }

    public String getCl_jiner() {
        return cl_jiner;
    }

    public void setCl_jiner(String cl_jiner) {
        this.cl_jiner = cl_jiner;
    }

    public List<String> getCl_jhfangshi() {
        return cl_jhfangshi;
    }

    public void setCl_jhfangshi(List<String> cl_jhfangshi) {
        this.cl_jhfangshi = cl_jhfangshi;
    }
}
