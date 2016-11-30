package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/13 0013.
 */
public class GoodsInStockDetailBean implements Serializable{
    private String mstEbsSkuInfoId;
    private RelatedProduct relatedProducts;
    private String cl_jifen;
    private String cl_cpid;
    private String cl_hyfenlei;
    private String cl_gongsiId;

    private String cl_gongsi;
    private String cl_qigou;
    private String cl_jine;
    private String cl_fenlei;
    private String cl_paihao;
    private String cl_shuliang;
    private String cl_xiaobian;
    private String cl_mingcheng;
    private String contract;
    private String cl_scqiye;
    private String cl_yingyong;
    private String cl_id;

    private List<String> cl_jhfangshi;
    private String cl_shijian;
    private String cl_type;
    private String cl_shijianend;
    private String amountUnitScale;
    private ArrayList<CLAttribute> cl_attribute;
    private String cl_jituan;
    private String cl_dizhi;
    private String cl_diqu;
    private String cl_cangku;
    private String cl_guojia;

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getCl_gongsiId() {
        return cl_gongsiId;
    }

    public void setCl_gongsiId(String cl_gongsiId) {
        this.cl_gongsiId = cl_gongsiId;
    }

    public List<String> getCl_jhfangshi() {
        return cl_jhfangshi;
    }

    public void setCl_jhfangshi(List<String> cl_jhfangshi) {
        this.cl_jhfangshi = cl_jhfangshi;
    }

    public RelatedProduct getRelatedProducts() {
        return relatedProducts;
    }

    public void setRelatedProducts(RelatedProduct relatedProducts) {
        this.relatedProducts = relatedProducts;
    }

    public String getMstEbsSkuInfoId() {
        return mstEbsSkuInfoId;
    }

    public void setMstEbsSkuInfoId(String mstEbsSkuInfoId) {
        this.mstEbsSkuInfoId = mstEbsSkuInfoId;
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

    public String getCl_hyfenlei() {
        return cl_hyfenlei;
    }

    public void setCl_hyfenlei(String cl_hyfenlei) {
        this.cl_hyfenlei = cl_hyfenlei;
    }

    public String getCl_gongsi() {
        return cl_gongsi;
    }

    public void setCl_gongsi(String cl_gongsi) {
        this.cl_gongsi = cl_gongsi;
    }

    public String getCl_qigou() {
        return cl_qigou;
    }

    public void setCl_qigou(String cl_qigou) {
        this.cl_qigou = cl_qigou;
    }

    public String getCl_jine() {
        return cl_jine;
    }

    public void setCl_jine(String cl_jine) {
        this.cl_jine = cl_jine;
    }

    public String getCl_fenlei() {
        return cl_fenlei;
    }

    public void setCl_fenlei(String cl_fenlei) {
        this.cl_fenlei = cl_fenlei;
    }

    public String getCl_paihao() {
        return cl_paihao;
    }

    public void setCl_paihao(String cl_paihao) {
        this.cl_paihao = cl_paihao;
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

    public String getCl_mingcheng() {
        return cl_mingcheng;
    }

    public void setCl_mingcheng(String cl_mingcheng) {
        this.cl_mingcheng = cl_mingcheng;
    }

    public String getCl_scqiye() {
        return cl_scqiye;
    }

    public void setCl_scqiye(String cl_scqiye) {
        this.cl_scqiye = cl_scqiye;
    }

    public String getCl_yingyong() {
        return cl_yingyong;
    }

    public void setCl_yingyong(String cl_yingyong) {
        this.cl_yingyong = cl_yingyong;
    }

    public String getCl_id() {
        return cl_id;
    }

    public void setCl_id(String cl_id) {
        this.cl_id = cl_id;
    }


    public String getCl_shijian() {
        return cl_shijian;
    }

    public void setCl_shijian(String cl_shijian) {
        this.cl_shijian = cl_shijian;
    }

    public String getCl_type() {
        return cl_type;
    }

    public void setCl_type(String cl_type) {
        this.cl_type = cl_type;
    }

    public String getCl_shijianend() {
        return cl_shijianend;
    }

    public void setCl_shijianend(String cl_shijianend) {
        this.cl_shijianend = cl_shijianend;
    }

    public String getAmountUnitScale() {
        return amountUnitScale;
    }

    public void setAmountUnitScale(String amountUnitScale) {
        this.amountUnitScale = amountUnitScale;
    }

    public ArrayList<CLAttribute> getCl_attribute() {
        return cl_attribute;
    }

    public void setCl_attribute(ArrayList<CLAttribute> cl_attribute) {
        this.cl_attribute = cl_attribute;
    }

    public String getCl_jituan() {
        return cl_jituan;
    }

    public void setCl_jituan(String cl_jituan) {
        this.cl_jituan = cl_jituan;
    }

    public String getCl_dizhi() {
        return cl_dizhi;
    }

    public void setCl_dizhi(String cl_dizhi) {
        this.cl_dizhi = cl_dizhi;
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
}
