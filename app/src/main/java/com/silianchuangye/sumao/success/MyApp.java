package com.silianchuangye.sumao.success;

import android.app.Application;
import android.content.Context;

import com.easemob.chat.EMChat;
import com.silianchuangye.sumao.success.HX.DemoHelper;
import com.silianchuangye.sumao.success.utils.HXUtils.HelpDeskPreferenceUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/4/14 0014.
 */
public class MyApp extends Application {
    public static Context applicationContext;
    private static MyApp instance;
    //login user name;
    public final String PREF_USERNAME = "username";
    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        instance = this;
        String appkey = HelpDeskPreferenceUtils.getInstance(this).getSettingCustomerAppkey();
        EMChat.getInstance().setAppkey(appkey);
        DemoHelper.getInstance().init(applicationContext);
        //初始化
        //初始化Jpush
        JPushInterface.setDebugMode(true);
        JPushInterface.init(applicationContext);
    }

    public static  MyApp getInstance(){return  instance;};
}
