package com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.forgetPassword.phonePassword.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.forgetPassword.RetrievePasswordActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.forgetPassword.phonePassword.presenter.PhonePasswordPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhone.CountDownTimerUtils;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhoneMVP.RegisterPhonePresenter.RegisterPhonePresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhoneMVP.RegisterPhoneView.IRegisterPhoneView;
import com.silianchuangye.sumao.success.utils.BPUtil;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Set;

/**
 * 通过手机找回密码
 */
public class PhonePasswordActivity extends AppCompatActivity implements View.OnClickListener,IRegisterPhoneView {

    private Button submit;
    private TextView bt_get_register;
    private EditText telephone_num;
    private PhonePasswordPresenter phonePasswordPresenter;
    private CountDownTimerUtils mCountDownTimerUtils;
    private RegisterPhonePresenter registerPhonePresenter;
    private EditText verification_code_num;
    private TextView tv_child_title_bar_title;
    private ImageView verification_code;
    private TextView change;
    private EditText checkOut;
    private boolean verificationCode;
//    验证码
    private String yanzhen = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_password);
        verification_code = ((ImageView) findViewById(R.id.iv_verification_code));
        verification_code.setImageBitmap(BPUtil.getInstance().createBitmap());
        verification_code_num= (EditText) findViewById(R.id.verification_code_num);
        change = ((TextView) findViewById(R.id.change));
        change.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        checkOut= (EditText) findViewById(R.id.checkout);
        checkOut.addTextChangedListener(mTextWatcher);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        tv_child_title_bar_title.setText("找回密码");
       // phonePasswordPresenter = new PhonePasswordPresenter(this);
        registerPhonePresenter = new RegisterPhonePresenter(this);
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
                if (telephone_num.getText().toString()==""||telephone_num.getText().toString()==null){
                    Toast.makeText(PhonePasswordActivity.this, "验证手机号码不能为空", Toast.LENGTH_SHORT).show();
                }
                if (checkOut.getText().toString()==""||checkOut.getText().toString()==null){
                    Toast.makeText(PhonePasswordActivity.this, "图片验证码不能为空", Toast.LENGTH_SHORT).show();
                }
                if (verification_code_num.getText().toString()==""||verification_code_num.getText().toString()==null){
                    Toast.makeText(PhonePasswordActivity.this, "手机验证码不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences sp=getSharedPreferences("sumao",Activity.MODE_PRIVATE);
                    String mianma=sp.getString("yanzhen","");
                    if(!mianma.equals(verification_code_num.getText().toString())){
                        Toast.makeText(PhonePasswordActivity.this, "手机验证码错误", Toast.LENGTH_SHORT).show();
                    }
                }
                        Intent intent = new Intent(PhonePasswordActivity.this,RetrievePasswordActivity.class);
                        startActivity(intent);




                break;

            case R.id.bt_get_register:
                if (telephone_num.getText().toString().length() != 11) {
                    Toast.makeText(PhonePasswordActivity.this, "您输入的手机号不符合规则", Toast.LENGTH_SHORT).show();
                } else {
                    //验证用户是否存在
                   // phonePasswordPresenter.getPasswordForgetPhone(telephone_num.getText().toString());
                    existPhone();
                }
                break;
            case R.id.change:
                verification_code.setImageBitmap(BPUtil.getInstance().createBitmap());
                break;


            default:
                break;
        }
    }
    public void existPhone(){
        RequestParams rp=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/phoneValidate");
        rp.addParameter("phoneNO",telephone_num.getText().toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("验证手机号是否存在","yesOrno");
                if (result.contains("success")){
                    //手机用户存在，发送验证码
                    Log.d("用户存在","用户存在");
                    registerPhonePresenter.sendRegisterPhoneToServer(telephone_num.getText().toString());

                }else{
                    Toast.makeText(PhonePasswordActivity.this, "该手机用户没有进行注册", Toast.LENGTH_SHORT).show();
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
    public void getRegisterPhoneInfo(String result) {
        Log.d("手机号发送验证码",result);
        if (result.equals("false")) {
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
                        //   正常返回输出data包体信息（map）
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
            SharedPreferences sp=getSharedPreferences("sumao", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("yanzhen",yanzhen);
            editor.commit();
        }

    }

    TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart;
        private int editEnd;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            editStart = checkOut.getSelectionStart();
            editEnd = checkOut.getSelectionEnd();
            if (temp.length() == 4) {
                if (BPUtil.getInstance().getCode().equals(checkOut.getText().toString())) {
                    verificationCode = true;
                } else {
                    checkOut.setText("");
                    verification_code.setImageBitmap(BPUtil.getInstance().createBitmap());
                    verificationCode = false;
                    Toast.makeText(PhonePasswordActivity.this, "验证码错误请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
            if (temp.length() > 4) {
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                checkOut.setText(s);
                checkOut.setSelection(tempSelection);
            }
        }

    };


}
