package com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.model.groupBuyingModel;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2017/1/11.
 */
public class GroupBuyingModel implements IGroupBuyingModel{
    boolean updateOrLoad;

    public GroupBuyingModel(boolean updateOrLoad) {
        this.updateOrLoad = updateOrLoad;
    }

    @Override
    public void getGroupBuyingResult(final GroupBuyingCallback callback) {
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/groupProductlist";
        RequestParams rp=new RequestParams(url);
        LogUtils.log("团购的RUL--->"+rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("团购的Result--->"+result);
                callback.callbackGroupBuying(result, updateOrLoad);
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
