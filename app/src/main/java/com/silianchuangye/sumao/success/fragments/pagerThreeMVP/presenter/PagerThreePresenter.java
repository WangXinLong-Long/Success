package com.silianchuangye.sumao.success.fragments.pagerThreeMVP.presenter;

import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.Orders;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.paymentOrderModel.IPaymentOrderModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.paymentOrderModel.PaymentOrderCallback;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.paymentOrderModel.PaymentOrderModel;
import com.silianchuangye.sumao.success.fragments.pagerThreeMVP.model.IPagerThreeModel;
import com.silianchuangye.sumao.success.fragments.pagerThreeMVP.model.PagerThreeCallback;
import com.silianchuangye.sumao.success.fragments.pagerThreeMVP.model.PagerThreeModel;
import com.silianchuangye.sumao.success.fragments.pagerThreeMVP.model.carsDeleteModel.CarsUpdateCallback;
import com.silianchuangye.sumao.success.fragments.pagerThreeMVP.model.carsDeleteModel.CarsUpdateModel;
import com.silianchuangye.sumao.success.fragments.pagerThreeMVP.model.carsDeleteModel.ICarsUpdateModel;
import com.silianchuangye.sumao.success.fragments.pagerThreeMVP.model.deleteGoodsModel.DeleteGoodsCallback;
import com.silianchuangye.sumao.success.fragments.pagerThreeMVP.model.deleteGoodsModel.DeleteGoodsModel;
import com.silianchuangye.sumao.success.fragments.pagerThreeMVP.model.deleteGoodsModel.IDeleteGoodsModel;
import com.silianchuangye.sumao.success.fragments.pagerThreeMVP.view.IPagerThreeView;

/**
 * Created by Jobs Created on 2016/12/23.
 */
public class PagerThreePresenter {
    IPagerThreeView pagerThreeView;

    public PagerThreePresenter(IPagerThreeView pagerThreeView) {
        this.pagerThreeView = pagerThreeView;
    }

    public void getPagerThreeResult(String _dynSessConf){
        IPagerThreeModel pagerThreeModel = new PagerThreeModel( _dynSessConf);
        pagerThreeModel.getPagerThreeResult(new PagerThreeCallback() {
            @Override
            public void callbackPagerThree(String result) {
                pagerThreeView.setDataInPagerThree(result);
            }
        });
    }

    public void getDeleteGoodsResult(String _dynSessConf,String commerceId,int position){
        IDeleteGoodsModel deleteGoodsModel = new DeleteGoodsModel(_dynSessConf, commerceId, position);
        deleteGoodsModel.getDeleteGoodsResult(new DeleteGoodsCallback() {
            @Override
            public void callbackDeleteGoods(String result,int position) {
                pagerThreeView.setDeleteGoodsData(result,position);
            }
        });
    }

    public void getCarsUpdateResult(String amountUnitScale, String qty, String commerceId, String _dynSessConf){
        ICarsUpdateModel carsUpdateModel = new CarsUpdateModel( amountUnitScale,  qty,  commerceId,  _dynSessConf);
        carsUpdateModel.getCarsUpdateResult(new CarsUpdateCallback() {
            @Override
            public void callbackCarsUpdate(String result) {
                pagerThreeView.setCarsUpdate(result);
            }
        });
    }

//    提交订单到订单界面
    public void getGenerateOrder(String _dynSessConf,String strCommerceItemIds){
        IPaymentOrderModel paymentOrderModel = new PaymentOrderModel(strCommerceItemIds,_dynSessConf);
        paymentOrderModel.getPaymentOrderResult(new PaymentOrderCallback() {
            @Override
            public void callbackPaymentOrder(  Orders orderList) {
                pagerThreeView.sendDataToOrderPage(  orderList);
            }
        });
    }
}
