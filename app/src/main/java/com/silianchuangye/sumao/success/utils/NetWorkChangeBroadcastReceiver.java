package com.silianchuangye.sumao.success.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkChangeBroadcastReceiver extends BroadcastReceiver {

    public static final String NET_CHANGE = "net_change";
    //标记当前网络状态，0为无可用网络状态，1表示有。  
    public static final String NET_TYPE = "net_type";

    @Override
    public void onReceive(Context context, Intent intent) {
//        CameraManager cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);


        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //移动数据  
        NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        //wifi网络  
        NetworkInfo wifiNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
//            网络状态全部不可用  
            Intent netIntent = new Intent(NET_CHANGE);
            netIntent.putExtra(NET_TYPE, 0);
            context.sendBroadcast(netIntent);
            return;
        }
        if (mobNetInfo.isConnected() || wifiNetInfo.isConnected()) {
            Intent netIntent = new Intent(NET_CHANGE);
            netIntent.putExtra(NET_TYPE, 1);
            context.sendBroadcast(netIntent);
            return;
        }


        if (mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
//            手机没有处于wifi网络而是处于移动网络  
        }

    }
}