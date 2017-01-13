package com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.bean;

import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2017/1/13.
 */
public class GroupBuyingDetailBean implements Serializable {

    /**
     * groupPercent : 0.8571428571
     * cl_jifen : 1
     * cl_cpid : sku1440064
     * cl_gongsiId : 100001
     * cl_baozhj : 5.0%
     * cl_gongsi : 北京橡胶
     * addressDetail : 清河北路440号
     * cl_fangshi : ["自提"]
     * utilDate : 2017-01-11 00:00:00
     * cl_jine : 5
     * cl_qigou : 20
     * cl_zongliang : 110.0
     * groupStartDate : 1484064000000
     * cl_fenlei : SBS胶
     * groupEndDate : 1484841600000
     * securedOfSection : saledOff
     * cl_xbianliang : 20
     * cl_mingcheng : 巴陵YH-791
     * contract : /mnt/template/auction_product.htm
     * section : 2
     * cl_id : prod1560077
     * cl_shijian : 1484150400000
     * cl_type : groupProduct
     * cl_shijianend : 1484323200000
     * cl_attribute : [{"attrName":"熔体质量流动速率","attrValue":"null"},{"attrName":"300%定伸应力（Mpa）","attrValue":"null"},{"attrName":"挥发分（%（m/m））","attrValue":"null"},{"attrName":"总苯乙烯含量","attrValue":"null"},{"attrName":"行业分类","attrValue":"null"},{"attrName":"拉伸强度（Mpa）","attrValue":"null"},{"attrName":"扯断伸长率（%）","attrValue":"null"}]
     * cl_diqu : 临沂市
     * cl_cangku : 中央储备粮临沂直属库
     * cl_guojia : 中国
     * sumAddQty : 60.0
     */

    private String groupPercent;
    private String cl_jifen;
    private String cl_cpid;
    private String cl_gongsiId;
    private String cl_baozhj;
    private String cl_gongsi;
    private String addressDetail;
    private String utilDate;
    private String cl_jine;
    private String cl_qigou;
    private String cl_zongliang;
    private String groupStartDate;
    private String cl_fenlei;
    private String groupEndDate;
    private String securedOfSection;
    private String cl_xbianliang;
    private String cl_mingcheng;
    private String contract;
    private String section;
    private String cl_id;
    private String cl_shijian;
    private String cl_type;
    private String cl_shijianend;
    private String cl_diqu;
    private String cl_cangku;
    private String cl_guojia;
    private String sumAddQty;
    private List<String> cl_fangshi;
    private List<GoodsInStockDetailBean.ClAttributeBean> cl_attribute;

    public String getGroupPercent() {
        return groupPercent;
    }

    public void setGroupPercent(String groupPercent) {
        this.groupPercent = groupPercent;
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

    public String getUtilDate() {
        return utilDate;
    }

    public void setUtilDate(String utilDate) {
        this.utilDate = utilDate;
    }


    public String getCl_zongliang() {
        return cl_zongliang;
    }

    public void setCl_zongliang(String cl_zongliang) {
        this.cl_zongliang = cl_zongliang;
    }

    public String getCl_fenlei() {
        return cl_fenlei;
    }

    public void setCl_fenlei(String cl_fenlei) {
        this.cl_fenlei = cl_fenlei;
    }

    public String getSecuredOfSection() {
        return securedOfSection;
    }

    public void setSecuredOfSection(String securedOfSection) {
        this.securedOfSection = securedOfSection;
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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCl_id() {
        return cl_id;
    }

    public void setCl_id(String cl_id) {
        this.cl_id = cl_id;
    }

    public String getCl_type() {
        return cl_type;
    }

    public void setCl_type(String cl_type) {
        this.cl_type = cl_type;
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

    public String getCl_guojia() {
        return cl_guojia;
    }

    public void setCl_guojia(String cl_guojia) {
        this.cl_guojia = cl_guojia;
    }

    public String getSumAddQty() {
        return sumAddQty;
    }

    public void setSumAddQty(String sumAddQty) {
        this.sumAddQty = sumAddQty;
    }

    public List<String> getCl_fangshi() {
        return cl_fangshi;
    }

    public void setCl_fangshi(List<String> cl_fangshi) {
        this.cl_fangshi = cl_fangshi;
    }

    public List<GoodsInStockDetailBean.ClAttributeBean> getCl_attribute() {
        return cl_attribute;
    }

    public void setCl_attribute(List<GoodsInStockDetailBean.ClAttributeBean> cl_attribute) {
        this.cl_attribute = cl_attribute;
    }

    public String getCl_jine() {
        return cl_jine;
    }

    public void setCl_jine(String cl_jine) {
        this.cl_jine = cl_jine;
    }

    public String getCl_qigou() {
        return cl_qigou;
    }

    public void setCl_qigou(String cl_qigou) {
        this.cl_qigou = cl_qigou;
    }

    public String getGroupStartDate() {
        return groupStartDate;
    }

    public void setGroupStartDate(String groupStartDate) {
        this.groupStartDate = groupStartDate;
    }

    public String getGroupEndDate() {
        return groupEndDate;
    }

    public void setGroupEndDate(String groupEndDate) {
        this.groupEndDate = groupEndDate;
    }

    public String getCl_xbianliang() {
        return cl_xbianliang;
    }

    public void setCl_xbianliang(String cl_xbianliang) {
        this.cl_xbianliang = cl_xbianliang;
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
}
