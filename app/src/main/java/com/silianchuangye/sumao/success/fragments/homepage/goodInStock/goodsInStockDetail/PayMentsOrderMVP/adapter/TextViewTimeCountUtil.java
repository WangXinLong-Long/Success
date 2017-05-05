package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.adapter;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/8/4.
 */
public class TextViewTimeCountUtil extends CountDownTimer{


    private TextView tv;// 按钮

    // 在这个构造方法里需要传入三个参数，一个是Activity，一个是总的时间millisInFuture，一个是countDownInterval，然后就是你在哪个按钮上做这个是，就把这个按钮传过来就可以了
    public TextViewTimeCountUtil(long millisInFuture,
                                 long countDownInterval, TextView tv) {
        super(millisInFuture, countDownInterval);
        this.tv = tv;

    }

    @Override
    public void onTick(long millisUntilFinished) {
        tv.setText("剩余支付时间:"+DateFormatUtils.getHours(millisUntilFinished));
       // Log.d("有没有进来这个方法","有没有进来这个方法");
    }

    @Override
    public void onFinish() {


    }


}
