package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.getListOfBankModel;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/19.
 */
public class GetListOfBankModel implements IGetListOfBankModel {
    private String order;
    private String roderIds;
    private Callback.Cancelable cancelable;

    public GetListOfBankModel(String order, String roderIds) {
        this.order = order;
        this.roderIds = roderIds;
    }

    @Override
    public void getListOfBankResult(final GetListOfBankCallback callback) {
//        String result = "{\"bankResult\":[{\"bankBalance\":\"112321.16\",\"openedAccount\":\"true\",\"bankName\":\"平安银行\"},{\"bankBalance\":\"882321.16\",\"openedAccount\":\"true\",\"bankName\":\"建设银行\"},{\"bankBalance\":\"992321.16\",\"openedAccount\":\"true\",\"bankName\":\"昆仑银行\"}],\"balance\":10,\"orderUrlType\":\"fixedPricingOrder\",\"remainingTime\":3596,\"payfor\":\"order\",\"info\":\"ture\"}";
//        callback.callbackListOfBank(result);
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/payment/OrderPayment/bankList";
        RequestParams rp = new RequestParams(url);
        if (this.order != null){
            rp.addBodyParameter("payFor",this.order);
        }

        rp.addParameter("orderIds", roderIds);
        LogUtils.log("获取银行列表的URL--->"+rp);
        cancelable = x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("银行列表是--->" + result);
                callback.callbackListOfBank(result);
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
