package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class FirmShowImageActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView shouImage_titlebar;
    private ImageView iv_child_title_bar_back;
    private TextView tv_child_title_bar_title;
    private String path;
    private ImageOptions imageOptions;
    URL url = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_show_image);
        shouImage_titlebar= (ImageView) findViewById(R.id.show_Image);
        shouImage_titlebar.setOnClickListener(this);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        //tv_child_title_bar_title.setOnClickListener(this);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(this);
        Bundle bundle=getIntent().getExtras();
        String title=bundle.getString("title");
        path=bundle.getString("path");
        tv_child_title_bar_title.setText(title);
        showImage();
//        //获得BitmapUtils的对象
//                BitmapUtils bitmapUtils  = new BitmapUtils(this);
////                bitmapUtils.configDefaultLoadingImage(R.mipmap.ic_launcher);//默认背景图片
////                 bitmapUtilstils.configDefaultLoadFailedImage(R.mipmap.ic_launcher);//加载失败图片
//                bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);//设置图片压缩类型
//                // 加载网络图片
//                 bitmapUtils.display(image, url);


//           new Thread(){
//               @Override
//               public void run() {
//                   super.run();
//                   try {
//                   url = new URL("http://images.csdn.net/20130609/zhuanti.jpg");
//                   shouImage_titlebar.setImageBitmap(BitmapFactory.decodeStream(url.openStream()));
//                   } catch (MalformedURLException e) {
//                       e.printStackTrace();
//                   } catch (IOException e) {
//                       e.printStackTrace();
//                   }
//               }
//           }.start();


    }
    public void showImage(){
       if (path.equals("abc")){
           Toast.makeText(FirmShowImageActivity.this, "扫描件不存在", Toast.LENGTH_SHORT).show();
       }else{
           Loding.show(FirmShowImageActivity.this,"图片加载中...",false,null);
           String url= SuMaoConstant.SUMAO_CONTRACT_IP+path;
           Log.d("图片上传中",""+url);

           imageOptions = new ImageOptions.Builder().
                    setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
                   .setRadius(DensityUtil.dip2px(5))
                   .setCrop(true)
                  // .setImageScaleType(ImageView.ScaleType.FIT_CENTER)CENTER_INSIDE / centerInside
                   .setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                   .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
                   .setUseMemCache(true)
                   .build();
           x.image().bind(shouImage_titlebar, url, imageOptions, new Callback.CommonCallback<Drawable>() {
               @Override
               public void onSuccess(Drawable result) {
                   Loding.dis();
               }

               @Override
               public void onError(Throwable ex, boolean isOnCallback) {
                   Log.v("加载图片出错",ex.getMessage());
                   Loding.dis();
                   Toast.makeText(FirmShowImageActivity.this, "图片加载出错,请稍后重试!", Toast.LENGTH_SHORT).show();

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


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_child_title_bar_back:
                FirmShowImageActivity.this.finish();
                break;
        }

    }
}
