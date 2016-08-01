package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.LogUtils;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class RegisterValueActivity extends AppCompatActivity {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            add_address,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title, tv, tvUpdate;
    RelativeLayout layoutTop, add_address_rl;
    private EditText ed_content_value;
    private TextView tv_a;
    private Button bt_save_register_value;
    Intent intent;
    String title;
    String content;
    String pass;
    String type;
    String url = "http://192.168.32.126:7023/rest/model/atg/store/profile/RegistrationActor/userVerify";
    SharedPreferences sharePreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_value);
        tv_a = (TextView) findViewById(R.id.tv_a);

        ed_content_value = (EditText) findViewById(R.id.et_content_value);
        bt_save_register_value = (Button) findViewById(R.id.bt_save_register_value);
        intent = new Intent();
       // RegisterFirmActivity registerFirmActivity = new RegisterFirmActivity();

        Bundle bundle = getIntent().getExtras();
        title = bundle.getString("title");
        content = bundle.getString("content");
        pass = bundle.getString("pass");

        if (!content.equals("6-16个字符")) {
            ed_content_value.setText(content);
        }
        title_Bar(title);
        if (!content.equals("")) {
            tv_a.setVisibility(View.VISIBLE);
            tv_a.setText("6-16个字符,可由中英文,数字,'-','_'组成");
        }
        if (title.equals("登录密码") || title.equals("确认密码")) {
            ed_content_value.setText(pass);
            ed_content_value.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else if (title.equals("邮箱")) {
            tv_a.setVisibility(View.VISIBLE);
            tv_a.setText("6-16个字符,可由中英文,数字,'-','_'组成");
            ed_content_value.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        }else if (title.equals("地址详情")){
            tv_a.setText("6-16个字符,可由中英文,数字,'-','_'组成");
        }

//        保存按钮
        bt_save_register_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                需要进行网络请求
                if (title.equals("登录账号")) {
                    type = "login";
                } else if (title.equals("邮箱")) {
                    type = "mail";
                }
                LogUtils.log("ed_content_value------->"+ed_content_value.getText().toString()+"<---");
                if (ed_content_value.getText().toString().trim() == null|| ed_content_value.getText().toString().trim().isEmpty()) {
                    tv_a.setText( title + "不能为空");
                    tv_a.setTextColor(Color.RED);
                    LogUtils.log("tv_a------->"+tv_a.getText().toString()+"<---");
                }else if (type=="login"||type == "mail"){
                    toDetermineWhetherOrNotToRegister(type, ed_content_value.getText().toString().trim());
                }else {
                    setResultW();
                }

            }
        });

    }

    private void toDetermineWhetherOrNotToRegister( String type, String attr) {
        RequestParams rp = new RequestParams(url);
        rp.setCharset("UTF-8");

        rp.addParameter("type", type);
        LogUtils.log("onSuccess------->"+type+"<---onSuccess");
        rp.addParameter("attr", attr.trim());
        LogUtils.log("onSuccess------->"+attr+"<---onSuccess");
        x.http().request(HttpMethod.POST, rp, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                LogUtils.log("onSuccess------->"+result+"<---onSuccess");
                if (result.equals("true")) {
                    setResultW();
                } else {
//                    判断返回值，如果为false，代表不能注册，显示因为那个类型已被注册，不能进行下一步
                    tv_a.setText("该" + title + "已被注册");
                    tv_a.setTextColor(Color.RED);
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

    public void title_Bar(String string) {

        iv_title_bar_back = ((ImageView) findViewById(R.id.iv_title_bar_back));
        iv_title_bar_logo = ((ImageView) findViewById(R.id.iv_title_bar_logo));
        iv_title_bar_service = ((ImageView) findViewById(R.id.iv_title_bar_service));
        iv_title_bar_search = ((ImageView) findViewById(R.id.iv_title_bar_search));
        sv_title_bar_serachView = ((Button) findViewById(R.id.sv_title_bar_serachView));
        tv_title_bar_title = ((TextView) findViewById(R.id.tv_title_bar_title));

        tvUpdate = (TextView) findViewById(R.id.tvUpdate);
        iv_title_bar_logo.setVisibility(View.GONE);
        iv_title_bar_service.setVisibility(View.GONE);
        sv_title_bar_serachView.setVisibility(View.GONE);
        iv_title_bar_search.setVisibility(View.GONE);
        iv_title_bar_back.setVisibility(View.GONE);
        tvUpdate.setVisibility(View.GONE);
        tv_title_bar_title.setText(string);


        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.GONE);
        layoutTop = (RelativeLayout) findViewById(R.id.layoutTop_register_value);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            intent.putExtra("name", "");
            RegisterValueActivity.this.setResult(RESULT_OK, intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    ;

    public void setResultW() {
        /*if (ed_content_value.getText().toString().trim() == null|| ed_content_value.getText().toString().trim().isEmpty()) {
            tv_a.setText( title + "不能为空");
            tv_a.setTextColor(Color.RED);
            tv_a.setVisibility(View.VISIBLE);
            LogUtils.log("tv_a------->"+tv_a.getText().toString()+"<---");
        }else{*/
            intent.putExtra("name", ed_content_value.getText().toString().trim());
            LogUtils.log("ed_content_value------->setResultW:"+ed_content_value.getText().toString()+"<---");
            RegisterValueActivity.this.setResult(RESULT_OK, intent);
            this.finish();
       /* }*/


    }
}
