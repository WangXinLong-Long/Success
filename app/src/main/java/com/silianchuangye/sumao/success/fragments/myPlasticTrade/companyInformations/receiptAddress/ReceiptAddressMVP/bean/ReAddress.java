package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class ReAddress implements Serializable {
    String phone;
    Createdate createdate;
    String addressType;
    UpdateDate updateDate;
    String city;
    String id;
    String address;
    String county;
    String zipCode;
    String name;
    String province;
    String mobile;

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public Createdate getCreatedate() {
      return createdate;
   }

   public void setCreatedate(Createdate createdate) {
      this.createdate = createdate;
   }

   public String getAddressType() {
      return addressType;
   }

   public void setAddressType(String addressType) {
      this.addressType = addressType;
   }

   public UpdateDate getUpdateDate() {
      return updateDate;
   }

   public void setUpdateDate(UpdateDate updateDate) {
      this.updateDate = updateDate;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getCounty() {
      return county;
   }

   public void setCounty(String county) {
      this.county = county;
   }

   public String getZipCode() {
      return zipCode;
   }

   public void setZipCode(String zipCode) {
      this.zipCode = zipCode;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getProvince() {
      return province;
   }

   public void setProvince(String province) {
      this.province = province;
   }

   public String getMobile() {
      return mobile;
   }

   public void setMobile(String mobile) {
      this.mobile = mobile;
   }
}
