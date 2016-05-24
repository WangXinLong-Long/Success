package com.silianchuangye.sumao.success.fragments.login;

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

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.register.RegisterActivity;

public class LoginUserActivity extends AppCompatActivity {
    ImageView iv_title_bar_logo,
            iv_title_bar_back,
            iv_title_bar_service,
            add_address,
            iv_title_bar_search;
    Button sv_title_bar_serachView;
    TextView tv_title_bar_title,tv,tvUpdate;
    RelativeLayout layoutTop,add_address_rl;
    private EditText et_account_login;
    private EditText et_pass_login;
    private Button bt_Login;
    private TextView tv_register,tv_findpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        title_Bar();
        et_account_login= (EditText) findViewById(R.id.et_account_login);
        et_pass_login= (EditText) findViewById(R.id.et_pass_login);
        bt_Login= (Button) findViewById(R.id.bt_login);
        bt_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登录功能
            }
        });
        tv_register= (TextView) findViewById(R.id.tv_register_login);
        tv_findpass= (TextView) findViewById(R.id.tv_findpass_login);
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginUserActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        tv_findpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //跳转找回密码界面
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
        tv_title_bar_title.setText("登录账号");


        tv_title_bar_title.setTextColor(Color.WHITE);
        iv_title_bar_back.setVisibility(View.VISIBLE);
        layoutTop= (RelativeLayout) findViewById(R.id.layoutTop_Login);
        layoutTop.setBackgroundColor(getResources().getColor(R.color.textColor_expandable_listview_show));

        iv_title_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUserActivity.this.finish();
            }
        });

    }
}
