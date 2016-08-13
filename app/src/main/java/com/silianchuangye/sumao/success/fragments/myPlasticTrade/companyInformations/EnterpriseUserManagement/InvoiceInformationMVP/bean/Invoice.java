package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class Invoice implements Serializable{
    class CreateDate {
        String time;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
    private CreateDate createDate;
//    固定电话
    private String notesRecipientPhone;
//    邮寄地址市
    private String notesRecipientCity;
//    开票信息id
    private String id;
//    姓名
    private String notesRecipientName;
//    邮寄地址详情
    private String notesRecipientAddress;
//    邮寄地址区
    private String notesRecipientCounty;
//    地址
    private String address;
//    电话
    private String phoneNO;
//    税号
    private String taxID;
//    邮编
    private String notesRecipientZipCode;
//    开户行
    private String bank;
//    邮寄地址省
    private String notesRecipientProvince;
//
    private Updatedate updatedate;
//    手机
    private String notesRecipientMobile;
//    账号
    private String bankAccount;

    public CreateDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(CreateDate createDate) {
        this.createDate = createDate;
    }

    public String getNotesRecipientPhone() {
        return notesRecipientPhone;
    }

    public void setNotesRecipientPhone(String notesRecipientPhone) {
        this.notesRecipientPhone = notesRecipientPhone;
    }

    public String getNotesRecipientCity() {
        return notesRecipientCity;
    }

    public void setNotesRecipientCity(String notesRecipientCity) {
        this.notesRecipientCity = notesRecipientCity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotesRecipientName() {
        return notesRecipientName;
    }

    public void setNotesRecipientName(String notesRecipientName) {
        this.notesRecipientName = notesRecipientName;
    }

    public String getNotesRecipientAddress() {
        return notesRecipientAddress;
    }

    public void setNotesRecipientAddress(String notesRecipientAddress) {
        this.notesRecipientAddress = notesRecipientAddress;
    }

    public String getNotesRecipientCounty() {
        return notesRecipientCounty;
    }

    public void setNotesRecipientCounty(String notesRecipientCounty) {
        this.notesRecipientCounty = notesRecipientCounty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNO() {
        return phoneNO;
    }

    public void setPhoneNO(String phoneNO) {
        this.phoneNO = phoneNO;
    }

    public String getTaxID() {
        return taxID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public String getNotesRecipientZipCode() {
        return notesRecipientZipCode;
    }

    public void setNotesRecipientZipCode(String notesRecipientZipCode) {
        this.notesRecipientZipCode = notesRecipientZipCode;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getNotesRecipientProvince() {
        return notesRecipientProvince;
    }

    public void setNotesRecipientProvince(String notesRecipientProvince) {
        this.notesRecipientProvince = notesRecipientProvince;
    }

    public Updatedate getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Updatedate updatedate) {
        this.updatedate = updatedate;
    }

    public String getNotesRecipientMobile() {
        return notesRecipientMobile;
    }

    public void setNotesRecipientMobile(String notesRecipientMobile) {
        this.notesRecipientMobile = notesRecipientMobile;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
