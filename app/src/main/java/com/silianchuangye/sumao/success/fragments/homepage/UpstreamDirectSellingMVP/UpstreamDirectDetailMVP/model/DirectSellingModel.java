package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.model;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.bean.DirectSellingBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/11/18.
 */
public class DirectSellingModel implements IDirectSellingModel {
    String pageNum;
    String sellerCompanyId;
    String _dynSessConf;

    public DirectSellingModel(String pageNum, String sellerCompanyId, String _dynSessConf) {
        this.pageNum = pageNum;
        this.sellerCompanyId = sellerCompanyId;
        this._dynSessConf = _dynSessConf;
    }

    @Override
    public void getDirecSellingInfo(final DirectSellingCallback callback) {

        String url = SuMaoConstant.SUMAO_IP+"/rest/model/com/sumao/mobile/order/purchase/PlanOrderActor/directEnterpriseProductList" ;
        RequestParams rp = new RequestParams(url);
        rp.setAsJsonContent(true);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNum",pageNum);
            jsonObject.put("sellerCompanyId",sellerCompanyId);
            jsonObject.put("_dynSessConf",_dynSessConf);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        rp.setBodyContent(jsonObject.toString());
        x.http().request(HttpMethod.POST, rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("Jobs Created 现货直销-->"+result);
                Gson gson = new Gson();
                DirectSellingBean directSellingBean = gson.fromJson(result, DirectSellingBean.class);
                LogUtils.log("DirectSellingBean的某个值为：--->"+directSellingBean.getAllCount());
                LogUtils.log("DirectSellingBean的某个值为：--->"+directSellingBean.getProductItem().get(0).getProductId());
                callback.callbackDirectSelling(directSellingBean);
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
