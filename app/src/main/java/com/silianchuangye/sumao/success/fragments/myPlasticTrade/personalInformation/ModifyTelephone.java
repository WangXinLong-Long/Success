package com.silianchuangye.sumao.success.fragments.myPlasticTrade.personalInformation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhone.CountDownTimerUtils;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhoneMVP.RegisterPhonePresenter.RegisterPhonePresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhoneMVP.RegisterPhoneView.IRegisterPhoneView;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Administrator on 2016/5/9 0009.
 */
public class ModifyTelephone extends Activity implements View.OnClickListener {

    ImageView iv_title_bar_logo, iv_title_bar_back, iv_title_bar_service, iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title;

    RelativeLayout title_modify_telephone;
    TextView omp_number;
    EditText nmb_number;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String name, email, phone;
    private TextView getNewPhoneNumberVerificationCode;
    private CountDownTimerUtils mCountDownTimerUtils;
    private CountDownTimerUtils mCountDownTimerUtils1;
    private Button Original_mobile_phone_verification_code_bn;
    private EditText Original_mobile_phone_verification_code_et;
    private EditText New_mobile_phone_verification_code_et;
    private String yanzhen;
    private String yanzhengold;
    private TextView Old_mobile_phone_verification_code_bn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_telephone);
        Old_mobile_phone_verification_code_bn= (TextView) findViewById(R.id.Old_mobile_phone_verification_code_bn);
        title_modify_telephone = ((RelativeLayout) findViewById(R.id.title_modify_telephone));
        title_modify_telephone.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));
        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title = ((TextView) findViewById(R.id.tv_title_bar_title));
        iv_title_bar_logo.setVisibility(View.INVISIBLE);
        iv_title_bar_service.setVisibility(View.INVISIBLE);
        sv_title_bar_serachView.setVisibility(View.INVISIBLE);
        iv_title_bar_search.setVisibility(View.INVISIBLE);
        iv_title_bar_back.setOnClickListener(this);
        tv_title_bar_title.setText("修改电话");
        tv_title_bar_title.setTextColor(Color.WHITE);
        Original_mobile_phone_verification_code_et= (EditText) findViewById(R.id.Original_mobile_phone_verification_code_et);
        New_mobile_phone_verification_code_et= (EditText) findViewById(R.id.New_mobile_phone_verification_code_et);
        Original_mobile_phone_verification_code_bn= (Button) findViewById(R.id.Original_mobile_phone_verification_code_bn);


        omp_number = ((TextView) findViewById(R.id.Omp_number));

        nmb_number = ((EditText) findViewById(R.id.Nmb_number));
//        sp = getSharedPreferences("silian",MODE_PRIVATE);
//        Log.e("TAG","sp.getString===="+sp.getString("count",""));
//        omp_number.setText(sp.getString("count",""));
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phoneNum");
        omp_number.setText(phone);
//        Jobs Created 获取新手机号的验证码
        getNewPhoneNumberVerificationCode = ((TextView) findViewById(R.id.New_mobile_phone_verification_code_bn));
