package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.presenter;

import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailCalendarBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.CalendarModel.CalendarCallback;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.CalendarModel.CalendarModels;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.CalendarModel.ICalendarModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.IPreSaleDetailModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.PreSaleDetailCallback;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.PreSaleDetailModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.paymentSecurityModel.IPaymentSecurityModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.paymentSecurityModel.PaymentSecurityCallback;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.paymentSecurityModel.PaymentSecurityModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.preSaleBondModel.IPreSaleBondModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.preSaleBondModel.PreSaleBondCallback;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.preSaleBondModel.PreSaleBondModel;
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
            public void callbackPreSaleDetail(PreSaleDetailBean preSaleDetailBean,String sku) {
                preSaleDetailView.getPreSaleDetailData(preSaleDetailBean,sku);
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

    //    从服务器获取银行列表
    public void getBankListFromService(String productId){
        IPreSaleBondModel preSaleBondModel = new PreSaleBondModel(productId);
        preSaleBondModel.getPresaleBondResult(new PreSaleBondCallback() {
            @Override
            public void callbakcPreSale(String result) {
                preSaleDetailView.setBankListResult(result);
            }
        });
    }

//    支付保证金
    public void getPaymentSecurityResult( String skuId, String productId, String quantity, String _dynSessConf, String paymentPlatform){
        IPaymentSecurityModel paymentSecurityModel = new PaymentSecurityModel( skuId,  productId,  quantity,  _dynSessConf,  paymentPlatform);
        paymentSecurityModel.getPaymentSecurityResult(new PaymentSecurityCallback() {
            @Override
            public void callbackPaymentSecurity(String result) {
                preSaleDetailView.payPaymentSecurity(result);
            }
        });
    }
}
