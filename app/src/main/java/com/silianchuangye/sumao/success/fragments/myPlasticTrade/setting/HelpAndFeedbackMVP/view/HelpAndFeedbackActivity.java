package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.HelpAndFeedbackMVP.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.HelpAndFeedbackMVP.presenter.HelpAndFeedbackPresenter;

public class HelpAndFeedbackActivity extends AppCompatActivity implements View.OnClickListener,IHelpAndFeedbackView {

    private TextView tv_child_title_bar_title;
    private ImageView iv_child_title_bar_back;
    private EditText telephone;
    private EditText feedbackInformation;
    private HelpAndFeedbackPresenter helpAndFeedbackPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helpAndFeedbackPresenter = new HelpAndFeedbackPresenter(this);
        setContentView(R.layout.activity_help_and_feedback);
        tv_child_title_bar_title = ((TextView) findViewById(R.id.tv_child_title_bar_title));
        tv_child_title_bar_title.setText("帮助与反馈");
        iv_child_title_bar_back = ((ImageView) findViewById(R.id.iv_child_title_bar_back));
        iv_child_title_bar_back.setOnClickListener(this);
//        保存
        Button determine = (Button) findViewById(R.id.btSave_password_update);
        determine.setOnClickListener(this);
//        手机号
        telephone = (EditText) findViewById(R.id.etold_password_update);
//        反馈信息
        feedbackInformation = (EditText) findViewById(R.id.etnew_password_update);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_child_title_bar_back:
                this.finish();
                break;
            case R.id.btSave_password_update:
                String telephoneNumber = telephone.getText().toString();
                String feedbackText = feedbackInformation.getText().toString();
                if (telephoneNumber.isEmpty()||telephoneNumber.equals(""))
                {
                    Toast.makeText(this,"请输入手机号",Toast.LENGTH_SHORT).show();
                }else if (telephoneNumber.length()!=13)
                {
                    Toast.makeText(this,"输入手机号的手机号不符合规范",Toast.LENGTH_SHORT).show();
                }else if (feedbackText.isEmpty()||feedbackText.equals("")){
                    Toast.makeText(this,"输入反馈信息",Toast.LENGTH_SHORT).show();
                }else {
                    helpAndFeedbackPresenter.sendMessageInServer(telephoneNumber,feedbackText,"账号");
                }
                break;

        }
    }

    @Override
    public void getResultInActivity() {

    }
}
