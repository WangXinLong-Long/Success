package com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.forgetPassword;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.CodeUtils;
import com.silianchuangye.sumao.success.utils.BPUtil;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 通过邮件找回密码
 */
public class  MailboxPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private Button submit;
    private ImageView verification_code;
    private TextView change;
    private TextView tv_child_title_bar_title;
    private EditText email_num;
    private boolean verificationCode;
    private EditText checkOut;
    private ImageView iv_child_title_bar_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailbox_password);
        iv_child_title_bar_back= (ImageView) findViewById(R.id.iv_child_title_bar_back);
        iv_child_title_bar_back.setOnClickListener(this);
        email_num= (EditText) findViewById(R.id.email_num);
        email_num.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        checkOut= (EditText) findViewById(R.id.checkout);
        checkOut.addTextChangedListener(mTextWatcher);
        tv_child_title_bar_title= (TextView) findViewById(R.id.tv_child_title_bar_title);
        tv_child_title_bar_title.setText("找回密码");
        submit = ((Button) findViewById(R.id.submit));
        submit.setOnClickListener(this);
//        图片验证码
        verification_code = ((ImageView) findViewById(R.id.verification_code));
        verification_code.setImageBitmap(CodeUtils.getInstance().createBitmap());
        change = ((TextView) findViewById(R.id.change));
        change.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        change.setOnClickListener(this);
    }
    //    检测验证码的正确性
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
                if (CodeUtils.getInstance().getCode().equals(checkOut.getText().toString())) {
                    verificationCode = true;
                } else {
                    checkOut.setText("");
                    verification_code.setImageBitmap(CodeUtils.getInstance().createBitmap());
                    verificationCode = false;
                    Toast.makeText(MailboxPasswordActivity.this, "验证码错误请重新输入", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_child_title_bar_back:
                finish();
                break;
            case R.id.submit:
                if (!email_num.getText().toString().contains("@")){
                    Toast.makeText(MailboxPasswordActivity.this, "请输入正确的邮箱格式", Toast.LENGTH_LONG).show();
                }else{
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            cunzai_YesorNo();
                        }
                    }.start();
                }

                break;
            case R.id.change:
                verification_code.setImageBitmap(CodeUtils.getInstance().createBitmap());
                break;
        }
    }

    /**
     * 验证邮箱是否存在
     */
    public void cunzai_YesorNo(){
        RequestParams rp=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/emailValidate");
        rp.addParameter("email",email_num.getText().toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                Log.d("验证成功的返回值",result);
                if (result.contains("success")){
                    send_SuccessorNo();
                }else if (result.contains("fail")){
                    Toast.makeText(MailboxPasswordActivity.this, "该邮箱不存在", Toast.LENGTH_SHORT).show();

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

    /**
     * 邮箱是否发送成功
     */
    public void send_SuccessorNo(){
        RequestParams rp=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/emailSend");
        rp.addParameter("email",email_num.getText().toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("邮箱是否发送成功",result);
                if (result.contains("success")){
                    //跳转到密码修改页
                    Toast.makeText(MailboxPasswordActivity.this, "请查看你的邮箱", Toast.LENGTH_SHORT).show();
                }else if (result.contains("fail")){
                    Toast.makeText(MailboxPasswordActivity.this, "邮箱发送失败，请再次尝试", Toast.LENGTH_SHORT).show();
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

    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    // Search.clearFocus();
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }


}
