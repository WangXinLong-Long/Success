package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean;

        import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean.CLAttribute;

        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class PreSaleDetailBean implements Serializable{
    private String deliveryPeriodEnd;
    private String cl_jifen;
    private String cl_mingcheng;
    private String cl_baozhj;
    private String cl_ckdizhi;
    private String cl_qigou;
    private String cl_id;
    private String cl_qiye;
    private List<String> cl_jhfangshi;
    private String deliveryPeriodStart;
    private String manufacturer;
    private String cl_fenlei;
    private ArrayList<CLAttribute> cl_attribute;
    private String cl_diqu;
    private String cl_cangku;
    private String cl_qiyeId;
    private String cl_shuliang;
    private String cl_xiaobian;

    public List<String> getCl_jhfangshi() {
        return cl_jhfangshi;
    }

    public void setCl_jhfangshi(List<String> cl_jhfangshi) {
        this.cl_jhfangshi = cl_jhfangshi;
    }

    public String getCl_fenlei() {
        return cl_fenlei;
    }

    public void setCl_fenlei(String cl_fenlei) {
        this.cl_fenlei = cl_fenlei;
    }

    public ArrayList<CLAttribute> getCl_attribute() {
        return cl_attribute;
    }

    public void setCl_attribute(ArrayList<CLAttribute> cl_attribute) {
        this.cl_attribute = cl_attribute;
    }

    public String getCl_qiyeId() {
        return cl_qiyeId;
    }

    public void setCl_qiyeId(String cl_qiyeId) {
        this.cl_qiyeId = cl_qiyeId;
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
}
