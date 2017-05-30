package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.informationView;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.shockwave.pdfium.PdfDocument;

import com.silianchuangye.sumao.success.MyApp;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.apache.commons.net.io.ToNetASCIIInputStream;

import java.io.File;
import java.util.List;

/**
 * 合同信息
 */
public class ContractInformationActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener,View.OnClickListener {
    private static final String TAG = ContractInformationActivity.class.getSimpleName();
    private ImageView iv_child_title_bar_back;
    private TextView tv_child_title_bar_title;
    private PDFView pdfView;
    private String orderId;
    private String contract;
    private int result;
    private Handler mHandler;
    private String filename;
    private final static int REQUEST_CODE = 42;
    public static final int PERMISSION_CODE = 42042;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_information);
        contract = getIntent().getStringExtra("contract");
        orderId=getIntent().getStringExtra("orderId");
        pdfView= (PDFView) findViewById(R.id.pdfView);
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
        iv_child_title_bar_back.setOnClickListener(this);
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("合同信息");
        filename=orderId+".pdf";
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }else{
            Log.d("没有权限","没有权限");
            new MyThread(){
                @Override
                public void run() {
                    super.run();
                }
            }.start();
        }


        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //操作界面
                super.handleMessage(msg);

                switch (msg.what){
                    case 1:

                        if (result==0||result==1){
                            Log.d("if","if");
                            displayFromFile(new File(Environment.getExternalStorageDirectory()+"/"+filename));
                        }else{
                            Log.d("else","else");
                            //displayFromAssets("bao.pdf");
                            Toast.makeText(ContractInformationActivity.this, "文件下载失败!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }

            }
        };






    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("进来","进来了");
        doNext(requestCode,grantResults);
    }
    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                Log.d("确定","确定");
                        new MyThread(){
                            @Override
                            public void run() {
                                super.run();
                            }
                        }.start();
            //}

            } else {
                // Permission Denied
                Log.d("拒绝","拒绝");
                finish();
            }
        }
    }

    public class MyThread extends Thread {
        public void run() {
            super.run();

            loadNetWork();
            Message msg = new Message();

            mHandler.sendEmptyMessage(1);

            mHandler.sendMessage(msg);//向Handler发送消息，
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        delete(Environment.getExternalStorageDirectory().toString(),filename);

    }

    public boolean delete(String SDPATH,String fileName) {

        //SDPATH目录路径，fileName文件名

        File file = new File(SDPATH+ "/" + fileName);
        if (file == null || !file.exists() || file.isDirectory()){
            return false;
        }
        file.delete();

        return true;
    }

    public int loadNetWork(){
        if (contract!=""||!contract.equals("")||contract!=null){
            LogUtils.log(SuMaoConstant.SUMAO_CONTRACT_IP+contract);
            Log.d("合同的路径",SuMaoConstant.SUMAO_CONTRACT_IP+contract);

            String url=SuMaoConstant.SUMAO_CONTRACT_IP+contract;
            Log.d("filename的值",filename);
            HttpDownloader downloader = new HttpDownloader();
            result= downloader.downFile(url,"",filename);
            System.out.println(result);
            Log.d("mp3",result+"");

        }else{
            Toast.makeText(ContractInformationActivity.this, "该订单暂时还没有生成合同信息", Toast.LENGTH_SHORT).show();
        }
        return result;
    }


    private void displayFromAssets(String assetFileName) {
        pdfView.fromAsset(assetFileName)   //设置pdf文件地址
                .defaultPage(0)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                //.scrollHandle(new DefaultScrollHandle(this))
                .load();
    }

    private void displayFromFile(File file) {
        pdfView.fromFile(file)   //设置pdf文件地址
                 .defaultPage(0)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                //.scrollHandle(new DefaultScrollHandle(this))
                .load();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_child_title_bar_back:
                finish();
                break;
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
//                                           @NonNull int[] grantResults) {
//        if (requestCode == PERMISSION_CODE) {
//            if (grantResults.length > 0
//                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                launchPicker();
//            }
//        }
//    }
//    void launchPicker() {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("application/pdf");
//        try {
//            startActivityForResult(intent, REQUEST_CODE);
//        } catch (ActivityNotFoundException e) {
//            //alert user that file manager not working
//            Toast.makeText(this, "出现了错误！", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        Log.e(TAG, "title = " + meta.getTitle());
        Log.e(TAG, "author = " + meta.getAuthor());
        Log.e(TAG, "subject = " + meta.getSubject());
        Log.e(TAG, "keywords = " + meta.getKeywords());
        Log.e(TAG, "creator = " + meta.getCreator());
        Log.e(TAG, "producer = " + meta.getProducer());
        Log.e(TAG, "creationDate = " + meta.getCreationDate());
        Log.e(TAG, "modDate = " + meta.getModDate());

        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }
    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }


}
