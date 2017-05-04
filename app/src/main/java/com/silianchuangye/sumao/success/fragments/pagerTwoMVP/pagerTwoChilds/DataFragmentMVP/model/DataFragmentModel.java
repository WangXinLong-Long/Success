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
    String searchDate;

    public DataFragmentModel(String dateType,String searchDate) {
        this.dateType = dateType;
        this.searchDate=searchDate;
    }

    @Override
    public void getDataFragmentResult(final DataFragmentCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/news/NewsAnalyzeActor/NewsDataList";
        RequestParams rp = new RequestParams(url);
        if (searchDate!=null && !searchDate.equals("")){
            rp.addParameter("searchDate",searchDate);
        }
        if (dateType!=null &&!dateType.equals("")){
            rp.addParameter("dateType",dateType);
        }
        Log.d("资讯中数据的返回值",rp+"");
            x.http().post(rp, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.d("资讯数据的result--->", result);

                    callback.callbackDataFragmentResult(result);


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
