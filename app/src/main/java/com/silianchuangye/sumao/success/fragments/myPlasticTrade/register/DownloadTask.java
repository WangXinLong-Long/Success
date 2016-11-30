package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/8/24 0024.
 */
public class DownloadTask extends AsyncTask<Integer, Integer, String> {
    //后面尖括号内分别是参数（线程休息时间），进度(publishProgress用到)，返回值 类型

    private Context mContext=null;
    private ProgressBar mProgressBar=null;
    private TextView mTextView=null;
    public DownloadTask(Context context,ProgressBar pb,TextView tv){
        this.mContext=context;
        this.mProgressBar=pb;
        this.mTextView=tv;
    }
    /*
     * 第一个执行的方法
     * 执行时机：在执行实际的后台操作前，被UI 线程调用
     * 作用：可以在该方法中做一些准备工作，如在界面上显示一个进度条，或者一些控件的实例化，这个方法可以不用实现。
     * @see android.os.AsyncTask#onPreExecute()
     */
    @Override
    protected void onPreExecute() {

        Log.d("sn", "00000");
        super.onPreExecute();
    }

    /*
     * 执行时机：在onPreExecute 方法执行后马上执行，该方法运行在后台线程中
     * 作用：主要负责执行那些很耗时的后台处理工作。可以调用 publishProgress方法来更新实时的任务进度。该方法是抽象方法，子类必须实现。
     * @see android.os.AsyncTask#doInBackground(Params[])
     */
    @Override
    protected String doInBackground(Integer... params) {

        Log.d("sn", "1111111");
        for(int i=0;i<=100;i++){
//            mProgressBar.setProgress(i);
            publishProgress(i);
            try {
                Thread.sleep(params[0]);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
        return "执行完毕";
    }

    /*
     * 执行时机：这个函数在doInBackground调用publishProgress时被调用后，UI 线程将调用这个方法.虽然此方法只有一个参数,但此参数是一个数组，可以用values[i]来调用
     * 作用：在界面上展示任务的进展情况，例如通过一个进度条进行展示。此实例中，该方法会被执行100次
     * @see android.os.AsyncTask#onProgressUpdate(Progress[])
     */
    @Override
    protected void onProgressUpdate(Integer... values) {

        Log.d("sn", "2222222222");
        mTextView.setText(values[0]+"%");
        super.onProgressUpdate(values);
    }

    /*
     * 执行时机：在doInBackground 执行完成后，将被UI 线程调用
     * 作用：后台的计算结果将通过该方法传递到UI 线程，并且在界面上展示给用户
     * result:上面doInBackground执行后的返回值，所以这里是"执行完毕"
     * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
     */
    @Override
    protected void onPostExecute(String result) {

        Log.d("sn", "3333333333");

        super.onPostExecute(result);
    }




}
