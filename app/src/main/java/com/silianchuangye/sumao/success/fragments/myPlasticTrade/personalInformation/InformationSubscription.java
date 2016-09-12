package com.silianchuangye.sumao.success.fragments.myPlasticTrade.personalInformation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.silianchuangye.sumao.success.R;
/**
 * 资讯订阅
 * Created by Administrator on 2016/5/9 0009.
 */
public class InformationSubscription extends Activity implements View.OnClickListener{
    private ImageView backImg,secondImg,topImg,Img1,Img2,Img3,Img4,Img5,Img6;
    private boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zixun);
        initView();
    }

    private void initView() {
        backImg= (ImageView) findViewById(R.id.img_zixun_back);
        topImg= (ImageView) findViewById(R.id.img_zixun_top);
        secondImg= (ImageView) findViewById(R.id.img_zixun_second);
//        Img1= (ImageView) findViewById(R.id.img_zixun_1);
//        Img2= (ImageView) findViewById(R.id.img_zixun_2);
//        Img3= (ImageView) findViewById(R.id.img_zixun_3);
//        Img4= (ImageView) findViewById(R.id.img_zixun_4);
//        Img5= (ImageView) findViewById(R.id.img_zixun_5);
//        Img6= (ImageView) findViewById(R.id.img_zixun_6);
        backImg.setOnClickListener(this);
        topImg.setOnClickListener(this);
        secondImg.setOnClickListener(this);
//        Img1.setOnClickListener(this);
//        Img2.setOnClickListener(this);
//        Img3.setOnClickListener(this);
//        Img4.setOnClickListener(this);
//        Img5.setOnClickListener(this);
//        Img6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_zixun_back:
                finish();
                break;
            case R.id.img_zixun_top:
                setImg(topImg);
                break;
            case R.id.img_zixun_second:
                setImg(secondImg);
                break;
//            case R.id.img_zixun_1:
//                setImg(Img1);
//                break;
//            case R.id.img_zixun_2:
//                setImg(Img2);
//                break;
//            case R.id.img_zixun_3:
//                setImg(Img3);
//                break;
//            case R.id.img_zixun_4:
//                setImg(Img4);
//                break;
//            case R.id.img_zixun_5:
//                setImg(Img5);
//                break;
//            case R.id.img_zixun_6:
//                setImg(Img6);
//                break;
        }
    }
    private void setImg(ImageView img){
        if(flag){
            img.setImageResource(R.mipmap.zixun_select);
            flag=false;
        }else{
            img.setImageResource(R.mipmap.zixun_select_null);
            flag=true;
        }
    }
}
