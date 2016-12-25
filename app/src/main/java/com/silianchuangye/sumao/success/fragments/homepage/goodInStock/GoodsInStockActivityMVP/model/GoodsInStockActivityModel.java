package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.model;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class GoodsInStockActivityModel implements IGoodsInStockActivityModel {
    String pattern;//交易模式
    String regions;//    大区
    String provinces;//省
    String cities;//市
    String categoryNames;//分类
    String appNames;//应用
    String gradeNums;//牌号
    String pageNum;//当前页码
    String updateOrLoad;

    public GoodsInStockActivityModel(String pattern, String regions, String provinces, String cities,
                                     String categoryNames, String appNames, String gradeNums, String pageNum, String updateOrLoad) {
        this.pattern = pattern;
        this.regions = regions;
        this.provinces = provinces;
        this.cities = cities;
        this.categoryNames = categoryNames;
        this.appNames = appNames;
        this.gradeNums = gradeNums;
        this.pageNum = pageNum;
        this.updateOrLoad = updateOrLoad;
    }

    @Override
    public void getGoodsInStockActivityResult(final GoodsInStockActivityCallback callback) {

        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/screen/screenShowProductListActor/ScreenShowProductList";


        LogUtils.log("URL----->" + url + "<-----URL");
        RequestParams requestParams = new RequestParams(url);
        requestParams.setAsJsonContent(true);
        JSONObject jsonObject = new JSONObject();
        try {
            if (regions != null) {

                jsonObject.put("regions", regions);

            }
            if (provinces != null) {
                jsonObject.put("provinces", provinces);
            }
            if (cities != null) {
                jsonObject.put("cities", cities);
            }
            if (categoryNames != null) {
                jsonObject.put("categoryNames", categoryNames);
            }
            if (appNames != null) {
                jsonObject.put("appNames", appNames);
            }
            if (gradeNums != null) {
                jsonObject.put("appNames", gradeNums);
            }

            jsonObject.put("pattern", pattern);
            jsonObject.put("pageNum", pageNum);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestParams.setBodyContent(jsonObject.toString());
        LogUtils.log("首页：现货requestParams--->" + requestParams + "<---首页：现货requestParams");
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
                    goodsInStockActivityBean = gson.fromJson(result, GoodsInStockActivityBean.class);
                    callback.callbackInGoodsInStockActivity(goodsInStockActivityBean, updateOrLoad);
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
                    Loding.dis();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("--------->" + "3.2+printStackTrace" + e.toString() + "<-----------");
        }
    }


}
