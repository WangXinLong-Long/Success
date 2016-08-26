package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.view;

import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailCalendarBean;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public interface IPreSaleDetailView {
//    返回数据给PreSaleDetailActivity

  void  getPreSaleDetailData(PreSaleDetailBean preSaleDetailBean);

 void getPreSaleDetailCalendarData(PreSaleDetailCalendarBean preSaleDetailCalendarBean);


}
