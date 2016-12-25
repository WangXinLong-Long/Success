package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.view;

import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailCalendarBean;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public interface IPreSaleDetailView {
//    返回数据给PreSaleDetailActivity

  void  getPreSaleDetailData(PreSaleDetailBean preSaleDetailBean,String sku);

 void getPreSaleDetailCalendarData(PreSaleDetailCalendarBean preSaleDetailCalendarBean);


    //    从服务器获取银行列表
    void setBankListResult(String result);

//    支付保证金
    void payPaymentSecurity(String result);
}
