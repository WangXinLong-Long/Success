package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/14 0014.
 */
public class RelatedProduct implements Serializable{
    private List<SimilarProduct> similarProduct;

    public List<SimilarProduct> getSimilarProduct() {
        return similarProduct;
    }

    public void setSimilarProduct(List<SimilarProduct> similarProduct) {
        this.similarProduct = similarProduct;
    }
}
