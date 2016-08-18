package com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.AnnouncementModel;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.AnnounceBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.AnnouncementBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.BannerBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/17 0017.
 */
public class AnnouncementModel implements IAnnouncementModel {
    @Override
    public void getAnnouncementInfo(final AnnouncementCallback callback) {
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/articles";
        RequestParams requestParams = new RequestParams(url);
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {

                private AnnounceBean announceBean;

                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("公告公告--->" + result + "<---公告公告");
                    Gson gson = new Gson();
                    announceBean = gson.fromJson(result, AnnounceBean.class);
                    LogUtils.log("公告公告--->" + announceBean.getArticles().get(1).getHeadline() + "<---公告公告");
                    callback.callbackAnnouncement( announceBean);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    LogUtils.log("--------->PagerOneModel" + "3.2+onError" +ex.toString()+ "<-----------");
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    LogUtils.log("--------->PagerOneModel" + "3.2+onCancelled" + "<-----------");
                }

                @Override
                public void onFinished() {
                    LogUtils.log("--------->PagerOneModel" + "3.2+onFinished" + "<-----------");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("--------->PagerOneModel" + "3.2+printStackTrace"+e.toString() + "<-----------");
        }
    }
}
