package com.silianchuangye.sumao.success.fragments.SearchActivityMVP.model;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.bean.SearchActivityBean;
import com.silianchuangye.sumao.success.model.DifferentTypes;
import com.silianchuangye.sumao.success.model.EnterpriseInformation;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class SearchActivityModel implements ISearchActivityModel {
    String Ntt;
    int Nrpp;
    int No;

    public SearchActivityModel(String ntt, int nrpp, int no) {
        Ntt = ntt;
        Nrpp = nrpp;
        No = no;
    }

    @Override
    public void getSearchActivityInfo(final SearchActivityCallback callback) {
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/researchProduct";
        RequestParams requestParams = new RequestParams(url);
       requestParams.setAsJsonContent(true);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Ntt", Ntt);
            jsonObject.put("Nrpp",Nrpp+"");
            jsonObject.put("No",No+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestParams.setBodyContent(jsonObject.toString());
        Log.e("TAG","requestParams---------"+requestParams);
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {

                private SearchActivityBean searchActivityBean;

                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("result--->" + result + "<---result");

                    Gson gson = new Gson();
                    searchActivityBean = gson.fromJson(result, SearchActivityBean.class);

                    callback.callbackSearchActivity(searchActivityBean);
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
