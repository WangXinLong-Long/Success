package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.model;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/13 0013.
 */
public class GoodsInStockDetailModel implements IGoodsInStockDetailModel {
    String cl_id;


    public GoodsInStockDetailModel(String cl_id) {
        this.cl_id = cl_id;
    }

    @Override
    public void getGoodsInStockDetailResult(final goodsInStockDetailCallback callback) {
      /*  GoodsInStockDetailBean goodsInStockDetailBean = new GoodsInStockDetailBean();
        goodsInStockDetailBean.setMstEbsSkuInfoId(2020+"1");
        List<SimilarProduct> similarProducts = new ArrayList<>();
        List<String> cl_jhfangshi = new ArrayList<>();
        ArrayList<CLAttribute> cl_attributes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SimilarProduct similarProduct = new SimilarProduct();
            similarProduct.setCl_id(2021+""+i);
            similarProduct.setCl_shuliang(12+i+"");
            similarProduct.setCl_cangku("仓库"+i);
            similarProduct.setCl_jine(12+""+i);
            similarProduct.setCl_mingcheng("名称"+1);
            similarProducts.add(similarProduct);
            cl_jhfangshi.add("交货方式"+i);
            CLAttribute clAttribute = new CLAttribute();
            clAttribute.setAttrName("姓名");
            clAttribute.setAttrValue("张三"+i);
            cl_attributes.add(clAttribute);
        }
        RelatedProduct relatedProduct = new RelatedProduct();
        relatedProduct.setSimilarProduct(similarProducts);
        goodsInStockDetailBean.setRelatedProducts(relatedProduct);
        goodsInStockDetailBean.setCl_jifen("2");
        goodsInStockDetailBean.setCl_cpid("2022"+1);
        goodsInStockDetailBean.setCl_hyfenlei("分类");
        goodsInStockDetailBean.setCl_gongsiId("2023"+1);
        goodsInStockDetailBean.setCl_gongsi("公司"+1);
        goodsInStockDetailBean.setCl_qigou(2+"");
        goodsInStockDetailBean.setCl_jine("13");
        goodsInStockDetailBean.setCl_fenlei("分类");
        goodsInStockDetailBean.setCl_paihao("牌号");
        goodsInStockDetailBean.setCl_shuliang("13");
        goodsInStockDetailBean.setCl_xiaobian("1");
        goodsInStockDetailBean.setCl_mingcheng("名称"+1);
        goodsInStockDetailBean.setContract("www.baidu.com");
        goodsInStockDetailBean.setCl_scqiye("企业"+1);
        goodsInStockDetailBean.setCl_yingyong("应用"+1);
        goodsInStockDetailBean.setCl_id("2023"+1);
        goodsInStockDetailBean.setCl_jhfangshi(cl_jhfangshi);
        goodsInStockDetailBean.setCl_shijian(System.currentTimeMillis()+"");
        goodsInStockDetailBean.setCl_type("类型"+1);
        goodsInStockDetailBean.setCl_shijianend(System.currentTimeMillis()+"");
        goodsInStockDetailBean.setAmountUnitScale("元");
        goodsInStockDetailBean.setCl_jituan("集团"+1);
        goodsInStockDetailBean.setCl_dizhi("地址"+1);
        goodsInStockDetailBean.setCl_diqu("地区"+1);
        goodsInStockDetailBean.setCl_cangku("仓库"+1);
        goodsInStockDetailBean.setCl_guojia("中国"+1);

        goodsInStockDetailBean.setCl_attribute(cl_attributes);
        callback.callbackGoodsInStockDetail(goodsInStockDetailBean);*/
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/getProduct";


        LogUtils.log("URL----->"+url+"<-----URL");
        RequestParams requestParams = new RequestParams(url);
        requestParams.addParameter("productId",cl_id+"");
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {

                private GoodsInStockDetailBean goodsInStockDetailBean;

                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("现货：详情--->" + result + "<---现货：详情");
                    Gson gson = new Gson();
                    goodsInStockDetailBean = gson.fromJson(result,GoodsInStockDetailBean.class);
                    callback.callbackGoodsInStockDetail(goodsInStockDetailBean);
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
