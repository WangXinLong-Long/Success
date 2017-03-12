package com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.medol.IMessagePushMedol;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.medol.MessagePushMedol;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.medol.MessagePushcallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.view.IMessagePushView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.messagePush.view.MessagePushActivity;

/**
 * Created by junjun on 2017/3/11.
 */
public class MessagePushPresenter {
    private IMessagePushView iMessagePushView;
    private Context context;



    public void MessagePushPresenter(IMessagePushView iMessagePushView){
        this.iMessagePushView=iMessagePushView;
    }

    public void getMessagePushResult(String pageNum, boolean UpdateOrLoad, final String unique){
        IMessagePushMedol iMessagePushMedol=new MessagePushMedol(pageNum,UpdateOrLoad,unique);

        iMessagePushMedol.getMesssagePush(new MessagePushcallback() {
            @Override
            public void callbackMessagePush(String pageNum, boolean updateOrload) {
              //  iMessagePushView.setMessagePushResult);
                iMessagePushView.setMessagePushResult(pageNum,updateOrload,unique);
            }
        });

    }
}
