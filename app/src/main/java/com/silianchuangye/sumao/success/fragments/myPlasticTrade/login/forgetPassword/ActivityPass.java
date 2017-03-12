package com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.forgetPassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.LoginUserActivity;

public class ActivityPass extends AppCompatActivity {
    private TextView tv_child_title_bar_title;
    private Button bt_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_pass);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        bt_Login= (Button) findViewById(R.id.bt_login);
        tv_child_title_bar_title.setText("找回密码");
        bt_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityPass.this, LoginUserActivity.class);
                startActivity(intent);
            }
        });

    }
}
