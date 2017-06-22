package com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.seller.dao;

import android.util.Log;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.seller.callback.SellerCallBack;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by junjun on 2017/6/21.
 */
public class SellerDaoimpl implements SellerDao {
    private String msgId;
    private String sender;
    private String title;
    private String sendStartTime;
    private String sendEndTime;
    private String page;
    private String _dynSessConf;
    private String resultInfo;
    private boolean update;

    public SellerDaoimpl(String msgId, String sender, String title, String sendStartTime, String sendEndTime, String page,String _dynSessConf) {
        this.msgId = msgId;
        this.sender = sender;
        this.title = title;
        this.sendStartTime = sendStartTime;
        this.sendEndTime = sendEndTime;
        this.page = page;
        this._dynSessConf=_dynSessConf;
    }

    @Override
    public void getSellerinfo(final SellerCallBack sellerCallBack) {
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/sellerNoticeList";
        RequestParams rp=new RequestParams(url);
       // rp.addParameter("");
        if (!msgId.equals("")&&!msgId.isEmpty()){
            rp.addParameter("msgId",msgId);
        }
        if (!sender.equals("")&&!sender.isEmpty()){
            rp.addParameter("sender",sender);
        }
        if (!title.equals("")&&!title.isEmpty()){
            rp.addParameter("title",title);
        }
        if (!sendStartTime.equals("")&&!sendStartTime.isEmpty()){
            rp.addParameter("sendStartTime",sendStartTime);
        }
        if (!sendEndTime.equals("")&&!sendEndTime.isEmpty()){
            rp.addParameter("sendEndTime",sendEndTime);
        }
        rp.addParameter("page",page);
        rp.addParameter("_dynSessConf",_dynSessConf);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("卖家通知的值",result);
                resultInfo=result;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("出错的",ex.getMessage()+"错误");
                resultInfo="错误的原因"+ex.getMessage();

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                sellerCallBack.getSellerinfo(resultInfo);

            }
        });

    }
}
