package com.silianchuangye.sumao.success.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.silianchuangye.sumao.success.R;

/**
 * Created by Administrator on 2016/8/26 0026.
 */
public class ProgressDialogUtils {
    public static   ProgressDialog dialog;
    private Context context;


    public static void show(Context context){
        dialog = new ProgressDialog(context);
        dialog.setTitle("提示");
        dialog.setMessage("正在上传…………");
        dialog.setIcon(R.drawable.ic_launcher);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.setIndeterminate(false);
        dialog.show();
    }
    public static void dismiss(){
        dialog.dismiss();
    }
}
