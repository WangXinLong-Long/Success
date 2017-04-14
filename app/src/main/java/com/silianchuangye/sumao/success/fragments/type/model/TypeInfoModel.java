package com.silianchuangye.sumao.success.fragments.type.model;

import android.util.Log;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.bean.SearchActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/29 0029.
 */
public class TypeInfoModel implements ITypeInfoModel {
    String region2;
    String classification2;
    String application2;
    String tradingMethod;
    int Nrpp;
    int No;
    String Ntt;
    private String keyword;
    private String page;

    public TypeInfoModel(String keyword,String page) {
        this.keyword = keyword;
        this.page=page;
    }

//    public TypeInfoModel(String region2, String classification2, String application2, String tradingMethod, int nrpp, int no, String Ntt) {
//        this.region2 = region2;
//        this.classification2 = classification2;
//        this.application2 = application2;
//        this.tradingMethod = tradingMethod;
//        Nrpp = nrpp;
//        No = no;
//        this.Ntt = Ntt;
//    }

    @Override
    public void searchMessageFromServer(final TypeInfoCallback callback) {
//      /rest/model/atg/commerce/catalog/ProductCatalogActor/researchProduct
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/endeca/HomeActor/EndecaAppScreenResult";

        LogUtils.log("URL----->"+url+"<-----URL");
        url=url+"?No="+page+"&keyword="+keyword;

        RequestParams requestParams = new RequestParams(url);
      //  requestParams.addParameter("keyword",Ntt);


        Log.e("TAG","request-------"+requestParams);
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {

                private SearchActivityBean searchActivityBean;

                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("首页：现货--->" + result + "<---首页：现货");
                     Gson gson = new Gson();
                    searchActivityBean = gson.fromJson(result, SearchActivityBean.class);
                    callback.callbackTypeInfo( searchActivityBean);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    LogUtils.log("--------->" + "3.2+onError" + ex.toString() + "<-----------");
                    ex.toString();
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
            LogUtils.log("--------->" + "3.2+printStackTrace" + e.toString() + "<-----------");
        }
    }
}
