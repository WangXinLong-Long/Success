package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.HelpAndFeedbackMVP.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.HelpAndFeedbackMVP.bean.HelpAndFeedbackBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.HelpAndFeedbackMVP.presenter.HelpAndFeedbackPresenter;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelpAndFeedbackActivity extends AppCompatActivity implements View.OnClickListener, IHelpAndFeedbackView {

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
        switch (v.getId()) {
            case R.id.iv_child_title_bar_back:
                this.finish();
                break;
            case R.id.btSave_password_update:
                String telephoneNumber = telephone.getText().toString();
                String feedbackText = feedbackInformation.getText().toString();
                if (telephoneNumber.isEmpty() || telephoneNumber.equals("")) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                } else if (!isMobileNO(telephoneNumber)) {
                    Toast.makeText(this, "输入手机号的手机号不符合规范", Toast.LENGTH_SHORT).show();
                } else if (feedbackText.isEmpty() || feedbackText.equals("")) {
                    Toast.makeText(this, "输入反馈信息", Toast.LENGTH_SHORT).show();
                } else {
                    String userName = getSharedPreferences("sumao", Activity.MODE_PRIVATE).getString("zhanghao", "");
                    LogUtils.log("userName" + userName);
                    helpAndFeedbackPresenter.sendMessageInServer(telephoneNumber, feedbackText, userName);
                }
                break;

        }
    }

    public  boolean isMobileNO(String mobiles) {

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

        Matcher m = p.matcher(mobiles);

        System.out.println(m.matches() + "---");

        return m.matches();

    }

    @Override
    public void getResultInActivity(HelpAndFeedbackBean helpAndFeedbackBean) {
        String info = helpAndFeedbackBean.getResult().get(0).getInformation();
        if (info.contains("成功")) {
            Toast.makeText(this, "上传成功，感谢您的反馈", Toast.LENGTH_SHORT).show();
            finish();
        } else if (info.contains("失败")) {
            Toast.makeText(this, "上传失败，请在试一次", Toast.LENGTH_SHORT).show();
        } else if (info.contains("无参数")) {
            Toast.makeText(this, "重新检查参数", Toast.LENGTH_SHORT).show();

        }

    }
}
