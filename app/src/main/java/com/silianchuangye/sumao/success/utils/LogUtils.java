package com.silianchuangye.sumao.success.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/6/28 0028.
 */
public class LogUtils {
    static boolean flag = true;
    static String TAG = "test";
    private static int LOG_MAXLENGTH = 2000;

    public static void log(String s) {
        if (flag) {
            int strLength = s.length();
            int start = 0;
            int end = LOG_MAXLENGTH;

            while (strLength > end) {
                Log.i(TAG, s.substring(start, end));
                Log.e(TAG, "------------");
                start = end;
                end = end + LOG_MAXLENGTH;
            }
            Log.i(TAG, s.substring(start, strLength));

        }
    }

    public static void logs(String s) {
        if (flag) {
            if (s.length() > 4000) {
                for (int i = 0; i < s.length(); i += 4000) {
                    if (i + 4000 < s.length())
                        Log.i("rescounter" + i, s.substring(i, i + 4000));
                    else
                        Log.i("rescounter" + i, s.substring(i, s.length()));
                }
            } else
                Log.i("resinfo", s);
        }

    }
}


