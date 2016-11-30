package com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.model.Warehousemodel;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.Bean.PlanDayBean;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.UpstreamDirectorySellingBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class WarehouseModel implements IWarehouseModel {
    String _dynSessConf;
    String productId;

    String url = SuMaoConstant.SUMAO_IP+"/rest/model/com/sumao/mobile/order/purchase/PlanOrderActor/vipWareHouseAndDeliveryMethodSearch";

    public WarehouseModel(String _dynSessConf, String productId) {
        this._dynSessConf = _dynSessConf;
        this.productId = productId;

    }

    @Override
    public void getWarehouseInfo(final WarehouseCallback callback) {
//      /rest/model/com/sumao/mobile/order/purchase/PlanOrderActor/vipWareHouseAndDeliveryMethodSearch
        RequestParams rp = new RequestParams(url);
        rp.setAsJsonContent(true);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("_dynSessConf",_dynSessConf);
            jsonObject.put("productId",productId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        rp.setBodyContent(jsonObject.toString());
        x.http().request(HttpMethod.POST, rp, new Callback.CommonCallback<String>() {
            private PlanDayBean planDayBean;

            @Override
            public void onSuccess(String result) {
                LogUtils.log("上游直销地址-->"+result);
                Gson gson = new Gson();
                planDayBean = gson.fromJson(result,PlanDayBean.class);
                callback.callbackWarehouse( planDayBean);
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
//
    }
}
