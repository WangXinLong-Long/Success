package com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.forgetPassword.phonePassword.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.forgetPassword.RetrievePasswordActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.forgetPassword.phonePassword.presenter.PhonePasswordPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhone.CountDownTimerUtils;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.HashMap;
import java.util.Set;

/**
 * 通过手机找回密码
 */
public class PhonePasswordActivity extends AppCompatActivity implements View.OnClickListener,IPhonePasswordView {

    private Button submit;
    private TextView bt_get_register;
    private EditText telephone_num;
    private PhonePasswordPresenter phonePasswordPresenter;
    private CountDownTimerUtils mCountDownTimerUtils;
//    验证码
    private String yanzhen = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_password);
        phonePasswordPresenter = new PhonePasswordPresenter(this);
//        立即提交
        submit = ((Button) findViewById(R.id.submit));
        submit.setOnClickListener(this);
//        获取验证码
        bt_get_register = ((TextView) findViewById(R.id.bt_get_register));
        bt_get_register.setOnClickListener(this);
//        手机号
        telephone_num = ((EditText) findViewById(R.id.telephone_num));
        telephone_num.setOnClickListener(this);

//        计时器，30s内不能发送验证码
        mCountDownTimerUtils = new CountDownTimerUtils(bt_get_register, 30000, 1000);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
                Intent intent = new Intent(PhonePasswordActivity.this,RetrievePasswordActivity.class);
                startActivity(intent);
                break;

            case R.id.bt_get_register:
                if (telephone_num.getText().toString().length() != 11) {

                    Toast.makeText(PhonePasswordActivity.this, "您输入的手机号不符合规则", Toast.LENGTH_SHORT).show();
                } else {
                    phonePasswordPresenter.getPasswordForgetPhone(telephone_num.getText().toString());
                }
                break;


            default:
                break;
        }
    }

//    获取是否注册过该手机号，如果注册过，发送验证码，如果没有注册过，不能发送验证码
    @Override
    public void getRegistrationInformation(String results) {
        if (results.equals("true")) {
            mCountDownTimerUtils.start();
            String s = System.currentTimeMillis() + "";
            LogUtils.log(s);
            yanzhen = s.substring(9, s.length());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HashMap<String, Object> result = null;
                    CCPRestSDK restAPI = new CCPRestSDK();
                    restAPI.init("app.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
                    restAPI.setAccount("aaf98f894fba2cb2014fcb18dd4a0cf4", "9be90b89b2434b16a7c36571c267432a");// 初始化主帐号和主帐号TOKEN
                    restAPI.setAppId("8a48b5514fba2f87014fd022e7e3321b");// 初始化应用ID

                    LogUtils.log("System.currentTimeMillis()" + yanzhen);
                    result = restAPI.sendTemplateSMS(telephone_num.getText().toString(), "36882", new String[]{yanzhen, "2"});
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
                            Toast.makeText(PhonePasswordActivity.this, "短信验证码发送过频繁", Toast.LENGTH_SHORT).show();
                        }
                        System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
                    }
                }
            }).start();
            Toast.makeText(this, yanzhen, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "该手机号未进行注册", Toast.LENGTH_SHORT).show();
        }
    }
}
