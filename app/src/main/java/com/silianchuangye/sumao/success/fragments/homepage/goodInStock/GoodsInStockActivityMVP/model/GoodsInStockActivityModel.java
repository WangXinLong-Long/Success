package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.model;

import android.util.Log;

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
    boolean fenlei;
    boolean diqu;
    boolean shengchan;
    boolean yingyong;
    String feileiString;
    String productName;
    String warehouseName;
    String title;
    String pattern;//交易模式
    String regions;//    大区
    String provinces;//省
    String cities;//市
    String categoryNames;//分类
    String appNames;//应用
    String gradeNums;//牌号
    String groups;//牌号
    String pageNum;//当前页码
    String updateOrLoad;
    String searchField = "";
    String urlSearch;
    private GoodsInStockActivityBean goodsInStockActivityBean;

    public GoodsInStockActivityModel(boolean fenlei, boolean diqu, boolean shengchan, boolean yingyong, String feileiString, String productName, String title, String warehouseName) {
        this.fenlei = fenlei;
        this.diqu = diqu;
        this.shengchan = shengchan;
        this.yingyong = yingyong;
        this.feileiString = feileiString;
        this.productName = productName;
        this.title = title;
        this.warehouseName = warehouseName;
    }

    public GoodsInStockActivityModel(String groups, String pattern, String regions, String provinces, String cities,
                                     String categoryNames, String appNames, String gradeNums, String pageNum, String updateOrLoad) {
        this.groups = groups;
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
                Log.d("地区","地区"+regions);
                searchField = "地区"+regions;

            }
            if (provinces != null) {
                jsonObject.put("provinces", provinces);
            }
            if (cities != null) {
                jsonObject.put("cities", cities);
            }
            if (categoryNames != null) {
                jsonObject.put("categoryNames", categoryNames);
                searchField = "分类"+categoryNames;
            }
            if (appNames != null) {
                jsonObject.put("appNames", appNames);
                searchField = "应用"+appNames;
            }
            if (groups != null) {
                jsonObject.put("groupsNames", groups);
                searchField = "供应"+groups;
            }
            if (gradeNums != null) {
                jsonObject.put("gradeNums", gradeNums);
            }

            jsonObject.put("pattern", pattern);
            jsonObject.put("pageNum", pageNum);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestParams.setBodyContent(jsonObject.toString());
        Log.d("传入的参数",jsonObject.toString());
        LogUtils.log("首页：现货requestParams--->" + requestParams + "<---首页：现货requestParams");
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {



                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("首页：现货--->" + result + "<---首页：现货");
                    Gson gson = new Gson();
                    goodsInStockActivityBean = gson.fromJson(result, GoodsInStockActivityBean.class);
                    callback.callbackInGoodsInStockActivity(goodsInStockActivityBean, updateOrLoad,searchField);
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

    @Override
    public void getGoodsInStockResultForSearch(final GoodsInStockActivityCallback callback) {
        urlSearch=SuMaoConstant.SUMAO_IP+"/rest/model/atg/endeca/HomeActor/EndecaAppScreenResult";
        if (!fenlei&&!diqu&&!shengchan&&!yingyong){
            if (title.equals("现货")){
                urlSearch+="?N=0&Nr=product.type:fixedProduct";
            }else if (title.equals("预售")){
                urlSearch+="?N=0&Nr=product.type:forward-pricing-product";
            }
        }else{
            urlSearch+=feileiString;
        }
        if (productName.length()>0){
            urlSearch+="&alias="+productName;
        }
        if (warehouseName.length()>0){
            urlSearch+="&wareHouse="+warehouseName;
        }
        urlSearch+="&No=0";
        Log.d("查询的url",urlSearch);
        RequestParams rp=new RequestParams(urlSearch);

        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("查询的返回值",result);
                Gson gson = new Gson();
                goodsInStockActivityBean = gson.fromJson(result, GoodsInStockActivityBean.class);
                callback.callbackInGoodsInStockActivity(goodsInStockActivityBean,updateOrLoad,"");

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
