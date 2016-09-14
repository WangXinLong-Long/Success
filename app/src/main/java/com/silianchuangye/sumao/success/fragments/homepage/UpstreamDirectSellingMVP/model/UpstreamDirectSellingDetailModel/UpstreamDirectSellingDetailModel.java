package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.model.UpstreamDirectSellingDetailModel;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.UpstreamDirectorySellingBean;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.vipProductBean.VipProductBean;
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
public class UpstreamDirectSellingDetailModel implements IUpstreamDirectSellingDetailModel {
    String sessionId;
    String sellerCompanyId;
    String url = SuMaoConstant.SUMAO_IP+"/rest/model/com/sumao/mobile/order/purchase/PlanOrderActor/vipProductSearch";
    public UpstreamDirectSellingDetailModel(String id, String sellerCompanyId) {
        this.sessionId = id;
        this.sellerCompanyId = sellerCompanyId;
    }

    @Override
    public void getUpstreamDirectSellingDetailInfo(final UpstreamDirectSellingDetailCallback callback) {
        RequestParams rp = new RequestParams(url);
        rp.setAsJsonContent(true);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("_dynSessConf",sessionId);
            jsonObject.put("sellerCompanyId",sellerCompanyId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        rp.setBodyContent(jsonObject.toString());
        x.http().request(HttpMethod.POST, rp, new Callback.CommonCallback<String>() {
            private VipProductBean vipProductBean;

            @Override
            public void onSuccess(String result) {
                LogUtils.log("上游直销-->产品名称-->"+result);
                Gson gson = new Gson();
                vipProductBean = gson.fromJson(result,VipProductBean.class);
                callback.callbackIUpstreamDirectSellingDetail( vipProductBean);
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
//        callback.callbackIUpstreamDirectSellingDetail();
    }
}
