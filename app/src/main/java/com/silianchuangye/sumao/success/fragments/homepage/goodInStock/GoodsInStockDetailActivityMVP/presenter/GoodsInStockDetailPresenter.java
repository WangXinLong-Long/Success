package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.presenter;

import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.model.GoodsInStockDetailModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.model.IGoodsInStockDetailModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.model.goodsInStockDetailCallback;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.view.GoodsInStockDetailActivity;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.view.IGoodsInStockDetailView;

/**
 * Created by Administrator on 2016/8/13 0013.
 */
public class GoodsInStockDetailPresenter {
    IGoodsInStockDetailView goodsInStockDetailView;

    public GoodsInStockDetailPresenter(IGoodsInStockDetailView goodsInStockDetailView) {
        this.goodsInStockDetailView = goodsInStockDetailView;
    }

    public void GoodsInStockDetailSendData(String cl_id) {
        IGoodsInStockDetailModel goodsInStockDetailModel = new GoodsInStockDetailModel(cl_id);
        goodsInStockDetailModel.getGoodsInStockDetailResult(new goodsInStockDetailCallback() {
            @Override
            public void callbackGoodsInStockDetail(GoodsInStockDetailBean goodsInStockDetailBean) {
                goodsInStockDetailView.getGoodsInStockDetailData(goodsInStockDetailBean);
            }
        });
    }
}
