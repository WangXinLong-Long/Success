package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.view;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.bean.InvoiceInformationBean;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public interface IInvoiceInformationView {
//    将从服务器收集到的数据放置到Text上
   void  setResultInText(InvoiceInformationBean bean);
//   将请求的省市县的结果放到TextView上
   void setRegionInText(String result);
//   将保存的结果存到服务器上成功的处理过程
   void saveModifyInvoiceInformationSuccess();
//   将保存的结果存到服务器上失败的处理过程
void saveModifyInvoiceInformationFailed();
}
