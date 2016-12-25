package com.silianchuangye.sumao.success.fragments.pagerThreeMVP.model.deleteGoodsModel;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/23.
 */
public class DeleteGoodsModel implements IDeleteGoodsModel {
    String _dynSessConf;
    String commerceId;
    int position;

    public DeleteGoodsModel(String _dynSessConf, String commerceId, int position) {
        this._dynSessConf = _dynSessConf;
        this.commerceId = commerceId;
        this.position = position;
    }

    @Override
    public void getDeleteGoodsResult(final DeleteGoodsCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/ShoppingCartActor/removeItemFromOrder";
        final RequestParams rp = new RequestParams(url);
        rp.addParameter("_dynSessConf", _dynSessConf);
        rp.addParameter("commerceId", commerceId);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.d("删除时的result", result);
callback.callbackDeleteGoods(result,position);
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
