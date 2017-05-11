package com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.medol;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
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
    private String title="test";
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
        rp.setAsJsonContent(true);
        JSONObject jsonObject=new JSONObject();


            //rp.addParameter("sendId",Id);
            try {
                if (Id!="productName" && Id!=null||!Id.equals("test")&&!Id.equals("")) {
                    jsonObject.put("sendId", Id);
                }
                if (sendType!="" && sendType!=null){
                    if (sendType.equals("塑贸通知")){
                        jsonObject.put("sendType",0);
                    }else if (sendType.equals("企业信息修改")){
                        jsonObject.put("sendType",1);
                    }
                }
                if (title!="productName" && title!=null &&!title.equals("productName")&&!title.equals("")){
                    jsonObject.put("title",title);
                }
                if (sendStartTime!=null&&sendStartTime!=""){
                    jsonObject.put("sendStartTime",sendStartTime);
                }
                if (sendEndTime!=""&&sendEndTime!=null){
                    jsonObject.put("sendEndTime",sendEndTime);
                }
                if (pageNum!=""&&sendEndTime!=null){
                    jsonObject.put("page",pageNum);
                }
                jsonObject.put("_dynSessConf",unique);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        rp.setBodyContent(jsonObject.toString());
        Log.d("参数的值",jsonObject+"");

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
