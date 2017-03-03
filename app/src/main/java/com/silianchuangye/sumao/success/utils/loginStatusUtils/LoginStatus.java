package com.silianchuangye.sumao.success.utils.loginStatusUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.silianchuangye.sumao.success.MyApp;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by Jobs Created on 2017/2/10.
 */
@Aspect
public class LoginStatus {
    @Pointcut("execution(@com.silianchuangye.sumao.success.utils.loginStatusUtils.AspectJLoginStatus * *(..))")
    public void annoBehavior(){

    }
    @Around("annoBehavior()")
    public Object dealBehavior(ProceedingJoinPoint point){
        MethodSignature signature = (MethodSignature) point.getSignature();
        Object aThis = point.getThis();
        AspectJLoginStatus annotation = signature.getMethod().getAnnotation(AspectJLoginStatus.class);
        String value = annotation.value();
        MyApp.getUnique();
       String unique =  MyApp.getContext().getSharedPreferences("sumao", Context.MODE_PRIVATE).getString("unique","");
        Object retVal = null;
        if (unique.equals("false")) {
            Toast.makeText(MyApp.getContext(), "请登录后进行操作——————>", Toast.LENGTH_SHORT).show();
            Intent intent1  = new Intent((Activity)aThis, LoginUserActivity.class);
            intent1.putExtra("roles", "buyer");
            MyApp.getContext().startActivity(intent1);
        } else {
            try {
                retVal = point.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return retVal;
    }
}
