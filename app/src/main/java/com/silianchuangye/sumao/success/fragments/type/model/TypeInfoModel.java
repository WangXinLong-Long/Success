package com.silianchuangye.sumao.success.fragments.type.model;

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

    public TypeInfoModel(String region2, String classification2, String application2, String tradingMethod, int nrpp, int no,String Ntt) {
        this.region2 = region2;
        this.classification2 = classification2;
        this.application2 = application2;
        this.tradingMethod = tradingMethod;
        Nrpp = nrpp;
        No = no;
        this.Ntt = Ntt;
    }

    @Override
    public void searchMessageFromServer(final TypeInfoCallback callback) {
//      /rest/model/atg/commerce/catalog/ProductCatalogActor/researchProduct
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/researchProduct";
        String params = "";
        String params1 = "";
        if(region2!=null && !region2.equals("") ){
            if(params.equals("")){
                params += region2;
            }else {
                params += "+"+region2;
            }
        }
        if(classification2!=null && !classification2.equals("")){
            if(params.equals("")){
                params += classification2;
            }else {
                params += "+"+classification2;
            }
        }
        if(application2!=null && !application2.equals("")){
            if(params.equals("")){
                params += application2;
            }else {
                params += "+"+application2;
            }
        }
        if(tradingMethod!=null && !tradingMethod.equals("")){
            if(params.equals("")){
                params += tradingMethod;
            }else {
                params += "+"+tradingMethod;
            }
        }
        if (Ntt!=null&&!Ntt.equals("")){
            params1 = "Ntt="+Ntt;
        }
        if (params1!=null&&!params1.equals("")){

            url=url+"?"+params1+"&N="+params;
        }else{
            url = url+"?N="+params;
        }
        LogUtils.log("URL----->"+url+"<-----URL");
        RequestParams requestParams = new RequestParams(url);
        requestParams.addParameter("Nrpp",Nrpp+"");
        requestParams.addParameter("No",No+"");
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
