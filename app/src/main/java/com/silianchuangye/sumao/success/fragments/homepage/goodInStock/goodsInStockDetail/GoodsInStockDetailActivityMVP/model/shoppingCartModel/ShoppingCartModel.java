package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.model.shoppingCartModel;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/20.
 */
public class ShoppingCartModel implements IShoppingCartModel {
    private String productId;
    private String quantity;
    private String skuId;
    private String _dynSessConf;

    public ShoppingCartModel(String productId, String quantity, String skuId, String _dynSessConf) {
        this.productId = productId;
        this.quantity = quantity;
        this.skuId = skuId;
        this._dynSessConf = _dynSessConf;
    }

    @Override
    public void getShoppingCarResult(final ShoppingCartCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/ShoppingCartActor/addItemToOrder";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("productId", productId);
        rp.addParameter("quantity", quantity);
        rp.addParameter("skuId", skuId);
        rp.addParameter("_dynSessConf", _dynSessConf);
        Log.d("添加到购物车的skuid", "" + rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("添加到购物车result", result);
                callback.callbackShoppingCartModel(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
