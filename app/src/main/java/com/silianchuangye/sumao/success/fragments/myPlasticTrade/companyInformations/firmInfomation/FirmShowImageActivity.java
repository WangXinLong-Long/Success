package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

public class FirmShowImageActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView shouImage_titlebar;
    private ImageView iv_child_title_bar_back;
    private TextView tv_child_title_bar_title;
    private String path;
    private ImageOptions imageOptions;

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
    }
    public void showImage(){
       if (path.equals("abc")){
           Toast.makeText(FirmShowImageActivity.this, "扫描件不存在", Toast.LENGTH_SHORT).show();
       }else{
           String url= SuMaoConstant.SUMAO_CONTRACT_IP+path;
           imageOptions = new ImageOptions.Builder().
                   setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
                   .setRadius(DensityUtil.dip2px(5))
                   .setCrop(true)
                   .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
//                   .setLoadingDrawableId(R.mipmap.logo)
//                   .setFailureDrawableId(R.mipmap.logo)
                   .build();
           x.image().bind(shouImage_titlebar, url, imageOptions);
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
