package com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.model.groupBuyingDetailResultModel;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2017/1/13.
 */
public class GroupBuyingDetailResultModel implements IGroupBuyingDetailResultModel {
    private String productId;
    private String state;
    private String result;
    private String securedOfSection;

    public GroupBuyingDetailResultModel( String productId, String state, String result) {

        this.productId = productId;
        this.state = state;
        this.result = result;
    }

    @Override
    public void getGroupBuyingDetailResultResult(final GroupBuyingDetailResultCallback callback) {
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/groupProduct";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("productId", productId);
        LogUtils.log("团购详情的RUL--->"+rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("团购详情的Result--->"+result);
                callback.callbackGroupBuyingDetailResult(result,state);
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
