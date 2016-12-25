package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.presenter;

import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.model.shoppingCartModel.IShoppingCartModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.model.shoppingCartModel.ShoppingCartCallback;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.model.shoppingCartModel.ShoppingCartModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.model.submitOrderModel.ISubmitOrderModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.model.submitOrderModel.SubmitOrderCallback;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.model.submitOrderModel.SubmitOrderModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.view.IGoodsInStockDetailView;

/**
 * Created by Jobs on 2016/8/13 0013.
 */
public class GoodsInStockDetailPresenter {
    IGoodsInStockDetailView goodsInStockDetailView;

    public GoodsInStockDetailPresenter(IGoodsInStockDetailView goodsInStockDetailView) {
        this.goodsInStockDetailView = goodsInStockDetailView;
    }



    //    提交订单
    public void submitOrder(String quantity, String _dynSessConf, String productId, String skuId) {
        ISubmitOrderModel submitOrderModel = new SubmitOrderModel(quantity, _dynSessConf, productId, skuId);
        submitOrderModel.getSubmitOrderResult(new SubmitOrderCallback() {
            @Override
            public void callbackSubmitOrder(String result) {
                goodsInStockDetailView.getSubmitOrderData(result);
            }
        });

    }

    //    加入购物车
    public void joinToCart(String productId, String quantity, String skuId, String _dynSessConf) {
        IShoppingCartModel shoppingCartModel = new ShoppingCartModel(productId, quantity, skuId, _dynSessConf);
        shoppingCartModel.getShoppingCarResult(new ShoppingCartCallback() {
            @Override
            public void callbackShoppingCartModel(String result) {
                goodsInStockDetailView.getJoinToCartData(result);
            }
        });
    }


}
