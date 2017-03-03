package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoPicture.FirmInfoPictureMVP;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;

/**
 * 填写统一社会信用代码
 */
public class UniformSocialCreditCode extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_child_title_bar_title;
    private Button bt_save_register_value;
    private EditText et_content_value;
    private TextView tv_a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uniform_social_credit_code);
//        统一社会信用代码
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("统一社会信用代码");
//        保存
        bt_save_register_value = ((Button) findViewById(R.id.bt_save_register_value));
        bt_save_register_value.setOnClickListener(this);
//        填写的内容
        et_content_value = ((EditText) findViewById(R.id.et_content_value));
        tv_a = ((TextView) findViewById(R.id.tv_a));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_save_register_value:
                String content = et_content_value.getText().toString();
                if (TextUtils.isEmpty(content)){
                    tv_a.setText("统一社会信用代码不能为空");
                    tv_a.setVisibility(View.VISIBLE);
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("result",content);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
        }
    }
}
