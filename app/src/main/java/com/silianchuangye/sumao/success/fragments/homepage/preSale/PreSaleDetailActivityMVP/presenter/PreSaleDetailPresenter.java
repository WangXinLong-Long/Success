package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.presenter;

import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailCalendarBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.CalendarModel.CalendarCallback;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.CalendarModel.CalendarModels;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.CalendarModel.ICalendarModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.IPreSaleDetailModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.PreSaleDetailCallback;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.PreSaleDetailModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.view.IPreSaleDetailView;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class PreSaleDetailPresenter {
    IPreSaleDetailView preSaleDetailView;

    public PreSaleDetailPresenter(IPreSaleDetailView preSaleDetailView) {
        this.preSaleDetailView = preSaleDetailView;
    }

    public void sendPreSaleDetailData(String skuId, String productId) {
        IPreSaleDetailModel preSaleDetailModel = new PreSaleDetailModel(skuId, productId);
        preSaleDetailModel.getPreSaleDetailResult(new PreSaleDetailCallback() {
            @Override
            public void callbackPreSaleDetail(PreSaleDetailBean preSaleDetailBean) {
                preSaleDetailView.getPreSaleDetailData(preSaleDetailBean);
            }
        });
    }

    public void sendPreSaleDetailCalendar(String productId/*,int position*/) {
        ICalendarModel calendarModel = new CalendarModels(productId/*, position*/);
        calendarModel.getCalendarInfo(new CalendarCallback() {
            @Override
            public void CalendarCallback(PreSaleDetailCalendarBean preSaleDetailCalendarBean/*,int position*/) {
                preSaleDetailView.getPreSaleDetailCalendarData(preSaleDetailCalendarBean/*, position*/);
            }
        });

    }
}
