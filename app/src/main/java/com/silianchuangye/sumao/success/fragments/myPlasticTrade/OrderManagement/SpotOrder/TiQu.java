package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;

/**
 * Created by Administrator on 2016/8/31.
 */
public class TiQu extends Activity{
    private Context ctx;
   public TiQu(Context ctx){
       this.ctx=ctx;
   }
    public void showLogin(){
        Intent intent=new Intent(ctx, LoginUserActivity.class);
        intent.putExtra("roles","buyer");
        ctx.startActivity(intent);
    }
}
