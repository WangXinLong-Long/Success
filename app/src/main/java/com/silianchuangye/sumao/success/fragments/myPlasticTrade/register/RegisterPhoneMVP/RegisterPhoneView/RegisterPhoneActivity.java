package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhoneMVP.RegisterPhoneView;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.view.RegisterFirmActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhone.CountDownTimerUtils;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhoneMVP.RegisterPhonePresenter.RegisterPhonePresenter;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.HashMap;
import java.util.Set;

public class RegisterPhoneActivity extends AppCompatActivity implements IRegisterPhoneView {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            add_address,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv,tvUpdate;
    RelativeLayout layoutTop,add_address_rl;
    private EditText et_phone_register,editText;
    private TextView bt_get_register;
    private TextView tv_next_register;
    private RegisterPhonePresenter registerPhonePresenter;
    private String yanzhen = "";
    private CountDownTimerUtils mCountDownTimerUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_phone);
        registerPhonePresenter = new RegisterPhonePresenter(this);
        title_Bar();
        //电话号码
        et_phone_register= (EditText) findViewById(R.id.et_phone_register);
        //手机验证码
        editText= (EditText) findViewById(R.id.et_phone);
        //获取验证码按钮
        bt_get_register= (TextView) findViewById(R.id.bt_get_register);
        mCountDownTimerUtils = new CountDownTimerUtils(bt_get_register, 30000, 1000);
        /**
         * 获取验证码
         */
        bt_get_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_phone_register.getText().toString().length()!=11){

                    Toast.makeText(RegisterPhoneActivity.this,"您输入的手机号不符合规则"+et_phone_register.getText().toString().length(),Toast.LENGTH_SHORT).show();
                }else {
                    mCountDownTimerUtils.start();
                    registerPhonePresenter.sendRegisterPhoneToServer(et_phone_register.getText().toString());
                }
            }
        });
        tv_next_register= (Button) findViewById(R.id.tv_next_register);
        tv_next_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString() == null||editText.getText().toString().equals("")
                        ||editText.getText().toString().isEmpty()){
                    Toast.makeText(RegisterPhoneActivity.this,"请输入验证码",Toast.LENGTH_SHORT).show();
                }else if (yanzhen.equals(editText.getText().toString())){
                    Intent intent=new Intent(RegisterPhoneActivity.this,RegisterActivity.class);
                    intent.putExtra("phone",et_phone_register.getText().toString());
                    startActivity(intent);
                }else {
                    Toast.makeText(RegisterPhoneActivity.this,"验证码错误",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void title_Bar(){

        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title  = ((TextView) findViewById(R.id.tv_title_bar_title));

        tvUpdate= (TextView) findViewById(R.id.tvUpdate);
        iv_title_bar_logo.setVisibility(View.GONE);
        iv_title_bar_service.setVisibility(View.GONE);
        sv_title_bar_serachView.setVisibility(View.GONE);
        iv_title_bar_search.setVisibility(View.GONE);

        tvUpdate.setVisibility(View.GONE);
        tv_title_bar_title.setText("注册账号");


        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop= (RelativeLayout) findViewById(R.id.layoutTop_register);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterPhoneActivity.this.finish();
            }
        });

    }

    @Override
    public void getRegisterPhoneInfo(String results) {

        if (results.equals("true")){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HashMap<String, Object> result = null;
                    CCPRestSDK restAPI = new CCPRestSDK();
                    restAPI.init("app.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
                    restAPI.setAccount("aaf98f894fba2cb2014fcb18dd4a0cf4", "9be90b89b2434b16a7c36571c267432a");// 初始化主帐号和主帐号TOKEN
                    restAPI.setAppId("8a48b5514fba2f87014fd022e7e3321b");// 初始化应用ID
                    String s = System.currentTimeMillis()+"";
                    LogUtils.log(s);
                    yanzhen = s.substring(9,s.length());
                    LogUtils.log("System.currentTimeMillis()"+ yanzhen);
                    result = restAPI.sendTemplateSMS(et_phone_register.getText().toString(), "36882", new String[]{yanzhen, "2"});


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
                        if (result.get("statusCode").equals("160038")){
                            Toast.makeText(RegisterPhoneActivity.this,"短信验证码发送过频繁",Toast.LENGTH_SHORT).show();
                        }
                        System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
                    }
                }
            }).start();

        }else {
            Toast.makeText(this, "该手机号已注册", Toast.LENGTH_SHORT).show();
        }
    }
}
