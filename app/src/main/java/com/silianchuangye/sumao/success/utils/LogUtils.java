package com.silianchuangye.sumao.success.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/6/28 0028.
 */
public class LogUtils {
    static boolean  flag = true;
    static String  TAG  = "test";
   public static void log(String s)
   {
       if(flag)
       {
            Log.i(TAG,"TAG------->"+s);
       }
   }

}
