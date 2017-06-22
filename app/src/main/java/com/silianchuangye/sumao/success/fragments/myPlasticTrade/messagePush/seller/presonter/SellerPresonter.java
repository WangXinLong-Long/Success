package com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.seller.presonter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.seller.callback.SellerCallBack;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.seller.dao.SellerDao;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.seller.dao.SellerDaoimpl;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.seller.view.SellerMessageView;

/**
 * Created by junjun on 2017/6/21.
 */
public class SellerPresonter {
    SellerMessageView sellerMessageView;

    public SellerPresonter(SellerMessageView sellerMessageView) {
        this.sellerMessageView = sellerMessageView;
    }

    public void geinfo(String msgId,String sender,String title,String sendStartTime,String sendEndTime,String page,String unique){
        //sellerMessageView.getInfoforseller(new Se);
        SellerDao sellerDao=new SellerDaoimpl(msgId,sender,title,sendStartTime,sendEndTime,page,unique);
        sellerDao.getSellerinfo(new SellerCallBack() {
            @Override
            public void getSellerinfo(String result) {
                sellerMessageView.getInfoforseller(result);
            }
        });
    }
}
