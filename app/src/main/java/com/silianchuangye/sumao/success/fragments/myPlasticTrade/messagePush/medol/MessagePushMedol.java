package com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.medol;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by junjun on 2017/3/11.
 */
public  class MessagePushMedol  implements IMessagePushMedol {
    private String pageNum;
    private boolean updateOrload;
  //  private Context context;
    private String unique;
    private String Id;
    private String sendType;
    private String title;
    private String sendStartTime;
    private String sendEndTime;

    public MessagePushMedol(String pageNum,boolean updateOrload,String unique){
        this.pageNum=pageNum;
        this.updateOrload=updateOrload;
        this.unique=unique;

    }

    public MessagePushMedol( String pageNum, boolean updateOrload,String unique, String id, String title, String sendType, String sendStartTime, String sendEndTime) {
        this.updateOrload = updateOrload;
        this.pageNum = pageNum;
        this.unique = unique;
        Id = id;
        this.title = title;
        this.sendType = sendType;
        this.sendStartTime = sendStartTime;
        this.sendEndTime = sendEndTime;
    }

    @Override
    public void getMesssagePush(final MessagePushcallback messagePushcallback) {
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/noticeList";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("page",pageNum);
        rp.addParameter("_dynSessConf",unique);
        Log.d("通知列表的rp",rp+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("通知列表的返回值",""+result);
                messagePushcallback.callbackMessagePush(result,updateOrload);
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

    @Override
    public void getMesssagePushForselect(final MessagePushcallback messagePushcallback) {
        String url=SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/noticeList";
        RequestParams rp=new RequestParams(url);
        if (Id!=""&&Id!=null){
            rp.addParameter("sendId",Id);
        }
        if (sendType!=""&&sendType!=null){
            if (sendType.equals("塑贸通知")){
                rp.addParameter("sendType",0);
            }else if (sendType.equals("企业信息修改")){
                rp.addParameter("sendType",1);
            }
        }
        if (title!=""&&title!=null){
            rp.addParameter("title",title);
        }
        if (sendStartTime!=null&&sendStartTime!=""){
            rp.addParameter("sendStartTime",sendStartTime);
        }
        if (sendEndTime!=""&&sendEndTime!=null){
            rp.addParameter("sendEndTime",sendEndTime);
        }
        if (pageNum!=""&&sendEndTime!=null){
            rp.addParameter("page",pageNum);
        }
        rp.addParameter("_dynSessConf",unique);
        Log.d("rp的值",rp.toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("返回值",result);
                messagePushcallback.callbackMessagePush(result,updateOrload);
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
