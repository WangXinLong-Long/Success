package com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.forgetPassword;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.utils.BPUtil;

/**
 * 通过邮件找回密码
 */
public class MailboxPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private Button submit;
    private ImageView verification_code;
    private TextView change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailbox_password);
        submit = ((Button) findViewById(R.id.submit));
        submit.setOnClickListener(this);
//        图片验证码
        verification_code = ((ImageView) findViewById(R.id.verification_code));
        verification_code.setImageBitmap(BPUtil.getInstance().createBitmap());
        change = ((TextView) findViewById(R.id.change));
        change.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        change.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:

                break;
            case R.id.change:
                verification_code.setImageBitmap(BPUtil.getInstance().createBitmap());
                break;
        }
    }
}
