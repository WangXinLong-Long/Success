package com.silianchuangye.sumao.success;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.easemob.chat.EMChat;
import com.silianchuangye.sumao.success.HX.DemoHelper;
import com.silianchuangye.sumao.success.utils.HXUtils.HelpDeskPreferenceUtils;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/4/14 0014.
 */
public class MyApp extends Application {

    private static MyApp instance;
    //login user name;
    public final String PREF_USERNAME = "username";
    @Override
    public void onCreate() {
        super.onCreate();
        final  Context  applicationContext = this;
        instance = this;
        new Runnable() {

            @Override
            public void run() {
                // put your logic here!
                // use the mContext instead of this here

                String appkey = HelpDeskPreferenceUtils.getInstance(applicationContext).getSettingCustomerAppkey();
                EMChat.getInstance().setAppkey(appkey);
                DemoHelper.getInstance().init(applicationContext);
                //初始化
                //初始化Jpush
                JPushInterface.setDebugMode(true);
                JPushInterface.init(applicationContext);
                x.Ext.init(instance);
                x.Ext.setDebug(true);
            }
        }.run();

    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static  MyApp getInstance(){return  instance;};
}
