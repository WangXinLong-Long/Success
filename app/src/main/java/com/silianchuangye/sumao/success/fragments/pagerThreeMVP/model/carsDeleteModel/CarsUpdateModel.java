package com.silianchuangye.sumao.success.fragments.pagerThreeMVP.model.carsDeleteModel;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/23.
 */
public class CarsUpdateModel implements ICarsUpdateModel {
    private String amountUnitScale;
    private String qty;
    private String commerceId;
    private String _dynSessConf;

    public CarsUpdateModel(String amountUnitScale, String qty, String commerceId, String _dynSessConf) {
        this.amountUnitScale = amountUnitScale;
        this.qty = qty;
        this.commerceId = commerceId;
        this._dynSessConf = _dynSessConf;
    }

    @Override
    public void getCarsUpdateResult(final CarsUpdateCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/ShoppingCartActor/commerceItemUpdate";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("amountUnitScale", amountUnitScale);
        rp.addParameter("qty", qty);
        rp.addParameter("commerceId", commerceId);

        rp.addParameter("_dynSessConf", _dynSessConf);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("修改的返回值", result);
               callback.callbackCarsUpdate(result);
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
