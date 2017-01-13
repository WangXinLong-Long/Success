package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.DataFragmentMVP.model;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/27.
 */
public class DataFragmentModel implements IDataFragmentModel {

    String dateType;

    public DataFragmentModel(String dateType) {
        this.dateType = dateType;
    }

    @Override
    public void getDataFragmentResult(final DataFragmentCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/news/NewsAnalyzeActor/NewsDataList";
        RequestParams rp = new RequestParams(url);
        if (dateType!=null){
            rp.addParameter("dateType",dateType);
        }
            x.http().post(rp, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.d("资讯数据的result--->", result);

                    callback.callbackDataFragmentResult(  result);


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
