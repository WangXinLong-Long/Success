package com.silianchuangye.sumao.success.fragments.homepage.AnnouncementDetailMVP.model;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.homepage.AnnouncementDetailMVP.bean.AnnouncementDetailBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/18 0018.
 */
public class AnnouncementDetailModel implements IAnnouncementDetailModel {
    String id;

    public AnnouncementDetailModel(String id) {
        this.id = id;
    }


    @Override
    public void getAnnouncementDetailInfo(final AnnouncementDetailCallback callback) {
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/articleDetail";
        RequestParams requestParams = new RequestParams(url);
        requestParams.setAsJsonContent(true);
        JSONObject jsonObject = new JSONObject();
        try {
            LogUtils.log("id是："+id);
            jsonObject.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestParams.setBodyContent(jsonObject.toString());
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {

                private AnnouncementDetailBean announcementDetailBean1;

                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("result--->" + result + "<---result");
                    Gson gson = new Gson();
                    announcementDetailBean1 = gson.fromJson(result, AnnouncementDetailBean.class);
                    callback.callbackAnnouncementDetail(announcementDetailBean1);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    LogUtils.log("--------->" + "3.2+onError" +ex.toString()+ "<-----------");
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    LogUtils.log("--------->" + "3.2+onCancelled" + "<-----------");
                }

                @Override
                public void onFinished() {
                    LogUtils.log("--------->" + "3.2+onFinished" + "<-----------");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("--------->" + "3.2+printStackTrace"+e.toString() + "<-----------");
        }
    }
}
