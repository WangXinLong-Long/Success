package com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.forgetPassword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.forgetPassword.phonePassword.view.PhonePasswordActivity;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout retrieve_by_phone_number;
    private RelativeLayout retrieve_by_email;
    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(this);
        retrieve_by_phone_number = ((RelativeLayout) findViewById(R.id.retrieve_by_phone_number));
        retrieve_by_email = ((RelativeLayout) findViewById(R.id.retrieve_by_email));
        retrieve_by_phone_number.setOnClickListener(this);
        retrieve_by_email.setOnClickListener(this);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        tv_child_title_bar_title.setText("找回密码");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_child_title_bar_back:
                ForgetPasswordActivity.this.finish();
                break;
            case R.id.retrieve_by_phone_number:
                Intent intent = new Intent();
                intent.setClass(ForgetPasswordActivity.this,PhonePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.retrieve_by_email:
                Intent intent1 = new Intent();
                intent1.setClass(ForgetPasswordActivity.this,MailboxPasswordActivity.class);
                startActivity(intent1);
                break;

            default:
                break;
        }

    }
}