//        Jobs Created 设置按钮倒计时的操作
        mCountDownTimerUtils = new CountDownTimerUtils(getNewPhoneNumberVerificationCode, 30000, 1000);
        mCountDownTimerUtils1 = new CountDownTimerUtils(Old_mobile_phone_verification_code_bn, 30000, 1000);
        //        Jobs Created 获取验证码操作
        getNewPhoneNumberVerificationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nmb_number.getText().toString().length() != 11) {

                    Toast.makeText(ModifyTelephone.this, "您输入的手机号不符合规则", Toast.LENGTH_SHORT).show();
                } else {
                    yanzhengphone();
                    //在这里判断手机号是否存在
                }
            }
        });
        Old_mobile_phone_verification_code_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Original_mobile_phone_verification_code_bn.setVisibility();
                mCountDownTimerUtils1.start();
                getOldRegisterPhoneInfo();
            }
        });
    }
    public void yanzhengphone(){
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/store/profile/RegistrationActor/userVerify";
        RequestParams rp=new RequestParams(url);
//        jsonObject.put("type", "mobile");
//        jsonObject.put("attr", telephone);
        rp.addParameter("type","mobile");
        rp.addParameter("attr",nmb_number.getText().toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("验证邮箱的返回值",result);
                if (result.contains("true")){
                    //updateUser();

                    mCountDownTimerUtils.start();
                    getRegisterPhoneInfo();

                }else{
                    Toast.makeText(ModifyTelephone.this, "该手机号已经注册!", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(CompanyUserUpdateActivity.this, "该邮箱已经注册!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_title_bar_back:
                finish();
                break;
            case R.id.modify_telephone_save:


                /**
                 * 这里要加n多层判断
                 */
                if (true) {
                    String telephone = nmb_number.getText().toString();
//                     sp = getSharedPreferences("silian",MODE_PRIVATE);
//                     editor = sp.edit();
//                    editor.putString("count",telephone);
//                    editor.commit();
                    Log.e("TAG", "phone-----" + phone);
                    if (phone.equals(omp_number.getText().toString())) {

                        RequestParams params = new RequestParams(SuMaoConstant.SUMAO_IP + "/rest/model/atg/store/profile/RegistrationActor/updateUser");
                        params.setCharset("UTF-8");
                        JSONObject job = new JSONObject();
                        try {
                            job.put("firstName", name);
                            job.put("email", email.trim());
                            job.put("phoneNumber", telephone);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        params.setBodyContent(job.toString());
                        SharedPreferences sp = getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                        String unique = sp.getString("unique", "");
                        params.addParameter("_dynSessConf", unique);
                        Log.e("TAG", "params====" + params);
                        x.http().post(params, new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                Log.e("TAG", "手机result-----" + result);
                                if (!result.contains("changeResult")) {
                                    Toast.makeText(ModifyTelephone.this, "您输入的手机号不符合规则", Toast.LENGTH_SHORT).show();
                                } else {
//
                                    SharedPreferences sharedPreferences=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                                    String newinfo=sharedPreferences.getString("newyanzhen","");
                                    Log.d("newinfo",newinfo);
                                    String oldinfo=sharedPreferences.getString("oldyanzhen","");

                                    Log.d("oldinfo",oldinfo);
                                    Log.d("newinfo",New_mobile_phone_verification_code_et.getText().toString());
                                    Log.d("oldinfo",Original_mobile_phone_verification_code_et.getText().toString());
                                    if (newinfo.equals(New_mobile_phone_verification_code_et.getText().toString())&&oldinfo.equals(Original_mobile_phone_verification_code_et.getText().toString())){
                                        Toast.makeText(ModifyTelephone.this, "修改手机号成功!", Toast.LENGTH_SHORT).show();
//                                        SharedPreferences sh_phone=getSharedPreferences("sumao_phone",Activity.MODE_PRIVATE);
//                                        SharedPreferences.Editor editor_phone=sh_phone.edit();
//                                        editor_phone.putString("phone",New_mobile_phone_verification_code_et.getText().toString());
//                                        editor_phone.commit();
                                        JPushInterface.setAliasAndTags(ModifyTelephone.this,New_mobile_phone_verification_code_et.getText().toString(),null);

                                        Intent intent = new Intent();
                                        setResult(0, intent);
                                        finish();
                                    }else{
                                        Toast.makeText(ModifyTelephone.this, "验证码不正确!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {

                            }

                            @Override
                            public void onCancelled(CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        });

                    } else {
                        Toast.makeText(ModifyTelephone.this, "与原手机号码不一致，修改失败", Toast.LENGTH_SHORT).show();
                    }

                }

                break;
        }
    }

    public void  getRegisterPhoneInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, Object> result = null;
                CCPRestSDK restAPI = new CCPRestSDK();
                restAPI.init("app.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
                restAPI.setAccount("aaf98f894fba2cb2014fcb18dd4a0cf4", "9be90b89b2434b16a7c36571c267432a");// 初始化主帐号和主帐号TOKEN
                restAPI.setAppId("8a48b5514fba2f87014fd022e7e3321b");// 初始化应用ID
                String s = System.currentTimeMillis() + "";
                LogUtils.log(s);
                yanzhen = s.substring(9, s.length());
                LogUtils.log("System.currentTimeMillis()" + yanzhen);
                result = restAPI.sendTemplateSMS(nmb_number.getText().toString(), "36882", new String[]{yanzhen, "2"});
                SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("newyanzhen",yanzhen);
                editor.commit();

                System.out.println("SDKTestSendTemplateSMS result=" + result);
                if ("000000".equals(result.get("statusCode"))) {
                    //正常返回输出data包体信息（map）
                    HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
                    Set<String> keySet = data.keySet();
                    for (String key : keySet) {
                        Object object = data.get(key);
                        System.out.println(key + " = " + object);
                    }
                } else {
                    //异常返回输出错误码和错误信息
                    if (result.get("statusCode").equals("160038")) {
                        Toast.makeText(ModifyTelephone.this, "短信验证码发送过频繁", Toast.LENGTH_SHORT).show();
                    }
                    System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
                }
            }
        }).start();



    }


    public void  getOldRegisterPhoneInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, Object> result = null;
                CCPRestSDK restAPI = new CCPRestSDK();
                restAPI.init("app.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
                restAPI.setAccount("aaf98f894fba2cb2014fcb18dd4a0cf4", "9be90b89b2434b16a7c36571c267432a");// 初始化主帐号和主帐号TOKEN
                restAPI.setAppId("8a48b5514fba2f87014fd022e7e3321b");// 初始化应用ID
                String s = System.currentTimeMillis() + "";
                LogUtils.log(s);
                yanzhengold = s.substring(9, s.length());
                LogUtils.log("System.currentTimeMillis()" + yanzhengold);
                result = restAPI.sendTemplateSMS(omp_number.getText().toString(), "36882", new String[]{yanzhengold, "2"});


                System.out.println("SDKTestSendTemplateSMS result=" + result);
                SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("oldyanzhen",yanzhengold);
                editor.commit();
                if ("000000".equals(result.get("statusCode"))) {
                    //正常返回输出data包体信息（map）
                    HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
                    Set<String> keySet = data.keySet();
                    for (String key : keySet) {
                        Object object = data.get(key);
                        System.out.println(key + " = " + object);
                    }
                } else {
                    //异常返回输出错误码和错误信息
                    if (result.get("statusCode").equals("160038")) {
                        Toast.makeText(ModifyTelephone.this, "短信验证码发送过频繁", Toast.LENGTH_SHORT).show();
//                        Toast.makeText(ModifyTelephone.this, "短信验证码发送过频繁", Toast.LENGTH_SHORT).show();
                    }
                    System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
                }
            }
        }).start();

    }
    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    // Search.clearFocus();
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
}
