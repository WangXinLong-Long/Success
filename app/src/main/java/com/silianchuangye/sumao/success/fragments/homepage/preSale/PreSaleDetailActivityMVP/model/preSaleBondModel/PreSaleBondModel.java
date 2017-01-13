package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.preSaleBondModel;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/24.
 */
public class PreSaleBondModel implements IPreSaleBondModel{
    String productId;
    String skuId;

    public PreSaleBondModel(String productId/*,String skuId*/) {
        this.productId = productId;
//        this.skuId = skuId;
    }

    @Override
    public void getPresaleBondResult(final PreSaleBondCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/availableBank";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("productId", productId);
//        rp.addParameter("skuId", skuId);
        LogUtils.log("支付保证金的获取银行列表的URL---->" + rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("支付保证金的获取银行列表的result---->" + result);
                callback.callbakcPreSale(result);

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
