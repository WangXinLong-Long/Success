package com.silianchuangye.sumao.success;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.easemob.chat.EMChat;
import com.silianchuangye.sumao.success.HX.DemoHelper;
import com.silianchuangye.sumao.success.utils.HXUtils.HelpDeskPreferenceUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/4/14 0014.
 */
public class MyApp extends Application {

    private static MyApp instance;
    //login user name;
    public final String PREF_USERNAME = "username";
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        final Context applicationContext = this;
        mContext = getApplicationContext();
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

    public static MyApp getInstance() {
        return instance;
    }

    public static Context getContext() {
        return mContext;
    }

    public static void getUnique() {
        SharedPreferences sp = mContext.getSharedPreferences("sumao", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();

        RequestParams unique_rp = new RequestParams(SuMaoConstant.SUMAO_IP + "/rest/model/atg/rest/SessionConfirmationActor/getSessionConfirmationNumber");
        x.http().post(unique_rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                 String   unique = object.getString("sessionConfirmationNumber");
                    Log.d("unique", "unique" + unique);
                    /**
                     * 把唯一标识储存在SharedPreferences
                     */
                    editor.putString("unique", unique);
                    editor.commit();
                } catch (Exception e) {
                    Log.d("exception", "解析唯一标识时错误！");
                }

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
