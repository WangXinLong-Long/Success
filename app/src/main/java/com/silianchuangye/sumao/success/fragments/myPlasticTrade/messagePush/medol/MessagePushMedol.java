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

    public MessagePushMedol(String pageNum,boolean updateOrload,String unique){
        this.pageNum=pageNum;
        this.updateOrload=updateOrload;
        this.unique=unique;


    }






    @Override
    public void getMesssagePush(final MessagePushcallback messagePushcallback) {
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/noticeList";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("page",pageNum);

      //  String unique="fasle";
        rp.addParameter("_dynSessConf",unique);
        Log.d("通知列表的rp",rp+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("通知列表的返回值",""+result);
                messagePushcallback.callbackMessagePush(pageNum,updateOrload);
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
