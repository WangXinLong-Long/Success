package com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2016/11/23.
 */
public class AuctionBean implements Serializable {

    /**
     * allCount : 10
     * productItem : [{"startDate":1479873000000,"wareHouse":"上海阳超库","status":"0","skuId":"sku990005","endDate":1479970800000,"quantity":111,"type":"englishAuctionProduct","productName":"三菱M7026U","supplier":"中国石油天然气股份有限公司西北化工销售分公司","productId":"prod1080006"},{"startDate":1479871560000,"wareHouse":"上游配送库-西北","status":"1","skuId":"sku990004","endDate":1480003200000,"quantity":111,"type":"sealedAuctionProduct","productName":"上海M2600R","supplier":"中国石油天然气股份有限公司西北化工销售分公司","productId":"prod1080005"},{"startDate":1479871800000,"wareHouse":"上游配送库-东北","status":"2","skuId":"sku990003","endDate":1479871790011,"quantity":111,"type":"englishAuctionProduct","productName":"上海SH151","supplier":"中国石油天然气股份有限公司西北化工销售分公司","productId":"prod1080004"},{"startDate":1479916800000,"wareHouse":"上海阳超库","status":"0","skuId":"sku990002","endDate":1480176000000,"quantity":111,"type":"englishAuctionProduct","productName":"SABIC 32T","supplier":"中国石油天然气股份有限公司西北化工销售分公司","productId":"prod1080003"},{"startDate":1479916800000,"wareHouse":"余姚集装箱库","status":"0","skuId":"sku990001","endDate":1480435200000,"quantity":100,"type":"englishAuctionProduct","productName":"上海GM1600E","supplier":"中国石油天然气股份有限公司西北化工销售分公司","productId":"prod1080001"},{"startDate":1479830400000,"wareHouse":"上海阳超库","status":"2","skuId":"sku980001","endDate":1479830390089,"quantity":11,"type":"englishAuctionProduct","productName":"上海F560EPS","supplier":"中国石油天然气股份有限公司西北化工销售分公司","productId":"prod1070003"},{"startDate":1480435200000,"wareHouse":"上海安石仓库","status":"1","skuId":"sku960016","endDate":1480521600000,"quantity":100,"type":"sealedAuctionProduct","productName":"SABIC PPRCP13X","supplier":"中国石油天然气股份有限公司西北化工销售分公司","productId":"prod1030014"},{"startDate":1480435200000,"wareHouse":"上海安石仓库","status":"0","skuId":"sku960015","endDate":1480521600000,"quantity":100,"type":"englishAuctionProduct","productName":"上海GM1600E","supplier":"中国石油天然气股份有限公司西北化工销售分公司","productId":"prod1030013"},{"startDate":1477376100000,"wareHouse":"上海阳超库","status":"2","skuId":"sku900021","endDate":1477376090012,"quantity":100,"type":"englishAuctionProduct","productName":"上海041","supplier":"中国石油天然气股份有限公司西北化工销售分公司","productId":"prod970022"},{"startDate":1476261000000,"wareHouse":"上游配送库-西北","status":"2","skuId":"sku820007","endDate":1476260990012,"quantity":1000,"type":"englishAuctionProduct","productName":"SABIC 22T","supplier":"中国石油天然气股份有限公司西北化工销售分公司","productId":"prod890006"}]
     */

    private int allCount;
    private List<ProductItemBean> productItem;

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public List<ProductItemBean> getProductItem() {
        return productItem;
    }

    public void setProductItem(List<ProductItemBean> productItem) {
        this.productItem = productItem;
    }

}
