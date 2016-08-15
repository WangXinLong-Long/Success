package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.model;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class PreSaleModel implements IPreSaleModel{
    String region2;
    String classification2;
    String application2;
    int Nrpp;
    int No;

    public PreSaleModel(String region2, String classification2, String application2, int nrpp, int no) {
        this.region2 = region2;
        this.classification2 = classification2;
        this.application2 = application2;
        Nrpp = nrpp;
        No = no;
    }

    @Override
    public void getPreSaleResult(final PreSaleCallback callback) {
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/forwardPrductlist";
        String params = "";
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
        url=url+"?N="+params;
        LogUtils.log("URL----->"+url+"<-----URL");
        RequestParams requestParams = new RequestParams(url);
        requestParams.addParameter("Nrpp",Nrpp+"");
        requestParams.addParameter("No",No+"");
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {

                private GoodsInStockActivityBean goodsInStockActivityBean;

                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("首页：现货--->" + result + "<---首页：现货");
                    Gson gson = new Gson();
                    goodsInStockActivityBean = gson.fromJson(result,GoodsInStockActivityBean.class);
                    callback.callbackPreSale(goodsInStockActivityBean);
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
