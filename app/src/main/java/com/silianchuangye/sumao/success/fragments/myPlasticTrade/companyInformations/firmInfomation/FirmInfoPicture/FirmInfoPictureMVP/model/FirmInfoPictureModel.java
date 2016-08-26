package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoPicture.FirmInfoPictureMVP.model;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.bean.AddressDisplay;
import com.silianchuangye.sumao.success.utils.FTPUtils;
import com.silianchuangye.sumao.success.utils.FavFTPUtil;
import com.silianchuangye.sumao.success.utils.FileTool;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Administrator on 2016/8/24 0024.
 */
public class FirmInfoPictureModel implements IFirmInfoPictureModel {
    String path;
    boolean flag;
//    192.168.32.126:21 mobile/Mm123456
    final String hostname = "192.168.32.126";
    final int port = 21;
    final String username = "mobile";
    final String password = "Mm123456";
    final String pathname = "/docs/cl_zhizhaoimage";
    final String filename = "";
    private Handler myHandler;

    public FirmInfoPictureModel(String path) {
        this.path = path;
    }
    private FileInputStream in;
    public boolean s;
    //FTP工具类
    private FTPUtils ftpUtils = null;
    //    http://192.168.32.126:7003/crsdocroot/mnt/docs/
String ss = "meili";

    @Override
    public void getFirmInfoPictureInfo(final FirmInfoPictureCallback firmInfoPictureCallback) {

        //初始化和FTP服务器交互的类
        new Thread(){
            @Override
            public void run() {
                super.run();



                firmInfoPictureCallback.callbackFirmInfoPicture(ss);
//                firmInfoPictureCallback.callbackFirmInfoPicture(s);
            }
        }.start();



//        String url = "http://192.168.32.126:7003/crsdocroot/mnt/docs/";
//        final RequestParams requestParams = new RequestParams(url);
//        requestParams.setMultipart(true);
//        requestParams.addBodyParameter("cl_zhizhaoimage", new File(path));
//        try {
//            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {
//                @Override
//                public boolean onCache(String result) {
//                    return false;
//                }
//
//                @Override
//                public void onSuccess(String result) {
//                    firmInfoPictureCallback.callbackFirmInfoPicture(result);
//                }
//
//                @Override
//                public void onError(Throwable ex, boolean isOnCallback) {
//                    LogUtils.log("--------->" + "FirmInfoPictureModel:3.2+onError" + ex.toString() + "<-----------");
//                }
//
//                @Override
//                public void onCancelled(CancelledException cex) {
//                    LogUtils.log("--------->" + "FirmInfoPictureModel:3.2+onCancelled" + "<-----------");
//                }
//
//                @Override
//                public void onFinished() {
//                    LogUtils.log("--------->" + "FirmInfoPictureModel:3.2+onFinished" + "<-----------");
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            LogUtils.log("--------->" + "FirmInfoPictureModel:3.2+printStackTrace" + e.toString() + "<-----------");
//        }


    }


}
